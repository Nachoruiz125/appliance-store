package curso.todocode.salesservice.service;

import curso.todocode.salesservice.model.Sales;

import java.util.List;

public interface ISalesService {

    public Sales findSaleById(Long id);

    public List<Sales> findAllSales();

    public Sales newSale(Long shopcartId);

    public void deleteSale(Long id);

}