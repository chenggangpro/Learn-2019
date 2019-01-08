package pro.chenggang.learn.rsocket.server.operation;

import io.rsocket.Payload;
import reactor.core.publisher.Mono;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
public interface MetadataPushOperation {
    /**
     * Handle Metadata Push
     * @param payload
     * @return
     */
    Mono<Void> metadataPush(Payload payload);
}
