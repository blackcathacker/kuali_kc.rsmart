DELIMITER /

CREATE TABLE SUBAWARD_ATTACHMENT_TYPE  
(
   ATTACHMENT_TYPE_CODE    DECIMAL(3,0) NOT NULL
   , DESCRIPTION           VARCHAR(200) NOT NULL
   , UPDATE_TIMESTAMP      DATE NOT NULL
   , UPDATE_USER           VARCHAR(60) NOT NULL
   , VER_NBR               DECIMAL(8,0) DEFAULT 1 NOT NULL
   , OBJ_ID                VARCHAR(36) NOT NULL
)
/

ALTER TABLE SUBAWARD_ATTACHMENT_TYPE 
ADD CONSTRAINT ATTACHMENT_TYPE_CODE_PK 
PRIMARY KEY (ATTACHMENT_TYPE_CODE) 
/

DELIMITER ;