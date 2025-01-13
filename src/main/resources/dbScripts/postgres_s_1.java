INSERT INTO ad_role(ad_role_id, created, createdby, description, isactive, isadmin, name, page_access, updated, updatedby, user_level, view_name, value) 
VALUES (1,  now() , null, 'SystemAdmin', 'Y', 'Y', 'System Admin', 'Y',  now() , null, 'home', 'home', 'SYSADM');

INSERT INTO ad_role(ad_role_id, created, createdby, description, isactive, isadmin, name, page_access, updated, updatedby, user_level, view_name, value) 
VALUES (2,  now() , null, 'Partner User', 'Y', 'Y', 'Partner User', 'Y',  now() , null, 'home', 'home', 'PATUSR');

INSERT INTO ad_role(ad_role_id, created, createdby, description, isactive, isadmin, name, page_access, updated, updatedby, user_level, view_name, value) 
VALUES (3,  now() , null, 'Vendor Admin', 'Y', 'Y', 'Vendor Admin', 'Y',  now() , null, 'home', 'home', 'VENADM');

Insert into m_bpartner (m_bpartner_id,isactive,value, name, crn_number, pan_no, iscustomer, isvendor, created, updated) values
(1,'Y','MSEDCL','Maharashtra state electicity board','XXXXX','XXXXX','Y','Y', now() , now()  );


Insert into ad_user (ad_user_id,isactive, name, email, password, created, updated, M_BPARTNER_ID) values
(1,'Y','SysUser','SysUser','SysUser', now() , now() ,1); 

Insert into ad_user (ad_user_id,isactive, name, email, password, created, updated, M_BPARTNER_ID) values
(2,'Y','MSEDCLUSR','MSEDCLUSR','MSEDCLUSR', now() , now() ,1 );

INSERT INTO ad_user_roles(created, createdby,  isactive, updated, updatedby
, ad_user_id, ad_role_id, ad_user_roles_id, page_path) 
VALUES ( now() , 1, 'Y',  now() , 1, 2, 3, 2, null);

INSERT INTO ad_user_roles(created, createdby,  isactive, updated, updatedby
, ad_user_id, ad_role_id, ad_user_roles_id, page_path) 
VALUES ( now() , 1, 'Y',  now() , 1, 1, 1, 1, null);

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(1,'Y','COMPANY_TYPE','Company Type', now() , now()  );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(2,'Y','CONTRACTOR_TYPE','Contractor Type', now() , now()  );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(3,'Y','TENDER_TYPE','Tender Type', now() , now()  );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(4,'Y','AUCTION_TYPE','Auction Type', now() , now()  );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(5,'Y','BID_TYPE','Bid Type', now() , now()  );

INSERT INTO M_REFERENCE (M_REFERENCE_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME) VALUES 
(6,  now() , 'Y',  now() , 'OFFICE_TYPE', 'Office Type');

INSERT INTO M_REFERENCE (M_REFERENCE_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME) VALUES 
(7,  now() , 'Y',  now() , 'PRICE_BASIS', 'Basis of price');

INSERT INTO M_REFERENCE (M_REFERENCE_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME) VALUES 
(8,  now() , 'Y',  now() , 'BUDGET_TYPE', 'Budget Type');

INSERT INTO M_REFERENCE (M_REFERENCE_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME) VALUES 
(9,  now() , 'Y',  now() , 'PAYMENT_MODE', 'Payment Mode');

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(10,'Y','EXEMPTION_REG_TYPE','EXEMPTION REG TYPE', now() , now()  );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(11,'Y','DEVELOPING_REGIONS','DEVELOPING REGIONS', now() , now()  );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(12,'Y','OE_TYPES','OE_TYPES', now() , now()  );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(13,'Y','BID_SECTION','BID_SECTION', now() , now()  );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(14,'Y','TITLE','Title', now() , now()  );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(15,'Y','FINANCIAL_TYPE','Financial Type', now() , now()  );

Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(16,'Y','DOCUMENT_STATUS','Document Status', now() , now()  );

