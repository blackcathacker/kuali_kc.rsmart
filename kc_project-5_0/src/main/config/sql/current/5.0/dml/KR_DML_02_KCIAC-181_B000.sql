-- IACUCAdminInitialReview
-- Create responsibility
INSERT INTO KRIM_RSP_T (RSP_ID,RSP_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) VALUES 
(KRIM_RSP_ID_BS_S.NEXTVAL,(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'), 'KC-WKFLW','IACUC Admin Approve Review Request','Protocol Online Review Document - IACUC Admin approves online review request made by PI during protocol submission.','Y',SYS_GUID(),1)
/
-- document for responsibility
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
    (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_RSP_ID_BS_S.CURRVAL,
    (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type, Routing Node & Action Information'), 
    (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'), 'IacucProtocolOnlineReviewDocument',
     SYS_GUID())
/
-- node name
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,KRIM_RSP_ID_BS_S.CURRVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM = 'Document Type, Routing Node & Action Information'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND
    NM = 'routeNodeName'),'IACUCAdminInitialReview',SYS_GUID(),1)
/
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_RSP_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM = 'Document Type, Routing Node & Action Information'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'required'),
'false', SYS_GUID())
/
-- action
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_RSP_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
NM = 'Document Type, Routing Node & Action Information'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'actionDetailsAtRoleMemberLevel' AND NMSPC_CD = 'KR-WKFLW'), 'false', SYS_GUID())
/

-- role responsibility mapping
INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, OBJ_ID, VER_NBR, ROLE_ID, RSP_ID, ACTV_IND) VALUES 
(KRIM_ROLE_RSP_ID_BS_S.NEXTVAL, SYS_GUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator') , 
KRIM_RSP_ID_BS_S.CURRVAL, 'Y')
/
-- inserting action for responsibility
INSERT INTO krim_role_rsp_actn_t (ROLE_RSP_ACTN_ID, OBJ_ID, VER_NBR, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_MBR_ID, ROLE_RSP_ID, FRC_ACTN) values 
(KRIM_ROLE_RSP_ACTN_ID_BS_S.NEXTVAL, sys_guid(), '1', 'A', '1', 'F', '*', KRIM_ROLE_RSP_ID_BS_S.CURRVAL,
'N')
/

-- IACUCOnlineReviewer
-- need a derived role for this
-- Create responsibility
INSERT INTO KRIM_RSP_T (RSP_ID,RSP_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) VALUES 
(KRIM_RSP_ID_BS_S.NEXTVAL,(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'), 'KC-WKFLW','IACUC Reviewer Approve Online Review','Protocol Onlinereview Document - IACUC Reviewer approves online review','Y',SYS_GUID(),1)
/
-- document for responsibility
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
    (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_RSP_ID_BS_S.CURRVAL,
    (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type, Routing Node & Action Information'), 
    (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'), 'IacucProtocolOnlineReviewDocument',
     SYS_GUID())
/
-- node name
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,KRIM_RSP_ID_BS_S.CURRVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM = 'Document Type, Routing Node & Action Information'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND
    NM = 'routeNodeName'),'IACUCOnlineReviewer',SYS_GUID(),1)
/
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_RSP_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM = 'Document Type, Routing Node & Action Information'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'required'),
'false', SYS_GUID())
/
-- action
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_RSP_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
NM = 'Document Type, Routing Node & Action Information'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'actionDetailsAtRoleMemberLevel' AND NMSPC_CD = 'KR-WKFLW'), 'false', SYS_GUID())
/

INSERT INTO KRIM_TYP_T (ACTV_IND,KIM_TYP_ID,NM,NMSPC_CD,OBJ_ID,SRVC_NM,VER_NBR)
  VALUES ('Y',KRIM_TYP_ID_BS_S.NEXTVAL,'Derived Role: IACUC Online Reviewer','KC-WKFLW',SYS_GUID(),'iacucProtocolOnlineReviewRoleTypeService',1)
