package com.inia_mscc.modulos.adm.entidades;
import com.inia_mscc.modulos.comun.entidades.Objeto;

public class Region extends Objeto {
	private String _codigo;
	private String _nombre;
	public String get_codigo() {
		return _codigo;
	}
	public void set_codigo(String codigo) {
		_codigo = codigo;
	}
	public String get_nombre() {
		return _nombre;
	}
	public void set_nombre(String nombre) {
		_nombre = nombre;
	}
}