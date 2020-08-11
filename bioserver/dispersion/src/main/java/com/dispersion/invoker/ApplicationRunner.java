package com.dispersion.invoker;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.dispersion.invoker", "com.dispersion.model", "com.dispersion.utils", 
		"com.dispersion.configuration", "com.dispersion.api", "com.dispersion.service", "com.dispersion.security"})
@EnableMongoRepositories(basePackages = { "com.dispersion.repository" })
public class ApplicationRunner implements CommandLineRunner {

	@Autowired
	private Environment environment;
	
	@Override
	public void run(String... arg0) throws Exception {
		if (arg0.length > 0 && arg0[0].equals("exitcode")) {
			throw new ExitException();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new SpringApplication(ApplicationRunner.class).run(args);
	}
	
	@Bean
	public SolrClient solrClient() {
		return new HttpSolrClient.Builder(environment.getProperty("solr.url")).build();
	}
	
	class ExitException extends RuntimeException implements ExitCodeGenerator {
		private static final long serialVersionUID = 1L;

		@Override
		public int getExitCode() {
			return 10;
		}

	}

}
