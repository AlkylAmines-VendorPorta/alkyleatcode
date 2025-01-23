INSERT INTO ad_role(ad_role_id, created, createdby, description, isactive, isadmin, name, page_access, updated, updatedby, user_level, view_name, value) 
VALUES (1, SYSDATE, null, 'SystemAdmin', 'Y', 'Y', 'System Admin', 'Y', SYSDATE, null, 'home', 'home', 'SYSADM');

INSERT INTO ad_role(ad_role_id, created, createdby, description, isactive, isadmin, name, page_access, updated, updatedby, user_level, view_name, value) 
VALUES (2, SYSDATE, null, 'Partner User', 'Y', 'Y', 'Partner User', 'Y', SYSDATE, null, 'home', 'home', 'PATUSR');

INSERT INTO ad_role(ad_role_id, created, createdby, description, isactive, isadmin, name, page_access, updated, updatedby, user_level, view_name, value) 
VALUES (3, SYSDATE, null, 'Vendor Admin', 'Y', 'Y', 'Vendor Admin', 'Y', SYSDATE, null, 'home', 'home', 'VENADM');

Insert into m_bpartner (m_bpartner_id,isactive,value, name, crn_number, pan_no, iscustomer, isvendor, created, updated) values
(1,'Y','MSEDCL','Maharashtra state electicity board','XXXXX','XXXXX','Y','Y',SYSDATE,SYSDATE );

Insert into ad_user (ad_user_id,isactive, name, email, password, created, updated, M_BPARTNER_ID) values
(1,'Y','SysUser','SysUser','SysUser',SYSDATE,SYSDATE,1); 

Insert into ad_user (ad_user_id,isactive, name, email, password, created, updated, M_BPARTNER_ID) values
(2,'Y','MSEDCLUSR','MSEDCLUSR','MSEDCLUSR',SYSDATE,SYSDATE,1 );

INSERT INTO ad_user_roles(created, createdby,  isactive, updated, updatedby
, ad_user_id, ad_role_id, ad_user_roles_id, page_path) 
VALUES (SYSDATE, 1, 'Y', SYSDATE, 1, 2, 3, 2, null);

INSERT INTO ad_user_roles(created, createdby,  isactive, updated, updatedby
, ad_user_id, ad_role_id, ad_user_roles_id, page_path) 
VALUES (SYSDATE, 1, 'Y', SYSDATE, 1, 1, 1, 1, null);

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(1,'Y','COMPANY_TYPE','Company Type',SYSDATE,SYSDATE );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(2,'Y','CONTRACTOR_TYPE','Contractor Type',SYSDATE,SYSDATE );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(3,'Y','TENDER_TYPE','Tender Type',SYSDATE,SYSDATE );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(4,'Y','AUCTION_TYPE','Auction Type',SYSDATE,SYSDATE );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(5,'Y','BID_TYPE','Bid Type',SYSDATE,SYSDATE );

INSERT INTO M_REFERENCE (M_REFERENCE_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME) VALUES 
(6, SYSDATE, 'Y', SYSDATE, 'OFFICE_TYPE', 'Office Type');

INSERT INTO M_REFERENCE (M_REFERENCE_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME) VALUES 
(7, SYSDATE, 'Y', SYSDATE, 'PRICE_BASIS', 'Basis of price');

INSERT INTO M_REFERENCE (M_REFERENCE_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME) VALUES 
(8, SYSDATE, 'Y', SYSDATE, 'BUDGET_TYPE', 'Budget Type');

INSERT INTO M_REFERENCE (M_REFERENCE_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME) VALUES 
(9, SYSDATE, 'Y', SYSDATE, 'PAYMENT_MODE', 'Payment Mode');

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(10,'Y','EXEMPTION_REG_TYPE','EXEMPTION REG TYPE',SYSDATE,SYSDATE );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(11,'Y','DEVELOPING_REGIONS','DEVELOPING REGIONS',SYSDATE,SYSDATE );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(12,'Y','OE_TYPES','OE_TYPES',SYSDATE,SYSDATE );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(13,'Y','BID_SECTION','BID_SECTION',SYSDATE,SYSDATE );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(14,'Y','TITLE','Title',SYSDATE,SYSDATE );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(15,'Y','FINANCIAL_TYPE','Financial Type',SYSDATE,SYSDATE );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(16,'Y','DOCUMENT_STATUS','Document Status',SYSDATE,SYSDATE );

