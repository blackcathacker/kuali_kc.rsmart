ALTER TABLE ACCOUNT_TYPE 
ADD CONSTRAINT PK_ACCOUNT_TYPE 
PRIMARY KEY (ACCOUNT_TYPE_CODE);

ALTER TABLE AFFILIATION_TYPE 
ADD CONSTRAINT PK_AFFILIATION_TYPE 
PRIMARY KEY (AFFILIATION_TYPE_CODE);

ALTER TABLE AWARD
ADD CONSTRAINT PK_AWARD
PRIMARY KEY (AWARD_ID);

ALTER TABLE AWARD_AMOUNT_INFO 
ADD CONSTRAINT PK_AWARD_AMOUNT_INFO 
PRIMARY KEY (AWARD_AMOUNT_INFO_ID);

alter table AWARD_AMT_FNA_DISTRIBUTION 
ADD CONSTRAINT PK_AWARD_AMT_FNA_DISTRIBUTION 
PRIMARY KEY(AWARD_AMT_FNA_DISTRIBUTION_ID) ENABLE;

ALTER TABLE AWARD_APPROVED_EQUIPMENT 
ADD CONSTRAINT PK_AWARD_APPRV_EQUIP 
PRIMARY KEY (AWARD_APPROVED_EQUIPMENT_ID);

ALTER TABLE AWARD_APPROVED_FOREIGN_TRAVEL 
ADD CONSTRAINT PK_AWARD_APPRV_FORN_TRVL 
PRIMARY KEY (AWARD_APPR_FORN_TRAVEL_ID);

alter table AWARD_APPROVED_SUBAWARDS 
ADD CONSTRAINT PK_AWARD_APPROVED_SUBAWARD 
PRIMARY KEY(AWARD_APPROVED_SUBAWARD_ID) ENABLE;

ALTER TABLE AWARD_BASIS_OF_PAYMENT 
ADD CONSTRAINT PK_AWARD_BASIS_OF_PAYMENT 
PRIMARY KEY (BASIS_OF_PAYMENT_CODE);

alter table AWARD_COMMENT 
ADD CONSTRAINT PK_AWARD_COMMENT 
PRIMARY KEY(AWARD_COMMENT_ID) ENABLE;

alter table AWARD_COST_SHARE 
ADD CONSTRAINT PK_AWARD_COST_SHARE 
PRIMARY KEY(AWARD_COST_SHARE_ID) ENABLE;

ALTER TABLE AWARD_DOCUMENT
ADD CONSTRAINT PK_AWARD_DOCUMENT
PRIMARY KEY (DOCUMENT_NUMBER);

ALTER TABLE AWARD_EXEMPT_NUMBER 
ADD CONSTRAINT PK_AWARD_EXEMPT_NUMBER 
PRIMARY KEY (AWARD_EXEMPT_NUMBER_ID);

ALTER TABLE AWARD_IDC_RATE 
ADD CONSTRAINT PK_AWARD_IDC_RATE 
PRIMARY KEY (AWARD_IDC_RATE_ID);

ALTER TABLE AWARD_METHOD_OF_PAYMENT 
ADD CONSTRAINT PK_AWARD_METHOD_OF_PAYMENT 
PRIMARY KEY (METHOD_OF_PAYMENT_CODE);

ALTER TABLE AWARD_PAYMENT_SCHEDULE 
ADD CONSTRAINT PK_AWARD_PAYMENT_SCHEDULE 
PRIMARY KEY (AWARD_PAYMENT_SCHEDULE_ID);

ALTER TABLE AWARD_PERSONS 
ADD CONSTRAINT PK_AWARD_PERSONS 
PRIMARY KEY (AWARD_PERSON_ID);

ALTER TABLE AWARD_PERSON_UNITS 
ADD CONSTRAINT PK_AWARD_PERSON_UNITS 
PRIMARY KEY (AWARD_PERSON_UNIT_ID);

ALTER TABLE AWARD_REP_TERMS_RECNT 
ADD CONSTRAINT PK_AWARD_REP_TERMS_RECNT
PRIMARY KEY (AWARD_REP_TERMS_RECNT_ID);

