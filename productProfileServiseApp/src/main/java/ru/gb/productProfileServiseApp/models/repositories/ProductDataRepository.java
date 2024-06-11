package ru.gb.productProfileServiseApp.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.productProfileServiseApp.models.domain.ProductData;


import java.util.UUID;
@Repository
public interface ProductDataRepository extends JpaRepository<ProductData, UUID> {

}
