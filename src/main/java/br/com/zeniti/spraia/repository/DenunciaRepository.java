package br.com.zeniti.spraia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zeniti.spraia.model.Denuncia;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long>{
    
}
