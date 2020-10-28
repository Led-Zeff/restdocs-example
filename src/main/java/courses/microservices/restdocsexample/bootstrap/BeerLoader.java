package courses.microservices.restdocsexample.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import courses.microservices.restdocsexample.domain.Beer;
import courses.microservices.restdocsexample.repository.BeerRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

  private final BeerRepository beerRepository;

  @Override
  public void run(String... args) throws Exception {
    loadBeers();
  }

  private void loadBeers() {
    if (beerRepository.count() == 0) {
      beerRepository.save(Beer.builder()
        .name("Beer")
        .style("ALE")
        .quantityToBrew(20)
        .minOnHand(12)
        .upc(2343453454L)
        .price(new BigDecimal("12.23"))
      .build());

      beerRepository.save(Beer.builder()
        .name("Beer 2")
        .style("PALE_ALE")
        .quantityToBrew(100)
        .minOnHand(20)
        .upc(2343453572L)
        .price(new BigDecimal("15.23"))
      .build());
    }
  }

}
