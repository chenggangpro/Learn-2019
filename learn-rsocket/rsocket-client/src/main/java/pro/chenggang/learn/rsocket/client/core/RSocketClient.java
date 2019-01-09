package pro.chenggang.learn.rsocket.client.core;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import pro.chenggang.learn.rsocket.client.properties.RSocketProperties;
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
public class RSocketClient extends AbstractRSocket implements InitializingBean,DisposableBean {

    private final RSocketProperties rSocketProperties;
    private RSocket rSocket;

    public RSocketClient(RSocketProperties rSocketProperties) {
        this.rSocketProperties = rSocketProperties;
    }

    @Override
    public void destroy() throws Exception {
        if(null !=rSocket){
            rSocket.dispose();
            log.debug("Close RScocket Client");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Integer serverPort = rSocketProperties.getServerPort();
        rSocket = RSocketFactory
                .connect()
                .transport(TcpClientTransport.create(serverPort))
                .start()
                .block();
        log.debug("Start RScocket Client On Port:{}",serverPort);
    }

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        return this.rSocket.fireAndForget(payload);
    }

    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        return this.rSocket.requestResponse(payload);
    }

    @Override
    public Flux<Payload> requestStream(Payload payload) {
        return this.rSocket.requestStream(payload);
    }

    @Override
    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
        return this.rSocket.requestChannel(payloads);
    }

    @Override
    public Mono<Void> metadataPush(Payload payload) {
        return this.rSocket.metadataPush(payload);
    }
}
