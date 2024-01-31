package com.example.services;

import java.util.List;

import com.example.entities.Correo;

public interface CorreoService {

    
    public List<Correo> getEmaList(int idEmpleado);
    public void deleteCorreoEmpoyee(int idEmpleado);
    public void addCorreoEmployee(int idEmpleado, Correo correo);


}