ALTER TABLE AWARD_REPORT_TERMS 
ADD CONSTRAINT PK_AWARD_REPORT_TERMS 
PRIMARY KEY (AWARD_REPORT_TERMS_ID);

ALTER TABLE AWARD_SPECIAL_REVIEW 
ADD CONSTRAINT PK_AWARD_SPECIAL_REVIEW 
PRIMARY KEY (AWARD_SPECIAL_REVIEW_ID);

ALTER TABLE AWARD_SPONSOR_TERM 
ADD CONSTRAINT PK_AWARD_SPONSOR_TERM 
PRIMARY KEY (AWARD_SPONSOR_TERM_ID);

ALTER TABLE AWARD_STATUS 
ADD CONSTRAINT PK_AWARD_STATUS 
PRIMARY KEY (STATUS_CODE);

ALTER TABLE AWARD_TEMPLATE 
ADD CONSTRAINT PK_AWARD_TEMPLATE 
PRIMARY KEY (AWARD_TEMPLATE_CODE);

ALTER TABLE AWARD_TEMPLATE_CONTACT 
ADD CONSTRAINT PK_AWARD_TEMPLATE_CONTACT 
PRIMARY KEY (AWARD_TEMPLATE_CONTACT_ID);

ALTER TABLE AWARD_TEMPLATE_COMMENTS 
ADD CONSTRAINT PK_AWARD_TEMPLATE_COMMENTS 
PRIMARY KEY (AWARD_TEMPLATE_COMMENTS_ID);

ALTER TABLE AWARD_TEMPLATE_REPORT_TERMS 
ADD CONSTRAINT PK_AWARD_TEMPLATE_REPORT_TERMS 
PRIMARY KEY (TEMPLATE_REPORT_TERMS_ID);

ALTER TABLE AWARD_TEMPL_REP_TERMS_RECNT 
ADD CONSTRAINT PK_TEMPL_REP_TERMS_RECNT 
PRIMARY KEY (TEMPL_REP_TERMS_RECNT_ID);

ALTER TABLE AWARD_TEMPLATE_TERMS 
ADD CONSTRAINT PK_AWARD_TEMPLATE_TERMS 
PRIMARY KEY (AWARD_TEMPLATE_TERMS_ID);

ALTER TABLE AWARD_TRANSFERRING_SPONSOR 
ADD CONSTRAINT PK_AWARD_TRANSFERRING_SPONSOR 
PRIMARY KEY (AWARD_TRANSFERRING_SPONSOR_ID);

ALTER TABLE AWARD_TYPE 
ADD CONSTRAINT PK_AWARD_TYPE 
PRIMARY KEY (AWARD_TYPE_CODE);

ALTER TABLE COMM_MEMBERSHIP_TYPE 
ADD CONSTRAINT PK_COMM_MEMBERSHIP_TYPE 
PRIMARY KEY (MEMBERSHIP_TYPE_CODE);

ALTER TABLE COMM_MEMBERSHIPS 
ADD CONSTRAINT PK_COMM_MEMBERSHIPS 
PRIMARY KEY (COMM_MEMBERSHIP_ID);
 
ALTER TABLE COMM_MEMBER_EXPERTISE 
ADD CONSTRAINT PK_COMM_MEMBER_EXPERTISE 
PRIMARY KEY (COMM_MEMBER_EXPERTISE_ID);

ALTER TABLE COMM_MEMBER_ROLES 
ADD CONSTRAINT PK_COMM_MEMBER_ROLES 
PRIMARY KEY (COMM_MEMBER_ROLES_ID);

ALTER TABLE COMM_RESEARCH_AREAS 
ADD CONSTRAINT PK_COMM_RESEARCH_AREAS 
PRIMARY KEY (ID);

alter table comm_schedule
  add constraint pk_comm_schedule_id
  primary key ( id );  

ALTER TABLE comm_schedule_attendance 
ADD CONSTRAINT pk_csa_id
PRIMARY KEY (id);  

alter table COMMENT_TYPE 
ADD CONSTRAINT PK_COMMENT_TYPE 
PRIMARY KEY(COMMENT_TYPE_CODE) ENABLE;

