create or replace view OSP$VALID_CE_RATE_TYPES as 
	select COST_ELEMENT,RATE_CLASS_CODE,RATE_TYPE_CODE,UPDATE_TIMESTAMP,UPDATE_USER
	from VALID_CE_RATE_TYPES;