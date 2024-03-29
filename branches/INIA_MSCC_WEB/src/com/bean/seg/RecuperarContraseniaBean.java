package com.bean.seg;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.NamingException;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.comun.entidades.EncriptacionSHA1BASE64;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioComun;
import com.inia_mscc.modulos.comun.entidades.Enumerados.ServicioSEG;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class RecuperarContraseniaBean extends MaestroBean implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String loginName;
	private String email;
	private String contrasenia;
	private String confirmacion;
	private String frase;
	private String actual;

	public RecuperarContraseniaBean() {
		this.setUsuario((Usuario) this.getSesion(Usuario.class.toString()));
	}

	public boolean isInit() {
		return false;
	}

	public void validarEmail() {
		try {
			if (this.getSegFachada(ServicioSEG.Usuario).ComprobarEmail(email)) {
				this
						.setError("El e-mail ingresado no está registrado en el sistema.");
				this.setUsuario(null);
			} else {
				this.setError("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void validarFrase() {
		try {
			this.setUsuario(this.getSegFachada(ServicioSEG.Usuario)
					.ObtenerUsuarioXDatos(null, null, null, email, frase));
			if (this.getUsuario() != null) {
				this.setError("");
				this.setExito("Bienvenido "
						+ this.getUsuario().get_datos().get_nombre()
						+ " su login es " + this.getUsuario().get_login());
			} else {
				this
						.setError("No existe Usuario registrado con este e-mail y Frase Secreta.");
				this.setExito("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public String enviarPassword() {
		String retorno = "";
		try {
			if (this.getUsuario() != null) {
				this.getUsuario().set_password("NoTeOlvides");
				this.getSegFachada(ServicioSEG.Usuario).CambiarPassword(this.getUsuario());
				if (!this.enviarMailConfirmacion(this.getUsuario())) {
					this.setError("No ha sido posible enviar el e-mail, "
							+ "el e-mail proporcionado no está disponible.");
					retorno = "registro-error";
				}
			}
			this.setError("");
			this.setExito("Se a enviado un e-mail a su casilla "
					+ "de correo, con la nueva Clave.");
			retorno = "EnvioPassword";
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	public void validarContrasenia() {
		try {
			if (!this.getUsuario().get_password().equals(EncriptacionSHA1BASE64.encriptar(getActual()))) {
				this.setError("La contraseña actual que "
						+ "ingreso no es correcta.");
				this.setActual("");
			} else {
				this.setError("");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void confirmacionIngreso() {
		try {
			if (this.getUsuario() != null) {
				if (EncriptacionSHA1BASE64.encriptar(this.getActual()).equals(this.getUsuario().get_password())) {
					if (this.getContrasenia().equals(getConfirmacion())) {
						if (!this.getUsuario().get_login().equalsIgnoreCase(
								getConfirmacion())
								|| !this.getUsuario().get_login()
										.equalsIgnoreCase(getContrasenia())) {
							if (this.getUsuario().get_datos().get_mail()
									.equalsIgnoreCase(getConfirmacion())
									|| this.getUsuario().get_datos().get_mail()
											.equalsIgnoreCase(getContrasenia())) {
								this
										.setError("La nueva contraseña no puede ser "
												+ "igual a su correo electrónico.");
							}
						} else {
							this.setError("La nueva contraseña no puede ser "
									+ "igual al nombre del usuario logueado.");
						}
					} else {
						this.setError("Ingrese la nueva contraseña y la confirmación o verifique que las mismas sean iguales.");
					}
				} else {
					this.setError("La contraseña actual ingresada "
							+ "no coincide.");
				}
			} else {
				this.setError("");
				this.setExito("Se a cambiado su contraseña correctamente.");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public String confirmar() {
		String retorno = "";
		try {
			if (this.getUsuario() != null) {
				if (EncriptacionSHA1BASE64.encriptar(this.getActual()).equals(this.getUsuario().get_password())) {
					if (this.getContrasenia().equals(getConfirmacion())) {
						if (!this.getUsuario().get_login().equalsIgnoreCase(
								getConfirmacion())
								|| !this.getUsuario().get_login()
										.equalsIgnoreCase(getContrasenia())) {
							if (!this.getUsuario().get_datos().get_mail()
									.equalsIgnoreCase(getConfirmacion())
									|| !this.getUsuario().get_datos()
											.get_mail().equalsIgnoreCase(
													getContrasenia())) {
								this.getUsuario().set_password(getConfirmacion());
								this.getSegFachada(ServicioSEG.Usuario)
										.CambiarPassword(this.getUsuario());
								retorno = "SEG001";
								this.setUsuario(null);
								this.setSesion(Usuario.class.toString(), null);
							} else {
								this
										.setError("La nueva contraseña no puede ser "
												+ "igual a su correo electrónico.");
								this.setContrasenia("");
								this.setConfirmacion("");
							}
						} else {
							this.setError("La nueva contraseña no puede ser "
									+ "igual al nombre del usuario logueado.");
							this.setContrasenia("");
							this.setConfirmacion("");
						}
					} else {
						this.setError("Ingrese la nueva contraseña y la confirmación.");
						this.setContrasenia("");
						this.setConfirmacion("");
					}
				} else {
					this.setError("La contraseña actual ingresada "
							+ "no coincide.");
					this.setActual("");
				}
			} else {
				this.setError("");
				this.setExito("Se a cambiado su contraseña correctamente.");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}


	public Boolean salvarNombre(Usuario pUsuario) throws IOException,
			NamingException, MessagingException {
		try {
			Properties props = new Properties();

			// Nombre del host de correo, es smtp.gmail.com
			// props.setProperty("mail.smtp.host", "smtp.live.com");// hotmail
			props.setProperty("mail.smtp.host", "smtp.gmail.com");// gmail

			// TLS si está disponible
			props.setProperty("mail.smtp.starttls.enable", "true");

			// Puerto de gmail para envio de correos
			props.setProperty("mail.smtp.port", "587");

			// Nombre del usuario
			props.setProperty("mail.smtp.user", "INIA - MSCC Administración");

			// Si requiere o no usuario y password para conectarse.
			props.setProperty("mail.smtp.auth", "true");

			Session session = Session.getDefaultInstance(props);
			session.setDebug(true);

			MimeMessage message = new MimeMessage(session);
			// Quien envia el correo
			message.setFrom(new InternetAddress("juan400SVN@gmail.com"));

			// A quien va dirigido
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					pUsuario.get_datos().get_mail()));

			message.setSubject("Clave de usuario en el sistema INIA - MSCC");

			message
					.setText(
							"<br></br><br></br><br><center><i><b>"
									+ pUsuario.get_datos().get_nombre()
									+ " "
									+ pUsuario.get_datos().get_apellido()
									+ " su usuario es "
									+ pUsuario.get_login()
									+ " en INIA - MSCC,</br>"
									+ "<br>para acceder al sistema su contraseña es: </br><br>"
									+ pUsuario.get_password()
									+ "</br><br>Recuerde memorizar su contraseña y eliminar este correo.</b></i></center><br></br><br></br><br></br>"
									+ "<br>Muchas gracias por registrarse!</b></i></center><br></br><br></br><br></br>",
							"ISO-8859-1", "html");

			Transport t = session.getTransport("smtp");
			t.connect("juan400SVN@gmail.com", "andres4003341");
			t.sendMessage(message, message.getAllRecipients());
			t.close();

		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return true;
	}

	// TODO Utilizar este envio de corro si encontramos como no de problemas con
	// los puetos 25 y 587
	public boolean enviarMailConfirmacion(Usuario pUsuario) {
		try {
			String body = "<br></br><br></br><br><center><i><b>"
					+ pUsuario.get_datos().get_nombre()
					+ " "
					+ pUsuario.get_datos().get_apellido()
					+ " su usuario es "
					+ pUsuario.get_login()
					+ " en INIA - MSCC,</br>"
					+ "<br>para acceder al sistema su contraseña es: NoTeOlvidesMas</b></i></br><br>"
					+ "<br><i><b>Muchas gracias por registrarse!</b></i></center><br></br><br></br><br></br>";
			this.getComunFachada(ServicioComun.MailSender).enviarMailTextoPlano(
					pUsuario.get_datos().get_mail(),
					"INIA - MSCC Administración", body);
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(String confirmacion) {
		this.confirmacion = confirmacion;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
	}
}
