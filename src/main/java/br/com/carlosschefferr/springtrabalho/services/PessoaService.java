package br.com.carlosschefferr.springtrabalho.services;

import br.com.carlosschefferr.springtrabalho.domain.Pessoa;
import br.com.carlosschefferr.springtrabalho.repositories.PessoaRepository;
import br.com.carlosschefferr.springtrabalho.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public Pessoa findById(Long id) {
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public Pessoa findByCpf(String cpf) {
        Optional<Pessoa> obj = repository.findByCpf(cpf);
        return obj.orElse(null);
    }

    public Pessoa findByEmail(String email) {
        Optional<Pessoa> obj = repository.findByEmail(email);
        return obj.orElse(null);
    }
}
