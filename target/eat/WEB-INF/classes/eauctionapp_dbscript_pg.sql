INSERT INTO ad_role(ad_role_id, created, createdby, description, isactive, isadmin, name, pageaccess, updated, updatedby, userlevel, viewname, value) VALUES (2, '2017-07-12', null, 'CustomerAdmin', 'Y', 'Y', 'Customer Admin', 'Y', '2017-07-12', null, 'home', 'home', 'CUSADM');
INSERT INTO ad_role(ad_role_id, created, createdby, description, isactive, isadmin, name, pageaccess, updated, updatedby, userlevel, viewname, value) VALUES (1, '2017-07-12', null, 'SystemAdmin', 'Y', 'Y', 'System Admin', 'Y', '2017-07-12', null, 'home', 'home', 'SYSADM');
Insert into ad_user (ad_user_id,isactive, name, email, password) values
(1,'Y','SysUser','SysUser','SysUser' );

INSERT INTO ad_user_roles(created, createdby,  isactive, updated, updatedby
, ad_user_id, ad_role_id, ad_user_roles_id, page_path) 
VALUES ('2017-07-20 13:44:53.374', 1, 'Y', '2017-07-20 13:44:53.374', 1, 1, 1, 1, null);


ALTER SEQUENCE ad_user_seq RESTART WITH 3;
ALTER SEQUENCE ad_role_seq RESTART WITH 3;

ALTER SEQUENCE ad_user_roles_seq RESTART WITH 2;
