
package com.novelerp.appbase.util;

/**
 * 
 * @author Vivek Birdi
 *
 */
public final class AppBaseConstant {
	
	private AppBaseConstant(){}

	public static final Integer BATCH_SIZE=1000;
	
	public static final int BASE_PRICE_INCREASE_RATE =5;
	
	public static final String SCHEMA ="eauction";
	public static final String CONTEXT = "context";
	public static final String SIMPLE_USER_SERVICE="SIMPLE_USER_SERVICE";
	/* User Types */
	public static final String USER_TYPE_CUSTOMER="CUSTOMER";
	public static final String USER_TYPE_VENDOR="VENDOR";
	public static final String USER_TYPE_EMPLOYEE="EMPLOYEE";
	/* User Types */
	
	public static final Integer SUCCESS=200;
	public static final Integer FAILURE=400;
	
	/* Roles */
	public static final String ROLE_CUSTOMER_ADMIN="CUSADM";
	public static final String ROLE_SYS_ADMIN="SYSADM";
	public static final String ROLE_VENDOR_ADMIN="VENADM";
	public static final String ROLE_QUICK_VENDOR_ADMIN="QVENADM";
	public static final String ROLE_VENDOR_USER="VENUSR";
	public static final String ROLE_CUSTOMER_EXECUTIVE="CUSEXC";
	public static final String ROLE_CUSTOMER_USER="CUSUSR";
	public static final String ROLE_AUDITOR_USER="AUDIT";
	public static final String ROLE_LOCATION_ADMIN="LOCADM";
	public static final String ROLE_PARTNER_USER="PATUSR";
	public static final String ROLE_PARTNER_ADMIN="PADMIN";
	public static final String ROLE_INFRA_ADMIN="INFADM";
	public static final String ROLE_FINANCE_OPERATOR="FINOPR";
	public static final String ROLE_FINANCE_ADMIN="FINADM";
	public static final String ROLE_VENDOR_APPROVAL="VENAPP";
	/* Roles */
	
	/* Roles ALKYL*/
	public static final String ROLE_SECURITY_ADMIN="SECADM";
	public static final String ROLE_SAFETY_ADMIN="SFTADM";
	public static final String ROLE_OHC_ADMIN="OHCADM";
	public static final String ROLE_STORE_ADMIN="STRADM";
	public static final String ROLE_QC_ADMIN="QCADM";
	public static final String ROLE_BUYER_ADMIN="BUADM";
	public static final String ROLE_TECHNICAL_ADMIN="TAADM";
	public static final String ROLE_REQUISTIONER_ADMIN="REQADM";
	public static final String ROLE_APPROVER_ADMIN="APPADM";
	public static final String ROLE_PURCHASE_MANAGER_ADMIN="PMADM";
	public static final String ROLE_GENERAL_MANAGER_ADMIN="GMADM";
	public static final String ROLE_EXECUTIVE_MANAGER_ADMIN="EMADM";
	public static final String ROLE_CHAIR_MAN_ADMIN="CHMADM";
	public static final String ROLE_NEGOTIATOR_ADMIN="NADMIN";
	public static final String ROLE_COMMERCIAL_ADMIN="CADM";
	public static final String ROLE_SSN_APPROVER="SSNAPP";
	/* Roles ALKYL*/
	
	/* user registration type*/
	public static final String CREATER="CREATER";
	public static final String PARTICIPANT="PARTICIPANT";
	/* user registration type*/

	
	/* file upload path */
	public static final	String FILE_UPLOAD_PATH ="/home/administrator/";
	/* file upload path */
	
	/*Reference Codes*/
	public static  final String BID_TYPE="BID_TYPE";
	public static  final String TENDER_TYPE="TENDER_TYPE";
	public static  final String AUCTION_TYPE="AUCTION_TYPE";
	public static  final String BASIS_TYPE="PRICE_BASIS";
	public static  final String OFFICE_TYPE="OFFICE_TYPE";
	public static  final String BUDGET_TYPE="BUDGET_TYPE";
	public static  final String PAYMENT_MODE="PAYMENT_MODE";
	public static  final String BID_SECTION="BID_SECTION";
	public static  final String DOCUMENT_STATUS="DOCUMENT_STATUS";
	public static  final String BIDDER_STATUS="BIDDER_STATUS";
	public static  final String TAHDR_OPENING_TYPES="OPENING_TYPE";
	public static  final String SALUTATION="SALUTATION";
	public static  final String METHODS_OF_INSPECTION_STANDARD="METHODS_OF_INSPECTION_STANDARD";
	public static  final String VENDOR_TYPE="VENDOR_TYPE"; 
	public static  final String VENDOR_CLASSIFICATION="VENDOR_CLASSIFICATION";
	public static  final String RECON_ACCOUNT="RECON_ACCOUNT";
	public static  final String VEDNOR_SCHEMA_GROUP="VENDOR_SCHEMA_GROUP";
	
	
	/*Reference Codes*/
	
	/*Bid Section Type Code*/
	public static  final String TECHNICAL_SECTION="TS";
	public static  final String COMMERCIAL_SECTION="CS";
	public static  final String PRICE_BID="PS";
	public static  final String COMMON_SECTION="CM";
	/*Bid Section Type Code*/
	
	/*Payment Type Codes*/
	public static  final String REGISTRATION_FEE="RG";
	public static  final String RENEWAL_FEE="RN";
	public static  final String TENDER_PURCHASE_FEE="TF";
	public static  final String EMD="EMD";
	public static  final String INFRA_REGISTRATION_FEE="INFRG";
	/*Payment Type Codes*/
	
	/*Document Status Codes*/
	public static  final String DOCUMENT_STATUS_DRAFTED="DR";
	public static  final String DOCUMENT_STATUS_IN_PROGRESS="IP";
	public static  final String DOCUMENT_STATUS_APPROVED="AP";
	public static  final String DOCUMENT_STATUS_REJECTED="RJ";
	public static  final String DOCUMENT_STATUS_PUBLISHED="PU";
	public static  final String DOCUMENT_STATUS_TC_BID_OPENED="TCOP";
	public static  final String DOCUMENT_STATUS_DEVIATION_BID_OPENED="DBOP";
	public static  final String DOCUMENT_STATUS_PRICE_BID_OPENED="PBOP";
	public static  final String DOCUMENT_STATUS_C1_BID_OPENED="C1OP";
	public static  final String DOCUMENT_STATUS_SCRUTINY="SC";
	public static  final String DOCUMENT_STATUS_FINAL_SCRUTINY="FS";
	public static  final String DOCUMENT_STATUS_COMPLETE="CO";
	public static  final String DOCUMENT_STATUS_VOID="VO";
	/*Document Status Codes*/
	
