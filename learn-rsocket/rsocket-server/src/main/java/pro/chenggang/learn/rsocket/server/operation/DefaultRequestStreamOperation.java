package pro.chenggang.learn.rsocket.server.operation;

import io.rsocket.Payload;
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/7
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
public class DefaultRequestStreamOperation implements RequestStreamOperation {

    @Override
    public Flux<Payload> requestStream(Payload payload) {
        String payloadStr = payload.getDataUtf8();
        log.debug("DefaultRequestStreamOperation Handle Payload : {}",payloadStr);
        return Flux
                .fromStream(Stream.generate(()-> "DefaultRequestStreamOperation->"+payloadStr+"["+ Instant.now().toString()+"]"))
                .delayElements(Duration.ofSeconds(1))
                .map(DefaultPayload::create);
    }
}
