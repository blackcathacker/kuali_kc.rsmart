--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

\. MYSQL/SEQUENCES/KC_SEQ_All.sql
\. MYSQL/TABLES/KC_TBL_AWARD_BUDGET_LIMIT.sql
\. MYSQL/TABLES/KC_TBL_AWARD_BUDGET_PERIOD_EXT.sql
\. MYSQL/TABLES/KC_TBL_AWARD_TEMPLATE_REPORT_TERMS.sql
\. MYSQL/TABLES/KC_TBL_AWD_BGT_PER_SUM_CALC_AMT.sql
\. MYSQL/TABLES/KC_TBL_BUDGET_DOCUMENT.sql
\. MYSQL/TABLES/KC_TBL_COST_ELEMENT.sql
\. MYSQL/TABLES/KC_TBL_EPS_PROP_YNQ.sql
\. MYSQL/TABLES/KC_TBL_NOTIFICATION_TYPE.sql
\. MYSQL/TABLES/KC_TBL_NOTIFICATION_TYPE_RECIPIENT.sql
\. MYSQL/TABLES/KC_TBL_PENDING_TRANSACTIONS.sql
\. MYSQL/TABLES/KC_TBL_PERSON_EDITABLE_FIELDS.sql
\. MYSQL/TABLES/KC_TBL_PROPOSAL_DOCUMENT.sql
\. MYSQL/TABLES/KC_TBL_PROPOSAL_LOG.sql
\. MYSQL/TABLES/KC_TBL_PROTOCOL_ATTACHMENT_PERSONNEL.sql
\. MYSQL/TABLES/KC_TBL_PROTOCOL_ATTACHMENT_PROTOCOL.sql
\. MYSQL/TABLES/KC_TBL_PROTOCOL_ONLN_RVWS.sql
\. MYSQL/TABLES/KC_TBL_PROTOCOL_PERSONS.sql
\. MYSQL/TABLES/KC_TBL_ROLODEX.sql
\. MYSQL/TABLES/KC_TBL_SPECIAL_REVIEW.sql
\. MYSQL/TABLES/KC_TBL_WATERMARK.sql
\. MYSQL/TABLES/KC_TBL_YNQ.sql
\. MYSQL/DML/KC_DML_BS1_ARG_VALUE_LOOKUP.sql
\. MYSQL/DML/KC_DML_BS1_COST_ELEMENT.sql
\. MYSQL/DML/KC_DML_BS1_MINUTE_ENTRY_CODE.sql
\. MYSQL/DML/KC_DML_BS1_NARRATIVE_TYPE.sql
\. MYSQL/DML/KC_DML_BS1_PROTOCOL_ACTION_TYPE.sql
\. MYSQL/DML/KC_DML_BS1_QUESTION.sql
\. MYSQL/DML/KC_DML_BS1_QUESTIONNAIRE.sql
\. MYSQL/DML/KC_DML_BS1_SPECIAL_REVIEW.sql
\. MYSQL/DML/KC_DML_BS1_TRAINING_STIPEND_RATES.sql
\. MYSQL/DML/KC_DML_BS1_YNQ.sql
\. MYSQL/DML/KC_DML_BS2_QUESTIONNAIRE_QUESTIONS.sql
\. MYSQL/DML/KC_DML_BS2_QUESTIONNAIRE_USAGE.sql
\. MYSQL/DML/KC_DML_BS2_QUESTION_EXPLANATION.sql
\. MYSQL/CONSTRAINTS/KC_FK_AWARD_BUDGET_LIMIT.sql
\. MYSQL/CONSTRAINTS/KC_FK_AWD_BUDGET_PER_SUM_CALC_AMT.sql
\. MYSQL/CONSTRAINTS/KC_FK_NOTIFICATION_TYPE.sql
\. MYSQL/CONSTRAINTS/KC_FK_NOTIFICATION_TYPE_RECIPIENT.sql
\. MYSQL/CONSTRAINTS/KC_UQ_AWD_BUDGET_PER_SUM_CALC_AMT.sql
\. MYSQL/CONSTRAINTS/KC_UQ_NOTIFICATION_TYPE.sql
commit;
exit
