package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikerRepository extends CrudRepository<Liker, Integer>{

	Liker findByUserIdAndArticleId(Integer auteurId, Integer articleId);

	Iterable<Liker> findAllByUserId(Integer id);
	
	@Query("SELECT l FROM Liker l WHERE l.user.id = :utilisateurId")
    List<Liker> findAllLikesByUtilisateur(@Param("utilisateurId") Integer utilisateurId);

}
