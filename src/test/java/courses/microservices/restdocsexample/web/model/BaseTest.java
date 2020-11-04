package courses.microservices.restdocsexample.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import courses.microservices.restdocsexample.domain.BeerStyle;

public class BaseTest {
  BeerDTO getBeerDto() {
    return BeerDTO.builder()
      .name("Beer")
      .style(BeerStyle.LAGER)
      .upc(43557646897L)
      .price(new BigDecimal("233.43"))
      .quantityOnHand(233)
      .createdDate(OffsetDateTime.of(2020, 11, 2, 10, 20, 46, 3453, ZoneOffset.UTC))
      .lastModifiedDate(OffsetDateTime.of(2020, 11, 3, 18, 5, 47, 3453, ZoneOffset.UTC))
      .build();
  } 
}
