package pro.chenggang.learn.rsocket.server.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/8
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Getter
@Setter
@ToString
@ConfigurationProperties("spring.rsocket")
public class RSocketProperties {

    /**
     * 默认端口
     */
    private Integer serverPort = 8080;

}
