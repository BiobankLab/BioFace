package com.bioface.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.bioface.service.ISolrIndexer;

@Configuration
@EnableScheduling
public class ShedulerConfig {

	@Autowired
	private ISolrIndexer iSolrIndexer;

	@Scheduled(cron = "*/10 * * * * *")
	public void runJob() {
		iSolrIndexer.run();
	}
}
