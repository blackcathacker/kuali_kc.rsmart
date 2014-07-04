/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItemBase;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.budget.framework.personnel.ValidCeJobCode;
import org.kuali.coeus.common.budget.framework.rate.BudgetRate;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.rice.kew.api.exception.WorkflowException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Budget Service interface
 */
public interface BudgetService<T extends BudgetParent>  {
    
    
    /**
     * Service method for adding a {@link BudgetVersionOverview} to a {@link ProposalDevelopmentDocument}. If a 
     * {@link BudgetVersionOverview} instance with the  <code>versionName</code> already exists 
     * in the {@link ProposalDevelopmentDocument}, then a hard error will occur. Try it and you'll see what I mean.
     * 
     * @param document instance to add {@link BudgetVersionOverview} to
     * @param versionName of the {@link BudgetVersionOverview}
     */
    public Budget addBudgetVersion(BudgetParentDocument<T> budgetParent, String versionName, Map<String, Object> options);
    
    /**
     * check if this line item CE has inflation rate
     * @param budgetLineItem
     * @return
     */
    public boolean ValidInflationCeRate(BudgetLineItemBase budgetLineItem);
    
    public String getActivityTypeForBudget(BudgetDocument<T> budgetDocument);
    
    /**
     * This method returns the applicable Object Codes (Cost Elements) for a given Budget Person 
     * based on his Job Code
     * @param budgetId
     * @param personSequenceNumber
     * @return List of Cost Elements
     */
    public List<ValidCeJobCode> getApplicableCostElements(Long budgetId, String personSequenceNumber);
    
    /**
     * 
     * This method returns the applicable Object Codes (Cost Elements) for a given Budget Person, converted to string separated by ",".
     * @param budgetId
     * @param personSequenceNumber
     * @param budgetCategoryTypeCode
     * @return List of Cost Elements
     */
    public String getApplicableCostElementsForAjaxCall(Long budgetId, String personSequenceNumber, String budgetCategoryTypeCode);

    /**
     * 
     * This method returns the Non-Personnel Panel Name (based on the variables).
     * @param budgetPeriod
     * @param budgetLineItem
     * @return Non-Personnel Panel Name for the passed in Line Item
     */
    public String getBudgetExpensePanelName(BudgetPeriod budgetPeriod, BudgetLineItem budgetLineItem);

    public Collection<BudgetRate> getSavedProposalRates(BudgetVersionOverview budgetToOpen);

    /**
     * 
     * This method to check the audit rule when try to save 'budgetversion' and with 'complete' status.
     * @param proposalDevelopmentDocument
     * @return
     * @throws Exception
     */
    public boolean validateBudgetAuditRuleBeforeSaveBudgetVersion(BudgetParentDocument<T> proposalDevelopmentDocument) throws Exception;

    /**
     * Determine if the names of a {@link BudgetVersionOverview} instances in the given {@link  ProposalDevelopmentDocument} instance is valid
     *
     * @param document {@link ProposalDevelopmentDocument} instance to get {@link BudgetVersionOverview} instances from
     * @param versionName to check
     * @return true for valid false otherwie
     */
    public boolean isBudgetVersionNameValid(BudgetParentDocument<T> document, String versionName);

    /**
     * 
     * This method to invoke audit rule check for budget if status is final only
     * This is called by PD's turnon validation
     * @param parentDocument
     * @return
     */
    public boolean validateBudgetAuditRule(BudgetParentDocument<T> parentDocument) throws Exception;

    /**
     * Returns a new finalized BudgetDocument with the data from the given BudgetDocument copied over.
     * @param budgetDocument
     * @param onlyOnePeriod
     * @return BudgetDocument
     * @throws WorkflowException
     */    
    public Budget copyBudgetVersion(Budget budget, boolean onlyOnePeriod);
    
    public String populateBudgetPersonSalaryDetailsInPeriods(String budgetId, String personSequenceNumber, String personId);
    
    public void populateNewBudgetLineItem(BudgetLineItem newBudgetLineItem, BudgetPeriod budgetPeriod);
    
    /**
     * This method sets the budget status of the 'final' budget version (if it exists) to the proposal budget status
     * as indicated in the proposal development document.
     * 
     * @param parentDocument
     */
    public void setBudgetStatuses(BudgetParentDocument<T> parentDocument);

}
