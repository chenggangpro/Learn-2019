package pro.chenggang.learn.rsocket.client.operation;

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
public class ClientRequestResponseOperation implements RequestResponseOperation {

    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        log.debug("ClientRequestResponseOperation Handle Payload :{}",payload.getDataUtf8());
        return Mono.just(payload);
    }
}
