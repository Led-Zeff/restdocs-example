package courses.microservices.restdocsexample.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@JsonTest
public class BerrDTOTest extends BaseTest {

  @Autowired
  ObjectMapper objectMapper;

  @Test
  void testSerializeDto() throws JsonProcessingException {
    BeerDTO dto = getBeerDto();
    String json = objectMapper.writeValueAsString(dto);
    log.info(json);
  }

  @Test
  void deserializeDto() throws JsonMappingException, JsonProcessingException {
    String json = "{\"name\":\"Beer\",\"style\":\"LAGER\",\"upc\":43557646897,\"price\":233.43,\"quantityOnHand\":233}";
    BeerDTO dto = objectMapper.readValue(json, BeerDTO.class);
    log.info(dto);
  }

}
