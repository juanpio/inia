package com.inia_mscc.modulos.gem.dao;

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
import com.inia_mscc.modulos.gem.entidades.Cultivo;

public class DAOCultivo implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(DAOCultivo.class);

	@SuppressWarnings("unchecked")
	public List<Cultivo> ObtenerCultivos(Cultivo pCultivo) {
		List<Cultivo> listaCultivo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Cultivo.class);
			listaCultivo = (List<Cultivo>) c.list();
		} catch (Exception e) {// catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return listaCultivo;
	}

	public Cultivo ObtenerCultivo(Cultivo pCultivo) {
		Cultivo unCultivo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Cultivo.class);
			if (pCultivo.get_id() != 0) {
				c.add(Restrictions.eq("_id", pCultivo.get_id()));
				System.out.println(pCultivo.get_id());
			}
			if (!pCultivo.get_nombre().isEmpty()) {
				c.add(Restrictions.eq("_nombre", pCultivo.get_nombre()));
				System.out.println(pCultivo.get_nombre());
			}
			unCultivo = (Cultivo) c.uniqueResult();
			if (unCultivo != null
					&& unCultivo.get_listaPropiedades().size() != 0) {
				unCultivo.get_listaPropiedades().get(0);
			}
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return unCultivo;
	}

	public Cultivo RegistrarCultivo(Cultivo pCultivo) {
		Cultivo cultivo = null;
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			Long id = (Long) session.save("Cultivo", pCultivo);
			Criteria c = session.createCriteria(Cultivo.class);
			c.add(Restrictions.eq("_id", id));
			cultivo = (Cultivo) c.uniqueResult();
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return cultivo;
	}

	public void ActualizarCultivo(Cultivo pCultivo) {
		try {
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.update("Cultivo", pCultivo);
		} catch (Exception e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
	}

	public Cultivo ComprobarCultivo(Cultivo pCultivo) {
		Cultivo retorno = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			Criteria c = session.createCriteria(Cultivo.class);
			c.add(Restrictions.ilike("_nombre", pCultivo
					.get_nombre()));
			retorno = (Cultivo) c.uniqueResult();
		} catch (Exception e) { 
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return retorno;
	}
}