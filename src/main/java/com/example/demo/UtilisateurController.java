package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class UtilisateurController {

	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	 @PostMapping(path="/add") // Map ONLY POST Requests
	  public @ResponseBody String addUser (@RequestParam String nom
	      , @RequestParam String password, @RequestParam String role) {

	    Utilisateur n = new Utilisateur();
	    n.setNom(nom);
	    n.setPassword(password);
	    n.setRole(role);
	    utilisateurRepository.save(n);
	    return "Saved";
	  }
	 
	 @GetMapping(path="/all")
	  public @ResponseBody Iterable<Utilisateur> getUsers() {
	    // This returns a JSON or XML with the users
	    return utilisateurRepository.findAll();
	  }
	
}
