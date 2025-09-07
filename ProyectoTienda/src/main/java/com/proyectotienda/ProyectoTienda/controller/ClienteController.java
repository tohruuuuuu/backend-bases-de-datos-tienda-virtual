package com.proyectotienda.ProyectoTienda.controller;


import com.proyectotienda.ProyectoTienda.model.Cliente;
import com.proyectotienda.ProyectoTienda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


//anotaciones

@Controller

@RequestMapping("/clientes")
public class ClienteController {

    //meotdos controlados e inyeccion automatica de las dependencias

    @Autowired
    private ClienteRepository clienteRepository;

    //mostrar lista de clientes

    //anotacion
    @GetMapping
    public String listarClientes(Model model){
        //mostrar todos los clientes de la base de datos

        List<Cliente> clientes=clienteRepository.findAll();

        //agregar elementos a la lista

        model.addAttribute("clientes",clienteRepository);

        return "clientes";

    }

}
