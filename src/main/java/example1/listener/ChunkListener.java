package example1.listener;

import org.springframework.batch.core.listener.ChunkListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;

public class ChunkListener extends ChunkListenerSupport {

    @Override
    public void beforeChunk(ChunkContext chunkContext) {
        System.out.println("Called beforeChunk()");
    }
    @Override
    public void afterChunk(ChunkContext chunkContext) {
        System.out.println("Called afterChunk()");
    }

}
