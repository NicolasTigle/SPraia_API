package br.com.zeniti.spraia.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zeniti.spraia.model.Praia;

public interface PraiaRepository extends JpaRepository<Praia, Long>{
    
}
