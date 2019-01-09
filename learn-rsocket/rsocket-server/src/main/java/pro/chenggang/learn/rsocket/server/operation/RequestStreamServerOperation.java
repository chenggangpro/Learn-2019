package pro.chenggang.learn.rsocket.server.operation;

import io.rsocket.Payload;
import reactor.core.publisher.Flux;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
public interface RequestStreamServerOperation {

    /**
     * Handle Request Stream
     * @param payload
     * @return
     */
    Flux<Payload> requestStream(Payload payload);
}
