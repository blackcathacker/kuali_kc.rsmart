INSERT INTO KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
       VALUES (KRIM_ROLE_MBR_ID_S.NEXTVAL, 1, SYS_GUID(), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM='Committee Administrator'), '10000000001', 'P', NULL, NULL, SYSDATE)
/