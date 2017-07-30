package br.com.klein.denis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import br.com.klein.denis.ws.controller.TokenFilter;

@SpringBootApplication
public class SpringBootAngularJsTutorialApplication extends SpringBootServletInitializer {
	
	@Bean
	public FilterRegistrationBean filtroJwt() {
		System.out.println("*********** Servlet initializer ***********");
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new TokenFilter());
		frb.addUrlPatterns("/admin/*"); // define o pattern para o qual este filtro será utilizado
		
		return frb;
	}

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
