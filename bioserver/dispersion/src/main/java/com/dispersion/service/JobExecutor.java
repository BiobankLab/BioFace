package com.dispersion.service;

import java.util.Date;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.bee2code.bioimporter.configuration.Configuration;
import com.bee2code.bioimporter.service.IImporterService;
import com.bee2code.bioimporter.service.ImporterService;
import com.dispersion.model.Job;
import com.dispersion.model.JobStatus;
import com.dispersion.repository.JobRepository;

public class JobExecutor implements Callable<Job> {

	private Job job;
	private Configuration configuration;
	private IImporterService importerService;
	private JobRepository jobRepository;

	@SuppressWarnings("unused")
	private JobExecutor() {

	}

	public JobExecutor(Job job, Configuration configuration, JobRepository jobRepository) {
		this.job = job;
		this.configuration = configuration;
		this.jobRepository = jobRepository;
		importerService = new ImporterService();
	}

	@Override
	public Job call() throws Exception {
		try {
			saveStart();
			importerService.runWithCustomConfiguration(configuration);
			saveFinish();
		} catch (Exception e) {
			saveError(e);
		}
		return job;
	}

	private void saveStart() {
		job.setIndexerStartTime(new Date().getTime());
		job.setStatus(JobStatus.IN_PROGRESS);
		jobRepository.save(job);
	}

	private void saveFinish() {
		job.setIndexerFinishTime(new Date().getTime());
		job.setStatus(JobStatus.FINISHED);
		jobRepository.save(job);
	}

	private void saveError(Throwable th) {
		job.setIndexerFinishTime(new Date().getTime());
		job.setStatus(JobStatus.ERROR);
		job.setExceptionMessage(ExceptionUtils.getRootCauseMessage(th));
		jobRepository.save(job);
	}

}
