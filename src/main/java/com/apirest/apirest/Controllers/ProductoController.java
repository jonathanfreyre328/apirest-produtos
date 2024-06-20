package com.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.apirest.apirest.Entities.Producto;
import com.apirest.apirest.Repositories.ProductoRepositorio;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id) {
        return productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoRepositorio.save(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto detailsProducto) {
        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        producto.setNombre(detailsProducto.getNombre());
        producto.setPrecio(detailsProducto.getPrecio());

        return productoRepositorio.save(producto);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        Producto producto = productoRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el producto con el ID: " + id));

        productoRepositorio.delete(producto);
        return "El producto con el ID: " + id + " fue eliminado correctamente.";
    }
}
