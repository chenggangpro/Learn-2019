package pro.chenggang.learn.rsocket.client.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.chenggang.learn.rsocket.client.core.RSocketClient;
import pro.chenggang.learn.rsocket.client.properties.RSocketProperties;

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
    @ConditionalOnMissingBean(RSocketClient.class)
    public RSocketClient rSocketClient(RSocketProperties rSocketProperties){
        return new RSocketClient(rSocketProperties);
    }

}
