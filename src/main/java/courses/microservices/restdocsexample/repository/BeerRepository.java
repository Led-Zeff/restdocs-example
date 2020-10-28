package courses.microservices.restdocsexample.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import courses.microservices.restdocsexample.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
  
}
