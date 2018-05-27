package QuartzTest.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloJob implements Job{
	final static Logger logger = LoggerFactory.getLogger(HelloJob.class);
	private float key1;
	
	public Float getKey1() {
		return key1;
	}

	public void setKey1(Float key1) {
		this.key1 = key1;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//打印当前的执行时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("Current Exec Time Is : {}",sdf.format(date));
		//编写具体的业务逻辑
		//int refireCount = context.getRefireCount();
		/*JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		System.err.println("====="+key1);
		Trigger trigger = context.getTrigger();
		Date endTime = trigger.getEndTime();
		Date startTime = trigger.getStartTime();
		System.err.println(endTime+" === "+startTime);*/
		System.out.println("Hello world");
		
	}

}
