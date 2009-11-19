CREATE OR REPLACE VIEW OSP$AWARD_AMT_FNA_DISTRIBUTION AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER,
	AMOUNT_SEQUENCE_NUMBER AMOUNT_SEQUENCE_NUMBER,
	BUDGET_PERIOD BUDGET_PERIOD,
	START_DATE START_DATE, 
	END_DATE END_DATE,
	DIRECT_COST DIRECT_COST, 
	INDIRECT_COST INDIRECT_COST, 
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_AMT_FNA_DISTRIBUTION;

CREATE OR REPLACE VIEW OSP$AWARD_CLOSEOUT AS 
SELECT C.AWARD_NUMBER MIT_AWARD_NUMBER, 
C.SEQUENCE_NUMBER SEQUENCE_NUMBER, 
C.FINAL_SUBMISSION_DATE FINAL_INV_SUBMISSION_DATE, 
D.FINAL_SUBMISSION_DATE FINAL_TECH_SUBMISSION_DATE, 
E.FINAL_SUBMISSION_DATE FINAL_PATENT_SUBMISSION_DATE, 
F.FINAL_SUBMISSION_DATE FINAL_PROP_SUBMISSION_DATE, 
B.ARCHIVE_LOCATION ARCHIVE_LOCATION, 
B.CLOSEOUT_DATE CLOSEOUT_DATE, 
C.UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
C.UPDATE_USER UPDATE_USER 
FROM 
(SELECT C.AWARD_ID, C.AWARD_NUMBER, C.SEQUENCE_NUMBER, C.UPDATE_USER, C.update_timestamp, C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '1') C, 
(SELECT C.AWARD_ID, C.AWARD_NUMBER, C.SEQUENCE_NUMBER, C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '4') D, 
(SELECT C.AWARD_ID, C.AWARD_NUMBER, C.SEQUENCE_NUMBER, C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '3') E, 
(SELECT C.AWARD_ID, C.AWARD_NUMBER, C.SEQUENCE_NUMBER, C.FINAL_SUBMISSION_DATE FROM AWARD_CLOSEOUT C WHERE C.CLOSEOUT_REPORT_CODE = '2') F, 
AWARD B 
WHERE C.AWARD_ID = B.AWARD_ID AND D.AWARD_ID = B.AWARD_ID 
AND E.AWARD_ID = B.AWARD_ID AND F.AWARD_ID = B.AWARD_ID; 

CREATE OR REPLACE VIEW OSP$AWARD_COST_SHARING
AS
   SELECT
    AWARD_NUMBER MIT_AWARD_NUMBER,
    SEQUENCE_NUMBER SEQUENCE_NUMBER,
    FISCAL_YEAR FISCAL_YEAR,
    COST_SHARE_PERCENTAGE COST_SHARING_PERCENTAGE,
    COST_SHARE_TYPE_CODE COST_SHARING_TYPE_CODE,
    SOURCE SOURCE_ACCOUNT,
    DESTINATION DESTINATION_ACCOUNT,
    COMMITMENT_AMOUNT AMOUNT,
    UPDATE_TIMESTAMP UPDATE_TIMESTAMP,
    UPDATE_USER UPDATE_USER
     FROM
    AWARD_COST_SHARE;
CREATE OR REPLACE VIEW OSP$AWARD_COMMENTS
AS
   SELECT
    AWARD_NUMBER MIT_AWARD_NUMBER,
    SEQUENCE_NUMBER SEQUENCE_NUMBER,
    COMMENT_TYPE_CODE COMMENT_CODE,
    CHECKLIST_PRINT_FLAG CHECKLIST_PRINT_FLAG,
    COMMENTS COMMENTS,
    UPDATE_TIMESTAMP UPDATE_TIMESTAMP,
    UPDATE_USER UPDATE_USER
     FROM
    AWARD_COMMENT;
    
CREATE OR REPLACE VIEW OSP$AWARD_DOCUMENT_TERMS AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER,
	SPONSOR_TERM_ID EQUIPMENT_APPROVAL_CODE,
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_SPONSOR_TERM where exists (select sponsor_term_type_code from sponsor_term where AWARD_SPONSOR_TERM.SPONSOR_TERM_ID = sponsor_term.SPONSOR_TERM_ID and sponsor_term_type_code='6');

