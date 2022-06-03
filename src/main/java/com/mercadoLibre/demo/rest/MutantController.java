package com.mercadoLibre.demo.rest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mercadoLibre.demo.Entity.Dna;
import com.mercadoLibre.demo.dto.EstadisticaDTO;
import com.mercadoLibre.demo.model.MutantModel;
import com.mercadoLibre.demo.service.IPersona;

/**
 * Clase donde se exponen los servicios POST mutant y GET stats del API REST
 * 
 * @author ccardona
 * 
 * @version 1.0, 03/06/2022
 * 
 * @param body, body del request para el servicio POST, el servicio GET no requiere body
 * 
 * @return ResponseEntity<Object>, resultado del consumo de la operacion.
 *
 */


@RestController
@RequestMapping("/")

public class MutantController {
	
	@Autowired
	IPersona iPersona;	
	
	@PostMapping("/mutant")
	public ResponseEntity mutante(@RequestBody Dna persona){
		
		//Se reciben los datos como una lista para ingresarlos en la matriz
		List<String> dna = persona.getDna();
		//Se convierte la lista a una secuencia de tipo String para guardarse en la base de datos
		String secuencia = String.join(",", dna);
				
		//Llamado a  Funcion isMutant devuelve true si una secuencia DNA pertenece a un mutante
		boolean muntant = MutantModel.isMutant(dna);
				
		HttpStatus status;
		String body;
		if(muntant) {			
			status = HttpStatus.OK;
			body = "{\"message\": \"200-OK\"}";
			
			//Seteo de atributos para guardar en base de datos
			persona.setAdn(secuencia);
			persona.setTipo("mutant");		

			try {
				iPersona.guardarPersona(persona);
			} catch (Exception e) {
				System.out.println("No se pudo registrar DNA, posiblemente este DNA ya fue registrado!");
			}
			
			
		} else {
			status = HttpStatus.FORBIDDEN;
			body = "{\"message\": \"403-Forbidden\"}";
			
			
			//Seteo de atributos para guardar en base de datos
			persona.setAdn(secuencia);
			persona.setTipo("human");	
			
			try {
				iPersona.guardarPersona(persona);	
			} catch (Exception e) {
				System.out.println("No se pudo registrar DNA, posiblemente este DNA ya fue registrado!");
			}
					
			
		}
		return ResponseEntity
				.status(status)
				.contentType(MediaType.APPLICATION_JSON)
				.body(body);		
		
	}
	

	@RequestMapping(value="/stats", method = RequestMethod.GET)
	public String Estadisticas() {
		
		//Seteo de atributos para consultar en base de datos
		EstadisticaDTO estadistica = new EstadisticaDTO();
		
		int humanos = iPersona.consultarH();
		int mutants = iPersona.consultarM();
		estadistica.setHumanos(humanos);
		estadistica.setMutantes(mutants);
		
		//Llamado a función que devuelve el ratio entre en mutantes y humanos

		double ratio = MutantModel.calcularRatio(estadistica);
		
		estadistica.setRatio(ratio);

		
		return estadistica.toString();
	}

}
