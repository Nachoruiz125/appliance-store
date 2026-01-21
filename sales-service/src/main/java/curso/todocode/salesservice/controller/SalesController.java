package curso.todocode.salesservice.controller;

import curso.todocode.salesservice.model.Sales;
import curso.todocode.salesservice.service.ISalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    @Autowired
    private ISalesService salesService;

    @GetMapping("/find/{id}")
    public Sales findSaleById(@PathVariable Long id) {
        return salesService.findSaleById(id);
    }

    @GetMapping("/find")
    public List<Sales> findAllSales() {
        return salesService.findAllSales();
    }

    @PostMapping("/new")
    public Sales newSale(@RequestBody Long shopcartId) {
        return salesService.newSale(shopcartId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSale(@PathVariable Long id) {
        salesService.deleteSale(id);
    }

}
