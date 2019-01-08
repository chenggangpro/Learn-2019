package pro.chenggang.learn.rsocket.server.core;

import io.rsocket.RSocketFactory;
import io.rsocket.SocketAcceptor;
import io.rsocket.transport.netty.server.CloseableChannel;
import io.rsocket.transport.netty.server.TcpServerTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import pro.chenggang.learn.rsocket.server.properties.RSocketProperties;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class RSocketServerManager implements InitializingBean,DisposableBean {

    private final SocketAcceptor serverSocketAcceptor;

    private final RSocketProperties rSocketProperties;

    private CloseableChannel closeableChannel;

    public RSocketServerManager(SocketAcceptor serverSocketAcceptor, RSocketProperties rSocketProperties) {
        this.serverSocketAcceptor = serverSocketAcceptor;
        this.rSocketProperties = rSocketProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Integer port = rSocketProperties.getServerPort();
        closeableChannel = RSocketFactory
                .receive()
                .acceptor(this.serverSocketAcceptor)
                .transport(TcpServerTransport.create(port))
                .start()
                .block();
        log.debug("Start CloseableChannel On Port:{}",port);
    }

    @Override
    public void destroy() throws Exception {
        if(null != closeableChannel){
            closeableChannel.dispose();
            log.debug("Close CloseableChannel");
        }
    }
}
