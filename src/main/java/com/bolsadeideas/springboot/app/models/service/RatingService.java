package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Rating;


public interface RatingService {

	public List<Rating> findAllRatings();
	public Rating findRatingById(Long id);
	public void addRating(Rating rating);
}
