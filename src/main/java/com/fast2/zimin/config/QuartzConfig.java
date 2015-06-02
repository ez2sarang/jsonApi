package com.fast2.zimin.config;

import com.fast2.zimin.tvod.job.TvodEpgFileCheckJob;
import com.fast2.zimin.util.TheLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("file:${catalina.home}/conf/zimin-navigator/config.properties")
public class QuartzConfig {
	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private HibernateTransactionManager transactionManager;

	@PostConstruct
	public void init() {
		TheLogger.debug("QuartzConfig initialized.");
	}

	@Bean
	public SchedulerFactoryBean quartzScheduler() {
		SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();

		/*
		 * 현재 사용 안함
		 * 
		// If site definition is "CP" then there will be no quartz operation.
		// Quartz will be operated when site definition is "OP" or "ALL"
		if (!env.getProperty("system.siteDefinition").equals("CP")) {
			quartzScheduler.setQuartzProperties(quartzProperties());
			quartzScheduler.setDataSource(dataSource);
			quartzScheduler.setTransactionManager(transactionManager);
			quartzScheduler.setOverwriteExistingJobs(true);

			Trigger[] triggers = {
					encodingCompletionCheckerJobTrigger().getObject(),
					drmRequestJobTrigger().getObject(),
					drmCompletionCheckerJobTrigger().getObject(),
					cdnCompletionCheckerJobTrigger().getObject(),
					tvodEpgFileCheckJobTrigger().getObject() };
			quartzScheduler.setTriggers(triggers);
		}
		*/

		return quartzScheduler;
	}

	// encoding job
	// -----------------------------------------
	/*@Bean
	public JobDetailFactoryBean encodingCompletionCheckerJobBean() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(EncodingCompletionCheckerJob.class);
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}*/

	/*@Bean
	public CronTriggerFactoryBean encodingCompletionCheckerJobTrigger() {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(encodingCompletionCheckerJobBean()
				.getObject());
		cronTriggerFactoryBean.setCronExpression("0 0/1 * * * ?");
		return cronTriggerFactoryBean;
	}*/

	// encryption request job
	// -----------------------------------------
	/*@Bean
	public JobDetailFactoryBean drmRequestJobBean() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(DrmRequestJob.class);
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}*/

	/*@Bean
	public CronTriggerFactoryBean drmRequestJobTrigger() {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(drmRequestJobBean().getObject());
		cronTriggerFactoryBean.setCronExpression("0 0/1 * * * ?");
		return cronTriggerFactoryBean;
	}*/

	// encryption response job
	// -----------------------------------------
	/*@Bean
	public JobDetailFactoryBean drmCompletionCheckerJobBean() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(DrmCompletionCheckerJob.class);
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}*/

	/*@Bean
	public CronTriggerFactoryBean drmCompletionCheckerJobTrigger() {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(drmCompletionCheckerJobBean()
				.getObject());
		cronTriggerFactoryBean.setCronExpression("0 0/1 * * * ?");
		return cronTriggerFactoryBean;
	}*/

	// cdn job
	// -----------------------------------------
	/*@Bean
	public JobDetailFactoryBean cdnCompletionCheckerJobBean() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(CdnCompletionCheckerJob.class);
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}*/

	/*@Bean
	public CronTriggerFactoryBean cdnCompletionCheckerJobTrigger() {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(cdnCompletionCheckerJobBean()
				.getObject());
		cronTriggerFactoryBean.setCronExpression("0 0/1 * * * ?");
		return cronTriggerFactoryBean;
	}*/

	// tvod job
	// -----------------------------------------
	@Bean
	public JobDetailFactoryBean tvodEpgFileCheckJobBean() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(TvodEpgFileCheckJob.class);
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}

	@Bean
	public CronTriggerFactoryBean tvodEpgFileCheckJobTrigger() {
		CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
		cronTriggerFactoryBean.setJobDetail(tvodEpgFileCheckJobBean()
				.getObject());
		cronTriggerFactoryBean.setCronExpression("0 0/1 * * * ?");
		return cronTriggerFactoryBean;
	}

	@Bean
	public Properties quartzProperties() {
		Properties properties = new Properties();

		// Configure Main Scheduler Properties
		properties.setProperty("org.quartz.scheduler.instanceId", "AUTO");
		properties.setProperty("org.quartz.scheduler.instanceName",
				"ziminScheduler");
		properties.setProperty(
				"org.quartz.scheduler.makeSchedulerThreadDaemon", "true");

		// Configure ThreadPool
		properties.setProperty("org.quartz.threadPool.class",
				"org.quartz.simpl.SimpleThreadPool");
		properties.setProperty("org.quartz.threadPool.threadCount", "5");
		properties.setProperty("org.quartz.threadPool.makeThreadsDaemons",
				"true");

		// Configure JobStore
		properties.setProperty("org.quartz.jobStore.class",
				"org.quartz.impl.jdbcjobstore.JobStoreTX");
		properties.setProperty("org.quartz.jobStore.driverDelegateClass",
				"org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
		properties.setProperty("org.quartz.jobStore.isClustered", "true");
		properties.setProperty("org.quartz.jobStore.clusterCheckinInterval",
				"20000");
		return properties;
	}
}
