package curso.todocode.salesservice.repository;

import curso.todocode.salesservice.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISalesRepository extends JpaRepository<Sales, Long> {
}
