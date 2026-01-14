package curso.todocode.productsservice.controller;

import curso.todocode.productsservice.model.Products;
import curso.todocode.productsservice.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService prodServ;

    @GetMapping("/{code}")
    public Products findProductByCode(@PathVariable Long code) {
        return prodServ.findProductByCode(code);
    }

    @GetMapping("/all")
    public List<Products> findAllProducts() {
        return prodServ.listProducts();
    }

    @PostMapping("/new")
    public Products saveProduct(@RequestBody Products product) {

        return prodServ.createProduct(product);
    }

}
