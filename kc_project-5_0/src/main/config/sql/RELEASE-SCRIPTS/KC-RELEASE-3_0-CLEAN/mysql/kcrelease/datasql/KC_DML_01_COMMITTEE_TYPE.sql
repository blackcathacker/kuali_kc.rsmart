delimiter /
TRUNCATE TABLE COMMITTEE_TYPE
/
INSERT INTO COMMITTEE_TYPE (COMMITTEE_TYPE_CODE,DESCRIPTION,UPDATE_USER,UPDATE_TIMESTAMP,OBJ_ID,VER_NBR)
    VALUES ('1','IRB','admin',NOW(),UUID(),1)
/
delimiter ;
