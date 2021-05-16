package io.x.priceengineservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
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
@Entity
@ToString
@Table(name = "product")
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", length = 50)
    private String name;
    @Column(name = "units_per_carton", length = 11)
    private Integer unitsPerCarton;
    @Column(name = "carton_price", scale = 2, precision = 20)
    private BigDecimal cartonPrice;
    @CreationTimestamp
    @Column(name = "created_date_time")
    private Date createdDateTime;
    @UpdateTimestamp
    @Column(name = "last_updated_date_time")
    private Date lastUpdatedDateTime;
    @Column(name = "created_user")
    private String createdUser;
    @Column(name = "last_updated_user")
    private String lastUpdatedUser;

}
