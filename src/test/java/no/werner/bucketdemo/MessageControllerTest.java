package no.werner.bucketdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MessageControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void sendShouldWorkThenFail() throws Exception {
        SMS sms = SMS.builder().shortNumber("20000").from("4793272429").to("4745299039").message("Testing").build();

        String json = new ObjectMapper().writeValueAsString(sms);

        mvc.perform(MockMvcRequestBuilders.post("/send")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(204));

        mvc.perform(MockMvcRequestBuilders.post("/send")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(429));
    }

    @Test
    void sendShouldWorkBothTimes() throws Exception {
        SMS sms = SMS.builder().shortNumber("21111").from("4793272429").to("4745299039").message("Testing").build();

        String json = new ObjectMapper().writeValueAsString(sms);

        mvc.perform(MockMvcRequestBuilders.post("/send")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(204));

        mvc.perform(MockMvcRequestBuilders.post("/send")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(204));
    }

    @Test
    void sendShouldFailTheThirdTime() throws Exception {
        SMS sms = SMS.builder().shortNumber("22222").from("4793272429").to("4745299039").message("Testing").build();

        String json = new ObjectMapper().writeValueAsString(sms);

        mvc.perform(MockMvcRequestBuilders.post("/send")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(204));

        mvc.perform(MockMvcRequestBuilders.post("/send")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(204));

        mvc.perform(MockMvcRequestBuilders.post("/send")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().is(429));
    }
}