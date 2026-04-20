package com.fiap.diversidade_esg.repositorios;

import com.fiap.diversidade_esg.entidades.Funcionario;
import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface FuncionarioRepository extends MongoRepository<Funcionario, Integer> {
 
    
    List<Funcionario> findByPcdTrue();
}
