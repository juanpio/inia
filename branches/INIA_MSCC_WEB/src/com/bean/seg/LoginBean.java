package com.bean.seg;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoUsuario;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioSEG;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class LoginBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	private String loginName;
	private String password;
	private String error;
	private static long intentos = 0;

	public boolean isInit() {
		return false;
	}

	public String olvidoContrasenia() {
		this.setLogged(true);
		this.setUsuario(null);
		this.limpiarSesion();
		error = "";
		return "login-olvido";
	}

	public String login() {
		String mensaje = "login-error";
		Usuario u = null;
		try {		
			u = this.getSegFachada(ServicioSEG.Usuario)
					.Login(loginName, password);
			if (u != null) {
				if (u.is_activado()) {
					if (!u.get_estadoUsuario().equals(EstadoUsuario.Inactivo)) {
						if (u.get_estadoUsuario().equals(EstadoUsuario.Activo)) {
							this.setLogged(true);
							this.setUsuario(u);
							Date fecha = u.get_ultimoAcceso();
							super.setFechaEjecucionFormt(formatoFecha.format(fecha));
							u.set_ultimoAcceso(new Date());
							this.getSegFachada(ServicioSEG.Usuario)
									.ActualizarUltimoAccesoUsuario(u);
							u.set_ultimoAcceso(fecha);
							this.setSesion(Usuario.class.toString(), u);
							this.setError("");
							mensaje = "login-ok";
						} else {
							this
									.setError(u.get_datos().get_nombre()
											+ " su cuenta est� "
											+ u.get_estadoUsuario().toString()
													.toLowerCase()
											+ " a�n, recuerde chequear su correo, se le a enviado un e-mail para concluir con el registro.");
							mensaje = "";// "login-error";
						}
					} else {
						this
								.setError(u.get_datos().get_nombre()
										+ " su cuenta a sido Inactivada.");
						mensaje = "";// "login-error";
					}
				} else {
					this
							.setError(u.get_datos().get_nombre()
									+ " su cuenta no est� activa a�n, recuerde chequear su correo, se le a enviado un e-mail para concluir con el registro.");
					mensaje = "";// "login-error";
				}
			} else {
				this
						.addGlobalMessage("El nombre de usuario o password no conciden.");
				intentos++;
				if (intentos == 5) {
					this
							.setError("Este es el quinto intento en logearse, por favor verifique sus datos e intente nuevamente.");
					intentos = 0;
				}
				mensaje = "";// "login-error";
			}
		} catch (Exception ex) {
			this.addGlobalMessage(ex.getMessage());
		}
		return mensaje;
	}

	public String logout() {
		try {
			this.setLogged(false);
			this.setUsuario(null);
			this.setOpcion("/Servicios/SEG/SEG001.jsp");
			this.removerSesion(Usuario.class.toString());
			error = "";
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		} finally {
			this.limpiarSesion();
		}
		return "logout";
	}

	public String olvidoPassword() {
		return "olvidoPassword";
	}

	public String registrarse() {
		return "registrarse";
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getError() {
		return error;
	}

}
