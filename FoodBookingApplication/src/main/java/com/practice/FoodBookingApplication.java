package com.practice;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.practice.config.RibbonConfiguration;
import com.practice.exception.BatchJobException;

@EnableEurekaClient
@SpringBootApplication
@EnableScheduling
@EnableFeignClients
@RibbonClient(name="ribbonfeignclient-service",configuration=RibbonConfiguration.class)
public class FoodBookingApplication {

	@Autowired
	private Job job;
	
	@Autowired
	JobLauncher jobLauncher;
	
	public static void main(String[] args) {
		SpringApplication.run(FoodBookingApplication.class, args);
	}


	@Scheduled(fixedDelay = 50000)
	public void doJob() {
		JobParameters parameters=new JobParametersBuilder()
				.addString("JobId", String.valueOf(System.currentTimeMillis()))
				.addString("JobName", job.getName())
				.toJobParameters();
		try {
			JobExecution	execution = jobLauncher.run(job, parameters);
			if(execution.getStatus().equals(BatchStatus.COMPLETED)){
				execution.stop();
			}
		} catch (JobExecutionAlreadyRunningException e) {
			throw new BatchJobException("JobExecutionAlreadyRunningException",e);
		} catch (JobRestartException e) {
			throw new BatchJobException("JobRestartException",e);
		} catch (JobInstanceAlreadyCompleteException e) {
			throw new BatchJobException("JobInstanceAlreadyCompleteException",e);
		} catch (JobParametersInvalidException e) {
			throw new BatchJobException("JobParametersInvalidException",e);
		}
	}

}
