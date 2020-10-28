package courses.microservices.restdocsexample;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import courses.microservices.restdocsexample.domain.BeerStyle;
import courses.microservices.restdocsexample.repository.BeerRepository;
import courses.microservices.restdocsexample.web.controller.BeerController;
import courses.microservices.restdocsexample.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;

@ExtendWith(RestDocumentationExtension.class)
@RequiredArgsConstructor
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

  private final MockMvc mockMvc;
  private final ObjectMapper objectMapper;

  @MockBean
  private BeerRepository beerRepository;

  @Test
  void getBeerById() throws Exception {
    // given...
    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/api/v1/beers/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void saveNewBeer() throws Exception {
    BeerDTO beerDTO = getValidBeerDto();
    String beerDtoJson = objectMapper.writeValueAsString(beerDTO);

    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beers/").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void updateBeer() throws Exception {
    BeerDTO beerDTO = getValidBeerDto();
    String beerDtoJson = objectMapper.writeValueAsString(beerDTO);

    mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beers/" + UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  private BeerDTO getValidBeerDto() {
    return BeerDTO.builder()
        .name("Name")
        .style(BeerStyle.API)
        .price(new BigDecimal("123.23"))
        .upc(4342434L)
        .build();
  }

}
