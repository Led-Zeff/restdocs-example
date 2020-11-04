package courses.microservices.restdocsexample.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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
  @Size(min = 3, max = 100)
  @JsonProperty("beerName")
  private String name;
  
  @NotNull
  private BeerStyle style;

  @Positive
  @NotNull
  private Long upc;

  @Positive
  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private BigDecimal price;

  @Positive
  private Integer quantityOnHand;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = Shape.STRING)
  private OffsetDateTime createdDate;
  private OffsetDateTime lastModifiedDate;

}
