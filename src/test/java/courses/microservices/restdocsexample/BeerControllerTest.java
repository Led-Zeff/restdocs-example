package courses.microservices.restdocsexample;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StringUtils;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.restdocs.snippet.Attributes;

import courses.microservices.restdocsexample.domain.Beer;
import courses.microservices.restdocsexample.domain.BeerStyle;
import courses.microservices.restdocsexample.repository.BeerRepository;
import courses.microservices.restdocsexample.web.controller.BeerController;
import courses.microservices.restdocsexample.web.model.BeerDTO;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "courses.microservices.restdocsexample.web.mappers")
public class BeerControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  private BeerRepository beerRepository;

  @Test
  void getBeerById() throws Exception {
    BDDMockito.given(beerRepository.findById(ArgumentMatchers.any())).willReturn(Optional.of(new Beer()));

    mockMvc.perform(
      RestDocumentationRequestBuilders
      .get("/api/v1/beers/{beerId}", UUID.randomUUID())
      .param("isCold", "yes")
      .accept(MediaType.APPLICATION_JSON))
    .andExpect(MockMvcResultMatchers.status().isOk())
    .andDo(MockMvcRestDocumentation.document("v1/beers", RequestDocumentation.pathParameters(
      RequestDocumentation.parameterWithName("beerId").description("UUID of desired beer to get.")
    ), RequestDocumentation.requestParameters(
      RequestDocumentation.parameterWithName("isCold").description("If you want your beer to be dead cold")
    ), PayloadDocumentation.responseFields(
      PayloadDocumentation.fieldWithPath("name").description("Beer's id"),
      PayloadDocumentation.fieldWithPath("style").description("Beer's id"),
      PayloadDocumentation.fieldWithPath("upc").description("Beer's id"),
      PayloadDocumentation.fieldWithPath("price").description("Beer's id"),
      PayloadDocumentation.fieldWithPath("quantityOnHand").description("Beer's id")
    )));
  }

  @Test
  void saveNewBeer() throws Exception {
    BeerDTO beerDTO = getValidBeerDto();
    String beerDtoJson = objectMapper.writeValueAsString(beerDTO);

    ConstraiedFields fields = new ConstraiedFields(BeerDTO.class);

    mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/beers/").contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andDo(MockMvcRestDocumentation.document("v1/beers", PayloadDocumentation.requestFields(
          fields.withPath("name").description("Beer name"),
          fields.withPath("style").description("Beer style"),
          fields.withPath("upc").description("Beer UPC"),
          fields.withPath("price").description("Beer Price"),
          fields.withPath("quantityOnHand").description("Quantity currently on hand")
        )));
  }

  @Test
  void updateBeer() throws Exception {
    BeerDTO beerDTO = getValidBeerDto();
    String beerDtoJson = objectMapper.writeValueAsString(beerDTO);

    mockMvc.perform(RestDocumentationRequestBuilders
    .put("/api/v1/beers/" + UUID.randomUUID().toString()).contentType(MediaType.APPLICATION_JSON).content(beerDtoJson))
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
  
  private static class ConstraiedFields {
    private final ConstraintDescriptions constraintDescriptions;

    ConstraiedFields(Class<?> input) {
      constraintDescriptions = new ConstraintDescriptions(input);
    }

    private FieldDescriptor withPath(String path) {
      return PayloadDocumentation.fieldWithPath(path).attributes(Attributes.key("constraints").value(
        StringUtils.collectionToDelimitedString(constraintDescriptions.descriptionsForProperty(path), ". ")
      ));
    }
  }

}
