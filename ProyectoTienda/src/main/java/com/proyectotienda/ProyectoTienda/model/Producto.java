package com.proyectotienda.ProyectoTienda.model;

public class Producto {

    private long id;

    private long nombreProducto;

    private double precio;

    private boolean disponible;

    //metodos

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public long getNombreProducto(){
        return nombreProducto;
    }

    public void setNombreProducto(long nombreProducto){
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio(){
        return precio;
    }

    public void setPrecio(){
        this.precio = precio;
    }

    public boolean getDisponible(){
        return disponible;
    }

    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }

}
