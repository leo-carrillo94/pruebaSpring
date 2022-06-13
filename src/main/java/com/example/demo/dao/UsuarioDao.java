package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entities.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Integer> {

}
