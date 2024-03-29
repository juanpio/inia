package com.inia_mscc.modulos.seg.dao;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;
import org.hibernate.criterion.Restrictions;

import com.inia_mscc.config.hibernate.HibernateUtil;
import com.inia_mscc.config.util.LoggingUtilities;
import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Perfil;

/**
 * @author User
 * 
 */
public class DAOPerfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOPerfil.class);

	
	public boolean ComprobarPerfilEnUso(Perfil pPeril) {
		boolean retorno = false;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Criteria c = session.createCriteria(DatoUsuario.class);
			c.add(Restrictions.eq("_perfil", pPeril));
			if((DatoUsuario) c.uniqueResult()!=null){
				retorno = true;
			}
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}
	
	public Perfil ObtenerPerfilConTransAcosiadas(Perfil pPerfil) {
		Perfil retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Perfil.class);
			if (pPerfil.get_id() != 0) {
				c.add(Restrictions.eq("_id", pPerfil.get_id()));
			}
			retorno = (Perfil) c.uniqueResult();
			if (retorno != null) {
				retorno.get_transaccionesSistema()
						.get(0);
			}
		} catch (Exception e) { // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}

	public Perfil ComprobarPerfil(Perfil pPerfil) {
		Perfil retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Perfil.class);
			c.add(Restrictions.ilike("_nombre", pPerfil.get_nombre()));
			retorno = (Perfil) c.uniqueResult();
		} catch (Exception e) { // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}

	public List<Perfil> ObtenerPerfiles() {
		List<Perfil> retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Perfil.class);
			retorno = (List<Perfil>) c.list();
		} catch (Exception e) { // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}

	public Perfil RegistrarPerfil(Perfil pPerfil) {
		Perfil perfil = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Long id = (Long) session.save("Perfil", pPerfil);
			Criteria c = session.createCriteria(Perfil.class);
			c.add(Restrictions.eq("_id", id));
			perfil = (Perfil) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return perfil;
	}

	public void ActualizarPerfil(Perfil pPerfil) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.saveOrUpdate("Perfil", pPerfil);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

	public void EliminarPerfil(Perfil pPerfil) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.delete("Perfil", pPerfil);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

}