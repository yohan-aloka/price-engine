package io.x.priceengineservice.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/15/2021
 * Time: 10:50 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PriceListModel {

    private Integer numberOfUnits;
    private List<ProductPriceModel> productPrices;

}