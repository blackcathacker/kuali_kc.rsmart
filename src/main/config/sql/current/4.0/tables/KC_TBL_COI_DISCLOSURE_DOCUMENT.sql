DECLARE temp NUMBER;
BEGIN
        SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'COI_DISCLOSURE_DOCUMENT';
        IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE COI_DISCLOSURE_DOCUMENT CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE COI_DISCLOSURE_DOCUMENT  ( 
	DOCUMENT_NUMBER 	VARCHAR2(40) NOT NULL,
	VER_NBR         	NUMBER(8,0) DEFAULT 1 NOT NULL,
	UPDATE_TIMESTAMP	DATE NOT NULL,
	UPDATE_USER     	VARCHAR2(60) NOT NULL,
	OBJ_ID          	VARCHAR2(36) NOT NULL)
/
ALTER TABLE COI_DISCLOSURE_DOCUMENT 
ADD CONSTRAINT PK_COI_DISCLOSURE_DOCUMENT 
PRIMARY KEY (DOCUMENT_NUMBER)
/

