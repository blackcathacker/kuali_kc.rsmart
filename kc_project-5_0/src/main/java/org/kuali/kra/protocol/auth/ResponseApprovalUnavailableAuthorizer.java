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
package org.kuali.kra.protocol.auth;

import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.irb.actions.ProtocolActionType;

/**
 * Is the user allowed to approve a response and the action is currently unavailable?
 */
public class ResponseApprovalUnavailableAuthorizer extends ProtocolAuthorizer {

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.protocol.auth.ProtocolAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.protocol.auth.ProtocolTask)
     */
    @Override
    public boolean isAuthorized(String userId, ProtocolTask task) {
        return !canExecuteAction(task.getProtocol(), ProtocolActionType.RESPONSE_APPROVAL) 
            && hasPermission(userId, task.getProtocol(), PermissionConstants.MAINTAIN_PROTOCOL_SUBMISSIONS);
    }

}
