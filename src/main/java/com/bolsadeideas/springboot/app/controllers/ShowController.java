package com.bolsadeideas.springboot.app.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Rating;
import com.bolsadeideas.springboot.app.models.entity.Show;
import com.bolsadeideas.springboot.app.models.entity.User;
import com.bolsadeideas.springboot.app.models.service.RatingService;
import com.bolsadeideas.springboot.app.models.service.ShowService;
import com.bolsadeideas.springboot.app.models.service.UserService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;



@Controller
@SessionAttributes("shows")
public class ShowController {

	@Autowired
	private ShowService showService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private UserService userService;

	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Show show = showService.findById(id);
		if (show == null) {
			flash.addFlashAttribute("error", "El Show no existe en la base de datos");
			return "redirect:/listar";
		}

		model.put("show",show);
		model.put("foto", show.getFoto());
		model.put("titulo","Detalle show: "+ show.getShowTitle());
		model.put("network",show.getShowNetwork());
		model.put("ratings", show.getRatings());
		model.put("duracion", show.getDuracion());
		model.put("descripcion", show.getDescripcion());
		return "ver";
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(@RequestParam(name="page", defaultValue="0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 10);
		
		Page<Show>shows = showService.findAll(pageRequest);
		
		PageRender<Show> pageRender = new PageRender<Show>("/listar",shows);
		model.addAttribute("titulo", "Listado de Shows");
		model.addAttribute("shows", shows);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	@RequestMapping(value = "/form")
	public String crear(Map<String, Object> model) {

		Show show = new Show();
		model.put("show", show);
		model.put("titulo", "Crear Show");
		return "form";
	}

	@RequestMapping(value = "/editShow/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Show show = null;

		if (id > 0) {
			show = showService.findById(id);
			if (show == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe en la BBDD!");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero!");
			return "redirect:/listar";
		}
		Rating rating=new Rating();
		showService.updateShow(show);
		model.put("addRatting",rating);
		model.put("show", show);
		model.put("titulo", "ADD Rating");
		return "editShow";
	}
	
	@PostMapping(value = "/editShow/{id}")
	public String updateShow(@PathVariable("id") Long id, @Valid @ModelAttribute("editShow") Show editedShow,
		BindingResult result, Model model) {
	
		Show show = showService.findById(id);
		User creatorShow = userService.findUserById(id);
		if (result.hasErrors()) {
		
			return "redirect:/editShow";
		} else {
			editedShow.setCreatorShow(creatorShow);

			showService.updateShow(show);
			return "redirect:/listar";
		}
	}


	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Show show, BindingResult result, Model model, 
			@RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
		
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear show");
			return "form";
		}
		
		if (!foto.isEmpty()) {
			
			Path directorioRecursos = Paths.get("src//main//resources//static//uploads");
			String rootPath = directorioRecursos.toFile().getAbsolutePath();

			try {

				byte[] bytes = foto.getBytes();
				Path rutaCompleta = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				flash.addFlashAttribute("info", "Has subido correctamente '" + foto.getOriginalFilename() + "'");

				show.setFoto(foto.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String mensajeFlash = (show.getId() != null) ? "Show editado con éxito!" : "Show creado con éxito!";

		showService.saveShow(show);
		status.setComplete();
		flash.addFlashAttribute("success", mensajeFlash);
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

		if (id > 0) {
			showService.deleteShow(id);
			flash.addFlashAttribute("success", "Show eliminado con éxito!");
		}
		return "redirect:/listar";
	}
	
	@PostMapping(value = "/{id}/add")
	public String addRating(@Valid @ModelAttribute("addRating") Rating rating, BindingResult result,
		@PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return "listar";


		} else {
		
			Show currentShow = showService.findById(id);
			
			ratingService.addRating(rating);
			currentShow.setRatings(ratingService.findAllRatings());
			showService.updateShow(currentShow);

			return "redirect:/listar" + id;
		}
	}

}