ALTER TABLE COMMITTEE 
ADD CONSTRAINT PK_COMMITTEE
PRIMARY KEY (ID);

ALTER TABLE COMMITTEE_DOCUMENT 
ADD CONSTRAINT PK_COMMITTEE_DOCUMENT 
PRIMARY KEY (DOCUMENT_NUMBER);

ALTER TABLE COMMITTEE_TYPE 
ADD CONSTRAINT PK_COMMITTEE_TYPE 
PRIMARY KEY (COMMITTEE_TYPE_CODE);

ALTER TABLE CONTACT_TYPE 
ADD CONSTRAINT PK_CONTACT_TYPE 
PRIMARY KEY (CONTACT_TYPE_CODE);

alter table COST_SHARE_TYPE 
ADD CONSTRAINT PK_COST_SHARE_TYPE 
PRIMARY KEY(COST_SHARE_TYPE_CODE) ENABLE;

ALTER TABLE DISTRIBUTION 
ADD CONSTRAINT PK_DISTRIBUTION 
PRIMARY KEY (OSP_DISTRIBUTION_CODE);

ALTER TABLE EXEMPT_STUDIES_CHECKLIST
ADD CONSTRAINT PK_EXEMPT_STUDIES_CHECKLIST
PRIMARY KEY (EXEMPT_STUDIES_CHECKLIST_CODE);

ALTER TABLE EXPEDITED_REVIEW_CHECKLIST
ADD CONSTRAINT PK_EXPEDITED_REVIEW_CHECKLIST
PRIMARY KEY (EXPEDITED_REV_CHKLST_CODE);

ALTER TABLE FREQUENCY 
ADD CONSTRAINT PK_FREQUENCY 
PRIMARY KEY (FREQUENCY_CODE);

ALTER TABLE FREQUENCY_BASE 
ADD CONSTRAINT PK_FREQUENCY_BASE 
PRIMARY KEY (FREQUENCY_BASE_CODE);

ALTER TABLE FUNDING_SOURCE_TYPE 
ADD CONSTRAINT PK_FUNDING_SOURCE_TYPE 
PRIMARY KEY (FUNDING_SOURCE_TYPE_CODE);

ALTER TABLE IDC_RATE_TYPE 
ADD CONSTRAINT PK_IDC_RATE_TYPE_CODE 
PRIMARY KEY (IDC_RATE_TYPE_CODE);

ALTER TABLE MEMBERSHIP_ROLE
ADD CONSTRAINT PK_MEMBERSHIP_ROLE 
PRIMARY KEY (MEMBERSHIP_ROLE_CODE);

ALTER TABLE PERSON_TRAINING 
ADD CONSTRAINT PK_PERSON_TRAINING 
PRIMARY KEY (PERSON_TRAINING_ID);

ALTER TABLE proto_corresp_type 
ADD CONSTRAINT pk_pct_proto_corresp_type
PRIMARY KEY (proto_corresp_type_code);   

ALTER TABLE PROTOCOL 
ADD CONSTRAINT PK_PROTOCOL 
PRIMARY KEY (PROTOCOL_NUMBER, SEQUENCE_NUMBER);

ALTER TABLE protocol_actions 
ADD CONSTRAINT pk_pa_action_id
PRIMARY KEY (action_id);    

ALTER TABLE PROTOCOL_ATTACHMENT_FILE
    ADD CONSTRAINT PK_PROTOCOL_ATTACHMENT_FILE
    PRIMARY KEY (PA_FILE_ID);

ALTER TABLE PROTOCOL_ATTACHMENT_GROUP
    ADD CONSTRAINT PK_PROTOCOL_ATTACHMENT_GROUP
    PRIMARY KEY (GROUP_CD);

ALTER TABLE PROTOCOL_ATTACHMENT_PERSONNEL
    ADD CONSTRAINT PK_PROTOCOL_ATTACH_PERSONNEL
    PRIMARY KEY (PA_PERSONNEL_ID);

