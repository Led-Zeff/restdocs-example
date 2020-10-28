package courses.microservices.restdocsexample.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class BeerPagedList extends PageImpl<BeerDTO> {

  private static final long serialVersionUID = 456378576438657323L;

  public BeerPagedList(List<BeerDTO> content, Pageable pageable, long total) {
    super(content, pageable, total);
  }

  public BeerPagedList(List<BeerDTO> content) {
    super(content);
  }
  
}
