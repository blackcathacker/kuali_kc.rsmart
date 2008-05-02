 CREATE TABLE S2S_APP_SUBMISSION 
   (	"PROPOSAL_NUMBER" VARCHAR2(8) constraint S2S_APP_SUBMISSIONN1 NOT NULL ENABLE, 
	"SUBMISSION_NUMBER" NUMBER(3,0) constraint S2S_APP_SUBMISSIONN2 NOT NULL ENABLE, 
	"COMMENTS" VARCHAR2(2000), 
	"STATUS" VARCHAR2(50), 
	"GG_TRACKING_ID" VARCHAR2(50), 
	"AGENCY_TRACKING_ID" VARCHAR2(50), 
	"RECEIVED_DATE" DATE, 
	"LAST_MODIFIED_DATE" DATE, 
	"LAST_NOTIFIED_DATE" DATE, 
	"UPDATE_TIMESTAMP" DATE, 
	"UPDATE_USER" VARCHAR2(60), 
	 "VER_NBR" NUMBER(8,0) DEFAULT 1  constraint  S2S_APP_SUBMISSIONN3  NOT NULL ENABLE,
	"OBJ_ID" VARCHAR2(36) DEFAULT SYS_GUID()  constraint  S2S_APP_SUBMISSIONN4  NOT NULL ENABLE,
	CONSTRAINT "PK_S2S_APP_SUBMISSION_KRA" PRIMARY KEY ("PROPOSAL_NUMBER", "SUBMISSION_NUMBER") ENABLE
);

ALTER TABLE S2S_APP_SUBMISSION ADD (CONSTRAINT "FK_S2S_APP_SUBMISSION_KRA" FOREIGN KEY ("PROPOSAL_NUMBER")
	  REFERENCES "EPS_PROPOSAL" ("PROPOSAL_NUMBER") );
	  

