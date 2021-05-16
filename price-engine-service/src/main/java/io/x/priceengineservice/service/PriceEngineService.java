package io.x.priceengineservice.service;

import io.x.priceengineservice.modal.PriceListModel;
import io.x.priceengineservice.modal.ProductPriceModel;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/15/2021
 * Time: 10:45 PM
 */
public interface PriceEngineService {

    List<PriceListModel> getPriceList(Integer from, Integer to);

    ProductPriceModel getPrice(Long productId,
                                     Integer numberOfSingleUnits,
                                     Integer numberOfCartons);

}