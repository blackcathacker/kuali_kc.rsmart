
insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
values ('KC-GEN','A','US_CITIZEN_OR_NONCITIZEN_NATIONAL_TYPE_CODE','CONFG','1','US citizen or non citizen',
'A',sys_guid());


insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
values ('KC-GEN','A','PERMANENT_RESIDENT_OF_US_TYPE_CODE','CONFG','2','Permanent resident of us',
'A',sys_guid());


insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,OBJ_ID) 
values ('KC-GEN','A','NON_US_CITIZEN_WITH_TEMPORARY_VISA_TYPE_CODE','CONFG','3','Non us citizen with temporary visa',
'A',sys_guid());

commit;
