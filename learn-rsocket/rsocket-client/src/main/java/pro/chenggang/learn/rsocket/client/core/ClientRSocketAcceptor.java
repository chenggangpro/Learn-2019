package pro.chenggang.learn.rsocket.client.core;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import pro.chenggang.learn.rsocket.client.template.RSocketOperationTemplate;
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
public class ClientRSocketAcceptor extends AbstractRSocket {

    private final RSocketOperationTemplate rSocketOperationTemplate;

    public ClientRSocketAcceptor(RSocketOperationTemplate rSocketOperationTemplate) {
        this.rSocketOperationTemplate = rSocketOperationTemplate;
    }

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        return rSocketOperationTemplate.opsForFireAndForget().fireAndForget(payload);
    }

    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        return rSocketOperationTemplate.opsForRequestResponse().requestResponse(payload);
    }

    @Override
    public Flux<Payload> requestStream(Payload payload) {
        return rSocketOperationTemplate.opsForRequestStream().requestStream(payload);
    }

    @Override
    public Mono<Void> metadataPush(Payload payload) {
        return rSocketOperationTemplate.opsForMetadataPush().metadataPush(payload);
    }

    @Override
    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
        return rSocketOperationTemplate.opsForRequestChannel().requestChannel(payloads);
    }



}
