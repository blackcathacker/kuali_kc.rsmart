DELIMITER /

INSERT INTO IACUC_AFFILIATION_TYPE ( VER_NBR, AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, ACTIVE_FLAG )
VALUES ( 1, 1, 'Faculty', NOW(), 'admin', UUID(), 'Y' )
/

INSERT INTO IACUC_AFFILIATION_TYPE ( VER_NBR, AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, ACTIVE_FLAG )
VALUES ( 1, 2, 'Non-Faculty', NOW(), 'admin', UUID(), 'Y' )
/

INSERT INTO IACUC_AFFILIATION_TYPE ( VER_NBR, AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, ACTIVE_FLAG )
VALUES ( 1, 3, 'Affiliate', NOW(), 'admin', UUID(), 'Y' )
/

INSERT INTO IACUC_AFFILIATION_TYPE ( VER_NBR, AFFILIATION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, OBJ_ID, ACTIVE_FLAG )
VALUES ( 1, 4, 'Student', NOW(), 'admin', UUID(), 'Y' )
/

DELIMITER ;