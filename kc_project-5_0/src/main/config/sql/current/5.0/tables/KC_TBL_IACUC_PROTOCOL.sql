ALTER TABLE 
	IACUC_PROTOCOL 
MODIFY
	APPLICATION_DATE null
/

ALTER TABLE 
	IACUC_PROTOCOL 
MODIFY (  
	PROJECT_TYPE_CODE VARCHAR2(3),
	PROTOCOL_STATUS_CODE VARCHAR2(3),
	PROTOCOL_TYPE_CODE VARCHAR2(3)
	)	
/

