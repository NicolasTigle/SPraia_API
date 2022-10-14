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

import br.com.zeniti.spraia.model.Praia;
import br.com.zeniti.spraia.service.PraiaService;


@RestController
@RequestMapping("/api/praia")
public class PraiaController {
    
    @Autowired
    private PraiaService service;

    @GetMapping
    public Page<Praia> index(@PageableDefault(size=5,sort = "id") Pageable paginacao){
        return service.listAll(paginacao);
    }

    @PostMapping
    public ResponseEntity<Praia> create(@RequestBody @Valid Praia praia){
        service.save(praia);
        return ResponseEntity.status(HttpStatus.CREATED).body(praia);
    }

    @GetMapping("{id}")
    public ResponseEntity<Praia> show(@PathVariable Long id){
       return ResponseEntity.of(service.get(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Praia> optional = service.get(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Praia> update(@PathVariable Long id, @RequestBody @Valid Praia newPraia){

        Optional<Praia> optional = service.get(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Praia praia = optional.get();
        BeanUtils.copyProperties(newPraia, praia);
        praia.setId(id);

        service.save(praia);

        return ResponseEntity.ok(praia);

    }
}
