package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Show;



public interface ShowService {

	public Show findById(Long id);
	public List<Show> findAllShows();
	public Page<Show> findAll(Pageable pageable);
	public void saveShow(Show show) ;
	public void updateShow(Show show);
	public void deleteShow(Long id);
	
}