ALTER TABLE PROTOCOL_ATTACHMENT_PROTOCOL
    ADD CONSTRAINT PK_PROTOCOL_ATTACH_PROTOCOL
    PRIMARY KEY (PA_PROTOCOL_ID);

ALTER TABLE PROTOCOL_ATTACHMENT_STATUS
    ADD CONSTRAINT PK_PROTOCOL_ATTACHMENT_STATUS
    PRIMARY KEY (STATUS_CD);

ALTER TABLE PROTOCOL_ATTACHMENT_TYPE
    ADD CONSTRAINT PK_PROTOCOL_ATTACHMENT_TYPE
    PRIMARY KEY (TYPE_CD);

ALTER TABLE PROTOCOL_ATTACHMENT_TYPE_GROUP
    ADD CONSTRAINT PK_PROTOCOL_ATTACH_TYPE_GROUP
    PRIMARY KEY (TYPE_GROUP_ID);

ALTER TABLE protocol_correspondence 
ADD CONSTRAINT pk_pc_id
PRIMARY KEY (id);    

ALTER TABLE PROTOCOL_DOCUMENT
ADD CONSTRAINT PK_PROTOCOL_DOCUMENT
PRIMARY KEY (DOCUMENT_NUMBER);

ALTER TABLE PROTOCOL_EXEMPT_NUMBER 
ADD CONSTRAINT PK_PROTOCOL_EXEMPT_NUMBER 
PRIMARY KEY (PROTOCOL_EXEMPT_NUMBER_ID);

ALTER TABLE PROTOCOL_FUNDING_SOURCE 
ADD CONSTRAINT PK_PROTOCOL_FUNDING_SOURCE 
PRIMARY KEY (PROTOCOL_FUNDING_SOURCE_ID); 

ALTER TABLE PROTOCOL_INVESTIGATORS 
ADD CONSTRAINT PK_PROTOCOL_INVESTIGATORS 
PRIMARY KEY (PROTOCOL_INVESTIGATORS_ID);

ALTER TABLE PROTOCOL_KEY_PERSONS 
ADD CONSTRAINT PK_PROTOCOL_KEY_PERSONS 
PRIMARY KEY (PROTOCOL_KEY_PERSONS_ID);

ALTER TABLE PROTOCOL_LOCATION 
ADD CONSTRAINT PK_PROTOCOL_LOCATION 
PRIMARY KEY (PROTOCOL_LOCATION_ID);

ALTER TABLE PROTOCOL_ORG_TYPE 
ADD CONSTRAINT PK_PROTOCOL_ORG_TYPE 
PRIMARY KEY (PROTOCOL_ORG_TYPE_CODE);

ALTER TABLE PROTOCOL_PERSONS 
ADD CONSTRAINT PK_PROTOCOL_PERSONS 
PRIMARY KEY (PROTOCOL_PERSON_ID);

ALTER TABLE PROTOCOL_PERSON_ROLES 
ADD CONSTRAINT PK_PROTOCOL_PERSON_ROLES 
PRIMARY KEY (PROTOCOL_PERSON_ROLE_ID);

ALTER TABLE PROTOCOL_PERSON_ROLE_MAPPING 
ADD CONSTRAINT PK_PERSON_ROLE_MAPPING 
PRIMARY KEY (ROLE_MAPPING_ID);

alter table protocol_reference_type
  add constraint pk_protocol_reference_type
  primary key ( protocol_reference_type_code );

alter table protocol_references
  add constraint pk_protocol_reference_id
  primary key ( protocol_reference_id );

ALTER TABLE PROTOCOL_RESEARCH_AREAS 
ADD CONSTRAINT PK_PROTOCOL_RESEARCH_AREAS 
PRIMARY KEY (ID);

ALTER TABLE PROTOCOL_REVIEWER_TYPE
ADD CONSTRAINT PK_PROTOCOL_REVIEWER_TYPE
PRIMARY KEY (REVIEWER_TYPE_CODE);

ALTER TABLE PROTOCOL_REVIEW_TYPE 
ADD CONSTRAINT PK_PROTOCOL_REVIEW_TYPE 
PRIMARY KEY (PROTOCOL_REVIEW_TYPE_CODE);

