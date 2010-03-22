/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.kra.infrastructure.Constants.MAPPING_BASIC;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.bo.SponsorFormTemplateList;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.common.web.struts.form.ReportHelperBean;
import org.kuali.kra.common.web.struts.form.ReportHelperBeanContainer;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPrintingService;
import org.kuali.kra.institutionalproposal.proposaladmindetails.ProposalAdminDetails;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.kra.kim.service.KcGroupService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalChangedData;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalOverview;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.ProposalHierarchyKeyConstants;
import org.kuali.kra.proposaldevelopment.hierarchy.service.ProposalHierarchyService;
import org.kuali.kra.proposaldevelopment.printing.service.ProposalDevelopmentPrintingService;
import org.kuali.kra.proposaldevelopment.rule.event.CopyProposalEvent;
import org.kuali.kra.proposaldevelopment.rule.event.ProposalDataOverrideEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalCopyService;
import org.kuali.kra.proposaldevelopment.service.ProposalStateService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.s2s.S2SException;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sSubmissionType;
import org.kuali.kra.s2s.service.PrintService;
import org.kuali.kra.s2s.service.S2SService;
import org.kuali.kra.service.KraPersistenceStructureService;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kew.dto.ActionRequestDTO;
import org.kuali.rice.kew.dto.DocumentDetailDTO;
import org.kuali.rice.kew.dto.NetworkIdDTO;
import org.kuali.rice.kew.dto.ReportCriteriaDTO;
import org.kuali.rice.kew.engine.node.KeyValuePair;
import org.kuali.rice.kew.service.WorkflowInfo;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.question.ConfirmationQuestion;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KualiRuleService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.service.PersistenceStructureService;
import org.kuali.rice.kns.service.PessimisticLockService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.ErrorMessage;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.util.WebUtils;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;
import org.kuali.rice.kns.web.struts.form.KualiForm;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * Handles all of the actions from the Proposal Development Actions web page.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentActionsAction extends ProposalDevelopmentAction implements AuditModeAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentActionsAction.class);
    private static final String DOCUMENT_APPROVE_QUESTION = "DocApprove";
    private static final String DOCUMENT_ROUTE_QUESTION="DocRoute";
    private static final String DOCUMENT_REJECT_QUESTION="DocReject";
    
    private static final String CONFIRM_SUBMISSION_WITH_WARNINGS_KEY = "submitApplication";
    private static final String EMPTY_STRING = "";
    
    private static final int OK = 0;
    private static final int WARNING = 1;
    private static final int ERROR = 2;
    
    /**
     * Struts mapping for the Proposal web page.  
     */
    private static final String MAPPING_PROPOSAL = "proposal";
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward actionForward = super.execute(mapping, form, request, response);
        return actionForward;
    }

    /**
     * Calls the document service to approve the document
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @Override
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
        KualiWorkflowDocument workflowDoc = kualiDocumentFormBase.getDocument().getDocumentHeader().getWorkflowDocument();

        ActionForward forward;
        if (canGenerateRequestsInFuture(workflowDoc)) {
            forward = promptUserForInput(workflowDoc, mapping, form, request, response);
        } else {
            forward = super.approve(mapping, form, request, response);
        }
        
        ProposalDevelopmentDocument proposalDevelopmentDocument = ((ProposalDevelopmentForm) form).getDocument();
        if (autogenerateInstitutionalProposal() && proposalDevelopmentDocument.getInstitutionalProposalNumber() != null) {
            if (ProposalType.REVISION_TYPE_CODE.equals(proposalDevelopmentDocument.getDevelopmentProposal().getProposalTypeCode())) {
                GlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_VERSIONED, 
                        proposalDevelopmentDocument.getInstitutionalProposalNumber());
            } else {
                String proposalNumber = proposalDevelopmentDocument.getInstitutionalProposalNumber();
                GlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_CREATED, proposalNumber);
            }
        }
        
        return forward;
    }

    private ActionForward promptUserForInput(KualiWorkflowDocument workflowDoc, ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        
        if (question == null) {
            // ask question if not already asked
              return this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_APPROVE_QUESTION, KNSServiceLocator
                      .getKualiConfigurationService().getPropertyString(KeyConstants.QUESTION_APPROVE_FUTURE_REQUESTS),
                       KNSConstants.CONFIRMATION_QUESTION, methodToCall, "");             
        }
        else if(DOCUMENT_APPROVE_QUESTION.equals(question) && ConfirmationQuestion.NO.equals(buttonClicked)) {
            workflowDoc.setDoNotReceiveFutureRequests();
        }
        else if(DOCUMENT_APPROVE_QUESTION.equals(question)){ 
            workflowDoc.setReceiveFutureRequests();  
        }
        
        return super.approve(mapping, form, request, response);
    }   

    private boolean canGenerateRequestsInFuture(KualiWorkflowDocument workflowDoc) throws Exception {
        String loggedInUserID = GlobalVariables.getUserSession().getPerson().getPrincipalName();
        String loggedInPrincipalId = GlobalVariables.getUserSession().getPrincipalId();
        NetworkIdDTO networkId = new NetworkIdDTO(loggedInUserID);
        ReportCriteriaDTO reportCriteria = new ReportCriteriaDTO(new Long(workflowDoc.getRouteHeaderId()));
        reportCriteria.setTargetPrincipalIds(new String[] { loggedInPrincipalId });

        boolean receiveFutureRequests = false;
        boolean doNotReceiveFutureRequests = false;    

        List variables = workflowDoc.getRouteHeader().getVariables();
        if (CollectionUtils.isNotEmpty(variables)) {
            for (Object variable : variables) {
                KeyValuePair kvp = (KeyValuePair) variable;
                if (kvp.getKey().startsWith(KEWConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                        && kvp.getValue().toUpperCase().equals(KEWConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                        && kvp.getKey().contains(networkId.getNetworkId())) {
                    receiveFutureRequests = true; 
                    break;
                }
                else if (kvp.getKey().startsWith(KEWConstants.RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_KEY)
                      && kvp.getValue().toUpperCase().equals(KEWConstants.DONT_RECEIVE_FUTURE_REQUESTS_BRANCH_STATE_VALUE)
                      && kvp.getKey().contains(networkId.getNetworkId())) {
                    doNotReceiveFutureRequests = true; 
                    break;
                }
            }
        } 

        return ((receiveFutureRequests == false && doNotReceiveFutureRequests == false) && canGenerateMultipleApprovalRequests(reportCriteria, networkId));
    }
    
    private boolean canGenerateMultipleApprovalRequests(ReportCriteriaDTO reportCriteria, NetworkIdDTO networkId) throws Exception {
        int approvalRequestsCount = 0;
        WorkflowInfo info = new WorkflowInfo();
        
        DocumentDetailDTO results1 = info.routingReport(reportCriteria);
        for(ActionRequestDTO actionRequest : results1.getActionRequests() ){
            if(!actionRequest.isRoleRequest() && actionRequest.isPending() && actionRequest.getActionRequested().equalsIgnoreCase(KEWConstants.ACTION_REQUEST_APPROVE_REQ) && 
                    recipientMatchesUser(actionRequest, networkId)) {
                approvalRequestsCount+=1; 
            }
        }
          
        return (approvalRequestsCount > 1);
    }
    
    private boolean recipientMatchesUser(ActionRequestDTO actionRequest, NetworkIdDTO networkId) {
        KcGroupService groupService = KraServiceLocator.getService(KcGroupService.class);
        if(actionRequest != null && networkId != null) {
            String recipientUser = actionRequest.getPrincipalId();
            if(recipientUser != null && recipientUser.equals(networkId.getNetworkId())) {
                return true;
            } else {
                String recipientGroupId = actionRequest.getGroupId();
                return groupService.isMemberOfGroup(networkId.getNetworkId(), recipientGroupId);
            }
        }
        
        return false;  
    }
    
    /**
     * Updates an Editable Proposal Field and 
     * adds it to the Proposal Change History
     * 
     * @param mapping the Struct's Action Mapping.
     * @param form the Proposal Development Form.
     * @param request the HTTP request.
     * @param response the HTTP response
     * @return the next web page to display
     * @throws Exception
     */
    public ActionForward addProposalChangedData(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        KraPersistenceStructureService kraPersistenceStructureService = KraServiceLocator.getService(KraPersistenceStructureService.class);

        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDocument = pdForm.getDocument();
        ProposalChangedData newProposalChangedData = pdForm.getNewProposalChangedData();
        
        newProposalChangedData.setProposalNumber(pdDocument.getDevelopmentProposal().getProposalNumber());
        newProposalChangedData.setChangeNumber(getNextChangeNumber(boService, newProposalChangedData.getProposalNumber(), newProposalChangedData.getColumnName()));
        if(StringUtils.isEmpty(newProposalChangedData.getDisplayValue()) && StringUtils.isNotEmpty(newProposalChangedData.getChangedValue())) {
            newProposalChangedData.setDisplayValue(newProposalChangedData.getChangedValue());
        }
        
        String tmpLookupReturnPk = "";
        if(newProposalChangedData.getEditableColumn() != null) {
            tmpLookupReturnPk = newProposalChangedData.getEditableColumn().getLookupPkReturn();
        }
        
        newProposalChangedData.refreshReferenceObject("editableColumn");
        newProposalChangedData.getEditableColumn().setLookupPkReturn(tmpLookupReturnPk);
        
        if(newProposalChangedData.getEditableColumn() != null) {
            if(!newProposalChangedData.getEditableColumn().getHasLookup()) {
                newProposalChangedData.setDisplayValue(newProposalChangedData.getChangedValue());
            }
        }
            
        if(getKualiRuleService().applyRules(new ProposalDataOverrideEvent(pdForm.getDocument(), newProposalChangedData))){
            boService.save(newProposalChangedData);
        
            ProposalOverview proposalWrapper = createProposalWrapper(pdDocument);
            Map<String, String> columnToAttributesMap = kraPersistenceStructureService.getDBColumnToObjectAttributeMap(ProposalOverview.class);
            String proposalAttributeToPersist = columnToAttributesMap.get(newProposalChangedData.getColumnName());
            ObjectUtils.setObjectProperty(proposalWrapper, proposalAttributeToPersist, newProposalChangedData.getChangedValue());
            ObjectUtils.setObjectProperty(pdDocument.getDevelopmentProposal(), proposalAttributeToPersist, newProposalChangedData.getChangedValue());
            
            boService.save(proposalWrapper);
            pdForm.getDocument().setVersionNumber(proposalWrapper.getVersionNumber());
            
            pdForm.setNewProposalChangedData(new ProposalChangedData());
            growProposalChangedHistory(pdDocument, newProposalChangedData);
        }
        
        return mapping.findForward("basic");
    }
    
    private void growProposalChangedHistory(ProposalDevelopmentDocument pdDocument, ProposalChangedData newProposalChangedData) {
        Map<String, List<ProposalChangedData>> changeHistory = pdDocument.getDevelopmentProposal().getProposalChangeHistory();
        if(changeHistory.get(newProposalChangedData.getEditableColumn().getColumnLabel()) == null) {
            changeHistory.put(newProposalChangedData.getEditableColumn().getColumnLabel(), new ArrayList<ProposalChangedData>());
        } 
        
        changeHistory.get(newProposalChangedData.getEditableColumn().getColumnLabel()).add(0, newProposalChangedData);
    }
    
    private ProposalOverview createProposalWrapper(ProposalDevelopmentDocument pdDocument) throws Exception {
        ProposalOverview proposalWrapper = new ProposalOverview();
        PersistenceStructureService persistentStructureService = KraServiceLocator.getService(PersistenceStructureService.class);
        List<String> fieldsToUpdate = (List<String>) persistentStructureService.listFieldNames(ProposalOverview.class);
        for(String field: fieldsToUpdate) {
            boolean noSuchFieldPD = false;
            boolean noSuchFieldBO = false;
            Object tempVal = null;
            
            try {
                tempVal = ObjectUtils.getPropertyValue(pdDocument, field);
            } catch ( Exception e ) {
                noSuchFieldPD = true;
            }
            
            try {
                tempVal = ObjectUtils.getPropertyValue(pdDocument.getDevelopmentProposal(), field);
            } catch ( Exception e ) {
                noSuchFieldBO = true;
            }
            System.out.println();
            if( tempVal == null && noSuchFieldPD && noSuchFieldBO ) {
                LOG.warn("Could not find property " + field + " in ProposalDevelopmentDocument or DevelopmnentProposal bo.");
            }
            
            ObjectUtils.setObjectProperty(proposalWrapper, field, (tempVal != null) ? tempVal : null);
        }
        return proposalWrapper;
    } 
    
    private int getNextChangeNumber(BusinessObjectService boService, String proposalNumber, String columnName) {
        int changeNumber = 0;
        Map<String, Object> keyMap = new HashMap<String, Object>();
        keyMap.put("proposalNumber", proposalNumber);
        keyMap.put("columnName", columnName);
        List<ProposalChangedData> changedDataList = (List<ProposalChangedData>) boService.findMatchingOrderBy(ProposalChangedData.class, keyMap, "changeNumber", true);
        if(CollectionUtils.isNotEmpty(changedDataList)) {
            changeNumber = ((ProposalChangedData) changedDataList.get(changedDataList.size()-1)).getChangeNumber();
        }
        
        return ++changeNumber;
    }

    /**
     * Copies a Proposal Development Document based upon user-specified criteria.
     * 
     * @param mapping the Struct's Action Mapping.
     * @param form the Proposal Development Form.
     * @param request the HTTP request.
     * @param response the HTTP response
     * @return the next web page to display
     * @throws Exception
     */
    public ActionForward copyProposal(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionForward nextWebPage = null;
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument doc = proposalDevelopmentForm.getDocument();
        ProposalCopyCriteria criteria = proposalDevelopmentForm.getCopyCriteria();
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new CopyProposalEvent(doc, criteria));
        
        if (!rulePassed) {
            nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
        }
        else {
            // Use the Copy Service to copy the proposal.
            
            ProposalCopyService proposalCopyService = (ProposalCopyService) KraServiceLocator.getService("proposalCopyService");
            if (proposalCopyService == null) {
                
                // Something bad happened. The errors are in the Global Error Map
                // which will be displayed to the user.
                
                nextWebPage = mapping.findForward(Constants.MAPPING_BASIC);
            }
            else {
                String newDocId = proposalCopyService.copyProposal(doc, criteria);
                KraServiceLocator.getService(PessimisticLockService.class).releaseAllLocksForUser(doc.getPessimisticLocks(), GlobalVariables.getUserSession().getPerson());
                
                // Switch over to the new proposal development document and
                // go to the Proposal web page.
                
                proposalDevelopmentForm.setDocId(newDocId);
                this.loadDocument(proposalDevelopmentForm);
                
                ProposalDevelopmentDocument copiedDocument = proposalDevelopmentForm.getDocument();
                initializeProposalUsers(copiedDocument);//add in any default permissions
                copiedDocument.getDevelopmentProposal().setS2sAppSubmission(new ArrayList<S2sAppSubmission>());            
                
                DocumentService docService = KraServiceLocator.getService(DocumentService.class);
                docService.saveDocument(copiedDocument);
                
                nextWebPage = mapping.findForward(MAPPING_PROPOSAL);
                
                // Helper method to clear document form data.
                proposalDevelopmentForm.clearDocumentRelatedState();
            
            }
        }

        return nextWebPage;
    }

    /** {@inheritDoc} */
    public ActionForward activate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (ProposalDevelopmentForm) form, true);
    }

    /** {@inheritDoc} */
    public ActionForward deactivate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return new AuditActionHelper().setAuditMode(mapping, (ProposalDevelopmentForm) form, false);
    }
    
    public ActionForward reject(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        KualiDocumentFormBase kualiDocumentFormBase = (KualiDocumentFormBase) form;
        
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        String reason = request.getParameter(KNSConstants.QUESTION_REASON_ATTRIBUTE_NAME);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        if(question == null){
            return this.performQuestionWithInput(mapping, form, request, response, DOCUMENT_REJECT_QUESTION,"Are you sure you want to reject this document?" , KNSConstants.CONFIRMATION_QUESTION, methodToCall, "");
         } 
        else if((DOCUMENT_REJECT_QUESTION.equals(question)) && ConfirmationQuestion.NO.equals(buttonClicked))  {
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
        else
        {
            //reject the document using the service.
            ProposalDevelopmentDocument pDoc = (ProposalDevelopmentDocument)kualiDocumentFormBase.getDocument();
            ProposalHierarchyService phService = KraServiceLocator.getService(ProposalHierarchyService.class);
            phService.rejectProposalDevelopmentDocument(pDoc.getDevelopmentProposal().getProposalNumber(), reason, GlobalVariables.getUserSession().getPrincipalName());
            return super.returnToSender(request, mapping, kualiDocumentFormBase);
        }
        
       
    }
    
    
    /**
     * route the document using the document service
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return ActionForward
     * @throws Exception
     */
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean success;
        
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = proposalDevelopmentForm.getDocument();
        
        proposalDevelopmentForm.setAuditActivated(true);
        //success=KraServiceLocator.getService(KualiRuleService.class).applyRules(new DocumentAuditEvent(proposalDevelopmentForm.getDocument()));
        //HashMap map=GlobalVariables.getAuditErrorMap();
        Object question = request.getParameter(KNSConstants.QUESTION_INST_ATTRIBUTE_NAME);
        Object buttonClicked = request.getParameter(KNSConstants.QUESTION_CLICKED_BUTTON);
        String methodToCall = ((KualiForm) form).getMethodToCall();
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        int status = isValidSubmission(pdDoc);

       //if((map.size()==1) &&  map.containsKey("sponsorProgramInformationAuditWarnings"))
        if (status == WARNING) 
        {

            if(status == WARNING && question == null){
                return this.performQuestionWithoutInput(mapping, form, request, response, DOCUMENT_ROUTE_QUESTION, "Validation Warning Exists.Are you sure want to submit to workflow routing.", KNSConstants.CONFIRMATION_QUESTION, methodToCall, "");
                
            } 
            else if(DOCUMENT_ROUTE_QUESTION.equals(question) && ConfirmationQuestion.YES.equals(buttonClicked)) {
                ActionForward actionForward = super.route(mapping, form, request, response);
                return actionForward;
            }

            else{
                return mapping.findForward((Constants.MAPPING_BASIC));
            }    

        }
        if(status == OK){
            ActionForward actionForward = super.route(mapping, form, request, response);
            return actionForward;
        }else   {
        GlobalVariables.getErrorMap().clear(); // clear error from isValidSubmission()    
        GlobalVariables.getErrorMap().putError("datavalidation",KeyConstants.ERROR_WORKFLOW_SUBMISSION,  new String[] {});
        
        if ((pdDoc.getDevelopmentProposal().getContinuedFrom() != null) && isNewProposalType(pdDoc) && isSubmissionApplication(pdDoc)) {
        	GlobalVariables.getErrorMap().putError("someKey", KeyConstants.ERROR_RESUBMISSION_INVALID_PROPOSALTYPE_SUBMISSIONTYPE);
        }
        
        return mapping.findForward((Constants.MAPPING_BASIC));
         }
        
}
    
    /**
     * Submit a proposal to a sponsor.  
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submitToSponsor(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)throws Exception{
      
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        
        if (requiresResubmissionPrompt(proposalDevelopmentForm)) {
            return mapping.findForward(Constants.MAPPING_RESUBMISSION_PROMPT);
        }

        proposalDevelopmentForm.setAuditActivated(true);
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        int status = isValidSubmission(proposalDevelopmentDocument);
        if (status == OK) {
            forward = submitApplication(mapping, form, request, response);
        } 
        else if (status == WARNING) {
            StrutsConfirmation question = buildSubmitToGrantsGovWithWarningsQuestion(mapping, form, request, response);
            forward = confirm(question, CONFIRM_SUBMISSION_WITH_WARNINGS_KEY, EMPTY_STRING);
        }
        
        proposalDevelopmentForm.setResubmissionOption(null);
        proposalDevelopmentForm.setInstitutionalProposalToVersion(null);
        return forward;      
    }
    
    private boolean requiresResubmissionPrompt(ProposalDevelopmentForm proposalDevelopmentForm) {
        DevelopmentProposal developmentProposal = proposalDevelopmentForm.getDocument().getDevelopmentProposal();
        return (ProposalType.RESUBMISSION_TYPE_CODE.equals(developmentProposal.getProposalTypeCode())
            || ProposalType.REVISION_TYPE_CODE.equals(developmentProposal.getProposalTypeCode())
            || isSubmissionChangeCorrected(developmentProposal))
            && proposalDevelopmentForm.getResubmissionOption() == null;
    }
    
    private boolean isSubmissionChangeCorrected(DevelopmentProposal developmentProposal) {
        if (developmentProposal.getS2sOpportunity() != null) {
            return S2sSubmissionType.CHANGE_CORRECTED_CODE.equals(developmentProposal.getS2sOpportunity().getS2sSubmissionTypeCode());
        }
        return false;
    }
    
    /**
     * Is this a valid submission?  
     * @param proposalDevelopmentDocument
     * @return OK, WARNING or ERROR
     */
    private int isValidSubmission(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        int state = OK;
        
        /*
         * If this proposal is a continuation from another proposal, it is illegal for
         * it to have a New Proposal Type and Application Submission Type.
         */
        if ((proposalDevelopmentDocument.getDevelopmentProposal().getContinuedFrom() != null) && isNewProposalType(proposalDevelopmentDocument) && isSubmissionApplication(proposalDevelopmentDocument)) {
            state = ERROR;
            //GlobalVariables.getErrorMap().putError("someKey", KeyConstants.ERROR_RESUBMISSION_INVALID_PROPOSALTYPE_SUBMISSIONTYPE);
        }
        else {
            /* 
             * Don't know what to do about the Audit Rules.  The parent class invokes the Audit rules
             * from its execute() method.  It will stay this way for the time being because I this is
             * what the code did before it was refactored.
             */
            boolean auditPassed = new AuditActionHelper().auditUnconditionally(proposalDevelopmentDocument);
            
            /*
             * Check for S2S validation if we have a Grants.gov Opportunity.  If there is no Grants.gov
             * Opportunity, then the user wants to submit the proposal manually (printing and sending via
             * snail mail).
             */
            boolean s2sPassed = true;
            if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null) {
                S2SService s2sService = (S2SService) KraServiceLocator.getService(S2SService.class);
//                s2sPassed = s2sService.validateApplication(proposalDevelopmentDocument.getProposalNumber());
                try {
                    s2sPassed = s2sService.validateApplication(proposalDevelopmentDocument);
                }
                catch (S2SException e) {
                    log.error(e.getMessage(),e);
                    s2sPassed=false;
                }
            }
            
            /*
             * If either reported an error, then we have to check to see if we only have warnings or
             * if we also have some errors.
             */
            if (!auditPassed || !s2sPassed) {
                state = WARNING;
                for (Iterator iter = GlobalVariables.getAuditErrorMap().keySet().iterator(); iter.hasNext();) {
                    AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get(iter.next());
                    if (!StringUtils.equalsIgnoreCase(auditCluster.getCategory(), Constants.AUDIT_WARNINGS)) {
                        state = ERROR;
                        GlobalVariables.getErrorMap().putError("noKey", KeyConstants.VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION);
                        break;
                    }
                }
            }
        }
            
        return state;
    }
        
    /**
     * Is the proposal classified as NEW according to its proposal type?
     * @param doc the proposal development document
     * @return true if new; otherwise false
     */
    private boolean isNewProposalType(ProposalDevelopmentDocument doc) {
        String newProposalTypeValue = getProposalParameterValue(KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW);
        return StringUtils.equalsIgnoreCase(doc.getDevelopmentProposal().getProposalTypeCode(), newProposalTypeValue);
    }
                
    /**
     * Does the proposal have a Grants.gov Opportunity with a S2S submission type of APPLICATION?
     * @param doc the proposal development document
     * @return true if proposal has grants.gov with submission type of APPLICATION; otherwise false
     */
    private boolean isSubmissionApplication(ProposalDevelopmentDocument doc) {
        if (doc.getDevelopmentProposal().getS2sOpportunity() != null) {
            String applicationSubmissionValue = getProposalParameterValue(KeyConstants.S2S_SUBMISSIONTYPE_APPLICATION);
            return StringUtils.equalsIgnoreCase(doc.getDevelopmentProposal().getS2sOpportunity().getS2sSubmissionTypeCode(), applicationSubmissionValue);
        }
        return false;
    }
    
    /**
     * Get a Proposal parameter value from the Kuali System Parameters.
     * @param parameterName the name of the parameter
     * @return the parameter's value
     */
    private String getProposalParameterValue(String parameterName) {
        return this.getParameterService().getParameterValue(ProposalDevelopmentDocument.class, parameterName);
    }
    
    /**
     * Submit the proposal to the sponsor without any further error checking.  Can be invoked if the user
     * wishes to ignore an warnings that occurred when first trying to submit the proposal.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward submitApplication(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response)throws Exception{
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        
        /*
         * If there is an opportunity, then this is a Grants.gov submission.  
         */
        if (proposalDevelopmentDocument.getDevelopmentProposal().getS2sOpportunity() != null) {
            try{
                submitS2sApplication(proposalDevelopmentDocument);
            }catch(S2SException ex){
                LOG.error(ex.getMessage(), ex);
                GlobalVariables.getErrorMap().putError(Constants.NO_FIELD, ex.getErrorKey(),ex.getMessage());
                return mapping.findForward(Constants.MAPPING_BASIC);
            }
        }
       
        proposalDevelopmentDocument.getDevelopmentProposal().setSubmitFlag(true);
        
        ProposalStateService proposalStateService = KraServiceLocator.getService(ProposalStateService.class);
        proposalDevelopmentDocument.getDevelopmentProposal().setProposalStateTypeCode(proposalStateService.getProposalStateTypeCode(proposalDevelopmentDocument, false, false));
        
        DocumentService documentService = KNSServiceLocator.getDocumentService();
        documentService.saveDocument(proposalDevelopmentDocument);
        
        if (autogenerateInstitutionalProposal()) {
            generateInstitutionalProposal(proposalDevelopmentForm);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private void generateInstitutionalProposal(ProposalDevelopmentForm proposalDevelopmentForm) {
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        if ("X".equals(proposalDevelopmentForm.getResubmissionOption())) {
            GlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_NOT_CREATED);
            return;
        }
        if ("O".equals(proposalDevelopmentForm.getResubmissionOption())) {
            proposalDevelopmentForm.setInstitutionalProposalToVersion(proposalDevelopmentDocument.getDevelopmentProposal().getContinuedFrom());
        }
        if ("O".equals(proposalDevelopmentForm.getResubmissionOption())
                || "A".equals(proposalDevelopmentForm.getResubmissionOption())) {
            String versionNumber = createInstitutionalProposalVersion(
                    proposalDevelopmentForm.getInstitutionalProposalToVersion(),
                    proposalDevelopmentDocument.getDevelopmentProposal(),
                    proposalDevelopmentDocument.getFinalBudgetForThisProposal());
            GlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_VERSIONED, 
                    versionNumber,
                    proposalDevelopmentForm.getInstitutionalProposalToVersion());
            
            persistProposalAdminDetails(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber(), getActiveProposalId(proposalDevelopmentForm.getInstitutionalProposalToVersion()));
        } else {
            String proposalNumber = createInstitutionalProposal(
                    proposalDevelopmentDocument.getDevelopmentProposal(), proposalDevelopmentDocument.getFinalBudgetForThisProposal());
            GlobalVariables.getMessageList().add(KeyConstants.MESSAGE_INSTITUTIONAL_PROPOSAL_CREATED, proposalNumber);
            persistProposalAdminDetails(proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber(), getActiveProposalId(proposalNumber));
        }
    }
    
    private Long getActiveProposalId(String proposalNumber) {
        BusinessObjectService service = KraServiceLocator.getService(BusinessObjectService.class);
        Collection<InstitutionalProposal> ips = service.findMatching(InstitutionalProposal.class, getFieldValues(proposalNumber, "proposalNumber"));
        Long proposalId = ((InstitutionalProposal) ips.toArray()[0]).getProposalId();
        return proposalId;
    }

    /**
     * This method...
     * @param proposalDevelopmentDocument
     * @param instProposalNumber
     * @return
     */
    private Long findInstProposalNumber(String devProposalNumber) {
        Long instProposalId = null;
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Collection<ProposalAdminDetails> proposalAdminDetails = businessObjectService.findMatching(ProposalAdminDetails.class, getFieldValues(devProposalNumber, "devProposalNumber"));
        
        for(Iterator iter = proposalAdminDetails.iterator(); iter.hasNext();){
            ProposalAdminDetails pad = (ProposalAdminDetails) iter.next();
            instProposalId = pad.getInstProposalId();
            break;
        }
        return instProposalId;
    }

    private Map<String, String> getFieldValues(String value, String fieldName) {
        Map<String, String> map = new HashMap<String, String>();
        map.put(fieldName, value);
        return map;
    }

    /**
     * This method...
     * @param instProposalNumber
     * @param instProposalSequenceNumber TODO
     * @param proposalDevelopmentDocument
     */
    private void persistProposalAdminDetails(String devProposalNumber, Long instProposalId) {
        ProposalAdminDetails proposalAdminDetails = new ProposalAdminDetails();
        proposalAdminDetails.setDevProposalNumber(devProposalNumber);
        proposalAdminDetails.setInstProposalId(instProposalId);
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        businessObjectService.save(proposalAdminDetails);
    }
    
    /**
     * Submit a proposal to Grants.gov.
     * @param proposalDevelopmentDocument
     * @throws Exception
     */
    private void submitS2sApplication(ProposalDevelopmentDocument proposalDevelopmentDocument) throws Exception{
        S2SService s2sService = ((S2SService) KraServiceLocator.getService(S2SService.class));
        s2sService.submitApplication(proposalDevelopmentDocument);
    }
    
    /**
     * 
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildSubmitToGrantsGovWithWarningsQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_SUBMISSION_WITH_WARNINGS_KEY, KeyConstants.QUESTION_SUMBMIT_OPPORTUNITY_WITH_WARNINGS_CONFIRMATION);
    }
    
    private String createInstitutionalProposal(DevelopmentProposal developmentProposal, Budget budget) {
        InstitutionalProposalService institutionalProposalService = KraServiceLocator.getService(InstitutionalProposalService.class);
        String proposalNumber = institutionalProposalService.createInstitutionalProposal(developmentProposal, budget);
        return proposalNumber;
    }
    
    private String createInstitutionalProposalVersion(String proposalNumber, DevelopmentProposal developmentProposal, Budget budget) {
        InstitutionalProposalService institutionalProposalService = KraServiceLocator.getService(InstitutionalProposalService.class);
        String versionNumber = institutionalProposalService.createInstitutionalProposalVersion(proposalNumber, developmentProposal, budget);
        return versionNumber;
    }
    
    

    @Override
    public ActionForward reload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = super.reload(mapping, form, request, response);
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = proposalDevelopmentForm.getDocument();
        ProposalDevelopmentPrintingService printService = KraServiceLocator.getService(ProposalDevelopmentPrintingService.class);
        printService.populateSponsorForms(proposalDevelopmentForm.getSponsorFormTemplates(), proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode());
        return forward;
    }
    
    /**
     * 
     * This method is called to print forms
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printSponsorForms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument proposalDevelopmentDocument = (ProposalDevelopmentDocument)proposalDevelopmentForm.getDocument();
        ActionForward actionForward = mapping.findForward(MAPPING_BASIC);
        String proposalNumber = proposalDevelopmentDocument.getDevelopmentProposal().getProposalNumber();
        
        List<SponsorFormTemplateList> sponsorFormTemplateLists = proposalDevelopmentForm.getSponsorFormTemplates();
        ProposalDevelopmentPrintingService printService = KraServiceLocator.getService(ProposalDevelopmentPrintingService.class);
        List<SponsorFormTemplate> printFormTemplates = new ArrayList<SponsorFormTemplate>();  
        printFormTemplates = printService.getSponsorFormTemplates(sponsorFormTemplateLists); 
        Map<String,Object> reportParameters = new HashMap<String,Object>();
        reportParameters.put(ProposalDevelopmentPrintingService.SELECTED_TEMPLATES, printFormTemplates);
        AttachmentDataSource dataStream = printService.printProposalDevelopmentReport(proposalDevelopmentDocument, 
                ProposalDevelopmentPrintingService.PRINT_PROPOSAL_SPONSOR_FORMS, reportParameters);
        streamToResponse(dataStream, response);
        return null;//mapping.findForward(Constants.MAPPING_AWARD_BASIC);
//        
//        if(!printFormTemplates.isEmpty()) {
//            String contentType = Constants.PDF_REPORT_CONTENT_TYPE;
//            String ReportName = proposalNumber.concat("_" + proposalDevelopmentDocument.getDevelopmentProposal().getSponsorCode()).concat(Constants.PDF_FILE_EXTENSION);
//            streamToResponse(printFormTemplates, proposalNumber, contentType, ReportName, response);
//            actionForward = null;
//        }
//        return actionForward;
    }
    public void streamToResponse(List<SponsorFormTemplate> printFormTemplates, String proposalNumber, String contentType, String ReportName, HttpServletResponse response) throws Exception{
        
        
        byte[] sftByteStream = KraServiceLocator.getService(PrintService.class).printProposalSponsorForms(proposalNumber, printFormTemplates);
        
        if(sftByteStream == null) {
            return;
        }
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream(sftByteStream.length);
            baos.write(sftByteStream);
            
            WebUtils.saveMimeOutputStreamAsFile(response, contentType, baos, ReportName);
            
        }finally{
            try{
                if(baos!=null){
                    baos.close();
                    baos = null;
                }
            }catch(IOException ioEx){
                LOG.warn(ioEx.getMessage(), ioEx);
            }
        }
}

    
    /**
     * 
     * This method is called to print forms
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printForms(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return super.printForms(mapping, form, request, response);
    }
    
    @Override
    protected KualiRuleService getKualiRuleService() {
        return getService(KualiRuleService.class);
    }
    
    
    /**
     * 
     * This method is for audit rule to forward to the page that the audit error fix is clicked.
     * This is an example for budget audit error forward from proposal to budget page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward personnel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        // TODO : what if forward is null
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetPersonnel.do?auditActivated=true&");
        
        return new ActionForward(forward, true);
    }
    
    /**
     * 
     * This method is for audit rule to forward to the Budget Parameters page when the associated audit error fix is clicked.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward budgetDistributionAndIncome(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetDistributionAndIncome.do?auditActivated=true&");
        
        return new ActionForward(forward, true);
    }

    /**
     * 
     * This method is for audit rule to forward to the Budget Parameters page when the associated audit error fix is clicked.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward parameters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetParameters.do?auditActivated=true&");
        
        return new ActionForward(forward, true);
    }
    
    /**
     * 
     * This method gets called upon clicking of resubmit(replace sponsor) button on Proposal Actions page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward resubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("resubmit");
    }

    /**
     * 
     * This method is for budget expense rule when 'fix' is clicked.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward budgetExpenses(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        String methodToCallAttribute = (String)request.getAttribute("methodToCallAttribute");
        String activePanelName = (String)request.getAttribute("activePanelName");
        
        String viewBudgetPeriodParam = null;
        if (StringUtils.isNotBlank(methodToCallAttribute)) {
            int idx = methodToCallAttribute.indexOf("&viewBudgetPeriod=");
            if (idx > 0) {
                viewBudgetPeriodParam = "viewBudgetPeriod=" + methodToCallAttribute.substring(methodToCallAttribute.indexOf("=", idx)+1,methodToCallAttribute.indexOf(".", idx))+"&";
            }
        }
        
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetExpenses.do?auditActivated=true&");
        if (viewBudgetPeriodParam != null) {
            forward = StringUtils.replace(forward, "budgetExpenses.do?", "budgetExpenses.do?"+viewBudgetPeriodParam); 
        }
        
        if (StringUtils.isNotBlank(activePanelName)) {
            forward = StringUtils.replace(forward, "budgetExpenses.do?", "budgetExpenses.do?activePanelName=" + activePanelName + "&"); 
        }

        return new ActionForward(forward, true);
    }
    
    /**
     * 
     * This method is to forward to budget summary page for audit errors.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward summary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetParameters.do?auditActivated=true&");
        
        return new ActionForward(forward, true);
    }
    
    public ActionForward budgetRate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = getForwardToBudgetUrl(form);
        forward = StringUtils.replace(forward, "budgetParameters.do?", "budgetRates.do?auditActivated=true&anchor="+((KualiForm)form).getAnchor()+"&");
        return new ActionForward(forward, true);
    }

    public ActionForward saveProposalActions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return save(mapping, form, request, response);
    }

    /**
     * 
     * This is a helper method to set up the forward to budget url for budget audit error.
     * @param form
     * @return
     */
    private String getForwardToBudgetUrl(ActionForm form) {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getDocument();
        BudgetDocument budgetDocument = null;
        String forward = null;
        try {
            for (BudgetDocumentVersion budgetDocumentVersion: pdDoc.getBudgetDocumentVersions()) {
                BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
                if (budgetVersion.isFinalVersionFlag()) {
                    DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
                    budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(budgetVersion.getDocumentNumber());
                }
            }
            Long routeHeaderId = budgetDocument.getDocumentHeader().getWorkflowDocument().getRouteHeaderId();
            forward = buildForwardUrl(routeHeaderId);
        } catch (Exception e) {
            LOG.info("forward to budgetsummary "+e.getStackTrace());
            //TODO what is the forward here
        }
        return forward;

    }

    public ActionForward linkChildToHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal hierarchyProposal = pdForm.getDocument().getDevelopmentProposal();
        DevelopmentProposal newChildProposal = getHierarchyHelper().getDevelopmentProposal(pdForm.getNewHierarchyChildProposalNumber());
        String hierarchyBudgetTypeCode = pdForm.getNewHierarchyBudgetTypeCode();
        if (newChildProposal == null) {
            GlobalVariables.getMessageMap().putError(ProposalHierarchyKeyConstants.FIELD_CHILD_NUMBER, KeyConstants.ERROR_REQUIRED, "Link Child Proposal");
        }
        else {
            getHierarchyHelper().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode);
            pdForm.setNewHierarchyChildProposalNumber("");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward syncAllHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        //DevelopmentProposal hierarchyProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().syncAllHierarchy(pdForm.getDocument());
        if (GlobalVariables.getMessageMap().containsMessageKey(ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM)) {
            return doEndDateConfirmation(mapping, form, request, response, "syncAllHierarchy", "syncAllHierarchyConfirm");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
        //return reload(mapping, form, request, response);
    }
    
    public ActionForward syncAllHierarchyConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        //DevelopmentProposal hierarchyProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().syncAllHierarchy(pdForm.getDocument(), true);
        return mapping.findForward(Constants.MAPPING_BASIC);
        //return reload(mapping, form, request, response);
    }
    
    public ActionForward removeFromHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        StrutsConfirmation question = buildParameterizedConfirmationQuestion(mapping, form, request, response, "removeFromHierarchy", ProposalHierarchyKeyConstants.QUESTION_REMOVE_CONFIRM);
        return confirm(question, "removeFromHierarchyConfirmed", "hierarchyActionCanceled");
    }
        
    public ActionForward removeFromHierarchyConfirmed(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal childProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().removeFromHierarchy(childProposal);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward hierarchyActionCanceled(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        GlobalVariables.getMessageList().add(ProposalHierarchyKeyConstants.MESSAGE_ACTION_CANCEL);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward syncToHierarchyParent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal childProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().syncToHierarchyParent(childProposal);
        if (GlobalVariables.getMessageMap().containsMessageKey(ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM)) {
            return doEndDateConfirmation(mapping, form, request, response, "syncToHierarchyParent", "syncToHierarchyParentConfirm");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward syncToHierarchyParentConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal childProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().syncToHierarchyParent(childProposal, true);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    public ActionForward createHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal initialChildProposal = pdForm.getDocument().getDevelopmentProposal();
        getHierarchyHelper().createHierarchy(initialChildProposal);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward linkToHierarchy(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal hierarchyProposal = getHierarchyHelper().getDevelopmentProposal(pdForm.getNewHierarchyProposalNumber());
        DevelopmentProposal newChildProposal = pdForm.getDocument().getDevelopmentProposal();
        String hierarchyBudgetTypeCode = pdForm.getNewHierarchyBudgetTypeCode();
        if (hierarchyProposal == null) {
            GlobalVariables.getMessageMap().putError(ProposalHierarchyKeyConstants.FIELD_PARENT_NUMBER, KeyConstants.ERROR_REQUIRED, "Link to Hierarchy");
        }
        else {
            getHierarchyHelper().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode);
            if (GlobalVariables.getMessageMap().containsMessageKey(ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM)) {
                return doEndDateConfirmation(mapping, form, request, response, "linkToHierarchy", "linkToHierarchyConfirm");
            }
            pdForm.setNewHierarchyProposalNumber("");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward linkToHierarchyConfirm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm)form;
        DevelopmentProposal hierarchyProposal = getHierarchyHelper().getDevelopmentProposal(pdForm.getNewHierarchyProposalNumber());
        DevelopmentProposal newChildProposal = pdForm.getDocument().getDevelopmentProposal();
        String hierarchyBudgetTypeCode = pdForm.getNewHierarchyBudgetTypeCode();
        if (hierarchyProposal == null) {
            GlobalVariables.getMessageMap().putError(ProposalHierarchyKeyConstants.FIELD_PARENT_NUMBER, KeyConstants.ERROR_REQUIRED, "Link to Hierarchy");
        }
        else {
            getHierarchyHelper().linkToHierarchy(hierarchyProposal, newChildProposal, hierarchyBudgetTypeCode, true);
            pdForm.setNewHierarchyProposalNumber("");
        }
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ParameterService getParameterService() {
        return KraServiceLocator.getService(ParameterService.class);
    }
   
    
    @Override
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ProposalDevelopmentForm pdForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument pdDoc = pdForm.getDocument();
        pdDoc.prepareForSave();
        return super.cancel(mapping, form, request, response);
    }
    
    @Override
    public ActionForward disapprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        return super.disapprove(mapping, form, request, response);
    }
    
    @Override
    public ActionForward fyi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        return super.fyi(mapping, form, request, response);
    }

    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        return super.blanketApprove(mapping, form, request, response);
    }

    @Override
    public ActionForward acknowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ((ProposalDevelopmentForm)form).getDocument().prepareForSave();
        return super.acknowledge(mapping, form, request, response);
    }
    /**
     * 
     * This method is to print current report of person 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printCurrentReportPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                                                                                                      throws Exception {
        ReportHelperBean helper = ((ReportHelperBeanContainer)form).getReportHelperBean();
        String personId = helper.getPersonId(); 
        InstitutionalProposalPrintingService printService = KraServiceLocator.getService(InstitutionalProposalPrintingService.class);

        //throw new RuntimeException("Functionality not supported");
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * 
     * This method is to print pending report of person 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printPendingReportPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                                                                                                      throws Exception {
        ReportHelperBean helper = ((ReportHelperBeanContainer)form).getReportHelperBean();
        String personId = helper.getPersonId(); 
        InstitutionalProposalPrintingService printService = KraServiceLocator.getService(InstitutionalProposalPrintingService.class);

        //throw new RuntimeException("Functionality not supported");
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    /**
     * Prepare current report (i.e. Awards that selected person is on)
     * {@inheritDoc}
     */
    public ActionForward prepareCurrentReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                                                                                                                    throws Exception {
        ReportHelperBean helper = ((ReportHelperBeanContainer)form).getReportHelperBean();
        request.setAttribute(ReportHelperBean.CURRENT_REPORT_BEANS_KEY, helper.prepareCurrentReport());
        request.setAttribute(ReportHelperBean.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName()); 
        return mapping.findForward(Constants.MAPPING_BASIC);
    }

    /**
     * Prepare pending report (i.e. InstitutionalProposals that selected person is on)
     * {@inheritDoc}
     */
    public ActionForward preparePendingReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
                                                                                                                    throws Exception {
        ReportHelperBean helper = ((ReportHelperBeanContainer)form).getReportHelperBean();
        request.setAttribute(ReportHelperBean.PENDING_REPORT_BEANS_KEY, helper.preparePendingReport());
        request.setAttribute(ReportHelperBean.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    private ActionForward doEndDateConfirmation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String questionId, String yesMethodName) throws Exception {
        List<ErrorMessage> errors = GlobalVariables.getMessageMap().getErrorMessagesForProperty(ProposalHierarchyKeyConstants.FIELD_CHILD_NUMBER);
        List<String> proposalNumbers = new ArrayList<String>();
        for (ErrorMessage error : errors) {
            if (error.getErrorKey().equals(ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM)) {
                proposalNumbers.add(error.getMessageParameters()[0]);
            }
        }
        String proposalNumberList = StringUtils.join(proposalNumbers, ',');
        StrutsConfirmation question = buildParameterizedConfirmationQuestion(mapping, form, request, response, questionId, ProposalHierarchyKeyConstants.QUESTION_EXTEND_PROJECT_DATE_CONFIRM, proposalNumberList);
        GlobalVariables.getMessageMap().getErrorMessages().clear();
        GlobalVariables.getMessageMap().getWarningMessages().clear();
        GlobalVariables.getMessageMap().getInfoMessages().clear();
        return confirm(question, yesMethodName, "hierarchyActionCanceled");
    }
    
    private boolean autogenerateInstitutionalProposal() {
        return getParameterService().getIndicatorParameter(
                Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, 
                Constants.PARAMETER_COMPONENT_DOCUMENT,
                KeyConstants.AUTOGENERATE_INSTITUTIONAL_PROPOSAL_PARAM);
    }
}
    
    
    
    
    