	/*Document Status Codes*/
	public static  final String BIDDER_STATUS_PURCHASED="PRCH";
	public static  final String BIDDER_STATUS_IN_PROGRESS="INPR";
	public static  final String BIDDER_STATUS_APPROVED="APPR";
	public static  final String BIDDER_STATUS_REJECTED="RJCT";
	public static  final String BIDDER_STATUS_BID_SUBMITED="SBMT";
	public static  final String BIDDER_STATUS_TC_BID_SUBMITED="TBSU";
	public static  final String BIDDER_STATUS_Price_BID_SUBMITED="PBSU";
	public static  final String BIDDER_STATUS_EMD_PAYMENT="EMDP";
	public static  final String BIDDER_STATUS_EMD_PAYMENT_APPROVED="EMDA";
	public static  final String BIDDER_STATUS_EMD_PAYMENT_REJECTED="EMDR";
	public static  final String BIDDER_STATUS_TECHNO_COMMERCIAL_OPENED="TCOP";
	public static  final String BIDDER_STATUS_SCRUTINY_PASSED="PASS";
	public static  final String BIDDER_STATUS_SCRUTINY_FAILED="FAIL";
	public static  final String BIDDER_STATUS_PRELIMINARY_TECHNICAL_PASSED="PTBP";
	public static  final String BIDDER_STATUS_PRELIMINARY_COMMERCIAL_PASSED="PCBP";
	public static  final String BIDDER_STATUS_PRELIMINARY_TECHNICAL_FAILED="PTBF";
	public static  final String BIDDER_STATUS_PRELIMINARY_COMMERCIAL_FAILED="PCBF";
	public static  final String BIDDER_STATUS_DEVIATION_CALLED="DVTN";
	public static  final String BIDDER_STATUS_DEVIATION_SUBMITTED="DBSU";
	public static  final String BIDDER_STATUS_DEVIATION_OPENED="DBOP";
	public static  final String BIDDER_STATUS_FINAL_TECHNICAL_PASSED="FTBP";
	public static  final String BIDDER_STATUS_FINAL_COMMERCIAL_PASSED="FCBP";
	public static  final String BIDDER_STATUS_FINAL_TECHNICAL_FAILED="FTBF";
	public static  final String BIDDER_STATUS_FINAL_COMMERCIAL_FAILED="FCBF";
	public static  final String BIDDER_STATUS_Price_BID_OPENED="PBOP";
	public static  final String BIDDER_STATUS_ANNEXURE_C1_CALLED="ANC1";
	public static  final String BIDDER_STATUS_ANNEXURE_C1_SUBMITED="C1SU";
	public static  final String BIDDER_STATUS_ANNEXURE_C1_OPENED="C1OP";
	public static  final String BIDDER_STATUS_WINNER="AW";
	public static  final String BIDDER_STATUS_QUICK_WINNER="QAW";
	public static  final String BIDDER_STATUS_COMPLETE="CO";
	
	public static  final String BIDDER_STATUS_DRAFTED="DR";
	/*Document Status Codes*/

	public static  final String SCRUTINY_PASSED="PASS";
	public static  final String SCRUTINY_FAILED="FAIL";
	public static  final String PRELIMINARY_TECHNICAL_PASSED="PTP";
	public static  final String PRELIMINARY_TECHNICAL_FAILED="PTF";
	public static  final String PRELIMINARY_COMMERCIAL_PASSED="COMMPASS";
	public static  final String PRELIMINARY_COMMERCIAL_FAILED="COMMFAIL";
	/*public static  final String PRELIMINARY_COMMERCIAL_PASSED="PCP";
	public static  final String PRELIMINARY_COMMERCIAL_FAILED="PCF";*/
	public static  final String DEVIATION_CALLED="DVTN";
	public static  final String SCRUTINY_PARTIAL_PASSED="PARTPASPRELIMINARY_COMMERCIAL_PASSEDS";
	public static  final String DEVIATION_OPENED="DBOP";
	public static  final String SCRUTINY_FINISHED="SCRDONE";
	public static  final String FINAL_TECHNICAL_PASSED="FTBP";
	public static  final String FINAL_COMMERCIAL_PASSED="FCBP";
	public static  final String FINAL_TECHNICAL_FAILED="FTBF";
	public static  final String FINAL_COMMERCIAL_FAILED="FCBF";
	public static  final String Price_BID_OPENED="PBOP";
	public static  final String ANNEXURE_C1_CALLED="ANC1";
	public static  final String ANNEXURE_C1_OPENED="C1OP";
	
	/*Partner Status*/
	public static  final String PARTNER_STATUS_DRAFTED="DR";
	public static  final String PARTNER_STATUS_IN_PROGRESS="IP";
	public static  final String PARTNER_STATUS_EDIT="EDIT";
	public static  final String PARTNER_STATUS_COMPLETE="CO";
	public static  final String EE_CLARIFICATION_STATUS="EEC";
	public static  final String CE_CLARIFICATION_STATUS="CEC";
	public static  final String PARTNER_STATUS_REJECT="RJ";
	/*Partner Status*/
	
	/*Status*/
	public static  final String APPROVED_STATUS="Y";
	public static  final String REJECTED_STATUS="N";
	public static  final String CLARIFICATION_STATUS="C";
	public static  final String EXEMPTED_APPROVED="EXEM";
	public static final String PARTIAL_STATUS="P";
	/*Status*/
	
	/* vender type payment*/
	public static  final String TRADER_PAYMENT="TP";
	public static  final String MANUFACTURER_PAYMENT="MP";
	public static  final String INFRA_PAYMENT="INFP";
	
	/* vender type payment*/
	/*Turn Over Amount For GST Applicable*/
	public static  final double TURNOVER_AMOUNT=2000000;
	/*Turn Over Amount For GST Applicable*/

	/*Tender Scheduling Stages*/
	public static final String Technical_Bid="TechnicalBid";
	public static final String Price_Bid="PriceBid";
	public static final String Annexture_C1="AnnextureC1";  
	public static final String Deviation_Bid="DeviationBid";
	public static final String Bid_Submission="BidSubmission";
	public static final String submission="SUBMISSION";
	/*Tender Scheduling Stages*/
	
	/*Tender Scrutiny Type*/
	public static final String TECHNICAL_SCRUTINY="TECHSCR";
	public static final String COMMERCIAL_SCRUTINY="COMMSCR";
	/*Tender Scrutiny Type*/
	
