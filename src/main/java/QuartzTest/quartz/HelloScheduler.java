package QuartzTest.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloScheduler {
	final static Logger logger = LoggerFactory.getLogger(HelloScheduler.class);
	public static void main(String[] args) throws SchedulerException {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("Current Time Is : {}",sdf.format(date));
		// 创建一个jobdetail实例绑定job
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).
				//withIdentity("myJob", "group1").
				withIdentity(new JobKey("myJob", "group1")).
				usingJobData("key1", 1.2D).
				build();
		//logger.info("jobdetail name ： {}",jobDetail.getKey().getName());
		//logger.info("jobdetail group ： {}",jobDetail.getKey().getGroup());
		//logger.info("jobdetail description ： {}",jobDetail.getDescription());
		//logger.info("jobdetail jobclass ： {}",jobDetail.getJobClass());
		/*Date date2 = new Date();
		date2.setTime(date2.getTime()+5000);
		Date date3 = new Date();
		date3.setTime(date3.getTime()+11000);*/
		// 创建一个trigger实例，定义该job立即执行，并且每个两秒执行重复执行一次
		//date.setTime(date.getTime()+4000);
		//距离当前时间四秒后执行，仅执行一次
		Trigger trigger = (CronTrigger)TriggerBuilder.newTrigger().
				withIdentity("myTrigger", "group1").
				//usingJobData("key1", 2.4F).
				startAt(date).
				//endAt(date3).
				withSchedule(
						CronScheduleBuilder.cronSchedule("* * * ? * *"))
						/*simpleSchedule().
						withIntervalInSeconds(2).
						withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY))*/
				.build();
		// jobDetail 的group1，trigger 的group1没有关系
		//创建schedule实例
		SchedulerFactory sfact=new StdSchedulerFactory();
		Scheduler scheduler = sfact.getScheduler();
		scheduler.start();
		scheduler.scheduleJob(jobDetail,trigger);
		//scheduler.standby();
		//scheduler.startDelayed(3000);
		scheduler.shutdown(1==1?true :false);
	}
}
