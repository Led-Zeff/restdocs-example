package courses.microservices.restdocsexample.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@JsonTest
@ActiveProfiles("snake")
public class BeerDTOSnakeTest extends BaseTest {

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void testSnake() throws JsonProcessingException {
    BeerDTO dto = getBeerDto();
    String json = objectMapper.writeValueAsString(dto);
    log.info(json);
  }

}
