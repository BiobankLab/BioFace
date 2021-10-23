package com.dispersion.service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.configuration.JsonFile;
import com.bee2code.bioimporter.configuration.Mongo;
import com.bee2code.bioimporter.configuration.Solr;
import com.dispersion.model.Job;
import com.dispersion.model.JobStatus;
import com.dispersion.repository.JobRepository;
import com.google.common.collect.Lists;

@Service
public class SolrIndexer implements ISolrIndexer {

	private static final Logger log = LoggerFactory.getLogger(SolrIndexer.class);

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private Environment environment;

	/* (non-Javadoc)
	 * @see com.dispersion.service.ISolrIndexer#run()
	 */
	@Override
	public void run() {

		List<Future<Job>> resultFuture = Lists.newArrayList();
		ExecutorService executor = Executors.newFixedThreadPool(8);

		jobRepository.getNewJobs().stream().forEach(job -> {

			Callable<Job> callable = new JobExecutor(job,
					getImporterConfiguration(job.getFilePath(), job.getBiobankId()), jobRepository);
			resultFuture.add(executor.submit(callable));
		});

		for (Future<Job> futureBiodata : resultFuture) {

			try {
				Job job = futureBiodata.get();
				if (job.getStatus().equals(JobStatus.ERROR)) {
					log.info("Job id: " + job.getId() + " file: " + job.getOriginalFileName()
							+ " finished with error: " + job.getExceptionMessage());
				} else if (job.getStatus().equals(JobStatus.FINISHED)) {
					log.info(
							"Job id: " + job.getId() + " file: " + job.getOriginalFileName() + " finished succesful");
				}
			} catch (InterruptedException | ExecutionException e) {
				log.error("Error while index file", e);
			}
		}
		executor.shutdown();
	}

	/**
	 * Method return configuration for all files with custom description
	 *
	 * @return
	 */
	public Configuration getImporterConfiguration(String filesPathToImport, String biobank) {
		Configuration configuration = new Configuration();
		Mongo mongoConf = new Mongo();
		mongoConf.setAddress(environment.getProperty("spring.data.mongodb.host"));
		mongoConf.setPort(Integer.valueOf(environment.getProperty("spring.data.mongodb.port")));
		mongoConf.setDatabaseName(environment.getProperty("spring.data.mongodb.database"));
		Solr solrConf = new Solr();
		solrConf.setUrl(environment.getProperty("solr.url"));

		JsonFile jsonFile = new JsonFile();
		jsonFile.setPath(filesPathToImport);
		jsonFile.setBiobank(biobank);
		jsonFile.setUser(environment.getProperty("dispersion.institutionName"));

		configuration.setJsonFile(jsonFile);
		configuration.setMongo(mongoConf);
		configuration.setSolr(solrConf);
		return configuration;
	}
}
