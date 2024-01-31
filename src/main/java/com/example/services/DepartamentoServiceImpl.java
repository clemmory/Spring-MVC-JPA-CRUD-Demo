package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.DepartamentoDao;
import com.example.entities.Departamento;

//import lombok.NoArgsConstructor;

@Service
//@RequiredArgsContructor

public class DepartamentoServiceImpl implements DepartamentoService{

    @Autowired
    private DepartamentoDao departamentoDao;

    //Option2 using constructor - 

    //private final DepartamentoDao departamentoDao;

    @Override
    public List<Departamento> dameTodosLosDepartamentos() {
        return departamentoDao.findAll();
    }

    @Override
    public Departamento dameUnDepartamento(int idDepartamento) {
        return departamentoDao.findById(idDepartamento).get();
    }

    @Override
    public void persistirDpto(Departamento departamento) {
        departamentoDao.save(departamento);
    }

}