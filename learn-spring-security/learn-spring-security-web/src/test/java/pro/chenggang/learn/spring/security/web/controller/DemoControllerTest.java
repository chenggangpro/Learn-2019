package pro.chenggang.learn.spring.security.web.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pro.chenggang.learn.spring.security.web.LearnSpringSecurityWebApplicationTests;

/**
 * @classDesc:
 * @author: chenggang
 * @createTime: 2019-02-15
 * @version: v1.0.0
 * @email: cg@choicesoft.com.cn
 */
public class DemoControllerTest extends LearnSpringSecurityWebApplicationTests {

  @Test
  public void toDemo() throws Exception {
    mvc.perform(MockMvcRequestBuilders
            .post("/demo")
            .with(SecurityMockMvcRequestPostProcessors.user(User.builder().username("user").password("password").roles("USER").build()))
            .contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(MockMvcResultMatchers.content().string("success"));

  }
}