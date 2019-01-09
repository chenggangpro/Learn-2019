package pro.chenggang.learn.rsocket.server.config;

import io.rsocket.SocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.chenggang.learn.rsocket.server.core.RSocketServer;
import pro.chenggang.learn.rsocket.server.core.RSocketServerManager;
import pro.chenggang.learn.rsocket.server.core.ServerSocketAcceptor;
import pro.chenggang.learn.rsocket.server.operation.DefaultFireAndForgetServerOperation;
import pro.chenggang.learn.rsocket.server.operation.DefaultMetadataPushServerOperation;
import pro.chenggang.learn.rsocket.server.operation.DefaultRequestChannelServerOperation;
import pro.chenggang.learn.rsocket.server.operation.DefaultRequestResponseServerOperation;
import pro.chenggang.learn.rsocket.server.operation.DefaultRequestStreamServerOperation;
import pro.chenggang.learn.rsocket.server.operation.FireAndForgetServerOperation;
import pro.chenggang.learn.rsocket.server.operation.MetadataPushServerOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestChannelServerOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestResponseServerOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestStreamServerOperation;
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
    @ConditionalOnMissingBean(FireAndForgetServerOperation.class)
    public FireAndForgetServerOperation fireAndForgetOperation(){
        return new DefaultFireAndForgetServerOperation();
    }

    @Bean
    @ConditionalOnMissingBean(MetadataPushServerOperation.class)
    public MetadataPushServerOperation metadataPushOperation(){
        return new DefaultMetadataPushServerOperation();
    }

    @Bean
    @ConditionalOnMissingBean(RequestChannelServerOperation.class)
    public RequestChannelServerOperation requestChannelOperation(){
        return new DefaultRequestChannelServerOperation();
    }

    @Bean
    @ConditionalOnMissingBean(RequestResponseServerOperation.class)
    public RequestResponseServerOperation requestResponseOperation(){
        return new DefaultRequestResponseServerOperation();
    }

    @Bean
    @ConditionalOnMissingBean(RequestStreamServerOperation.class)
    public RequestStreamServerOperation requestStreamOperation(){
        return new DefaultRequestStreamServerOperation();
    }

    @Bean
    @ConditionalOnMissingBean(RSocketServerTemplate.class)
    public RSocketServerTemplate rSocketServerTemplate(@Autowired(required = false) FireAndForgetServerOperation fireAndForgetServerOperation,
                                                       @Autowired(required = false) MetadataPushServerOperation metadataPushServerOperation,
                                                       @Autowired(required = false) RequestChannelServerOperation requestChannelServerOperation,
                                                       @Autowired(required = false) RequestResponseServerOperation requestResponseServerOperation,
                                                       @Autowired(required = false) RequestStreamServerOperation requestStreamServerOperation){
        return new DefaultRSocketServerTemplate(fireAndForgetServerOperation, metadataPushServerOperation, requestChannelServerOperation, requestResponseServerOperation, requestStreamServerOperation);
    }

    @Bean
    @ConditionalOnMissingBean(RSocketServer.class)
    public RSocketServer rSocketServer(RSocketServerTemplate rSocketServerTemplate){
        return new RSocketServer(rSocketServerTemplate);
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
