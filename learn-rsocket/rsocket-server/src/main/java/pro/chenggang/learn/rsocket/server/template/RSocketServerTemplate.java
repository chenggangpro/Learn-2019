package pro.chenggang.learn.rsocket.server.template;


import pro.chenggang.learn.rsocket.server.operation.FireAndForgetOperation;
import pro.chenggang.learn.rsocket.server.operation.MetadataPushOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestChannelOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestResponseOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestStreamOperation;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
public interface RSocketServerTemplate {

    /**
     * Operation For Fire And Forget
     * @return
     */
    FireAndForgetOperation opsForFireAndForget();

    /**
     * Operation For Request Resposne
     * @return
     */
    RequestResponseOperation opsForRequestResponse();

    /**
     * Operation For Metadata Push
     * @return
     */
    MetadataPushOperation opsForMetadataPush();

    /**
     * Operation For Request Stream
     * @return
     */
    RequestStreamOperation opsForRequestStream();

    /**
     * Operation For Request Channel
     * @return
     */
    RequestChannelOperation opsForRequestChannel();
}
