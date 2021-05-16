package io.x.priceengineservice.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/16/2021
 * Time: 12:11 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPriceModel extends RepresentationModel<ProductPriceModel> {

    private Long productId;
    private String productName;
    private BigDecimal price;
    private BigDecimal discount;
    private Integer numberOfCartons;
    private Integer numberOfSingleUnits;

}
