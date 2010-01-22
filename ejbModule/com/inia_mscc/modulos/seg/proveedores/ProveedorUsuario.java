package com.inia_mscc.modulos.seg.proveedores;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.seg.servicios.ServicioUsuario;
import com.inia_mscc.excepciones.ProviderException;

public class ProveedorUsuario implements ServicioUsuario {

	private ServicioUsuario ejbUsuario;

	public ProveedorUsuario() throws IOException {
		try {
			Context ctx = new InitialContext();
			ejbUsuario = (ServicioUsuario) ctx.lookup("EJBUsuario");
		} catch (NamingException e) {
			throw new ProviderException(e);
		}
	}
	
	@Override
	public Usuario Login(String pLogin, String pPassword) {
		return ejbUsuario.Login(pLogin, pPassword);
	}

	@Override
	public DatoUsuario RegistrarUsuario(DatoUsuario pUsuario) {
		return ejbUsuario.RegistrarUsuario(pUsuario);
	}
	
}