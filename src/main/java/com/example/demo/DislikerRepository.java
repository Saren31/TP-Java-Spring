package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikerRepository extends CrudRepository<Disliker, Integer>{

	Disliker findByUserIdAndArticleId(Integer auteurId, Integer articleId);
	
	Iterable<Disliker> findAllByUserId(Integer id);
	
}
