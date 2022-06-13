package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.entities.Usuario;




@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@GetMapping("/")
	public List<Usuario> getAllUsuarios(){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = (List<Usuario>) usuarioDao.findAll();
		
		return usuarios;
	}
	
	@GetMapping("/{id}")
	public Usuario postUsuario(@PathVariable Integer id) {
		Optional usuario = usuarioDao.findById(id);
		if(usuario.isPresent()) {
			Usuario u =  (Usuario) usuario.get();
			return u;
		}
		return null;	
	}
	
	public Usuario postUsuario(@RequestBody Usuario user) {
		
		Usuario entity = new Usuario();
		entity.setNombre(user.getNombre());
		entity.setEmail(user.getEmail());
		entity.setPais(user.getPais());
		
		
		usuarioDao.save(entity);
		
		return entity;
		
	}
	

	@PutMapping("/{id}")
	public Usuario putObject(@PathVariable Integer id, @RequestBody Usuario userIn) {
		Optional user = usuarioDao.findById(id);
		
		if(user.isPresent()) {
			Usuario u = new Usuario();
			u.setId(id);
			u.setNombre(userIn.getNombre());
			u.setEmail(userIn.getEmail());
			u.setPais(userIn.getPais());
			
			usuarioDao.save(u);
			return u;
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public Usuario deleteObject(@PathVariable Integer id) {
		Optional user = usuarioDao.findById(id);
		
		if(user.isPresent()) {
			Usuario u = (Usuario) user.get();
			usuarioDao.delete(u);
			return u;
		}
		
		return null;
	}

	
	

}