CREATE OR REPLACE VIEW OSP$AWARD_EQUIPMENT_TERMS AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER,
	SPONSOR_TERM_ID EQUIPMENT_APPROVAL_CODE,
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_SPONSOR_TERM where exists (select sponsor_term_type_code from sponsor_term where AWARD_SPONSOR_TERM.SPONSOR_TERM_ID = sponsor_term.SPONSOR_TERM_ID and sponsor_term_type_code='1');

CREATE OR REPLACE VIEW OSP$AWARD_HEADER( "MIT_AWARD_NUMBER", "SEQUENCE_NUMBER", "PROPOSAL_NUMBER", "TITLE", "AWARD_TYPE_CODE", "SPECIAL_EB_RATE_OFF_CAMPUS", "SPECIAL_EB_RATE_ON_CAMPUS", "PRE_AWARD_AUTHORIZED_AMOUNT", "PRE_AWARD_EFFECTIVE_DATE", "CFDA_NUMBER", "DFAFS_NUMBER", "SUB_PLAN_FLAG", "PROCUREMENT_PRIORITY_CODE", "PRIME_SPONSOR_CODE", "NON_COMPETING_CONT_PRPSL_DUE", "COMPETING_RENEWAL_PRPSL_DUE", "BASIS_OF_PAYMENT_CODE", "METHOD_OF_PAYMENT_CODE", "PAYMENT_INVOICE_FREQ_CODE", "INVOICE_NUMBER_OF_COPIES", "FINAL_INVOICE_DUE", "ACTIVITY_TYPE_CODE", "ACCOUNT_TYPE_CODE", "UPDATE_TIMESTAMP", "UPDATE_USER" )
AS
   SELECT
    AWARD_NUMBER,
    SEQUENCE_NUMBER,
    PROPOSAL_NUMBER,
    TITLE,
    AWARD_TYPE_CODE,
    SPECIAL_EB_RATE_OFF_CAMPUS,
    SPECIAL_EB_RATE_ON_CAMPUS,
    PRE_AWARD_AUTHORIZED_AMOUNT,
    PRE_AWARD_EFFECTIVE_DATE,
    CFDA_NUMBER,
    DFAFS_NUMBER,
    SUB_PLAN_FLAG,
    PROCUREMENT_PRIORITY_CODE,
    PRIME_SPONSOR_CODE,
    NON_COMPETING_CONT_PRPSL_DUE,
    COMPETING_RENEWAL_PRPSL_DUE,
    BASIS_OF_PAYMENT_CODE,
    METHOD_OF_PAYMENT_CODE,
    PAYMENT_INVOICE_FREQ_CODE,
    INVOICE_NUMBER_OF_COPIES,
    FINAL_INVOICE_DUE,
    ACTIVITY_TYPE_CODE,
    ACCOUNT_TYPE_CODE,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    AWARD;
    
CREATE OR REPLACE VIEW OSP$AWARD_IDC_RATE AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER, 
	APPLICABLE_IDC_RATE APPLICABLE_IDC_RATE, 
	IDC_RATE_TYPE_CODE IDC_RATE_TYPE_CODE, 
	FISCAL_YEAR FISCAL_YEAR, 
	ON_CAMPUS_FLAG ON_CAMPUS_FLAG, 
	UNDERRECOVERY_OF_IDC UNDERRECOVERY_OF_IDC, 
	SOURCE_ACCOUNT SOURCE_ACCOUNT, 
	DESTINATION_ACCOUNT DESTINATION_ACCOUNT, 
	START_DATE START_DATE, 
	END_DATE END_DATE, 
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_IDC_RATE;

CREATE OR REPLACE VIEW OSP$AWARD_INVENTION_TERMS AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER,
	SPONSOR_TERM_ID EQUIPMENT_APPROVAL_CODE,
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_SPONSOR_TERM where exists (select sponsor_term_type_code from sponsor_term where AWARD_SPONSOR_TERM.SPONSOR_TERM_ID = sponsor_term.SPONSOR_TERM_ID and sponsor_term_type_code='2');

CREATE OR REPLACE VIEW OSP$AWARD_PAYMENT_SCHEDULE AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER, 
	DUE_DATE, 
	AMOUNT, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER, 
	SUBMIT_DATE, 
	SUBMITTED_BY, 
	INVOICE_NUMBER, 
	STATUS_DESCRIPTION
