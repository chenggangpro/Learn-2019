package pro.chenggang.learn.rsocket.server.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019/1/11
 * @version: v1.0.0
 * @email: chenggangpro@gmail.com
 */
@Slf4j
@RestController
public class RScoketServerController {

    @PostMapping("/metadata/push")
    public Mono<String> pushMetadata(@RequestBody Object metaData){
        String data = JSON.toJSONString(metaData);
        log.debug("[RScoketServerController]Push MetaData :{}",data);
        return Mono.just("Success");
    }
}
