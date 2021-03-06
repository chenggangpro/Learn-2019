package pro.chenggang.learn.rsocket.server.operation;

import io.rsocket.Payload;
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class ServerRequestChannelOperation implements RequestChannelOperation {

    @Override
    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
        log.debug("ServerRequestChannelOperation Handle Payload Publisher");
        return Flux
                .from(payloads)
                .map(Payload::getDataUtf8)
                .doOnNext(payload-> log.debug("ServerRequestChannelOperation Handle Payload :{}",payload))
                .map(DefaultPayload::create);
    }
}
