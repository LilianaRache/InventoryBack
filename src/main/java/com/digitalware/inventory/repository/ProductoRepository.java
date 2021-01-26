/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalware.inventory.repository;

import com.digitalware.inventory.entity.Producto;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
    Optional<Producto> findByNombre(String nombre);
    Boolean existsByNombre(String nombre);
    
}
