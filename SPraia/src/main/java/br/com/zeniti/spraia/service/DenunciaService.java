package br.com.zeniti.spraia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zeniti.spraia.model.Denuncia;
import br.com.zeniti.spraia.repository.DenunciaRepository;

@Service
public class DenunciaService {

    @Autowired
    DenunciaRepository denunciaRepository;
    
    public Page<Denuncia> listAll(Pageable paginacao){
        return denunciaRepository.findAll(paginacao);
    }

    public void save(Denuncia denuncia){
        denunciaRepository.save(denuncia);
    }

    public Optional<Denuncia> get(Long id){
        return denunciaRepository.findById(id);
    }

    public void deleteById(Long id) {
        denunciaRepository.deleteById(id);
    }
    
}
