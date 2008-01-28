 CREATE TABLE EPS_PROP_RATES 
   (	"PROPOSAL_NUMBER" VARCHAR2(12) constraint EPS_PROP_RATESN1 NOT NULL ENABLE, 
	"VERSION_NUMBER" NUMBER(3,0) constraint EPS_PROP_RATESN2 NOT NULL ENABLE, 
	"RATE_CLASS_CODE" NUMBER(3,0) constraint EPS_PROP_RATESN3 NOT NULL ENABLE, 
	"RATE_TYPE_CODE" NUMBER(3,0) constraint EPS_PROP_RATESN4 NOT NULL ENABLE, 
	"FISCAL_YEAR" CHAR(4) constraint EPS_PROP_RATESN5 NOT NULL ENABLE, 
	"ON_OFF_CAMPUS_FLAG" CHAR(1) constraint EPS_PROP_RATESN6 NOT NULL ENABLE, 
	"ACTIVITY_TYPE_CODE" VARCHAR2(3) constraint EPS_PROP_RATESN7 NOT NULL ENABLE, 
	"START_DATE" DATE constraint EPS_PROP_RATESN8 NOT NULL ENABLE, 
	"APPLICABLE_RATE" NUMBER(5,2) constraint EPS_PROP_RATESN9 NOT NULL ENABLE, 
	"INSTITUTE_RATE" NUMBER(5,2), 
	"UPDATE_TIMESTAMP" DATE constraint EPS_PROP_RATESN10 NOT NULL ENABLE, 
	"UPDATE_USER" VARCHAR2(8) constraint EPS_PROP_RATESN11 NOT NULL ENABLE, 
	 "VER_NBR" NUMBER(8,0) DEFAULT 1  constraint  EPS_PROP_RATESN12  NOT NULL ENABLE,
	"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID()  constraint  EPS_PROP_RATESN13  NOT NULL ENABLE,
	CONSTRAINT "PK_EPS_PROP_RATES_KRA" PRIMARY KEY ("PROPOSAL_NUMBER", "VERSION_NUMBER", "RATE_CLASS_CODE", "RATE_TYPE_CODE", "FISCAL_YEAR", "START_DATE", "ON_OFF_CAMPUS_FLAG") ENABLE
);
 
ALTER table EPS_PROP_RATES modify RATE_CLASS_CODE VARCHAR2(3);
ALTER table EPS_PROP_RATES modify RATE_TYPE_CODE VARCHAR2(3);

 ALTER TABLE EPS_PROP_RATES ADD (CONSTRAINT "FK_EPS_PROP_RATES_KRA" FOREIGN KEY ("PROPOSAL_NUMBER", "VERSION_NUMBER")
	  REFERENCES "BUDGET" ("PROPOSAL_NUMBER", "VERSION_NUMBER") ); 
 

 ALTER TABLE EPS_PROP_RATES ADD (CONSTRAINT "FK_EPS_PROP_RATES_ACTIVITY_KRA" FOREIGN KEY ("ACTIVITY_TYPE_CODE")
	  REFERENCES "ACTIVITY_TYPE" ("ACTIVITY_TYPE_CODE") ) ;

 ALTER TABLE EPS_PROP_RATES ADD (CONSTRAINT "FK_EPS_PROP_RATES_CLASS_KRA" FOREIGN KEY ("RATE_CLASS_CODE", "RATE_TYPE_CODE")
	  REFERENCES "RATE_TYPE" ("RATE_CLASS_CODE", "RATE_TYPE_CODE") ) ;
