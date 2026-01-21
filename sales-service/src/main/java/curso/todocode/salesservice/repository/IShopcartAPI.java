package curso.todocode.salesservice.repository;

import curso.todocode.salesservice.dto.ShopcartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "shopcart-service")
public interface IShopcartAPI {

    @GetMapping("/shopcarts/find/{id}")
    public ShopcartDTO findShopcartById(@PathVariable("id") Long code);

}
