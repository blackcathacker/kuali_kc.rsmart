CREATE OR REPLACE VIEW OSP$VALID_CLASS_REPORT_FREQ AS 
SELECT REPORT_CLASS_CODE,
       REPORT_CODE,
       FREQUENCY_CODE,
       UPDATE_TIMESTAMP,
       UPDATE_USER
FROM   VALID_CLASS_REPORT_FREQ;