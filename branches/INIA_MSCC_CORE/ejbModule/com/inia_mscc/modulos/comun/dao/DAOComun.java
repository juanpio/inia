package com.inia_mscc.modulos.comun.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.StaleObjectStateException;

import com.inia_mscc.config.hibernate.HibernateUtil;
import com.inia_mscc.config.util.LoggingUtilities;
import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.excepciones.ObjetoNoEncontradoException;

public class DAOComun {

	private static final Logger logger = Logger.getLogger(DAOComun.class);
	
	
	
	@SuppressWarnings("unchecked")
	public <T> T  obtenerEntidad(Class<T> clazz){
		Object retorno ;
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Criteria c;
            c = session.createCriteria(clazz);
            retorno = c.uniqueResult();
			
			if(retorno == null)
			{
				throw new ObjetoNoEncontradoException();
			}
			
		} catch (StaleObjectStateException e) {
			String stackTrace = LoggingUtilities.obtenerStackTrace(e);
			logger.error(stackTrace);
			throw new IniaPersistenciaException(e.getMessage(), e);
		}
		return (T) retorno;
	}
	
	
	
	
}
