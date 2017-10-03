package com.institucional.dto;

import java.io.Serializable;

public class AlmacenDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
		private Integer codAlmacen;
		
		private String nomAlmacen;
		
		private Integer codCentro;
				
		private String nomCentro;

		public Integer getCodAlmacen() {
			return codAlmacen;
		}

		public void setCodAlmacen(Integer codAlmacen) {
			this.codAlmacen = codAlmacen;
		}

		public String getNomAlmacen() {
			return nomAlmacen;
		}

		public void setNomAlmacen(String nomAlmacen) {
			this.nomAlmacen = nomAlmacen;
		}
		
		
		public Integer getCodCentro() {
			return codCentro;
		}

		public void setCodCentro(Integer codCentro) {
			this.codCentro = codCentro;
		}

		public String getNomCentro() {
			return nomCentro;
		}

		public void setNomCentro(String nomCentro) {
			this.nomCentro = nomCentro;
		}
		
		
		

}
