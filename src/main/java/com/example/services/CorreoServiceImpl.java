package com.example.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dao.CorreoDao;
import com.example.dao.EmpleadoDao;
import com.example.entities.Correo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CorreoServiceImpl implements CorreoService {



    private final CorreoDao correoDao;
    private final EmpleadoDao empleadoDao;
    
    @Override
    public List<Correo> getEmaList(int idEmpleado) {
        return correoDao.findByEmpleado(empleadoDao.findById(idEmpleado).get());
    }

    @Override
    public void deleteCorreoEmpoyee(int idEmpleado) {
        correoDao.deleteByEmpleado(empleadoDao.findById(idEmpleado).get());

    }

    @Override
    public void addCorreoEmployee(int idEmpleado, Correo correo) {
        correo.setEmpleado(empleadoDao.findById(idEmpleado).get());
        correoDao.save(correo);

    }

}
