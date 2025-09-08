package com.proyectotienda.ProyectoTienda.model;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_producto", nullable = false, length = 150)
    private String nombreProducto; // Cambiado de long a String

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "disponible", nullable = false)
    private boolean disponible;

    @Column(name = "descripcion", length = 500)
    private String descripcion;

    @Column(name = "stock", nullable = false)
    private int stock;

    // Constructores
    public Producto() {}

    public Producto(String nombreProducto, double precio, boolean disponible, int stock) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.disponible = disponible;
        this.stock = stock;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() { // Corregido el tipo de retorno
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) { // Corregido el parámetro
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) { // Corregido - faltaba el parámetro
        this.precio = precio;
    }

    public boolean isDisponible() { // Cambiado a isDisponible() para boolean
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}