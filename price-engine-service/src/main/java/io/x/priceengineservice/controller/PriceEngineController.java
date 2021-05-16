package io.x.priceengineservice.controller;

import io.x.priceengineservice.modal.PriceListModel;
import io.x.priceengineservice.modal.ProductPriceModel;
import io.x.priceengineservice.service.PriceEngineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/15/2021
 * Time: 10:44 PM
 */
@RestController
@RequestMapping("/v1/prices")
@CrossOrigin(origins = "http://localhost:3000")
public class PriceEngineController {

    private final PriceEngineService priceEngineService;

    public PriceEngineController(PriceEngineService priceEngineService) {
        this.priceEngineService = priceEngineService;
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceListModel>> getPriceList(@RequestParam("from") Integer from, @RequestParam("to") Integer to) {
        List<PriceListModel> priceListModelList = priceEngineService.getPriceList(from, to);

        priceListModelList.forEach(priceListModel -> priceListModel.getProductPrices().forEach(productPriceModel -> {
            productPriceModel.add(linkTo(methodOn(PriceEngineController.class).getPrice(productPriceModel.getProductId(), priceListModel.getNumberOfUnits(), 0)).withSelfRel());
        }));

        return new ResponseEntity<>(priceListModelList, HttpStatus.OK);
    }

    @GetMapping(path = "/calculate")
    public ResponseEntity<ProductPriceModel> getPrice(
            @RequestParam("productId") Long productId,
            @RequestParam(value = "numberOfSingleUnits", required = false) Integer numberOfSingleUnits,
            @RequestParam(value = "numberOfCartons", required = false) Integer numberOfCartons) {

        ProductPriceModel productPriceModel = priceEngineService.getPrice(productId, numberOfSingleUnits, numberOfCartons);
        productPriceModel.add(linkTo(methodOn(PriceEngineController.class).getPrice(productId, numberOfSingleUnits, numberOfCartons)).withSelfRel());

        return new ResponseEntity<>(productPriceModel, HttpStatus.OK);
    }

}