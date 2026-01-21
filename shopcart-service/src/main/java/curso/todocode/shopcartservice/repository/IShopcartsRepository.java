package curso.todocode.shopcartservice.repository;

import curso.todocode.shopcartservice.model.Shopcarts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IShopcartsRepository extends JpaRepository<Shopcarts, Long> {

    @Query("SELECT p FROM Shopcarts p WHERE p.id = :id")
    Shopcarts findShopcartById(Long id);

}
