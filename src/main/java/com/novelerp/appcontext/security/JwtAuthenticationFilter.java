package com.novelerp.appcontext.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.novelerp.appcontext.service.UserSessionService;
import com.novelerp.commons.util.CommonUtil;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private RedisTemplate<String, Object> template;
	
	@Autowired
	private UserSessionService userSessionService;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			
			boolean isfilteredUrl=false;
			boolean isLoginApi=isLoginApi(request);
			isfilteredUrl=getFilteredUrl(request);
			
			String jwt = getJwtFromRequest(request);

			if (isfilteredUrl || isLoginApi || "OPTIONS".equalsIgnoreCase(request.getMethod())){
				filterChain.doFilter(request, response);
			}else if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
				/*Long userId = tokenProvider.getUserIdFromJWT(jwt);*/

				// UserDto userDto = tokenProvider.getUserFromJWT(jwt);
				// UserDetails userDetails = UserPrincipal.create(userDto);
				//UserDetails userDetails = loginService.loadUserById(userId);
				
				UserPrincipal userDetails = new UserPrincipal(jwt);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
				filterChain.doFilter(request, response);
			}else{
				try{
					logout(request,jwt);
				}catch (Exception e) {
					e.printStackTrace();
				}
				/*((HttpServletResponse) response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);*/
				response.setCharacterEncoding("UTF-8");
				response.addHeader("Access-Control-Allow-Origin", "*");
				response.setContentType("text/xml");
				PrintWriter out = response.getWriter();
				out.write("{'status':'401'}");
				out.flush();
			}
		} catch (Exception ex) {
			ex.printStackTrace();// ("Could not set user authentication in security context", ex);
		}
	}
	
	/*@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (Exception ex) {
			ex.printStackTrace();// ("Could not set user authentication in security context", ex);
		}
	}*/

	private String getJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(CommonUtil.isStringEmpty(bearerToken) && "OPTIONS".equalsIgnoreCase(request.getMethod()))
			return request.getMethod();
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	private boolean isLoginApi(HttpServletRequest request){
		if("/api/auth/signin".equals(request.getServletPath()))
			return true;
		return false;
	}
	
	private boolean getFilteredUrl(HttpServletRequest request){
		String url=request.getServletPath();
		if(CommonUtil.isStringEmpty(url)){
			return false;
		}else if("/api/auth/signin".equals(url)){
			return true;
		}else if(url.contains("/rest/download/")){
			return true;
		}else if(url.contains("/rest/forgotPassword")){
			return true;
		}else if(url.contains("/rest/downloadManual")){
		return true;
	    }else if(url.contains("/rest/getQCFForApproval")){
		return true;
	    }else if(url.contains("/rest/updateFinalApproval")){
		return true;
	    }else if(url.contains("/rest/updateAnnexureReject")){
		return true;
	    }else if(url.contains("/rest/getSalesOrderList")){
		return true;
	    }else if(url.contains("/rest/getGSTNDetails")){
		return true;
		} else if (url.contains("/rest/hanaEmail")) {
			return true;
		} else if (url.contains("/rest/oaAprrovalLevel1")) {
			return true;
		} else if (url.contains("/rest/oaAprrovalLevel2")) {
			return true;
		} else if (url.contains("/rest/soAprrovalLevel1")) {
			return true;
		} else if (url.contains("/rest/soAprrovalLevel2")) {
			return true;
		}  else if (url.contains("/rest/sapVendorRegistration")) {
			return true;
		}
		 else if (url.contains("/rest/exportExcel")) {
				return true;
			}
		 else if (url.contains("/rest/exportASNLineExcel")) {
				return true;
			}
		 else if (url.contains("/rest/moveType103ForSAP")) {
				return true;
			}  else if (url.contains("/rest/downloadPO")) {
				return true;
			} 
			 else if (url.contains("/rest/POSAPStatus")) {
					return true;
				}
			 else if (url.contains("/rest/NicerGlobeVehicleStatusUpdate")) {
					return true;
				}
			 else if (url.contains("/rest/moveType105ForSAP")) {
					return true;
				}
			 else if (url.contains("/rest/unloadingFormJson")) {
					return true;
				}
			 else if (url.contains("/rest/serviceSheetForSAP")) {
					return true;
				}
			 else if (url.contains("/rest/sentQCFApprovalMail")) {
					return true;
				}
			 else if (url.contains("/rest/getQCFAnnexureDetailsForSAP")) {
					return true;
				}
			 else if (url.contains("/rest/fetchAnyUser")) {
					return true;
				}
		
		else {
			return false;
		}
	}
	
	private void logout(HttpServletRequest request,String jwt){
		if(!"OPTIONS".equalsIgnoreCase(request.getMethod())){
			request.getSession().invalidate();
			template.delete(jwt);
			userSessionService.updateUserAuthTokenSession(jwt);
		}
	}
	
}
