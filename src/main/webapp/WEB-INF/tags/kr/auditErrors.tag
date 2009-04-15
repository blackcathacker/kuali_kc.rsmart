<%--
 Copyright 2006-2009 The Kuali Foundation
 
 Licensed under the Educational Community License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at
 
 http://www.osedu.org/licenses/ECL-2.0
 
 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--%>
<%@ include file="/kr/WEB-INF/jsp/tldHeader.jsp"%>

<%@ attribute name="cluster" required="false"%>
<%@ attribute name="keyMatch" required="false"%>
<%@ attribute name="isLink" required="true"%>
<%@ attribute name="includesTitle" required="false"%>


<c:forEach items="${AuditErrors}" var="auditCluster">
	<c:if test="${auditCluster.key == cluster}">
       <c:set var="errorCategory" value="${auditCluster.value.category}" />
	</c:if>
</c:forEach>

<c:if test="${!empty cluster}">
	<c:set var="isFirstLocalError" value="true"/>
	<div class="error">
		<c:forEach items="${AuditErrors[cluster].auditErrorList}" var="audit" varStatus="status">
			<c:set var="errorText">
				<bean:message key="${audit.messageKey}" arg0="${audit.params[0]}" arg1="${audit.params[1]}" arg2="${audit.params[2]}" arg3="${audit.params[3]}" arg4="${audit.params[4]}"/>
			</c:set>
			<c:forEach items="${fn:split(keyMatch,',')}" var="prefix">
				<c:if test="${(empty prefix) || (audit.errorKey == prefix) || (fn:endsWith(prefix, '*') && fn:startsWith(audit.errorKey, fn:replace(prefix, '*', '')))}">
					<c:if test="${includesTitle && isFirstLocalError}">
						<c:set var="isFirstLocalError" value="false"/>
						<strong>${errorCategory} found in this Section:</strong><br/>
					</c:if>
					<c:choose>
						<c:when test="${isLink}">
							<c:set var="splitStr" value="${fn:split(audit.link,'.')}"/>
							<tr>
								<td>&nbsp;</td>
								<td width="94%">${errorText}</td>
								<td width="5%"><div align="center">
									<c:if test="${not empty splitStr[0]}" >
										<html:image src="${ConfigProperties.externalizable.images.url}tinybutton-fix.gif" property="methodToCall.${splitStr[0]}.anchor${splitStr[1]}.x"/>
									</c:if>
								</div></td>
							</tr>
						</c:when>
						<c:otherwise><li>${errorText}</li></c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach>
		</c:forEach>
	</div>
</c:if>
