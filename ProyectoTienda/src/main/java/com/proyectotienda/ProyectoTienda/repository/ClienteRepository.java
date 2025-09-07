package com.proyectotienda.ProyectoTienda.repository;


//metodos de libreria de comunicacion con la base de datos e interfaz
import com.proyectotienda.ProyectoTienda.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//anotacion
@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{
    //al extender el JPArepository, esta interfaz hereda los meotodps como:
    //findAll()
    //findById(Long Id)
    //save(Cliente cliente)
    //deleteByid(Long id)
    //existById(Long id)

//esto viene por defecto
}
