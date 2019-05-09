/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.products.entity;

import java.math.BigDecimal;
import lombok.Data;
import py.una.pol.paronline.commons.domain.entity.BaseEntity;

/**
 *
 * @author dlopez
 */
@Data
public class Product extends BaseEntity<Integer>{
    
    private Category category;
    private BigDecimal unitPrice;
    private Integer quantity;
    
    public Product(Integer id, String descripcion, Category category, BigDecimal unitPrice, Integer quantity) {
        super(id, descripcion);
        
        this.category = category;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        
    }
    
}
