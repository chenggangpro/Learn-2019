package pro.chenggang.learn.rsocket.server.operation;

import io.rsocket.Payload;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
public interface RequestChannelServerOperation {

    /**
     * Handle Request Channel
     * @param payloads
     * @return
     */
    Flux<Payload> requestChannel(Publisher<Payload> payloads);
}
