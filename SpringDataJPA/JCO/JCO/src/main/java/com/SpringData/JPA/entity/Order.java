package com.SpringData.JPA.entity;

import com.SpringData.JPA.entity.embedded.Address;
import com.SpringData.JPA.entity.embedded.OrderId;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_order") //Orders is a reserved keyword in SQL, so we are using "_order" as the table name
public class Order {

    @EmbeddedId
    private OrderId id;

    private String productName;
    private Integer quantity;

    @Embedded // This annotation is used to indicate that the Address field is an embedded object, but this is optionnal as JPA will automatically detect it as an embedded object as Address is annotated with @Embeddable
    private Address perm_OrderAddress;

    @Embedded 
    @AttributeOverride(name = "houseName", column = @jakarta.persistence.Column(name = "temp_house_name")) // This is used to override the column name of the embedded Address object
    @AttributeOverride(name = "street", column = @jakarta.persistence.Column(name = "temp_street"))
    @AttributeOverride(name = "city", column = @jakarta.persistence.Column(name = "temp_city"))
    private Address temp_OrderAddress;
    
}
