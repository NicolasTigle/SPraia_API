package br.com.zeniti.spraia.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zeniti.spraia.model.Praia;
import br.com.zeniti.spraia.repository.PraiaRepository;

@Service
public class PraiaService {
    
    @Autowired
    PraiaRepository praiaRepository;
    
    public Page<Praia> listAll(Pageable paginacao){
        return praiaRepository.findAll(paginacao);
    }

    public void save(Praia praia){
        praiaRepository.save(praia);
    }

    public Optional<Praia> get(Long id){
        return praiaRepository.findById(id);
    }

    public void deleteById(Long id) {
        praiaRepository.deleteById(id);
    }
}