	/*Tender Scheduling Type*/
	public static final String TENDER_PRICE_BID_SCHEDULING="PBSCH";
	public static final String TENDER_ANNEXURE_C1_SCHEDULING="C1SCH";
	public static final String TENDER_REVISED_BID_SCHEDULING="RBSCH";
	public static final String TENDER_AWARD_WINNER_SCHEDULING="AWSCH";
	public static final String TENDER_DEVIATION_BID_SCHEDULING="DBSCH";
	public static final String TENDER_AWARD_WINNER_COMPLETED = "AWCO";
	public static final String QUICK_RFQ_AWARD_WINNER_COMPLETED = "QRFQCO";
	public static final String RFQ_AWARD_WINNER_COMPLETED = "RFQCO";
	public static final String QUICK_AUCTION_AWARD_WINNER_COMPLETED = "QAWCO";
	public static final String AUCTION_SCHEDULING = "ASCH";
	/*Tender Scheduling Type*/
	
	/*Tender Scheduling Type*/
	public static final String TENDER_TECHNO_COMMERCIAL_OPENNING="TCOP";
	public static final String TENDER_DEVIATION_OPENNING="DBOP";
	public static final String TENDER_PRICE_BID_OPENNING="PBOP";
	public static final String TENDER_ANNEXURE_C1_OPENNING="C1OP";
	public static final String TENDER_REVISED_BID_OPENNING="RBOP";
	public static final String TENDER_AWARD_WINNER_OPENNING="AWOP";
	/*Tender Scheduling Type*/
	
	/*SESSION KEY SALT*/
	public static final String SESSION_KEY="SESSIONKEY";
	/*SESSION KEY SALT*/
	
	public static final Integer LATEST_TAHDR_COUNT=5;
	
	public static final String TENDER_TYPE_CODE_PROCUREMENT="PT";
	public static final String TENDER_TYPE_CODE_WORKS="WT";
	public static final String AUCTION_TYPE_CODE_FORWORD="FA";
	public static final String AUCTION_TYPE_CODE_REVERSE="RA";
	public static final String QUICK_AUCTION_TYPE_CODE_FORWORD="QFA";
	public static final String QUICK_AUCTION_TYPE_CODE_REVERSE="QRA";
	public static final String DOCUMENT_TYPE_QUICK_RFQ="QRFQ";
	public static final String DOCUMENT_TYPE_RFQ="RFQ";
	
	/* MY TENDER REFERENCE LIST */
	
	public static final String MY_TENDER_BUTTON="MY_TENDER_BUTTON";
	
	public static final String MY_TENDER_BID_SUBMISSION="BID";
	public static final String MY_TENDER_C1_BID="C1";
	public static final String MY_TENDER_REVISED_BID="REVISED";
	public static final String MY_TENDER_DVTN_BID="DVTN";
	public static final String MY_TENDER_TC_OPENING="TCOP";
	public static final String MY_TENDER_PB_OPENING="PBOP";
	public static final String MY_TENDER_C1_OPENING="C1OP";
	public static final String MY_TENDER_DV_OPENING="DBOP";
	public static final String MY_TENDER_TC_SCHEDULING="TCSCHD";
	public static final String MY_TENDER_C1_SCHEDULING="C1SCHD";
	public static final String MY_TENDER_PB_SCHEDULING="PBSCHD";
	public static final String MY_TENDER_RB_SCHEDULING="RBSCHD";
	public static final String MY_TENDER_AW_SCHEDULING="AWSCHD";
	public static final String MY_TENDER_DV_SCHEDULING="DBSCHD";
	public static final String MY_TENDER_PRELIMINARY_SCRUTINY="PSCRUTINY";
	public static final String MY_TENDER_FINAL_SCRUTINY="FSCRUTINY";
	public static final String MY_TENDER_WINNER="AW";
	public static final String MY_LIVE_BID="LIVBID";
	
	/* MY TENDER REFERENCE LIST */
	
	public static final String PAYMENT_GATEWAY_SYSTEMID="etender";
	public static final String PAYMENT_GATEWAY_REDIRECT_URL = "eat.payment.gateway.response.url";
	public static final String PAYMENT_GATEWAY_DOCNO_PREFIX = "etdr";
	
	/* MATERIAL TYPE CODE */
	public static final String TAHDR_MATERIAL_TYPE_CODE_SINGLE="single";
	public static final String TAHDR_MATERIAL_TYPE_CODE_BOM="bom";
	
	public static final String DATES_EDITED="Y";
	public static final String DATES_NOT_EDITED="N";
	
	/*TENDER COMMITTEE FOR OPENING TYPE*/
	public static final String TENDER_COMMITTEE_TechnoCommercial_Opening="TCO";
	public static final String TENDER_COMMITTEE_Deviation_Opening="DBO";
	public static final String TENDER_COMMITTEE_PriceBid_Opening="PBO";
	public static final String TENDER_COMMITTEE_Annexure_Opening="C1O";
	public static final String TENDER_COMMITTEE_RevisedBid_Opening="RBO";
	/*TENDER COMMITTEE FOR OPENING TYPE*/
	/*online payment status*/
	public static final String PAYMENT_SUCCESS_STATUS="Success";
	public static final String PAYMENT_FAILED_STATUS="Failed";
	public static final String PAYMENT_PENDING_STATUS="Pending";
	/*online payment status*/
	/*payment mode*/
	public static final String PAYMENT_MODE_DD="DD";
	public static final String PAYMENT_MODE_CA="CA";
	public static final String PAYMENT_MODE_OP="OP";
	public static final String PAYMENT_MODE_ISEXEMP="ISEXEMP";
	public static final String PAYMENT_MODE_CH="CH";
	public static final String PAYMENT_MODE_BG="BG";
	/*payment mode*/
	
	/* INTERPOL RESPONSE */
	public static final String INTERPOL_RESP_SUCCESS="Success";
	public static final String INTERPOL_RESP_FAILED="Failed";
	/* INTERPOL RESPONSE */
	
