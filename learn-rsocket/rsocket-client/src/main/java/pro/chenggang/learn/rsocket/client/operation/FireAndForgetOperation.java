package pro.chenggang.learn.rsocket.client.operation;

import io.rsocket.Payload;
import reactor.core.publisher.Mono;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
public interface FireAndForgetOperation {
    /**
     * Handle Fire And Forget
     * @param payload
     * @return
     */
    Mono<Void> fireAndForget(Payload payload);
}
