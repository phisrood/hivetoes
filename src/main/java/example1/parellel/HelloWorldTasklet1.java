package example1.parellel;

import example1.dao.MySqlDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloWorldTasklet1 implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldTasklet1.class);
    @Autowired
    private MySqlDAO commonDao;
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("1.작업 시작...");
        //작업 처리
        LOGGER.info("1.작업 완료...");
        return null;
    }
}
