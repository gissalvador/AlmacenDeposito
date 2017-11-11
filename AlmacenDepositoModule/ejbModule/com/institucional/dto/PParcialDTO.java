package com.institucional.dto;

import java.io.Serializable;

public class PParcialDTO implements Serializable{

	
		private static final long serialVersionUID = 1L;
		
		private int codPParcial;
		private int nroPParcial;
		private String nomClasificacion;
		private Float movEntrada;
		private Float movSalida;
		
		public int getCodPParcial() {
			return codPParcial;
		}
		public void setCodPParcial(int codPParcial) {
			this.codPParcial = codPParcial;
		}
		public int getNroPParcial() {
			return nroPParcial;
		}
		public void setNroPParcial(int nroPParcial) {
			this.nroPParcial = nroPParcial;
		}
		public String getNomClasificacion() {
			return nomClasificacion;
		}
		public void setNomClasificacion(String nomClasificacion) {
			this.nomClasificacion = nomClasificacion;
		}
		public Float getMovEntrada() {
			return movEntrada;
		}
		public void setMovEntrada(Float movEntrada) {
			this.movEntrada = movEntrada;
		}
		public Float getMovSalida() {
			return movSalida;
		}
		public void setMovSalida(Float movSalida) {
			this.movSalida = movSalida;
		}
		
		
		
		
		
}
