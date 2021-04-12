package example1.listener;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;

import java.util.List;

public class ItemWriterListener<S> implements ItemWriteListener<S> {


    @Override
    public void beforeWrite(List list) {

    }

    @Override
    public void afterWrite(List list) {

    }

    @Override
    public void onWriteError(Exception e, List list) {

    }
}
