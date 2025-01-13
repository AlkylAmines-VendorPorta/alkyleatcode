package com.novelerp.core.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.novelerp.appbase.util.AppBaseConstant;
import com.novelerp.appcontext.filter.SessionOutInterceptor;
import com.novelerp.core.util.CoreConstant;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.novelerp.*")
@EnableTransactionManagement
//@EnableSwagger2
public class AppConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		viewResolver.setOrder(1);
		return viewResolver;
	}
	
    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        String [] definitions =  {"/WEB-INF/views/tiles/tiles.xml"};
        tilesConfigurer.setDefinitions(definitions);
        tilesConfigurer.setCheckRefresh(true);
        return tilesConfigurer;
    }
    
   
    @Bean
    TilesViewResolver tileViewResolver(){
        TilesViewResolver viewResolver = new TilesViewResolver();
        viewResolver.setOrder(0);
        return viewResolver;
    }
   
    /* 
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) 
    {
        TilesViewResolver viewResolver = new TilesViewResolver();
//        viewResolver.setOrder(0);
        registry.viewResolver(viewResolver);
    }
   */ 


	 /**
     * Configure ViewResolvers to deliver preferred views.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");*/
        registry.addResourceHandler("/resource/**").addResourceLocations("/resource/");
       /* registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");*/
    }
    
    
    /**
     * 
     * @return DataSource Object from JNDI configuration
     */
    @Bean(name = "dataSource",destroyMethod="")
    public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        /*return dsLookup.getDataSource("jdbc/eat");*/ 
       return dsLookup.getDataSource("jdbc/cloudEat");

    }
    
    /**
     * Get hibernate properties
     * @return Properties Object with hibernate configuration.
     */
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "none");
		properties.put("hibernate.dialect",
			CoreConstant.SQLSERVER_DIALECT);//ORACLE_DIALECT--------POSTGRES_DIALECT
		properties.put("hibernate.jdbc.batch_size", AppBaseConstant.BATCH_SIZE);
		
		return properties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.**.entity" });
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(getHibernateProperties());
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory emf) {
		final JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

    
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public Mapper getMapper(){		
		return new DozerBeanMapper();
	}
	
	@Bean
    public CommonsMultipartResolver multipartResolver(){

        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }
	
/*	@Bean
	public HttpSessionListener httpSessionListener(){
		return new SessionListenerController();
	}
	*/

	//add in your AppConfig.java
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	registry.addInterceptor(new SessionOutInterceptor());
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }    
	/*@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()                 
                .apis(RequestHandlerSelectors.any())
                .paths( PathSelectors.any())
                .build();             
    }*/
	
	
}
