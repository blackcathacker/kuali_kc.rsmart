/* Table Script */ 
CREATE TABLE FREQUENCY_BASE ( 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL, 
	FREQUENCY_BASE_CODE VARCHAR2(3) NOT NULL, 
	DESCRIPTION VARCHAR2(200) NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL)
/

/* Primary Key Constraint */ 
ALTER TABLE FREQUENCY_BASE 
ADD CONSTRAINT PK_FREQUENCY_BASE 
PRIMARY KEY (FREQUENCY_BASE_CODE)
/