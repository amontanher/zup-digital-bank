package zup.digitalBank.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zup.digitalBank.models.CustomerPersonalDetail;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerPersonalDetail, Long> {
}
