package com.turismo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.turismo.model.Travel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turismo.repository.TravelRepository;

import com.turismo.util.GenerateFile;


@RestController
public class TravelController {
	
	@Autowired
	TravelRepository repository;
	
	//http://localhost:8080/travel/save
	/*
	 * INSERIR
	   {
	    "origem": {
    			"id": 5,
    			"descricao": "Beto Carrero World",
   				"diaria": 280
				},
		"destino": {
    		"id": 6,
    		"descricao": "Narnia",
    		"diaria": 1000
		},
	    "saida": "19/09/2019",
	    "volta": "10/10/2019"
	   }
	 * 
	 * ALTERAR
	   {
	    "id": 3,
	    "origem": {
		    "id": 5,
		    "descricao": "Disney",
		    "diaria": 2800
		},
		"destino": {
		    "id": 4,
		    "descricao": "Brotas",
		    "diaria": 50
		},
	    "saida": "19/09/2019",
	    "volta": "10/10/2019"
	   }
	 * */
	@PostMapping("travel/save")
	public Travel save(@RequestBody Travel travel){
		return repository.save(travel);
	}
	
	//http://localhost:8080/travel/all
	@GetMapping("travel/all")
	public List<Travel> all(){
		return (List<Travel>) repository.findAll();
	}
	
	//http://localhost:8080/travel/file
	@GetMapping("travel/file")
	public String createFile(){
		String arquivo = "C:\\Users\\Home\\Desktop\\relatorio.txt";
		
		GenerateFile editorArquivo = new GenerateFile(arquivo, true);
		
		try {
			SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
			String dataAtual = formatoData.format(new Date());
			
			editorArquivo.writeToFile("Relatório gerado em " + dataAtual);
			editorArquivo.writeToFile("id;origem;destino;valorTotal");
			
			List<Travel> travels = repository.findAll();
			
			for (Travel travel : travels) {
				editorArquivo.writeToFile(String.valueOf(travel.getId())+";"+travel.getOrigem().getDescricao()+";"+travel.getDestino().getDescricao()+";"+String.valueOf(travel.getValorTotal()));
			}
		} catch (IOException e) {
			return "Falha ao gerar o arquivo";
		}
		
		return "OK";
	}
	
	//http://localhost:8080/travel/delete/2
	@DeleteMapping("viagem/delete/{id}")
	public boolean delete(@PathVariable("id") int id) {
		try {
			repository.deleteById(id);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}