Insert into M_REFERENCE (M_REFERENCE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(17,SYSDATE,'Y',SYSDATE,'RESPONSE_TYPE_CODE',null,'Deviation Type Code',null,null,null);

Insert into M_REFERENCE (M_REFERENCE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(18,SYSDATE,'Y',SYSDATE,'BIDDER_STATUS',null,'Bidder Status Code',null,null,null);

Insert into M_REFERENCE (M_REFERENCE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(19,SYSDATE,'Y',SYSDATE,'OPENING_TYPE',null,'Opening Types',null,null,null);

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(1,1,'Y','COMPANY_TYPE','PVTLTD','Private Ltd.',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(2,1,'Y','COMPANY_TYPE','GOVFRM','Goverment Firm.',SYSDATE,SYSDATE );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(3,2,'Y','CONTRACTOR_TYPE','ELE','Electrical',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(4,2,'Y','CONTRACTOR_TYPE','MEC','Mechanical',SYSDATE,SYSDATE );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(5,3,'Y','TENDER_TYPE','WT','Works Tender',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(6,3,'Y','TENDER_TYPE','PT','Procurement Tender',SYSDATE,SYSDATE );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(7,5,'Y','BID_TYPE','SB','Single Bid',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(8,5,'Y','BID_TYPE','TB','Two Bid',SYSDATE,SYSDATE );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(9,4,'Y','AUCTION_TYPE','FA','Two Bid 2 Stage',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(10,4,'Y','AUCTION_TYPE','RA','Two Bid 3 Stage',SYSDATE,SYSDATE );

INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(11, SYSDATE, 'Y', SYSDATE, 'HO', 'Ho', 'OFFICE_TYPE',6);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(12, SYSDATE, 'Y', SYSDATE, 'ZONE', 'Zone', 'OFFICE_TYPE',6);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(13, SYSDATE, 'Y', SYSDATE, 'CIRCLE', 'Circle', 'OFFICE_TYPE',6);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(14, SYSDATE, 'Y', SYSDATE, 'DIV', 'Division', 'OFFICE_TYPE',6);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(15, SYSDATE, 'Y', SYSDATE, 'SUB_DIV', 'Sub Division', 'OFFICE_TYPE',6);

INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(16, SYSDATE, 'Y', SYSDATE, 'VP', 'Variable Price', 'PRICE_BASIS',7);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(17, SYSDATE, 'Y', SYSDATE, 'FP', 'Firm Price Basis', 'PRICE_BASIS',7);

INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(18, SYSDATE, 'Y', SYSDATE, 'CAP', 'Capex', 'BUDGET_TYPE',8);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(19, SYSDATE, 'Y', SYSDATE, 'RVN', 'Revenue', 'BUDGET_TYPE',8);

INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(20, SYSDATE, 'Y', SYSDATE, 'CA', 'Cash', 'PAYMENT_MODE',9);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(21, SYSDATE, 'Y', SYSDATE, 'DD', 'DD', 'PAYMENT_MODE',9);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(22, SYSDATE, 'Y', SYSDATE, 'CH', 'Cheque', 'PAYMENT_MODE',9);


Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(23,10,'Y','EXEMPTION_REG_TYPE','MSE','MSE',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(24,10,'Y','EXEMPTION_REG_TYPE','NSIC','NSIC',SYSDATE,SYSDATE );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(25,11,'Y','DEVELOPING_REGIONS','test','test',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(26,11,'Y','DEVELOPING_REGIONS','test1','test1',SYSDATE,SYSDATE );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(27,12,'Y','OE_TYPES','DGSD','DGSD',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(28,12,'Y','OE_TYPES','DGTD','DGTD',SYSDATE,SYSDATE );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(29,13,'Y','BID_SECTION','TS','Technical Section',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(30,13,'Y','BID_SECTION','CS','Commercial Section',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(31,13,'Y','BID_SECTION','PS','Price Section',SYSDATE,SYSDATE );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(32,14,'Y','TITLE','MR.','MR.',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(33,14,'Y','TITLE','MISS','MISS',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(34,14,'Y','TITLE','MRS.','MRS.',SYSDATE,SYSDATE );


Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(35,15,'Y','FINANCIAL_TYPE','PNL','Profit and Loss',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(36,15,'Y','FINANCIAL_TYPE','BSA','Balance Sheet Account',SYSDATE,SYSDATE );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(37,15,'Y','FINANCIAL_TYPE','TD','Turnover details',SYSDATE,SYSDATE );


Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(38,16,'Y','DOCUMENT_STATUS','DR','Drafted', SYSDATE, SYSDATE ,'Pending Submition','Draft');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(39,16,'Y','DOCUMENT_STATUS','IP','In Progress', SYSDATE, SYSDATE ,'Approval Pending','Submit');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(40,16,'Y','DOCUMENT_STATUS','AP','Approved', SYSDATE, SYSDATE ,'Not Published','Approve');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(41,16,'Y','DOCUMENT_STATUS','RJ','Rejected', SYSDATE, SYSDATE ,'Rejected','Reject');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(42,16,'Y','DOCUMENT_STATUS','PU','Published', SYSDATE, SYSDATE ,'Published','Publish');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(43,16,'Y','DOCUMENT_STATUS','OP','Opened', SYSDATE, SYSDATE ,'Pending Scrutiny','Open');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(44,16,'Y','DOCUMENT_STATUS','CO','Completed', SYSDATE, SYSDATE ,'Awarded','Complete');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(45,16,'Y','DOCUMENT_STATUS','VO','Abandoned', SYSDATE, SYSDATE ,'Void','Abandon');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(46,16,'Y','DOCUMENT_STATUS','SC','Scrutinised', SYSDATE, SYSDATE ,'Preliminary Scrutiny Finished','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(47,16,'Y','DOCUMENT_STATUS','FS','Final Scrutiny', SYSDATE, SYSDATE ,'Final Scrutiny Finished','');


Insert into M_REFERENCE_LIST (M_REFERENCE_LIST_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,REFERENCE_CODE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_REFERENCE_ID) values 
(48,SYSDATE,'Y',SYSDATE,'NUMERIC',null,'Numeric','RESPONSE_TYPE_CODE',null,null,null,17);
Insert into M_REFERENCE_LIST (M_REFERENCE_LIST_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,REFERENCE_CODE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_REFERENCE_ID) values 
(49,SYSDATE,'Y',SYSDATE,'TEXT',null,'Text','RESPONSE_TYPE_CODE',null,null,null,17);
Insert into M_REFERENCE_LIST (M_REFERENCE_LIST_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,REFERENCE_CODE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_REFERENCE_ID) values 
(50,SYSDATE,'Y',SYSDATE,'YESNO',null,'Yes No','RESPONSE_TYPE_CODE',null,null,null,17);
Insert into M_REFERENCE_LIST (M_REFERENCE_LIST_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,REFERENCE_CODE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_REFERENCE_ID) values 
(51,SYSDATE,'Y',SYSDATE,'FILE',null,'File','RESPONSE_TYPE_CODE',null,null,null,17);

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(52,18,'Y','BIDDER_STATUS','PRCH','Tender Purchased', SYSDATE, SYSDATE ,'In Progress');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(53,18,'Y','BIDDER_STATUS','APPR','Purchase Approved', SYSDATE, SYSDATE ,'Bid Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(54,18,'Y','BIDDER_STATUS','RJCT','Purchase Rejected', SYSDATE, SYSDATE ,'In Progress');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(55,18,'Y','BIDDER_STATUS','SBMT','Bid Submitted', SYSDATE, SYSDATE ,'Bid Submitted');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(56,18,'Y','BIDDER_STATUS','TBSU','Technical Bid Submitted', SYSDATE, SYSDATE ,'Price Bid Submit Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(57,18,'Y','BIDDER_STATUS','PBSU','Price Bid Submitted', SYSDATE, SYSDATE ,'EMD Payment pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(58,18,'Y','BIDDER_STATUS','EMDP','EMD Paid', SYSDATE, SYSDATE ,'EMD Approval pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(59,18,'Y','BIDDER_STATUS','EMDA','EMD Approved', SYSDATE, SYSDATE ,'Bid Submitted');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(60,18,'Y','BIDDER_STATUS','EMDR','EMD Rejected', SYSDATE, SYSDATE ,'EMD Payment Rejected');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(61,18,'Y','BIDDER_STATUS','TBOP','Technical Bid Opened', SYSDATE, SYSDATE ,'Under Scrutiny');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(62,18,'Y','BIDDER_STATUS','PTBP','Preliminary Technical Bid Approved', SYSDATE, SYSDATE ,'Preliminary Commercial Scrutiny Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(63,18,'Y','BIDDER_STATUS','PCBP','Preliminary Commercial Bid Approved', SYSDATE, SYSDATE ,'Price Bid Opening Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(64,18,'Y','BIDDER_STATUS','PTBF','Preliminary Technical Bid Rejected', SYSDATE, SYSDATE ,'Preliminary Technical Scrutiny Rejected');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(65,18,'Y','BIDDER_STATUS','PCBF','Preliminary Commercial Bid Rejected', SYSDATE, SYSDATE ,'Preliminary Commercial Scrutiny Rejected');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(66,18,'Y','BIDDER_STATUS','DVTN','Deviation Called', SYSDATE, SYSDATE ,'Deviation Bid Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(67,18,'Y','BIDDER_STATUS','DBSU','Deviation Bid Submitted', SYSDATE, SYSDATE ,'Deviation Bid Opening Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(68,18,'Y','BIDDER_STATUS','DBOP','Deviation Bid Opened', SYSDATE, SYSDATE ,'Under Final Scrutiny');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(69,18,'Y','BIDDER_STATUS','FTBP','Final Technical Bid Approved', SYSDATE, SYSDATE ,'Final Commercial Scrutiny Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(70,18,'Y','BIDDER_STATUS','FCBP','Final Commercial Bid Approved', SYSDATE, SYSDATE ,'Price Bid Opening Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(71,18,'Y','BIDDER_STATUS','FTBF','Final Technical Bid Rejected', SYSDATE, SYSDATE ,'Final Technical Bid Rejected');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(72,18,'Y','BIDDER_STATUS','FCBF','Final Commercial Bid Rejected', SYSDATE, SYSDATE ,'Final Commercial Bid Rejected');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(73,18,'Y','BIDDER_STATUS','PBOP','Price Bid Opened', SYSDATE, SYSDATE ,'Price Bid Opened');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(74,18,'Y','BIDDER_STATUS','ANC1','Annexure C1 Called', SYSDATE, SYSDATE ,'Annexure C1 Bid Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(75,18,'Y','BIDDER_STATUS','C1SU','Annexure C1 Bid Submitted', SYSDATE, SYSDATE ,'Annexure C1 Bid Opening Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(76,18,'Y','BIDDER_STATUS','C1OP','Deviation Bid Opened', SYSDATE, SYSDATE ,'Winner Selection Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(77,18,'Y','BIDDER_STATUS','AW','Awarded Winner', SYSDATE, SYSDATE ,'Winner');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(78,18,'Y','BIDDER_STATUS','CO','Completed', SYSDATE, SYSDATE ,'Not Winner');

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(79,19,'Y','OPENING_TYPE','TCOP','Techno-Commercial Bid Opening', SYSDATE, SYSDATE ,null);
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(80,19,'Y','OPENING_TYPE','PBOP','Price Bid Opening', SYSDATE, SYSDATE ,null);
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(81,19,'Y','OPENING_TYPE','DBOP','Deviation Bid Opening', SYSDATE, SYSDATE ,null);
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(82,19,'Y','OPENING_TYPE','C1OP','Annexure C1 Opening', SYSDATE, SYSDATE ,null);

INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(83, SYSDATE, 'Y', SYSDATE, 'ISEXEMP', 'ISEXEMPTED', 'PAYMENT_MODE',9);

ALTER SEQUENCE ad_user_seq increment by 3;
ALTER SEQUENCE ad_role_seq increment by 4;
ALTER SEQUENCE ad_user_roles_seq increment by 3;
ALTER SEQUENCE m_bpartner_seq increment by 2;

alter table m_attachment drop column media;

alter table m_attachment add media blob;

Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (4,SYSDATE,'Y',SYSDATE,'Location Admin','Y','Location Admin','Y','home','LOCADM','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (5,SYSDATE,'Y',SYSDATE,'Finance Admin','Y','Finance Admin','Y','home','FINADM','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (6,SYSDATE,'N',SYSDATE,'Msedcl User','N','Msedcl User','Y','home','MSEBUSER','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (7,SYSDATE,'Y',SYSDATE,'Finance Operator','N','Finance Operator','Y','home','FINOPR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (8,SYSDATE,'Y',SYSDATE,'Executive Engr','N','Executive Engr','Y','home','EXEENGR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (9,SYSDATE,'Y',SYSDATE,'Planning Engr','N','Planning Engr','Y','home','PLENGR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (10,SYSDATE,'Y',SYSDATE,'Scrutiny Engr','N','Scrutiny Engr','Y','home','SCRENGR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (11,SYSDATE,'Y',SYSDATE,'Suprintending Engr','N','Suprintending Engr','Y','home','SUPENGR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (12,SYSDATE,'Y',SYSDATE,'Chief Engr','N','Chief Engr','Y','home','CHFENGR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (13,SYSDATE,'Y',SYSDATE,'Account Section','N','Account Section','Y','home','ACCSEC','dashboard',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (14,SYSDATE,'Y',SYSDATE,'Purchase Group','N','Purchase Group','Y','home','PURGRP','dashboard',null,null,null);



Insert into M_LOCATION_TYPE (M_LOCATION_TYPE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (1,to_timestamp('18-01-18 02:28:58.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-01-18 02:28:58.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'HO','HO','HO',null,null,null);
Insert into M_LOCATION_TYPE (M_LOCATION_TYPE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (2,to_timestamp('18-01-18 02:28:58.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-01-18 02:28:58.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'ZONE','ZONE','ZONE',null,null,null);
Insert into M_LOCATION_TYPE (M_LOCATION_TYPE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (3,to_timestamp('18-01-18 02:28:58.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-01-18 02:28:58.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'CIRCLE','CIRCLE','CIRCLE',null,null,null);
Insert into M_LOCATION_TYPE (M_LOCATION_TYPE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (4,to_timestamp('18-01-18 02:28:58.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-01-18 02:28:58.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'DIVISION','DIVISION','DIVISION',null,null,null);
Insert into M_LOCATION_TYPE (M_LOCATION_TYPE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (5,to_timestamp('18-01-18 02:28:58.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-01-18 02:28:58.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'SUBDIVISION','SUBDIVISION','SUBDIVISION',null,null,null);


Insert into C_LOCATION (C_LOCATION_ID,CREATED,ISACTIVE,UPDATED,ADDRESS_1,ADDRESS_2,LOCATION_TYPE,POSTAL,TO_ADDRESS,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,CITY,M_COUNTRY_ID,C_REGION_ID) values 
(1,SYSDATE,'Y',SYSDATE,'VASHI',null,'ZONE','410222',null,null,null,null,null,null,null);
Insert into C_LOCATION (C_LOCATION_ID,CREATED,ISACTIVE,UPDATED,ADDRESS_1,ADDRESS_2,LOCATION_TYPE,POSTAL,TO_ADDRESS,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,CITY,M_COUNTRY_ID,C_REGION_ID) values 
(2,SYSDATE,'Y',SYSDATE,'CHEMBUR',null,'ZONE','400067',null,null,null,null,null,null,null);
Insert into C_LOCATION (C_LOCATION_ID,CREATED,ISACTIVE,UPDATED,ADDRESS_1,ADDRESS_2,LOCATION_TYPE,POSTAL,TO_ADDRESS,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,CITY,M_COUNTRY_ID,C_REGION_ID) values 
(3,SYSDATE,'Y',SYSDATE,'KURLA',null,'HO','400010',null,null,null,null,null,null,null);
Insert into C_LOCATION (C_LOCATION_ID,CREATED,ISACTIVE,UPDATED,ADDRESS_1,ADDRESS_2,LOCATION_TYPE,POSTAL,TO_ADDRESS,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,CITY,M_COUNTRY_ID,C_REGION_ID) values 
(4,SYSDATE,'Y',SYSDATE,'ANDHERI',null,'HO','400009',null,null,null,null,null,null,null);


Insert into M_DESIGNATION (M_DESIGNATION_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(1,to_timestamp('18-01-18 02:29:29.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-01-18 02:29:29.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'A ENGR','A ENGR','A ENGR',null,null,null);
Insert into M_DESIGNATION (M_DESIGNATION_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(2,to_timestamp('18-01-18 02:29:29.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-01-18 02:29:29.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'A PROGMR','A PROGMR','A PROGMR',null,null,null);
Insert into M_DESIGNATION (M_DESIGNATION_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(3,to_timestamp('18-01-18 02:29:29.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-01-18 02:29:29.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'S.O','S.O','S.O',null,null,null);
Insert into M_DESIGNATION (M_DESIGNATION_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values
(4,to_timestamp('18-01-18 02:29:29.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-01-18 02:29:29.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'MD','MD','MD',null,null,null);


Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (1,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Dashboard','Dashboard','Y',0,'Dashboard',0,1,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (2,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Master','Master','Y',0,'Master',0,2,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (3,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Tenders','Tenders','Y',0,'Tenders',0,3,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (4,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Auction','Auction','Y',0,'Auction',0,4,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (5,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Bids','Bids','Y',0,'Bids',0,5,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (6,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Payments','Payments','Y',0,'Payments',0,6,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (7,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Reports','Reports','Y',0,'Reports',0,7,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (8,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'PurchaseProposal','Purchase Proposal','Y',0,'Purchase Proposal',0,8,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (9,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Mail','Mail','Y',0,'Mail',0,9,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (10,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'RoleDetails','Role Details','Y',0,'Role Details',0,10,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (11,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Dashboard','Dashboard','N',1,'Dashboard',1,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (12,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'AddUser','Add User','N',1,'Add User',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (13,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TendersPreparation','Tenders Preparation','N',1,'Tenders Preparation',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (14,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'AuctionPreparation','Auction Preparation','N',1,'Auction Preparation',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (15,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Bids','Bids','N',1,'Bids',5,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (16,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Payments','Payments','N',1,'Payments',6,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (17,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Reports','Reports','N',1,'Reports',7,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (18,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'PurchaseProposal','PurchaseProposal','N',1,'PurchaseProposal',8,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (19,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Mail','Mail','N',1,'Mail',9,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (20,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'RoleAccess','RoleAccess','N',1,'RoleAccess',10,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (21,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'UOM','UOM','N',1,'UOM',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (22,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'GTPParameter','GTP Parameter','N',1,'GTP Parameter',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (23,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'GTPParameterType','GTP ParameterType','N',1,'GTP ParameterType',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (24,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'BidType','Bid Type','N',1,'Bid Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (25,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'CompanyType','Company Type','N',1,'Company Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (26,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'ContractorType','Contractor Type','N',1,'Contractor Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (27,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Department','Department','N',1,'Department',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (28,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'DocumentType','Document Type','N',1,'Document Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (29,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'ExemptedCategories','Exempted Categories','N',1,'Exempted Categories',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (30,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'FinancialDocuments','Financial Documents','N',1,'Financial Documents',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (31,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'HolderType','Holder Type','N',1,'Holder Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (32,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Designation','Designation','N',1,'Designation',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (33,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Role','Role','N',1,'Role',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (34,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderType','Tender Type','N',1,'Tender Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (35,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'IsStandard','Is Standard','N',1,'Is Standard',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (36,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Material','Material','N',1,'Material',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (37,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'MaterialGroup','Material Group','N',1,'Material Group',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (38,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'MaterialSubGroup','Material SubGroup','N',1,'Material SubGroup',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (39,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'MaterialSpecification','Material Specification','N',1,'Material Specification',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (40,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'MaterialType','Material Type','N',1,'Material Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (41,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'MaterialVersion','Material Version','N',1,'Material Version',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (42,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'PaymentType','Payment Type','N',1,'Payment Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (43,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'PublicNotices','Public Notices','N',1,'Public Notices',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (44,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'RegistrationType','Registration Type','N',1,'Registration Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (45,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderBudgetType','Tender Budget Type','N',1,'Tender Budget Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (46,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'VendorType','Vendor Type','N',1,'Vendor Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (47,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TaxCategory','Tax Category','N',1,'Tax Category',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (48,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Tax','Tax','N',1,'Tax',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (49,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderPublishing','Tender Publishing','N',1,'Tender Publishing',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (50,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderScheduling','Tender Scheduling','N',1,'Tender Scheduling',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (51,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderPurchase','Tender Purchase','N',1,'Tender Purchase',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (52,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderDetails','Tender Details','N',1,'Tender Details',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (53,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderOpening','Tender Opening','N',1,'Tender Opening',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (54,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderCommercialScrutiny','Tender Commercial Scrutiny','N',1,'Tender Commercial Scrutiny',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (55,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderTechnicalScrutiny','Tender Technical Scrutiny','N',1,'Tender Technical Scrutiny',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (56,to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:12.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'AuctionPreparation','Auction Preparation','N',1,'Auction Preparation',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (57,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'AuctionPublishing','Auction Publishing','N',1,'Auction Publishing',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (58,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'AuctionScheduling','Auction Scheduling','N',1,'Auction Scheduling',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (59,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'AuctionPurchase','Auction Purchase','N',1,'Auction Purchase',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (60,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'AuctionDetails','Auction Details','N',1,'Auction Details',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (61,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'AuctionOpening','Auction Opening','N',1,'Auction Opening',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (62,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'AuctionScrutiny','Auction Scrutiny','N',1,'Auction Scrutiny',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (63,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'BusinessPartnerGroup','Business Partner Group','N',1,'Business Partner Group',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (64,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Registration','Registration','Y',0,'Registration',0,11,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (65,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Utilities','Utilities','Y',0,'Utilities',0,12,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (66,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Certificate','Certificate','Y',0,'Certificate',0,13,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (67,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Registration','Registration','N',1,'Registration',64,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (68,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Utilities','Utilities','N',1,'Utilities',65,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (69,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Certificate','Certificate','N',1,'Certificate',66,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (70,to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:13.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderSubmission','Tender Submission','N',1,'Tender Submission',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (71,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'AnnexureC1','Annexure C1','N',1,'Annexure C1',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (72,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'PaymentApproval','Payment Approval','Y',0,'Payment Approval',0,14,null,null,null);

Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (73,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'PaymentApproval','Payment Approval','N',1,'Payment Approval',72,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (74,to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:11.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'TenderCommittee','Tender Committee','N',1,'Tender Committee',3,0,null,null,null);

Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (1,to_timestamp('20-01-18 08:55:34.716000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:55:34.716000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (2,to_timestamp('20-01-18 08:56:48.491000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.491000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,63);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (3,to_timestamp('20-01-18 08:56:48.502000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.502000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y','Y','Y',null,null,null,1,12);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (4,to_timestamp('20-01-18 08:56:48.514000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.514000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,48);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (5,to_timestamp('20-01-18 08:56:48.522000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.522000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,46);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (6,to_timestamp('20-01-18 08:56:48.530000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.530000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,47);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (7,to_timestamp('20-01-18 08:56:48.538000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.538000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,45);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (8,to_timestamp('20-01-18 08:56:48.550000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.550000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,44);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (9,to_timestamp('20-01-18 08:56:48.562000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.562000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y','Y','Y',null,null,null,1,43);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (10,to_timestamp('20-01-18 08:56:48.570000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.570000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,42);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (11,to_timestamp('20-01-18 08:56:48.578000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.578000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,41);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (12,to_timestamp('20-01-18 08:56:48.586000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.586000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,40);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (13,to_timestamp('20-01-18 08:56:48.590000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.590000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,39);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (14,to_timestamp('20-01-18 08:56:48.598000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.598000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,38);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (15,to_timestamp('20-01-18 08:56:48.610000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.610000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,37);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (16,to_timestamp('20-01-18 08:56:48.634000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.634000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,36);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (17,to_timestamp('20-01-18 08:56:48.642000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.642000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,35);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (18,to_timestamp('20-01-18 08:56:48.662000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.662000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,34);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (19,to_timestamp('20-01-18 08:56:48.674000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.674000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,33);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (20,to_timestamp('20-01-18 08:56:48.682000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.682000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,32);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (21,to_timestamp('20-01-18 08:56:48.690000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.690000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,31);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (22,to_timestamp('20-01-18 08:56:48.698000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.698000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,30);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (23,to_timestamp('20-01-18 08:56:48.706000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.706000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y','Y','Y',null,null,null,1,29);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (24,to_timestamp('20-01-18 08:56:48.714000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.714000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,21);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (25,to_timestamp('20-01-18 08:56:48.722000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.722000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y','Y','Y',null,null,null,1,22);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (26,to_timestamp('20-01-18 08:56:48.726000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.726000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y','Y','Y',null,null,null,1,23);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (27,to_timestamp('20-01-18 08:56:48.734000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.734000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,24);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (28,to_timestamp('20-01-18 08:56:48.738000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.738000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,25);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (29,to_timestamp('20-01-18 08:56:48.746000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.746000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,26);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (30,to_timestamp('20-01-18 08:56:48.750000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.750000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,27);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (31,to_timestamp('20-01-18 08:56:48.758000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:56:48.758000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,28);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (32,to_timestamp('20-01-18 08:57:20.062000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:02:05.659000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,55);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (33,to_timestamp('20-01-18 08:57:20.067000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:57:20.067000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,13);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (34,to_timestamp('20-01-18 08:57:20.075000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:57:20.075000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,49);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (35,to_timestamp('20-01-18 08:57:20.079000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:57:20.079000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,50);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (36,to_timestamp('20-01-18 08:57:20.087000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:57:20.087000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,51);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (37,to_timestamp('20-01-18 08:57:20.091000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:02:16.868000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,54);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (38,to_timestamp('20-01-18 08:57:20.099000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:57:20.099000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,53);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (39,to_timestamp('20-01-18 08:57:20.103000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:57:20.103000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,52);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (40,to_timestamp('20-01-18 08:59:21.937000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:21.937000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,61);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (41,to_timestamp('20-01-18 08:59:21.945000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:21.945000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,62);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (42,to_timestamp('20-01-18 08:59:21.957000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:21.957000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,57);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (43,to_timestamp('20-01-18 08:59:21.961000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:21.961000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,58);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (44,to_timestamp('20-01-18 08:59:21.965000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:21.965000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,60);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (45,to_timestamp('20-01-18 08:59:21.973000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:21.973000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,59);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (46,to_timestamp('20-01-18 08:59:21.977000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:21.977000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,14);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (47,to_timestamp('20-01-18 08:59:27.529000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:27.529000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,15);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (48,to_timestamp('20-01-18 08:59:33.469000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:33.469000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,16);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (49,to_timestamp('20-01-18 08:59:42.654000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:42.654000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,17);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (50,to_timestamp('20-01-18 08:59:49.001000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:49.001000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,18);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (51,to_timestamp('20-01-18 08:59:55.220000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 08:59:55.220000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,19);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (52,to_timestamp('20-01-18 09:00:01.153000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:00:01.153000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,20);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (53,to_timestamp('20-01-18 09:33:09.210000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:33:09.210000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y','Y','Y',null,null,null,4,12);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (54,to_timestamp('20-01-18 09:33:42.883000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:33:42.883000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,4,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (55,to_timestamp('20-01-18 09:39:26.396000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:39:26.396000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,5,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (56,to_timestamp('20-01-18 09:39:37.166000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:39:37.166000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y','Y','Y',null,null,null,5,16);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (57,to_timestamp('20-01-18 10:10:25.073000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 10:10:25.073000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,3,15);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (58,to_timestamp('20-01-18 10:10:36.932000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 10:10:36.932000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,3,19);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (59,to_timestamp('20-01-18 10:20:39.791000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 11:11:03.388000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,2,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (60,to_timestamp('21-01-18 12:04:21.538000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 12:04:21.538000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,4,20);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (61,to_timestamp('20-01-18 09:43:22.967000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:43:22.967000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y','Y','Y',null,null,null,7,16);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (62,to_timestamp('20-01-18 09:44:01.797000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:44:01.797000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,8,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (63,to_timestamp('20-01-18 09:45:23.246000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:45:23.246000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,8,13);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (64,to_timestamp('20-01-18 09:45:23.252000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:45:23.252000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,8,49);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (65,to_timestamp('20-01-18 09:45:23.256000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:45:23.256000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,8,50);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (66,to_timestamp('20-01-18 09:45:23.260000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:45:23.260000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,8,54);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (67,to_timestamp('20-01-18 09:45:23.264000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:45:23.264000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,8,53);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (68,to_timestamp('20-01-18 09:45:23.268000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:45:23.268000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,8,52);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (69,to_timestamp('20-01-18 09:45:58.305000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:45:58.305000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,9,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (70,to_timestamp('20-01-18 09:46:14.002000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:46:14.002000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y','Y','Y',null,null,null,9,22);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (71,to_timestamp('20-01-18 09:46:14.007000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:46:14.007000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,9,41);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (72,to_timestamp('20-01-18 09:46:49.705000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:46:49.705000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,10,55);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (73,to_timestamp('20-01-18 09:46:55.540000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 09:46:55.540000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,10,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (74,to_timestamp('20-01-18 10:10:01.888000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 10:10:01.888000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,3,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (75,to_timestamp('20-01-18 10:10:17.114000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('20-01-18 10:10:17.114000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,3,52);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (76,to_timestamp('29-01-18 11:23:14.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:23:14.000000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,3,51);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (77,to_timestamp('29-01-18 11:46:25.611000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:46:25.611000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,69);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (78,to_timestamp('29-01-18 11:46:31.247000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:46:31.247000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,68);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (79,to_timestamp('29-01-18 11:46:37.085000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 11:46:37.085000000 AM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,67);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (80,to_timestamp('29-01-18 03:21:10.701000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 03:21:10.701000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,3,70);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (81,to_timestamp('29-01-18 03:21:10.701000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 03:21:10.701000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,1,70);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (82,to_timestamp('29-01-18 03:21:10.701000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('29-01-18 03:21:10.701000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y','Y','Y',null,null,null,1,71);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (83,to_timestamp('26-02-18 02:15:26.020000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('26-02-18 02:15:26.020000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,13,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (84,to_timestamp('26-02-18 02:15:51.437000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('26-02-18 02:15:51.437000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,13,67);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (85,to_timestamp('26-02-18 02:16:18.529000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('26-02-18 02:16:18.529000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,14,67);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (86,to_timestamp('26-02-18 02:16:28.694000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('26-02-18 02:16:28.694000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,14,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (88,to_timestamp('06-03-18 12:35:52.975000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('06-03-18 12:35:52.975000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,12,13);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (89,to_timestamp('06-03-18 12:35:52.975000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('06-03-18 12:35:52.975000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,7,73);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (90,to_timestamp('06-03-18 12:35:52.975000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('06-03-18 12:35:52.975000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,5,73);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (91,to_timestamp('06-03-18 12:35:52.975000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('06-03-18 12:35:52.975000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,8,67);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (92,to_timestamp('06-03-18 12:35:52.975000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('06-03-18 12:35:52.975000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'N','N','Y',null,null,null,12,67);

INSERT INTO M_TENDER_TYPE (M_TENDER_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'LIMITED', 'LIMITED', 'LIMITED');

INSERT INTO M_TENDER_TYPE (M_TENDER_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'OPEN', 'OPEN', 'OPEN');

INSERT INTO M_TENDER_BUDGET_TYPE (M_TENDER_BUDGET_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'CAPEX', 'CAPEX', 'CAPEX');

INSERT INTO M_TENDER_BUDGET_TYPE (M_TENDER_BUDGET_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'REVENUE', 'REVENUE', 'REVENUE');


INSERT INTO M_CONTRACTOR_TYPE (M_CONTRACTOR_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'CIVIL', 'CIVIL', 'CIVIL');

INSERT INTO M_CONTRACTOR_TYPE (M_CONTRACTOR_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'ELECTRICAL', 'ELECTRICAL', 'ELECTRICAL');


INSERT INTO M_COMPANY_TYPE (M_COMPANY_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',SYSDATE, 'Y', SYSDATE, 'Partnership Firm', 'Partnership Firm', 'Partnership Firm');

INSERT INTO M_COMPANY_TYPE (M_COMPANY_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'Private Limited', 'Private Limited', 'Private Limited');

INSERT INTO M_COMPANY_TYPE (M_COMPANY_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'UDYOG AADHAAR', 'UDYOG AADHAAR', 'UDYOG AADHAAR');

INSERT INTO M_COMPANY_TYPE (M_COMPANY_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4', SYSDATE, 'Y', SYSDATE, 'Public Limited', 'Public Limited', 'Public Limited');

INSERT INTO M_COMPANY_TYPE (M_COMPANY_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('5', SYSDATE, 'Y', SYSDATE, 'Proprietary Firm', 'Proprietary Firm', 'Proprietary Firm');


INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'Abrasives', 'Abrasives', 'Abrasives');

INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'Appliances', 'Appliances', 'Appliances');

INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'Boiler', 'Boiler', 'Boiler');

INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4', SYSDATE, 'Y', SYSDATE, 'Gas Turbines', 'Gas Turbines', 'Gas Turbines');

INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('5', SYSDATE, 'Y', SYSDATE, 'TRANSFORMER(Power and Regulators)', 'TRANSFORMER(Power and Regulators)', 'TRANSFORMER(Power and Regulators)');

INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('6', SYSDATE, 'Y', SYSDATE, 'Works(Contractor)', 'Works(Contractor)','Works(Contractor)');


INSERT INTO M_DEPARTMENT (M_DEPARTMENT_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'Common', 'Common', 'Common');

INSERT INTO M_DEPARTMENT (M_DEPARTMENT_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'Generation', 'Generation', 'Generation');

INSERT INTO M_DEPARTMENT (M_DEPARTMENT_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'Transmission', 'Transmission', 'Transmission');

INSERT INTO M_DEPARTMENT (M_DEPARTMENT_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4', SYSDATE, 'Y', SYSDATE, 'Distribution', 'Distribution', 'Distribution');

INSERT INTO M_DEPARTMENT (M_DEPARTMENT_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('5', SYSDATE, 'Y', SYSDATE, 'IT Director', 'IT Director', 'IT Director');


INSERT INTO M_CHARGE (M_CHARGE_ID, CREATED, ISACTIVE, UPDATED, AMOUNT, GST, VALUE, DESCRIPTION, ISREFUNDABLE, NAME, PERIOD_TYPE) VALUES ('1', SYSDATE, 'Y', SYSDATE, '25000', '18', 'RG', 'Registration Fee', 'N', 'Registration Fee','Y');

INSERT INTO M_CHARGE (M_CHARGE_ID, CREATED, ISACTIVE, UPDATED, AMOUNT, GST, VALUE, DESCRIPTION, ISREFUNDABLE, NAME, PERIOD_TYPE) VALUES ('2', SYSDATE, 'Y', SYSDATE, '2500', '18', 'RN', 'Registration Renewal Fee', 'N', 'Registration Renewal Fee', 'Y');

INSERT INTO M_CHARGE (M_CHARGE_ID, CREATED, ISACTIVE, UPDATED, AMOUNT, GST, VALUE, DESCRIPTION, ISREFUNDABLE, NAME, PERIOD_TYPE) VALUES ('3', SYSDATE, 'Y', SYSDATE, '', '18', 'TF', 'Tender Fee', 'N', 'Tender Fee', 'N');

INSERT INTO M_CHARGE (M_CHARGE_ID, CREATED, ISACTIVE, UPDATED, AMOUNT, GST, VALUE, DESCRIPTION, ISREFUNDABLE, NAME, PERIOD_TYPE) VALUES ('4', SYSDATE, 'Y', SYSDATE, '', '18', 'EMD', 'EMD', 'Y', 'EMD', 'N');


INSERT INTO M_REGISTRATION_TYPE (M_REGISTRATION_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'Provisional', 'Provisional', 'Provisional');

INSERT INTO M_REGISTRATION_TYPE (M_REGISTRATION_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'Permanent', 'Permanent', 'Permanent');


INSERT INTO M_BPARTNER_GROUP (M_BPARTNER_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'CUSTOMERS', 'CUSTOMERS', 'CUSTOMERS');

INSERT INTO M_BPARTNER_GROUP (M_BPARTNER_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'VENDORS', 'VENDORS', 'VENDORS');

INSERT INTO M_BPARTNER_GROUP (M_BPARTNER_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'CONTRACTORS', 'CONTRACTORS', 'CONTRACTORS');


INSERT INTO M_VENDOR_TYPE (M_VENDOR_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'MANUFACTURER', 'MANUFACTURER', 'MANUFACTURER');

INSERT INTO M_VENDOR_TYPE (M_VENDOR_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'TRADER', 'TRADER', 'TRADER');


INSERT INTO M_HOLDERTYPE (M_HOLDERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'DIGITAL SIGNATORY', 'DIGITAL SIGNATORY', 'DIGITAL SIGNATORY');

INSERT INTO M_HOLDERTYPE (M_HOLDERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'POWER OF ATTORNEY', 'POWER OF ATTORNEY', 'POWER OF ATTORNEY');

INSERT INTO M_HOLDERTYPE (M_HOLDERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'TENDER SIGNATORY', 'TENDER SIGNATORY', 'TENDER SIGNATORY');


INSERT INTO M_EXEMPTED_CATEGORIES (M_EXEMPTED_CATEGORIES_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'MSE', 'MSE', 'MSE');

INSERT INTO M_EXEMPTED_CATEGORIES (M_EXEMPTED_CATEGORIES_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'UDYOG AADHAAR', 'UDYOG AADHAAR', 'UDYOG AADHAAR');

INSERT INTO M_EXEMPTED_CATEGORIES (M_EXEMPTED_CATEGORIES_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'GOVT Firms', 'GOVT Firms', 'GOVT Firms');

INSERT INTO M_EXEMPTED_CATEGORIES (M_EXEMPTED_CATEGORIES_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4', SYSDATE, 'Y', SYSDATE, 'STARTUP[Only MAHARASHTRA]', 'STARTUP[Only MAHARASHTRA]', 'STARTUP[Only MAHARASHTRA]');

INSERT INTO M_EXEMPTED_CATEGORIES (M_EXEMPTED_CATEGORIES_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('5', SYSDATE, 'Y', SYSDATE, 'New Units Other than Startup[PS and TP Certified]', 'New Units Other than Startup[PS and TP Certified]', 'New Units Other than Startup[PS and TP Certified]');

ALTER SEQUENCE m_exempted_categories_seq increment by 6;

INSERT INTO M_DOCUMENTTYPE (M_DOCUMENTTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',SYSDATE, 'Y',SYSDATE, 'ST 1', 'ST 1', 'ST 1');

INSERT INTO M_DOCUMENTTYPE (M_DOCUMENTTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',SYSDATE, 'Y',SYSDATE, 'CM 1', 'CM 1', 'CM 1');

INSERT INTO M_DOCUMENTTYPE (M_DOCUMENTTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',SYSDATE, 'Y',SYSDATE, 'ST 2', 'ST 2', 'ST 2');


INSERT INTO M_TAX_CATEGORY (M_TAX_CATEGORY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'GST 5%', 'GST 5%', 'GST 5%');

INSERT INTO M_TAX_CATEGORY (M_TAX_CATEGORY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'GST  18%', 'GST  18%', 'GST  18%');

INSERT INTO M_TAX_CATEGORY (M_TAX_CATEGORY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'GST 28%', 'GST 28%', 'GST 28%');


INSERT INTO M_BIDTYPE (M_BIDTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'TWO BID One Stage', 'TWO BID One Stage', 'TWO BID One Stage');

INSERT INTO M_BIDTYPE (M_BIDTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'TWO BID Two Stage', 'TWO BID Two Stage', 'TWO BID Two Stage');


INSERT INTO M_UOM (M_UOM_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'Un', 'Unit', 'Unit');

INSERT INTO M_UOM (M_UOM_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'ft', 'Feets', 'Feets');

INSERT INTO M_UOM (M_UOM_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'm', 'Metre', 'Metre');

INSERT INTO M_UOM (M_UOM_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4', SYSDATE, 'Y', SYSDATE, 'mm', 'MiliMetre', 'MiliMetre');


INSERT INTO M_PUBLIC_NOTICE (M_PUBLIC_NOTICE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, MATTER, NAME, PUBLISHING_DATE, SALE_CLOSING_DATE, SALE_OPENING_DATE, TITLE) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'Advertisment 01-2018', 'Advertisment 01-2018', 'Matter 1', 'Advertisment 01-2018', SYSDATE, SYSDATE, SYSDATE, 'Title 1');

INSERT INTO M_PUBLIC_NOTICE (M_PUBLIC_NOTICE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, MATTER, NAME, PUBLISHING_DATE, SALE_CLOSING_DATE, SALE_OPENING_DATE, TITLE) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'Advertisment 02-2018', 'Advertisment 02-2018', 'Matter 2', 'Advertisment 02-2018', SYSDATE, SYSDATE, SYSDATE, 'Title 2');

INSERT INTO M_PUBLIC_NOTICE (M_PUBLIC_NOTICE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, MATTER, NAME, PUBLISHING_DATE, SALE_CLOSING_DATE, SALE_OPENING_DATE, TITLE) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'Advertisment 03-2018', 'Advertisment 03-2018', 'Matter 3', 'Advertisment 03-2018', SYSDATE, SYSDATE, SYSDATE, 'Title 3');

INSERT INTO M_PUBLIC_NOTICE (M_PUBLIC_NOTICE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, MATTER, NAME, PUBLISHING_DATE, SALE_CLOSING_DATE, SALE_OPENING_DATE, TITLE) VALUES ('4', SYSDATE, 'Y', SYSDATE, 'Advertisment 04-2018', 'Advertisment 04-2018', 'Matter 4', 'Advertisment 04-2018', SYSDATE, SYSDATE, SYSDATE, 'Title 4');


INSERT INTO M_FINANCIAL_DOC (M_FINANCIAL_DOC_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'Financial Doc 1', 'Financial Doc 1', 'Financial Doc 1');

INSERT INTO M_FINANCIAL_DOC (M_FINANCIAL_DOC_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'Financial Doc 2', 'Financial Doc 2', 'Financial Doc 2');

INSERT INTO M_FINANCIAL_DOC (M_FINANCIAL_DOC_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'Financial Doc 3', 'Financial Doc 3', 'Financial Doc 3');


INSERT INTO M_GTP_PARAMETERTYPE (M_GTP_PARAMETERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'TEXT', 'Text', 'Text');

INSERT INTO M_GTP_PARAMETERTYPE (M_GTP_PARAMETERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'BOOL', 'Boolean', 'Boolean');

INSERT INTO M_GTP_PARAMETERTYPE (M_GTP_PARAMETERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'NUMR', 'Numeric', 'Numeric');
INSERT INTO M_GTP_PARAMETERTYPE (M_GTP_PARAMETERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4', SYSDATE, 'Y', SYSDATE, 'FILE', 'File', 'File');

INSERT INTO M_TAX (M_TAX_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_TAX_CATEGORY_ID) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'IGST', 'IGST', 'IGST', '1');
INSERT INTO M_TAX (M_TAX_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_TAX_CATEGORY_ID) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'SGST', 'SGST', 'SGST', '1');


INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) VALUES ('1', SYSDATE  , 'Y', SYSDATE  , 'Spares for 210MW BHEL', 'Spares for 210MW BHEL', 'Spares for 210MW BHEL', '3');

INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) VALUES ('2', SYSDATE  , 'Y', SYSDATE  , 'Spares for CTPS (1*500MW BHEL)', 'Spares for CTPS (1*500MW BHEL)', 'Spares for CTPS (1*500MW BHEL)', '3');

INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) VALUES ('3', SYSDATE  , 'Y', SYSDATE  , 'Spares for CTPS(210MW A.V.B)', 'Spares for CTPS(210MW A.V.B)', 'Spares for CTPS(210MW A.V.B)', '3');

INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) VALUES ('4', SYSDATE  , 'Y', SYSDATE  , 'Super Heater', 'Super Heater', 'Super Heater', '3');

INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) 
VALUES ('5', SYSDATE  , 'Y', SYSDATE  , 'Spares for CTPS (2*500MW BHEL)', 'Spares for CTPS (2*500MW BHEL)', 'Spares for CTPS (2*500MW BHEL)', '3');

INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) 
VALUES ('6', SYSDATE  , 'Y', SYSDATE  , 'Services', 'Services', 'Services', '6');



INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('8', SYSDATE, 'Y', SYSDATE, 'Lead acid st.battary 30v 60 AH', 'Lead acid st.battary 30v 60 AH', '1', 'Y', 'N', 'N', 'Y', 'Battary 30v 60H', 'Lead acid st.battary 30v 60 AH', 'I', '3', '3', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('9', SYSDATE, 'Y', SYSDATE, '110V.100AH,LA.St.Battery55Cell', '110V.100AH,LA.St.Battery55Cell', '2', 'N', 'Y', 'N', 'Y', 'LA.St.Battery55Cell', '110V.100AH,LA.St.Battery55Cell', 'I', '3', '3', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('10', SYSDATE, 'Y', SYSDATE, 'Battery 150 AH 12 volts', 'Battery 150 AH 12 volts', '5', 'Y', 'Y', 'N', 'Y', 'Battery 150 AH', 'Battery 150 AH 12 volts', 'I', '3', '2', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('11', SYSDATE, 'Y', SYSDATE, 'DC Batteries (12V, 200 AH)', 'DC Batteries (12V, 200 AH)', '4', 'Y', 'Y', 'Y', 'Y', 'DC Batteries', 'DC Batteries (12V, 200 AH)', 'I', '3', '1', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('12', SYSDATE, 'Y', SYSDATE, 'LT C. XLPE 3.5C x 35 Sq mm', 'LT C. XLPE 3.5C x 35 Sq mm', '3', 'N', 'N', 'Y', 'N', 'LT C. XLPE', 'LT C. XLPE 3.5C x 35 Sq mm', 'I', '3', '1', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('13', SYSDATE, 'Y', SYSDATE, 'Building Maintainence', 'Building Maintainence','2', 'Y', 'N', 'N', 'Y', 'Building Maintainence', 'Building Maintainence', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('14', SYSDATE, 'Y', SYSDATE, 'Electrification', 'Electrification', '5','Y', 'N', 'N', 'Y', 'Electrification', 'Electrification', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('15', SYSDATE, 'Y', SYSDATE, 'Road Maintainence', 'Road Maintainence','6', 'Y', 'N', 'N', 'Y', 'Road Maintainence', 'Road Maintainence', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID,ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID)
 VALUES ('16', SYSDATE, 'Y', SYSDATE, 'Drainage Re-Modelling', 'Drainage Re-Modelling','7', 'Y', 'N', 'N', 'Y', 'Drainage Re-Modelling', 'Drainage Re-Modelling', 'S', '6', '6', '1');
 
INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('17', SYSDATE, 'Y', SYSDATE, 'Flyover Construction', 'Flyover Construction','1', 'Y', 'N', 'N', 'Y', 'Flyover Construction', 'Flyover Construction', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('18', SYSDATE, 'Y', SYSDATE, 'Sanitary Materials', 'Sanitary Materials','2', 'Y', 'N', 'N', 'Y', 'Sanitary Materials', 'Sanitary Materials', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('19', SYSDATE, 'Y', SYSDATE, 'Plumbing Materials', 'Plumbing Materials','3', 'Y', 'N', 'N', 'Y', 'Plumbing Materials', 'Plumbing Materials', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('20', SYSDATE, 'Y', SYSDATE, 'WaterProofing Materials', 'WaterProofing Materials','8', 'Y', 'N', 'N', 'Y', 'WaterProofing Materials', 'WaterProofing Materials', 'S', '6', '6', '1');



INSERT INTO M_MATERIAL_SPECIFICATION (M_MATERIAL_SPECIFICATION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, QUANTITY, SPECIFICATION, M_MATERIAL_ID) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'Spec 1', 'Spec 1', 'Spec 1', '120', 'Specification 1', '1');
INSERT INTO M_MATERIAL_SPECIFICATION (M_MATERIAL_SPECIFICATION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, QUANTITY, SPECIFICATION, M_MATERIAL_ID) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'Spec 2', 'Spec 2', 'Spec 2', '180', 'Specification 2', '2');
INSERT INTO M_MATERIAL_SPECIFICATION (M_MATERIAL_SPECIFICATION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, QUANTITY, SPECIFICATION, M_MATERIAL_ID) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'Spec 3', 'Spec 3', 'Spec 3', '210', 'Specification 3', '3');

INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('1', SYSDATE, 'Y', SYSDATE, '1', 'VESRION 1', 'VESRION 1', '1');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('2', SYSDATE, 'Y', SYSDATE, '2', 'VERSION 2', 'VERSION 2', '1');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('3', SYSDATE, 'Y', SYSDATE, '3', 'VERSION 3', 'VERSION 3', '1');

INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('4', SYSDATE, 'Y', SYSDATE, '1', 'VESRION 1', 'VESRION 1', '2');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('5', SYSDATE, 'Y', SYSDATE, '2', 'VERSION 2', 'VERSION 2', '2');

INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('6', SYSDATE, 'Y', SYSDATE, '1', 'VESRION 1', 'VESRION 1', '3');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('7', SYSDATE, 'Y', SYSDATE, '2', 'VERSION 2', 'VERSION 2', '3');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('8', SYSDATE, 'Y', SYSDATE, '3', 'VERSION 3', 'VERSION 3', '3');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('9', SYSDATE, 'Y', SYSDATE, '3', 'VERSION 3', 'VERSION 3', '3');

INSERT INTO M_GTP_PARAMETER (M_GTP_PARAMETER_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_GTP_PARAMETERTYPE_ID) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'Gtp 1', 'Gtp 1', 'Gtp 1', '1');
INSERT INTO M_GTP_PARAMETER (M_GTP_PARAMETER_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_GTP_PARAMETERTYPE_ID) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'Gtp 2', 'Gtp 2', 'Gtp 2', '1');

INSERT INTO M_ISSTD(M_ISSTD_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('1', SYSDATE, 'Y', SYSDATE, 'Standard A', 'Standard A', 'Standard A', '1');
INSERT INTO M_ISSTD(M_ISSTD_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('2', SYSDATE, 'Y', SYSDATE, 'Standard B', 'Standard B', 'Standard B', '1');
INSERT INTO M_ISSTD(M_ISSTD_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('3', SYSDATE, 'Y', SYSDATE, 'Standard C', 'Standard C', 'Standard C', '1');

ALTER SEQUENCE m_gtp_parameter_seq increment by 5;
ALTER SEQUENCE m_gtp_parametertype_seq increment by 5;
ALTER SEQUENCE m_public_notice_seq increment by 6;
ALTER SEQUENCE m_exempted_categories_seq increment by 6;

ALTER TABLE ad_role_access_master
   ADD CONSTRAINT roleAccess_Constraint UNIQUE(ad_role_id,m_tile_id);
   
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (1,to_timestamp('21-01-18 01:11:04.906000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 01:11:04.906000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,'Location','Admin','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (2,to_timestamp('21-01-18 01:12:27.217000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 01:12:27.217000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,'Location','Admin','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (3,to_timestamp('21-01-18 03:49:01.045000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:22:50.350000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,'Finance','Operator','HO','N',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (4,to_timestamp('21-01-18 07:13:42.085000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:13:42.085000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,'Finance','Admin','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (5,to_timestamp('21-01-18 07:14:40.012000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:14:40.012000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,'Executive','Engr','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (6,to_timestamp('21-01-18 07:15:17.639000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:15:17.639000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,'Planning','Engr','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (7,to_timestamp('21-01-18 07:15:56.235000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:15:56.235000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,'Scrutiny','Engr','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (8,to_timestamp('21-01-18 07:16:42.582000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:16:42.582000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,'Superintending','Engr','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (9,SYSDATE,'Y',SYSDATE,null,null,null,'Chief','Engr','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (10,to_timestamp('22-02-18 04:28:41.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('22-02-18 04:28:41.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,'Account','Section','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (11,to_timestamp('22-02-18 04:28:41.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('22-02-18 04:28:41.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,'Purchase','Group','HO','M',null,null,null,null,null,null,null,null,1,1,1);



Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (5,to_timestamp('21-01-18 01:12:27.232000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 01:12:27.232000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'locationadmin@test.com','Location','password',null,1,null,2);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (9,to_timestamp('21-01-18 03:49:01.273000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:22:50.375000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'financeoperator@test.com','Finance','password',null,1,null,3);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (13,to_timestamp('21-01-18 07:13:42.262000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:13:42.262000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'financeadmin@test.com','Finance','password',null,1,null,4);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (17,to_timestamp('21-01-18 07:14:40.031000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:14:40.031000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'executiveengr@test.com','Executive','password',null,1,null,5);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (21,to_timestamp('21-01-18 07:15:17.655000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:15:17.655000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'planningengr@test.com','Planning','password',null,1,null,6);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (25,to_timestamp('21-01-18 07:15:56.253000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:15:56.253000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'scrutinyengr@test.com','Scrutiny','password',null,1,null,7);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (29,to_timestamp('21-01-18 07:16:42.595000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:16:42.595000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'suprintendingengr@test.com','Superintending','password',null,1,null,8);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (33,to_timestamp('21-01-18 07:20:51.334000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:20:51.334000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'chiefengr@test.com','Chief','password',null,1,null,9);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (35,to_timestamp('21-01-18 07:14:40.031000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:14:40.031000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'accountsection@test.com','Account','password',null,1,null,10);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (36,to_timestamp('21-01-18 07:14:40.031000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:14:40.031000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'purchasegroup@test.com','Purchase group','password',null,1,null,11);


Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (3,to_timestamp('21-01-18 01:12:27.281000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 01:12:27.281000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,null,4,5);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (6,to_timestamp('21-01-18 03:49:01.538000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:22:50.394000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,null,7,9);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (9,to_timestamp('21-01-18 07:13:42.509000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:13:42.509000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,null,5,13);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (12,to_timestamp('21-01-18 07:14:40.081000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:14:40.081000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,null,8,17);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (15,to_timestamp('21-01-18 07:15:17.668000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:15:17.668000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,null,9,21);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (18,to_timestamp('21-01-18 07:15:56.267000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:15:56.267000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,null,10,25);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (21,to_timestamp('21-01-18 07:16:42.610000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:16:42.610000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,null,11,29);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (24,to_timestamp('21-01-18 07:20:51.345000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('21-01-18 07:20:51.345000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,null,12,33);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (26,to_timestamp('22-02-18 04:31:19.548000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('26-02-18 12:42:17.913000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,null,13,35);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (27,to_timestamp('22-02-18 04:31:19.548000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('26-02-18 12:42:17.913000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null,null,14,36);


INSERT INTO M_COUNTRY (M_COUNTRY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES (1, SYSDATE, 'Y', SYSDATE, 'IND', 'India', 'INDIA');
INSERT INTO M_COUNTRY (M_COUNTRY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES (2, SYSDATE, 'Y', SYSDATE, 'SA', 'South Africa', 'SOUTH AFRICA');
INSERT INTO M_COUNTRY (M_COUNTRY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES (3, SYSDATE, 'Y', SYSDATE, 'RU', 'Russia', 'RUSSIA');
INSERT INTO M_COUNTRY (M_COUNTRY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES (4, SYSDATE, 'Y', SYSDATE, 'USA', 'America', 'USA');


INSERT INTO C_REGION (C_REGION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, NAME, M_COUNTRY_ID) VALUES (1, SYSDATE, 'Y', SYSDATE, 'Maharashtra', 'Maharashtra', 1);
INSERT INTO C_REGION (C_REGION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, NAME, M_COUNTRY_ID) VALUES (2, SYSDATE, 'Y', SYSDATE, 'Karnataka', 'Karnataka', 1);
INSERT INTO C_REGION (C_REGION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, NAME, M_COUNTRY_ID) VALUES (3, SYSDATE, 'Y', SYSDATE, 'Karnataka', 'West Bengal', 1);
INSERT INTO C_REGION (C_REGION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, NAME, M_COUNTRY_ID) VALUES (4, SYSDATE, 'Y', SYSDATE, 'Gujrat', 'Gujrat', 1);


INSERT INTO C_DISTRICT (C_DISTRICT_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, C_REGION_ID) VALUES (1, SYSDATE, 'Y', SYSDATE, 'NGP', 'Nagpur', 1);
INSERT INTO C_DISTRICT (C_DISTRICT_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, C_REGION_ID) VALUES (2, SYSDATE, 'Y', SYSDATE, 'MUM', 'Mumbai', 1);
INSERT INTO C_DISTRICT (C_DISTRICT_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, C_REGION_ID) VALUES (3, SYSDATE, 'Y', SYSDATE, 'SRT', 'Surat', 4);
INSERT INTO C_DISTRICT (C_DISTRICT_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, C_REGION_ID) VALUES (4, SYSDATE, 'Y', SYSDATE, 'VAD', 'Vadodra', 4);


INSERT INTO C_CITY (C_CITY_ID, CREATED, ISACTIVE, UPDATED, NAME) VALUES (1, SYSDATE, 'Y', SYSDATE, 'Mumbai');
INSERT INTO C_CITY (C_CITY_ID, CREATED, ISACTIVE, UPDATED, NAME) VALUES (2, SYSDATE, 'Y', SYSDATE, 'Navi Mumbai');
INSERT INTO C_CITY (C_CITY_ID, CREATED, ISACTIVE, UPDATED, NAME) VALUES (3, SYSDATE, 'Y', SYSDATE, 'Kolkata');
INSERT INTO C_CITY (C_CITY_ID, CREATED, ISACTIVE, UPDATED, NAME) VALUES (4, SYSDATE, 'Y', SYSDATE, 'Lucknow');

INSERT INTO M_OFFICE_LOCATION (M_OFFICE_LOCATION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, LOCATION_TYPE, NAME) VALUES (1, SYSDATE, 'Y', SYSDATE, 'Head Quartor', 'HO', 'Prakash Gadh');
INSERT INTO M_OFFICE_LOCATION (M_OFFICE_LOCATION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, LOCATION_TYPE, NAME) VALUES (2, SYSDATE, 'Y', SYSDATE, 'Head Quartor', 'HO', 'Prakash Ganga');

Insert into M_FINANCIAL_YEAR (M_FINANCIAL_YEAR_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,VALIDTO,VALIDFROM,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (1,SYSDATE,'Y',SYSDATE,'15-16',null,'2015-2016',to_timestamp('01-01-16 12:04:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),to_timestamp('01-01-15 12:04:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null);
Insert into M_FINANCIAL_YEAR (M_FINANCIAL_YEAR_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,VALIDTO,VALIDFROM,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (2,SYSDATE,'Y',SYSDATE,'16-17',null,'2016-2017',to_timestamp('01-01-17 12:05:49.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),to_timestamp('01-01-16 12:05:49.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null);
Insert into M_FINANCIAL_YEAR (M_FINANCIAL_YEAR_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,VALIDTO,VALIDFROM,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (3,SYSDATE,'Y',SYSDATE,'17-18',null,'2017-2018',to_timestamp('01-01-18 12:05:50.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),to_timestamp('01-01-17 12:05:50.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),null,null,null);

Insert into M_SCRUTINY_POINT (M_SCRUTINY_POINT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(1,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'MSE Registration Certificate Submitted','MSE Registration Certificate Submitted','MSE Registration Certificate Submitted',1,1,1);
Insert into M_SCRUTINY_POINT (M_SCRUTINY_POINT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(2,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Exceise Duty Tarif item No. indicated','Exceise Duty Tarif item No. indicated','Exceise Duty Tarif item No. indicated',1,1,1);
Insert into M_SCRUTINY_POINT (M_SCRUTINY_POINT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(3,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Balance Sheet of 3 Years and Profit and loss account submitted','Balance Sheet of 3 Years and Profit and loss account submitted','Balance Sheet of 3 Years and Profit and loss account submitted',1,1,1);
Insert into M_SCRUTINY_POINT (M_SCRUTINY_POINT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values
(4,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'List of order executed/under execution certified by Chartered Engineer/Accountant','List of order executed/under execution certified by Chartered Engineer/Accountant','List of order executed/under execution certified by Chartered Engineer/Accountant',1,1,1);
Insert into M_SCRUTINY_POINT (M_SCRUTINY_POINT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(5,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Documentry evidence for consessional excise duty is sumbitted','Documentry evidence for consessional excise duty is sumbitted','Documentry evidence for consessional excise duty is sumbitted',1,1,1);


Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(1,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Std_Doc1','ANNEXURE I TO SECTION I 33KV','ANNEXURE_I_TO_SECTION_I_33KV.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(2,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Std_Doc2','ANNEXURE C1 33KV','ANNEXURE_C1_33KV.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(3,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Std_Doc3','ANNEXURE Q DIST','FILE_ANNEXURE_Q_Version_1.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(4,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Std_Doc4','CONDITIONS OF TENDER AND SUPPLY SECTION II 33KV','CONDITIONS_OF_TENDER_AND_SUPPLY_SECTION_II_33KV.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(5,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Std_Doc5','SECTION I INSTRUCTIONS TO THE TENDERER 33KV','SECTION_I_INSTRUCTIONS_TO_THE_TENDERER_33KV.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(6,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Std_Doc6','SECTION I RE','SECTION_I_RE.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(7,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Std_Doc7','SECTION II RE','SECTION_II_RE.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(8,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Std_Doc8','ANNEXURE C1 RE','ANNEXURE_C1_RE.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(9,to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Y',to_timestamp('18-02-18 07:29:40.000000000 PM','DD-MM-RR HH12:MI:SSXFF AM'),'Std_Doc9','ADDITIONAL INSTRUCTIONS RE','ADDITIONAL_INSTRUCTIONS_RE.pdf','','','',1,1,1,'');



ALTER SEQUENCE m_financial_year_seq increment by 5;

UPDATE AD_USER SET AD_USER_DETAILS_ID = '2' WHERE AD_USER_ID=2;
ALTER SEQUENCE ad_user_seq increment by 37;
ALTER SEQUENCE ad_user_details_seq increment by 12;
ALTER SEQUENCE ad_user_roles_seq increment by 28;
ALTER SEQUENCE ad_role_access_master_seq increment by 93;
ALTER SEQUENCE c_location_seq increment by 5;
ALTER SEQUENCE m_attachment_seq increment by 4;
ALTER SEQUENCE ad_role_seq increment by 15; 

update ad_role set view_name='dashboard' where ad_role_id<>2;
update ad_role set view_name='partnerRegistration' where ad_role_id=2;
Commit;