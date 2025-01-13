package com.novelerp.core.configuration;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.service.SystemConfiguratorService;
/**
 * 
 * @author Vivek Birdi
 * Component representing Application information, which should be available at application level.
 *  
 * 
 */
@Component
public class AppInfo implements ServletContextAware{

	@Autowired
	SystemConfiguratorService sysConfigService;
	@Override
	public void setServletContext(ServletContext context) {
		String appVer=sysConfigService.getSystemPropertyConfigurator(AppBaseConstant.EAT_APP_CONTEXT_VERSION);
		String appMode=sysConfigService.getSystemPropertyConfigurator(AppBaseConstant.EAT_APP_CONTEXT_MODE);
		/* app mode 
		 * - debug = deb
		 * - minified= min */ 
		context.setAttribute("appVer", appVer);
		context.setAttribute("appMode", appMode);
	}

}
