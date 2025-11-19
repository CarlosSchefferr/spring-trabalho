package br.com.carlosschefferr.springtrabalho.services;

import br.com.carlosschefferr.springtrabalho.domain.Pessoa;
import br.com.carlosschefferr.springtrabalho.domain.Tecnico;
import br.com.carlosschefferr.springtrabalho.domain.enums.Perfil;
import br.com.carlosschefferr.springtrabalho.dto.TecnicoDTO;
import br.com.carlosschefferr.springtrabalho.repositories.PessoaRepository;
import br.com.carlosschefferr.springtrabalho.repositories.TecnicoRepository;
import br.com.carlosschefferr.springtrabalho.services.exceptions.DataIntegrityViolationException;
import br.com.carlosschefferr.springtrabalho.services.exceptions.ObjectNotFoundException;
import br.com.carlosschefferr.springtrabalho.mapper.TecnicoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private TecnicoMapper mapper; 

    @Transactional(readOnly = true)
    public Tecnico findById(Long id) {
        Optional<Tecnico> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Tecnico newObj = mapper.toEntity(objDTO); 
        newObj.addPerfil(Perfil.TECNICO); 
        return repository.save(newObj);
    }

    public Tecnico update(Long id, TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        mapper.updateEntityFromDto(objDTO, oldObj); 
        return repository.save(oldObj);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
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
