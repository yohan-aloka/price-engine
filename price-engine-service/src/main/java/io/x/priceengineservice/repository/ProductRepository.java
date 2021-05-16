package io.x.priceengineservice.repository;

import io.x.priceengineservice.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/15/2021
 * Time: 9:17 PM
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
