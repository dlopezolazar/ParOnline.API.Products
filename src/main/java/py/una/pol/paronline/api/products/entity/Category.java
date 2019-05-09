/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.paronline.api.products.entity;

import py.una.pol.paronline.commons.domain.entity.BaseEntity;

/**
 *
 * @author dlopez
 */
public class Category extends BaseEntity<Integer>{
    
    public Category(Integer id, String nombre) {
        super(id, nombre);
    }
    
}
