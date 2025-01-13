package com.novelerp.eat.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.novelerp.alkyl.config.AlkylConfig;
//import com.novelerp.alkyl.filter.CORSFilter;

import com.novelerp.appbase.config.BaseAppConfig;
import com.novelerp.appcontext.config.ContextAppConfig;
import com.novelerp.core.configuration.AppConfig;
import com.novelerp.process.config.ProcessAppConfig;


public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	final static Logger log = LoggerFactory.getLogger(AppInitializer.class);
 
    @Override
    protected Class<?>[] getRootConfigClasses() {
    	log.info("Inside AppInitializer getRootconfigClasses()");
        return new Class[] {AlkylConfig.class,EatAppConfig.class,AppConfig.class,BaseAppConfig.class,ContextAppConfig.class,ProcessAppConfig.class};
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
    	log.info("Inside AppInitializer getServletMappings()");
        return new String[] { "/" };
    }
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        /*servletContext.addListener(new SessionListener());*/
        servletContext.addListener( new RequestContextListener());
    }
  
//    @Override
//	protected Filter[] getServletFilters() {
//		return new Filter[]{new CORSFilter()};
//	}
}
