package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Article;
import com.example.demo.entity.ArticleDTO;
import com.example.demo.entity.Utilisateur;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer>{
	void deleteByUser(Utilisateur utilisateur);
	
	void deleteById(Integer id);
	
	@Query("SELECT NEW com.example.demo.entity.ArticleDTO(a.titre, a.datePublication, a.user.nom, a.contenu) FROM Article a")
	List<ArticleDTO> findByUserNom(String nomUtilisateur);
	
	@Query("SELECT NEW com.example.demo.entity.ArticleDTO(a.titre, a.datePublication, a.user.nom, a.contenu) FROM Article a")
    List<ArticleDTO> getAllArticlesNoAuth();
}
