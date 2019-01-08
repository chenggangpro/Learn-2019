package pro.chenggang.learn.rsocket.server.template;

import lombok.extern.slf4j.Slf4j;
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

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/8
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class DefaultRSocketServerTemplate implements RSocketServerTemplate {

    private final FireAndForgetOperation fireAndForgetOperation;
    private final MetadataPushOperation metadataPushOperation;
    private final RequestChannelOperation requestChannelOperation;
    private final RequestResponseOperation requestResponseOperation;
    private final RequestStreamOperation requestStreamOperation;

    public DefaultRSocketServerTemplate(FireAndForgetOperation fireAndForgetOperation, MetadataPushOperation metadataPushOperation, RequestChannelOperation requestChannelOperation, RequestResponseOperation requestResponseOperation, RequestStreamOperation requestStreamOperation) {
        if(null == fireAndForgetOperation){
            log.warn("Fire And Forget Operation Is Null Init With Default");
            fireAndForgetOperation = new DefaultFireAndForgetOperation();
        }
        if(null == metadataPushOperation){
            log.warn("Metadata Push Operation Is Null Init With Default");
            metadataPushOperation = new DefaultMetadataPushOperation();
        }
        if(null == requestChannelOperation){
            log.warn("Request Channel Operation Is Null Init With Default");
            requestChannelOperation = new DefaultRequestChannelOperation();
        }
        if(null == requestResponseOperation){
            log.warn("Request Response Operation Is Null Init With Default");
            requestResponseOperation = new DefaultRequestResponseOperation();
        }
        if(null == requestStreamOperation){
            log.warn("Request Stream Operation Is Null Init With Default");
            requestStreamOperation = new DefaultRequestStreamOperation();
        }
        this.fireAndForgetOperation = fireAndForgetOperation;
        this.metadataPushOperation = metadataPushOperation;
        this.requestChannelOperation = requestChannelOperation;
        this.requestResponseOperation = requestResponseOperation;
        this.requestStreamOperation = requestStreamOperation;
    }

    @Override
    public FireAndForgetOperation opsForFireAndForget() {
        return this.fireAndForgetOperation;
    }

    @Override
    public RequestResponseOperation opsForRequestResponse() {
        return this.requestResponseOperation;
    }

    @Override
    public MetadataPushOperation opsForMetadataPush() {
        return this.metadataPushOperation;
    }

    @Override
    public RequestStreamOperation opsForRequestStream() {
        return this.requestStreamOperation;
    }

    @Override
    public RequestChannelOperation opsForRequestChannel() {
        return this.requestChannelOperation;
    }
}
