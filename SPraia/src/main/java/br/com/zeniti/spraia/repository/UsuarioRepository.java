package br.com.zeniti.spraia.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zeniti.spraia.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);
}
