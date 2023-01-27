package com.bolsadeideas.springboot.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.bolsadeideas.springboot.app.models.entity.Show;
import com.bolsadeideas.springboot.app.repositories.ShowRepository;




@SpringBootApplication
public class SpringBootDataJpaApplication {

   private static final Logger log= LoggerFactory.getLogger(SpringBootDataJpaApplication.class);
	
	public static void main(String[] args) {
	
		ApplicationContext context=SpringApplication.run(SpringBootDataJpaApplication.class);
	
	
       ShowRepository repository=context.getBean(ShowRepository.class);
		
	    Show show1= new Show("Runaway","Netflix");
	
		
		repository.save(show1);
		
		
		System.out.println("numero de shows en base de datos " + repository.findAll().size());
		System.out.println(repository.findAll().toString()); 
	} 
	
	@Bean
	public CommandLineRunner inicioApp(ShowRepository repo) {
		
		return (args)->{
			
			
			
			log.info("lista de productos a mostrar");
			for(Show show:repo.findAll()) {
				log.info(show.toString());
			}
		};
	}
}
