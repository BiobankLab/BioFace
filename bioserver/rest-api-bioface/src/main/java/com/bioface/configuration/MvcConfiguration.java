package com.bioface.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

	private static final String[] ROUTER_PATHS = { "/biobanks", "/samples", "/projects", "/collections",
			"/adminPanel" };

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS")
				.allowCredentials(true);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**/*.css", "/**/*.html", "/**/*.js", "/**/*.png").setCachePeriod(0)
				.addResourceLocations("classpath:/static/");

		registry.addResourceHandler(ROUTER_PATHS).setCachePeriod(0).addResourceLocations("classpath:/static/index.html")
				.resourceChain(true).addResolver(new PathResourceResolver() {
					@Override
					protected Resource getResource(String resourcePath, Resource location) throws IOException {
						return location.exists() && location.isReadable() ? location : null;
					}
				});
	}

}
