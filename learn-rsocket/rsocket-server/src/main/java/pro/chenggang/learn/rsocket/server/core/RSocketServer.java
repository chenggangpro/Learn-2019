package pro.chenggang.learn.rsocket.server.core;

import com.alibaba.fastjson.JSON;
import io.rsocket.AbstractRSocket;
import io.rsocket.ConnectionSetupPayload;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.util.StringUtils;
import pro.chenggang.learn.rsocket.server.template.RSocketOperationTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class RSocketServer extends AbstractRSocket {

    private final RSocketOperationTemplate rSocketOperationTemplate;

    private static final ConcurrentHashMap<String,RSocket> CLIENT_MAP  = new ConcurrentHashMap<>();

    public RSocketServer(RSocketOperationTemplate rSocketOperationTemplate) {
        this.rSocketOperationTemplate = rSocketOperationTemplate;
    }

    public void connect(ConnectionSetupPayload connectionSetupPayload, RSocket clientRSocket){
        SetupPayload setupPayload = JSON.parseObject(connectionSetupPayload.getDataUtf8(),SetupPayload.class);
        String clientName;
        if(null == setupPayload || StringUtils.isEmpty(setupPayload.getClientName())){
            clientName = "Anonymous_Client"+ UUID.randomUUID().toString().replace("-","");
        }else{
            clientName = setupPayload.getClientName();
        }
        log.debug("[RSocket Server]Connect RSocket Client :{},Client RSocket:{}",clientName,clientRSocket);
        CLIENT_MAP.put(clientName,clientRSocket);
        clientRSocket.onClose().subscriberContext(context -> context.put("clientName",clientName));
    }

    @Override
    public Mono<Void> fireAndForget(Payload payload) {
        return rSocketOperationTemplate.opsForFireAndForget().fireAndForget(payload);
    }

    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        return rSocketOperationTemplate.opsForRequestResponse().requestResponse(payload);
    }

    @Override
    public Flux<Payload> requestStream(Payload payload) {
        return rSocketOperationTemplate.opsForRequestStream().requestStream(payload);
    }

    @Override
    public Mono<Void> metadataPush(Payload payload) {
        return rSocketOperationTemplate.opsForMetadataPush().metadataPush(payload);
    }

    @Override
    public Flux<Payload> requestChannel(Publisher<Payload> payloads) {
        return rSocketOperationTemplate.opsForRequestChannel().requestChannel(payloads);
    }
}
