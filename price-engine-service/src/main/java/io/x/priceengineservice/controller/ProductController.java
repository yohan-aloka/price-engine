package io.x.priceengineservice.controller;

import io.x.priceengineservice.assemblers.ProductModelAssembler;
import io.x.priceengineservice.entity.ProductEntity;
import io.x.priceengineservice.modal.ProductModel;
import io.x.priceengineservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/15/2021
 * Time: 8:10 PM
 */
@RestController
@RequestMapping("/v1/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductModelAssembler productModelAssembler;
    private final PagedResourcesAssembler<ProductEntity> pagedResourcesAssembler;

    public ProductController(ProductRepository productRepository, ProductModelAssembler productModelAssembler, PagedResourcesAssembler<ProductEntity> pagedResourcesAssembler) {
        this.productRepository = productRepository;
        this.productModelAssembler = productModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<ProductModel>> getProducts() {
        List<ProductEntity> products = productRepository.findAll();

        return new ResponseEntity<>(productModelAssembler.toCollectionModel(products), HttpStatus.OK);
    }

    @GetMapping(path = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<ProductModel>> getProducts(Pageable pageable) {
        Page<ProductEntity> products = productRepository.findAll(pageable);


        PagedModel<ProductModel> collModel = pagedResourcesAssembler
                .toModel(products, productModelAssembler);

        return new ResponseEntity<>(collModel, HttpStatus.OK);
    }

    @GetMapping(path = "/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductModel> getProduct(@PathVariable Long productId) {
        return productRepository.findById(productId)
                .map(productModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
