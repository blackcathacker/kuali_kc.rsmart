 CREATE TABLE RATE_TYPE 
   (	"RATE_CLASS_CODE" VARCHAR2(3) constraint RATE_TYPEN1 NOT NULL ENABLE, 
	"RATE_TYPE_CODE" VARCHAR2(3) constraint RATE_TYPEN2 NOT NULL ENABLE, 
	"DESCRIPTION" VARCHAR2(200) constraint RATE_TYPEN3 NOT NULL ENABLE, 
	"UPDATE_TIMESTAMP" DATE constraint RATE_TYPEN4 NOT NULL ENABLE, 
	"UPDATE_USER" VARCHAR2(8) constraint RATE_TYPEN5 NOT NULL ENABLE, 
	 "VER_NBR" NUMBER(8,0) DEFAULT 1  constraint  RATE_TYPEN6  NOT NULL ENABLE,
	"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID()  constraint  RATE_TYPEN7  NOT NULL ENABLE,
	CONSTRAINT "PK_RATE_TYPE_KRA" PRIMARY KEY ("RATE_CLASS_CODE", "RATE_TYPE_CODE") ENABLE
) ;
 