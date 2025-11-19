package br.com.carlosschefferr.springtrabalho.controller;

import br.com.carlosschefferr.springtrabalho.domain.Chamado;
import br.com.carlosschefferr.springtrabalho.dto.ChamadoDTO;
import br.com.carlosschefferr.springtrabalho.mapper.ChamadoMapper;
import br.com.carlosschefferr.springtrabalho.services.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @Autowired
    private ChamadoMapper mapper;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Long id) {
        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(mapper.toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> list = service.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO) {
        Chamado newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Long id, @Valid @RequestBody ChamadoDTO objDTO) {
        Chamado obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(mapper.toDto(obj));
    }
}