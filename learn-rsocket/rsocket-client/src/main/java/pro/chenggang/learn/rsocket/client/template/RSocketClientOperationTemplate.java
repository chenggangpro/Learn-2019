package pro.chenggang.learn.rsocket.client.template;

import lombok.extern.slf4j.Slf4j;
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

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/8
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class RSocketClientOperationTemplate implements RSocketOperationTemplate {

    private final FireAndForgetOperation fireAndForgetOperation;
    private final MetadataPushOperation metadataPushOperation;
    private final RequestChannelOperation requestChannelOperation;
    private final RequestResponseOperation requestResponseOperation;
    private final RequestStreamOperation requestStreamOperation;

    public RSocketClientOperationTemplate(FireAndForgetOperation fireAndForgetOperation, MetadataPushOperation metadataPushOperation, RequestChannelOperation requestChannelOperation, RequestResponseOperation requestResponseOperation, RequestStreamOperation requestStreamOperation) {
        if(null == fireAndForgetOperation){
            log.warn("Fire And Forget Operation Is Null Init With Default Client Operation");
            fireAndForgetOperation = new ClientFireAndForgetOperation();
        }
        if(null == metadataPushOperation){
            log.warn("Metadata Push Operation Is Null Init With Default Client Operation");
            metadataPushOperation = new ClientMetadataPushOperation();
        }
        if(null == requestChannelOperation){
            log.warn("Request Channel Operation Is Null Init With Default Client Operation");
            requestChannelOperation = new ClientRequestChannelOperation();
        }
        if(null == requestResponseOperation){
            log.warn("Request Response Operation Is Null Init With Default Client Operation");
            requestResponseOperation = new ClientRequestResponseOperation();
        }
        if(null == requestStreamOperation){
            log.warn("Request Stream Operation Is Null Init With Default Client Operation");
            requestStreamOperation = new ClientRequestStreamOperation();
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
