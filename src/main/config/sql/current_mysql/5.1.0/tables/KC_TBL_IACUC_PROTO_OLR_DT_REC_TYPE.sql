DELIMITER /
CREATE TABLE IACUC_PROTO_OLR_DT_REC_TYPE (
    REVIEW_DETERM_RECOM_CD DECIMAL(3,0) NOT NULL,
    DESCRIPTION VARCHAR(200) NOT NULL,
    UPDATE_TIMESTAMP DATE NOT NULL,
    UPDATE_USER VARCHAR(60) NOT NULL,
    VER_NBR DECIMAL(8,0) DEFAULT 1 NOT NULL,
    OBJ_ID VARCHAR(36) NOT NULL)
/

ALTER TABLE IACUC_PROTO_OLR_DT_REC_TYPE
ADD CONSTRAINT PK_IACUC_PROTO_OLR_DT_REC_TYPE
PRIMARY KEY (REVIEW_DETERM_RECOM_CD)
/

DELIMITER ;
