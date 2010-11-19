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
package org.kuali.kra.irb.actions.risklevel;

import java.io.Serializable;

/**
 * Encapsulates the actions that can be performed on a ProtocolRiskLevel.
 */
public class ProtocolRiskLevelBean implements Serializable {

    private static final long serialVersionUID = -3356100216341676530L;

    private String errorPropertyName;
    
    private ProtocolRiskLevel newProtocolRiskLevel;
    
    /**
     * Constructs a ProtocolRiskLevelBean.
     */
    public ProtocolRiskLevelBean(String errorPropertyName) {
        this.setErrorPropertyName(errorPropertyName);
        
        newProtocolRiskLevel = new ProtocolRiskLevel();
    }

    public String getErrorPropertyName() {
        return errorPropertyName;
    }
    
    public void setErrorPropertyName(String errorPropertyName) {
        this.errorPropertyName = errorPropertyName;
    }
    
    public ProtocolRiskLevel getNewProtocolRiskLevel() {
        return newProtocolRiskLevel;
    }
    
    public void setNewProtocolRiskLevel(ProtocolRiskLevel newProtocolRiskLevel) {
        this.newProtocolRiskLevel = newProtocolRiskLevel;
    }

}