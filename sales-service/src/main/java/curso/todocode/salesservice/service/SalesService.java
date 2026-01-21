package curso.todocode.salesservice.service;

import curso.todocode.salesservice.model.Sales;
import curso.todocode.salesservice.repository.ISalesRepository;
import curso.todocode.salesservice.repository.IShopcartAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesService implements ISalesService {

    @Autowired
    private ISalesRepository salesRepo;

    @Autowired
    private IShopcartAPI shopcartAPI;

    @Override
    public Sales findSaleById(Long id) {
        return salesRepo.findById(id).orElse(null);
    }

    @Override
    public List<Sales> findAllSales() {
        return salesRepo.findAll();
    }

    @Override
    public Sales newSale(Long shopcartId) {
        Sales sale = new Sales();

        sale.setDate(LocalDate.now());
        sale.setShopcartId(shopcartId);
        sale.setProdCodes(shopcartAPI.findShopcartById(shopcartId).getProdCodes());
        sale.setTotalPrice(shopcartAPI.findShopcartById(shopcartId).getTotalPrice());

        return salesRepo.save(sale);
    }

    @Override
    public void deleteSale(Long id) {
        salesRepo.deleteById(id);
    }
}
