package br.com.zeniti.spraia.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zeniti.spraia.model.Denuncia;
import br.com.zeniti.spraia.service.DenunciaService;

@RestController
@RequestMapping("/api/denuncia")
public class DenunciaController {
    
    @Autowired
    private DenunciaService service;

    @GetMapping
    public Page<Denuncia> index(@PageableDefault(size=5,sort = "id") Pageable paginacao){
        return service.listAll(paginacao);
    }

    @PostMapping
    public ResponseEntity<Denuncia> create(@RequestBody @Valid Denuncia denuncia){
        service.save(denuncia);
        return ResponseEntity.status(HttpStatus.CREATED).body(denuncia);
    }

    @GetMapping("{id}")
    public ResponseEntity<Denuncia> show(@PathVariable Long id){
       return ResponseEntity.of(service.get(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Denuncia> optional = service.get(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Denuncia> update(@PathVariable Long id, @RequestBody @Valid Denuncia newDenuncia){

        Optional<Denuncia> optional = service.get(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Denuncia denuncia = optional.get();
        BeanUtils.copyProperties(newDenuncia, denuncia);
        denuncia.setId(id);

        service.save(denuncia);

        return ResponseEntity.ok(denuncia);

    }
}
