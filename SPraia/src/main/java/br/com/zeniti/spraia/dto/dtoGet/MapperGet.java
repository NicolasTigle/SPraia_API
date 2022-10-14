package br.com.zeniti.spraia.dto.dtoGet;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import br.com.zeniti.spraia.model.Usuario;

@Component
public class MapperGet {
    public static UsuarioDtoGet convertToDto(Usuario usuario) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataNascimento = usuario.getDataNascimento().format(formatter);
        return new UsuarioDtoGet(usuario.getId(), usuario.getNome(), usuario.getEmail(), dataNascimento);

    }
}
