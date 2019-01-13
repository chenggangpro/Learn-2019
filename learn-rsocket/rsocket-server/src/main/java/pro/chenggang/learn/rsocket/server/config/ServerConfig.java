package pro.chenggang.learn.rsocket.server.config;

import io.rsocket.SocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.chenggang.learn.rsocket.server.core.RSocketServer;
import pro.chenggang.learn.rsocket.server.core.RSocketServerManager;
import pro.chenggang.learn.rsocket.server.core.ServerSocketAcceptor;
import pro.chenggang.learn.rsocket.server.operation.ServerFireAndForgetOperation;
import pro.chenggang.learn.rsocket.server.operation.ServerMetadataPushOperation;
import pro.chenggang.learn.rsocket.server.operation.ServerRequestChannelOperation;
import pro.chenggang.learn.rsocket.server.operation.ServerRequestResponseOperation;
import pro.chenggang.learn.rsocket.server.operation.ServerRequestStreamOperation;
import pro.chenggang.learn.rsocket.server.operation.FireAndForgetOperation;
import pro.chenggang.learn.rsocket.server.operation.MetadataPushOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestChannelOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestResponseOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestStreamOperation;
import pro.chenggang.learn.rsocket.server.properties.RSocketProperties;
import pro.chenggang.learn.rsocket.server.template.RSocketServerOperationTemplate;
import pro.chenggang.learn.rsocket.server.template.RSocketOperationTemplate;

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
    @ConditionalOnMissingBean(ServerFireAndForgetOperation.class)
    public FireAndForgetOperation fireAndForgetOperation(){
        return new ServerFireAndForgetOperation();
    }

    @Bean
    @ConditionalOnMissingBean(ServerMetadataPushOperation.class)
    public MetadataPushOperation metadataPushOperation(){
        return new ServerMetadataPushOperation();
    }

    @Bean
    @ConditionalOnMissingBean(ServerRequestChannelOperation.class)
    public RequestChannelOperation requestChannelOperation(){
        return new ServerRequestChannelOperation();
    }

    @Bean
    @ConditionalOnMissingBean(ServerRequestResponseOperation.class)
    public RequestResponseOperation requestResponseOperation(){
        return new ServerRequestResponseOperation();
    }

    @Bean
    @ConditionalOnMissingBean(ServerRequestStreamOperation.class)
    public RequestStreamOperation requestStreamOperation(){
        return new ServerRequestStreamOperation();
    }

    @Bean
    @ConditionalOnMissingBean(RSocketServerOperationTemplate.class)
    public RSocketOperationTemplate rSocketServerOperationTemplate(@Autowired(required = false) FireAndForgetOperation fireAndForgetOperation,
                                                          @Autowired(required = false) MetadataPushOperation metadataPushOperation,
                                                          @Autowired(required = false) RequestChannelOperation requestChannelOperation,
                                                          @Autowired(required = false) RequestResponseOperation requestResponseOperation,
                                                          @Autowired(required = false) RequestStreamOperation requestStreamOperation){
        return new RSocketServerOperationTemplate(fireAndForgetOperation, metadataPushOperation, requestChannelOperation, requestResponseOperation, requestStreamOperation);
    }

    @Bean
    @ConditionalOnMissingBean(RSocketServer.class)
    public RSocketServer rSocketServer(RSocketOperationTemplate rSocketServerOperationTemplate){
        return new RSocketServer(rSocketServerOperationTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(SocketAcceptor.class)
    public SocketAcceptor socketAcceptor(RSocketServer rSocketServer){
        return new ServerSocketAcceptor(rSocketServer);
    }

    @Bean
    public RSocketServerManager rSocketServerManager(SocketAcceptor socketAcceptor, RSocketProperties rSocketProperties){
        return new RSocketServerManager(socketAcceptor,rSocketProperties);
    }


}
