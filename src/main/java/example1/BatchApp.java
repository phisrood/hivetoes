package example1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class BatchApp {
    public static void main(String[] args){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/job/reg/job-register.xml");
        context.refresh();

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("sometrendTransfer");
        System.out.println("Starting the batch job");

        try {
            Map<String, JobParameter> parameters = new HashMap<>();
            JobParameter jobParameter = new JobParameter("20210411");
            parameters.put("date", jobParameter);

            JobExecution execution = jobLauncher.run(job, new JobParameters(parameters));
            System.out.println("Job Status : " + execution.getStatus());
            System.out.println("Job completed");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Job failed");
        }
    }
}
