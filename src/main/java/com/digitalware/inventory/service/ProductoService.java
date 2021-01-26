/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalware.inventory.service;

import com.digitalware.inventory.entity.Producto;
import com.digitalware.inventory.repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
@Transactional
public class ProductoService {
    
    @Autowired
    ProductoRepository productoRepository;
    
    
//    metodos
    
    public List<Producto> list(){
        return productoRepository.findAll();
    }
    
    public Producto getOne(int id){
        return productoRepository.findById(id).get();
    }
    
    public Optional<Producto> getByNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }
    
    public void save(Producto producto){
        productoRepository.save(producto);
    }
    
    public void delete(int id){
        productoRepository.deleteById(id);
    }
    public boolean existsById(int id){
        return productoRepository.existsById(id);
    }
    public boolean existsByNombre(String nombre){
        return productoRepository.existsByNombre(nombre);
    }
    
}
