package courses.microservices.restdocsexample.web.mappers;

import org.mapstruct.Mapper;

import courses.microservices.restdocsexample.domain.Beer;
import courses.microservices.restdocsexample.web.model.BeerDTO;

@Mapper
public interface BeerMapper {
  BeerDTO beerToDto(Beer beer);
  Beer dtoToBeer(BeerDTO beerDTO);
}
