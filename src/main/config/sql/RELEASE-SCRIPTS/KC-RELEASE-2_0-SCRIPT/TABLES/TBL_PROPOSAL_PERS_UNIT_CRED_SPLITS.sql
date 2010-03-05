CREATE TABLE PROPOSAL_PERS_UNIT_CRED_SPLITS (
    PPU_CREDIT_SPLIT_ID number (22, 0) NOT NULL, 
    PROPOSAL_PERSON_UNIT_ID number (22, 0) NOT NULL, 
    INV_CREDIT_TYPE_CODE varchar2 (3) NOT NULL, 
    CREDIT decimal (5, 2) , 
    UPDATE_TIMESTAMP date NOT NULL, 
    UPDATE_USER VARCHAR2 (60) NOT NULL, 
    VER_NBR decimal (8) NOT NULL, 
    OBJ_ID varchar2 (36) NOT NULL) ;

ALTER TABLE PROPOSAL_PERS_UNIT_CRED_SPLITS 
    ADD CONSTRAINT PK_PPU_CREDIT_SPLIT 
            PRIMARY KEY (PPU_CREDIT_SPLIT_ID) ;