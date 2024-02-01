package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.EmpleadoDao;
import com.example.dao.TelefonoDao;
import com.example.entities.Telefono;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class TelefonoServiceImpl implements TelefonoService {

    private final TelefonoDao telefonoDao;
    private final EmpleadoDao empleadoDao;

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
