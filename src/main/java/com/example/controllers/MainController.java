package com.example.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Telefono;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;

import lombok.RequiredArgsConstructor;

@Controller
// Receive what the dispatcher servlet(receiving request http) is sending. To
// understand the request we need the annotations @RequestMapping
@RequestMapping("/") // (direct request finishing by / here )
@RequiredArgsConstructor

public class MainController {

    private final EmpleadoService empleadoService;
    private final DepartamentoService departamentoService;
    private final Logger LOG = Logger.getLogger("MainController");

    // OLD METHOD

    // @GetMapping("/all")
    // public ModelAndView dameEmpleados(){
    // ModelAndView modelo = new ModelAndView("views/listadoEmpleados");
    // List<Empleado> empleados = empleadoService.dameTodosLosEmpleados();
    // modelo.addObject("empleado", empleados);
    // return modelo;
    // }

    @GetMapping("/all")
    public String dameEmpleados(Model model) {
        model.addAttribute("empleados", empleadoService.dameTodosLosEmpleados());
        return "views/listadoEmpleados";
    }

    // Method when a parameter is received with the request-
    // It is used but it is a method less common than send a variable in the route
    // @GetMapping("/detalles")
    // public String detalles(@RequestParam(name ="id") int idEmpleado, Model
    // model){
    // LOG.info("ID Empleado Recibido: " + idEmpleado);
    // return "views/empleadoDetalles";
    // }

    @GetMapping("/detalles/{id}")
    public String detalles(@PathVariable(name = "id") int idEmpleado, Model model) {
        LOG.info("ID Empleado Recibido: " + idEmpleado);
        Empleado empleado = empleadoService.dameUnEmpleado(idEmpleado);

        List<Departamento> departamentos = departamentoService.dameTodosLosDepartamentos();
        model.addAttribute("departamentos", departamentos);

        if(empleado.getTelefonos() != null) {
            String numerosTelefono = empleado.getTelefonos().stream()
                .map(Telefono::getNumero)
                .collect(Collectors.joining(" ; "));
                    
            model.addAttribute("numerosTelefono", numerosTelefono);
        }
        
                //4. Construct correos from the employee details
        if(empleado.getCorreos() != null) {
            String correos = empleado.getCorreos().stream()
                .map(Correo::getCorreo)
                .collect(Collectors.joining(" ; "));
                    
            model.addAttribute("correos", correos);
        }

        return "views/empleadoDetalles";
    }

    @GetMapping("/formularioAlta")
    public String formularioAltaModificacionEmpleado(Model model) {
        // Send an empty object
        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);

        // Send departamento
        model.addAttribute("departamentos", departamentoService.dameTodosLosDepartamentos());
        return "views/formularioAlta";
    }

    @PostMapping("/persistirEmpleado")
    public String persistirEmpleado(@ModelAttribute(name = "empleado") Empleado empleado,
            @RequestParam(name = "phoneNumber", required = false) String telefonosRecibidos,
            @RequestParam(name = "email", required = false) String correosRecibidos) {

        if (telefonosRecibidos != null) {
            String[] arrayTelefonos = telefonosRecibidos.split(";");
            List<String> numerosTelefonos = Arrays.asList(arrayTelefonos);

            List<Telefono> telefonos = new ArrayList<>();
            numerosTelefonos.stream()
                    .forEach(numeroTelefono -> {
                        telefonos.add(
                                Telefono.builder()
                                        .numero(numeroTelefono)
                                        .empleado(empleado)
                                        .build());
                    });
            empleado.setTelefonos((telefonos));
        }

        if (correosRecibidos != null) {
            String[] arrayCorreos = correosRecibidos.split(";");
            List<String> correosList = Arrays.asList(arrayCorreos);

            List<Correo> correos = new ArrayList<>();
            correosList.stream()
                    .forEach(correo -> {
                        correos.add(
                                Correo.builder()
                                        .correo(correo)
                                        .empleado(empleado)
                                        .build());
                    });
            empleado.setCorreos(correos);
        }
        empleadoService.persistirEmpleado(empleado);
        return "redirect:/all";
    }

    @GetMapping("/actualizar/{id}")
    public String actualizarEmpleado(@PathVariable(name = "id", required = true) int idEmpleado, Model model) {

        // 1. Look for employee details
        Empleado empleado = empleadoService.dameUnEmpleado(idEmpleado);
        model.addAttribute("empleado", empleado);

        // 2. Get the departamentos
        List<Departamento> departamentos = departamentoService.dameTodosLosDepartamentos();
        model.addAttribute("departamentos", departamentos);

        // 3. Construc phone numbers from the employee details
        if (empleado.getTelefonos() != null) {
            String numerosTelefono = empleado.getTelefonos().stream()
                    .map(Telefono::getNumero)
                    .collect(Collectors.joining(" ; "));

            model.addAttribute("numerosTelefono", numerosTelefono);
        }

        // 4. Construct correos from the employee details
        if (empleado.getCorreos() != null) {
            String correos = empleado.getCorreos().stream()
                    .map(Correo::getCorreo)
                    .collect(Collectors.joining(" ; "));

            model.addAttribute("correos", correos);
        }

        return "views/formularioAlta";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmpleado(@PathVariable(name="id", required=true) int idEmpleado){

        Empleado empleado = empleadoService.dameUnEmpleado(idEmpleado);

        empleadoService.eliminarEmpleado(idEmpleado);

        
        return "redirect:/all";

    }
}
