delimiter /
TRUNCATE TABLE COEUS_MODULE
/
INSERT INTO COEUS_MODULE (MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (1,'Award','admin',NOW(),UUID(),1)
/
INSERT INTO COEUS_MODULE (MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (2,'Institute Proposal','admin',NOW(),UUID(),1)
/
INSERT INTO COEUS_MODULE (MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (3,'Development Proposal','admin',NOW(),UUID(),1)
/
INSERT INTO COEUS_MODULE (MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (4,'Subcontracts','admin',NOW(),UUID(),1)
/
INSERT INTO COEUS_MODULE (MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (5,'Negotiations','admin',NOW(),UUID(),1)
/
INSERT INTO COEUS_MODULE (MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (6,'Person','admin',NOW(),UUID(),1)
/
INSERT INTO COEUS_MODULE (MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (7,'IRB','admin',NOW(),UUID(),1)
/
INSERT INTO COEUS_MODULE (MODULE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR) 
    VALUES (8,'Annual COI Disclosure','admin',NOW(),UUID(),1)
/
delimiter ;