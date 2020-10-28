package courses.microservices.restdocsexample.web.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import courses.microservices.restdocsexample.domain.BeerStyle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDTO {
  
  @NotBlank
  private String name;
  
  @NotNull
  private BeerStyle style;


  @Positive
  @NotNull
  private Long upc;

  @Positive
  @NotNull
  private BigDecimal price;

  private Integer quantityOnHand;

}
