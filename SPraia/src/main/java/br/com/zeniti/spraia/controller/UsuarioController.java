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

import br.com.zeniti.spraia.dto.dtoGet.UsuarioDtoGet;
import br.com.zeniti.spraia.dto.dtoPost.MapperPost;
import br.com.zeniti.spraia.dto.dtoPost.UsuarioDtoPost;
import br.com.zeniti.spraia.dto.dtoPut.UsuarioDtoPut;
import br.com.zeniti.spraia.dto.dtoGet.MapperGet;
import br.com.zeniti.spraia.model.Usuario;
import br.com.zeniti.spraia.service.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService service;

    @GetMapping
    public Page<UsuarioDtoGet> index(@PageableDefault(size=5, sort = "id") Pageable paginacao){
        return service.listAll(paginacao).map(MapperGet::convertToDto);
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody @Valid UsuarioDtoPost usuarioDtoPost){
        Usuario usuario = MapperPost.convertToObj(usuarioDtoPost);
        service.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioDtoGet> show(@PathVariable Long id){
       return ResponseEntity.of(service.get(id).map(MapperGet::convertToDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
                
        Optional<Usuario> optional = service.get(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody @Valid UsuarioDtoPut newUsuarioPut){

        Optional<Usuario> optional = service.get(id);

        if(optional.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        Usuario usuario = optional.get();
        BeanUtils.copyProperties(newUsuarioPut, usuario);
        usuario.setId(id);

        service.save(usuario);

        return ResponseEntity.ok(usuario);

    }
}