	/*mail template*/
	public static final String TEMPLATE_TENDER_DETAIL="Tender Details";
	public static final String TEMPLATE_DEVIATION="DEVIATION";
	public static final String VENDOR_RENEWAL_REMINDER_TEMPLATE="VENDOR_RENEWAL_REMINDER";
	public static final String FACTORY_RENEWAL_REMINDER_TEMPLATE="FACTORY_RENEWAL_REMINDER";
	public static final String VENDOR_APPROVE_TEMPLATE="VENDOR_APPROVE";
	public static final String VENDOR_REJECT_TEMPLATE="VENDOR_REJECT";
	public static final String VENDOR_CLARIFY_TEMPLATE="VENDOR_CLARIFY";
	public static final String VENDOR_SUBMIT_TEMPLATE="VENDOR_SUBMIT";
	public static final String VENDOR_EDIT_TEMPLATE="VENDOR_EDIT";
	public static final String VENDOR_AUTO_APPROVE_TEMPLATE="VENDOR_AUTO_APPROVE";
	public static final String VENDOR_EXPIRED_TEMPLATE="VENDOR_EXPIRED";
	public static final String VENDOR_PROFILE_REMINDER_TEMPLATE="VENDOR_PROFILE_REMINDER";
	public static final String VENDOR_PROFILE_EXPIRED_TEMPLATE="VENDOR_PROFILE_EXPIRED";
	public static final String FACTORY_EXPIRED_TEMPLATE="FACTORY_EXPIRED";
	public static final String TEMPLATE_FORGOT_PASSWORD="Forgot Password";
	public static final String TEMPLATE_SCHEDULE_OPENING="Schedule Opening";
	public static final String PAYMENT_APPROVE_TEMPLATE="PAYMENT_APPROVE";
	public static final String PAYMENT_REJECT_TEMPLATE="PAYMENT_REJECT";
	public static final String PASSWORD_AUTO_TEMPLATE="PASSWORD_AUTO";
	public static final String PASSWORD_RESET_TEMPLATE="PASSWORD_RESET";
	public static final String TEMPLATE_AWARD_WINNER="AWARD_WINNER";
	public static final String TENDER_PAYMENT_APPROVE_TEMPLATE="TENDER_PAYMENT_APPROVE";
	public static final String TENDER_PAYMENT_REJECT_TEMPLATE="TENDER_PAYMENT_REJECT";
	public static final String EMD_PAYMENT_APPROVE_TEMPLATE="EMD_PAYMENT_APPROVE";
	public static final String EMD_PAYMENT_REJECT_TEMPLATE="EMD_PAYMENT_REJECT";
	public static final String TEMPLATE_TENDER_DATE="MODIFIED_DATES";
	public static final String TEMPLATE_ABANDONED_TENDER="ABANDONED_TENDER";
	public static final String TEMPLATE_TAHDR_SEND_FOR_APPROVAL="TAHDR_SEND_FOR_APPROVAL";
	public static final String TEMPLATE_LOGIN_GENERATOR="LOGIN_GENERATOR";
	public static final String TEMPLATE_PUBLISH_TENDER="PUBLISH_TENDER";
	public static final String TEMPLATE_REJECT_TENDER="REJECT_TENDER";
	public static final String TEMPLATE_APPROVE_TENDER="APPROVE_TENDER";
	public static final String TEMPLATE_OPENING_REMINDER_MAIL="OPENING_REMINDER_MAIL";
	public static final String TEMPLATE_SUBMISSION_REMINDER_MAIL="SUBMISSION_REMINDER_MAIL";
	public static final String BID_RESUBMITTED="BID_RESUBMITTED";
	public static final String BID_SUBMITTED_SUCCESSFULLY="BID_SUBMITTED_SUCCESSFULLY";

	public static final String Vendor_CODE_CREATION_SAP_TEMPLATE="Vendor_Code_Creation_SAP";
	public static final String PO_RELEASED="PO_UPDATED";
	public static final String PO_ACCEPTED_TEMPLATE="PO_ACCEPTED";
	public static final String ASN_CREATED_TEMPLATE="ASN_CREATED";
	public static final String ASN_REPORTED_TEMPLATE="ASN_REPORTED";
	public static final String SSN_CREATED_1_MAIL_TEMPLATE="SSN_CREATED_1_MAIL";
	public static final String SSN_CREATED_2_MAIL_TEMPLATE="SSN_CREATED_2_MAIL";
	public static final String SSN_APPROVED_TEMPLATE="SSN_APPROVED";
	public static final String INVOICE_SUBMITTED_TEMPLATE1="INVOICE_SUBMITTED1";
	public static final String INVOICE_SUBMITTED_TEMPLATE2="INVOICE_SUBMITTED2";
	public static final String ASN_CREATE_REMINDER="ASN_CREATE_REMINDER";
	public static final String QUATATION_SUBMITION_TEMPLATE="QUATATION_SUBMITION_TEMPLATE";
	public static final String VEHICLE_GATEOUT_TEMPLATE="VEHICLE_GATEOUT_TEMPLATE";
	public static final String QCF_APPROVAL_TEMPLATE="QCF_APPROVAL_TEMPLATE";
	public static final String QCF_CC_APPROVAL_TEMPLATE="QCF_CC_APPROVAL_TEMPLATE";
	public static final String ADVANCE_PAYMENT_REQUEST_TEMPLATE="ADVANCE_PAYMENT_REQUEST_TEMPLATE";
	/*mail template*/
	
	/*Session Attributes For Generated Reports*/
	
	public static final String GENERATED_FILE_NAME="GENERATED_FILE_NAME";
	public static final String GENERATION_TIME="GENERATION_TIME";
	public static final String GENERATION_DIFFERENCE="300000";
	/*Session Attributes For Generated Reports*/
	
	/* financial year type*/
	public static final String TURNOVER_DETAILS="TD";
	public static final String PROFIT_AND_LOSS="PNL";
	public static final String BALANCE_SHEET_ACCOUNT="BSA";
	/* financial year type*/ 
	
	/* SMS Template Ids */
	public static final String SMS_TEMPLATE_TENDER_DETAIL_CHANGED = "476666";
	public static final String SMS_TEMPLATE_EXPIRE = "476667";
	public static final String SMS_TEMPLATE_DEVIATION_BID_SCHEDULE = "476668";
	public static final String SMS_TEMPLATE_PASSWORD_CHANGED = "476669";
	public static final String SMS_TEMPLATE_NEW_PASSWORD = "476670";
	public static final String SMS_TEMPLATE_FACTORY_PHISICAL_VERIFICATION_NOT_APPROVED = "476671";
	public static final String SMS_TEMPLATE_REGISTRATION_APPROVED = "476672";
	public static final String SMS_TEMPLATE_VENDOR_RENEWAL_DUE = "476673";
	public static final String SMS_TEMPLATE_TENDER_ABONDONED = "476674";
	public static final String SMS_TEMPLATE_TENDER_SCHEDULE = "476675";
	public static final String SMS_TEMPLATE_TENDER_OPENED = "476676";
	public static final String SMS_TEMPLATE_PREBID_MEETING_COMPLETED = "476677";
	public static final String SMS_TEMPLATE_PRELIMINARY_SCRUTIINY_COMPLETED = "476678";
	public static final String SMS_TEMPLATE_AWARDED_FOR_TENDER = "476679";
	public static final String SMS_TEMPLATE_PARTNER_INFORMATION_CHANGED = "476680";
	public static final String SMS_TEMPLATE_TENDER_FEE = "476681";
	public static final String SMS_TEMPLATE_LIVE_BID_OTP = "476692";
	public static final String SMS_TEMPLATE_PAYEMNT_RECEIVED = "476693";
	public static final String SMS_TEMPLATE_PROFILE_VERIFICATION = "476694";
	public static final String SMS_TEMPLATE_SUBMITION = "476695";
	/* SMS Template Ids */
	
/*	 SMS parameter 
	public static final String SMS_PARAMETER_1 = "F1";
	public static final String SMS_PARAMETER_2 = "F2";
	public static final String SMS_PARAMETER_3 = "F3";
	public static final String SMS_PARAMETER_4 = "F4";
	public static final String SMS_PARAMETER_5 = "F5";
	
*/	
	/* SMS Template parameter */
	
