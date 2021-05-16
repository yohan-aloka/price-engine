package io.x.priceengineservice.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User: Yohan Aloka
 * Date: 5/15/2021
 * Time: 8:14 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "products", itemRelation = "product")
public class ProductModel extends RepresentationModel<ProductModel> {


    private Long id;
    private String name;
    private Integer unitsPerCarton;
    private BigDecimal cartonPrice;
//    private Date createdDateTime;
//    private Date lastUpdatedDateTime;
//    private String createdUser;
//    private String lastUpdatedUser;

}
