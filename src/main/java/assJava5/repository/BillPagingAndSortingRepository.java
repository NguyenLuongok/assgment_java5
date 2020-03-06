package assJava5.repository;


import assJava5.model.Bill;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BillPagingAndSortingRepository extends PagingAndSortingRepository<Bill,Long> {
}