	/* SMS parameter */
	public static final String SMS_PARAMETER_1 = "F1";
	public static final String SMS_PARAMETER_2 = "F2";
	public static final String SMS_PARAMETER_3 = "F3";
	public static final String SMS_PARAMETER_4 = "F4";
	public static final String SMS_PARAMETER_5 = "F5";
	
	
	/* SMS Template parameter */
	
	/* Timer for live bid auto refresh*/
	public static final String AUTO_REFRESH_TIMER="5s";
	/* Timer for live bid auto refresh*/
	
	public static final String OFFICE_TYPE_OT ="HO";
	public static final String OFFICE_LOCATION_OL ="Corporate Office";
	
	/*Budget type*/
	public static  final String BUDGET_TYPE_CAPEX="CAP";
	public static  final String BUDGET_TYPE_REVENUE="RVN";
	/*budget type*/
	
	/*Budget type*/
	public static  final String BID_TYPE_CODE_SINGLE_BID="SB";
	public static  final String BID_TYPE_CODE_TWO_BID="TB";
	/*budget type*/
   
	/* infra approve status */
	public static  final String INFRA_APPROVE="Y";
	public static  final String INFRA_CLARIFY="C";
	public static  final String INFRA_REJECT="N";
	public static  final String INFRA_IN_PROGRESS="IP";
	public static  final String INFRA_DRAFTED="DR";
	public static  final String INFRA_COMPLETE="CO";
	public static  final String INFRA_EE_CLARIFIED="EEC";
	public static  final String INFRA_SE_CLARIFIED="SEC";
	public static  final String INFRA_CE_CLARIFIED="CEC";
	public static  final String INFRA_ED_CLARIFIED="EDC";
	public static  final String INFRA_DIR_CLARIFIED="DC";
	public static  final String INFRA_STATUS_REJECTED="RJ";
	public static  final String INFRA_STATUS_CLARIFY="CLRFN";
	/* infra approve status */
	
	/*website url*/
	public static final String WEBSITE_URL = "http://10.0.2.173:9099/eatApp/";
	/*website url*/
	
	public static  final String COMPANY_TYPE_GOVT="GOVFRM";
	
	/*SYSTEM CONFIGURATOR*/
	public static final String REPORT_DIR = "REPORT_DIR";
	public static final String REPORT_DOCUMENT_URL = "REPORT_DOCUMENT_URL";
	
	public static final String eat_file_location_windows = "EAT_FILE_LOCATION_WINDOWS";
	public static final String eat_file_location_linux = "EAT_FILE_LOCATION_LINUX";
	
	public static final String eat_tmp_location_windows = "EAT_TMP_LOCATION_WINDOWS";
	public static final String eat_tmp_location_linux = "EAT_TMP_LOCATION_LINUX";
	
	public static final String eat_report_path_windows = "EAT_REPORT_PATH_WINDOWS";
	public static final String eat_report_path_linux = "EAT_REPORT_PATH_LINUX";
	
	public static final String eat_file_size = "EAT_FILE_SIZE";
	public static final String mail_smtp_host = "MAIL_SMTP_HOST";
	public static final String mail_username = "MAIL_USERNAME";
	public static final String mail_password = "MAIL_PASSWORD";
	public static final String mail_smtp_auth = "MAIL_SMTP_AUTH";
	public static final String mail_smtp_port = "MAIL_SMTP_PORT";
	
	public static final String eat_payment_gateway_key = "EAT_PAYMENT_GATEWAY";
	public static final String eat_payment_gateway_iv = "EAT_PAYMENT_GATEWAY_IV";
	
	public static final String sms_request_url = "SMS_REQUEST_URL";
	public static final String sms_isSetProxy = "SMS_ISSETPROXY";
	
	public static final String partner_file_size = "PARTNER_FILE_SIZE";
	public static final String tender_file_size = "TENDER_FILE_SIZE";
	
	public static final String sap_service_username = "SAP_SERVICE_USERNAME";
	public static final String sap_service_password = "SAP_SERVICE_PASSWORD";
	
	public static final String eat_auto_extend_minutes  = "EAT_AUTO_EXTEND_MINUTES";
	
	public static final String eat_compare_minutes = "EAT_COMAPRE_MINUTES";
	
	public static final String timestamp_validation = "TIMESTAMP_VALIDATION";
	
	public static final String eat_pan_verification_userId = "EAT_PAN_VERIFICATION_USERID";
	public static final String eat_pan_verification_password  = "EAT_PAN_VERIFICATION_PASSWORD";
	public static final String eat_pan_verification_url = "EAT_PAN_VERIFICATION_URL";
	
	public static final String eat_pan_verification_serviceurl = "EAT_PAN_VERIFICTION_SERVICEURL";
	public static final String eat_compare_otp_expiryminutes  = "EAT_COMPATRE_OTP_EXPIRYMINUTES";
	public static final String eat_sap_service_username = "EAT_SAP_SERVICE_USERAME";
	public static final String eat_sap_service_password = "EAT_SAP_SERVICE_PASSWORD";
	public static final String eat_sap_service_documentPostingUri = "EAT_SAP_SERVICE_DOCUMENTPOSTINGURI";
	public static final String eat_sap_service_vendorCreationUri  = "EAT_SAP_SERVICE_VENDORCREATIONURI";
	public static final String onlinepayment_billdesk_url = "ONLINEPAYMENT_BILLDESK_URL";
	
	public static final String onlinepayment_billdesk_systemid = "ONLINE_BILLDESK_SYSTEMID";
	public static final String query_for_revenue_graph="Query_For_Revenue_Graph";
	public static final String Query_For_TotalSaving_Graph="Query_For_TotalSaving_Graph";
	/*SYSTEM CONFIGURATOR*/
	
