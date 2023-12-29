package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/like")
public class LikeController {

	@Autowired
	private LikerRepository likeRepository;
	
	@Autowired
	private DislikerRepository dislikeRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@PostMapping(path="/addLike")
	public @ResponseBody String addLike (@RequestParam Integer auteurId, 
	       @RequestParam Integer articleId) {
		 
		 Article article = articleRepository.findById(articleId)
	                .orElseThrow(() -> new IllegalArgumentException("Aucun article trouvé avec cet id"));
	 
		 Utilisateur auteur = utilisateurRepository.findById(auteurId)
	                .orElseThrow(() -> new IllegalArgumentException("Aucun article trouvé avec cet id"));
	 
		 
		 Disliker d = dislikeRepository.findByUserIdAndArticleId(auteurId, articleId);
	     if (d != null) {
	         dislikeRepository.delete(d);
	     }
		
	    Liker n = new Liker();
	    n.setArticle(article);
	    n.setUser(auteur);
	    likeRepository.save(n);
	    return "Like ajouté";
	  }
	 
	 @DeleteMapping(path="/deleteLike")
	 public @ResponseBody String deleteLike(@RequestParam Integer auteurId,
	        @RequestParam Integer articleId) {
		 Liker l = likeRepository.findByUserIdAndArticleId(auteurId, articleId);
		 if (l != null) {
			 likeRepository.delete(l);
			 return "Like supprimé";
		 }
		 return "Like n'existe pas";
	 }
	 
	 @GetMapping(path="/allLikes")
	  public @ResponseBody Iterable<Liker> getLikes() {
	    return likeRepository.findAll();
	  }
	 
	 @GetMapping(path="/user")
	    public @ResponseBody Iterable<Liker> getLikesByUserId(@RequestParam Integer id) {
		 
			 if (!utilisateurRepository.existsById(id)) {
				 return null;
		     }

	        //return likeRepository.findAllLikesByUtilisateur(id);
	        return likeRepository.findAllByUserId(id);
	    }

	 
	 @PostMapping(path="/addDislike")
	  public @ResponseBody String addDislike (@RequestParam Integer auteurId, 
	         @RequestParam Integer articleId) {
		 
		 Article article = articleRepository.findById(articleId)
	                .orElseThrow(() -> new IllegalArgumentException("Aucun article trouvé avec cet id"));
	 
		 Utilisateur auteur = utilisateurRepository.findById(auteurId)
	                .orElseThrow(() -> new IllegalArgumentException("Aucun article trouvé avec cet id"));
	 
		 Liker l = likeRepository.findByUserIdAndArticleId(auteurId, articleId);
	     if (l != null) {
	    	 likeRepository.delete(l);
	     }
	    Disliker n = new Disliker();
	    n.setArticle(article);
	    n.setUser(auteur);
	    dislikeRepository.save(n);
	    return "Saved";
	  }
	 
	 @DeleteMapping(path="/deleteDislike")
	 public @ResponseBody String deleteDislike(@RequestParam Integer auteurId,
	        @RequestParam Integer articleId) {
		 Disliker l = dislikeRepository.findByUserIdAndArticleId(auteurId, articleId);
		 if (l != null) {
			 dislikeRepository.delete(l);
			 return "Dislike supprimé";
		 }
		 return "Le dislike n'existe pas";
	 }
	 
	 @GetMapping(path="/allDislikes")
	  public @ResponseBody Iterable<Disliker> getDislikes() {
	    return dislikeRepository.findAll();
	  }
	 
	 @GetMapping(path="/userDislike")
	    public @ResponseBody Iterable<Disliker> getDislikesByUserId(@RequestParam Integer id) {
		 
			 if (!utilisateurRepository.existsById(id)) {
				 return null;
		     }

	        return dislikeRepository.findAllByUserId(id);
	    }
	
}
