package com.inia_mscc.modulos.gem;

import java.io.IOException;
import java.util.List;

import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.Cultivo;
import com.inia_mscc.modulos.gem.entidades.Escenario;
import com.inia_mscc.modulos.gem.entidades.Modelo;
import com.inia_mscc.modulos.gem.entidades.Propiedad;
import com.inia_mscc.modulos.gem.proveedores.ProveedorArchivo;
import com.inia_mscc.modulos.gem.proveedores.ProveedorCultivo;
import com.inia_mscc.modulos.gem.proveedores.ProveedorEscenario;
import com.inia_mscc.modulos.gem.proveedores.ProveedorModelo;
import com.inia_mscc.modulos.gem.proveedores.ProveedorPropiedad;
import com.inia_mscc.modulos.gem.servicios.ServicioArchivo;
import com.inia_mscc.modulos.gem.servicios.ServicioCultivo;
import com.inia_mscc.modulos.gem.servicios.ServicioEscenario;
import com.inia_mscc.modulos.gem.servicios.ServicioModelo;
import com.inia_mscc.modulos.gem.servicios.ServicioPropiedad;

public class GEMFachada {
	private ServicioCultivo srvCultivo;
	private ServicioPropiedad srvPropiedad;
	private ServicioArchivo srvArchivo;
	private ServicioEscenario srvEscenario;
	private ServicioModelo srvModelo;

	public GEMFachada(Enumerados.ServicioGEM servicio) {
		try {
			switch (servicio) {
			case Cultivo:
				srvCultivo = new ProveedorCultivo();
				break;
			case Propiedad:
				srvPropiedad = new ProveedorPropiedad();
				break;
			case Archivo:
				srvArchivo = new ProveedorArchivo();
				break;
			case Escenario:
				srvEscenario = new ProveedorEscenario();
				break;
			case Modelo:
				srvModelo = new ProveedorModelo();
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ActualizarCultivo(Cultivo pCultivo) {
		srvCultivo.ActualizarCultivo(pCultivo);

	}

	public Cultivo ObtenerCultivo(Cultivo pCultivo) {
		return srvCultivo.ObtenerCultivo(pCultivo);
	}

	public List<Cultivo> ObtenerCultivos(Cultivo pCultivo) {
		return srvCultivo.ObtenerCultivos(pCultivo);
	}

	public Cultivo RegistrarCultivo(Cultivo pCultivo) {
		return srvCultivo.RegistrarCultivo(pCultivo);
	}

	public Cultivo ComprobarCultivo(Cultivo pCultivo) {
		return srvCultivo.ComprobarCultivo(pCultivo);
	}

	public void ActualizarPropiedad(Propiedad pPropiedad) {
		srvPropiedad.ActualizarPropiedad(pPropiedad);
	}

	public Propiedad ObtenerPropiedad(Propiedad pPropiedad) {
		return srvPropiedad.ObtenerPropiedad(pPropiedad);
	}

	public List<Propiedad> ObtenerPropiedades(Propiedad pPropiedad) {
		return srvPropiedad.ObtenerPropiedades(pPropiedad);
	}

	public void EliminarPropiedad(Propiedad pPropiedad) {
		srvPropiedad.EliminarPropiedad(pPropiedad);
	}

	public void EliminarPropiedades(List<Propiedad> pPropiedades) {
		srvPropiedad.EliminarPropiedades(pPropiedades);
	}

	public void ActualizarArchivo(Archivo pArchivo) {
		srvArchivo.ActualizarArchivo(pArchivo);
	}

	public Archivo ObtenerArchivo(Archivo pArchivo) {
		return srvArchivo.ObtenerArchivo(pArchivo);
	}

	public List<Archivo> ObtenerArchivos(Archivo pArchivo) {
		return srvArchivo.ObtenerArchivos(pArchivo);
	}

	public Archivo RegistrarArchivo(Archivo pArchivo) throws Exception {
		return srvArchivo.RegistrarArchivo(pArchivo);
	}

	public void ActualizarEscenario(Escenario pEscenario) throws Exception {
		srvEscenario.ActualizarEscenario(pEscenario);
	}

	public Escenario ObtenerEscenario(Escenario pEscenario) {
		return srvEscenario.ObtenerEscenario(pEscenario);
	}

	public List<Escenario> ObtenerEscenarios(Escenario pEscenario) {
		return srvEscenario.ObtenerEscenarios(pEscenario);
	}

	public Escenario RegistrarEscenario(Escenario pEscenario) throws Exception {
		return srvEscenario.RegistrarEscenario(pEscenario);
	}

	public void ActualizarModelo(Modelo pModelo) throws Exception {
		srvModelo.ActualizarModelo(pModelo);
	}

	public Modelo ObtenerModelo(Modelo pModelo) {
		return srvModelo.ObtenerModelo(pModelo);
	}

	public List<Modelo> ObtenerModelos(Modelo pModelo) {
		return srvModelo.ObtenerModelos(pModelo);
	}

	public Modelo RegistrarModelo(Modelo pModelo) throws Exception {
		return srvModelo.RegistrarModelo(pModelo);
	}
}