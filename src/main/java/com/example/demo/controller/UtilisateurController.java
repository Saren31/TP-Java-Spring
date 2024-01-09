package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Role;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repository.UtilisateurRepository;

@Controller
@RequestMapping(path="/user")
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@PreAuthorize("hasAuthority('ROLE_MODERATOR')")
	@PostMapping
	public @ResponseBody String addUser (@RequestParam String nom,
			@RequestParam String password, @RequestParam Role role) {

	    Utilisateur n = new Utilisateur();
	    n.setNom(nom);
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    n.setPassword(encoder.encode(password));
	    n.setRole(role);
	    utilisateurRepository.save(n);
	    return "Sauvegardé";
	  }
	 
	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	@GetMapping
	public @ResponseBody Iterable<Utilisateur> getUsers() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.getName());
		return utilisateurRepository.findAll();
	}
	 
	@PreAuthorize("hasAuthority('ROLE_MODERATOR')")
	@GetMapping(path="/{id}")
	public @ResponseBody Utilisateur getUser(@PathVariable Integer id) {
		return utilisateurRepository.findById(id).orElse(null);
	}	
	 
	@PreAuthorize("hasAuthority('ROLE_MODERATOR')")
	@PutMapping(path="/{id}")
	public @ResponseBody Utilisateur changeUtilisateur(@PathVariable Integer id,
			@RequestParam(required = false) String nom,
			@RequestParam(required = false) String password, 
			@RequestParam(required = false) Role role) {
		Utilisateur n = utilisateurRepository.findById(id).orElseThrow();
		if (nom != null) {
			n.setNom(nom);
		}
		if (password != null) {
			n.setPassword(password);
		}
		if (role != null) {
			n.setRole(role);
		}
		utilisateurRepository.save(n);
		return n;
	}

	@PreAuthorize("hasRole('ROLE_MODERATOR')")
	@DeleteMapping(path="/{id}")
	public @ResponseBody String deleteUtilisateur(@PathVariable Integer id) {
		utilisateurRepository.deleteById(id);
		return "Supprimé";
	}

}