FROM AWARD_PAYMENT_SCHEDULE;

CREATE OR REPLACE VIEW OSP$AWARD_PRIOR_APPROVAL_TERMS AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER,
	SPONSOR_TERM_ID EQUIPMENT_APPROVAL_CODE,
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_SPONSOR_TERM where exists (select sponsor_term_type_code from sponsor_term where AWARD_SPONSOR_TERM.SPONSOR_TERM_ID = sponsor_term.SPONSOR_TERM_ID and sponsor_term_type_code='3');

CREATE OR REPLACE VIEW OSP$AWARD_PROPERTY_TERMS AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER,
	SPONSOR_TERM_ID EQUIPMENT_APPROVAL_CODE,
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_SPONSOR_TERM where exists (select sponsor_term_type_code from sponsor_term where AWARD_SPONSOR_TERM.SPONSOR_TERM_ID = sponsor_term.SPONSOR_TERM_ID and sponsor_term_type_code='4');

CREATE OR REPLACE VIEW OSP$AWARD_PUBLICATION_TERMS AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER,
	SPONSOR_TERM_ID EQUIPMENT_APPROVAL_CODE,
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_SPONSOR_TERM where exists (select sponsor_term_type_code from sponsor_term where AWARD_SPONSOR_TERM.SPONSOR_TERM_ID = sponsor_term.SPONSOR_TERM_ID and sponsor_term_type_code='5');

CREATE OR REPLACE VIEW OSP$AWARD_RIGHTS_IN_DATA_TERMS AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER,
	SPONSOR_TERM_ID EQUIPMENT_APPROVAL_CODE,
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_SPONSOR_TERM where exists (select sponsor_term_type_code from sponsor_term where AWARD_SPONSOR_TERM.SPONSOR_TERM_ID = sponsor_term.SPONSOR_TERM_ID and sponsor_term_type_code='7');

CREATE OR REPLACE VIEW OSP$AWARD_REPORT_TERMS ( MIT_AWARD_NUMBER, SEQUENCE_NUMBER, REPORT_CLASS_CODE, REPORT_CODE, FREQUENCY_CODE, FREQUENCY_BASE_CODE, OSP_DISTRIBUTION_CODE, CONTACT_TYPE_CODE, ROLODEX_ID, DUE_DATE, NUMBER_OF_COPIES, UPDATE_TIMESTAMP, UPDATE_USER )
AS
SELECT
AWARD_NUMBER MIT_AWARD_NUMBER,
SEQUENCE_NUMBER,
REPORT_CLASS_CODE,
REPORT_CODE,
FREQUENCY_CODE,
FREQUENCY_BASE_CODE,
OSP_DISTRIBUTION_CODE,
CONTACT_TYPE_CODE,
ROLODEX_ID,
DUE_DATE,
NUMBER_OF_COPIES,
A.UPDATE_TIMESTAMP,
A.UPDATE_USER
FROM AWARD_REPORT_TERMS A, AWARD_REP_TERMS_RECNT B WHERE A.AWARD_REPORT_TERMS_ID = B.AWARD_REPORT_TERMS_ID;

CREATE OR REPLACE VIEW OSP$AWARD_SUBCONTRACT_TERMS AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER,
	SPONSOR_TERM_ID EQUIPMENT_APPROVAL_CODE,
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_SPONSOR_TERM where exists (select sponsor_term_type_code from sponsor_term where AWARD_SPONSOR_TERM.SPONSOR_TERM_ID = sponsor_term.SPONSOR_TERM_ID and sponsor_term_type_code='8');

CREATE OR REPLACE VIEW OSP$AWARD_TRAVEL_TERMS AS SELECT 
	AWARD_NUMBER MIT_AWARD_NUMBER, 
	SEQUENCE_NUMBER SEQUENCE_NUMBER,
	SPONSOR_TERM_ID EQUIPMENT_APPROVAL_CODE,
	UPDATE_TIMESTAMP UPDATE_TIMESTAMP, 
	UPDATE_USER UPDATE_USER
FROM AWARD_SPONSOR_TERM where exists (select sponsor_term_type_code from sponsor_term where AWARD_SPONSOR_TERM.SPONSOR_TERM_ID = sponsor_term.SPONSOR_TERM_ID and sponsor_term_type_code='9');