	/*digital test file*/
	public static final String DIGITAL_TEST_COPY = "DIGITAL_TEST_COPY.pdf";
	/*public static final String DIGITAL_TEST_DOCUMENT = "Annexure 1 - Proposal Form.sig";*/
	/*digital test file*/
	/*Attachment Type*/
	public static final String  VENDOR_ATTACHMENT= "VENDOR_REG_DOCS";
	public static final String  PROCUREMENT_TENDER_ATTACHMENT= "PROCUREMENT_DOCS";
	public static final String  WORKS_TENDER_ATTACHMENT= "WORKS_DOCS";
	public static final String  FORWARD_AUCTION_ATTACHMENT= "FA_DOCS";
	public static final String  REVERSE_AUCTION_ATTACHMENT= "RA_DOCS";
	public static final String  QUICK_RFQ_ATTACHMENT= "QRFQ_DOCS";
	public static final String  RFQ_ATTACHMENT= "RFQ_DOCS";
	public static final String  INFRA_ATTACHMENT= "INFRA_DOCS";
	public static final String  MASTER_ATTACHMENT="MASTER_DOCS";
	public static final String  OTHER_ATTACHMENT="others";
	/*Attachment Type*/
	/*sftp server detail code */
	public static final String  SFTP_FILE_SEPERATOR="/";
	public static final String  SFTP_PATH="eat.sftp.file.path.linux";
	public static final String  SFTP_URL="eat.sftp.url";
	public static final String  SFTP_PORT="eat.sftp.port";
	public static final String  SFTP_USERNAME="eat.sftp.username";
	public static final String  SFTP_PASSWORD="eat.sftp.password";
	public static final String  SFTP_CONN_DETAILS="SFTP_CONN_CODE";
	/*sftp server detail code */
	
	/*sftp server detail code */
	public static final String  FTP_FILE_SEPERATOR="/";
	public static final String  FTP_PATH="eat.ftp.file.path";
	public static final String  FTP_URL="eat.ftp.url";
	public static final String  FTP_PORT="eat.ftp.port";
	public static final String  FTP_USERNAME="eat.ftp.username";
	public static final String  FTP_PASSWORD="eat.ftp.password";
	public static final String  FTP_CONN_DETAILS="FTP_CONN_CODE";
	/*sftp server detail code */
	
	/*user detail type*/
	public static final String  COMPANY_USER="COMPUSR";
	public static final String  MANAGEMENT_USER="COMPDIR";
	public static final String  SIGNATORY_USER="SIGNUSR";
	public static final String  FACTORY_USER="ORGUSR";
	/*user detail type*/
	
	public static final String SCHEDULAR_TIME="0 0 10 * * ?";
	
	/* SYSTEM CONFIGURATOR CONSTANTS */
	public static final String  EAT_SFTP_ENABLED="eat.sftp.enabled";
	public static final String  EAT_APP_CONTEXT_VERSION="eat.app.context.version";
	public static final String  EAT_APP_CONTEXT_MODE="eat.app.context.mode";
	/* SYSTEM CONFIGURATOR CONSTANTS */
	
	/* SFTP MEDIA SERVICE IMPLEMENTATION QUALIFIER  */
	public static final String  SFTP_MEDIA_SERVICE_IMPL="sftpMediaServiceImpl";
	public static final String  FTP_MEDIA_SERVICE_IMPL="ftpMediaServiceImpl";
	public static final String  LOCAL_MEDIA_SERVICE_IMPL="localMediaServiceImpl";
	public static final String  ABSTRACT_MEDIA_SERVICE_IMPL="abstractMediaServiceImpl";
	/* SFTP MEDIA SERVICE IMPLEMENTATION QUALIFIER  */
	
	/* APP CONTEXT MODE  */
	public static final String  EAT_APP_CONTEXT_MODE_DEBUG="DEB";
	public static final String  EAT_APP_CONTEXT_MODE_MINIFIED="MIN";
	/* APP CONTEXT MODE  */
	
	/* SAP WEB SERVICE URI */
	public static final String  EAT_SAP_SERVICE_PAYMENT_POSTING="eat.sap.service.documentPostingUri";
	public static final String  EAT_SAP_SERVICE_VENDOR_POSTING="eat.sap.service.vendorCreationUri";
	public static final String  EAT_SAP_SERVICE_CONTRACT_POSTING="eat.sap.service.contractCreationUri";
	
	public static final String  EAT_SAP_SERVICE_USER_NAME="eat.sap.service.username";
	public static final String  EAT_SAP_SERVICE_PASSWORD="eat.sap.service.password";
	/* SAP WEB SERVICE URI */

	/* MIS CONFIG KEYS */
	public static final String  EAT_MIS_FILE_PATH="eat.mis.file.path";
	public static final String  EAT_MIS_FILE_BKP_PATH="eat.mis.file.bkp.path";
	public static final String  EAT_MIS_FILE_NAME="eat.mis.file.name";
	/* MIS CONFIG KEYS */
	
	/* MIS COLUMN NAMES */
	public static final String  MIS_COLUMN_ONLINE_PAY_ID="Online Pay id";
	public static final String  MIS_COLUMN_CHARGES="Charges (Rs.Ps)";
	public static final String  MIS_COLUMN_BILLER_ID="Biller Id";
	public static final String  MIS_COLUMN_BANK_ID="Bank Id";
	public static final String  MIS_COLUMN_BANK_REF_NO="Bank Ref. No.";
	public static final String  MIS_COLUMN_PGI_REF_NO="PGI Ref. No.";
	public static final String  MIS_COLUMN_FEE_TYPE="Fee Type";
	public static final String  MIS_COLUMN_COMPANNY_NAME= "Company Name";
	public static final String  MIS_COLUMN_TENDER_NO= "Tender No";
	public static final String  MIS_COLUMN_PAN_NO= "Ref. 5(PAN No)";
	public static final String  MIS_COLUMN_GSTIN= "Ref. 6(GSTIN)";
	public static final String  MIS_COLUMN_VENDOR_CODE= "Ref. 7(Vender Code)";
	public static final String  MIS_COLUMN_REF_NO_8= "Ref. 8";
	public static final String  MIS_COLUMN_FILLER= "Filler";
	public static final String  MIS_COLUMN_DATE_OF_TXN= "Date of Txn";
	public static final String  MIS_COLUMN_SETTLEMENT_DATE= "Settlement Date";
	public static final String  MIS_COLUMN_GROSS_AMOUNT= "Gross Amount(Rs.Ps)";
	public static final String  MIS_COLUMN_GST_AMOUNT= "GST (Rs Ps)";
	public static final String  MIS_COLUMN_NET_AMOUNT= "Net Amount(Rs.Ps)";
	
	/* MIS COLUMN NAMES */
	
	/*PRICE BID TYPE */
	
