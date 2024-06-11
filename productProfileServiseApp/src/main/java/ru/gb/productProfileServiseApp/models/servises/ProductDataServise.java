package ru.gb.productProfileServiseApp.models.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.productProfileServiseApp.models.domain.ProductData;
import ru.gb.productProfileServiseApp.models.interfaces.IServiceProductData;
import ru.gb.productProfileServiseApp.models.repositories.ProductDataRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDataServise implements IServiceProductData {

    private final ProductDataRepository productRepository;

    @Override
    public void saveNewProduct(ProductData product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductData> getAll() {
        return productRepository.findAll();
    }
}
