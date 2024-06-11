package ru.gb.productProfileServiseApp.models.interfaces;

import ru.gb.productProfileServiseApp.models.domain.ProductData;

import java.util.List;

public interface IServiceProductData {
    void saveNewProduct(ProductData product);
    List<ProductData> getAll();
}
