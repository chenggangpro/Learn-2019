package pro.chenggang.learn.spring.security.web;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnSpringSecurityWebApplicationTests {

  @Autowired
  protected WebApplicationContext context;
  protected MockMvc mvc;

  @BeforeClass
  public static void init() {
    org.apache.catalina.webresources.TomcatURLStreamHandlerFactory.getInstance();
  }

  @Before
  public void setUpContext() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(context)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .alwaysExpect(MockMvcResultMatchers.status().isOk())
            .alwaysDo(MockMvcResultHandlers.print())
            .build();
  }

}
