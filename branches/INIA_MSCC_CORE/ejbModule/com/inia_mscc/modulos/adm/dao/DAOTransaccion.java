package com.inia_mscc.modulos.adm.dao;

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
import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;

public class DAOTransaccion implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DAOTransaccion.class);

	public List<Transaccion> ObtenerTransacciones() {
		List<Transaccion> retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Transaccion.class);
			retorno = (List<Transaccion>) c.list();
		} catch (Exception e) { // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}

	public List<Transaccion> ObtenerTransaccionesActivas() {
		List<Transaccion> retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Transaccion.class);
			c.add(Restrictions.eq("_estado", Estado.Activo));
			retorno = (List<Transaccion>) c.list();
		} catch (Exception e) { // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}

	public void ActualizarTransaccion(Transaccion pTransaccion) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.update("Transaccion", pTransaccion);
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

	public Transaccion ComprobarTransaccion(Transaccion pTransaccion) {
		Transaccion retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Transaccion.class);
			c.add(Restrictions.ilike("_descripcion", pTransaccion
					.get_descripcion()));
			retorno = (Transaccion) c.uniqueResult();
		} catch (Exception e) { // catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}
}
