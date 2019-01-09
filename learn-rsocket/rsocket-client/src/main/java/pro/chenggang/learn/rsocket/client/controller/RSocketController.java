package pro.chenggang.learn.rsocket.client.controller;

import com.alibaba.fastjson.JSON;
import io.rsocket.Payload;
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pro.chenggang.learn.rsocket.client.core.RSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Map;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/8
 * @version: v1.0.0
 * @email: cg@choicesoft.com.cn
 */
@Slf4j
@RestController
public class RSocketController {

    private final RSocketClient rSocketClient;

    public RSocketController(RSocketClient rSocketClient) {
        this.rSocketClient = rSocketClient;
    }

    @PostMapping("/fire")
    public Mono<String> fireAndForget(@RequestBody Map<String,String> param){
        this.rSocketClient.fireAndForget(DefaultPayload.create(JSON.toJSONString(param)));
        log.debug("RSocket Controller [Fire And Forget]-> {}",param);
        return Mono.just("Success");
    }

    @PostMapping("/request")
    public Mono<Payload> requestResponse(@RequestBody Map<String,String> param) {
        log.debug("RSocket Controller [Request And Response]-> {}",param);
        return this.rSocketClient.requestResponse(DefaultPayload.create(JSON.toJSONString(param)));
    }

    @PostMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Payload> requestStream(@RequestBody Map<String,String> param) {
        log.debug("RSocket Controller [Request Stream]-> {}",param);
        return this.rSocketClient.requestStream(DefaultPayload.create(JSON.toJSONString(param)));
    }

    @GetMapping(value = "/channel", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Payload> requestChannel() {
        log.debug("RSocket Controller [Request Channel]");
        return this.rSocketClient.requestChannel(Flux
                .interval(Duration.ofSeconds(1))
                .take(10)
                .map((index)-> DefaultPayload.create(String.valueOf(index)))
        );
    }

    @PostMapping("/metadata")
    public Mono<String> metadataPush(@RequestBody Map<String,String> param) {
        log.debug("RSocket Controller [Metadata Push]-> {}",param);
        this.rSocketClient.fireAndForget(DefaultPayload.create(JSON.toJSONString(param)));
        return Mono.just("Success");
    }
}
