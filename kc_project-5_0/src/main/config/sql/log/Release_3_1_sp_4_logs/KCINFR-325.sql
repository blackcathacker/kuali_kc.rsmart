INSERT INTO KRIM_GRP_MBR_T (GRP_MBR_ID,GRP_ID,MBR_ID,MBR_TYP_CD,LAST_UPDT_DT,OBJ_ID,VER_NBR) 
    VALUES (KRIM_GRP_MBR_ID_BS_S.NEXTVAL,(SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KC-WKFLW' AND GRP_NM = 'ProposalAdmin'),'1','P',SYSDATE,SYS_GUID(),1);