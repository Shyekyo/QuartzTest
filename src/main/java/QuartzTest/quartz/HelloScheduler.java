package QuartzTest.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloScheduler {
	final static Logger logger = LoggerFactory.getLogger(HelloScheduler.class);
	public static void main(String[] args) throws SchedulerException {
		// 创建一个jobdetail实例绑定job
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("myJob", "group1").build();
		// 创建一个trigger实例，定义该job立即执行，并且每个两秒执行重复执行一次
		Trigger trigger = TriggerBuilder.newTrigger().
				withIdentity("myTrigger", "group1").
				startNow()
				.withSchedule(
						SimpleScheduleBuilder.
						simpleSchedule().
						withIntervalInSeconds(2).
						repeatForever()).build();
		// jobDetail 的group1，trigger 的group1没有关系
		//创建schedule实例
		SchedulerFactory sfact=new StdSchedulerFactory();
		Scheduler scheduler = sfact.getScheduler();
		scheduler.start();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("Current Time Is : {}",sdf.format(date));
		scheduler.scheduleJob(jobDetail,trigger);
	}
}
