package example1.partitioner;

import example1.dao.MariaDAO;
import example1.multiThread.domain.Employee;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

public class FileItemWriter implements ItemWriter<Employee>, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileItemWriter.class);
    @Autowired
    private MariaDAO mariaDAO;
    private boolean assertUpdates = true;

    public void setAssertUpdates(boolean assertUpdates) {
        this.assertUpdates = assertUpdates;
    }
    @Override
    public void write(List items) throws Exception {
        if (!items.isEmpty()) {
            int result = 0;
            LOGGER.debug(() -> {
                return "Executing batch with " + items.size() + " items.";
            });
            Iterator var2 = items.iterator();

            while(var2.hasNext()) {
                Employee item = (Employee) var2.next();
                mariaDAO.update("insertEmployees", item);
            }

        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
