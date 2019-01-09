package pro.chenggang.learn.rsocket.server.template;


import pro.chenggang.learn.rsocket.server.operation.FireAndForgetServerOperation;
import pro.chenggang.learn.rsocket.server.operation.MetadataPushServerOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestChannelServerOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestResponseServerOperation;
import pro.chenggang.learn.rsocket.server.operation.RequestStreamServerOperation;

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
    FireAndForgetServerOperation opsForFireAndForget();

    /**
     * Operation For Request Resposne
     * @return
     */
    RequestResponseServerOperation opsForRequestResponse();

    /**
     * Operation For Metadata Push
     * @return
     */
    MetadataPushServerOperation opsForMetadataPush();

    /**
     * Operation For Request Stream
     * @return
     */
    RequestStreamServerOperation opsForRequestStream();

    /**
     * Operation For Request Channel
     * @return
     */
    RequestChannelServerOperation opsForRequestChannel();
}
