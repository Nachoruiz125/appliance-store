package curso.todocode.shopcartservice.service;

import curso.todocode.shopcartservice.model.Shopcarts;
import curso.todocode.shopcartservice.repository.IProductsAPI;
import curso.todocode.shopcartservice.repository.IShopcartsRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
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

        shopRepo.save(shopcart);

        return shopcart;
    }

    @Override
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallback")
    @Retry(name = "products-service-retry")
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
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallback")
    @Retry(name = "products-service-retry")
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
    @CircuitBreaker(name = "products-service", fallbackMethod = "fallback")
    @Retry(name = "products-service-retry")
    public Shopcarts removeProductFromShopcart(Long id, Long prodCode) {
        this.shopcart = shopRepo.findById(id).orElse(null);

        if(shopcart != null) {

            shopcart.getProdCodes().removeIf(codes -> codes.equals(prodCode));
            shopcart.setTotalPrice(calculateTotalPrice(shopcart.getProdCodes()));

            shopRepo.save(shopcart);
        }
        return shopcart;
    }

    @Override
    public double calculateTotalPrice(List<Long> prodCodes) {
        double price = 0.0;

        for(Long proCo : prodCodes) {
            price += productsAPI.findProductByCode(proCo).getPrice();
        }


        return price;
    }

    public Shopcarts fallback(List<Long> prodCodes, Throwable throwable) {
        shopcart.setTotalPrice(-1.1);
        return shopcart;
    }
}