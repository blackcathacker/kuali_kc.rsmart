Merge CX 5.2.1.x fixes into 6.0.0.x Notes
=========================================

Needs more attention
--------------------
* `cx_patches/src/main/config/sql/RELEASE-SCRIPTS/KC_Install.sh.patch`
  * Merge was *mostly* clean; rejects saved for reference.
  * Probably not worth messing with as long as:
  * TODO: manually merge all new KC6 SQL upgrade scripts into Flyway migrations.
  * Troubleshoot KC6 upgrade SQL errors/warnings:
    * `rice/KR_RICE_01_2.3.5-2.4.0_2014-01-09_B000.sql`: `ERROR 1832 (HY000) at line 21: Cannot change column 'attr_defn_id': used in a foreign key constraint 'krms_typ_attr_fk1'`
* TODO: Review TODO and FIXME comments from commit https://github.com/rSmart/kc_hr_rest/commit/e00cc7c54b29b80653446646b572f3addc0f64f3.

Complete
--------
* `cx_patches/pom.xml.patch`
  * Manually merged `cb38ce9` KualiCo pom.xml changes. `10c5684b4567511317c887261b582eb26d420816`
* `cx_patches/src/main/config/kew/xml/2/AwardDocument.xml.patch`
  * Manually merged patch conflict.
* `cx_patches/src/main/config/sql/current/5.2.1/dml/KR_DML_01_KRACOEUS-6640_B000.sql.patch`
  * No change necessary AFAICT.
* `cx_patches/src/main/config/sql/current/5.2.1/sequences/KC_SEQ_S2S_USER_ATTACHED_FORM_ID.sql.patch`
  * No change necessary AFAICT.
* `cx_patches/src/main/config/sql/current/5.2.1/sequences/KC_SEQ_S2S_USER_ATTD_FORM_ATT_ID.sql.patch`
  * No change necessary AFAICT.
* `cx_patches/src/main/config/sql/current_mysql/0.0.0/dml/KR_DML_01_KCINFR-1018_B000.sql.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/config/sql/current_mysql/5.2.1/dml/KR_DML_01_KRACOEUS-6640_B000.sql.patch`
  * No change necessary AFAICT.
* `cx_patches/src/main/config/sql/current_mysql/5.2.1/sequences/KC_SEQ_S2S_USER_ATTACHED_FORM_ATT_ID.sql.patch`
  * No change necessary AFAICT.
* `cx_patches/src/main/config/sql/current_mysql/5.2.1/sequences/KC_SEQ_S2S_USER_ATTACHED_FORM_ID.sql.patch`
  * No change necessary AFAICT.
* `cx_patches/src/main/config/sql/current_mysql/5.2.1/tables/KC_TBL_CONTACT_USAGE.sql.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/com/rsmart/kuali/kra/reporting/web/struts/action/ReportForwardAction.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/com/rsmart/kuali/kra/reporting/web/struts/form/ReportFormwardForm.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/com/rsmart/kuali/kra/web/jstl/PortalFunctions.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/award/contacts/AwardSponsorContactAuditRule.java.patch`
  * Manually merged patch conflict.
* `cx_patches/src/main/java/org/kuali/kra/award/document/authorization/AwardDocumentAuthorizer.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/award/home/Award.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/award/home/AwardAmountInfo.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/award/lookup/AwardLookupableHelperServiceImpl.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/award/printing/xmlstream/AwardBaseStream.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/award/web/struts/action/AwardHomeAction.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/bo/KcPerson.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/bo/UnitAdministrator.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/budget/printing/xmlstream/BudgetCumilativeXmlStream.java.patch`
  * Fix already applied upstream in KC6.
* `cx_patches/src/main/java/org/kuali/kra/budget/printing/xmlstream/BudgetSummaryXmlStream.java.patch`
  * Fix already applied upstream in KC6.
* `cx_patches/src/main/java/org/kuali/kra/budget/printing/xmlstream/IndustrialBudgetXmlStream.java.patch`
  * Fix already applied upstream in KC6.
* `cx_patches/src/main/java/org/kuali/kra/budget/web/struts/action/BudgetAction.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/coi/disclosure/CoiDisclosureServiceImpl.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/coi/notification/CoiNotificationRoleQualifierServiceImpl.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/coi/notification/FinancialEntityNotificationRenderer.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/coi/service/impl/CoiMessagesServiceImpl.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/committee/lookup/CommitteeLookupableHelperServiceImpl.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/common/committee/bo/CommitteeBase.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/common/committee/lookup/CommitteeLookupableHelperServiceImplBase.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/common/committee/meeting/CommitteeScheduleAttachmentsBase.java.patch`
  * Fix already applied upstream in KC6.
