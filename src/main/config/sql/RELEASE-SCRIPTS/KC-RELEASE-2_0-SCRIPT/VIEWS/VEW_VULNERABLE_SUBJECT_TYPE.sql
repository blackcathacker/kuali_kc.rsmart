CREATE OR REPLACE VIEW OSP$VULNERABLE_SUBJECT_TYPE AS 
SELECT VULNERABLE_SUBJECT_TYPE_CODE,
       DESCRIPTION,
       UPDATE_TIMESTAMP,
       UPDATE_USER
FROM   VULNERABLE_SUBJECT_TYPE;