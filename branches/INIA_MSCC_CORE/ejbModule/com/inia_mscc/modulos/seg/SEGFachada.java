package com.inia_mscc.modulos.seg;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Perfil;
import com.inia_mscc.modulos.seg.entidades.Usuario;
import com.inia_mscc.modulos.seg.proveedores.ProveedorPerfil;
import com.inia_mscc.modulos.seg.proveedores.ProveedorUsuario;
import com.inia_mscc.modulos.seg.servicios.ServicioPerfil;
import com.inia_mscc.modulos.seg.servicios.ServicioUsuario;

public class SEGFachada {
	private ServicioUsuario srvUsuario;
	private ServicioPerfil srvPerfil;
	
	public SEGFachada(Enumerados.ServicioSEG servicio) {
		try {
			switch (servicio) {
			case Usuario:
				srvUsuario = new ProveedorUsuario();
				break;
			case Perfil:
				srvPerfil = new ProveedorPerfil();	
				break;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void ActualizarUltimoAccesoUsuario(Usuario pUsuario){
		srvUsuario.ActualizarUltimoAccesoUsuario(pUsuario);
	}
	
	public Usuario ObtenerUsuarioXDatos(String pLoginName, Date pUltimoAcceso,
			Perfil pPerfil, String pEmail, String pFrase) {
		return srvUsuario.ObtenerUsuarioXDatos(pLoginName, pUltimoAcceso, pPerfil, pEmail, pFrase);
	}
	
	public void ActualizarDatosUsuario(DatoUsuario pDatosUsuario) {
		srvUsuario.ActualizarDatosUsuario(pDatosUsuario);
	}

	public void CambiarPassword(Usuario pUsuario) {
		srvUsuario.CambiarPassword(pUsuario);
	}

	public Usuario ComprobarClaveReigstro(String pClave) {
		return srvUsuario.ComprobarClaveReigstro(pClave);
	}

	public Usuario RegistrarUsuario(Usuario pUsuario) throws Exception {
		return srvUsuario.RegistrarUsuario(pUsuario);
	}

	public Usuario Login(String pLogin, String pPassword) {
		return srvUsuario.Login(pLogin, pPassword);
	}
	
	public String EliminarPerfil(Perfil pPerfil) {
		return srvPerfil.EliminarPerfil(pPerfil);
	}

	public void ActualizarPerfil(Perfil pPerfil) {
		srvPerfil.ActualizarPerfil(pPerfil);
	}
	
	public Perfil RegistrarPerfil(Perfil pPerfil) {
		return srvPerfil.RegistrarPerfil(pPerfil);
	}
	
	public List<Perfil> ObtenerPerfiles() {
		return srvPerfil.ObtenerPerfiles();
	}
	
	public boolean ComprobarEmail(String pEmail) {
		return srvUsuario.ComprobarEmail(pEmail);
	}
	
	public Perfil ComprobarPerfil(Perfil pPerfil){
		return srvPerfil.ComprobarPerfil(pPerfil);
	}
	
	public void DarBajaBloquearUsuario(Usuario pUsuario) {
		srvUsuario.DarBajaBloquearUsuario(pUsuario);
	}
	
	public Perfil ObtenerPerfilConTransAsociadas(Perfil pPerfil) {
		return srvPerfil.ObtenerPerfilConTransAsociadas(pPerfil);
	}
	
	public void ActualizarUsuario(Usuario pUsuario) {
		srvUsuario.ActualizarUsuario(pUsuario);
	}
	
	public List<Usuario> ObtenerUsuarios() {
		return srvUsuario.ObtenerUsuarios();
	}
	
}
