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
package org.kuali.kra.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.budget.bo.CostElement;
import org.kuali.kra.service.ObjectCodeToBudgetCategoryCodeService;

public class ObjectCodeToBudgetCategoryCodeServiceImpl implements ObjectCodeToBudgetCategoryCodeService {

    private BusinessObjectService businessObjectService;

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ObjectCodeToBudgetCategoryCodeService#getBudgetCategoryCodeForCostElment(java.lang.String)
     */
    public String getBudgetCategoryCodeForCostElment(String objectCode) {
        String budgetCategoryCode = null;

        Map<String, String> primaryKeys = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(objectCode)) {
            primaryKeys.put("costElement", objectCode);
            CostElement costElement = (CostElement)businessObjectService.findByPrimaryKey(CostElement.class, primaryKeys);
            if (costElement != null) {
                budgetCategoryCode = costElement.getBudgetCategoryCode();
            }
        }

        return budgetCategoryCode;
    }

    /**
     * Gets the businessObjectService attribute.
     * @return Returns the businessObjectService.
     */
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    /**
     * Sets the businessObjectService attribute value.
     * @param businessObjectService The businessObjectService to set.
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}
