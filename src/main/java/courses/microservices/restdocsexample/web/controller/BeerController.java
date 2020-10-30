package courses.microservices.restdocsexample.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import courses.microservices.restdocsexample.domain.Beer;
import courses.microservices.restdocsexample.repository.BeerRepository;
import courses.microservices.restdocsexample.web.mappers.BeerMapper;
import courses.microservices.restdocsexample.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beers")
public class BeerController {
  
  private final BeerMapper beerMapper;
  private final BeerRepository beerRepository;

  @GetMapping("/{beerId}")
  public BeerDTO getBeerById(@PathVariable UUID beerId) {
    Beer beer = beerRepository.findById(beerId)
                  .orElseThrow();
    return beerMapper.beerToDto(beer);
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public void saveNewBeer(@RequestBody @Valid BeerDTO beerDTO) {
    Beer beer = beerMapper.dtoToBeer(beerDTO);
    beerRepository.save(beer);
  }

  @PutMapping("/{beerId}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  public void updateBeer(@RequestBody @Valid BeerDTO beerDTO, @PathVariable UUID beerId) {
    Beer beer = beerRepository.findById(beerId).orElse(new Beer());
    beer.setName(beerDTO.getName());
    beer.setStyle(beerDTO.getStyle().name());
    beer.setPrice(beerDTO.getPrice());
    beer.setUpc(beerDTO.getUpc());

    beerRepository.save(beer);
  }

}
