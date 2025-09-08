package com.proyectotienda.ProyectoTienda.controller;

import com.proyectotienda.ProyectoTienda.model.Cliente;
import com.proyectotienda.ProyectoTienda.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Optional;

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

    // Procesar el registro del nuevo cliente (versión simplificada sin validación de correo duplicado)
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente,
                                 BindingResult bindingResult,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        try {
            // Guardar el cliente en la base de datos
            clienteRepository.save(cliente);

            // Mensaje de éxito
            redirectAttributes.addFlashAttribute("mensaje", "Cliente registrado exitosamente");
            redirectAttributes.addFlashAttribute("tipo", "success");

        } catch (DataIntegrityViolationException e) {
            // Error de integridad (correo duplicado u otra violación de constraint)
            redirectAttributes.addFlashAttribute("mensaje", "Error: Ya existe un cliente con ese correo electrónico");
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/clientes/nuevo";

        } catch (Exception e) {
            // Error general
            redirectAttributes.addFlashAttribute("mensaje", "Error al registrar el cliente: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/clientes/nuevo";
        }

        // Redirigir a la lista de clientes
        return "redirect:/clientes";
    }

    //funcion ruta editar campos
// Agregar estos métodos a tu ClienteController.java existente

    // quiero ostrar el formulario para editar cliente existente
@GetMapping("/editar/{id}")
public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
    try {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if (clienteOptional.isPresent()) {
            model.addAttribute("cliente", clienteOptional.get());
            return "editar_cliente";
        } else {
            redirectAttributes.addFlashAttribute("mensaje", "Cliente no encontrado");
            redirectAttributes.addFlashAttribute("tipo", "error");
            return "redirect:/clientes";
        }
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("mensaje", "Error al cargar el cliente: " + e.getMessage());
        redirectAttributes.addFlashAttribute("tipo", "error");
        return "redirect:/clientes";
    }
}

// Actualizar el método guardar existente para manejar tanto nuevo como edición
@PostMapping("/guardar")
public String guardarCliente(@ModelAttribute("cliente") Cliente cliente,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {

    try {
        // Guardar funciona tanto para nuevo (id=null) como para actualización (id!=null)
        clienteRepository.save(cliente);

        // Mensaje según si es nuevo o actualización
        String mensaje = (cliente.getId() == null) ? "Cliente registrado exitosamente" : "Cliente actualizado exitosamente";
        redirectAttributes.addFlashAttribute("mensaje", mensaje);
        redirectAttributes.addFlashAttribute("tipo", "success");

    } catch (DataIntegrityViolationException e) {
        redirectAttributes.addFlashAttribute("mensaje", "Error: Ya existe un cliente con ese correo electrónico");
        redirectAttributes.addFlashAttribute("tipo", "error");

        if (cliente.getId() == null) {
            return "redirect:/clientes/nuevo";
        } else {
            return "redirect:/clientes/editar/" + cliente.getId();
        }

    } catch (Exception e) {
        String tipoOperacion = (cliente.getId() == null) ? "registrar" : "actualizar";
        redirectAttributes.addFlashAttribute("mensaje", "Error al " + tipoOperacion + " el cliente: " + e.getMessage());
        redirectAttributes.addFlashAttribute("tipo", "error");

        if (cliente.getId() == null) {
            return "redirect:/clientes/nuevo";
        } else {
            return "redirect:/clientes/editar/" + cliente.getId();
        }
    }

    return "redirect:/clientes";
    }

}