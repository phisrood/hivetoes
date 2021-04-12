package example1.multiThread;

import example1.dao.HiveDAO;
import example1.dao.MySqlDAO;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.checkerframework.checker.units.qual.A;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class EmployeesItemReader extends AbstractPagingItemReader {
    @Autowired
    private HiveDAO hiveDAO;

    private Map<String, Object> parameterValues;

    @Override
    protected void doReadPage() {

        Map<String, Object> parameters = new HashMap();
        if (this.parameterValues != null) {
            parameters.putAll(this.parameterValues);
        }

        parameters.put("_page", this.getPage());
        parameters.put("_pagesize", this.getPageSize());
        parameters.put("_skiprows", this.getPage() * this.getPageSize());
        if (this.results == null) {
            this.results = new CopyOnWriteArrayList();
        } else {
            this.results.clear();
        }

        this.results.addAll(hiveDAO.selectList("retrieveEmployees", parameters));
    }

    @Override
    protected void doJumpToPage(int i) {

    }
}
