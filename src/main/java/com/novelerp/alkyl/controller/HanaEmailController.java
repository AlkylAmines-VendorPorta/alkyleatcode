package com.novelerp.alkyl.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import com.novelerp.core.dto.CustomResponseDto;
import com.novelerp.core.dto.ResponseDto;

@Controller
@RequestMapping("/rest")
public class HanaEmailController {
	
	
	@RequestMapping(value="/hanaEmail/{Qcfno}/{R2}/{QcfApprved}",method=RequestMethod.GET)
	public @ResponseBody String hanaEmail(@PathVariable("Qcfno") String Qcfno, @PathVariable("R2")  String R2, @PathVariable("QcfApprved") String QcfApprved){
		
		 // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
        // Install the all-trusting trust manager
        SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
 
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		CustomResponseDto response=new CustomResponseDto();
		
	//	String url ="https://172.18.2.28:44300/sap/opu/odata/sap/ZMM_QCF_UPD_STATUS_SRV/QCFLevel2Set(Qcfno="+Qcfno+",R2="+R2+",QcfApprved="+QcfApprved+")?sap-client=100";
		String url ="https://172.18.2.36:44300/sap/opu/odata/sap/ZMM_QCF_UPD_STATUS_SRV/QCFLevel2Set(Qcfno="+"'"+Qcfno+"'"+",R2="+"'"+R2+"'"+",QcfApprved="+"'"+QcfApprved+"'"+")?sap-client=100";
		
	//	String url ="https://172.18.2.28:44300/sap/opu/odata/sap/ZMM_QCF_UPD_STATUS_SRV/QCFLevel2Set(Qcfno='0000000020',R2='Y',QcfApprved='VAIBHAV%20K')?sap-client=100";
		System.out.println(url);

		URLConnection request = null;
		try {
		//	URL u = new URL( URLEncoder.encode(QcfApprved, "UTF-8"));
			URL u = new URL(url.replace(" ","%20"));
		//	URL u = new URL(url);
			request = u.openConnection();
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();
			if(R2.equals("Y")) {
			//	response.setMessage("The QCF Document is Approved");
				//response.setSuccess(true);
				return "<html><body><h1>The QCF Document is Approved</h1></body></html>";
			}
			else {
				return "<html><body><h1>The QCF Document is Rejected</h1></body></html>";
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			//response.setMessage("The QCF Document is Rejected");
			//response.setSuccess(false);
		}finally {
			if (request != null) {
				try {
					((HttpURLConnection) request).disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}		
		//response.setMessage("The QCF Document is Rejected");
		return null;
	}
	
	
	
	@RequestMapping(value="/oaAprrovalLevel1/{augru}/{vbeln}/{zlvl1approver}/{zlvl1appstatus}",method=RequestMethod.GET)
	public @ResponseBody String oaAprrovalLevel1(@PathVariable("augru") String augru, @PathVariable("vbeln")  String vbeln, @PathVariable("zlvl1approver") String zlvl1approver, @PathVariable("zlvl1appstatus") String zlvl1appstatus){
		
		 // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
        // Install the all-trusting trust manager
        SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
 
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		CustomResponseDto response=new CustomResponseDto();
		
	
		//String url="https://172.18.2.36:44300/sap/opu/odata/sap/ZSD_OA_UPD_STATUS_SRV/OALevel1Set(Augru='600',Vbeln='006000089',Zlvl1approver='davisraja.pious@pwc.com',Zlvl1appstatus='001')"
		String url ="https://172.18.2.36:44300/sap/opu/odata/sap/ZSD_OA_UPD_STATUS_SRV/OALevel1Set(Augru="+"'"+augru+"'"+",Vbeln="+"'"+vbeln+"'"+",Zlvl1approver="+"'"+zlvl1approver+"'"+",Zlvl1appstatus="+"'"+zlvl1appstatus+"'"+")";
		
	
		System.out.println(url);

		URLConnection request = null;
		try {
		//	URL u = new URL( URLEncoder.encode(url, "UTF-8"));
		//	URL u = new URL(url.replace(" ","%20"));
			URL u = new URL(url);
			request = u.openConnection();
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();
			if(zlvl1appstatus.equals("001")) {
			//	response.setMessage("The QCF Document is Approved");
				//response.setSuccess(true);
				return "<html><body><h1>The OA document "+vbeln+" has been Approved by GM/SM and released for further approval.</h1></body></html>";
			}
			else {
				return "<html><body><h1>The OA document "+vbeln+" has been Rejected by GM/SM and cannot be processed further.</h1></body></html>";
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			//response.setMessage("The QCF Document is Rejected");
			//response.setSuccess(false);
		}finally {
			if (request != null) {
				try {
					((HttpURLConnection) request).disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
		
		//response.setMessage("The QCF Document is Rejected");
	
		return null;
	}
	
	@RequestMapping(value="/oaAprrovalLevel2/{augru}/{vbeln}/{zlvl2approver}/{zlvl2appstatus}",method=RequestMethod.GET)
	public @ResponseBody String oaAprrovalLevel2(@PathVariable("augru") String augru, @PathVariable("vbeln")  String vbeln, @PathVariable("zlvl2approver") String zlvl2approver, @PathVariable("zlvl2appstatus") String zlvl2appstatus){
		
		 // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
        // Install the all-trusting trust manager
        SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
 
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		CustomResponseDto response=new CustomResponseDto();
		
	
		//String utl="https://172.18.2.36:44300/sap/opu/odata/sap/ZSD_OA_UPD_STATUS_SRV/OALevel2Set(Augru='600',Vbeln='006000089',Zlvl2approver='davisraja.pious@pwc.com',Zlvl2appstatus='001')"
		String url ="https://172.18.2.36:44300/sap/opu/odata/sap/ZSD_OA_UPD_STATUS_SRV/OALevel2Set(Augru="+"'"+augru+"'"+",Vbeln="+"'"+vbeln+"'"+",Zlvl2approver="+"'"+zlvl2approver+"'"+",Zlvl2appstatus="+"'"+zlvl2appstatus+"'"+")";
		

		System.out.println(url);

		URLConnection request = null;
		try {
		//	URL u = new URL( URLEncoder.encode(url, "UTF-8"));
		//	URL u = new URL(url.replace(" ","%20"));
			URL u = new URL(url);
			
			request = u.openConnection();
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();
			if(zlvl2appstatus.equals("001")) {
			//	response.setMessage("The QCF Document is Approved");
				//response.setSuccess(true);
				return "<html><body><h1>The OA document "+vbeln+" has been Approved by ED and released for further approval.</h1></body></html>";
			}
			else {
				return "<html><body><h1>The OA document "+vbeln+" has been Rejected by ED and cannot be processed further.</h1></body></html>";
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			//response.setMessage("The QCF Document is Rejected");
			//response.setSuccess(false);
		}finally {
			if (request != null) {
				try {
					((HttpURLConnection) request).disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}
		//return "<html><body><h1>The OA is Rejected</h1></body></html>";
		return null;
	}
	
	
	@RequestMapping(value="/soAprrovalLevel1/{augru}/{vbeln}/{zlvl1approver}/{zlvl1appstatus}",method=RequestMethod.GET)
	public @ResponseBody String soAprrovalLevel1(@PathVariable("augru") String augru, @PathVariable("vbeln")  String vbeln, @PathVariable("zlvl1approver") String zlvl1approver, @PathVariable("zlvl1appstatus") String zlvl1appstatus){
		
		 // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
        // Install the all-trusting trust manager
        SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
 
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		CustomResponseDto response=new CustomResponseDto();
		//String url="https://172.18.2.36:44300/sap/opu/odata/sap/ZSD_SO_UPD_STATUS_SRV/OALevel1Set(Augru='600',Vbeln='006000089',Zlvl1approver='davisraja.pious@pwc.com',Zlvl1appstatus='001')"
	    String url ="https://172.18.2.36:44300/sap/opu/odata/sap/ZSD_SO_UPD_STATUS_SRV/SOLevel1Set(Augru="+"'"+augru+"'"+",Vbeln="+"'"+vbeln+"'"+",Zlvl1approver="+"'"+zlvl1approver+"'"+",Zlvl1appstatus="+"'"+zlvl1appstatus+"'"+")";
		
	
		System.out.println(url);

		URLConnection request = null;
		try {
		//	URL u = new URL( URLEncoder.encode(url, "UTF-8"));
		//	URL u = new URL(url.replace(" ","%20"));
			URL u = new URL(url);
			request = u.openConnection();
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();
			if(zlvl1appstatus.equals("001")) {
			//	response.setMessage("The QCF Document is Approved");
				//response.setSuccess(true);
//				return "<html><body><h1>The Sales Order is Approved</h1></body></html>";
				return "<html><body><h1>The Sales Order document "+vbeln+" has been Approved by GM/SM and released for further approval.</h1></body></html>";
			}
			else {
			//	return "<html><body><h1>The Sales Order is Rejected</h1></body></html>";
				return "<html><body><h1>The Sales Order document "+vbeln+" has been Rejected by GM/SM and cannot be processed further.</h1></body></html>";
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			//response.setMessage("The QCF Document is Rejected");
			//response.setSuccess(false);
		}finally {
			if (request != null) {
				try {
					((HttpURLConnection) request).disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}

		return null;
	}
	
	@RequestMapping(value="/soAprrovalLevel2/{augru}/{vbeln}/{zlvl2approver}/{zlvl2appstatus}",method=RequestMethod.GET)
	public @ResponseBody String soAprrovalLevel2(@PathVariable("augru") String augru, @PathVariable("vbeln")  String vbeln, @PathVariable("zlvl2approver") String zlvl2approver, @PathVariable("zlvl2appstatus") String zlvl2appstatus){
		
		 // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
 
        // Install the all-trusting trust manager
        SSLContext sc = null;
		try {
			sc = SSLContext.getInstance("SSL");
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
		} catch (KeyManagementException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
 
        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
 
        // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		
		CustomResponseDto response=new CustomResponseDto();
		

		//String url="https://172.18.2.36:44300/sap/opu/odata/sap/ZSD_SO_UPD_STATUS_SRV/OALevel2Set(Augru='600',Vbeln='006000089',Zlvl2approver='davisraja.pious@pwc.com',Zlvl2appstatus='001')"
		String url ="https://172.18.2.36:44300/sap/opu/odata/sap/ZSD_SO_UPD_STATUS_SRV/SOLevel2Set(Augru="+"'"+augru+"'"+",Vbeln="+"'"+vbeln+"'"+",Zlvl2approver="+"'"+zlvl2approver+"'"+",Zlvl2appstatus="+"'"+zlvl2appstatus+"'"+")";
		

		System.out.println(url);

		URLConnection request = null;
		try {
		//	URL u = new URL( URLEncoder.encode(url, "UTF-8"));
		//	URL u = new URL(url.replace(" ","%20"));
			URL u = new URL(url);
			request = u.openConnection();
			request.connect();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + "\n");
			}
			System.out.println(sb);
			br.close();
			if(zlvl2appstatus.equals("001")) {
			//	response.setMessage("The QCF Document is Approved");
				//response.setSuccess(true);
				return "<html><body><h1>The Sales Order document "+vbeln+" has been Approved by ED and released for further approval.</h1></body></html>";
			}
			else {
				return "<html><body><h1>The Sales Order document "+vbeln+" has been Rejected by ED and cannot be processed further.</h1></body></html>";
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			//response.setMessage("The QCF Document is Rejected");
			//response.setSuccess(false);
		}finally {
			if (request != null) {
				try {
					((HttpURLConnection) request).disconnect();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		}

		return null;
	}

	
}