CREATE OR REPLACE VIEW OSP$COMM_MEMBERSHIPS AS
SELECT
    COMMITTEE_ID,
    DECODE (PERSON_ID, NULL, TO_CHAR(ROLODEX_ID),
                             PERSON_ID) AS PERSON_ID,
    COMM_MEMBERSHIP_ID AS MEMBERSHIP_ID,
    SEQUENCE_NUMBER,
    PERSON_NAME,
    DECODE (PERSON_ID, NULL, 'N',
                             'Y') AS NON_EMPLOYEE_FLAG,
    PAID_MEMBER_FLAG,
    TERM_START_DATE,
    TERM_END_DATE,
    MEMBERSHIP_TYPE_CODE,
    COMMENTS,
    UPDATE_TIMESTAMP,
    UPDATE_USER
FROM COMM_MEMBERSHIPS;

CREATE OR REPLACE VIEW OSP$DISTRIBUTION
AS
   SELECT
    OSP_DISTRIBUTION_CODE,
    DESCRIPTION,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    DISTRIBUTION;


CREATE OR REPLACE VIEW OSP$FREQUENCY
AS
   SELECT
    FREQUENCY_CODE,
    DESCRIPTION,
    NUMBER_OF_DAYS,
    NUMBER_OF_MONTHS,
    REPEAT_FLAG,
    PROPOSAL_DUE_FLAG,
    INVOICE_FLAG,
    UPDATE_TIMESTAMP,
    UPDATE_USER,
    ADVANCE_NUMBER_OF_DAYS,
    ADVANCE_NUMBER_OF_MONTHS
     FROM
    FREQUENCY;
CREATE OR REPLACE VIEW OSP$IDC_RATE_TYPE
AS
   SELECT
    IDC_RATE_TYPE_CODE,
    DESCRIPTION,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    IDC_RATE_TYPE;

CREATE OR REPLACE VIEW OSP$MEMBERSHIP_ROLE AS SELECT
    MEMBERSHIP_ROLE_CODE,
    DESCRIPTION,
    UPDATE_TIMESTAMP,
    UPDATE_USER
FROM MEMBERSHIP_ROLE;
CREATE OR REPLACE VIEW OSP$PERSON_TRAINING AS SELECT 
	PERSON_ID, 
	TRAINING_NUMBER, 
	TRAINING_CODE, 
	DATE_REQUESTED, 
	DATE_SUBMITTED, 
	DATE_ACKNOWLEDGED, 
	FOLLOWUP_DATE, 
	SCORE, 
	COMMENTS, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM PERSON_TRAINING;

CREATE OR REPLACE VIEW OSP$PROTO_AMEND_RENEW_MODULES AS SELECT
  PROTO_AMEND_RENEWAL_NUMBER,
  PROTOCOL_NUMBER,
  PROTOCOL_MODULE_CODE,
  UPDATE_TIMESTAMP,
  UPDATE_USER
FROM PROTO_AMEND_RENEW_MODULES;

CREATE OR REPLACE VIEW OSP$PROTO_AMEND_RENEWAL AS SELECT
  PROTO_AMEND_REN_NUMBER,
  DATE_CREATED,
  SUMMARY,
  PROTOCOL_NUMBER,
  SEQUENCE_NUMBER,
  UPDATE_TIMESTAMP,
  UPDATE_USER
FROM PROTO_AMEND_RENEWAL;

CREATE OR REPLACE VIEW osp$proto_corresp_type AS SELECT
  proto_corresp_type_code,
  description,
  module_id,
  update_timestamp,
  update_user
FROM proto_corresp_type;
   
CREATE OR REPLACE VIEW OSP$PROTOCOL_SUBMISSION_DOC AS 
    SELECT pan.PROTOCOL_NUMBER, pan.SEQUENCE_NUMBER, paf.FILE_NAME, paf.FILE_DATA,
    pan.UPDATE_TIMESTAMP, pan.UPDATE_USER, pan.DOCUMENT_ID
    FROM PROTOCOL_ATTACHMENT_NOTIF pan, PROTOCOL_ATTACHMENT_FILE paf, PROTOCOL_SUBMISSION ps
    WHERE pan.FILE_ID_FK = paf.PA_FILE_ID AND pan.SUBMISSION_ID_FK = ps.SUBMISSION_ID;

