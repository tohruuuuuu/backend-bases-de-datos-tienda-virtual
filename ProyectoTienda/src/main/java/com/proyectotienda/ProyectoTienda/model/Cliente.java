package com.proyectotienda.ProyectoTienda.controller;

import com.proyectotienda.ProyectoTienda.model.Cliente;
import com.proyectotienda.ProyectoTienda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Mostrar lista de clientes
    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteRepository.findAll();
        model.addAttribute("clientes", clientes);
        return "clientes";
    }

    // Mostrar formulario para registrar nuevo cliente
    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "registro_cliente";
    }

    // Procesar el registro del nuevo cliente con validación
    @PostMapping("/guardar")
    public String guardarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        // Si hay errores de validación, regresar al formulario
        if (bindingResult.hasErrors()) {
            model.addAttribute("cliente", cliente);
            return "registro_cliente";
        }

        try {
            // Verificar si ya existe un cliente con el mismo correo
            if (clienteRepository.findByCorreo(cliente.getCorreo()).isPresent()) {
                bindingResult.rejectValue("correo", "correo.duplicado",
                        "Ya existe un cliente con ese correo electrónico");
                model.addAttribute("cliente", cliente);
                return "registro_cliente";
            }

            // Guardar el cliente en la base de datos
            clienteRepository.save(cliente);

            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("mensaje", "Cliente registrado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");

        } catch (DataIntegrityViolationException e) {
            // Error de integridad (correo duplicado)
            redirectAttributes.addFlashAttribute("mensaje", "Ya existe un cliente con ese correo electrónico");
            redirectAttributes.addFlashAttribute("tipo", "error");

        } catch (Exception e) {
            // Error general
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar el cliente: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
        }

        // Redirigir a la lista de clientes
        return "redirect:/clientes";
    }
}