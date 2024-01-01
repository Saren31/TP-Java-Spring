package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Article;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.UtilisateurRepository;

@Controller
@RequestMapping(path="/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@PostMapping
	public @ResponseBody String addArticle (@RequestParam String contenu, 
			@RequestParam(required = false) LocalDate date,
			@RequestParam String titre,
			@RequestParam Integer auteurId) {

		Article n = new Article();
		if (date != null) {
			n.setDatePublication(date);
		}
		else {
			n.setDatePublication(LocalDate.now());
		}
	    n.setContenu(contenu);
	    n.setTitre(titre);
	    n.setUser(utilisateurRepository.findById(auteurId).orElseThrow());
	    articleRepository.save(n);
	    return "Sauvegardé";
	  }
	 
	@GetMapping
	public @ResponseBody Iterable<Article> getArticles() {
		return articleRepository.findAll();
	}
	 
	@GetMapping(path="/{id}")
	public @ResponseBody Article getArticleById(@PathVariable Integer id) {
		return articleRepository.findById(id).orElse(null);
	}
	 
	@PutMapping(path="/{id}")
	 public @ResponseBody Article changeArticle(@PathVariable Integer id, 
			 @RequestParam(required = false) String contenu, 
			 @RequestParam(required = false) String titre, 
			 @RequestParam(required = false) LocalDate date,
			 @RequestParam(required = false) Integer auteurId ) {
		 Article a = articleRepository.findById(id).orElseThrow();
		 if (date != null) {
			 a.setDatePublication(date);
		 }
		 if (contenu != null) {
			 a.setContenu(contenu);
		 }
		 if (titre != null) {
			 a.setTitre(titre);
		 }
		 if (auteurId != null) {
			 a.setUser(utilisateurRepository.findById(auteurId).get());
		 }		 
		 articleRepository.save(a);
		 return a;
	 }
	 
	 @DeleteMapping(path="/{id}")
	 public @ResponseBody String deleteArticle(@PathVariable Integer id) {
		 articleRepository.deleteById(id);
		 return "Supprimé";
	 }
	
}
