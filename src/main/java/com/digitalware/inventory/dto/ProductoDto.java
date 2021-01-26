/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalware.inventory.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;



/**
 *
 * @author Usuario
 */
public class ProductoDto {
    
    @NotBlank
    private String nombre;
    @Min(0)
    private Float precio;

    public ProductoDto() {
    }

    public ProductoDto(String nombre, Float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    
    
}
