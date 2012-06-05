/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.subaward.customdata;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.rule.event.KraDocumentEventBase;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.krad.rules.rule.BusinessRule;

/**
 * This class is using as a
 * Event class for Rule processing on
 * Save for subAward Custom Data.

 */
public class SubAwardSaveCustomDataRuleEvent extends KraDocumentEventBase {


    private static final org.apache.commons.
    logging.Log LOG = org.apache.commons.logging.LogFactory
    .getLog(SubAwardSaveCustomDataRuleEvent.class);

    /**
     * Constructs a SubAwardSaveCustomDataRuleEvent.java.
     * @param errorPathPrefix
     * @param document
     */
    public SubAwardSaveCustomDataRuleEvent(
    String errorPathPrefix, ResearchDocumentBase document) {
        super("Adding custom attribute to document "
        + getDocumentId(document), errorPathPrefix, document);
    }

    /**.
     * Convenience method to return an SubAwardDocument
     * @return SubAwardDocument
     */
    public SubAwardDocument getSubAwardDocument() {
        return (SubAwardDocument) getDocument();
    }


    /**
     * @see org.kuali.kra.rule.event.KraDocumentEventBase#logEvent()
     */
    @Override
    protected void logEvent() {
        LOG.info("Save subaward custom data event");
    }

    /**.
     * This class is for getting SubAwardCustomDataRule
     * @return SubAwardCustomDataRule
     */
    public Class getRuleInterfaceClass() {
        return SubAwardCustomDataRule.class;
    }

    /**.
     * This is for invoking rule methods
     */
    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((SubAwardCustomDataRule) rule).
        processSaveSubAwardCustomDataBusinessRules(this);
    }



}
