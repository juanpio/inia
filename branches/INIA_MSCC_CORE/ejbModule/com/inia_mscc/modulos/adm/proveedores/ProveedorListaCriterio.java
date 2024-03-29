package com.inia_mscc.modulos.adm.proveedores;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.excepciones.ProviderException;
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;
import com.inia_mscc.modulos.adm.servicios.ServicioListaCriterio;

public class ProveedorListaCriterio implements ServicioListaCriterio {

	private ServicioListaCriterio ejbListaCriterio;

	public ProveedorListaCriterio() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbListaCriterio = (ServicioListaCriterio) ctx
					.lookup("EJBListaCriterio");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}

	@Override
	public ListaCriterioSeleccion ObtenerCriterio(
			ListaCriterioSeleccion pCriterio) {
		return ejbListaCriterio.ObtenerCriterio(pCriterio);
	}

	@Override
	public List<ListaCriterioSeleccion> ObtenerListaCriterio() {
		return ejbListaCriterio.ObtenerListaCriterio();
	}

	@Override
	public void ActualizarListaCriterio(ListaCriterioSeleccion pCriterio) {
		ejbListaCriterio.ActualizarListaCriterio(pCriterio);
	}
}
