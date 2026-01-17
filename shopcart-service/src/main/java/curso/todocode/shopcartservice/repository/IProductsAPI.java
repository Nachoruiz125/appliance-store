package curso.todocode.shopcartservice.repository;

import curso.todocode.shopcartservice.dto.ProductsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "products-service")
public interface IProductsAPI {

    @GetMapping("/products/{code}")
    public ProductsDTO findProductByCode(@PathVariable("code") Long code);

}
