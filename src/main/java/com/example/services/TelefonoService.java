package com.example.services;

import java.util.List;

import com.example.entities.Telefono;

public interface TelefonoService {

    public List<Telefono> getPhoneList(int idEmpleado);
    public void deletePhoneEmpoyee(int idEmpleado);
    public void addPhoneEmployee(Telefono telefono, int idEmpleado);


}
