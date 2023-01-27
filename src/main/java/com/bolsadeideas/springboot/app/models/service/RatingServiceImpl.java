package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.app.models.entity.Rating;
import com.bolsadeideas.springboot.app.repositories.RatingRepository;



@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;

	public List<Rating> findAllRatings() {
		return ratingRepository.findAll();
	}

	public Rating findRatingById(Long id) {
		return ratingRepository.getOne(id);
	}

	public void addRating(Rating rating) {
		ratingRepository.save(rating);
	}
}
