package com.novelerp.eat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.novelerp.appcontext.security.CustomPasswordValidator;
import com.novelerp.appcontext.security.JwtAuthenticationEntryPoint;
import com.novelerp.appcontext.security.JwtAuthenticationFilter;
import com.novelerp.appcontext.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;

	/*@Autowired
	CustomSuccessHandler successHandler;*/

	@Autowired
	UserService userService;

	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	/*
	@Autowired
	private CustomAuthenticationProvider authProvider;
*/
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		//builder.authenticationProvider(authProvider);
		builder
        .userDetailsService(userDetailsService).passwordEncoder(new CustomPasswordValidator());
	}

	@Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeRequests().antMatchers("/",
								                "/favicon.ico",
								                "/**/*.png",
								                "/**/*.gif",
								                "/**/*.svg",
								                "/**/*.jpg",
								                "/**/*.html",
								                "/**/*.css",
								                "/**/*.js"
								                ).permitAll()
       // "/eat/rest/hanaEmail/**"
        .antMatchers("/api/auth/**").permitAll()
        .antMatchers("/**").permitAll()
       // .antMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**").permitAll()
        .anyRequest().authenticated().and().

        // Add our custom JWT security filter
        addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {/*
		web.ignoring().antMatchers("/createCaptcha",
								   "/getTahdrTypeCode/**",
								   "/home/**",
								   "/rest/**",
								   "/invite/rest/**",
								   "/newUser/rest/**",
								   "/rest/**",
								   "/pu/**",
								   "/api/auth/signin",
								   "/testReact",
								   "/latestAuctionReverse/**",
								   "/latestAuctionForward/**",
								   "/latestTendersProcurement/**",
								   "/blacklistedVendors/**",
								   "/registeredVendors/**",
								   "/blacklistedCustomer/**",
								   "/registeredCustomer/**",
								   "/getRegisteredVendorList/**",
								   "/register/**",
								   "/getTenderSearchDetail/**",
								   "/advanceSearch/**",
								   "/getSearchList/**",
								   "/termsnCondition/**",
								   "/notification/**",
								   "/forgotPassword/**",
								   "/contactUs/**",
								   "/calendar/**",
								   "/disclaimer/**",
								   "/getHomePublicNoticeList/**",
								   "/publicNotices/**",
								   "/registration/**",
								   "/download/**",
								   "/getLatestTahdrTypeCode/**",
								   "/getTahdrTypeCode/**",
								   "/latestTendersWorks/**",
								   "/latestAnnouncements/**",
								   "/getRegisteredCustomerList/**",
								   "/getTAHDRDetailsById/**",
								   "/reloadContext/**",
								   "/reloadServletContext/**",
								   "/reloadProperties/**",
								   "/changePlainPassword/**",
								   "/faq/**", "/credits/**",
								   "/sessionOut",
								   "/Access_Denied",
								   "/interpoleResponse",
								   "/getApprovedVendorsList");
	*/}

	/*
	 * @Bean public DaoAuthenticationProvider authenticationProvider() {
	 * DaoAuthenticationProvider authenticationProvider = new
	 * DaoAuthenticationProvider();
	 * authenticationProvider.setUserDetailsService(userDetailsService);
	 * authenticationProvider.setPasswordEncoder(new CustomPasswordEncoder());
	 * return authenticationProvider; }
	 */

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}
	
	 @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }

	/*@Bean
	public CustomAuthenticationFilter authenticationFilter() throws Exception {
		CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
		filter.setAuthenticationManager(authenticationManagerBean());
		filter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/userlogin", "POST"));
		filter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());
		filter.setAuthenticationFailureHandler(customAuthenticationFailureHandler());
		return filter;
	}*/

	/*@Bean
	public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return new CustomSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler customAuthenticationFailureHandler(){
		return new CustomAuthenticationFailureHandler();
	}*/

}