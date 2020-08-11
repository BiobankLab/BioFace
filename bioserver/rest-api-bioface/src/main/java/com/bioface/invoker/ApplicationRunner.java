package com.bioface.invoker;

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
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.bioface.invoker", "com.bioface.api", "io.swagger.configuration",
		"com.bioface.service", "com.bioface.configuration" })
@EnableMongoRepositories(basePackages = { "com.bioface.repository" })
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

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	class ExitException extends RuntimeException implements ExitCodeGenerator {
		private static final long serialVersionUID = 1L;

		@Override
		public int getExitCode() {
			return 10;
		}

	}
}
