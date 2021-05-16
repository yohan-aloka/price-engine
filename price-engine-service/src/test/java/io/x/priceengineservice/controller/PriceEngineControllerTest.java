package io.x.priceengineservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.x.priceengineservice.modal.PriceListModel;
import io.x.priceengineservice.modal.ProductPriceModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/16/2021
 * Time: 10:49 AM
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PriceEngineControllerTest {

    private static final String BASE_URL = "http://localhost";
    @LocalServerPort
    int randomServerPort;

    @Test
    void test_001_getPriceList() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = BASE_URL.concat(":").concat(String.valueOf(randomServerPort)).concat("/price-engine-api/v1/prices/list?from=1&to=50");
        URI uri = new URI(baseUrl);

        HttpEntity<PriceListModel> requestEntity = new HttpEntity<>(null);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

            ObjectMapper mapper = new ObjectMapper();

            List<PriceListModel> priceList = mapper.readValue(responseEntity.getBody(), mapper.getTypeFactory().constructCollectionType(List.class, PriceListModel.class));

            Assertions.assertNotNull(priceList);
            assertEquals(50, priceList.size());
        } catch (JsonProcessingException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void test_002_getPriceUsingSingleUnits() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = BASE_URL.concat(":").concat(String.valueOf(randomServerPort)).concat("/price-engine-api/v1/prices/calculate?productId=2&numberOfSingleUnits=15");
        URI uri = new URI(baseUrl);

        HttpEntity<PriceListModel> requestEntity = new HttpEntity<>(null);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

            ObjectMapper mapper = new ObjectMapper();

            ProductPriceModel priceModel = mapper.readValue(responseEntity.getBody(), ProductPriceModel.class);

            assertNotNull(priceModel, "Price object cannot be null");
            assertEquals(BigDecimal.valueOf(2392.500).setScale(2, RoundingMode.HALF_UP), priceModel.getPrice().setScale(2, RoundingMode.HALF_UP), "Total price failed");
            assertEquals(BigDecimal.valueOf(82.500).setScale(2, RoundingMode.HALF_UP), priceModel.getDiscount().setScale(2, RoundingMode.HALF_UP), "Discount failed");

        } catch (JsonProcessingException e) {
            Assertions.fail(e.getMessage());
        }
    }

    @Test
    void test_003_getPriceUsingCartons() throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = BASE_URL.concat(":").concat(String.valueOf(randomServerPort)).concat("/price-engine-api/v1/prices/calculate?productId=1&numberOfCartons=3");
        URI uri = new URI(baseUrl);

        HttpEntity<PriceListModel> requestEntity = new HttpEntity<>(null);

        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, String.class);

            ObjectMapper mapper = new ObjectMapper();

            ProductPriceModel priceModel = mapper.readValue(responseEntity.getBody(), ProductPriceModel.class);

            assertNotNull(priceModel, "Price object cannot be null");
            assertEquals(BigDecimal.valueOf(507.500).setScale(2, RoundingMode.HALF_UP), priceModel.getPrice().setScale(2, RoundingMode.HALF_UP), "Total price failed");
            assertEquals(BigDecimal.valueOf(17.500).setScale(2, RoundingMode.HALF_UP), priceModel.getDiscount().setScale(2, RoundingMode.HALF_UP), "Discount failed");

        } catch (JsonProcessingException e) {
            Assertions.fail(e.getMessage());
        }
    }
}
