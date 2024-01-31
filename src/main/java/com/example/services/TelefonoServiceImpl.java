package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.EmpleadoDao;
import com.example.dao.TelefonoDao;
import com.example.entities.Empleado;
import com.example.entities.Telefono;

@Service

public class TelefonoServiceImpl implements TelefonoService {

    @Autowired
    private TelefonoDao telefonoDao;
    private EmpleadoDao empleadoDao;

    @Override
    public List<Telefono> getPhoneList(int idEmpleado) {
        return telefonoDao.findByEmpleado(empleadoDao.findById(idEmpleado).get());
    }

    @Override
    public void deletePhoneEmpoyee(int idEmpleado) {
        telefonoDao.deleteByEmpleado(empleadoDao.findById(idEmpleado).get());

    }

    @Override
    public void addPhoneEmployee(Telefono telefono, int idEmpleado) {
        telefono.setEmpleado(empleadoDao.findById(idEmpleado).get());
        telefonoDao.save(telefono);

    }

}
