package example1.listener;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.batch.core.scope.StepScope;

public class StepListener extends StepExecutionListenerSupport {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        if(stepExecution.getStatus() == BatchStatus.STARTED) {
            System.out.println("Called beforeStep()");
        }
    }
    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if(stepExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("Called afterStep()");
        }
        return new ExitStatus("stepListener exit");
    }
}
