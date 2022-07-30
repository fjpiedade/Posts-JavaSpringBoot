package ao.phi.posts.users;

import ao.phi.posts.model.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void userTestGetAll() throws Exception {
        mockMvc.perform(get("/api/v1/user"))
                .andExpect(status().isOk());
    }

    @Test
    public void userTestSave() throws Exception {

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

        mockMvc.perform(post("/api/v1/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userModel)))
                .andExpect(status().isOk());

    }
}