ALTER TABLE PROTOCOL_RISK_LEVELS 
ADD CONSTRAINT PK_PROTOCOL_RISK_LEVELS 
PRIMARY KEY (PROTOCOL_RISK_LEVELS_ID);

ALTER TABLE PROTOCOL_SPECIAL_REVIEW 
ADD CONSTRAINT PK_PROTOCOL_SPECIAL_REVIEW 
PRIMARY KEY (PROTOCOL_SPECIAL_REVIEW_ID);

ALTER TABLE PROTOCOL_STATUS 
ADD CONSTRAINT PK_PROTOCOL_STATUS 
PRIMARY KEY (PROTOCOL_STATUS_CODE);

ALTER TABLE protocol_submission 
ADD CONSTRAINT pk_ps_protocol_submission
PRIMARY KEY (submission_number);  

ALTER TABLE PROTOCOL_TYPE 
ADD CONSTRAINT PK_PROTOCOL_TYPE 
PRIMARY KEY (PROTOCOL_TYPE_CODE);

ALTER TABLE PROTOCOL_UNITS 
ADD CONSTRAINT PK_PROTOCOL_UNITS 
PRIMARY KEY (PROTOCOL_UNITS_ID);

ALTER TABLE PROTOCOL_VULNERABLE_SUB 
ADD CONSTRAINT PK_PROTOCOL_VULNERABLE_SUB 
PRIMARY KEY (PROTOCOL_VULNERABLE_SUB_ID);

ALTER TABLE REPORT 
ADD CONSTRAINT PK_REPORT 
PRIMARY KEY (REPORT_CODE);

ALTER TABLE REPORT_CLASS 
ADD CONSTRAINT PK_REPORT_CLASS 
PRIMARY KEY (REPORT_CLASS_CODE);

ALTER TABLE RISK_LEVEL 
ADD CONSTRAINT PK_RISK_LEVEL 
PRIMARY KEY (RISK_LEVEL_CODE);

ALTER TABLE SPONSOR_TERM_TYPE 
ADD CONSTRAINT PK_SPONSOR_TERM_TYPE 
PRIMARY KEY (SPONSOR_TERM_TYPE_CODE);

ALTER TABLE SPONSOR_TERM 
ADD CONSTRAINT PK_SPONSOR_TERM 
PRIMARY KEY (SPONSOR_TERM_ID);

alter table schedule_status
  add constraint pk_schedule_status_code
  primary key ( schedule_status_code );

ALTER TABLE submission_status 
ADD CONSTRAINT pk_ss_submission_status_code
PRIMARY KEY (submission_status_code);

ALTER TABLE SUBMISSION_TYPE 
ADD CONSTRAINT PK_SUBMISSION_TYPE 
PRIMARY KEY (SUBMISSION_TYPE_CODE);

ALTER TABLE SUBMISSION_TYPE_QUALIFIER 
ADD CONSTRAINT PK_SUBMISSION_TYPE_QUALIFIER 
PRIMARY KEY (SUBMISSION_TYPE_QUAL_CODE);

ALTER TABLE TRAINING 
ADD CONSTRAINT PK_TRAINING 
PRIMARY KEY (TRAINING_CODE);

ALTER TABLE VALID_CLASS_REPORT_FREQ 
ADD CONSTRAINT PK_VALID_CLASS_REPORT_FREQ 
PRIMARY KEY (VALID_CLASS_REPORT_FREQ_ID);

ALTER TABLE VALID_FREQUENCY_BASE 
ADD CONSTRAINT PK_VALID_FREQUENCY_BASE 
PRIMARY KEY (VALID_FREQUENCY_BASE_ID);

ALTER TABLE VALID_RATES 
ADD CONSTRAINT PK_VALID_RATES 
PRIMARY KEY (VALID_RATES_ID);

ALTER TABLE VULNERABLE_SUBJECT_TYPE 
ADD CONSTRAINT PK_VULNERABLE_SUBJECT_TYPE 
PRIMARY KEY (VULNERABLE_SUBJECT_TYPE_CODE);

