package tests;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.bioface.invoker", "com.bioface.api", "io.swagger.configuration",
		"com.bioface.service"})
@EnableMongoRepositories(basePackages = { "com.bioface.repository" })
public class IntegrationTestConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public SolrClient solrClient() {
		return new HttpSolrClient.Builder(environment.getProperty("solr.url")).build();
	}

	@Bean
	public MongoClient mongoClient() {
		return new MongoClient("localhost", 27017);
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		return new MongoTemplate(mongoClient(), "bioface_test");
	}
}
