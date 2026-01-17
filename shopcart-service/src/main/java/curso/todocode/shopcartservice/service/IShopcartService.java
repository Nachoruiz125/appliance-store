package curso.todocode.shopcartservice.service;

import curso.todocode.shopcartservice.model.Shopcarts;

import java.util.List;

public interface IShopcartService {

    public List<Shopcarts> findAllShopcarts();

    public Shopcarts findShopcartById(Long id);

    public Shopcarts saveShopcart(List<Long> prodCodes);

    public Shopcarts editShopcart(Long id, List<Long>prodCodes);

}
