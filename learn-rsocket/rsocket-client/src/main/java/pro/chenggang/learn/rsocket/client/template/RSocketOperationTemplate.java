package pro.chenggang.learn.rsocket.client.template;


import pro.chenggang.learn.rsocket.client.operation.FireAndForgetOperation;
import pro.chenggang.learn.rsocket.client.operation.MetadataPushOperation;
import pro.chenggang.learn.rsocket.client.operation.RequestChannelOperation;
import pro.chenggang.learn.rsocket.client.operation.RequestResponseOperation;
import pro.chenggang.learn.rsocket.client.operation.RequestStreamOperation;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
public interface RSocketOperationTemplate {

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
