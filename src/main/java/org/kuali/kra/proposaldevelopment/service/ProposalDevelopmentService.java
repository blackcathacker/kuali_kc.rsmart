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
package org.kuali.kra.proposaldevelopment.service;

import java.util.List;

import org.kuali.kra.bo.Unit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

public interface ProposalDevelopmentService {
    
    public void initializeUnitOrganizationLocation(ProposalDevelopmentDocument proposalDevelopmentDocument);
    
    /**
     * This method initializes the siteNumber property on proposal sites that don't have it set yet.
     * @param proposalDevelopmentDocument
     */
    public void initializeProposalSiteNumbers(ProposalDevelopmentDocument proposalDevelopmentDocument);
    
   /**
     * This method returns a Map of Units for which the user represented by the userId passed in has the role Proposal Aggregator
     * @param userId unique identifer representing the user whose units will be returned
     * @return A Map in the form of Unit Number, Unit Name representing the units for which the userId passed in has the Proposal Aggregator role.
     */
    public List<Unit> getDefaultModifyProposalUnitsForUser(String userId);

    public String populateProposalEditableFieldMetaDataForAjaxCall(String proposalNumber, String editableFieldDBColumn);
    
    public Object getProposalFieldValueFromDBColumnName(String proposalNumber, String dbColumnName) ;

}