Insert into M_REFERENCE (M_REFERENCE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(17, now() ,'Y', now() ,'RESPONSE_TYPE_CODE',null,'Deviation Type Code',null,null,null);

Insert into M_REFERENCE (M_REFERENCE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(18, now() ,'Y', now() ,'BIDDER_STATUS',null,'Bidder Status Code',null,null,null);

Insert into M_REFERENCE (M_REFERENCE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(19, now() ,'Y', now() ,'OPENING_TYPE',null,'Opening Types',null,null,null);

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(1,1,'Y','COMPANY_TYPE','PVTLTD','Private Ltd.', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(2,1,'Y','COMPANY_TYPE','GOVFRM','Goverment Firm.', now() , now()  );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(3,2,'Y','CONTRACTOR_TYPE','ELE','Electrical', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(4,2,'Y','CONTRACTOR_TYPE','MEC','Mechanical', now() , now()  );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(5,3,'Y','TENDER_TYPE','WT','Works Tender', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(6,3,'Y','TENDER_TYPE','PT','Procurement Tender', now() , now()  );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(7,5,'Y','BID_TYPE','SB','Single Bid', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(8,5,'Y','BID_TYPE','TB','Two Bid', now() , now()  );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(9,4,'Y','AUCTION_TYPE','FA','Two Bid 2 Stage', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(10,4,'Y','AUCTION_TYPE','RA','Two Bid 3 Stage', now() , now()  );

INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(11,  now() , 'Y',  now() , 'HO', 'Ho', 'OFFICE_TYPE',6);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(12,  now() , 'Y',  now() , 'ZONE', 'Zone', 'OFFICE_TYPE',6);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(13,  now() , 'Y',  now() , 'CIRCLE', 'Circle', 'OFFICE_TYPE',6);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(14,  now() , 'Y',  now() , 'DIV', 'Division', 'OFFICE_TYPE',6);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(15,  now() , 'Y',  now() , 'SUB_DIV', 'Sub Division', 'OFFICE_TYPE',6);

INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(16,  now() , 'Y',  now() , 'VP', 'Variable Price', 'PRICE_BASIS',7);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(17,  now() , 'Y',  now() , 'FP', 'Firm Price Basis', 'PRICE_BASIS',7);

INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(18,  now() , 'Y',  now() , 'CAP', 'Capex', 'BUDGET_TYPE',8);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(19,  now() , 'Y',  now() , 'RVN', 'Revenue', 'BUDGET_TYPE',8);

INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(20,  now() , 'Y',  now() , 'CA', 'Cash', 'PAYMENT_MODE',9);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(21,  now() , 'Y',  now() , 'DD', 'DD', 'PAYMENT_MODE',9);
INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(22,  now() , 'Y',  now() , 'CH', 'Cheque', 'PAYMENT_MODE',9);


Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(23,10,'Y','EXEMPTION_REG_TYPE','MSE','MSE', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(24,10,'Y','EXEMPTION_REG_TYPE','NSIC','NSIC', now() , now()  );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(25,11,'Y','DEVELOPING_REGIONS','test','test', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(26,11,'Y','DEVELOPING_REGIONS','test1','test1', now() , now()  );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(27,12,'Y','OE_TYPES','DGSD','DGSD', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(28,12,'Y','OE_TYPES','DGTD','DGTD', now() , now()  );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(29,13,'Y','BID_SECTION','TS','Technical Section', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(30,13,'Y','BID_SECTION','CS','Commercial Section', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(31,13,'Y','BID_SECTION','PS','Price Section', now() , now()  );

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(32,14,'Y','TITLE','MR.','MR.', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(33,14,'Y','TITLE','MISS','MISS', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(34,14,'Y','TITLE','MRS.','MRS.', now() , now()  );


Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(35,15,'Y','FINANCIAL_TYPE','PNL','Profit and Loss', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(36,15,'Y','FINANCIAL_TYPE','BSA','Balance Sheet Account', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(37,15,'Y','FINANCIAL_TYPE','TD','Turnover details', now() , now()  );


Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(38,16,'Y','DOCUMENT_STATUS','DR','Drafted',  now() ,  now()  ,'Pending Submition','Draft');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(39,16,'Y','DOCUMENT_STATUS','IP','In Progress',  now() ,  now()  ,'Approval Pending','Submit');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(40,16,'Y','DOCUMENT_STATUS','AP','Approved',  now() ,  now()  ,'Not Published','Approve');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(41,16,'Y','DOCUMENT_STATUS','RJ','Rejected',  now() ,  now()  ,'Rejected','Reject');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(42,16,'Y','DOCUMENT_STATUS','PU','Published',  now() ,  now()  ,'Published','Publish');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(43,16,'Y','DOCUMENT_STATUS','OP','Opened',  now() ,  now()  ,'Pending Scrutiny','Open');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(44,16,'Y','DOCUMENT_STATUS','CO','Completed',  now() ,  now()  ,'Awarded','Complete');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(45,16,'Y','DOCUMENT_STATUS','VO','Abandoned',  now() ,  now()  ,'Void','Abandon');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(46,16,'Y','DOCUMENT_STATUS','SC','Scrutinised',  now() ,  now()  ,'Preliminary Scrutiny Finished','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(47,16,'Y','DOCUMENT_STATUS','FS','Final Scrutiny',  now() ,  now()  ,'Final Scrutiny Finished','');


Insert into M_REFERENCE_LIST (M_REFERENCE_LIST_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,REFERENCE_CODE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_REFERENCE_ID) values 
(48, now() ,'Y', now() ,'NUMERIC',null,'Numeric','RESPONSE_TYPE_CODE',null,null,null,17);
Insert into M_REFERENCE_LIST (M_REFERENCE_LIST_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,REFERENCE_CODE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_REFERENCE_ID) values 
(49, now() ,'Y', now() ,'TEXT',null,'Text','RESPONSE_TYPE_CODE',null,null,null,17);
Insert into M_REFERENCE_LIST (M_REFERENCE_LIST_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,REFERENCE_CODE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_REFERENCE_ID) values 
(50, now() ,'Y', now() ,'YESNO',null,'Yes No','RESPONSE_TYPE_CODE',null,null,null,17);
Insert into M_REFERENCE_LIST (M_REFERENCE_LIST_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,REFERENCE_CODE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_REFERENCE_ID) values 
(51, now() ,'Y', now() ,'FILE',null,'File','RESPONSE_TYPE_CODE',null,null,null,17);

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(52,18,'Y','BIDDER_STATUS','PRCH','Tender Purchased',  now() ,  now()  ,'In Progress');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(53,18,'Y','BIDDER_STATUS','APPR','Purchase Approved',  now() ,  now()  ,'Bid Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(54,18,'Y','BIDDER_STATUS','RJCT','Purchase Rejected',  now() ,  now()  ,'In Progress');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(55,18,'Y','BIDDER_STATUS','SBMT','Bid Submited',  now() ,  now()  ,'Bid Submited');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(56,18,'Y','BIDDER_STATUS','TBSU','Technical Bid Submited',  now() ,  now()  ,'Price Bid Submit Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(57,18,'Y','BIDDER_STATUS','PBSU','Price Bid Submited',  now() ,  now()  ,'EMD Payment pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(58,18,'Y','BIDDER_STATUS','EMDP','EMD Paid',  now() ,  now()  ,'EMD Approval pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(59,18,'Y','BIDDER_STATUS','EMDA','EMD Approved',  now() ,  now()  ,'Bid Submited');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(60,18,'Y','BIDDER_STATUS','EMDR','EMD Rejected',  now() ,  now()  ,'EMD Payment Rejected');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(61,18,'Y','BIDDER_STATUS','TBOP','Technical Bid Opened',  now() ,  now()  ,'Under Scrutiny');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(62,18,'Y','BIDDER_STATUS','PTBP','Preliminary Technical Bid Approved',  now() ,  now()  ,'Preliminary Commercial Scrutiny Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(63,18,'Y','BIDDER_STATUS','PCBP','Preliminary Commercial Bid Approved',  now() ,  now()  ,'Price Bid Opening Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(64,18,'Y','BIDDER_STATUS','PTBF','Preliminary Technical Bid Rejected',  now() ,  now()  ,'Preliminary Technical Scrutiny Rejected');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(65,18,'Y','BIDDER_STATUS','PCBF','Preliminary Commercial Bid Rejected',  now() ,  now()  ,'Preliminary Commercial Scrutiny Rejected');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(66,18,'Y','BIDDER_STATUS','DVTN','Deviation Called',  now() ,  now()  ,'Deviation Bid Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(67,18,'Y','BIDDER_STATUS','DBSU','Deviation Bid Submited',  now() ,  now()  ,'Deviation Bid Opening Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(68,18,'Y','BIDDER_STATUS','DBOP','Deviation Bid Opened',  now() ,  now()  ,'Under Final Scrutiny');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(69,18,'Y','BIDDER_STATUS','FTBP','Final Technical Bid Approved',  now() ,  now()  ,'Final Commercial Scrutiny Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(70,18,'Y','BIDDER_STATUS','FCBP','Final Commercial Bid Approved',  now() ,  now()  ,'Price Bid Opening Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(71,18,'Y','BIDDER_STATUS','FTBF','Final Technical Bid Rejected',  now() ,  now()  ,'Final Technical Bid Rejected');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(72,18,'Y','BIDDER_STATUS','FCBF','Final Commercial Bid Rejected',  now() ,  now()  ,'Final Commercial Bid Rejected');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(73,18,'Y','BIDDER_STATUS','PBOP','Price Bid Opened',  now() ,  now()  ,'Price Bid Opened');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(74,18,'Y','BIDDER_STATUS','ANC1','Annexure C1 Called',  now() ,  now()  ,'Annexure C1 Bid Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(75,18,'Y','BIDDER_STATUS','C1SU','Annexure C1 Bid Submited',  now() ,  now()  ,'Annexure C1 Bid Opening Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(76,18,'Y','BIDDER_STATUS','C1OP','Deviation Bid Opened',  now() ,  now()  ,'Winner Selection Pending');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(77,18,'Y','BIDDER_STATUS','AW','Awarded Winner',  now() ,  now()  ,'Winner');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(78,18,'Y','BIDDER_STATUS','CO','Completed',  now() ,  now()  ,'Not Winner');

Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(79,19,'Y','OPENING_TYPE','TCOP','Techno-Commercial Bid Opening',  now() ,  now()  ,null);
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(80,19,'Y','OPENING_TYPE','PBOP','Price Bid Opening',  now() ,  now()  ,null);
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(81,19,'Y','OPENING_TYPE','DBOP','Deviation Bid Opening',  now() ,  now()  ,null);
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(82,19,'Y','OPENING_TYPE','C1OP','Annexure C1 Opening',  now() ,  now()  ,null);

INSERT INTO M_REFERENCE_LIST (M_REFERENCE_LIST_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, REFERENCE_CODE,M_REFERENCE_ID) VALUES 
(83,  now() , 'Y',  now() , 'ISEXEMP', 'ISEXEMPTED', 'PAYMENT_MODE',9);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (4, now() ,'Y', now() ,'Location Admin','Y','Location Admin','Y','home','LOCADM','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (5, now() ,'Y', now() ,'Finance Admin','Y','Finance Admin','Y','home','FINADM','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (6, now() ,'N', now() ,'Msedcl User','N','Msedcl User','Y','home','MSEBUSER','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (7, now() ,'Y', now() ,'Finance Operator','N','Finance Operator','Y','home','FINOPR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (8, now() ,'Y', now() ,'Executive Engr','N','Executive Engr','Y','home','EXEENGR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (9, now() ,'Y', now() ,'Planning Engr','N','Planning Engr','Y','home','PLENGR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (10, now() ,'Y', now() ,'Scrutiny Engr','N','Scrutiny Engr','Y','home','SCRENGR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (11, now() ,'Y', now() ,'Suprintending Engr','N','Suprintending Engr','Y','home','SUPENGR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (12, now() ,'Y', now() ,'Chief Engr','N','Chief Engr','Y','home','CHFENGR','home',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (13, now() ,'Y', now() ,'Account Section','N','Account Section','Y','home','ACCSEC','dashboard',null,null,null);
Insert into AD_ROLE (AD_ROLE_ID,CREATED,ISACTIVE,UPDATED,DESCRIPTION,ISADMIN,NAME,PAGE_ACCESS,USER_LEVEL,VALUE,VIEW_NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (14, now() ,'Y', now() ,'Purchase Group','N','Purchase Group','Y','home','PURGRP','dashboard',null,null,null);



Insert into M_LOCATION_TYPE (M_LOCATION_TYPE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (1, now() ,'Y', now() ,'HO','HO','HO',null,null,null);
Insert into M_LOCATION_TYPE (M_LOCATION_TYPE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (2, now() ,'Y', now() ,'ZONE','ZONE','ZONE',null,null,null);
Insert into M_LOCATION_TYPE (M_LOCATION_TYPE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (3, now() ,'Y', now() ,'CIRCLE','CIRCLE','CIRCLE',null,null,null);
Insert into M_LOCATION_TYPE (M_LOCATION_TYPE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (4, now() ,'Y', now() ,'DIVISION','DIVISION','DIVISION',null,null,null);
Insert into M_LOCATION_TYPE (M_LOCATION_TYPE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (5, now() ,'Y', now() ,'SUBDIVISION','SUBDIVISION','SUBDIVISION',null,null,null);


Insert into C_LOCATION (C_LOCATION_ID,CREATED,ISACTIVE,UPDATED,ADDRESS_1,ADDRESS_2,LOCATION_TYPE,POSTAL,TO_ADDRESS,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,CITY,M_COUNTRY_ID,C_REGION_ID) values 
(1, now() ,'Y', now() ,'VASHI',null,'ZONE','410222',null,null,null,null,null,null,null);
Insert into C_LOCATION (C_LOCATION_ID,CREATED,ISACTIVE,UPDATED,ADDRESS_1,ADDRESS_2,LOCATION_TYPE,POSTAL,TO_ADDRESS,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,CITY,M_COUNTRY_ID,C_REGION_ID) values 
(2, now() ,'Y', now() ,'CHEMBUR',null,'ZONE','400067',null,null,null,null,null,null,null);
Insert into C_LOCATION (C_LOCATION_ID,CREATED,ISACTIVE,UPDATED,ADDRESS_1,ADDRESS_2,LOCATION_TYPE,POSTAL,TO_ADDRESS,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,CITY,M_COUNTRY_ID,C_REGION_ID) values 
(3, now() ,'Y', now() ,'KURLA',null,'HO','400010',null,null,null,null,null,null,null);
Insert into C_LOCATION (C_LOCATION_ID,CREATED,ISACTIVE,UPDATED,ADDRESS_1,ADDRESS_2,LOCATION_TYPE,POSTAL,TO_ADDRESS,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,CITY,M_COUNTRY_ID,C_REGION_ID) values 
(4, now() ,'Y', now() ,'ANDHERI',null,'HO','400009',null,null,null,null,null,null,null);


Insert into M_DESIGNATION (M_DESIGNATION_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(1, now() ,'Y', now() ,'A ENGR','A ENGR','A ENGR',null,null,null);
Insert into M_DESIGNATION (M_DESIGNATION_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(2, now() ,'Y', now() ,'A PROGMR','A PROGMR','A PROGMR',null,null,null);
Insert into M_DESIGNATION (M_DESIGNATION_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(3, now() ,'Y', now() ,'S.O','S.O','S.O',null,null,null);
Insert into M_DESIGNATION (M_DESIGNATION_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values
(4, now() ,'Y', now() ,'MD','MD','MD',null,null,null);


Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (1, now() ,'Y', now() ,'Dashboard','Dashboard','Y',0,'Dashboard',0,1,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (2, now() ,'Y', now() ,'Master','Master','Y',0,'Master',0,2,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (3, now() ,'Y', now() ,'Tenders','Tenders','Y',0,'Tenders',0,3,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (4, now() ,'Y', now() ,'Auction','Auction','Y',0,'Auction',0,4,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (5, now() ,'Y', now() ,'Bids','Bids','Y',0,'Bids',0,5,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (6, now() ,'Y', now() ,'Payments','Payments','Y',0,'Payments',0,6,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (7, now() ,'Y', now() ,'Reports','Reports','Y',0,'Reports',0,7,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (8, now() ,'Y', now() ,'PurchaseProposal','Purchase Proposal','Y',0,'Purchase Proposal',0,8,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (9, now() ,'Y', now() ,'Mail','Mail','Y',0,'Mail',0,9,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (10, now() ,'Y', now() ,'RoleDetails','Role Details','Y',0,'Role Details',0,10,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (11, now() ,'Y', now() ,'Dashboard','Dashboard','N',1,'Dashboard',1,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (12, now() ,'Y', now() ,'AddUser','Add User','N',1,'Add User',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (13, now() ,'Y', now() ,'TendersPreparation','Tenders Preparation','N',1,'Tenders Preparation',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (14, now() ,'Y', now() ,'AuctionPreparation','Auction Preparation','N',1,'Auction Preparation',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (15, now() ,'Y', now() ,'Bids','Bids','N',1,'Bids',5,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (16, now() ,'Y', now() ,'Payments','Payments','N',1,'Payments',6,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (17, now() ,'Y', now() ,'Reports','Reports','N',1,'Reports',7,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (18, now() ,'Y', now() ,'PurchaseProposal','PurchaseProposal','N',1,'PurchaseProposal',8,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (19, now() ,'Y', now() ,'Mail','Mail','N',1,'Mail',9,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (20, now() ,'Y', now() ,'RoleAccess','RoleAccess','N',1,'RoleAccess',10,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (21, now() ,'Y', now() ,'UOM','UOM','N',1,'UOM',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (22, now() ,'Y', now() ,'GTPParameter','GTP Parameter','N',1,'GTP Parameter',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (23, now() ,'Y', now() ,'GTPParameterType','GTP ParameterType','N',1,'GTP ParameterType',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (24, now() ,'Y', now() ,'BidType','Bid Type','N',1,'Bid Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (25, now() ,'Y', now() ,'CompanyType','Company Type','N',1,'Company Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (26, now() ,'Y', now() ,'ContractorType','Contractor Type','N',1,'Contractor Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (27, now() ,'Y', now() ,'Department','Department','N',1,'Department',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (28, now() ,'Y', now() ,'DocumentType','Document Type','N',1,'Document Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (29, now() ,'Y', now() ,'ExemptedCategories','Exempted Categories','N',1,'Exempted Categories',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (30, now() ,'Y', now() ,'FinancialDocuments','Financial Documents','N',1,'Financial Documents',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (31, now() ,'Y', now() ,'HolderType','Holder Type','N',1,'Holder Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (32, now() ,'Y', now() ,'Designation','Designation','N',1,'Designation',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (33, now() ,'Y', now() ,'Role','Role','N',1,'Role',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (34, now() ,'Y', now() ,'TenderType','Tender Type','N',1,'Tender Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (35, now() ,'Y', now() ,'IsStandard','Is Standard','N',1,'Is Standard',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (36, now() ,'Y', now() ,'Material','Material','N',1,'Material',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (37, now() ,'Y', now() ,'MaterialGroup','Material Group','N',1,'Material Group',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (38, now() ,'Y', now() ,'MaterialSubGroup','Material SubGroup','N',1,'Material SubGroup',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (39, now() ,'Y', now() ,'MaterialSpecification','Material Specification','N',1,'Material Specification',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (40, now() ,'Y', now() ,'MaterialType','Material Type','N',1,'Material Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (41, now() ,'Y', now() ,'MaterialVersion','Material Version','N',1,'Material Version',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (42, now() ,'Y', now() ,'PaymentType','Payment Type','N',1,'Payment Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (43, now() ,'Y', now() ,'PublicNotices','Public Notices','N',1,'Public Notices',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (44, now() ,'Y', now() ,'RegistrationType','Registration Type','N',1,'Registration Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (45, now() ,'Y', now() ,'TenderBudgetType','Tender Budget Type','N',1,'Tender Budget Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (46, now() ,'Y', now() ,'VendorType','Vendor Type','N',1,'Vendor Type',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (47, now() ,'Y', now() ,'TaxCategory','Tax Category','N',1,'Tax Category',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (48, now() ,'Y', now() ,'Tax','Tax','N',1,'Tax',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (49, now() ,'Y', now() ,'TenderPublishing','Tender Publishing','N',1,'Tender Publishing',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (50, now() ,'Y', now() ,'TenderScheduling','Tender Scheduling','N',1,'Tender Scheduling',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (51, now() ,'Y', now() ,'TenderPurchase','Tender Purchase','N',1,'Tender Purchase',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (52, now() ,'Y', now() ,'TenderDetails','Tender Details','N',1,'Tender Details',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (53, now() ,'Y', now() ,'TenderOpening','Tender Opening','N',1,'Tender Opening',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (54, now() ,'Y', now() ,'TenderCommercialScrutiny','Tender Commercial Scrutiny','N',1,'Tender Commercial Scrutiny',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (55, now() ,'Y', now() ,'TenderTechnicalScrutiny','Tender Technical Scrutiny','N',1,'Tender Technical Scrutiny',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (56, now() ,'Y', now() ,'AuctionPreparation','Auction Preparation','N',1,'Auction Preparation',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (57, now() ,'Y', now() ,'AuctionPublishing','Auction Publishing','N',1,'Auction Publishing',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (58, now() ,'Y', now() ,'AuctionScheduling','Auction Scheduling','N',1,'Auction Scheduling',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (59, now() ,'Y', now() ,'AuctionPurchase','Auction Purchase','N',1,'Auction Purchase',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (60, now() ,'Y', now() ,'AuctionDetails','Auction Details','N',1,'Auction Details',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (61, now() ,'Y', now() ,'AuctionOpening','Auction Opening','N',1,'Auction Opening',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (62, now() ,'Y', now() ,'AuctionScrutiny','Auction Scrutiny','N',1,'Auction Scrutiny',4,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (63, now() ,'Y', now() ,'BusinessPartnerGroup','Business Partner Group','N',1,'Business Partner Group',2,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (64, now() ,'Y', now() ,'Registration','Registration','Y',0,'Registration',0,11,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (65, now() ,'Y', now() ,'Utilities','Utilities','Y',0,'Utilities',0,12,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (66, now() ,'Y', now() ,'Certificate','Certificate','Y',0,'Certificate',0,13,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (67, now() ,'Y', now() ,'Registration','Registration','N',1,'Registration',64,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (68, now() ,'Y', now() ,'Utilities','Utilities','N',1,'Utilities',65,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (69, now() ,'Y', now() ,'Certificate','Certificate','N',1,'Certificate',66,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (70, now() ,'Y', now() ,'TenderSubmission','Tender Submission','N',1,'Tender Submission',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (71, now() ,'Y', now() ,'AnnexureC1','Annexure C1','N',1,'Annexure C1',3,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (72, now() ,'Y', now() ,'PaymentApproval','Payment Approval','Y',0,'Payment Approval',0,14,null,null,null);

Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (73, now() ,'Y', now() ,'PaymentApproval','Payment Approval','N',1,'Payment Approval',72,0,null,null,null);
Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (74, now() ,'Y', now() ,'TenderCommittee','Tender Committee','N',1,'Tender Committee',3,0,null,null,null);

Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (1, now() ,'Y', now() ,'N','N','Y',null,null,null,1,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (2, now() ,'Y', now() ,'N','N','Y',null,null,null,1,63);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (3, now() ,'Y', now() ,'Y','Y','Y',null,null,null,1,12);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (4, now() ,'Y', now() ,'N','N','Y',null,null,null,1,48);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (5,now(),'Y',now(),'N','N','Y',null,null,null,1,46);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (6,now(),'Y',now(),'N','N','Y',null,null,null,1,47);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (7,now(),'Y',now(),'N','N','Y',null,null,null,1,45);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (8,now(),'Y',now(),'N','N','Y',null,null,null,1,44);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (9,now(),'Y',now(),'Y','Y','Y',null,null,null,1,43);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (10,now(),'Y',now(),'N','N','Y',null,null,null,1,42);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (11,now(),'Y',now(),'N','N','Y',null,null,null,1,41);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (12,now(),'Y',now(),'N','N','Y',null,null,null,1,40);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (13,now(),'Y',now(),'N','N','Y',null,null,null,1,39);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (14,now(),'Y',now(),'N','N','Y',null,null,null,1,38);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (15,now(),'Y',now(),'N','N','Y',null,null,null,1,37);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (16,now(),'Y',now(),'N','N','Y',null,null,null,1,36);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (17,now(),'Y',now(),'N','N','Y',null,null,null,1,35);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (18,now(),'Y',now(),'N','N','Y',null,null,null,1,34);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (19,now(),'Y',now(),'N','N','Y',null,null,null,1,33);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (20,now(),'Y',now(),'N','N','Y',null,null,null,1,32);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (21,now(),'Y',now(),'N','N','Y',null,null,null,1,31);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (22,now(),'Y',now(),'N','N','Y',null,null,null,1,30);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (23,now(),'Y',now(),'Y','Y','Y',null,null,null,1,29);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (24,now(),'Y',now(),'N','N','Y',null,null,null,1,21);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (25,now(),'Y',now(),'Y','Y','Y',null,null,null,1,22);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (26,now(),'Y',now(),'Y','Y','Y',null,null,null,1,23);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (27,now(),'Y',now(),'N','N','Y',null,null,null,1,24);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (28,now(),'Y',now(),'N','N','Y',null,null,null,1,25);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (29,now(),'Y',now(),'N','N','Y',null,null,null,1,26);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (30,now(),'Y',now(),'N','N','Y',null,null,null,1,27);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (31,now(),'Y',now(),'N','N','Y',null,null,null,1,28);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (32,now(),'Y',now(),'N','N','Y',null,null,null,1,55);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (33,now(),'Y',now(),'N','N','Y',null,null,null,1,13);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (34,now(),'Y',now(),'N','N','Y',null,null,null,1,49);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (35,now(),'Y',now(),'N','N','Y',null,null,null,1,50);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (36, now() ,'Y', now() ,'N','N','Y',null,null,null,1,51);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (37,now(),'Y',now(),'N','N','Y',null,null,null,1,54);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (38,now(),'Y',now(),'N','N','Y',null,null,null,1,53);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (39,now(),'Y',now(),'N','N','Y',null,null,null,1,52);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (40,now(),'Y',now(),'N','N','Y',null,null,null,1,61);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (41,now(),'Y',now(),'N','N','Y',null,null,null,1,62);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (42,now(),'Y',now(),'N','N','Y',null,null,null,1,57);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (43,now(),'Y',now(),'N','N','Y',null,null,null,1,58);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (44,now(),'Y',now(),'N','N','Y',null,null,null,1,60);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (45,now(),'Y',now(),'N','N','Y',null,null,null,1,59);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (46,now(),'Y',now(),'N','N','Y',null,null,null,1,14);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (47,now(),'Y',now(),'N','N','Y',null,null,null,1,15);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (48,now(),'Y',now(),'N','N','Y',null,null,null,1,16);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (49,now(),'Y',now(),'N','N','Y',null,null,null,1,17);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (50,now(),'Y',now(),'N','N','Y',null,null,null,1,18);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (51,now(),'Y',now(),'N','N','Y',null,null,null,1,19);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (52,now(),'Y',now(),'N','N','Y',null,null,null,1,20);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (53,now(),'Y',now(),'Y','Y','Y',null,null,null,4,12);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (54,now(),'Y',now(),'N','N','Y',null,null,null,4,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (55,now(),'Y',now(),'N','N','Y',null,null,null,5,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (56,now(),'Y',now(),'Y','Y','Y',null,null,null,5,16);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (57,now(),'Y',now(),'N','N','Y',null,null,null,3,15);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (58,now(),'Y',now(),'N','N','Y',null,null,null,3,19);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (59,now(),'Y',now(),'N','N','Y',null,null,null,2,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (60,now(),'Y',now(),'N','N','Y',null,null,null,4,20);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (61,now(),'Y',now(),'Y','Y','Y',null,null,null,7,16);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (62,now(),'Y',now(),'N','N','Y',null,null,null,8,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (63,now(),'Y',now(),'N','N','Y',null,null,null,8,13);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (64,now(),'Y',now(),'N','N','Y',null,null,null,8,49);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (65,now(),'Y',now(),'N','N','Y',null,null,null,8,50);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (66,now(),'Y',now(),'N','N','Y',null,null,null,8,54);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (67,now(),'Y',now(),'N','N','Y',null,null,null,8,53);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (68,now(),'Y',now(),'N','N','Y',null,null,null,8,52);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (69,now(),'Y',now(),'N','N','Y',null,null,null,9,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (70,now(),'Y',now(),'Y','Y','Y',null,null,null,9,22);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (71,now(),'Y',now(),'N','N','Y',null,null,null,9,41);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (72,now(),'Y',now(),'N','N','Y',null,null,null,10,55);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (73,now(),'Y',now(),'N','N','Y',null,null,null,10,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (74,now(),'Y',now(),'N','N','Y',null,null,null,3,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (75,now(),'Y',now(),'N','N','Y',null,null,null,3,52);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (76,now(),'Y',now(),'N','N','Y',null,null,null,3,51);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (77,now(),'Y',now(),'N','N','Y',null,null,null,1,69);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (78,now(),'Y',now(),'N','N','Y',null,null,null,1,68);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (79, now() ,'Y', now() ,'N','N','Y',null,null,null,1,67);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (80,now(),'Y',now(),'N','N','Y',null,null,null,3,70);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (81,now(),'Y',now(),'N','N','Y',null,null,null,1,70);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (82,now(),'Y',now(),'Y','Y','Y',null,null,null,1,71);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (83,now(),'Y',now(),'N','N','Y',null,null,null,13,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (84,now(),'Y',now(),'N','N','Y',null,null,null,13,67);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (85,now(),'Y',now(),'N','N','Y',null,null,null,14,67);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (86,now(),'Y',now(),'N','N','Y',null,null,null,14,11);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (88,now(),'Y',now(),'N','N','Y',null,null,null,12,13);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (89,now(),'Y',now(),'N','N','Y',null,null,null,7,73);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (90,now(),'Y',now(),'N','N','Y',null,null,null,5,73);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (91,now(),'Y',now(),'N','N','Y',null,null,null,8,67);
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) values (92,now(),'Y',now(),'N','N','Y',null,null,null,12,67);

INSERT INTO M_TENDER_TYPE (M_TENDER_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'LIMITED', 'LIMITED', 'LIMITED');

INSERT INTO M_TENDER_TYPE (M_TENDER_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'OPEN', 'OPEN', 'OPEN');

INSERT INTO M_TENDER_BUDGET_TYPE (M_TENDER_BUDGET_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'CAPEX', 'CAPEX', 'CAPEX');

INSERT INTO M_TENDER_BUDGET_TYPE (M_TENDER_BUDGET_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'REVENUE', 'REVENUE', 'REVENUE');


INSERT INTO M_CONTRACTOR_TYPE (M_CONTRACTOR_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'CIVIL', 'CIVIL', 'CIVIL');

INSERT INTO M_CONTRACTOR_TYPE (M_CONTRACTOR_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'ELECTRICAL', 'ELECTRICAL', 'ELECTRICAL');


INSERT INTO M_COMPANY_TYPE (M_COMPANY_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', now() , 'Y',  now() , 'Partnership Firm', 'Partnership Firm', 'Partnership Firm');

INSERT INTO M_COMPANY_TYPE (M_COMPANY_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'Private Limited', 'Private Limited', 'Private Limited');

INSERT INTO M_COMPANY_TYPE (M_COMPANY_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',  now() , 'Y',  now() , 'UDYOG AADHAAR', 'UDYOG AADHAAR', 'UDYOG AADHAAR');

INSERT INTO M_COMPANY_TYPE (M_COMPANY_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4',  now() , 'Y',  now() , 'Public Limited', 'Public Limited', 'Public Limited');

INSERT INTO M_COMPANY_TYPE (M_COMPANY_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('5',  now() , 'Y',  now() , 'Proprietary Firm', 'Proprietary Firm', 'Proprietary Firm');


INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'Abrasives', 'Abrasives', 'Abrasives');

INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'Appliances', 'Appliances', 'Appliances');

INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',  now() , 'Y',  now() , 'Boiler', 'Boiler', 'Boiler');

INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4',  now() , 'Y',  now() , 'Gas Turbines', 'Gas Turbines', 'Gas Turbines');

INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('5',  now() , 'Y',  now() , 'TRANSFORMER(Power and Regulators)', 'TRANSFORMER(Power and Regulators)', 'TRANSFORMER(Power and Regulators)');

INSERT INTO M_MATERIAL_GROUP (M_MATERIAL_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('6',  now() , 'Y',  now() , 'Works(Contractor)', 'Works(Contractor)','Works(Contractor)');


INSERT INTO M_DEPARTMENT (M_DEPARTMENT_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'Common', 'Common', 'Common');

INSERT INTO M_DEPARTMENT (M_DEPARTMENT_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'Generation', 'Generation', 'Generation');

INSERT INTO M_DEPARTMENT (M_DEPARTMENT_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',  now() , 'Y',  now() , 'Transmission', 'Transmission', 'Transmission');

INSERT INTO M_DEPARTMENT (M_DEPARTMENT_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4',  now() , 'Y',  now() , 'Distribution', 'Distribution', 'Distribution');

INSERT INTO M_DEPARTMENT (M_DEPARTMENT_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('5',  now() , 'Y',  now() , 'IT Director', 'IT Director', 'IT Director');


INSERT INTO M_CHARGE (M_CHARGE_ID, CREATED, ISACTIVE, UPDATED, AMOUNT, GST, VALUE, DESCRIPTION, ISREFUNDABLE, NAME, PERIOD_TYPE) VALUES ('1',  now() , 'Y',  now() , '25000', '18', 'RG', 'Registration Fee', 'N', 'Registration Fee','Y');

INSERT INTO M_CHARGE (M_CHARGE_ID, CREATED, ISACTIVE, UPDATED, AMOUNT, GST, VALUE, DESCRIPTION, ISREFUNDABLE, NAME, PERIOD_TYPE) VALUES ('2',  now() , 'Y',  now() , '2500', '18', 'RN', 'Registration Renewal Fee', 'N', 'Registration Renewal Fee', 'Y');

INSERT INTO M_CHARGE (M_CHARGE_ID, CREATED, ISACTIVE, UPDATED, AMOUNT, GST, VALUE, DESCRIPTION, ISREFUNDABLE, NAME, PERIOD_TYPE) VALUES ('3',  now() , 'Y',  now() , '0', '18', 'TF', 'Tender Fee', 'N', 'Tender Fee', 'N');

INSERT INTO M_CHARGE (M_CHARGE_ID, CREATED, ISACTIVE, UPDATED, AMOUNT, GST, VALUE, DESCRIPTION, ISREFUNDABLE, NAME, PERIOD_TYPE) VALUES ('4',  now() , 'Y',  now() , '0', '18', 'EMD', 'EMD', 'Y', 'EMD', 'N');


INSERT INTO M_REGISTRATION_TYPE (M_REGISTRATION_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'Provisional', 'Provisional', 'Provisional');

INSERT INTO M_REGISTRATION_TYPE (M_REGISTRATION_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'Permanent', 'Permanent', 'Permanent');


INSERT INTO M_BPARTNER_GROUP (M_BPARTNER_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'CUSTOMERS', 'CUSTOMERS', 'CUSTOMERS');

INSERT INTO M_BPARTNER_GROUP (M_BPARTNER_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'VENDORS', 'VENDORS', 'VENDORS');

INSERT INTO M_BPARTNER_GROUP (M_BPARTNER_GROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',  now() , 'Y',  now() , 'CONTRACTORS', 'CONTRACTORS', 'CONTRACTORS');


INSERT INTO M_VENDOR_TYPE (M_VENDOR_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'MANUFACTURER', 'MANUFACTURER', 'MANUFACTURER');

INSERT INTO M_VENDOR_TYPE (M_VENDOR_TYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'TRADER', 'TRADER', 'TRADER');


INSERT INTO M_HOLDERTYPE (M_HOLDERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'DIGITAL SIGNATORY', 'DIGITAL SIGNATORY', 'DIGITAL SIGNATORY');

INSERT INTO M_HOLDERTYPE (M_HOLDERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'POWER OF ATTORNEY', 'POWER OF ATTORNEY', 'POWER OF ATTORNEY');

INSERT INTO M_HOLDERTYPE (M_HOLDERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',  now() , 'Y',  now() , 'TENDER SIGNATORY', 'TENDER SIGNATORY', 'TENDER SIGNATORY');


INSERT INTO M_EXEMPTED_CATEGORIES (M_EXEMPTED_CATEGORIES_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'MSE', 'MSE', 'MSE');

INSERT INTO M_EXEMPTED_CATEGORIES (M_EXEMPTED_CATEGORIES_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'UDYOG AADHAAR', 'UDYOG AADHAAR', 'UDYOG AADHAAR');

INSERT INTO M_EXEMPTED_CATEGORIES (M_EXEMPTED_CATEGORIES_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',  now() , 'Y',  now() , 'GOVT Firms', 'GOVT Firms', 'GOVT Firms');

INSERT INTO M_EXEMPTED_CATEGORIES (M_EXEMPTED_CATEGORIES_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4',  now() , 'Y',  now() , 'STARTUP[Only MAHARASHTRA]', 'STARTUP[Only MAHARASHTRA]', 'STARTUP[Only MAHARASHTRA]');

INSERT INTO M_EXEMPTED_CATEGORIES (M_EXEMPTED_CATEGORIES_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('5',  now() , 'Y',  now() , 'New Units Other than Startup[PS and TP Certified]', 'New Units Other than Startup[PS and TP Certified]', 'New Units Other than Startup[PS and TP Certified]');

INSERT INTO M_DOCUMENTTYPE (M_DOCUMENTTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1', now() , 'Y', now() , 'ST 1', 'ST 1', 'ST 1');

INSERT INTO M_DOCUMENTTYPE (M_DOCUMENTTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2', now() , 'Y', now() , 'CM 1', 'CM 1', 'CM 1');

INSERT INTO M_DOCUMENTTYPE (M_DOCUMENTTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3', now() , 'Y', now() , 'ST 2', 'ST 2', 'ST 2');


INSERT INTO M_TAX_CATEGORY (M_TAX_CATEGORY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'GST 5%', 'GST 5%', 'GST 5%');

INSERT INTO M_TAX_CATEGORY (M_TAX_CATEGORY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'GST  18%', 'GST  18%', 'GST  18%');

INSERT INTO M_TAX_CATEGORY (M_TAX_CATEGORY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',  now() , 'Y',  now() , 'GST 28%', 'GST 28%', 'GST 28%');


INSERT INTO M_BIDTYPE (M_BIDTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'TWO BID One Stage', 'TWO BID One Stage', 'TWO BID One Stage');

INSERT INTO M_BIDTYPE (M_BIDTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'TWO BID Two Stage', 'TWO BID Two Stage', 'TWO BID Two Stage');


INSERT INTO M_UOM (M_UOM_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'Un', 'Unit', 'Unit');

INSERT INTO M_UOM (M_UOM_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'ft', 'Feets', 'Feets');

INSERT INTO M_UOM (M_UOM_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',  now() , 'Y',  now() , 'm', 'Metre', 'Metre');

INSERT INTO M_UOM (M_UOM_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4',  now() , 'Y',  now() , 'mm', 'MiliMetre', 'MiliMetre');


INSERT INTO M_PUBLIC_NOTICE (M_PUBLIC_NOTICE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, MATTER, NAME, PUBLISHING_DATE, SALE_CLOSING_DATE, SALE_OPENING_DATE, TITLE) VALUES ('1',  now() , 'Y',  now() , 'Advertisment 01-2018', 'Advertisment 01-2018', 'Matter 1', 'Advertisment 01-2018',  now() ,  now() ,  now() , 'Title 1');

INSERT INTO M_PUBLIC_NOTICE (M_PUBLIC_NOTICE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, MATTER, NAME, PUBLISHING_DATE, SALE_CLOSING_DATE, SALE_OPENING_DATE, TITLE) VALUES ('2',  now() , 'Y',  now() , 'Advertisment 02-2018', 'Advertisment 02-2018', 'Matter 2', 'Advertisment 02-2018',  now() ,  now() ,  now() , 'Title 2');

INSERT INTO M_PUBLIC_NOTICE (M_PUBLIC_NOTICE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, MATTER, NAME, PUBLISHING_DATE, SALE_CLOSING_DATE, SALE_OPENING_DATE, TITLE) VALUES ('3',  now() , 'Y',  now() , 'Advertisment 03-2018', 'Advertisment 03-2018', 'Matter 3', 'Advertisment 03-2018',  now() ,  now() ,  now() , 'Title 3');

INSERT INTO M_PUBLIC_NOTICE (M_PUBLIC_NOTICE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, MATTER, NAME, PUBLISHING_DATE, SALE_CLOSING_DATE, SALE_OPENING_DATE, TITLE) VALUES ('4',  now() , 'Y',  now() , 'Advertisment 04-2018', 'Advertisment 04-2018', 'Matter 4', 'Advertisment 04-2018',  now() ,  now() ,  now() , 'Title 4');


INSERT INTO M_FINANCIAL_DOC (M_FINANCIAL_DOC_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'Financial Doc 1', 'Financial Doc 1', 'Financial Doc 1');

INSERT INTO M_FINANCIAL_DOC (M_FINANCIAL_DOC_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'Financial Doc 2', 'Financial Doc 2', 'Financial Doc 2');

INSERT INTO M_FINANCIAL_DOC (M_FINANCIAL_DOC_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',  now() , 'Y',  now() , 'Financial Doc 3', 'Financial Doc 3', 'Financial Doc 3');


INSERT INTO M_GTP_PARAMETERTYPE (M_GTP_PARAMETERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('1',  now() , 'Y',  now() , 'TEXT', 'Text', 'Text');

INSERT INTO M_GTP_PARAMETERTYPE (M_GTP_PARAMETERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('2',  now() , 'Y',  now() , 'BOOL', 'Boolean', 'Boolean');

INSERT INTO M_GTP_PARAMETERTYPE (M_GTP_PARAMETERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('3',  now() , 'Y',  now() , 'NUMR', 'Numeric', 'Numeric');
INSERT INTO M_GTP_PARAMETERTYPE (M_GTP_PARAMETERTYPE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES ('4',  now() , 'Y',  now() , 'FILE', 'File', 'File');

INSERT INTO M_TAX (M_TAX_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_TAX_CATEGORY_ID) VALUES ('1',  now() , 'Y',  now() , 'IGST', 'IGST', 'IGST', '1');
INSERT INTO M_TAX (M_TAX_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_TAX_CATEGORY_ID) VALUES ('2',  now() , 'Y',  now() , 'SGST', 'SGST', 'SGST', '1');


INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) VALUES ('1',  now()   , 'Y',  now()   , 'Spares for 210MW BHEL', 'Spares for 210MW BHEL', 'Spares for 210MW BHEL', '3');

INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) VALUES ('2',  now()   , 'Y',  now()   , 'Spares for CTPS (1*500MW BHEL)', 'Spares for CTPS (1*500MW BHEL)', 'Spares for CTPS (1*500MW BHEL)', '3');

INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) VALUES ('3',  now()   , 'Y',  now()   , 'Spares for CTPS(210MW A.V.B)', 'Spares for CTPS(210MW A.V.B)', 'Spares for CTPS(210MW A.V.B)', '3');

INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) VALUES ('4',  now()   , 'Y',  now()   , 'Super Heater', 'Super Heater', 'Super Heater', '3');

INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) 
VALUES ('5',  now()   , 'Y',  now()   , 'Spares for CTPS (2*500MW BHEL)', 'Spares for CTPS (2*500MW BHEL)', 'Spares for CTPS (2*500MW BHEL)', '3');

INSERT INTO M_MATERIAL_SUBGROUP (M_MATERIAL_SUBGROUP_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_GROUP_ID) 
VALUES ('6',  now()   , 'Y',  now()   , 'Services', 'Services', 'Services', '6');

INSERT INTO M_HSN (M_HSN_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) 
VALUES ('1',  now() , 'Y',  now() , '96110000', 'DATE, SEALING OR NUMBERING STAMPS, AND THE LIKE (INCLUDING D', 'DATE, SEALING OR NUMBERING STAMPS, AND THE LIKE (INCLUDING D');
INSERT INTO M_HSN (M_HSN_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) 
VALUES ('2',  now() , 'Y',  now() , '94036000', 'OTHER FURNITURE AND PARTS THEREOF -Other wooden furniture', 'OTHER FURNITURE AND PARTS THEREOF -Other wooden furniture');
INSERT INTO M_HSN (M_HSN_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) 
VALUES ('3',  now() , 'Y',  now() , '94031090', 'OTHER FURNITURE AND PARTS THEREOF - Metal furniture of a kin', 'OTHER FURNITURE AND PARTS THEREOF - Metal furniture of a kin');
INSERT INTO M_HSN (M_HSN_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) 
VALUES ('4',  now() , 'Y',  now() , '94031010', 'OTHER FURNITURE AND PARTS THEREOF - Metal furniture of a kin', 'OTHER FURNITURE AND PARTS THEREOF - Metal furniture of a kin');
INSERT INTO M_HSN (M_HSN_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) 
VALUES ('5',  now() , 'Y',  now() , '94013000', 'SEATS, WHETHER OR NOT CONVERTIBLE INTO BEDS, AND PARTS THERE', 'SEATS, WHETHER OR NOT CONVERTIBLE INTO BEDS, AND PARTS THERE');
INSERT INTO M_HSN (M_HSN_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) 
VALUES ('6',  now() , 'Y',  now() , '90308400', 'OSCILLOSCOPES, SPECTRUM ANALYSERS AND OTHER INSTRUMENTS AND', 'OSCILLOSCOPES, SPECTRUM ANALYSERS AND OTHER INSTRUMENTS AND');
INSERT INTO M_HSN (M_HSN_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) 
VALUES ('7',  now() , 'Y',  now() , '90303950', 'OSCILLOSCOPES, SPECTRUM ANALYSERS AND OTHER INSTRUMENTS AND', 'OSCILLOSCOPES, SPECTRUM ANALYSERS AND OTHER INSTRUMENTS AND');
INSERT INTO M_HSN (M_HSN_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) 
VALUES ('8',  now() , 'Y',  now() , '90303940', 'OSCILLOSCOPES, SPECTRUM ANALYSERS AND OTHER INSTRUMENTS AND', 'OSCILLOSCOPES, SPECTRUM ANALYSERS AND OTHER INSTRUMENTS AND');
INSERT INTO M_HSN (M_HSN_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) 
VALUES ('9',  now() , 'Y',  now() , '90303910', 'OSCILLOSCOPES, SPECTRUM ANALYSERS AND OTHER INSTRUMENTS AND', 'OSCILLOSCOPES, SPECTRUM ANALYSERS AND OTHER INSTRUMENTS AND');
INSERT INTO M_HSN (M_HSN_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) 
VALUES ('10',  now() , 'Y',  now() , '90303900', 'OSCILLOSCOPES, SPECTRUM ANALYSERS AND OTHER INSTRUMENTS AND', 'OSCILLOSCOPES, SPECTRUM ANALYSERS AND OTHER INSTRUMENTS AND');


INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('8',  now() , 'Y',  now() , 'Lead acid st.battary 30v 60 AH', 'Lead acid st.battary 30v 60 AH', '1', 'Y', 'N', 'N', 'Y', 'Battary 30v 60H', 'Lead acid st.battary 30v 60 AH', 'I', '3', '3', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('9',  now() , 'Y',  now() , '110V.100AH,LA.St.Battery55Cell', '110V.100AH,LA.St.Battery55Cell', '2', 'N', 'Y', 'N', 'Y', 'LA.St.Battery55Cell', '110V.100AH,LA.St.Battery55Cell', 'I', '3', '3', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('10',  now() , 'Y',  now() , 'Battery 150 AH 12 volts', 'Battery 150 AH 12 volts', '5', 'Y', 'Y', 'N', 'Y', 'Battery 150 AH', 'Battery 150 AH 12 volts', 'I', '3', '2', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('11',  now() , 'Y',  now() , 'DC Batteries (12V, 200 AH)', 'DC Batteries (12V, 200 AH)', '4', 'Y', 'Y', 'Y', 'Y', 'DC Batteries', 'DC Batteries (12V, 200 AH)', 'I', '3', '1', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('12',  now() , 'Y',  now() , 'LT C. XLPE 3.5C x 35 Sq mm', 'LT C. XLPE 3.5C x 35 Sq mm', '3', 'N', 'N', 'Y', 'N', 'LT C. XLPE', 'LT C. XLPE 3.5C x 35 Sq mm', 'I', '3', '1', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('13',  now() , 'Y',  now() , 'Building Maintainence', 'Building Maintainence','2', 'Y', 'N', 'N', 'Y', 'Building Maintainence', 'Building Maintainence', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('14',  now() , 'Y',  now() , 'Electrification', 'Electrification', '5','Y', 'N', 'N', 'Y', 'Electrification', 'Electrification', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('15',  now() , 'Y',  now() , 'Road Maintainence', 'Road Maintainence','6', 'Y', 'N', 'N', 'Y', 'Road Maintainence', 'Road Maintainence', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, M_HSN_ID,ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID)
 VALUES ('16',  now() , 'Y',  now() , 'Drainage Re-Modelling', 'Drainage Re-Modelling','7', 'Y', 'N', 'N', 'Y', 'Drainage Re-Modelling', 'Drainage Re-Modelling', 'S', '6', '6', '1');
 
INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('17',  now() , 'Y',  now() , 'Flyover Construction', 'Flyover Construction','1', 'Y', 'N', 'N', 'Y', 'Flyover Construction', 'Flyover Construction', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('18',  now() , 'Y',  now() , 'Sanitary Materials', 'Sanitary Materials','2', 'Y', 'N', 'N', 'Y', 'Sanitary Materials', 'Sanitary Materials', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('19',  now() , 'Y',  now() , 'Plumbing Materials', 'Plumbing Materials','3', 'Y', 'N', 'N', 'Y', 'Plumbing Materials', 'Plumbing Materials', 'S', '6', '6', '1');

INSERT INTO M_MATERIAL (M_MATERIAL_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION,M_HSN_ID, ISNSIC, ISPROPREPORT, ISSSI, ISTESTREPORT, ITEM_TRADE, NAME, TYPE_CODE, M_MATERIAL_GROUP_ID, M_MATERIAL_SUBGROUP_ID, M_UOM_ID) 
VALUES ('20',  now() , 'Y',  now() , 'WaterProofing Materials', 'WaterProofing Materials','8', 'Y', 'N', 'N', 'Y', 'WaterProofing Materials', 'WaterProofing Materials', 'S', '6', '6', '1');


/* 
INSERT INTO M_MATERIAL_SPECIFICATION (M_MATERIAL_SPECIFICATION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, QUANTITY, SPECIFICATION, M_MATERIAL_ID) VALUES ('1',  now() , 'Y',  now() , 'Spec 1', 'Spec 1', 'Spec 1', '120', 'Specification 1', '1');
INSERT INTO M_MATERIAL_SPECIFICATION (M_MATERIAL_SPECIFICATION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, QUANTITY, SPECIFICATION, M_MATERIAL_ID) VALUES ('2',  now() , 'Y',  now() , 'Spec 2', 'Spec 2', 'Spec 2', '180', 'Specification 2', '2');
INSERT INTO M_MATERIAL_SPECIFICATION (M_MATERIAL_SPECIFICATION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, QUANTITY, SPECIFICATION, M_MATERIAL_ID) VALUES ('3',  now() , 'Y',  now() , 'Spec 3', 'Spec 3', 'Spec 3', '210', 'Specification 3', '3');

INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('1',  now() , 'Y',  now() , '1', 'VESRION 1', 'VESRION 1', '1');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('2',  now() , 'Y',  now() , '2', 'VERSION 2', 'VERSION 2', '1');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('3',  now() , 'Y',  now() , '3', 'VERSION 3', 'VERSION 3', '1');

INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('4',  now() , 'Y',  now() , '1', 'VESRION 1', 'VESRION 1', '2');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('5',  now() , 'Y',  now() , '2', 'VERSION 2', 'VERSION 2', '2');

INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('6',  now() , 'Y',  now() , '1', 'VESRION 1', 'VESRION 1', '3');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('7',  now() , 'Y',  now() , '2', 'VERSION 2', 'VERSION 2', '3');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('8',  now() , 'Y',  now() , '3', 'VERSION 3', 'VERSION 3', '3');
INSERT INTO M_MATERIAL_VERSION (M_MATERIAL_VERSION_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('9',  now() , 'Y',  now() , '3', 'VERSION 3', 'VERSION 3', '3');

INSERT INTO M_GTP_PARAMETER (M_GTP_PARAMETER_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_GTP_PARAMETERTYPE_ID) VALUES ('1',  now() , 'Y',  now() , 'Gtp 1', 'Gtp 1', 'Gtp 1', '1');
INSERT INTO M_GTP_PARAMETER (M_GTP_PARAMETER_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_GTP_PARAMETERTYPE_ID) VALUES ('2',  now() , 'Y',  now() , 'Gtp 2', 'Gtp 2', 'Gtp 2', '1');
 INSERT INTO M_ISSTD(M_ISSTD_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('1',  now() , 'Y',  now() , 'Standard A', 'Standard A', 'Standard A', '1');
INSERT INTO M_ISSTD(M_ISSTD_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('2',  now() , 'Y',  now() , 'Standard B', 'Standard B', 'Standard B', '1');
INSERT INTO M_ISSTD(M_ISSTD_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME, M_MATERIAL_ID) VALUES ('3',  now() , 'Y',  now() , 'Standard C', 'Standard C', 'Standard C', '1');
*/

Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (1,now(),'Y',now(),null,null,null,'Location','Admin','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (2,now(),'Y',now(),null,null,null,'Location','Admin','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (3,now(),'Y',now(),null,null,null,'Finance','Operator','HO','N',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (4,now(),'Y',now(),null,null,null,'Finance','Admin','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (5,now(),'Y',now(),null,null,null,'Executive','Engr','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (6,now(),'Y',now(),null,null,null,'Planning','Engr','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (7,now(),'Y',now(),null,null,null,'Scrutiny','Engr','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (8,now(),'Y',now(),null,null,null,'Superintending','Engr','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (9, now() ,'Y', now() ,null,null,null,'Chief','Engr','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (10,now(),'Y',now(),null,null,null,'Account','Section','HO','M',null,null,null,null,null,null,null,null,1,1,1);
Insert into AD_USER_DETAILS (AD_USER_DETAILS_ID,CREATED,ISACTIVE,UPDATED,EMAIL,FAX_1,FAX_2,FIRST_NAME,LAST_NAME,LOCATION_TYPE,MIDDLE_NAME,MOBILE_NO,TELEPHONE_1,TELEPHONE_2,TITLE,USER_DETAIL_TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,M_DESIGNATION_ID,C_LOCATION_ID,M_LOCATION_TYPE_ID) values (11,now(),'Y',now(),null,null,null,'Purchase','Group','HO','M',null,null,null,null,null,null,null,null,1,1,1);



Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (5, now() ,'Y', now() ,'locationadmin@test.com','Location','password',null,1,null,2);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (9, now() ,'Y',now(),'financeoperator@test.com','Finance','password',null,1,null,3);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (13,now(),'Y',now(),'financeadmin@test.com','Finance','password',null,1,null,4);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (17,now(),'Y',now(),'executiveengr@test.com','Executive','password',null,1,null,5);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (21,now(),'Y',now(),'planningengr@test.com','Planning','password',null,1,null,6);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (25,now(),'Y',now(),'scrutinyengr@test.com','Scrutiny','password',null,1,null,7);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (29,now(),'Y',now(),'suprintendingengr@test.com','Superintending','password',null,1,null,8);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (33,now(),'Y',now(),'chiefengr@test.com','Chief','password',null,1,null,9);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (35,now(),'Y',now(),'accountsection@test.com','Account','password',null,1,null,10);
Insert into AD_USER (AD_USER_ID,CREATED,ISACTIVE,UPDATED,EMAIL,NAME,PASSWORD,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_USER_DETAILS_ID) values (36,now(),'Y',now(),'purchasegroup@test.com','Purchase group','password',null,1,null,11);


Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (3,now(),'Y',now(),null,null,null,null,4,5);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (6,now(),'Y',now(),null,null,null,null,7,9);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (9,now(),'Y',now(),null,null,null,null,5,13);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (12,now(),'Y',now(),null,null,null,null,8,17);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (15,now(),'Y',now(),null,null,null,null,9,21);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (18,now(),'Y',now(),null,null,null,null,10,25);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (21,now(),'Y',now(),null,null,null,null,11,29);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (24,now(),'Y',now(),null,null,null,null,12,33);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (26, now() ,'Y',now(),null,null,null,null,13,35);
Insert into AD_USER_ROLES (AD_USER_ROLES_ID,CREATED,ISACTIVE,UPDATED,PAGE_PATH,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,AD_USER_ID) values (27, now() ,'Y',now(),null,null,null,null,14,36);


INSERT INTO M_COUNTRY (M_COUNTRY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES (1,  now() , 'Y',  now() , 'IND', 'India', 'INDIA');
INSERT INTO M_COUNTRY (M_COUNTRY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES (2,  now() , 'Y',  now() , 'SA', 'South Africa', 'SOUTH AFRICA');
INSERT INTO M_COUNTRY (M_COUNTRY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES (3,  now() , 'Y',  now() , 'RU', 'Russia', 'RUSSIA');
INSERT INTO M_COUNTRY (M_COUNTRY_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, NAME) VALUES (4,  now() , 'Y',  now() , 'USA', 'America', 'USA');


INSERT INTO C_REGION (C_REGION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, NAME, M_COUNTRY_ID) VALUES (1,  now() , 'Y',  now() , 'Maharashtra', 'Maharashtra', 1);
INSERT INTO C_REGION (C_REGION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, NAME, M_COUNTRY_ID) VALUES (2,  now() , 'Y',  now() , 'Karnataka', 'Karnataka', 1);
INSERT INTO C_REGION (C_REGION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, NAME, M_COUNTRY_ID) VALUES (3,  now() , 'Y',  now() , 'Karnataka', 'West Bengal', 1);
INSERT INTO C_REGION (C_REGION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, NAME, M_COUNTRY_ID) VALUES (4,  now() , 'Y',  now() , 'Gujrat', 'Gujrat', 1);


INSERT INTO C_DISTRICT (C_DISTRICT_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, C_REGION_ID) VALUES (1,  now() , 'Y',  now() , 'NGP', 'Nagpur', 1);
INSERT INTO C_DISTRICT (C_DISTRICT_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, C_REGION_ID) VALUES (2,  now() , 'Y',  now() , 'MUM', 'Mumbai', 1);
INSERT INTO C_DISTRICT (C_DISTRICT_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, C_REGION_ID) VALUES (3,  now() , 'Y',  now() , 'SRT', 'Surat', 4);
INSERT INTO C_DISTRICT (C_DISTRICT_ID, CREATED, ISACTIVE, UPDATED, VALUE, NAME, C_REGION_ID) VALUES (4,  now() , 'Y',  now() , 'VAD', 'Vadodra', 4);


INSERT INTO C_CITY (C_CITY_ID, CREATED, ISACTIVE, UPDATED, NAME) VALUES (1,  now() , 'Y',  now() , 'Mumbai');
INSERT INTO C_CITY (C_CITY_ID, CREATED, ISACTIVE, UPDATED, NAME) VALUES (2,  now() , 'Y',  now() , 'Navi Mumbai');
INSERT INTO C_CITY (C_CITY_ID, CREATED, ISACTIVE, UPDATED, NAME) VALUES (3,  now() , 'Y',  now() , 'Kolkata');
INSERT INTO C_CITY (C_CITY_ID, CREATED, ISACTIVE, UPDATED, NAME) VALUES (4,  now() , 'Y',  now() , 'Lucknow');

INSERT INTO M_OFFICE_LOCATION (M_OFFICE_LOCATION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, LOCATION_TYPE, NAME) VALUES (1,  now() , 'Y',  now() , 'Head Quartor', 'HO', 'Prakash Gadh');
INSERT INTO M_OFFICE_LOCATION (M_OFFICE_LOCATION_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, LOCATION_TYPE, NAME) VALUES (2,  now() , 'Y',  now() , 'Head Quartor', 'HO', 'Prakash Ganga');

Insert into M_FINANCIAL_YEAR (M_FINANCIAL_YEAR_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,VALIDTO,VALIDFROM,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (1, now() ,'Y', now() ,'15-16',null,'2015-2016',now(),now(),null,null,null);
Insert into M_FINANCIAL_YEAR (M_FINANCIAL_YEAR_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,VALIDTO,VALIDFROM,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (2, now() ,'Y', now() ,'16-17',null,'2016-2017',now(),now(),null,null,null);
Insert into M_FINANCIAL_YEAR (M_FINANCIAL_YEAR_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,VALIDTO,VALIDFROM,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values (3, now() ,'Y', now() ,'17-18',null,'2017-2018',now(),now(),null,null,null);

Insert into M_SCRUTINY_POINT (M_SCRUTINY_POINT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(1, now() ,'Y', now() ,'MSE Registration Certificate Submitted','MSE Registration Certificate Submitted','MSE Registration Certificate Submitted',1,1,1);
Insert into M_SCRUTINY_POINT (M_SCRUTINY_POINT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(2, now() ,'Y', now() ,'Exceise Duty Tarif item No. indicated','Exceise Duty Tarif item No. indicated','Exceise Duty Tarif item No. indicated',1,1,1);
Insert into M_SCRUTINY_POINT (M_SCRUTINY_POINT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(3, now() ,'Y', now() ,'Balance Sheet of 3 Years and Profit and loss account submitted','Balance Sheet of 3 Years and Profit and loss account submitted','Balance Sheet of 3 Years and Profit and loss account submitted',1,1,1);
Insert into M_SCRUTINY_POINT (M_SCRUTINY_POINT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values
(4, now() ,'Y', now() ,'List of order executed/under execution certified by Chartered Engineer/Accountant','List of order executed/under execution certified by Chartered Engineer/Accountant','List of order executed/under execution certified by Chartered Engineer/Accountant',1,1,1);
Insert into M_SCRUTINY_POINT (M_SCRUTINY_POINT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,NAME,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) values 
(5, now() ,'Y', now() ,'Documentry evidence for consessional excise duty is sumbitted','Documentry evidence for consessional excise duty is sumbitted','Documentry evidence for consessional excise duty is sumbitted',1,1,1);


Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(1, now() ,'Y', now() ,'Std_Doc1','ANNEXURE I TO SECTION I 33KV','ANNEXURE_I_TO_SECTION_I_33KV.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(2, now() ,'Y', now() ,'Std_Doc2','ANNEXURE C1 33KV','ANNEXURE_C1_33KV.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(3, now() ,'Y', now() ,'Std_Doc3','ANNEXURE Q DIST','FILE_ANNEXURE_Q_Version_1.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(4, now() ,'Y', now() ,'Std_Doc4','CONDITIONS OF TENDER AND SUPPLY SECTION II 33KV','CONDITIONS_OF_TENDER_AND_SUPPLY_SECTION_II_33KV.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(5, now() ,'Y', now() ,'Std_Doc5','SECTION I INSTRUCTIONS TO THE TENDERER 33KV','SECTION_I_INSTRUCTIONS_TO_THE_TENDERER_33KV.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(6, now() ,'Y', now() ,'Std_Doc6','SECTION I RE','SECTION_I_RE.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(7, now() ,'Y', now() ,'Std_Doc7','SECTION II RE','SECTION_II_RE.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(8, now() ,'Y', now() ,'Std_Doc8','ANNEXURE C1 RE','ANNEXURE_C1_RE.pdf','','','',1,1,1,'');

Insert into M_ATTACHMENT(M_ATTACHMENT_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,FILE_NAME,NAME,PATH,TYPE,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,MEDIA) values 
(9, now() ,'Y', now() ,'Std_Doc9','ADDITIONAL INSTRUCTIONS RE','ADDITIONAL_INSTRUCTIONS_RE.pdf','','','',1,1,1,'');

UPDATE AD_USER SET AD_USER_DETAILS_ID = '2' WHERE AD_USER_ID=2;
update ad_role set view_name='dashboard' where ad_role_id<>2;
update ad_role set view_name='partnerRegistration' where ad_role_id=2;

UPDATE AD_USER SET PASSWORD = 'pass,123',HASPLAINPASSWORD = 'Y' ;


Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(20,'Y','SCHEDULING','Scheduling', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(84,20,'Y','SCHEDULING','TechnicalBid','Technical Bid Opening',  now() ,  now()  ,null);
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(85,20,'Y','SCHEDULING','PriceBid','Price Bid Opening',  now() ,  now()  ,null);
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(86,20,'Y','SCHEDULING','AnnextureC1','Annexture C1 Opening',  now() ,  now()  ,null);
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description) values
(87,20,'Y','SCHEDULING','DeviationBid','Deviation Bid Opening',  now() ,  now()  ,null);



Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) 
values (75, now() ,'Y', now() ,'PreBidMeeting','Pre Bid Meeting','N',1,'Pre Bid Meeting',3,0,null,null,null);
INSERT INTO AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID, CREATED, ISACTIVE, UPDATED, DELETE_ACCESS, MODIFY_ACCESS, VIEW_ONLY, AD_ROLE_ID, M_TILE_ID) 
VALUES ('93',  now() , 'Y',  now() , 'N', 'N', 'Y', '1', '74');
INSERT INTO AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID, CREATED, ISACTIVE, UPDATED, DELETE_ACCESS, MODIFY_ACCESS, VIEW_ONLY, AD_ROLE_ID, M_TILE_ID) 
VALUES ('94',  now() , 'Y',  now() , 'N', 'N', 'Y', '1', '75');



UPDATE M_TILE SET VALUE = 'FinalScrutiny',DESCRIPTION = 'Final Scrutiny',NAME='Final Scrutiny' WHERE M_TILE_ID=54;
UPDATE M_TILE SET VALUE = 'PreliminaryScrutiny',DESCRIPTION = 'Preliminary Scrutiny',NAME='Preliminary Scrutiny' WHERE M_TILE_ID=55;



Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(21,'Y','HOLDER_TYPE','Holder type', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(88,21,'Y','HOLDER_TYPE','DS','Digital Signatory', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(90,21,'Y','HOLDER_TYPE','POA','Power Of Attorney', now() , now()  );
Insert into AD_ROLE_ACCESS_MASTER (AD_ROLE_ACCESS_MASTER_ID,CREATED,ISACTIVE,UPDATED,DELETE_ACCESS,MODIFY_ACCESS,VIEW_ONLY,CREATEDBY,M_BPARTNER_ID,UPDATEDBY,AD_ROLE_ID,M_TILE_ID) 
values (95, now() ,'N', now() ,'N','N','Y',null,null,null,8,74);
INSERT INTO AD_ROLE (AD_ROLE_ID, CREATED, ISACTIVE, UPDATED, DESCRIPTION, ISADMIN, NAME, PAGE_ACCESS, USER_LEVEL, VALUE, VIEW_NAME) 
VALUES ('15',  now() , 'Y',  now() , 'Auditor', 'N', 'Auditor', 'Y', 'home', 'AUDIT', 'dashboard');




UPDATE M_REFERENCE SET VALUE = 'SCHEDULING_TYPE', NAME = 'Scheduling Types' WHERE M_REFERENCE_ID=20;
UPDATE M_REFERENCE_LIST SET VALUE = 'PBSCH', DESCRIPTION = 'Price Bid Scheduling', NAME = 'Price Bid Scheduling', REFERENCE_CODE = 'SCHEDULING_TYPE' WHERE M_REFERENCE_LIST_ID=84;
UPDATE M_REFERENCE_LIST SET VALUE = 'C1SCH', DESCRIPTION = 'Annexure C1 Scheduling', NAME = 'Annexure C1 Scheduling', REFERENCE_CODE = 'SCHEDULING_TYPE' WHERE M_REFERENCE_LIST_ID=85;
UPDATE M_REFERENCE_LIST SET VALUE = 'RBSCH', DESCRIPTION = 'Revised Bid Scheduling', NAME = 'Revised Bid Scheduling', REFERENCE_CODE = 'SCHEDULING_TYPE' WHERE M_REFERENCE_LIST_ID=86;
UPDATE M_REFERENCE_LIST SET VALUE = 'AWSCH', DESCRIPTION = 'Award Winner Scheduling', NAME = 'Award Winner Scheduling', REFERENCE_CODE = 'SCHEDULING_TYPE' WHERE M_REFERENCE_LIST_ID=87;
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(91,1,'Y','COMPANY_TYPE','PARTNERSHIP','Partnership', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(92,1,'Y','COMPANY_TYPE','PROPRIETARY','Proprietary', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(93,1,'Y','COMPANY_TYPE','PUBLTD','Public Limited', now() , now()  );



UPDATE T_TAHDR SET OFFICE_TYPE= 'HO' WHERE TAHDR_TYPE_CODE='PT';
UPDATE T_TAHDR SET C_LOCATION_ID=3 WHERE TAHDR_TYPE_CODE='PT' AND OFFICE_TYPE='HO';
UPDATE M_TILE SET NAME='Notification',VALUE='Notification',DESCRIPTION='Notification' WHERE M_TILE_ID=9 OR M_TILE_ID=19;
update M_REFERENCE_LIST set ACTION_NAME='Send For Approval'  WHERE M_REFERENCE_LIST_ID=39;
update M_REFERENCE_LIST set ACTION_NAME='Cancel'  WHERE M_REFERENCE_LIST_ID=45;
update M_REFERENCE_LIST set ACTION_NAME='Approve and Publish'  WHERE M_REFERENCE_LIST_ID=42;
Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(22,'Y','COMMENCEMENT_PERIOD_CODE','Commencement Period Code', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(94,22,'Y','COMMENCEMENT_PERIOD_CODE','FirstDemand','First Demand', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(95,22,'Y','COMMENCEMENT_PERIOD_CODE','LOA','LOA Award', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(96,22,'Y','COMMENCEMENT_PERIOD_CODE','ArtworkApproval','Artwork Approval', now() , now()  );



Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(24,'Y','VALIDITY_TYPE','Company Type', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(98,24,'Y','VALIDITY_TYPE','PERMANENT','Permanent', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(99,24,'Y','VALIDITY_TYPE','PROVISIONAL','Provisional', now() , now()  );
 UPDATE AD_USER SET EMAIL = 'sysuser' WHERE AD_USER_ID =1;



Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(25,'Y','MY_TENDER_BUTTON','My Tender Button', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(100,25,'Y','MY_TENDER_BUTTON','BID','Bid Submission', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(101,25,'Y','MY_TENDER_BUTTON','DVTN','Deviation Bid Submission', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(102,25,'Y','MY_TENDER_BUTTON','C1','Annexure C1 Bid Submission', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(103,25,'Y','MY_TENDER_BUTTON','REVISED','Revised Bid Submission', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(104,25,'Y','MY_TENDER_BUTTON','TCOP','Technical Bid Opening', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(105,25,'Y','MY_TENDER_BUTTON','PBOP','Price Bid Opening', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(106,25,'Y','MY_TENDER_BUTTON','DVOP','Deviation Bid Opening', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(107,25,'Y','MY_TENDER_BUTTON','C1OP','Annexure C1 Bid Opening', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(108,25,'Y','MY_TENDER_BUTTON','TCSCHD','Technical Bid Scheduling', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(109,25,'Y','MY_TENDER_BUTTON','PBSCHD','Price Bid Scheduling', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(110,25,'Y','MY_TENDER_BUTTON','C1SCHD','Annexure C1 Bid Scheduling', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(111,25,'Y','MY_TENDER_BUTTON','RBSCHD','Revised Bid Scheduling', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(112,25,'Y','MY_TENDER_BUTTON','AWSCHD','Award Winner Scheduling', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated) values
(113,25,'Y','MY_TENDER_BUTTON','WINNER','Award Winner', now() , now()  );
 Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY) 
values (76, now() ,'Y', now() ,'DeviationBid','Deviation Bid','N',1,'Deviation Bid',3,0,null,null,null);
INSERT INTO M_TILE (M_TILE_ID, CREATED, ISACTIVE, UPDATED, VALUE, DESCRIPTION, IS_SUMMARY, LEVEL_NO, NAME, PARENT_ID, SEQUENCE_NO) 
VALUES ('77',  now() , 'Y',  now() , 'MyTender', 'My Tender', 'N', '1', 'My Tender', '3', '0');
UPDATE AD_ROLE SET VIEW_NAME = 'dashboard4' WHERE VALUE='VENADM';



UPDATE M_REFERENCE_LIST SET VALUE = 'AW' WHERE M_REFERENCE_LIST_ID =113; 
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderSubmission' WHERE M_REFERENCE_LIST_ID =100;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'deviationBid' WHERE M_REFERENCE_LIST_ID =101;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'annexureC1' WHERE M_REFERENCE_LIST_ID =102;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderSubmission' WHERE M_REFERENCE_LIST_ID =103;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderOpening' WHERE M_REFERENCE_LIST_ID =104;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderOpening' WHERE M_REFERENCE_LIST_ID =105;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderOpening' WHERE M_REFERENCE_LIST_ID =106;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderOpening' WHERE M_REFERENCE_LIST_ID =107;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderScheduling' WHERE M_REFERENCE_LIST_ID =108;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderScheduling' WHERE M_REFERENCE_LIST_ID =109;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderScheduling' WHERE M_REFERENCE_LIST_ID =110;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderScheduling' WHERE M_REFERENCE_LIST_ID =111;
UPDATE M_REFERENCE_LIST SET DESCRIPTION = 'tenderScheduling' WHERE M_REFERENCE_LIST_ID =112;



Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(114,16,'Y','DOCUMENT_STATUS','TCOP','Techno Commercial Opening',  now() ,  now()  ,'Techno Commercial Opening','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(115,16,'Y','DOCUMENT_STATUS','DBOP','Deviation Bid Opening',  now() ,  now()  ,'Deviation Bid Opening','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(116,16,'Y','DOCUMENT_STATUS','PBOP','Price Bid Opening',  now() ,  now()  ,'Price Bid Opening','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(117,16,'Y','DOCUMENT_STATUS','C1OP','Annexure C1 Opening',  now() ,  now()  ,'Annexure C1 Opening','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(118,16,'Y','DOCUMENT_STATUS','RBOP','Revised Bid Opening',  now() ,  now()  ,'Annexure C1 Opening','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(119,16,'Y','DOCUMENT_STATUS','AWOP','Award Winner Opening',  now() ,  now()  ,'Annexure C1 Opening','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(120,16,'Y','DOCUMENT_STATUS','DBSCH','Deviation Bid Scheduling',  now() ,  now()  ,'Deviation Bid Scheduling','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(121,16,'Y','DOCUMENT_STATUS','PBSCH','Price Bid Scheduling',  now() ,  now()  ,'Price Bid Scheduling','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(122,16,'Y','DOCUMENT_STATUS','C1SCH','Annexure C1 Scheduling',  now() ,  now()  ,'Annexure C1 Scheduling','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(123,16,'Y','DOCUMENT_STATUS','RBSCH','Revise Bid Scheduling',  now() ,  now()  ,'Revised Bid Scheduling','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(124,16,'Y','DOCUMENT_STATUS','AWSCH','Award Winner Scheduling',  now() ,  now()  ,'Award Winner Scheduling','');
Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(26,'Y','NATURE_OF_ACTIVITY','Nature Of Activity', now() , now()  );
Insert into m_reference (m_reference_id,isactive,value, name, created, updated) values
(27,'Y','ENTERPRISE_CATEGORY','Nature Of Activity', now() , now()  );
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(125,26,'Y','NATURE_OF_ACTIVITY','MFR','Manufacturing',  now() ,  now()  ,'Manufacturing','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(126,26,'Y','NATURE_OF_ACTIVITY','SRV','Service',  now() ,  now()  ,'Service','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(127,27,'Y','ENTERPRISE_CATEGORY','MDM','Medium',  now() ,  now()  ,'Medium','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(128,27,'Y','ENTERPRISE_CATEGORY','SML','Small',  now() ,  now()  ,'Small','');
Insert into m_reference_list (M_REFERENCE_LIST_ID,m_reference_id,isactive,REFERENCE_CODE ,value, name, created, updated, description,action_name) values
(129,27,'Y','ENTERPRISE_CATEGORY','MCR','Micro',  now() ,  now()  ,'Micro','');


INSERT INTO AD_SYSCONFIG (AD_SYSCONFIG_ID, VALUE, NAME) VALUES (1, 'REPORT_DIR','/home/vivekb/server/reports/');


Insert into M_TILE (M_TILE_ID,CREATED,ISACTIVE,UPDATED,VALUE,DESCRIPTION,IS_SUMMARY,LEVEL_NO,NAME,PARENT_ID,SEQUENCE_NO,CREATEDBY,M_BPARTNER_ID,UPDATEDBY)
values (78, now() ,'Y', now() ,'TenderApproval','Tender Approval','N',1,'Tender Approval',3,0,null,null,null);

ALTER SEQUENCE ad_user_seq increment by 3;
ALTER SEQUENCE ad_role_seq increment by 4;
ALTER SEQUENCE ad_user_roles_seq increment by 3;
ALTER SEQUENCE m_bpartner_seq increment by 2;
ALTER SEQUENCE m_exempted_categories_seq increment by 6;
ALTER SEQUENCE m_gtp_parameter_seq increment by 5;
ALTER SEQUENCE m_gtp_parametertype_seq increment by 5;
ALTER SEQUENCE m_public_notice_seq increment by 6;
ALTER SEQUENCE m_exempted_categories_seq increment by 6;
ALTER SEQUENCE m_financial_year_seq increment by 5;
ALTER SEQUENCE ad_user_seq increment by 37;
ALTER SEQUENCE ad_user_details_seq increment by 12;
ALTER SEQUENCE ad_user_roles_seq increment by 28;
ALTER SEQUENCE ad_role_access_master_seq increment by 93;
ALTER SEQUENCE c_location_seq increment by 5;
ALTER SEQUENCE m_attachment_seq increment by 4;
ALTER SEQUENCE ad_role_seq increment by 15; 
ALTER SEQUENCE ad_role_access_master_seq increment by 95;
ALTER SEQUENCE m_attachment_seq increment by 10;
alter sequence m_material_version_seq increment by 10;
ALTER SEQUENCE ad_role_access_master_seq increment by 96;
ALTER SEQUENCE ad_role_seq increment by 16;



