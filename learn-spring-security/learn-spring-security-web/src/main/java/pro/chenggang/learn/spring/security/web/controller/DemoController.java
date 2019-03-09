package pro.chenggang.learn.spring.security.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019-02-15
 * @version: v1.0.0
 */
@Slf4j
@RestController
public class DemoController {

    @PostMapping("/demo")
    public String toDemo(){
        return "success";
    }
}