* `cx_patches/src/main/java/org/kuali/kra/common/committee/meeting/MeetingActionBase.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/common/committee/meeting/MeetingActionsActionBase.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/common/committee/notification/AgendaCreatedNotificationRenderer.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/common/notification/service/impl/KcNotificationServiceImpl.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/iacuc/actions/IacucProtocolActionsAction.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/iacuc/committee/lookup/IacucCommitteeLookupableHelperServiceImpl.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/iacuc/protocol/IacucProtocolProtocolAction.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/institutionalproposal/contacts/InstitutionalProposalPerson.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/institutionalproposal/contacts/InstitutionalProposalProjectPersonnelBean.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/institutionalproposal/proposallog/ProposalLogMergeAction.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/institutionalproposal/proposallog/service/impl/ProposalLogServiceImpl.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/institutionalproposal/service/impl/InstitutionalProposalLookupableHelperServiceImpl.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/institutionalproposal/service/impl/InstitutionalProposalServiceImpl.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/irb/actions/IrbProtocolActionRequestServiceImpl.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/irb/actions/notification/ProtocolWithdrawnNotificationRenderer.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/irb/actions/ProtocolProtocolActionsAction.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/irb/protocol/ProtocolProtocolAction.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/negotiations/notifications/NegotiationNotificationRenderer.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/notification/ProposalDevelopmentNotificationRenderer.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/rules/ProposalDevelopmentDocumentRule.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/web/struts/form/ProposalDevelopmentForm.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/protocol/actions/ActionHelperBase.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/protocol/notification/ProtocolReplacementParameters.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/questionnaire/answer/QuestionnaireAnswerServiceImpl.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/subaward/web/struts/action/SubAwardAction.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/subaward/web/struts/action/SubAwardActionsAction.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/subawardReporting/printing/service/impl/SubAwardPrintingServiceImpl.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/subawardReporting/printing/xmlstream/SubAwardFDPPrintXmlStream.java.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/timeandmoney/transactions/TransactionRuleImpl.java.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/java/org/kuali/kra/web/filter/PerformanceLoggingFilter.java.patch`
  * Abandoned patch; not important enough to merge manually.
* `cx_patches/src/main/resources/ApplicationResources.properties.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/resources/META-INF/kc-config-defaults.xml.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/resources/org/kuali/kra/CoreSpringBeans.xml.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/Award.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/AwardPerson.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/Budget.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/CustReportDetails.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/CustReportType.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/CustRptDefaultParms.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/CustRptTypeDocument.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/DevelopmentProposal.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/IacucProtocol.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/InstitutionalProposal.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/InstitutionalProposalPerson.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/LookupableDevelopmentProposal.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/Negotiation.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/NegotiationActivity.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/NegotiationUnassociatedDetail.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/ProposalDevelopmentDocument.xml.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/Protocol.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/Sponsor.xml.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/SubAward.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/SubAwardCopyRightsType.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/SubAwardCostType.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/SubAwardFundingSource.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/printing/stylesheet/AwardNotice.xsl.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/resources/org/kuali/kra/printing/stylesheet/BudgetSummaryTotalPage.xsl.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/resources/org/kuali/kra/printing/stylesheet/FDP_Modification_Template.xsl.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/printing/stylesheet/FDP_Template_Agreement.xsl.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/org/kuali/kra/RiceOverridesSpringBeans.xml.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/resources/repository.xml.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/webapp/css/bootstrap/css/bootstrap-skinned.css.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/bootstrap.css.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawesome-webfont.eot.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawesome-webfont.svg.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawesome-webfont.ttf.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawesome-webfont.woff.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawesome.eot.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/FontAwesome.otf.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawesome.svg.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawesome.ttf.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawsome.dev.svg.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawsome.svg.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawsome.ttf.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/fonts/fontawsome.woff.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/index.html.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/license.txt.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/lte-ie7.js.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/lte-ie7.txt.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/Read Me.txt.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/css/icons/style.css.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/img/glyphicons-halflings.png.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/img/logo-login.png.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/img/logo-watermark.png.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/bootstrap/img/logo.png.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/images/bl-bevel-focus.gif.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/css/rsmart.css.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/rice-portal/scripts/easyXDM/resize_intermediate.html.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/scripts/bootstrap/bootstrap.js.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/sponsorHierarchyMaint.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/static/images/checkbox.gif.patch`
  * No change required.
* `cx_patches/src/main/webapp/static/images/checked.gif.patch`
  * No change required.
