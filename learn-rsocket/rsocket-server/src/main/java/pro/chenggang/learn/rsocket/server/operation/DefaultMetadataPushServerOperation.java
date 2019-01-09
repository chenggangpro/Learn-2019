package pro.chenggang.learn.rsocket.server.operation;

import io.rsocket.Payload;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class DefaultMetadataPushServerOperation implements MetadataPushServerOperation {

    @Override
    public Mono<Void> metadataPush(Payload payload) {
        log.debug("DefaultMetadataPushOperation Handle Payload :{}",payload);
        return Mono.empty();
    }
}