CREATE or REPLACE VIEW OSP$PROTOCOL_CORRESPONDENCE AS SELECT
    PROTOCOL_NUMBER,
    SEQUENCE_NUMBER,
    ACTION_ID,
    PROTO_CORRESP_TYPE_CODE,
    CORRESPONDENCE,
    FINAL_FLAG,
    UPDATE_TIMESTAMP,
    UPDATE_USER
FROM PROTOCOL_CORRESPONDENCE;

CREATE OR REPLACE VIEW OSP$PROTOCOL_DOCUMENT_TYPE AS 
    SELECT t.TYPE_CD, t.DESCRIPTION, t.UPDATE_TIMESTAMP, t.UPDATE_USER, g.GROUP_CD
    FROM PROTOCOL_ATTACHMENT_TYPE t, PROTOCOL_ATTACHMENT_GROUP g, PROTOCOL_ATTACHMENT_TYPE_GROUP tg
    WHERE t.TYPE_CD = tg.TYPE_CD AND g.GROUP_CD = tg.GROUP_CD;

CREATE OR REPLACE VIEW OSP$PROTOCOL_EXEMPT_CHKLST AS SELECT
    PROTOCOL_NUMBER,
    SEQUENCE_NUMBER,
    SUBMISSION_NUMBER,
    EXEMPT_STUDIES_CHECKLIST_CODE,
    UPDATE_TIMESTAMP,
    UPDATE_USER
FROM PROTOCOL_EXEMPT_CHKLST;

CREATE OR REPLACE VIEW OSP$PROTOCOL_EXPIDITED_CHKLST AS SELECT
    PROTOCOL_NUMBER,
    SEQUENCE_NUMBER,
    SUBMISSION_NUMBER,
    EXPEDITED_REV_CHKLST_CODE,
    UPDATE_TIMESTAMP,
    UPDATE_USER
FROM PROTOCOL_EXPIDITED_CHKLST;

CREATE OR REPLACE VIEW OSP$PROTOCOL_FUNDING_SOURCE AS SELECT 
    PROTOCOL_NUMBER, 
    SEQUENCE_NUMBER, 
    FUNDING_SOURCE_TYPE_CODE, 
    FUNDING_SOURCE, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM PROTOCOL_FUNDING_SOURCE;
CREATE OR REPLACE VIEW OSP$PROTOCOL_INVESTIGATORS(
PROTOCOL_NUMBER, SEQUENCE_NUMBER, PERSON_ID, PERSON_NAME, NON_EMPLOYEE_FLAG,
PRINCIPAL_INVESTIGATOR_FLAG, AFFILIATION_TYPE_CODE, UPDATE_TIMESTAMP, UPDATE_USER)
AS SELECT PROTOCOL_NUMBER, SEQUENCE_NUMBER, 
cast(DECODE(TO_CHAR(ROLODEX_ID),NULL,PERSON_ID,TO_CHAR(ROLODEX_ID)) AS VARCHAR2(10)) PERSON_ID,
PERSON_NAME, DECODE(PERSON_ID,NULL,'Y','N') NON_EMPLOYEE_FLAG, 
DECODE(PROTOCOL_PERSON_ROLE_ID,'PI','Y','N') PRINCIPAL_INVESTIGATOR_FLAG,
AFFILIATION_TYPE_CODE, UPDATE_TIMESTAMP, UPDATE_USER
FROM PROTOCOL_PERSONS
WHERE PROTOCOL_PERSON_ROLE_ID IN ('PI','COI');


CREATE OR REPLACE VIEW OSP$PROTOCOL_KEY_PERSONS
(PROTOCOL_NUMBER, SEQUENCE_NUMBER, PERSON_ID, PERSON_NAME, PERSON_ROLE,
NON_EMPLOYEE_FLAG, AFFILIATION_TYPE_CODE, UPDATE_TIMESTAMP, UPDATE_USER)
AS 
SELECT
PROTOCOL_NUMBER, SEQUENCE_NUMBER,
DECODE(PERSON_ID,NULL,TO_CHAR(ROLODEX_ID),PERSON_ID) PERSON_ID, 
PERSON_NAME, B.DESCRIPTION PERSON_ROLE,
DECODE(PERSON_ID,NULL,'N','Y') NON_EMPLOYEE_FLAG, 
AFFILIATION_TYPE_CODE, A.UPDATE_TIMESTAMP UPDATE_TIMESTAMP, A.UPDATE_USER UPDATE_USER
FROM PROTOCOL_PERSONS A, PROTOCOL_PERSON_ROLES B 
WHERE B.PROTOCOL_PERSON_ROLE_ID = A.PROTOCOL_PERSON_ROLE_ID AND
A.PROTOCOL_PERSON_ROLE_ID NOT IN ('PI','COI');

