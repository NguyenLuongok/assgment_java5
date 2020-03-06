package assJava5.repository;


import assJava5.model.Products;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPagingAndSortingRepository extends PagingAndSortingRepository<Products,Long> {
}
