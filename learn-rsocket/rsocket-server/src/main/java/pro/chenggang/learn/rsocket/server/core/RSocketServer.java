package pro.chenggang.learn.rsocket.server.core;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import pro.chenggang.learn.rsocket.server.template.RSocketServerTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class RSocketServer extends AbstractRSocket {

    private final RSocketServerTemplate rSocketServerTemplate;

    public RSocketServer(RSocketServerTemplate rSocketServerTemplate) {
        this.rSocketServerTemplate = rSocketServerTemplate;
    }

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        return rSocketServerTemplate.opsForFireAndForget().fireAndForget(payload);
    }

    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        return rSocketServerTemplate.opsForRequestResponse().requestResponse(payload);
    }

    @Override
    public Flux<Payload> requestStream(Payload payload) {
        return rSocketServerTemplate.opsForRequestStream().requestStream(payload);
    }

    @Override
    public Mono<Void> metadataPush(Payload payload) {
        return rSocketServerTemplate.opsForMetadataPush().metadataPush(payload);
    }

    @Override
    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
        return rSocketServerTemplate.opsForRequestChannel().requestChannel(payloads);
    }
}