CREATE OR REPLACE VIEW OSP$PROTOCOL_LOCATION
AS
   SELECT
    PROTOCOL_NUMBER,
    SEQUENCE_NUMBER,
    PROTOCOL_ORG_TYPE_CODE,
    ORGANIZATION_ID,
    ROLODEX_ID,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    PROTOCOL_LOCATION;

CREATE OR REPLACE VIEW OSP$PROTOCOL_MODULES AS SELECT
  PROTOCOL_MODULE_CODE,
  DESCRIPTION,
  UPDATE_TIMESTAMP,
  UPDATE_USER
FROM PROTOCOL_MODULES;

CREATE OR REPLACE VIEW OSP$PROTOCOL_ORG_TYPE
AS
   SELECT
    PROTOCOL_ORG_TYPE_CODE,
    DESCRIPTION,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    PROTOCOL_ORG_TYPE;

    CREATE OR REPLACE VIEW osp$protocol_reference_type
AS
   SELECT
    protocol_reference_type_code,
    description,
    update_timestamp,
    update_user
     FROM
    protocol_reference_type;
CREATE OR REPLACE VIEW osp$protocol_references
AS
   SELECT
    protocol_number,
    sequence_number,
    protocol_reference_number,
    protocol_reference_type_code,
    reference_key,
    application_date,
    approval_date,
    comments,
    update_timestamp,
    update_user
     FROM
    protocol_references;
CREATE OR REPLACE VIEW OSP$PROTOCOL_RESEARCH_AREAS AS SELECT 
  PROTOCOL_NUMBER,
  SEQUENCE_NUMBER,
  RESEARCH_AREA_CODE,
  UPDATE_TIMESTAMP, 
  UPDATE_USER
FROM PROTOCOL_RESEARCH_AREAS;

CREATE OR REPLACE VIEW OSP$PROTOCOL_REVIEWER_TYPE AS SELECT
  REVIEWER_TYPE_CODE,
  DESCRIPTION,
  UPDATE_TIMESTAMP,
  UPDATE_USER
FROM PROTOCOL_REVIEWER_TYPE;

CREATE VIEW OSP$PROTOCOL_REVIEW_TYPE
AS SELECT PROTOCOL_REVIEW_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, null as AVAILABLE_IN_LITE
FROM PROTOCOL_REVIEW_TYPE;

CREATE OR REPLACE VIEW OSP$PROTOCOL_REVIEWERS AS SELECT
    PROTOCOL_NUMBER,
    SEQUENCE_NUMBER,
    SUBMISSION_NUMBER,
    PERSON_ID,
    NON_EMPLOYEE_FLAG, 
    REVIEWER_TYPE_CODE,
    UPDATE_TIMESTAMP,
    UPDATE_USER
FROM PROTOCOL_REVIEWERS;

CREATE OR REPLACE VIEW OSP$PROTOCOL_RISK_LEVELS AS SELECT 
	PROTOCOL_NUMBER, 
	SEQUENCE_NUMBER, 
	RISK_LEVEL_CODE, 
	COMMENTS, 
	DATE_ASSIGNED, 
	DATE_UPDATED, 
	STATUS, 
	UPDATE_USER, 
	UPDATE_TIMESTAMP
FROM PROTOCOL_RISK_LEVELS;

CREATE VIEW OSP$PROTOCOL_SPECIAL_REVIEW
AS SELECT PROTOCOL_NUMBER,  
          0 as SEQUENCE_NUMBER, 
          SPECIAL_REVIEW_NUMBER,
          SPECIAL_REVIEW_CODE, 
          APPROVAL_TYPE_CODE, 
          null as SP_REV_PROTOCOL_NUMBER,
          APPLICATION_DATE, 
          APPROVAL_DATE, 
          COMMENTS,
          UPDATE_TIMESTAMP, 
          UPDATE_USER
FROM PROTOCOL_SPECIAL_REVIEW;

