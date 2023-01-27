package com.bolsadeideas.springboot.app.controllers;



import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bolsadeideas.springboot.app.models.entity.Show;
import com.bolsadeideas.springboot.app.models.service.ShowService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;



@Controller
public class HomeController {
	
	@Autowired
	private ShowService showS;
	
	public HomeController() {
    new ArrayList<Show>();
	}
	
		
	public void setShow(Show show) {
	
	}
	
	

/*    @GetMapping({ "/","home"})
		public ModelAndView shows() {
			ModelAndView modelAndView= new ModelAndView("home");
			modelAndView.addObject("shows",showS.findAllShows());
			return modelAndView;
		}
		*/
	
	@RequestMapping(value = { "/","home"}, method = RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 10);
		
		Page<Show>shows = showS.findAll(pageRequest);
		
		PageRender<Show> pageRender = new PageRender<Show>("/home",shows);
		model.addAttribute("titulo", "Listado de Shows");
		model.addAttribute("shows", shows);
		model.addAttribute("page", pageRender);
		return "home";
	}

	}


