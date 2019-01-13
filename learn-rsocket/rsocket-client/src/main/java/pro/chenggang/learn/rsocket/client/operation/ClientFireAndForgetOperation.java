package pro.chenggang.learn.rsocket.client.operation;

import io.rsocket.Payload;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/8
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class ClientFireAndForgetOperation implements FireAndForgetOperation {

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        log.debug("ClientFireAndForgetOperation Handle Payload :{}",payload.getDataUtf8());
        return Mono.empty();
    }
}
