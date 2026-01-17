package curso.todocode.shopcartservice.repository;

import curso.todocode.shopcartservice.model.Shopcarts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopcartsRepository extends JpaRepository<Shopcarts, Long> {
}
