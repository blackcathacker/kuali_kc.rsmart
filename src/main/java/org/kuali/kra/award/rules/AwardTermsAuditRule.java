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
package org.kuali.kra.award.rules;

import java.util.ArrayList;
import java.util.List;

import org.kuali.core.document.Document;
import org.kuali.core.lookup.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.core.rule.DocumentAuditRule;
import org.kuali.core.util.AuditCluster;
import org.kuali.core.util.AuditError;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.award.bo.AwardSponsorTerm;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.SponsorTermType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;

/**
 * This class processes audit rules (warnings) for the Terms Information related
 * data of the AwardDocument.
 */
public class AwardTermsAuditRule implements DocumentAuditRule {

    private static final int FIVE = 5;
    private static final int ZERO = 0;
    private static final String TERMS_AUDIT_ERRORS = "termsAuditErrors";
    private List<AuditError> auditErrors;
    private List<KeyLabelPair> sponsorTermTypes;
    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    @SuppressWarnings("unchecked")
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument) document;
        auditErrors = new ArrayList<AuditError>();
        setSponsorTermTypes();
        //List<KeyLabelPair> sponsorTermTypes = (ArrayList) getSponsorTermTypesFromDatabase();
        List<AwardSponsorTerm> awardSponsorTerms = awardDocument.getAward().getAwardSponsorTerms();
        
        for(KeyLabelPair keyLabelPair : sponsorTermTypes) {
            boolean sponsorTermTypeExists = isSponsorTermTypeInAwardSponsorTerms(keyLabelPair.getKey().toString(), awardSponsorTerms);
            if(!sponsorTermTypeExists){
                valid=false;
                addErrorToAuditErrors(keyLabelPair.getLabel().toString());
            }
        }
        reportAndCreateAuditCluster();
        return valid;
    }
    
    /**
     * This method checks if sponsorTermTypes is null if true sets the list to a list of database return from getSponsorTermTypesFromDatabase()
     */
    @SuppressWarnings("unchecked")
    protected void setSponsorTermTypes() {
        if(this.sponsorTermTypes == null) {
            this.sponsorTermTypes = (ArrayList) getSponsorTermTypesFromDatabase();
        }
    }
    
    /**
     * This method sets sponsor term types to List<KeyLabelPair> argument.
     * @param sponsorTermTypes
     */
    protected void setSponsorTermTypes(List<KeyLabelPair> sponsorTermTypes) {
        this.sponsorTermTypes = sponsorTermTypes;
    }
    
    /**
     * This method tests if there is an Award Sponsor Term with Sponsor Term Type Code that is equal to the parameter "key".
     * @param key
     * @param awardSponsorTerms
     * @return
     */
    protected boolean isSponsorTermTypeInAwardSponsorTerms(String key, List<AwardSponsorTerm> awardSponsorTerms){
        boolean valid = false;
        for(AwardSponsorTerm awardSponsorTerm : awardSponsorTerms){
              if(awardSponsorTerm.getSponsorTermTypeCode().equals(key)){
                  valid = true;
              }
        }
        return valid;
    }
    
    /**
     * This method creates and adds the Audit Error to the List<AuditError> auditError.
     * @param description
     */
    protected void addErrorToAuditErrors(String description) {
        String[] params = new String[FIVE];
        params[ZERO] = description;
        auditErrors.add(new AuditError(Constants.TERMS_AUDIT_RULES_ERROR_KEY, 
                                        KeyConstants.ERROR_EMPTY_TERMS, 
                                        Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE + "." + Constants.TERMS_PANEL_ANCHOR,
                                        params));   
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > ZERO) {
            GlobalVariables.getAuditErrorMap().put(TERMS_AUDIT_ERRORS, new AuditCluster(Constants.TERMS_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
        }
    }
    
    /**
     * 
     * This method gets the list of Sponsor Term Types from the database.
     * 
     * @param
     */
    protected List<KeyLabelPair> getSponsorTermTypesFromDatabase(){
        PersistableBusinessObjectValuesFinder persistableBusinessObjectValuesFinder = new PersistableBusinessObjectValuesFinder();
        persistableBusinessObjectValuesFinder.setBusinessObjectClass(SponsorTermType.class);
        persistableBusinessObjectValuesFinder.setKeyAttributeName("sponsorTermTypeCode");
        persistableBusinessObjectValuesFinder.setLabelAttributeName("description");
        return persistableBusinessObjectValuesFinder.getKeyValues();
    }

}
