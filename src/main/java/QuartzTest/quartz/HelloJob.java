package QuartzTest.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job{
	final static Logger logger = LoggerFactory.getLogger(HelloJob.class);
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//打印当前的执行时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("Current Exec Time Is : {}",sdf.format(date));
		//编写具体的业务逻辑
		System.out.println("hello world");
	}

}
