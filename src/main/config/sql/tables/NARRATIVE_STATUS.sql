 CREATE TABLE NARRATIVE_STATUS 
   (	NARRATIVE_STATUS_CODE VARCHAR2(3) CONSTRAINT NARRATIVE_STATUSN1 NOT NULL ENABLE, 
	DESCRIPTION VARCHAR2(20) CONSTRAINT NARRATIVE_STATUSN2 NOT NULL ENABLE, 
	UPDATE_TIMESTAMP DATE CONSTRAINT NARRATIVE_STATUSN3 NOT NULL ENABLE, 
	UPDATE_USER VARCHAR2(8) CONSTRAINT NARRATIVE_STATUSN4 NOT NULL ENABLE, 
	VER_NBR NUMBER(8,0) DEFAULT 1 CONSTRAINT NARRATIVE_STATUSN5 NOT NULL ENABLE, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() CONSTRAINT NARRATIVE_STATUSN6 NOT NULL ENABLE, 
	 CONSTRAINT PK_NARRATIVE_STATUS_KRA PRIMARY KEY (NARRATIVE_STATUS_CODE)
) 
 