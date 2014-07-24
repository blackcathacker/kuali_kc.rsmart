INSERT INTO KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, SORT_CD, ACTV_IND, OBJ_ID)
    VALUES (KRIM_TYP_ATTR_ID_BS_S.NEXTVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName'), 'b', 'Y', SYS_GUID())
/

INSERT INTO KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, SORT_CD, ACTV_IND, OBJ_ID) 
    VALUES (KRIM_TYP_ATTR_ID_BS_S.NEXTVAL, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName'), 'b', 'Y', SYS_GUID())
/