/
INSERT INTO KRIM_ROLE_T ( ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT )
VALUES( KRIM_ROLE_ID_BS_S.NEXTVAL, SYS_GUID(), 1, 'IACUC Online Reviewer', 'KC-IACUC', 'IACUC Online Reviewer', KRIM_TYP_ID_BS_S.CURRVAL, 'Y', sysdate )
/
-- role responsibility mapping
INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, OBJ_ID, VER_NBR, ROLE_ID, RSP_ID, ACTV_IND) VALUES 
(KRIM_ROLE_RSP_ID_BS_S.NEXTVAL, SYS_GUID(), '1', KRIM_ROLE_ID_BS_S.CURRVAL , 
KRIM_RSP_ID_BS_S.CURRVAL, 'Y')
/
-- inserting action for responsibility
INSERT INTO krim_role_rsp_actn_t (ROLE_RSP_ACTN_ID, OBJ_ID, VER_NBR, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_MBR_ID, ROLE_RSP_ID, FRC_ACTN) values 
(KRIM_ROLE_RSP_ACTN_ID_BS_S.NEXTVAL, sys_guid(), '1', 'A', '1', 'F', '*', KRIM_ROLE_RSP_ID_BS_S.CURRVAL,
'Y')
/

-- IACUCAdminReview

-- Create responsibility
INSERT INTO KRIM_RSP_T (RSP_ID,RSP_TMPL_ID,NMSPC_CD,NM,DESC_TXT,ACTV_IND,OBJ_ID,VER_NBR) VALUES 
(KRIM_RSP_ID_BS_S.NEXTVAL,(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'), 'KC-WKFLW','IACUC Admin Approve Online Review','Protocol Online Review Document - IACUC Admin approves online review','Y',SYS_GUID(),1)
/
-- document for responsibility
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
    (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_RSP_ID_BS_S.CURRVAL,
    (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Type, Routing Node & Action Information'), 
    (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName' AND NMSPC_CD = 'KR-WKFLW'), 'IacucProtocolOnlineReviewDocument',
     SYS_GUID())
/
-- node name
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,OBJ_ID,VER_NBR) 
    VALUES (KRIM_ATTR_DATA_ID_BS_S.NEXTVAL,KRIM_RSP_ID_BS_S.CURRVAL,(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM = 'Document Type, Routing Node & Action Information'),(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND
    NM = 'routeNodeName'),'IACUCAdminReview',SYS_GUID(),1)
/
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_RSP_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
    NM = 'Document Type, Routing Node & Action Information'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'required'),
'false', SYS_GUID())
/
-- action
INSERT INTO KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID, RSP_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL, OBJ_ID) VALUES 
(KRIM_ATTR_DATA_ID_BS_S.NEXTVAL, KRIM_RSP_ID_BS_S.CURRVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND 
NM = 'Document Type, Routing Node & Action Information'), 
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'actionDetailsAtRoleMemberLevel' AND NMSPC_CD = 'KR-WKFLW'), 'false', SYS_GUID())
/
-- role responsibility mapping
INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID, OBJ_ID, VER_NBR, ROLE_ID, RSP_ID, ACTV_IND) VALUES 
(KRIM_ROLE_RSP_ID_BS_S.NEXTVAL, SYS_GUID(), '1', (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Administrator') , 
KRIM_RSP_ID_BS_S.CURRVAL, 'Y')
/
-- inserting action for responsibility
INSERT INTO krim_role_rsp_actn_t (ROLE_RSP_ACTN_ID, OBJ_ID, VER_NBR, ACTN_TYP_CD, PRIORITY_NBR, ACTN_PLCY_CD, ROLE_MBR_ID, ROLE_RSP_ID, FRC_ACTN) values 
(KRIM_ROLE_RSP_ACTN_ID_BS_S.NEXTVAL, sys_guid(), '1', 'A', '1', 'F', '*', KRIM_ROLE_RSP_ID_BS_S.CURRVAL,
'Y')
/
-- online reviewer role permission
INSERT INTO KRIM_ROLE_PERM_T 
(ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID) VALUES 
(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Online Reviewer' AND NMSPC_CD='KC-IACUC'),
    (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View IACUC Protocol' AND NMSPC_CD='KC-IACUC'),'Y',1,SYS_GUID())
/
INSERT INTO KRIM_ROLE_PERM_T 
(ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID) VALUES 
(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Online Reviewer' AND NMSPC_CD='KC-IACUC'),
    (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Maintain IACUC Protocol Online Review Comments' AND NMSPC_CD='KC-IACUC'),'Y',1,SYS_GUID())
/
INSERT INTO KRIM_ROLE_PERM_T 
(ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID) VALUES 
(KRIM_ROLE_PERM_ID_BS_S.NEXTVAL,(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IACUC Online Reviewer' AND NMSPC_CD='KC-IACUC'),
    (SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View IACUC Protocol Online Review Comments' AND NMSPC_CD='KC-IACUC' ),'Y',1,SYS_GUID())
/
