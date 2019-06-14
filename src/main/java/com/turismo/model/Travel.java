package com.turismo.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="travel")
public class Travel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Place origem;
	
	@ManyToOne
	private Place destino;
	
	private String saida;
	
	private String volta;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Place getOrigem() {
		return origem;
	}

	public void setOrigem(Place origem) {
		this.origem = origem;
	}

	public Place getDestino() {
		return destino;
	}

	public void setDestino(Place destino) {
		this.destino = destino;
	}

	public String getSaida() {
		return saida;
	}

	public void setSaida(String saida) {
		this.saida = saida;
	}

	public String getVolta() {
		return volta;
	}

	public void setVolta(String volta) {
		this.volta = volta;
	}
	
	private long getDiasDeViagem() {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date inicio = formatoData.parse(this.saida);
			Date fim = formatoData.parse(this.volta);
			
			long dias = fim.getTime() - inicio.getTime();
			
			return (TimeUnit.DAYS.convert(dias, TimeUnit.MILLISECONDS));
		} catch(ParseException e) {
			return 0;
		}
	}
	
	public double getValorTotal() {
		long dias = this.getDiasDeViagem();
		
		return (this.getDestino().getDiaria() * dias);
	}
	
}