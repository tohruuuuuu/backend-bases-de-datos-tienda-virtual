package com.proyectotienda.ProyectoTienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//anotaciones


@Controller
public class HomeController {

    //metodo
    @GetMapping("/")
    public String home(Model model){
        return "home";
    }



}
