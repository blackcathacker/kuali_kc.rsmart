 CREATE TABLE S2S_APP_ATTACHMENTS 
   (	"CONTENT_ID" VARCHAR2(300) constraint S2S_APP_ATTACHMENTSN1 NOT NULL ENABLE, 
	"PROPOSAL_NUMBER" VARCHAR2(8) constraint S2S_APP_ATTACHMENTSN2 NOT NULL ENABLE, 
	"HASH_CODE" VARCHAR2(200), 
	"UPDATE_TIMESTAMP" DATE, 
	"UPDATE_USER" VARCHAR2(60), 
	"CONTENT_TYPE" VARCHAR2(30), 
	 "VER_NBR" NUMBER(8,0) DEFAULT 1  constraint  S2S_APP_ATTACHMENTSN3  NOT NULL ENABLE,
	"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID()  constraint  S2S_APP_ATTACHMENTSN4  NOT NULL ENABLE,
	CONSTRAINT "PK_S2S_ATTACHMENTS_KRA" PRIMARY KEY ("CONTENT_ID", "PROPOSAL_NUMBER") ENABLE
) 
 