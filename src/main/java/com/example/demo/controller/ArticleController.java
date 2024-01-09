package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.example.demo.entity.ArticleDTO;
import com.example.demo.entity.ArticleModerator;
import com.example.demo.entity.ArticlePublisher;
import com.example.demo.entity.Role;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.LikerRepository;
import com.example.demo.repository.UtilisateurRepository;

@Controller
@RequestMapping(path="/article")
public class ArticleController {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private LikerRepository likeRepository;
	
	
	@PreAuthorize("hasAuthority('ROLE_PUBLISHER')")
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
	public @ResponseBody Object getArticles() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth instanceof AnonymousAuthenticationToken) {
			return articleRepository.getAllArticlesNoAuth();
		}
		else if (utilisateurRepository.findByNom(auth.getName()).getRole() == Role.PUBLISHER.toString() ){
			List<ArticleDTO> articles = articleRepository.getAllArticlesNoAuth();
			List<ArticlePublisher> resultats = new ArrayList<ArticlePublisher>();
			for (ArticleDTO a : articles) {
				ArticlePublisher res = new ArticlePublisher(a.getTitre(), a.getDatePublication(), a.getNomAuteur(), a.getContenu());
				res.setNbLikes(likeRepository.countLikesByArticleAndAuteur(a.getTitre(), auth.getName()));
				res.setNbDislikes(likeRepository.countDislikesByArticleAndAuteur(a.getTitre(), auth.getName()));
				resultats.add(res);
			}
			return resultats;
		}
		else if (utilisateurRepository.findByNom(auth.getName()).getRole() == Role.MODERATOR.toString() ){
			List<ArticleDTO> articles = articleRepository.getAllArticlesNoAuth();
			List<ArticlePublisher> resultats = new ArrayList<ArticlePublisher>();
			for (ArticleDTO a : articles) {
				ArticleModerator res = new ArticleModerator(a.getTitre(), a.getDatePublication(), a.getNomAuteur(), a.getContenu());
				res.setNbLikes(likeRepository.countLikesByArticleAndAuteur(a.getTitre(), auth.getName()));
				res.setNbDislikes(likeRepository.countDislikesByArticleAndAuteur(a.getTitre(), auth.getName()));
				res.setListeUsersLikes(likeRepository.findUsersWhoLikedArticle(a.getTitre(), auth.getName()));
				res.setListeUsersDislikes(likeRepository.findUsersWhoDislikedArticle(a.getTitre(), auth.getName()));
				resultats.add(res);
			}
			return resultats;
		}
		return articleRepository.findAll();
	}
	
	@PreAuthorize("hasAuthority('ROLE_PUBLISHER')")
	@GetMapping(path="/own")
	public @ResponseBody List<ArticlePublisher> getOwnArticles() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<ArticleDTO> articles = articleRepository.findByUserNom(auth.getName());
		List<ArticlePublisher> resultats = new ArrayList<ArticlePublisher>();
		for (ArticleDTO a : articles) {
			ArticlePublisher res = new ArticlePublisher(a.getTitre(), a.getDatePublication(), a.getNomAuteur(), a.getContenu());
			res.setNbLikes(likeRepository.countLikesByArticleAndAuteur(a.getTitre(), auth.getName()));
			res.setNbDislikes(likeRepository.countDislikesByArticleAndAuteur(a.getTitre(), auth.getName()));
			resultats.add(res);
		}
		return resultats;
	}
	 
	@PreAuthorize("hasAnyAuthority('ROLE_MODERATOR', 'ROLE_PUBLISHER')")
	@GetMapping(path="/{id}")
	public @ResponseBody Object getArticleById(@PathVariable Integer id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Article a = articleRepository.findById(id).orElse(null);
		if (utilisateurRepository.findByNom(auth.getName()).getRole() == Role.PUBLISHER.toString() ){
				ArticlePublisher res = new ArticlePublisher(a.getTitre(), a.getDatePublication(), a.getUser().getNom(), a.getContenu());
				res.setNbLikes(likeRepository.countLikesByArticleAndAuteur(a.getTitre(), auth.getName()));
				res.setNbDislikes(likeRepository.countDislikesByArticleAndAuteur(a.getTitre(), auth.getName()));
				return res;
		}
		else if (utilisateurRepository.findByNom(auth.getName()).getRole() == Role.MODERATOR.toString() ){
				ArticleModerator res = new ArticleModerator(a.getTitre(), a.getDatePublication(), a.getUser().getNom(), a.getContenu());
				res.setNbLikes(likeRepository.countLikesByArticleAndAuteur(a.getTitre(), auth.getName()));
				res.setNbDislikes(likeRepository.countDislikesByArticleAndAuteur(a.getTitre(), auth.getName()));
				res.setListeUsersLikes(likeRepository.findUsersWhoLikedArticle(a.getTitre(), auth.getName()));
				res.setListeUsersDislikes(likeRepository.findUsersWhoDislikedArticle(a.getTitre(), auth.getName()));
				return res;
		}
		return a;
	}
	 
	@PreAuthorize("hasAuthority('ROLE_PUBLISHER')")
	@PutMapping(path="/{id}")
	 public @ResponseBody Article changeArticle(@PathVariable Integer id, 
			 @RequestParam(required = false) String contenu, 
			 @RequestParam(required = false) String titre, 
			 @RequestParam(required = false) LocalDate date,
			 @RequestParam(required = false) Integer auteurId ) {
		 Article a = articleRepository.findById(id).orElseThrow();
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		 if (auth.getName() != a.getUser().getNom()) {
			 return null;
		 }
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
	 
	
	@PreAuthorize("hasAnyAuthority('ROLE_MODERATOR', 'ROLE_PUBLISHER')")
	@DeleteMapping(path="/{id}")
	public @ResponseBody String deleteArticle(@PathVariable Integer id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Article a = articleRepository.findById(id).orElseThrow();
		if (auth.getName() != a.getUser().getNom()) {
			return null;
		}
		articleRepository.deleteById(id);
		return "Supprimé";
	}
	
}
