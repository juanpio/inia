package com.inia_mscc.modulos.gem.entidades;

import java.util.Date;

import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.comun.entidades.Objeto;

public class Archivo extends Objeto {

	private String _nombre;
	private Enumerados.TipoArchivo _tipo;
	private Date _fechaHora;
	private Enumerados.EstadoArchivo _estadoArchivo;
	private Enumerados.TipoExtencionArchivo _extencion;
	private Ubicacion _ubicacion;
	
	public Archivo() {
		super();
		_nombre = null;
		_tipo = Enumerados.TipoArchivo.Ninguno;
		_fechaHora = new Date();
		_estadoArchivo = Enumerados.EstadoArchivo.Ninguno;
		_extencion = Enumerados.TipoExtencionArchivo.txt;
		
	}
	public String get_nombre() {
		return _nombre;
	}
	public void set_nombre(String nombre) {
		_nombre = nombre;
	}
	public Enumerados.TipoArchivo get_tipo() {
		return _tipo;
	}
	public void set_tipo(Enumerados.TipoArchivo tipo) {
		_tipo = tipo;
	}
	public Date get_fechaHora() {
		return _fechaHora;
	}
	public void set_fechaHora(Date fechaHora) {
		_fechaHora = fechaHora;
	}
	public Enumerados.EstadoArchivo get_estadoArchivo() {
		return _estadoArchivo;
	}
	public void set_estadoArchivo(Enumerados.EstadoArchivo estadoArchivo) {
		_estadoArchivo = estadoArchivo;
	}
	public Enumerados.TipoExtencionArchivo get_extencion() {
		return _extencion;
	}
	public void set_extencion(Enumerados.TipoExtencionArchivo extencion) {
		_extencion = extencion;
	}
	public Ubicacion get_ubicacion() {
		return _ubicacion;
	}
	public void set_ubicacion(Ubicacion ubicacion) {
		_ubicacion = ubicacion;
	}
	
}