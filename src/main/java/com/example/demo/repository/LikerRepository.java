package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Liker;
import com.example.demo.entity.Utilisateur;

@Repository
public interface LikerRepository extends CrudRepository<Liker, Integer>{

	Liker findByUserIdAndArticleId(Integer auteurId, Integer articleId);

	Iterable<Liker> findAllByUserId(Integer id);

	Iterable<Liker> findAllByArticleId(Integer id);
	
	@Query("SELECT COUNT(l) FROM Liker l WHERE l.article.titre = :articleTitre AND l.user.nom = :auteurNom AND l.isLiked = true")
    Integer countLikesByArticleAndAuteur(@Param("articleTitre") String articleTitre, @Param("auteurNom") String auteurNom);
	
	@Query("SELECT COUNT(l) FROM Liker l WHERE l.article.titre = :articleTitre AND l.user.nom = :auteurNom AND l.isLiked = false")
    Integer countDislikesByArticleAndAuteur(@Param("articleTitre") String articleTitre, @Param("auteurNom") String auteurNom);

	@Query("SELECT l.user FROM Liker l WHERE l.article.titre = :articleTitre AND l.article.user.nom = :auteurNom AND l.isLiked = true")
	List<Utilisateur> findUsersWhoLikedArticle(@Param("articleTitre") String articleTitre, @Param("auteurNom") String auteurNom);

	@Query("SELECT l.user FROM Liker l WHERE l.article.titre = :articleTitre AND l.article.user.nom = :auteurNom AND l.isLiked = false")
	List<Utilisateur> findUsersWhoDislikedArticle(@Param("articleTitre") String articleTitre, @Param("auteurNom") String auteurNom);


}
