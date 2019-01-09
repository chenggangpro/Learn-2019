package pro.chenggang.learn.rsocket.server.template;

import lombok.extern.slf4j.Slf4j;
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

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/8
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class DefaultRSocketServerTemplate implements RSocketServerTemplate {

    private final FireAndForgetServerOperation fireAndForgetServerOperation;
    private final MetadataPushServerOperation metadataPushServerOperation;
    private final RequestChannelServerOperation requestChannelServerOperation;
    private final RequestResponseServerOperation requestResponseServerOperation;
    private final RequestStreamServerOperation requestStreamServerOperation;

    public DefaultRSocketServerTemplate(FireAndForgetServerOperation fireAndForgetServerOperation, MetadataPushServerOperation metadataPushServerOperation, RequestChannelServerOperation requestChannelServerOperation, RequestResponseServerOperation requestResponseServerOperation, RequestStreamServerOperation requestStreamServerOperation) {
        if(null == fireAndForgetServerOperation){
            log.warn("Fire And Forget Operation Is Null Init With Default");
            fireAndForgetServerOperation = new DefaultFireAndForgetServerOperation();
        }
        if(null == metadataPushServerOperation){
            log.warn("Metadata Push Operation Is Null Init With Default");
            metadataPushServerOperation = new DefaultMetadataPushServerOperation();
        }
        if(null == requestChannelServerOperation){
            log.warn("Request Channel Operation Is Null Init With Default");
            requestChannelServerOperation = new DefaultRequestChannelServerOperation();
        }
        if(null == requestResponseServerOperation){
            log.warn("Request Response Operation Is Null Init With Default");
            requestResponseServerOperation = new DefaultRequestResponseServerOperation();
        }
        if(null == requestStreamServerOperation){
            log.warn("Request Stream Operation Is Null Init With Default");
            requestStreamServerOperation = new DefaultRequestStreamServerOperation();
        }
        this.fireAndForgetServerOperation = fireAndForgetServerOperation;
        this.metadataPushServerOperation = metadataPushServerOperation;
        this.requestChannelServerOperation = requestChannelServerOperation;
        this.requestResponseServerOperation = requestResponseServerOperation;
        this.requestStreamServerOperation = requestStreamServerOperation;
    }

    @Override
    public FireAndForgetServerOperation opsForFireAndForget() {
        return this.fireAndForgetServerOperation;
    }

    @Override
    public RequestResponseServerOperation opsForRequestResponse() {
        return this.requestResponseServerOperation;
    }

    @Override
    public MetadataPushServerOperation opsForMetadataPush() {
        return this.metadataPushServerOperation;
    }

    @Override
    public RequestStreamServerOperation opsForRequestStream() {
        return this.requestStreamServerOperation;
    }

    @Override
    public RequestChannelServerOperation opsForRequestChannel() {
        return this.requestChannelServerOperation;
    }
}
