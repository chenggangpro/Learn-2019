package pro.chenggang.learn.rsocket.server.template;

import lombok.extern.slf4j.Slf4j;
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

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/8
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class RSocketServerOperationTemplate implements RSocketOperationTemplate {

    private final FireAndForgetOperation fireAndForgetOperation;
    private final MetadataPushOperation metadataPushOperation;
    private final RequestChannelOperation requestChannelOperation;
    private final RequestResponseOperation requestResponseOperation;
    private final RequestStreamOperation requestStreamOperation;

    public RSocketServerOperationTemplate(FireAndForgetOperation fireAndForgetOperation, MetadataPushOperation metadataPushOperation, RequestChannelOperation requestChannelOperation, RequestResponseOperation requestResponseOperation, RequestStreamOperation requestStreamOperation) {
        if(null == fireAndForgetOperation){
            log.warn("Fire And Forget Operation Is Null Init With Default Server Operation");
            fireAndForgetOperation = new ServerFireAndForgetOperation();
        }
        if(null == metadataPushOperation){
            log.warn("Metadata Push Operation Is Null Init With Default Server Operation");
            metadataPushOperation = new ServerMetadataPushOperation();
        }
        if(null == requestChannelOperation){
            log.warn("Request Channel Operation Is Null Init With Default Server Operation");
            requestChannelOperation = new ServerRequestChannelOperation();
        }
        if(null == requestResponseOperation){
            log.warn("Request Response Operation Is Null Init With Default Server Operation");
            requestResponseOperation = new ServerRequestResponseOperation();
        }
        if(null == requestStreamOperation){
            log.warn("Request Stream Operation Is Null Init With Default Server Operation");
            requestStreamOperation = new ServerRequestStreamOperation();
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
