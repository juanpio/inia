package com.bean.adm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioSEG;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TipoArchivo;
import com.inia_mscc.modulos.gem.entidades.Ubicacion;

public class UbicacionBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tipoArchvo;
	private SelectItem[] tipos;
	private String pathDirectorio;
	private List<Ubicacion> ubicaciones;
	private String ubicacionEjegida;
	private Ubicacion ubicacion = new Ubicacion();

	public String actualizar() {
		return "ADM010";
	}

	public String verUbicacion() {
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String ubicacionEjegida = (String) paramMap.get("ubicacionEjegida");
		Iterator<Ubicacion> it = ubicaciones.iterator();
		while (it.hasNext()) {
			Ubicacion ubicacionSeleccionada = (Ubicacion) it.next();
			if (ubicacionSeleccionada.get_id() == (long) Long
					.parseLong(ubicacionEjegida)) {
				this.setUbicacion(ubicacionSeleccionada);
			}
		}
		return "resultados";
	}

	public boolean isInit() {
		tipos = new SelectItem[TipoArchivo.values().length];
		SelectItem si = new SelectItem(TipoArchivo.Climatologico.name());
		tipos[0] = si;
		si = new SelectItem(TipoArchivo.Eejcucion.name(), "Ejecuci�n");
		tipos[1] = si;
		si = new SelectItem(TipoArchivo.Escenario.name());
		tipos[2] = si;
		si = new SelectItem(TipoArchivo.ModeloSimulacion.name(),
				"Modelo de simulaci�n");
		tipos[3] = si;
		si = new SelectItem(TipoArchivo.ParametrosClimaticos.name(),
				"Par�metros clim�ticos");
		tipos[4] = si;
		si = new SelectItem(TipoArchivo.Resultados.name());
		tipos[5] = si;
		tipoArchvo = tipos[0].getValue().toString();
		// this.getUbicacion().set_tipoArchivo(TipoArchivo.Eejcucion);
		return false;
	}

	public UbicacionBean() {
		try {
//			this.setUbicaciones(this.getSegFachada(ServicioADM).ObtenerUsuarios();
			this.setUbicaciones(new ArrayList<Ubicacion>());
			tipos = new SelectItem[TipoArchivo.values().length];
			SelectItem si = new SelectItem(TipoArchivo.Climatologico.name());
			tipos[0] = si;
			si = new SelectItem(TipoArchivo.Eejcucion.name(), "Ejecuci�n");
			tipos[1] = si;
			si = new SelectItem(TipoArchivo.Escenario.name());
			tipos[2] = si;
			si = new SelectItem(TipoArchivo.ModeloSimulacion.name(),
					"Modelo de simulaci�n");
			tipos[3] = si;
			si = new SelectItem(TipoArchivo.ParametrosClimaticos.name(),
					"Par�metros clim�ticos");
			tipos[4] = si;
			si = new SelectItem(TipoArchivo.Resultados.name());
			tipos[5] = si;
			tipoArchvo = tipos[0].getValue().toString();
			// this.getUbicacion().set_tipoArchivo(TipoArchivo.Eejcucion);
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public String getTipoArchvo() {
		return tipoArchvo;
	}

	public void setTipoArchvo(String tipoArchvo) {
		this.tipoArchvo = tipoArchvo;
	}

	public SelectItem[] getTipos() {
		return tipos;
	}

	public void setTipos(SelectItem[] tipos) {
		this.tipos = tipos;
	}

	public String getPathDirectorio() {
		return pathDirectorio;
	}

	public void setPathDirectorio(String pathDirectorio) {
		this.pathDirectorio = pathDirectorio;
	}

	public void setUbicaciones(List<Ubicacion> ubicaciones) {
		this.ubicaciones = ubicaciones;
	}

	public List<Ubicacion> getUbicaciones() {
		return ubicaciones;
	}

	public String getUbicacionEjegida() {
		return ubicacionEjegida;
	}

	public void setUbicacionEjegida(String ubicacionEjegida) {
		this.ubicacionEjegida = ubicacionEjegida;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

}