CREATE or REPLACE VIEW OSP$PROTOCOL_SUBMISSION_DOC AS SELECT
    PROTOCOL_NUMBER,
    SEQUENCE_NUMBER,
    SUBMISSION_NUMBER,
    DOCUMENT_ID,
    FILE_NAME,
    DOCUMENT,
    UPDATE_TIMESTAMP,
    UPDATE_USER
FROM PROTOCOL_SUBMISSION_DOC;

CREATE OR REPLACE VIEW OSP$PROTOCOL_TYPE
AS
   SELECT
    PROTOCOL_TYPE_CODE,
    DESCRIPTION,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    PROTOCOL_TYPE;
CREATE OR REPLACE VIEW OSP$PROTOCOL_UNITS
AS
   SELECT
    PROTOCOL_NUMBER,
    SEQUENCE_NUMBER,
    UNIT_NUMBER,
    LEAD_UNIT_FLAG,
    PERSON_ID,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    PROTOCOL_UNITS;
CREATE OR REPLACE VIEW OSP$PROTOCOL_VULNERABLE_SUB
AS
   SELECT
    PROTOCOL_NUMBER,
    SEQUENCE_NUMBER,
    VULNERABLE_SUBJECT_TYPE_CODE,
    SUBJECT_COUNT,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    PROTOCOL_VULNERABLE_SUB;

CREATE OR REPLACE VIEW OSP$QUESTIONNAIRE_USAGE AS SELECT 
    MODULE_ITEM_CODE, 
    MODULE_SUB_ITEM_CODE, 
    QUESTIONNAIRE_ID, 
    RULE_ID, 
    QUESTIONNAIRE_LABEL, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM QUESTIONNAIRE_USAGE;

CREATE OR REPLACE VIEW OSP$REPORT
AS
   SELECT
    REPORT_CODE,
    DESCRIPTION,
    FINAL_REPORT_FLAG,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    REPORT;
CREATE OR REPLACE VIEW OSP$RISK_LEVEL AS SELECT 
	RISK_LEVEL_CODE, 
	DESCRIPTION, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM RISK_LEVEL;
CREATE OR REPLACE VIEW osp$schedule_status AS SELECT 
  schedule_status_code,
  description,
  update_timestamp,
  update_user
FROM schedule_status;

CREATE OR REPLACE VIEW osp$submission_status AS SELECT
  submission_status_code,
  description,
  update_timestamp,
  update_user
FROM submission_status;

CREATE OR REPLACE VIEW OSP$SUBMISSION_TYPE AS SELECT
  SUBMISSION_TYPE_CODE,
  DESCRIPTION,
  UPDATE_TIMESTAMP,
  UPDATE_USER
FROM SUBMISSION_TYPE;

CREATE OR REPLACE VIEW OSP$SUBMISSION_TYPE_QUALIFIER AS SELECT
  SUBMISSION_TYPE_QUAL_CODE,
  DESCRIPTION,
  UPDATE_TIMESTAMP,
  UPDATE_USER
FROM SUBMISSION_TYPE_QUALIFIER;

CREATE OR REPLACE VIEW OSP$TRAINING AS SELECT 
    TRAINING_CODE, 
    DESCRIPTION, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM TRAINING;

CREATE OR REPLACE VIEW OSP$VALID_BASIS_METHOD_PMT AS SELECT 
    BASIS_OF_PAYMENT_CODE, 
    METHOD_OF_PAYMENT_CODE, 
    FREQUENCY_INDICATOR, 
    INV_INSTRUCTIONS_INDICATOR, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM VALID_BASIS_METHOD_PMT;

CREATE OR REPLACE VIEW OSP$VALID_CLASS_REPORT_FREQ AS SELECT 
	REPORT_CLASS_CODE, 
	REPORT_CODE, 
	FREQUENCY_CODE, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM VALID_CLASS_REPORT_FREQ;
CREATE OR REPLACE VIEW OSP$VALID_RATES
AS
   SELECT
    ON_CAMPUS_RATE,
    OFF_CAMPUS_RATE,
    RATE_CLASS_TYPE,
    ADJUSTMENT_KEY,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    VALID_RATES;
CREATE OR REPLACE VIEW OSP$VULNERABLE_SUBJECT_TYPE
AS
   SELECT
    VULNERABLE_SUBJECT_TYPE_CODE,
    DESCRIPTION,
    UPDATE_TIMESTAMP,
    UPDATE_USER
     FROM
    VULNERABLE_SUBJECT_TYPE;