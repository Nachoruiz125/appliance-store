package curso.todocode.shopcartservice.controller;

import curso.todocode.shopcartservice.model.Shopcarts;
import curso.todocode.shopcartservice.service.IShopcartService;
import curso.todocode.shopcartservice.service.ShopcartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopcarts")
public class ShopcartsController {

    @Autowired
    private IShopcartService cartService;

    @GetMapping("/all")
    public List<Shopcarts> findAllShopcarts() {
        return cartService.findAllShopcarts();
    }

    @GetMapping("/find/{id}")
    public Shopcarts findShopcartById(@PathVariable Long id) {
        return cartService.findShopcartById(id);
    }

    @PostMapping("/new")
    public Shopcarts saveShopcart(@RequestBody List<Long> prodCodes) {
        return cartService.saveShopcart(prodCodes);
    }

    @PutMapping("/edit")
    public Shopcarts updateShopcart(@RequestBody Long id, List<Long> prodCodes) {
        return cartService.editShopcart(id, prodCodes);
    }

    @PutMapping("/add/{id}/{prodCode}")
    public Shopcarts addProductToShopcart(@PathVariable Long id,
                                          @PathVariable Long prodCode) {
        return cartService.addProductToShopcart(id, prodCode);
    }

    @PutMapping("/remove/{id}/{prodCode}")
    public Shopcarts removeProductFromShopcart(@PathVariable Long id,
                                               @PathVariable Long prodCode) {
        return cartService.removeProductFromShopcart(id, prodCode);
    }
}
