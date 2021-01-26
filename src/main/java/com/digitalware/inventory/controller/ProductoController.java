/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.digitalware.inventory.controller;

import com.digitalware.inventory.dto.Message;
import com.digitalware.inventory.dto.ProductoDto;
import com.digitalware.inventory.entity.Producto;
import com.digitalware.inventory.service.ProductoService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Usuario
 */
@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")

public class ProductoController {
    
    @Autowired
    ProductoService productoService;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Producto>> list(){
        
        List<Producto> list = productoService.list();
        return new ResponseEntity<List<Producto>>(list, HttpStatus.OK);
    }
    
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id){
        if(!productoService.existsById(id)){
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        } 
        Producto producto = productoService.getOne(id);
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
        
    }
    
    @GetMapping("/detail/{nombre}")
    public ResponseEntity<Producto> getByNombre(@PathVariable("nombre") String nombre){
        if(!productoService.existsByNombre(nombre)){
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        } 
        Producto producto = productoService.getByNombre(nombre).get();
        return new ResponseEntity<Producto>(producto, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductoDto productoDto){
        if(StringUtils.isBlank(productoDto.getNombre())){
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(productoDto.getPrecio() == null || productoDto.getPrecio()<0 ){
            return new ResponseEntity(new Message("El precio debe ser mayor que 0 y es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(productoService.existsByNombre(productoDto.getNombre())){
            return new ResponseEntity(new Message("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Producto producto = new Producto(productoDto.getNombre(), productoDto.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Message("Producto creado"),HttpStatus.OK);
    }
    
   
    @PostMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id,@RequestBody ProductoDto productoDto){
        if(!productoService.existsById(id)){
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        } 
        if(productoService.existsByNombre(productoDto.getNombre()) && productoService.getByNombre(productoDto.getNombre()).get().getId()!= id){
            return new ResponseEntity(new Message("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(productoDto.getNombre())){
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(productoDto.getPrecio() == null || productoDto.getPrecio() < 0){
            return new ResponseEntity(new Message("El precio debe ser mayor que 0 y es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Producto producto = productoService.getOne(id);
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        productoService.save(producto);
        return new ResponseEntity(new Message("Producto actualizado"),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(! productoService.existsById(id)){
            return new ResponseEntity(new Message("no existe el producto a eliminar"), HttpStatus.NOT_FOUND);
        }
        productoService.delete(id);
        return new ResponseEntity(new Message("Eliminado correctamente"), HttpStatus.OK); 
    }



}
