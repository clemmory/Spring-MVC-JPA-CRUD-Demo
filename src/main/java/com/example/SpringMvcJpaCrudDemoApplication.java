package com.example;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.Correo;
import com.example.entities.Departamento;
import com.example.entities.Empleado;
import com.example.entities.Genero;
import com.example.entities.Telefono;
import com.example.services.CorreoService;
import com.example.services.DepartamentoService;
import com.example.services.EmpleadoService;
import com.example.services.TelefonoService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor

public class SpringMvcJpaCrudDemoApplication implements CommandLineRunner {

	private final DepartamentoService departamentoService;
	private final TelefonoService telefonoService;
	private final EmpleadoService empleadoService;
	private final CorreoService correoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcJpaCrudDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//Create departamento (parent)
		Departamento dpt1 = Departamento.builder()
			.nombre("RRHH")
			.build();

		Departamento dpt2 = Departamento.builder()
			.nombre("INFORMATICA")
			.build();

		Departamento dpt3 = Departamento.builder()
			.nombre("CONTABILIDAD")
			.build();	
		
		departamentoService.persistirDpto(dpt1);
		departamentoService.persistirDpto(dpt2);
		departamentoService.persistirDpto(dpt3);

		Empleado emp1 = Empleado.builder()
			.nombre("Clémentine")
			.primerApellido("Mory")
			.segundoApellido("Esquer")
			.fechaAlta(LocalDate.of(2021, Month.JANUARY,23))
			.salario(2500.50)
			.genero(Genero.MUJER)
			.departamento(departamentoService.dameUnDepartamento(1))
			.build();
		
				
		Empleado emp2 = Empleado.builder()
			.nombre("Joan")
			.primerApellido("Baptiste")
			.segundoApellido("Esquer")
			.fechaAlta(LocalDate.of(1998, Month.SEPTEMBER,14))
			.salario(2800.50)
			.genero(Genero.HOMBRE)
			.departamento(departamentoService.dameUnDepartamento(2))
			.build();
		
				
		Empleado emp3 = Empleado.builder()
			.nombre("Mapi")
			.primerApellido("Belles")
			.segundoApellido("Molina")
			.fechaAlta(LocalDate.of(2020, Month.APRIL,03))
			.salario(1500.50)
			.genero(Genero.MUJER)
			.departamento(departamentoService.dameUnDepartamento(1))
			.build();

				
		Empleado emp4 = Empleado.builder()
			.nombre("Truc")
			.primerApellido("Lespes")
			.segundoApellido("Gamond")
			.fechaAlta(LocalDate.of(2016, Month.FEBRUARY,14))
			.salario(6700.50)
			.genero(Genero.OTRO)
			.departamento(departamentoService.dameUnDepartamento(3))
			.build();

		empleadoService.persistirEmpleado(emp1);
		empleadoService.persistirEmpleado(emp2);
		empleadoService.persistirEmpleado(emp3);
		empleadoService.persistirEmpleado(emp4);


		//List<Telefono> telefonosEmp1 = new ArrayList<>();
		
		Telefono telefono1Emp1 = Telefono.builder()
			.numero("651728873")
			.empleado(empleadoService.dameUnEmpleado(1))
			.build();
		
		Telefono telefono2Emp1 = Telefono.builder()
			.numero("651723673")
			.empleado(empleadoService.dameUnEmpleado(1))
			.build();
		
			// telefonosEmp1.add(telefono1Emp1);
			// telefonosEmp1.add(telefono2Emp1);

		//List<Telefono> telefonosEmp2 = new ArrayList<>();

		Telefono telefono1Emp2 = Telefono.builder()
			.numero("652783673")
			.empleado(empleadoService.dameUnEmpleado(2))
			.build();

		Telefono telefono2Emp2 = Telefono.builder()
			.numero("651723684")
			.empleado(empleadoService.dameUnEmpleado(2))
			.build();

			//telefonosEmp2.add(telefono1Emp2);
			//telefonosEmp2.add(telefono2Emp2);

		telefonoService.addPhoneEmployee(telefono1Emp1, 1);
		telefonoService.addPhoneEmployee(telefono2Emp1, 1);
		telefonoService.addPhoneEmployee(telefono2Emp2, 2);
		telefonoService.addPhoneEmployee(telefono1Emp2, 2);


		
			Correo correo1Emp1 = Correo.builder()
				.correo("clementine.m@gmail.com")
				.build();
			
			Correo correo1Emp2 = Correo.builder()
				.correo("joan@gmail.com")
				.build();
			
		
			Correo correo1Emp3 = Correo.builder()
				.correo("rosa@gmail.com")
				.build();
			
			Correo correo1Emp4 = Correo.builder()
				.correo("gaby@gmail.com")
				.build();
		
				correoService.addCorreoEmployee(1, correo1Emp1);
				correoService.addCorreoEmployee(2, correo1Emp2);
				correoService.addCorreoEmployee(3, correo1Emp3);
				correoService.addCorreoEmployee(4, correo1Emp4);



	




	}
}