	public static final String  PRICE_BID_CHANGE="CHA";
	public static final String  PRICE_BID_C1="AC1";
	public static final String  PRICE_BID_REVISED="REV";
	public static final String  PRICE_BID_SCRAP_BEFORE_OPENING="BOP";
	public static final String  PRICE_BID_SCRAP_AFTER_OPENING="AOP";
	
	
	/*PRICE BID TYPE */
	
   /*Tile Id */
	
	public static final Long  TILE_QUICK_RFQ= 132l;
	
	
	/*PRICE BID TYPE */
	
	/* LOCATION TYPE */
	public static final String LOCATION_TYPE_COMP_ADDR="COMP_ADDR";
	public static final String LOCATION_TYPE_ASSOCIATE_COMP_ADDR="COMP_ASSOCIATE_ADDR";
	/* LOCATION TYPE */
	
	/* DIRECTOR TYPE */
	public static final String DIRECTOR_TYPE_DIRECTOR="DIRECTOR";
	public static final String DIRECTOR_TYPE_PARTNER="PARTNER";
	public static final String DIRECTOR_TYPE_PROPRIETOR="PROPRIETOR";
	/* DIRECTOR TYPE */
	
	/* PO STATUS TYPE */
	public static final String PO_STATUS_RELEASED = "REL";
	public static final String PO_STATUS_ACCEPTED = "ACPT";
	public static final String PO_STATUS_REJECTED = "REJ";
	public static final String PO_STATUS_CLOSED = "CLOSED";
	/* PO STATUS */
	
	/* ASN STATUS */
	public static final String ASN_STATUS_DRAFTED = "DR";
	public static final String ASN_STATUS_IN_TRANSIT = "IT";
	public static final String ASN_STATUS_REPORTED = "REPORTED";
	public static final String ASN_STATUS_SAFETY_CHECK_PASSED = "SCP";
	public static final String ASN_STATUS_SAFETY_CHECK_FAILED = "SCF";
	public static final String ASN_STATUS_SAFETY_CHECK_HOLD = "SCH";
	public static final String ASN_STATUS_OHC_PASSED = "OHCP";
	public static final String ASN_STATUS_OHC_HOLD = "OHCH";
	public static final String ASN_STATUS_OHC_FAILED = "OHCF";
	public static final String ASN_STATUS_GATE_IN = "GATE_IN";
	public static final String ASN_STATUS_103_IN_Progress = "103_IN_Progress";
	public static final String ASN_STATUS_103_Posted = "103_Posted";
//	public static final String ASN_STATUS_105_IN_Progress = "105_IN_Progress";
//	public static final String ASN_STATUS_105_Posted = "105_Posted";
	public static final String ASN_STATUS_101 = "GATE_IN_101";
	public static final String ASN_STATUS_GATE_OUT_WITHOUT_PO = "GATE_OUT_WITHOUT_PO";
	public static final String ASN_STATUS_GATE_OUT = "GATE_OUT";
	public static final String ASN_STATUS_QC_PASSED = "QCP";
	public static final String ASN_STATUS_QC_FAILED = "QCF";
	public static final String ASN_STATUS_UNLOAD = "UNLOAD";
	public static final String ASN_STATUS_105 = "GRN";
	public static final String ASN_STATUS_CLOSED = "CLOSED";
	public static final String ASN_STATUS_CANCELED = "CANCELED";
	public static final String ASN_STATUS_REJECTED = "REJECTED";
	
	public static final String SERVICE_SHEET_STATUS_IN_PROGRESS = "SSIP";
	public static final String SERVICE_SHEET_STATUS_APPROVED = "SSAP";
	public static final String SERVICE_SHEET_STATUS_REJECTED = "SSRJ";
	public static final String SERVICE_SHEET_STATUS_DRAFTED = "SSDR";
	public static final String SERVICE_SHEET_STATUS_APPROVED_2 = "SSAPP";

	public static final String SERVICE_ENTRY_SHEET_STATUS_IN_PROGRESS = "SESIP";
	public static final String SERVICE_ENTRY_SHEET_STATUS_APPROVED = "SESAP";
	public static final String SERVICE_ENTRY_SHEET_STATUS_REJECTED = "SESRJ";
	public static final String SERVICE_ENTRY_SHEET_STATUS_DRAFTED = "SESDR";
	public static final String SERVICE_ENTRY_SHEET_STATUS_CLOSED = "CLOSED";
	public static final String BILL_BOOKED = "BOOKED";
	/* ASN STATUS*/
	
	
	/*Vendor Early Payment Status*/
	public static final String EARLY_PAYMENT_STATUS_IN_PROGRESS = "IP";
	public static final String EARLY_PAYMENT_STATUS_APPROVED = "APPROVED";
	public static final String EARLY_PAYMENT_STATUS_REJECTED = "REJECTED";
	/*Vendor Early Payment Status*/
	
	/* VENDOR TYPE*/
	public static final String VENDOR_TYPE_MANUFACTURER="MANUFACTURER";
	/* VENDOR TYPE*/
	
	public static final String PO_DOCUMENT_TYPE_ZNPO = "ZNPO";
	public static final String PO_DOCUMENT_TYPE_ZNPW = "ZNPW";
	/* REGISTRATION LINK */
	//dev
	/*public static final String REGISTATION_LINK="http://52.76.216.164:5000/";*/
	//prd
//	public static final String REGISTATION_LINK="https://portal.alkylamines.com/alkyl";
	public static final String REGISTATION_LINK="https://portal.alkylamines.com/";
	/* REGISTRATION LINK */
	
	
	/* TEMPLATE FOR ALKYL USERS*/
	public static final String VENDOR_REGISTRATION_ALKYL="VENDOR_REGISTRATION_ALKYL";
	public static final String VENDOR_REGISTRATION_SUCCESSFUL="VENDOR_REGISTRATION_SUCCESSFUL";
	public static final String PORTAL_REGISTRATION_COMPLETED="PORTAL_REGISTRATION_COMPLETED";
	public static final String ALKYL_GENERATE_LOGIN="ALKYL_GENERATE_LOGIN";
	public static final String ALKYL_FORGOT_PASSWORD="Forgot Password";
	public static final String ALKYL_VENDOR_APPROVAL="Vendor_Approval";
	public static final String ALKYL_MAIL_TO_INVITER="Invitation_to_inviter";
	/* TEMPLATE FOR ALKYL USERS*/
	
	/* TEMPLATE FOR ALKYL USERS*/
//	public static final String FTP_HOST="202.143.99.102";
//	public static final String FTP_USER_NAME="ftpuser";
//	//public static final String FTP_PASSWORD="Alkylftp1";
	public static final String FTP_DOCS="/DOCS/";
	//public static final String FTP_INTEGRATION="/SAP_INTEGRATION/";
	public static final String FTP_VENDOR_TO_PROCESS_PATH="/SAP_INTEGRATION/VENDOR_DATA/TO_PROCESS/";
	public static final String FTP_VENDOR_PROCESSED_PATH="/SAP_INTEGRATION/VENDOR_DATA/PROCESSED/";
	
