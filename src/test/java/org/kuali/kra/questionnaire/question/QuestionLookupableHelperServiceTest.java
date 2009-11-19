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
package org.kuali.kra.questionnaire.question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.KraTestBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.rice.kns.UserSession;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocumentBase;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.service.MaintenanceDocumentDictionaryService;
import org.kuali.rice.kns.util.GlobalVariables;

public class QuestionLookupableHelperServiceTest extends KraTestBase {
    
    private static final String SEQUENCE_STATUS_CURRENT = "C";
    private static final String SEQUENCE_STATUS_ARCHIVED = "A";
    
    private QuestionLookupableHelperServiceImpl questionLookupableHelperServiceImpl;
    private DocumentService documentService;
    private Mockery context = new JUnit4Mockery();
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        questionLookupableHelperServiceImpl = (QuestionLookupableHelperServiceImpl) KraServiceLocator.getService("questionLookupableHelperService");
        questionLookupableHelperServiceImpl.setBusinessObjectClass(Question.class);
        documentService = KraServiceLocator.getService(DocumentService.class);
        GlobalVariables.setUserSession(new UserSession("quickstart"));
    }
    
    @After
    public void tearDown() throws Exception {
        questionLookupableHelperServiceImpl = null;
        documentService = null;
        GlobalVariables.setUserSession(null);
        super.tearDown();
    }
    
    /**
     * 
     * This method tests getSearchResults.  It ensures that only the most recent version of a question
     * will be returned in the search result.
     * @throws Exception
     */
    @Test
    public void testGetSearchResults() throws Exception {
        
        // Create & submit new Question document for version 1 of the question
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestion(1, SEQUENCE_STATUS_ARCHIVED));
        documentService.routeDocument(maintDocument, null, null);
        
        // Create & submit new Question document for version 2 of the question
        maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 2"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(createQuestion(2, SEQUENCE_STATUS_CURRENT));
        documentService.routeDocument(maintDocument, null, null);
        
        List<? extends BusinessObject> searchResults = questionLookupableHelperServiceImpl.getSearchResults(new HashMap());
        Assert.assertEquals(1, searchResults.size());
        // Check that the current version is being returned
        Question question = (Question)searchResults.get(0);
        Assert.assertEquals("test2", question.getQuestion());
    }

    /**
     * 
     * This method tests getCustomActionUrls with VIEW_QUESTION permission
     * @throws Exception
     */
    @Test
    public void testGetCustomActionUrlsWithViewQuestionPermission() throws Exception {
        final QuestionAuthorizationService questionAuthorizationService = context.mock(QuestionAuthorizationService.class);
        questionLookupableHelperServiceImpl.setQuestionAuthorizationService(questionAuthorizationService);
        context.checking(new Expectations() {{
            one(questionAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTION);
            will(returnValue(false));
            one(questionAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTION);
            will(returnValue(true));
        }});
  
        Question question = createQuestion(1, SEQUENCE_STATUS_CURRENT);
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        documentService.routeDocument(maintDocument,null,null);
        
        List pkNames = new ArrayList();
        pkNames.add("questionRefId");
        
        List<HtmlData> htmldata = questionLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);
        Assert.assertEquals(htmldata.size(), 1);
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(0)).getHref(), "../maintenance.do?questionRefId="+question.getQuestionRefId()+"&businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=edit&readOnly=true");
    }
    
    /**
     * 
     * This method tests getCustomActionUrls with MODIFY_QUESTION permission
     * @throws Exception
     */
    @Test
    public void testGetCustomActionUrlsWithModifyQuestionPermission() throws Exception {
        final QuestionAuthorizationService questionAuthorizationService = context.mock(QuestionAuthorizationService.class);
        questionLookupableHelperServiceImpl.setQuestionAuthorizationService(questionAuthorizationService);
        context.checking(new Expectations() {{
            one(questionAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTION);
            will(returnValue(true));
            one(questionAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTION);
            will(returnValue(false));
        }});
  
        Question question = createQuestion(1, SEQUENCE_STATUS_CURRENT);
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        documentService.routeDocument(maintDocument,null,null);
        
        List pkNames = new ArrayList();
        pkNames.add("questionRefId");
        
        List<HtmlData> htmldata = questionLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);
        Assert.assertEquals(htmldata.size(), 3);
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(0)).getHref(), "../maintenance.do?questionRefId="+question.getQuestionRefId()+"&businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=edit");
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(1)).getHref(), "../maintenance.do?questionRefId="+question.getQuestionRefId()+"&businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=copy");
        Assert.assertEquals(((AnchorHtmlData)htmldata.get(2)).getHref(), "../maintenance.do?questionRefId="+question.getQuestionRefId()+"&businessObjectClassName=org.kuali.kra.questionnaire.question.Question&methodToCall=edit&readOnly=true");
    }

    /**
     * 
     * This method tests getCustomActionUrls with no permissions
     * @throws Exception
     */
    @Test
    public void testGetCustomActionUrlsWithNoPermissions() throws Exception {
        final QuestionAuthorizationService questionAuthorizationService = context.mock(QuestionAuthorizationService.class);
        questionLookupableHelperServiceImpl.setQuestionAuthorizationService(questionAuthorizationService);
        context.checking(new Expectations() {{
            one(questionAuthorizationService).hasPermission(PermissionConstants.MODIFY_QUESTION);
            will(returnValue(false));
            one(questionAuthorizationService).hasPermission(PermissionConstants.VIEW_QUESTION);
            will(returnValue(false));
        }});
  
        Question question = createQuestion(1, SEQUENCE_STATUS_CURRENT);
        MaintenanceDocumentBase maintDocument = (MaintenanceDocumentBase) documentService.getNewDocument(KraServiceLocator.getService(MaintenanceDocumentDictionaryService.class).getDocumentTypeName(Question.class));
        maintDocument.getDocumentHeader().setDocumentDescription("test 1"); 
        maintDocument.getNewMaintainableObject().setBusinessObject(question);
        documentService.routeDocument(maintDocument,null,null);

        List pkNames = new ArrayList();
        pkNames.add("questionRefId");
        
        List<HtmlData> htmldata = questionLookupableHelperServiceImpl.getCustomActionUrls(maintDocument.getNewMaintainableObject().getBusinessObject(), pkNames);
        Assert.assertEquals(htmldata.size(), 0);
    }
    
    /**
     * 
     * This method creates a simple question 
     * @param questionRefId 
     * @parm sequenceStatus
     * @return question
     */
    private Question createQuestion(int questionRefId, String sequenceStatus) {
        Question question = new Question();
        question.setQuestionRefId(Long.valueOf(questionRefId));
        question.setQuestionId(questionRefId);
        question.setSequenceStatus(sequenceStatus);
        question.setQuestion("test" + questionRefId);
        question.setStatus("A");
        question.setCategoryTypeCode(1);
        question.setQuestionTypeId(1);  
        return question;
    }
}
