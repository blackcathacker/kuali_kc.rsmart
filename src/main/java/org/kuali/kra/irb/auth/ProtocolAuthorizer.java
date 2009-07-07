/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.auth;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.authorization.Task;
import org.kuali.kra.authorization.TaskAuthorizerImpl;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.actions.amendrenew.ProtocolAmendRenewModule;
import org.kuali.kra.irb.actions.submit.ProtocolActionService;

/**
 * A Protocol Authorizer determines if a user can perform
 * a given task on a protocol.  
 */
public abstract class ProtocolAuthorizer extends TaskAuthorizerImpl {
    
    private ProtocolAuthorizationService protocolAuthorizationService;
    private ProtocolActionService protocolActionService;
    
    /**
     * Set the Protocol Action Service.
     * @param protocolActionService
     */
    public final void setProtocolActionService(ProtocolActionService protocolActionService) {
        this.protocolActionService = protocolActionService;
    }

    /**
     * @see org.kuali.kra.authorization.TaskAuthorizer#isAuthorized(java.lang.String, org.kuali.kra.authorization.Task)
     */
    public final boolean isAuthorized(String username, Task task) {
        return isAuthorized(username, (ProtocolTask) task);
    }

    /**
     * Is the user authorized to execute the given protocol task?
     * @param username the user's unique username
     * @param task the protocol task
     * @return true if the user is authorized; otherwise false
     */
    public abstract boolean isAuthorized(String username, ProtocolTask task);
    
    /**
     * Set the Protocol Authorization Service.  Usually injected by the Spring Framework.
     * @param protocolAuthorizationService
     */
    public void setProtocolAuthorizationService(ProtocolAuthorizationService protocolAuthorizationService) {
        this.protocolAuthorizationService = protocolAuthorizationService;
    }
    
    /**
     * Does the given user has the permission for this protocol?
     * @param username the unique username of the user
     * @param protocol the protocol
     * @param permissionName the name of the permission
     * @return true if the person has the permission; otherwise false
     */
    protected final boolean hasPermission(String username, Protocol protocol, String permissionName) {
        return protocolAuthorizationService.hasPermission(username, protocol, permissionName);
    }
    
    /**
     * Is the protocol an amendment or renewal protocol?
     * @param protocol the protocol
     * @return true if the protocol is an amendment or renewal; otherwise false
     */
    protected final boolean isAmendmentOrRenewal(Protocol protocol) {
        return protocol.getProtocolNumber() != null &&
               (protocol.getProtocolNumber().contains("A") ||
                protocol.getProtocolNumber().contains("R"));
    }
    
    /**
     * Can the user on the current thread execute the given action for the given protocol?
     * @param protocol
     * @param protocolActionTypeCode
     * @return true if the action can be executed; otherwise false
     */
    protected final boolean canExecuteAction(Protocol protocol, String protocolActionTypeCode) {
        return protocolActionService.isActionAllowed(protocolActionTypeCode, protocol);
    }
}
