package pro.chenggang.learn.rsocket.server.config;

import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.chenggang.learn.rsocket.server.core.RSocketServer;
import pro.chenggang.learn.rsocket.server.core.RSocketServerManager;
import pro.chenggang.learn.rsocket.server.core.ServerSocketAcceptor;
import pro.chenggang.learn.rsocket.server.operation.DefaultFireAndForgetOperation;
import pro.chenggang.learn.rsocket.server.operation.DefaultMetadataPushOperation;
import pro.chenggang.learn.rsocket.server.operation.DefaultRequestChannelOperation;
import pro.chenggang.learn.rsocket.server.operation.DefaultRequestResponseOperation;
import pro.chenggang.learn.rsocket.server.operation.DefaultRequestStreamOperation;
import pro.chenggang.learn.rsocket.server.operation.FireAndForgetOperation;
import pro.chenggang.learn.rsocket.server.operation.MetadataPushOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestChannelOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestResponseOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestStreamOperation;
import pro.chenggang.learn.rsocket.server.properties.RSocketProperties;
import pro.chenggang.learn.rsocket.server.template.DefaultRSocketServerTemplate;
import pro.chenggang.learn.rsocket.server.template.RSocketServerTemplate;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/8
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Configuration
public class ServerConfig {

    @Bean
    public RSocketProperties rSocketProperties(){
        return new RSocketProperties();
    }

    @Bean
    @ConditionalOnMissingBean(FireAndForgetOperation.class)
    public FireAndForgetOperation fireAndForgetOperation(){
        return new DefaultFireAndForgetOperation();
    }

    @Bean
    @ConditionalOnMissingBean(MetadataPushOperation.class)
    public MetadataPushOperation metadataPushOperation(){
        return new DefaultMetadataPushOperation();
    }

    @Bean
    @ConditionalOnMissingBean(RequestChannelOperation.class)
    public RequestChannelOperation requestChannelOperation(){
        return new DefaultRequestChannelOperation();
    }

    @Bean
    @ConditionalOnMissingBean(RequestResponseOperation.class)
    public RequestResponseOperation requestResponseOperation(){
        return new DefaultRequestResponseOperation();
    }

    @Bean
    @ConditionalOnMissingBean(RequestStreamOperation.class)
    public RequestStreamOperation requestStreamOperation(){
        return new DefaultRequestStreamOperation();
    }

    @Bean
    @ConditionalOnMissingBean(RSocketServerTemplate.class)
    public RSocketServerTemplate rSocketServerTemplate(@Autowired(required = false) FireAndForgetOperation fireAndForgetOperation,
                                                       @Autowired(required = false) MetadataPushOperation metadataPushOperation,
                                                       @Autowired(required = false) RequestChannelOperation requestChannelOperation,
                                                       @Autowired(required = false) RequestResponseOperation requestResponseOperation,
                                                       @Autowired(required = false) RequestStreamOperation requestStreamOperation){
        return new DefaultRSocketServerTemplate(fireAndForgetOperation, metadataPushOperation, requestChannelOperation, requestResponseOperation, requestStreamOperation);
    }

    @Bean
    @ConditionalOnMissingBean(RSocket.class)
    public RSocket rSocket(RSocketServerTemplate rSocketServerTemplate){
        return new RSocketServer(rSocketServerTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(SocketAcceptor.class)
    public SocketAcceptor socketAcceptor(RSocket rSocket){
        return new ServerSocketAcceptor(rSocket);
    }

    @Bean
    public RSocketServerManager rSocketServerManager(SocketAcceptor socketAcceptor, RSocketProperties rSocketProperties){
        return new RSocketServerManager(socketAcceptor,rSocketProperties);
    }


}
