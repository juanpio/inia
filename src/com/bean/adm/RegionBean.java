package com.bean.adm;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Region;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;

public class RegionBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descripcion;
	private String nombre;
	private Region region = new Region();
	private List<Region> regiones;

	public boolean isInit() {
		this.LimpiarBean();
		this.setRegiones(this.getAdmFachada(Servicio.Region).ObtenerRegiones());
		return false;
	}
	
	private void LimpiarBean() {
		nombre = "";
		descripcion = "";
		codigo = "";
	}

	public String actualizar() throws Exception {
		String retorno = "registro-error";
		try {
			region.set_codigo(codigo);
			region.set_descripcion(descripcion);
			region.set_nombre(nombre);
			this.getAdmFachada(Servicio.Region).ActualizarRegion(region);
			retorno = "registro-ok";
		} catch (Exception ex) {
			this
					.setError("Se ha producido un error, por favor intente nuevamente.");
		}
		return retorno;
	}

	public String verRegiones() {
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String regionElegida = (String) paramMap.get("regionElegida");
		Iterator<Region> it = regiones.iterator();
		while (it.hasNext()) {
			Region regionSeleccionada = (Region) it.next();
			if (regionSeleccionada.get_id() == (long) Long
					.parseLong(regionElegida)) {
				region = regionSeleccionada;

				codigo = region.get_codigo();
				descripcion = region.get_descripcion();
				nombre = region.get_nombre();
			}
		}
		return "resultados";
	}

	public String registrar() throws Exception {
		String retorno = "";
		try {
			Region datosRegion = new Region();
			datosRegion.set_codigo(codigo);
			datosRegion.set_descripcion(descripcion);
			datosRegion.set_nombre(nombre);

			Region reg = this.getAdmFachada(Servicio.Region).ComprobarRegion(
					datosRegion);
			if (reg == null) {
				setError("");
				Region r = this.getAdmFachada(Servicio.Region).RegistrarRegion(
						datosRegion);
				if (r != null) {
					this.setError("");
					MaestroBean.getInstance().setOpcion(
							"/Servicios/ADM/ADM005.jsp");
					retorno = "registro-ok";
					LimpiarBean();
				} else {
					this
							.setError("No ha sido posible crear la Regi�n, revise los datos ingresados y intentelo nuevamente.");
					MaestroBean.getInstance().setOpcion(
							"/Servicios/ADM/ADM006.jsp");
					retorno = "registro-error";
				}
			} else {
				this
						.setError("Ya existe una Regi�n con igual nombre, Por favor ingrese otro nombre.");
				MaestroBean.getInstance()
						.setOpcion("/Servicios/ADM/ADM006.jsp");
				retorno = "registro-error";
			}
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
		return retorno;
	}
	
	
	public String eliminar() {
		String retorno = "";
		try {
			Map paramMap = FacesContext.getCurrentInstance()
					.getExternalContext().getRequestParameterMap();
			String regionElegida = (String) paramMap.get("regionEliminar");
			Iterator<Region> it = regiones.iterator();
			while (it.hasNext()) {
				Region object = (Region) it.next();
				if ((Long) object.get_id() == Long.parseLong(regionElegida)) {
					region = object;
					nombre = region.get_nombre();
					descripcion = region.get_descripcion();
					codigo = region.get_codigo();
				}
			}
			this.getAdmFachada(Servicio.Region).EliminarRegion(region);
				retorno = "eliminado";
			
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegiones(List<Region> regiones) {
		this.regiones = regiones;
	}

	public List<Region> getRegiones() {
		return regiones;
	}

}