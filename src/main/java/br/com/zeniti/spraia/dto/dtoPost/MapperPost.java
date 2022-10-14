package br.com.zeniti.spraia.dto.dtoPost;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import br.com.zeniti.spraia.model.Usuario;

@Component
public class MapperPost {

    public static Usuario convertToObj(UsuarioDtoPost usuarioDtoPost){
        LocalDate dataNascimento =  LocalDate.parse(usuarioDtoPost.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new Usuario(usuarioDtoPost.getNome(), usuarioDtoPost.getEmail(), usuarioDtoPost.getSenha(), dataNascimento);
    }

}
