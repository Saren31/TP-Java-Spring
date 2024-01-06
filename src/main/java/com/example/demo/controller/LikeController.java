package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Article;
import com.example.demo.entity.Liker;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.LikerRepository;
import com.example.demo.repository.UtilisateurRepository;

@Controller
@RequestMapping(path="/like")
public class LikeController {

	@Autowired
	private LikerRepository likeRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@PreAuthorize("hasAnyAuthority('ROLE_PUBLISHER')")
	@PostMapping
	public @ResponseBody String addLike (@RequestParam Integer auteurId, 
			@RequestParam Integer articleId,
			@RequestParam Boolean like) {
		 Article article = articleRepository.findById(articleId).orElseThrow();
		 Utilisateur auteur = utilisateurRepository.findById(auteurId).orElseThrow();
	  
		 Liker l = likeRepository.findByUserIdAndArticleId(auteurId, articleId);
	     if (l != null && l.getLike() == like) {
	    	 if (like == true)
	    		 return auteur.getNom() + " a déjà liké l'article";
	    	 return auteur.getNom() + " a déjà disliké l'article";
	     }
	     if (l == null) {
	    	 l = new Liker();
	    	 l.setArticle(article);
	    	 l.setUser(auteur);
	     }
	 	 l.setLike(like);
	    
	 	 likeRepository.save(l);
	 	 if (like == true)
	 		 return "Like ajouté";
	 	 return "Dislike ajouté";
	}
	
	@GetMapping
	public @ResponseBody Iterable<Liker> getLikes() {
		return likeRepository.findAll();
	}
	 
	@GetMapping(path="/user/{id}")
	public @ResponseBody Iterable<Liker> getLikesByUserId(@PathVariable Integer id) {
		if (!utilisateurRepository.existsById(id)) {
			return null;
		}
		return likeRepository.findAllByUserId(id);
	}
	
	@GetMapping(path="/article/{id}")
	public @ResponseBody Iterable<Liker> getLikesByArticleId(@PathVariable Integer id) {
		if (!articleRepository.existsById(id)) {
			return null;
		}
		return likeRepository.findAllByArticleId(id);
	}
	 
	@DeleteMapping
	public @ResponseBody String deleteLike(@RequestParam Integer auteurId,
			@RequestParam Integer articleId) {
		Liker l = likeRepository.findByUserIdAndArticleId(auteurId, articleId);
		if (l != null) {
			likeRepository.delete(l);
			return "Like supprimé";
		}
		return "Like n'existe pas";
	}
}
