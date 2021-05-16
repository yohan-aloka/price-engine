package io.x.priceengineservice.service;

import io.x.priceengineservice.modal.PriceListModel;
import io.x.priceengineservice.modal.ProductPriceModel;
import io.x.priceengineservice.service.PriceEngineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/16/2021
 * Time: 10:18 AM
 */
@SpringBootTest
class PriceEngineServiceTest {

    @Autowired
    PriceEngineService priceEngineService;


    @Test
    void test_001_getPriceList() {
        List<PriceListModel> priceList = priceEngineService.getPriceList(1, 50);

        assertNotNull(priceList);
        assertEquals(50, priceList.size());
    }

    @Test
    void test_002_getPriceUsingSingleUnits() {
        ProductPriceModel price = priceEngineService.getPrice(2L, 15, null);

        assertNotNull(price, "Price object cannot be null");
        assertEquals(BigDecimal.valueOf(2392.500).setScale(2, RoundingMode.HALF_UP), price.getPrice().setScale(2, RoundingMode.HALF_UP), "Total price failed");
        assertEquals(BigDecimal.valueOf(82.500).setScale(2, RoundingMode.HALF_UP), price.getDiscount().setScale(2, RoundingMode.HALF_UP), "Discount failed");

    }

    @Test
    void test_003_getPriceUsingCartons() {
        ProductPriceModel price = priceEngineService.getPrice(1L, null, 3);

        assertNotNull(price, "Price object cannot be null");
        assertEquals(BigDecimal.valueOf(507.500).setScale(2, RoundingMode.HALF_UP), price.getPrice().setScale(2, RoundingMode.HALF_UP), "Total price failed");
        assertEquals(BigDecimal.valueOf(17.500).setScale(2, RoundingMode.HALF_UP), price.getDiscount().setScale(2, RoundingMode.HALF_UP), "Discount failed");

    }

}
