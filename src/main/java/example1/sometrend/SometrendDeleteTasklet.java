package example1.sometrend;

import example1.dao.MySqlDAO;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.InetAddress;

public class SometrendDeleteTasklet implements Tasklet {

    private static final Logger LOGGER = LoggerFactory.getLogger(SometrendDeleteTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        LOGGER.info("1.작업 시작...");
//        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("121.66.185.30", 9201, "http")));
        String date = (String) chunkContext.getStepContext().getJobParameters().get("date");
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new TransportAddress(InetAddress.getByName("121.66.185.30"),9201));
        BulkByScrollResponse response = new DeleteByQueryRequestBuilder(client, DeleteByQueryAction.INSTANCE)
                .filter(QueryBuilders.matchQuery("date", date))
                .source("sometrend")
                .get();


        //작업 처리
        LOGGER.info("1.작업 완료...");
        return null;
    }
}
