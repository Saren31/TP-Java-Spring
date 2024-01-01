package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Liker;

@Repository
public interface LikerRepository extends CrudRepository<Liker, Integer>{

	Liker findByUserIdAndArticleId(Integer auteurId, Integer articleId);

	Iterable<Liker> findAllByUserId(Integer id);

}
