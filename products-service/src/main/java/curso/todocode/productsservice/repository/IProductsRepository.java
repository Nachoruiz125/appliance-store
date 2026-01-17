package curso.todocode.productsservice.repository;

import curso.todocode.productsservice.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductsRepository extends JpaRepository<Products, Long> {

    @Query("SELECT p FROM Products p WHERE p.code = :code")
    Products findProductByCode(Long code);

}
