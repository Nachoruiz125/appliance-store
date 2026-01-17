package curso.todocode.shopcartservice.service;

import curso.todocode.shopcartservice.dto.ProductsDTO;
import curso.todocode.shopcartservice.model.Shopcarts;
import curso.todocode.shopcartservice.repository.IProductsAPI;
import curso.todocode.shopcartservice.repository.IShopcartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopcartService implements IShopcartService {

    @Autowired
    private IShopcartsRepository shopRepo;

    @Autowired
    private IProductsAPI productsAPI;

    @Override
    public List<Shopcarts> findAllShopcarts() {
        return shopRepo.findAll();
    }

    @Override
    public Shopcarts findShopcartById(Long id) {
        return shopRepo.findById(id).orElse(null);
    }

    @Override
    public Shopcarts saveShopcart(List<Long> prodCodes) {
        Shopcarts shopcart = new Shopcarts();
        ProductsDTO productsDTO;
        Double price= 0.0;

        shopcart.setProdCodes(prodCodes);

        for(Long proCo : shopcart.getProdCodes()) {
            productsDTO = productsAPI.findProductByCode(proCo);
            price = productsDTO.getPrice() + price;

        }
        shopcart.setTotalPrice(price);
        shopRepo.save(shopcart);

        return shopcart;
    }

    @Override
    public Shopcarts editShopcart(Long id, List<Long> prodCodes) {
        Shopcarts shopcart = shopRepo.findById(id).orElse(null);
        Double price= 0.0;

        if (shopcart != null) {
            shopcart.setProdCodes(prodCodes);

            for(Long proCo : shopcart.getProdCodes()) {
                price = productsAPI.findProductByCode(proCo).getPrice() + price;
            }

            shopcart.setTotalPrice(price);
            shopRepo.save(shopcart);
        }

        return shopcart;
    }
}