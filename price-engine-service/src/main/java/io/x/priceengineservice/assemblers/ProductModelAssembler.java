package io.x.priceengineservice.assemblers;

import io.x.priceengineservice.controller.ProductController;
import io.x.priceengineservice.entity.ProductEntity;
import io.x.priceengineservice.modal.ProductModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/15/2021
 * Time: 10:09 PM
 */
@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<ProductEntity, ProductModel> {

    public ProductModelAssembler() {
        super(ProductController.class, ProductModel.class);
    }

    @Override
    public ProductModel toModel(ProductEntity entity) {
        ProductModel productModel = instantiateModel(entity);

        productModel.setId(entity.getId());
        productModel.setName(entity.getName());
        productModel.setUnitsPerCarton(entity.getUnitsPerCarton());
        productModel.setCartonPrice(entity.getCartonPrice());
//        productModel.setCreatedDateTime(entity.getCreatedDateTime());
//        productModel.setLastUpdatedDateTime(entity.getLastUpdatedDateTime());
//        productModel.setCreatedUser(entity.getCreatedUser());
//        productModel.setLastUpdatedUser(entity.getLastUpdatedUser());

        productModel.add(linkTo(
                methodOn(ProductController.class)
                        .getProduct(entity.getId()))
                .withSelfRel());


        return productModel;
    }

    @Override
    public CollectionModel<ProductModel> toCollectionModel(Iterable<? extends ProductEntity> entities) {
        CollectionModel<ProductModel> productModels = super.toCollectionModel(entities);

        productModels.add(linkTo(methodOn(ProductController.class).getProducts()).withSelfRel());

        return productModels;
    }
}
