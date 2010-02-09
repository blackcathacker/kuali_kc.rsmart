/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.institutionalproposal.service;

import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;

/**
 * This interface defines public services made available by the Institutional Proposal module.
 */
public interface InstitutionalProposalService {
    
    /**
     * Creates a new Institutional Proposal with data copied from the given development proposal and budget.
     * 
     * @param developmentProposal DevelopmentProposal
     * @param budget Budget
     * @return String The new proposal number
     */
    String createInstitutionalProposal(DevelopmentProposal developmentProposal, Budget budget);
    
    /**
     * Creates a new version of the Institutional Proposal corresponding to the given proposal number, 
     * with data copied from the given development proposal and budget.
     * 
     * @param proposalNumber String
     * @param developmentProposal DevelopmentProposal
     * @param budget Budget
     * @return String The new version number
     */
    String createInstitutionalProposalVersion(String proposalNumber, DevelopmentProposal developmentProposal, Budget budget);
}
