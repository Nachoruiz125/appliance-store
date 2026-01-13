package curso.todocode.productsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Repositorys extends JpaRepository<Product, Long> {
}
