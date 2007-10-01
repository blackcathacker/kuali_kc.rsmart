/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.web.struts.action;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.Person;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeStatus;
import org.kuali.kra.proposaldevelopment.bo.NarrativeType;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

public class ProposalDevelopmentAbstractsAttachmentsAction extends ProposalDevelopmentAction {
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentAbstractsAttachmentsAction.class);
    public ActionForward addProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        ProposalDevelopmentDocument propDoc = proposalDevelopmentForm.getProposalDevelopmentDocument(); 
        Narrative narr = propDoc.getNewNarrative();
        narr.setProposalNumber(propDoc.getProposalNumber());
        narr.setModuleNumber(propDoc.getProposalNextValue(Constants.NARRATIVE_MODULE_NUMBER));
        narr.setModuleSequenceNumber(propDoc.getProposalNextValue(Constants.NARRATIVE_MODULE_SEQUENCE_NUMBER));
        narr.setUpdateUser(propDoc.getUpdateUser());
        narr.setUpdateTimestamp(propDoc.getUpdateTimestamp());

        Map narrTypeMap = new HashMap();
        narrTypeMap.put("narrativeTypeCode", narr.getNarrativeTypeCode());
        BusinessObjectService service = getService(BusinessObjectService.class);
        NarrativeType narrType = (NarrativeType)service.findByPrimaryKey(NarrativeType.class, narrTypeMap);
        if(narrType!=null) narr.setNarrativeType(narrType);
        
        Map narrStatusMap = new HashMap();
        narrStatusMap.put("narrativeStatusCode", narr.getModuleStatusCode());
        NarrativeStatus narrStatus = (NarrativeStatus)service.findByPrimaryKey(NarrativeStatus.class, narrStatusMap);
        if(narrStatus!=null) narr.setNarrativeStatus(narrStatus);

        FormFile narrFile = narr.getNarrativeFile();
        byte[] fileData = narrFile.getFileData();
        if(fileData.length>0){
            NarrativeAttachment narrPdf = new NarrativeAttachment();
            narrPdf.setFileName(narrFile.getFileName());
            narrPdf.setContentType(narrFile.getContentType());
            narrPdf.setProposalNumber(narr.getProposalNumber());
            narrPdf.setModuleNumber(narr.getModuleNumber());
            narrPdf.setNarrativeData(narrFile.getFileData());
            if(narr.getNarrativeAttachmentList().isEmpty()) 
                narr.getNarrativeAttachmentList().add(narrPdf);
            else
                narr.getNarrativeAttachmentList().set(0,narrPdf);
        }
        propDoc.getNarratives().add(narr);
        propDoc.setNewNarrative(new Narrative());
        
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    public ActionForward deleteProposalAttachment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentForm proposalDevelopmentForm = (ProposalDevelopmentForm) form;
        proposalDevelopmentForm.getProposalDevelopmentDocument().getNarratives().remove(getLineToDelete(request));
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    private BusinessObjectService getBusinessObjectService() {
        return getService(BusinessObjectService.class);
    }
    
}
