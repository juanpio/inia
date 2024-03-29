package com.inia_mscc.modulos.adm;

import java.io.IOException;
import java.util.List;

import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;
import com.inia_mscc.modulos.adm.entidades.Pais;
import com.inia_mscc.modulos.adm.entidades.Region;
import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.adm.entidades.Ubicacion;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;
import com.inia_mscc.modulos.adm.proveedores.ProveedorListaCriterio;
import com.inia_mscc.modulos.adm.proveedores.ProveedorRegion;
import com.inia_mscc.modulos.adm.proveedores.ProveedorRelacionPCD;
import com.inia_mscc.modulos.adm.proveedores.ProveedorTransaccion;
import com.inia_mscc.modulos.adm.proveedores.ProveedorUbicacion;
import com.inia_mscc.modulos.adm.proveedores.ProveedorValorSeleccion;
import com.inia_mscc.modulos.adm.servicios.ServicioListaCriterio;
import com.inia_mscc.modulos.adm.servicios.ServicioRegion;
import com.inia_mscc.modulos.adm.servicios.ServicioRelacionPCD;
import com.inia_mscc.modulos.adm.servicios.ServicioTransaccion;
import com.inia_mscc.modulos.adm.servicios.ServicioUbicacion;
import com.inia_mscc.modulos.adm.servicios.ServicioValorSeleccion;
import com.inia_mscc.modulos.comun.entidades.Enumerados;

public class ADMFachada {

	private ServicioRelacionPCD srvRelacionPCD;
	private ServicioTransaccion srvTransaccion;
	private ServicioRegion srvRegion;
	private ServicioValorSeleccion srvValorSeleccion;
	private ServicioListaCriterio srvListaCriterio;
	private ServicioUbicacion srvUbicacion;

	public ADMFachada(Enumerados.ServicioADM servicio) {
		try {
			switch (servicio) {
			case RelacionPCD:
				srvRelacionPCD = new ProveedorRelacionPCD();
				break;
			case Transaccion:
				srvTransaccion = new ProveedorTransaccion();
				break;
			case Region:
				srvRegion = new ProveedorRegion();
				break;
			case ValorSeleccion:
				srvValorSeleccion = new ProveedorValorSeleccion();
				break;
			case ListaCriterio:
				srvListaCriterio = new ProveedorListaCriterio();
				break;
			case Ubicacion:
				srvUbicacion = new ProveedorUbicacion();	
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Ciudad ObtenerCiudad(Ciudad pCiudad) {
		return srvRelacionPCD.ObtenerCiudad(pCiudad);
	}

	public List<Ciudad> ObtenerCiudades() {
		return srvRelacionPCD.ObtenerCiudades();
	}

	public Departamento ObtenerDepartamento(Departamento pDepartamento) {
		return srvRelacionPCD.ObtenerDepartamento(pDepartamento);
	}

	public List<Departamento> ObtenerDepartamentos() {
		return srvRelacionPCD.ObtenerDepartamentos();
	}

	public Pais ObtenerPais(Pais pPais) {
		return srvRelacionPCD.ObtenerPais(pPais);
	}

	public List<Pais> ObtenerPaises() {
		return srvRelacionPCD.ObtenerPaises();
	}

	public List<Departamento> ObtenerDepartamentosXPais(Pais pPais) {
		return srvRelacionPCD.ObtenerDepartamentosXPais(pPais);
	}

	public List<Ciudad> ObtenerCiudadesXDeptos(Departamento unDepto) {
		return srvRelacionPCD.ObtenerCiudadesXDeptos(unDepto);
	}

	public List<Transaccion> ObtenerTransacciones() {
		return srvTransaccion.ObtenerTransacciones();
	}

	public List<Transaccion> ObtenerTransaccionesActiva() {
		return srvTransaccion.ObtenerTransaccionesActiva();
	}

	public void ActualizarTransaccion(Transaccion pTransaccion) {
		srvTransaccion.ActualizarTransaccion(pTransaccion);
	}

	public Transaccion ComprobarTransaccion(Transaccion pTransaccion) {
		return srvTransaccion.ComprobarTransaccion(pTransaccion);
	}

	public void ActualizarRegion(Region pRegion) {
		srvRegion.ActualizarRegion(pRegion);
	}

	public Region RegistrarRegion(Region pRegion) {
		return srvRegion.RegistrarRegion(pRegion);
	}

	public List<Region> ObtenerRegiones() {
		return srvRegion.ObtenerRegiones();
	}

	public Region ComprobarRegion(Region pRegion) {
		return srvRegion.ComprobarRegion(pRegion);
	}

	public Region ComprobarRegionCodigo(Region pRegion) {
		return srvRegion.ComprobarRegionCodigo(pRegion);
	}

	public void EliminarRegion(Region pRegion) {
		srvRegion.EliminarRegion(pRegion);
	}

	public void ActualizarValor(ValorSeleccion pValor) {
		srvValorSeleccion.ActualizarValor(pValor);
	}

	public ValorSeleccion ObtenerValor(ValorSeleccion pValor) {
		return srvValorSeleccion.ObtenerValor(pValor);
	}

	public List<ValorSeleccion> ObtenerValores(ValorSeleccion pValores) {
		return srvValorSeleccion.ObtenerValores(pValores);
	}

	public void EliminarValor(ValorSeleccion pValor) {
		srvValorSeleccion.EliminarValor(pValor);
	}

	public void EliminarValores(List<ValorSeleccion> pValores) {
		srvValorSeleccion.EliminarValores(pValores);
	}
	
	public List<ListaCriterioSeleccion> ObtenerListaCriterio() {
		return srvListaCriterio.ObtenerListaCriterio();
	}
	
	public ListaCriterioSeleccion ObtenerCriterio(ListaCriterioSeleccion pCriterio){
		return srvListaCriterio.ObtenerCriterio(pCriterio);
	}
	
	public void ActualizarListaCriterio(ListaCriterioSeleccion pCriterio){
		srvListaCriterio.ActualizarListaCriterio(pCriterio);
	}

	public void ActualizarUbicacion(Ubicacion pUbicacion) {
		srvUbicacion.ActualizarUbicacion(pUbicacion);
	}

	public Ubicacion ObtenerUbicacion(Ubicacion pUbicacion) {
		return srvUbicacion.ObtenerUbicacion(pUbicacion);
	}

	public List<Ubicacion> ObtenerUbicacions(Ubicacion pUbicacion) {
		return srvUbicacion.ObtenerUbicacions(pUbicacion);
	}

	public Ubicacion RegistrarUbicacion(Ubicacion pUbicacion) {
		return srvUbicacion.RegistrarUbicacion(pUbicacion);
	}
	
}
