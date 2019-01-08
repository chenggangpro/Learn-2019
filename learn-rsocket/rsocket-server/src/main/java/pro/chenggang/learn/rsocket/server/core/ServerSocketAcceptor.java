package pro.chenggang.learn.rsocket.server.core;

import io.rsocket.ConnectionSetupPayload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
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
public class ServerSocketAcceptor implements SocketAcceptor {

    private final RSocket rSocketServer;

    public ServerSocketAcceptor(RSocket rSocketServer) {
        this.rSocketServer = rSocketServer;
    }

    @Override
    public Mono<RSocket> accept(ConnectionSetupPayload connectionSetupPayload, RSocket rSocket) {
        return Mono.just(rSocket);
    }
}
