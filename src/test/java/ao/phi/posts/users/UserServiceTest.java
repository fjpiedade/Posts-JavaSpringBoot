package ao.phi.posts.users;

import ao.phi.posts.model.UserModel;
import ao.phi.posts.repository.UserRepository;
import ao.phi.posts.service.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.UUID;

//@SpringBootTestXmlBeanApplication.class
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = XmlBeanApplication.class)
//@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/application-context.xml")
//@ContextConfiguration(locations = "classpath*:/spring/applicationContext*.xml")
//@SpringBootTest(classes = XmlBeanApplication.class)
//@SpringBootTest
//@ContextConfiguration(locations = "/applicationContext.xml")
public class UserServiceTest {

    @TestConfiguration
    static class UserServiceTestConfiguration{
        @Bean
        public UserService userService(){
            return new UserService();
        }
    }

    @Autowired
    //@Qualifier("userService")
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    public void numberOfDayInSystemPhone(){
        String phone="923947627";
        int days = userService.numberOfDays(phone);
        Assertions.assertEquals(days,10);
    }

    @Test
    public void findByName(){
        String name = "Ensei Tankado";
        String result = userService.findByName(name);
        Assertions.assertEquals(result,"raiz@gmail.com");
    }

    @Before
    public void setup(){
        UserModel userModel= new UserModel(
                UUID.randomUUID(),
                "Ensei Tankado",
                "raiz@gmail.com",
                "244923947627",
                "password",
                "https://localhost/avatar/raiz.png",
                false,
                false,
                LocalDateTime.now(),
                null,
                null,
                null,
                null
                );
        Mockito.when(userRepository.findByName(userModel.getName()))
                .thenReturn(java.util.Optional.of(userModel));
    }
}
