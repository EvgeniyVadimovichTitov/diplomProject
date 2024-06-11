package ru.gb.productProfileServiseApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.gb.productProfileServiseApp.models.domain.ProductData;
import ru.gb.productProfileServiseApp.models.interfaces.IServiceProductData;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile/product")
@RequiredArgsConstructor
public class RestProductDataController {

    private final IServiceProductData serviceProduct;

    @PostMapping("addProduct")
    public ResponseEntity<ProductData> addProduct(@RequestBody ProductData product){
        System.out.println(product);
        serviceProduct.saveNewProduct(product);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<ProductData>> getAll(){
        return new ResponseEntity<>(serviceProduct.getAll(), HttpStatus.OK);
    }
}
