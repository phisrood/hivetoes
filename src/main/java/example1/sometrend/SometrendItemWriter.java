package example1.sometrend;

import example1.sometrend.domain.Sometrend;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SometrendItemWriter<T> implements ItemWriter<T>, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(SometrendItemWriter.class);

    private Map<String, Object> parameterValues;

    public void setParameterValues(Map<String, Object> parameterValues) {
        this.parameterValues = parameterValues;
    }
    @Override
    public void write(List<? extends T> items) {
        if(items != null) {
            try {

                List<Sometrend> list = items.stream().map(v -> (Sometrend)v).collect(Collectors.toList());
                RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("121.66.185.30", 9200, "http")));
                BulkRequest bulkRequest    = new BulkRequest();

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                int cnt = 0;
                for (Sometrend sometrend : list) {
//                    LOGGER.info("### Sometrend Batch Elasticsearch "+sometrend.toString());
                    IndexRequest indexRequest = new IndexRequest()
                            .index("some_contents") //테이블명
                            .source(XContentFactory.jsonBuilder()
                                    .startObject()
                                    .field("date",sometrend.getDate())
                                    .field("documentdate",sometrend.getDocumentdate())
                                    .field("source",sometrend.getSource())
                                    .field("keyword",sometrend.getKeyword())
                                    .field("title",sometrend.getTitle())
                                    .field("content",sometrend.getContent())
                                    .field("url",sometrend.getUrl())
                                    .field("@timestamp", timestamp.toString())
                                    .endObject());

//                    UpdateRequest updateRequest = new UpdateRequest()
//                            .index("sometrend")
//                            .doc(XContentFactory.jsonBuilder()
//                                    .startObject()
//                                    .field("date",sometrend.getDate())
//                                    .field("documentdate",sometrend.getDocumentdate())
//                                    .field("source",sometrend.getSource())
//                                    .field("keyword",sometrend.getKeyword())
//                                    .field("title",sometrend.getTitle())
//                                    .field("content",sometrend.getContent())
//                                    .field("url",sometrend.getUrl())
//                                    .field("@timestamp", timestamp.toString())
//                                    .endObject())
//                            .upsert(indexRequest); // Upsert 구현을 위해 UpdateRequest를 함께 사용
//
                    bulkRequest.add(indexRequest);

                    cnt++;
                }

                BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);

                if(bulkResponse.hasFailures()) {
                    LOGGER.info("### Sometrend Batch Elasticsearch Error! - " + bulkResponse.buildFailureMessage());
                }else {
                    LOGGER.info("### Sometrend Batch Elasticsearch Success! - " + bulkResponse.toString());
                }

                client.close();

            } catch (Exception e) {
                // TODO: handle exception
                LOGGER.info("오류발생 ------------------------------------------------");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
