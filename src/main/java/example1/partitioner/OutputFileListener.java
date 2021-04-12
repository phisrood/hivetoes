package example1.partitioner;

import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;

public class OutputFileListener {
    // outputKeyName
    private String outputKeyName = "outputFile";
    // inputKeyName
    private String inputKeyName = "fileName";
    // path
    private String path = "file:./target/output/";
    @BeforeStep
    public void createOutputNameFromInput(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution.getExecutionContext();
        String inputName = stepExecution.getStepName().replace(":", "-");
        if (executionContext.containsKey(inputKeyName)) {
            inputName = executionContext.getString(inputKeyName);
        }
        if (!executionContext.containsKey(outputKeyName)) {
            executionContext.putString(outputKeyName, path + FilenameUtils.getBaseName(inputName) + ".csv");
        }
    }

    public void setPath(String path) {
        this.path = path;
    }
}
