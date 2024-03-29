package com.inia_mscc.config.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.inia_mscc.config.util.LoggingUtilities;
import com.inia_mscc.excepciones.IniaPersistenciaException;
import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.ListaCriterioSeleccion;
import com.inia_mscc.modulos.adm.entidades.Pais;
import com.inia_mscc.modulos.adm.entidades.Region;
import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.adm.entidades.Ubicacion;
import com.inia_mscc.modulos.adm.entidades.ValorSeleccion;
import com.inia_mscc.modulos.eje.entidades.EjecucionMSCC;
import com.inia_mscc.modulos.eje.entidades.Proyeccion;
import com.inia_mscc.modulos.eje.entidades.ResultadoMSCC;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.Cultivo;
import com.inia_mscc.modulos.gem.entidades.Escenario;
import com.inia_mscc.modulos.gem.entidades.Modelo;
import com.inia_mscc.modulos.gem.entidades.Propiedad;
import com.inia_mscc.modulos.seg.entidades.DatoUsuario;
import com.inia_mscc.modulos.seg.entidades.Perfil;
import com.inia_mscc.modulos.seg.entidades.Usuario;


public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY;

    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    
    static {
        try {
        	
        	SESSION_FACTORY = new AnnotationConfiguration()
        	.addAnnotatedClass(Usuario.class)
        	.addAnnotatedClass(DatoUsuario.class)
        	.addAnnotatedClass(Perfil.class)
        	.addAnnotatedClass(Pais.class)
        	.addAnnotatedClass(Departamento.class)
        	.addAnnotatedClass(Ciudad.class)
        	.addAnnotatedClass(Transaccion.class)
        	.addAnnotatedClass(Region.class)
        	.addAnnotatedClass(Cultivo.class)
        	.addAnnotatedClass(Propiedad.class)
        	.addAnnotatedClass(ValorSeleccion.class)
        	.addAnnotatedClass(ListaCriterioSeleccion.class)
        	.addAnnotatedClass(Archivo.class)
        	.addAnnotatedClass(Ubicacion.class)
        	.addAnnotatedClass(Escenario.class)
        	.addAnnotatedClass(EjecucionMSCC.class)
        	.addAnnotatedClass(ResultadoMSCC.class)
        	.addAnnotatedClass(Modelo.class)
        	.addAnnotatedClass(Proyeccion.class)
        	.configure("hibernate.cfg.xml")
        	.buildSessionFactory();
            
        } catch (Throwable ex) {
            logger.fatal("Creación de la SessionFactory fallida." + ex);
            String stackTrace = LoggingUtilities.obtenerStackTrace(ex);
            logger.fatal(stackTrace);
            throw new IniaPersistenciaException(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public static void createSchema() {
        Configuration cfg = new Configuration().configure();
        SchemaExport schemaExport = new SchemaExport(cfg);
        schemaExport.create(true, true);
    }
}
