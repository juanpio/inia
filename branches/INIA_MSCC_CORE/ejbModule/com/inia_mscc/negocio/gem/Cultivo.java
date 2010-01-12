package com.inia_mscc.negocio.gem;

import java.util.List;

import com.inia_mscc.negocio.comun.Objeto;

public class Cultivo extends Objeto{
	
	private String _descripcion;
	private List<Propiedad> _listaPropiedades;
	
	public Cultivo() {
		super();
		_descripcion = null;
		_listaPropiedades = null;
	}
	
	public String get_descripcion() {
		return _descripcion;
	}
	public void set_descripcion(String descripcion) {
		_descripcion = descripcion;
	}
	public List<Propiedad> get_listaPropiedades() {
		return _listaPropiedades;
	}
	public void set_listaPropiedades(List<Propiedad> listaPropiedades) {
		_listaPropiedades = listaPropiedades;
	}
	
}
