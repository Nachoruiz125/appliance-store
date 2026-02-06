package curso.todocode.productsservice.service;

import curso.todocode.productsservice.model.Products;
import curso.todocode.productsservice.repository.IProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService implements IProductsService{

    @Autowired
    private IProductsRepository prodRepo;

    @Override
    public List<Products> listProducts() {

        return prodRepo.findAll();
    }

    @Override
    public Products findProductByCode(Long code) {

        return prodRepo.findById(code).orElse(null);
    }

    @Override
    public Products createProduct(Products product) {

        return prodRepo.save(product);
    }
}
