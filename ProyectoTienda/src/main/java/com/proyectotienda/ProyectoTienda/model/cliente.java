package com.proyectotienda.ProyectoTienda.model;

//anotaciones


import jakarta.persistence.Entity;
import jakarta.persistence.Id;


//anotacion que indica que clase debe ser gestionada por JPA
@Entity
public class cliente {

    //variables del cliente

    //llave primaria


    @Id //indica la clave primaria
    private Long id;

    private String nombre;

    private String correo;

    private int telefono;

    //metodos 'getter y setters

    //devuelve el Id del cliente
    public Long getid(){
        return id;
    }

    //asigna un nuevo valor Id cliente
    public void setId(Long id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;

    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo = correo;
    }

    public int getTelefono(){
        return telefono;
    }

    public void setTelefono(int telefono){
        this.telefono = telefono;
    }


}
