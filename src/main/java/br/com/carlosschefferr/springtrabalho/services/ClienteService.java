package br.com.carlosschefferr.springtrabalho.services;

import br.com.carlosschefferr.springtrabalho.domain.Pessoa;
import br.com.carlosschefferr.springtrabalho.domain.Cliente;
import br.com.carlosschefferr.springtrabalho.domain.enums.Perfil;
import br.com.carlosschefferr.springtrabalho.dto.ClienteDTO;
import br.com.carlosschefferr.springtrabalho.repositories.PessoaRepository;
import br.com.carlosschefferr.springtrabalho.repositories.ClienteRepository;
import br.com.carlosschefferr.springtrabalho.services.exceptions.DataIntegrityViolationException;
import br.com.carlosschefferr.springtrabalho.services.exceptions.ObjectNotFoundException;
import br.com.carlosschefferr.springtrabalho.mapper.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ClienteMapper mapper; 

    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Cliente newObj = mapper.toEntity(objDTO); 
        newObj.addPerfil(Perfil.CLIENTE); 
        return repository.save(newObj);
    }

    public Cliente update(Long id, ClienteDTO objDTO) {
        objDTO.setId(id);
        Cliente oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        mapper.updateEntityFromDto(objDTO, oldObj); 
        return repository.save(oldObj);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}
