package com.fiap.diversidade_esg.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.fiap.diversidade_esg.entidades.Funcionario;
import com.fiap.diversidade_esg.repositorios.FuncionarioRepository;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository repository;

  
    @GetMapping
    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }

    
    @GetMapping("/pcd")
    public List<Funcionario> listarPcd() {
        return repository.findByPcdTrue();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario criar(@RequestBody Funcionario funcionario) {
        return repository.save(funcionario);
    }
}