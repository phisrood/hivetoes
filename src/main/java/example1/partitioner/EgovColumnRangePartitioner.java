package example1.partitioner;

import example1.dao.OracleDAO;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class EgovColumnRangePartitioner implements Partitioner {

    @Autowired
    private OracleDAO oracleDAO;

    public Map<String, ExecutionContext> partition(int gridSize) {
        // 테이블의 특정컬럼의 가장 작은 값
        int min = (int) oracleDAO.selectOne("retrieveMinId");

        //  테이블의 특정컬럼의 가장 큰 값
        int max = (int) oracleDAO.selectOne("retrieveMaxId");

        // 하나의 Execution에서 지정될 Data의 범위(크기)
        int targetSize = (max - min) / gridSize + 1;

        Map<String, ExecutionContext> result = new HashMap<String, ExecutionContext>();
        int number = 0;
        int start = min;
        // targetSize 만큼 의 Data
        int end = start + targetSize - 1;

        // 파티션된 범위의 수만큼 ExecutionContext를 생성하고 minVlaue 와 maxValue를 셋팅
        while (start <= max) {

            ExecutionContext value = new ExecutionContext();
            result.put("partition" + number, value);

            if (end >= max) {
                end = max;
            }
            value.putInt("minValue", start);
            value.putInt("maxValue", end);
            start += targetSize;
            end += targetSize;
            number++;
        }
        return result;
    }
}