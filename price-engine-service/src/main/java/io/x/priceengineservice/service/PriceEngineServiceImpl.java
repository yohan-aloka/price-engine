package io.x.priceengineservice.service;

import io.x.priceengineservice.entity.ProductEntity;
import io.x.priceengineservice.modal.PriceListModel;
import io.x.priceengineservice.modal.ProductPriceModel;
import io.x.priceengineservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/15/2021
 * Time: 10:45 PM
 */
@Service
public class PriceEngineServiceImpl implements PriceEngineService {

    private final ProductRepository productRepository;

    public PriceEngineServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<PriceListModel> getPriceList(Integer from, Integer to) {
        List<ProductEntity> product = productRepository.findAll();

        List<PriceListModel> prices = new ArrayList<>();

        for (Integer i = from; i <= to; i++) {
            PriceListModel price = new PriceListModel();
            price.setNumberOfUnits(i);
            price.setProductPrices(new ArrayList<>());

            Integer finalI = i;
            product.forEach((productEntity -> {
                ProductPriceModel productPriceModel = getProductPriceModel(finalI, productEntity);
                price.getProductPrices().add(productPriceModel);
            }));

            prices.add(price);
        }

        return prices;
    }

    @Override
    public ProductPriceModel getPrice(Long productId, Integer numberOfSingleUnits, Integer numberOfCartons) {
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(productId);

        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();


            return getProductPriceModel(numberOfSingleUnits != null ? numberOfSingleUnits : (numberOfCartons * productEntity.getUnitsPerCarton()), productEntity);

        }

        return null;
    }

    private ProductPriceModel getProductPriceModel(Integer finalI, ProductEntity productEntity) {
        ProductPriceModel productPriceModel = new ProductPriceModel();
        productPriceModel.setProductName(productEntity.getName());
        productPriceModel.setProductId(productEntity.getId());

        BigDecimal cartonPrice = productEntity.getCartonPrice();
        cartonPrice = cartonPrice.add(productEntity.getCartonPrice().multiply(BigDecimal.valueOf(30.0 / 100.0)));

        BigDecimal unitPrice = cartonPrice.divide(BigDecimal.valueOf(productEntity.getUnitsPerCarton()), RoundingMode.HALF_UP);


        if (finalI >= productEntity.getUnitsPerCarton()) {
            int numberOfCartons = finalI / productEntity.getUnitsPerCarton();
            int numberOfSingleUnits = finalI % productEntity.getUnitsPerCarton();

            BigDecimal totalCartonsPrice = productEntity.getCartonPrice().multiply(BigDecimal.valueOf(numberOfCartons));
            BigDecimal totalSingleUnitsPrice = unitPrice.multiply(BigDecimal.valueOf(numberOfSingleUnits));
            BigDecimal discount = BigDecimal.ZERO;

            if (numberOfCartons >= 3) {
                discount = productEntity.getCartonPrice().multiply(BigDecimal.valueOf(10.0 / 100.0));
            }

            productPriceModel.setPrice(totalCartonsPrice.add(totalSingleUnitsPrice).subtract(discount));
            productPriceModel.setDiscount(discount);
            productPriceModel.setNumberOfCartons(numberOfCartons);
            productPriceModel.setNumberOfSingleUnits(numberOfSingleUnits);
        } else {
            productPriceModel.setPrice(unitPrice.multiply(BigDecimal.valueOf(finalI)));
            productPriceModel.setDiscount(BigDecimal.ZERO);
            productPriceModel.setNumberOfCartons(0);
            productPriceModel.setNumberOfSingleUnits(finalI);
        }
        return productPriceModel;
    }

}