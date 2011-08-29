CREATE TABLE PERSON_FIN_INT_DISCLOSURE ( 
    PERSON_FIN_INT_DISCLOSURE_ID NUMBER(12,0) NOT NULL, 
    PERSON_ID VARCHAR2(40) NOT NULL, 
    ENTITY_NUMBER VARCHAR2(10) NOT NULL, 
    SEQUENCE_NUMBER NUMBER(4,0) NOT NULL, 
    STATUS_CODE VARCHAR2(3) NOT NULL, 
    STATUS_DESCRIPTION VARCHAR2(2000), 
    ENTITY_NAME VARCHAR2(60) NOT NULL, 
    ENTITY_TYPE_CODE NUMBER(3,0) NOT NULL, 
    ENTITY_OWNERSHIP_TYPE CHAR(1), 
    RELATIONSHIP_TYPE_CODE VARCHAR2(3) NOT NULL, 
    RELATIONSHIP_DESCRIPTION VARCHAR2(2000), 
    RELATED_TO_ORG_FLAG CHAR(1) NOT NULL, 
    CURRENT_FLAG CHAR(1) NOT NULL, 
    ORG_RELATION_DESCRIPTION VARCHAR2(2000), 
    SPONSOR_CODE VARCHAR2(6), 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) NOT NULL)
/
ALTER TABLE PERSON_FIN_INT_DISCLOSURE 
ADD CONSTRAINT PK_PERSON_FIN_INT_DISCLOSURE 
PRIMARY KEY (PERSON_FIN_INT_DISCLOSURE_ID)
/
