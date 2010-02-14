package com.inia_mscc.modulos.comun.entidades;

public class Enumerados {


	static public enum TransaccionesNoContenidas {
		SEG001, SEG002, SEG003,SEG006
	}
	

	static public enum Proceso {
		ADM, SEG, EJE, GEM, HPE
	}
	

	static public enum NombreProceso {
		Administraci�n, Seguridad, Ejecuci�n, Escenarios, Historial
	}
	

	static public enum EstadoUsuario {
		Ninguno, Activo, Inactivo, Registrado, Bloqueado
	}

	static public enum Servicio{
		Usuario, Perfil, RelacionPCD, Transaccion, MailSender, Ejecucion, Region
	}
	

	static public enum Estado {
		Activo, Inactivo
	}

	/**
	 * Establece los posibles estados de un usuario
	 */
	static public enum EstadoSolicitud {
		Registrada, Pendiente, Autorizada, Rechazada
	}


	static public enum TipoPropiedadCultivo {
		Ninguno, Fenotipica, Genotipica, Ambiente
	}
	

	static public enum EstadoArchivo {
		Ninguno ,Subiendo, Cargado
	}


	static public enum TipoArchivo {
		ModeloSimulacion, Climatologico , Escenario, ParametrosClimaticos, Resultados,
	}
	

	static public enum NobresDeArchivos {
		mscc_, weather_data , scenario, climate_parameters_,
	}


	static public enum TipoExtencionArchivo {
		xml, txt, py
	}
	

	static public enum EstadoModificacion {
		Ninguno, Modificado, Insertado, Eliminado
	}
	

	static public enum TipoSolicitud {
		Ninguno, Perfil, Transaccion, Habilitacion
	}
	
	
}