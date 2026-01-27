package curso.todocode.shopcartservice.service;

import curso.todocode.shopcartservice.dto.ProductsDTO;
import curso.todocode.shopcartservice.model.Shopcarts;
import curso.todocode.shopcartservice.repository.IProductsAPI;
import curso.todocode.shopcartservice.repository.IShopcartsRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopcartService implements IShopcartService {

    @Autowired
    private IShopcartsRepository shopRepo;

    @Autowired
    private IProductsAPI productsAPI;

    private Shopcarts shopcart =  new Shopcarts();

    @Override
    public List<Shopcarts> findAllShopcarts() {
        return shopRepo.findAll();
    }

    @Override
    public Shopcarts findShopcartById(Long id) {
        return shopRepo.findById(id).orElse(null);
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallback")
    @Retry(name = "products-service-retry")
    public Shopcarts saveShopcart(List<Long> prodCodes) {

        shopcart.setProdCodes(prodCodes);
        shopcart.setTotalPrice(calculateTotalPrice(prodCodes));

        //createExeption();
        shopRepo.save(shopcart);

        return shopcart;
    }

    @Override
    public Shopcarts editShopcart(Long id, List<Long> prodCodes) {
        this.shopcart = shopRepo.findById(id).orElse(null);

        if (shopcart != null) {
            shopcart.setProdCodes(prodCodes);

            shopcart.setTotalPrice(calculateTotalPrice(prodCodes));

            shopRepo.save(shopcart);
        }

        return shopcart;
    }

    @Override
    public Shopcarts addProductToShopcart(Long id, Long prodCode) {
        this.shopcart = shopRepo.findById(id).orElse(null);

        if(shopcart != null) {

            shopcart.getProdCodes().add(prodCode);
            shopcart.setTotalPrice(calculateTotalPrice(shopcart.getProdCodes()));

            shopRepo.save(shopcart);
        }

        return shopcart;
    }

    @Override
    public Shopcarts removeProductFromShopcart(Long id, Long prodCode) {
        this.shopcart = shopRepo.findById(id).orElse(null);

        if(shopcart != null) {

            shopcart.getProdCodes().removeIf(codes -> codes.equals(prodCode));
            shopcart.setTotalPrice(calculateTotalPrice(shopcart.getProdCodes()));

            shopRepo.save(shopcart);
        }
        return shopcart;
    }


    private double calculateTotalPrice(List<Long> prodCodes) {
        double price = 0.0;

        for(Long proCo : prodCodes) {
            price += productsAPI.findProductByCode(proCo).getPrice();
        }


        return price;
    }

    public double fallback(List<Long> prodCodes, Throwable throwable) {
        double error= -1.1;
        return error;
    }

    public void createExeption() {
        throw new RuntimeException("Prueba recillience y circuit breaker");
    }
}