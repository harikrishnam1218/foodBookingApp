package com.practice.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.practice.model.Item;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	DataSource dataSource;

	@Autowired
	EntityManagerFactory entityManager;

	@Bean
	public FlatFileItemReader<Item> reader() {
		FlatFileItemReader<Item> flatfile = new FlatFileItemReader<Item>();
		flatfile.setName("ItemsReadBatchFile");
		flatfile.setResource(new ClassPathResource("vendoritems.csv"));
		DefaultLineMapper<Item> lineMapper=new DefaultLineMapper<>();
		DelimitedLineTokenizer delimiter = new DelimitedLineTokenizer();
		String[] tokens = { "itemname", "itemdec", "cost","vid" };
		delimiter.setDelimiter(",");
		
		delimiter.setNames(tokens);
		lineMapper.setLineTokenizer(delimiter);
		
		BeanWrapperFieldSetMapper<Item> fieldset=new BeanWrapperFieldSetMapper<>();
		fieldset.setTargetType(Item.class);
		
		lineMapper.setFieldSetMapper(fieldset);
		
		flatfile.setLineMapper(lineMapper);
		
		flatfile.setLinesToSkip(1);
		return flatfile;
		
	}


	@Bean
	public JpaItemWriter<Item> jpaFileWriter() {
		JpaItemWriter<Item> jpa = new JpaItemWriter<>();
		jpa.setEntityManagerFactory(entityManager);
		return jpa;
	}
	

	@Bean
	public Job readCSVFile() {
		return jobBuilderFactory.get("addItemsReadBatch")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}
	
	
	@Bean
	public Step step1() {
		return 	stepBuilderFactory.get("addItemsData")
		.<Item,Item>chunk(1)
		.reader(reader())
		.writer(jpaFileWriter())
		.build();
	}



}