* `cx_patches/src/main/webapp/unitHierarchy.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/award/awardHierarchyAwardActionsAjax.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/coi/FinancialEntityEditList.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/coi/FinancialEntityEditNew.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/coi/FinancialEntityManagement.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/coi/FinancialEntityView.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/coi/ViewNotification.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/iacuc/IacucResearchAreaAjax.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/irb/researchAreaAjax.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/meeting/IacucMeetingActions.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/meeting/IacucMeetingCorrespondenceAction.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/meeting/IacucMeetingManagement.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/meeting/meetingActions.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/meeting/meetingCorrespondenceAction.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/meeting/meetingManagement.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/multicampus_dummy_login.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/proposaldevelopment/ProposalDevelopmentKeyPersonnel.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/jsp/proposaldevelopment/ProposalDevelopmentProposal.jsp.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/webapp/WEB-INF/jsp/timeandmoney/awardHierarchyTimeAndMoneyAjax.jsp.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/struts-config.xml.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/webapp/WEB-INF/tags/budget/budgetVersions.tag.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/tags/coi/approveAction.tag.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/webapp/WEB-INF/tags/iacuc/onlinereview/onlineReview.tag.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/tags/infopage.tag.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/tags/irb/onlinereview/onlineReview.tag.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/tags/medusa/medusaNegotiationSummary.tag.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/tags/questionnaire/qnInfopage.tag.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/researcherAwards.tag.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/researcherCompliance.tag.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/researcherPersonnel.tag.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/researcherQuicklinks.tag.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/researcherReportLinks.tag.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/channel/unitQuicklinks.tag.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/portalBody.tag.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/portalResearcherBody.tag.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/tags/rice-portal/portalTabs.tag.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/webapp/WEB-INF/tlds/portal-func.tld.patch`
  * Patch merged cleanly.
* `cx_patches/src/main/webapp/WEB-INF/web.xml.patch`
  * Manually merged patch conflicts.
* `cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/document/authorizer/NarrativeReplaceAuthorizer.java.patch`
* `cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/document/authorizer/PersonnelAttachmentReplaceAuthorizer.java.patch`
  * Manually merged into /coeus-impl/src/main/java/org/kuali/coeus/propdev/impl/auth/ProposalDevelopmentDocumentAuthorizer.java
* `cx_patches/src/main/java/org/kuali/kra/service/impl/KraAuthorizationServiceImpl.java.patch`
  * Manually merged into /coeus-impl/src/main/java/org/kuali/coeus/common/impl/auth/perm/KcAuthorizationServiceImpl.java
* `cx_patches/src/main/java/org/kuali/kra/web/struts/action/KraTransactionalDocumentActionBase.java.patch`
  * Manually merged into /coeus-impl/src/main/java/org/kuali/coeus/sys/framework/controller/KcTransactionalDocumentActionBase.java
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/SponsorSpecial.xml.patch`
  * `SponsorSpecial.xml` removed in KC6 and similar change already made to Sponsor.xml
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/docs/SponsorMaintenanceDocument.xml.patch`
  * Not merged. This was removed in CX to avoid a conflict with SponsorSpecial, but in 6.0 SponsorSpecial has been removed so this is the only maint doc file for Sponsor now.
* `cx_patches/src/main/java/org/kuali/kra/bo/RolodexMaintainableImpl.java.patch`
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/docs/RolodexMaintenanceDocument.xml.patch`
* `cx_patches/src/main/resources/org/kuali/kra/datadictionary/Rolodex.xml.patch`
* `cx_patches/src/main/java/org/kuali/kra/proposaldevelopment/service/impl/ProposalCopyServiceImpl.java.patch`
  * Unnecessary in 6.0
* `cx_patches/src/main/java/org/kuali/kra/award/web/struts/action/AwardActionsAction.java.patch`
  * Patch merges but does not compile with Rice 2.5.x. -- Rice 2.5.x related errors fixed and patch applied
* `cx_patches/src/main/java/com/rsmart/kuali/kra/workflow/UnitAdministratorRoleAttribute.java.patch`
  * TODO: Review `getCode()` change; is the behavior equivalent? See commit `948d2708d8174b27ebb103921b53ca0861acafb1` -- verified this was the correct fix
* `cx_patches/src/main/java/org/kuali/kra/bo/Rolodex.java.patch`
  * Relevant changes from patch made to Rolodex.java
* `cx_patches/src/main/java/org/kuali/kra/budget/printing/xmlstream/BudgetBaseStream.java.patch`
  * Confirmed all changes appear to be in 6.0 version
* `cx_patches/src/main/java/org/kuali/kra/s2s/service/impl/S2SUtilServiceImpl.java.patch`
  * This change is being made upstream via https://github.com/kuali/kc-s2sgen/pull/50 and https://jira.kuali.org/browse/KRACOEUS-8594
