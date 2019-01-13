package pro.chenggang.learn.rsocket.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.chenggang.learn.rsocket.client.core.ClientRSocketAcceptor;
import pro.chenggang.learn.rsocket.client.core.RSocketClient;
import pro.chenggang.learn.rsocket.client.operation.ClientFireAndForgetOperation;
import pro.chenggang.learn.rsocket.client.operation.ClientMetadataPushOperation;
import pro.chenggang.learn.rsocket.client.operation.ClientRequestChannelOperation;
import pro.chenggang.learn.rsocket.client.operation.ClientRequestResponseOperation;
import pro.chenggang.learn.rsocket.client.operation.ClientRequestStreamOperation;
import pro.chenggang.learn.rsocket.client.operation.FireAndForgetOperation;
import pro.chenggang.learn.rsocket.client.operation.MetadataPushOperation;
import pro.chenggang.learn.rsocket.client.operation.RequestChannelOperation;
import pro.chenggang.learn.rsocket.client.operation.RequestResponseOperation;
import pro.chenggang.learn.rsocket.client.operation.RequestStreamOperation;
import pro.chenggang.learn.rsocket.client.properties.RSocketProperties;
import pro.chenggang.learn.rsocket.client.template.RSocketClientOperationTemplate;
import pro.chenggang.learn.rsocket.client.template.RSocketOperationTemplate;

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
    @ConditionalOnMissingBean(ClientFireAndForgetOperation.class)
    public FireAndForgetOperation fireAndForgetOperation(){
        return new ClientFireAndForgetOperation();
    }

    @Bean
    @ConditionalOnMissingBean(ClientMetadataPushOperation.class)
    public MetadataPushOperation metadataPushOperation(){
        return new ClientMetadataPushOperation();
    }

    @Bean
    @ConditionalOnMissingBean(ClientRequestChannelOperation.class)
    public RequestChannelOperation requestChannelOperation(){
        return new ClientRequestChannelOperation();
    }

    @Bean
    @ConditionalOnMissingBean(ClientRequestResponseOperation.class)
    public RequestResponseOperation requestResponseOperation(){
        return new ClientRequestResponseOperation();
    }

    @Bean
    @ConditionalOnMissingBean(ClientRequestStreamOperation.class)
    public RequestStreamOperation requestStreamOperation(){
        return new ClientRequestStreamOperation();
    }

    @Bean
    @ConditionalOnMissingBean(RSocketClientOperationTemplate.class)
    public RSocketOperationTemplate rSocketClientOperationTemplate(@Autowired(required = false) FireAndForgetOperation fireAndForgetOperation,
                                                          @Autowired(required = false) MetadataPushOperation metadataPushOperation,
                                                          @Autowired(required = false) RequestChannelOperation requestChannelOperation,
                                                          @Autowired(required = false) RequestResponseOperation requestResponseOperation,
                                                          @Autowired(required = false) RequestStreamOperation requestStreamOperation){
        return new RSocketClientOperationTemplate(fireAndForgetOperation, metadataPushOperation, requestChannelOperation, requestResponseOperation, requestStreamOperation);
    }

    @Bean
    @ConditionalOnMissingBean(ClientRSocketAcceptor.class)
    public ClientRSocketAcceptor clientRSocketAcceptor(RSocketOperationTemplate rSocketClientOperationTemplate){
        return new ClientRSocketAcceptor(rSocketClientOperationTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(RSocketClient.class)
    public RSocketClient rSocketClient(RSocketProperties rSocketProperties,ClientRSocketAcceptor clientRSocketAcceptor){
        return new RSocketClient(rSocketProperties, clientRSocketAcceptor);
    }

}
