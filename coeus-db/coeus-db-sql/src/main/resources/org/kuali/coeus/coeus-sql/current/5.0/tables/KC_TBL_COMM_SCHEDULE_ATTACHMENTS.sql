CREATE TABLE COMM_SCHEDULE_ATTACHMENTS (
	ATTACHMENT_ID NUMBER(3,0) NOT NULL, 
	DESCRIPTION VARCHAR2(200),
	FILE_NAME VARCHAR2(300),
	ATTACHMENT BLOB,
	MIME_TYPE VARCHAR2(100),
	UPDATE_TIMESTAMP DATE,
	UPDATE_USER VARCHAR2(60),
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL,
	OBJ_ID VARCHAR2(36) NOT NULL,
	SCHEDULE_ID_FK NUMBER(12,0),
	ATTACHMENT_TYPE_CODE VARCHAR2(20), 
	NEW_UPDATE_TIMESTAMP DATE,
	NEW_UPDATE_USER VARCHAR2(60))
/
ALTER TABLE COMM_SCHEDULE_ATTACHMENTS ADD CONSTRAINT COMM_SCHEDULE_ATTACHMENTS_PK
PRIMARY KEY (ATTACHMENT_ID)
/