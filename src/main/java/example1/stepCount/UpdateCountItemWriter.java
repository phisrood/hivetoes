//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package example1.stepCount;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.util.Assert;

public class UpdateCountItemWriter<T> implements ItemWriter<T>, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCountItemWriter.class);
    private SqlSessionTemplate sqlSessionTemplate;
    private String statementId;
    private boolean assertUpdates = true;
    private Converter<T, ?> itemToParameterConverter = new UpdateCountItemWriter.PassThroughConverter();
    private JobOperator jobOperator;
    private Map<String, Object> parameterValues;

    public void setParameterValues(Map<String, Object> parameterValues) {
        this.parameterValues = parameterValues;
    }

    public void setJobOperator(JobOperator jobOperator){
        this.jobOperator = jobOperator;
    }
    public UpdateCountItemWriter() {
    }

    public void setAssertUpdates(boolean assertUpdates) {
        this.assertUpdates = assertUpdates;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        if (this.sqlSessionTemplate == null) {
            this.sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory, ExecutorType.BATCH);
        }

    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }

    public void setItemToParameterConverter(Converter<T, ?> itemToParameterConverter) {
        this.itemToParameterConverter = itemToParameterConverter;
    }

    public void afterPropertiesSet() {
        Assert.notNull(this.sqlSessionTemplate, "A SqlSessionFactory or a SqlSessionTemplate is required.");
        Assert.isTrue(ExecutorType.BATCH == this.sqlSessionTemplate.getExecutorType(), "SqlSessionTemplate's executor type must be BATCH");
        Assert.notNull(this.statementId, "A statementId is required.");
        Assert.notNull(this.itemToParameterConverter, "A itemToParameterConverter is required.");
    }

    public void write(List<? extends T> items) {
        if (!items.isEmpty()) {
            LOGGER.debug(() -> {
                return "Executing batch with " + items.size() + " items.";
            });

            StepExecution stepExecution = (StepExecution) parameterValues.get("stepExecution");
            Iterator var2 = items.iterator();
            StepContribution contribution= stepExecution.createStepContribution();
            while(var2.hasNext()) {
                T item = (T) var2.next();
                this.sqlSessionTemplate.update(this.statementId, this.itemToParameterConverter.convert(item));
            }

            List<BatchResult> results = this.sqlSessionTemplate.flushStatements();
            int resultCount = 0;

//            if (results.size() != 1) {
//                throw new InvalidDataAccessResourceUsageException("Batch execution returned invalid results. Expected 1 but number of BatchResult objects returned was " + results.size());
//            }else{
//                int[] updateCounts = ((BatchResult)results.get(0)).getUpdateCounts();
//
//                for(int i = 0; i < updateCounts.length; ++i) {
//                    int value = updateCounts[i];
//                    if (value == 0) {
//                        throw new EmptyResultDataAccessException("Item " + i + " of " + updateCounts.length + " did not update any rows: [" + items.get(i) + "]", 1);
//                    }
//                    resultCount += value;
//                }
//            }
//            stepExecution.setWriteCount(resultCount);
        }

    }

    private static class PassThroughConverter<T> implements Converter<T, T> {
        private PassThroughConverter() {
        }

        public T convert(T source) {
            return source;
        }
    }
}
