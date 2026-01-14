package curso.todocode.productsservice.service;

import curso.todocode.productsservice.model.Products;

import java.util.List;

public interface IProductsService {

    public List<Products> listProducts();

    public Products findProductByCode(Long code);

}