	public static final String FTP_PO_ITEM_TO_PROCESS_PATH="/SAP_INTEGRATION/PO_ITEM/TO_PROCESS/";
	public static final String FTP_PO_ITEM_PROCESSED_PATH="/SAP_INTEGRATION/PO_ITEM/PROCESSED/";
	
	public static final String FTP_PR_DATA_TO_PROCESS_PATH="/SAP_INTEGRATION/PR_DATA/FROM_SAP/";
	public static final String FTP_PR_DATA_PROCESSED_PATH="/SAP_INTEGRATION/PR_DATA/PROCESSED/";
	
	public static final String FTP_GRN_TO_PROCESS_PATH="/SAP_INTEGRATION/GRN_DATA/TO_PROCESS/";
	public static final String FTP_GRN_PROCESSED_PATH="/SAP_INTEGRATION/GRN_DATA/PROCESSED/";
	
	public static final String FTP_SES_TO_PROCESS_PATH="/SAP_INTEGRATION/SERVICES_DATA/TO_PROCESS/";
	public static final String FTP_SES_PROCESSED_PATH="/SAP_INTEGRATION/SERVICES_DATA/PROCESSED/";
	
	public static final String FTP_MPO_TO_PROCESS_PATH="/SAP_INTEGRATION/MAT_MIRO/FROM_PORTAL/";
	public static final String FTP_QCF_TO_PROCESS_PATH="/SAP_INTEGRATION/QCF_DATA/FROM_PORTAL/";
	/* path for material po push in ftp*/
	
	public static final String FTP_PO_PDF_PATH="/SAP_INTEGRATION/PO_PDF/";
	/* TEMPLATE FOR ALKYL USERS*/

	public static final String PREFIX = "SSN";
	
	/* replace COMP_USER IN REGISTRATION WITH INIT_USER */
	public static final String INIT_USER = "INIT_USER";
	
	/*Jwt Expiration Time 900=(15 Minutes)*/
	public static final Integer JWT_EXPIRATION_IN_SECONDS =9000;
	public static final Integer JWT_EXPIRATION = JWT_EXPIRATION_IN_SECONDS*1000;
	
	/*PR Approval Status*/
	public static final String PR_STATUS_IN_PROGRESS = "IP";
	public static final String PR_STATUS_ACCEPTED = "ACPT";
	public static final String PR_STATUS_REJECTED = "REJ";
	public static final String PR_STATUS_BUYER_ASSIGNED = "BA";//buyer assigned
	public static final String PR_STATUS_RELEASED = "REL";
	public static final String PR_STATUS_CLOSED = "CLOSED";
	public static final String PR_STATUS_CREATED = "CREATED";
	
	/*Annexure Doc Status*/
	public static final String ANNEXURE_DRAFT_STATUS= "DRAFTED";
	public static final String ANNEXURE_SUBMIT_STATUS= "SUBMITTED";
	public static final String ANNEXURE_APPROVE_STATUS= "APPROVED";
	public static final String ANNEXURE_REJECT_STATUS= "REJECTED";
	public static final String ANNEXURE_FINAL_APPROVE_STATUS= "POSTED";
	
	/*Static Emails*/
	public static final String PURCHASE_TEAM="purchase2@alkylamines.com";
	public static final String PURCHASE_TEAM_1="purchase1@alkylamines.com";
	public static final String TAX_TEAM="taxation@alkylamines.com";
	public static final String Testing_Mail="sgayatri544@gmail.com";
	
	public static final String ASN_COST_CENTER="ASNCC";
	public static final String ASN_STORAGE_LOCATION="ASNSL";
	
	/*Vehicle Registration Constant*/
	public static final String REQPREFIX = "REQ";
	public static final String STATUSCREATED ="CR";
	public static final String STATUSREGISTER= "RG";
	public static final String STATUSREPOTED= "RP";
	public static final String STATUSVEHICLEGATEIN="VGI";
	public static final String STATUSVEHICLEGATEOUT="VGO";
	public static final String STATUSVEHICLECANCEL = "CANCELLED";
	public static final String STATUSDESTINATIONGATEIN="DESTINATIONGATEIN";
	public static final String STATUSDESTINATIONGATEOUT="DESTINATIONGATEOUT";
	
	public static final String ENQPREFIX = "ENQ";
	
	public static final String RFQPREFIX = "22";
	
	/*Gate Entry*/
	public static final String DOCTYPERGP = "RGP";
	public static final String DOCTYPENRGP = "NRGP";
	
	public static final String GATEENTRYCANCELED = "CANCELED";
	public static final String GECLOSED = "GATE ENTRY CLOSED";
	public static final String GELINEeCLOSED = "GATE ENTRY LINE CLOSED";
	public static final String GATEENTRYCREATED = "CREATED";
	public static final String HODAPPROVED = "HOD APPROVED";
	public static final String HODREJECTED = "HOD REJECTED";
	public static final String FHAPPROVED = "FH APPROVED";
	public static final String FHREJECTED = "FH REJECTED";
	public static final String COMMERCIALAPPROVED = "COMMERCIAL APPROVED";
	public static final String COMMERCIALREJECTED = "COMMERCIAL REJECTED";
	public static final String SECURITYGATEOUT = "GATE OUT";
	public static final String GATEENTRYCLOSED = "CLOSED";
	public static final String MATERIALGATEIN="MATERIAL GATE IN";
	public static final String DOCPREFIX = "DOC";
	public static final String MATERIALCHECKED= "Material Checked";
	public static final String MATERIALCLOSED="CLOSED";
	public static final String PLANTHEADAPPROVED = "PLANT HEAD APPROVED";
	public static final String PLANTHEADREJECTED = "PLANT HEAD REJECTED";
	
	public static final String SSNVERSION="SSN_VERSION";
	
	/*GSTIN Constant*/
	public static final String GSTIN_AUTH_URL="https://einv-apisandbox.nic.in/eivital/v1.04/auth";
	public static final String ALKYL_CLIENT_ID="AAACA27TXPMTOD2";
	public static final String ALKYL_CLIENT_SECRET="Oo53IhWvk1PEd4r87LlZ";
	public static final String ALKYL_GSTIN="27AAACA6783F1ZM";
	public static final String GSTIN_STATUS_URL="https://einv-apisandbox.nic.in/eivital/v1.04/Master/gstin/";
	public static final String ALKYL_USER_NAME="alkyl_maharashtra";
	public static final String ALKYL_PASSWORD="AlkylS#123";
}
