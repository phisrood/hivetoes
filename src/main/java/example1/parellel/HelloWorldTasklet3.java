package example1.parellel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class HelloWorldTasklet3 implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldTasklet3.class);
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("3.작업 시작...");
        //작업 처리
        LOGGER.info("3.작업 완료...");

        return null;
    }
}
