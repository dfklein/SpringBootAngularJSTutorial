package br.com.klein.denis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringBootAngularJsTutorialApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngularJsTutorialApplication.class, args);
	}
	
//	@Configuration
//	public static class MvcConfig extends WebMvcConfigurerAdapter {
//		
//		@Override
//		public void addViewControllers(ViewControllerRegistry registry) {
//			registry.addRedirectViewController("/", "/SpringBootAngularJSTutorial"); // redirecionamento, tipo um filtro.
//		}
//		
//	}
	
}
