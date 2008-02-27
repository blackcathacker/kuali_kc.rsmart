/*
 * Copyright 2008 The Kuali Foundation.
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
package org.kuali.kra.proposaldevelopment.web;

import org.junit.Test;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.test.data.PerTestUnitTestData;
import org.kuali.rice.test.data.UnitTestData;
import org.kuali.rice.test.data.UnitTestFile;
import org.kuali.rice.test.data.UnitTestSql;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

@PerTestUnitTestData(
        @UnitTestData(
            sqlStatements = {
                    @UnitTestSql("delete from S2S_SUBMISSION_TYPE")
                    ,@UnitTestSql("delete from S2S_REVISION_TYPE")    
            },
            sqlFiles = {
                @UnitTestFile(filename = "classpath:sql/dml/load_s2s_submission_type.sql", delimiter = ";")
                ,@UnitTestFile(filename = "classpath:sql/dml/load_s2s_revision_type.sql", delimiter = ";")
            }
        )
    )
public class S2sOpportunityWebTest extends ProposalDevelopmentWebTestBase {
    private static final String GRANTS_GOV_IMAGE_NAME = "methodToCall.headerTab.headerDispatch.save.navigateTo.grantsGov.x";
    
    /*
    @Test
    public void testS2sOpportunity() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        
        setFieldValue(proposalPage, "document.programAnnouncementTitle", "we want to give you money");
        setFieldValue(proposalPage, "document.cfdaNumber", "84.264");
        setFieldValue(proposalPage, "document.programAnnouncementNumber", "ED-GRANTS-102003-003");
        
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");
        
        String documentNumber = getFieldValue(savedProposalPage, "document.documentHeader.documentNumber");
        
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);
        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber");
        
        clickOn(page2, "methodToCall.save", "Kuali :: Proposal Development Document");
        
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) documentService.getByDocumentHeaderId(documentNumber);
        
        assertEquals("84.264",doc.getS2sOpportunity().getCfdaNumber());
    }*/
    
    @Test
    public void testS2sOpportunityCfdaNumberOpportunityIDPassedInLookup() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        
        setFieldValue(proposalPage, "document.programAnnouncementTitle", "we want to give you money");
        setFieldValue(proposalPage, "document.cfdaNumber", "84.264");
        setFieldValue(proposalPage, "document.programAnnouncementNumber", "ED-GRANTS-102003-003");
        
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber");
        
        assertContains(page2,"84.264");
        assertContains(page2,"ED-GRANTS-102003-003");
    }

    @Test
    public void testS2sOpportunityOnlyCfdaNumberPassedInLookup() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);        
        setFieldValue(proposalPage, "document.cfdaNumber", "84.264");                

        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");                
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber");
        
        assertContains(page2,"84.264");
    }    
    
    @Test
    public void testS2sOpportunityOnlyOpportunityIdPassedInLookup() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);        
        setFieldValue(proposalPage, "document.programAnnouncementNumber", "ED-GRANTS-102003-003");
        
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");               
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber");
                       
        assertContains(page2,"ED-GRANTS-102003-003");           
    }        
   
    @Test
    public void testS2sOpportunityOnlyInvalidOpportunityIdPassedInLookup() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);        
        setFieldValue(proposalPage, "document.programAnnouncementNumber", "InValidOpportunityID");
        
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");        
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber","opportunityId","InValidOpportunityID",true);
        
        assertContains(page2,"Not a S2S Candidate");                     
    }        

    @Test
    public void testS2sOpportunityOnlyInvalidCfdaNumberPassedInLookup() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);        
        setFieldValue(proposalPage, "document.cfdaNumber", "11.111");
        
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");        
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber","cfdaNumber","InValid",true);
        
        assertContains(page2,"Not a valid CFDA number");         
    }

    @Test
    public void testS2sOpportunitySave() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        
        String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");
                
        setFieldValue(proposalPage, "document.programAnnouncementTitle", "we want to give you money");
        setFieldValue(proposalPage, "document.cfdaNumber", "84.264");
        setFieldValue(proposalPage, "document.programAnnouncementNumber", "ED-GRANTS-102003-003");
        
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber","opportunityId","ED-GRANTS-102003-003",false);
        
        assertContains(page2,"84.264");
        assertContains(page2,"ED-GRANTS-102003-003");
        
        setFieldValue(page2,"document.s2sOpportunity.s2sSubmissionTypeCode","1");
        clickOn(page2, "methodToCall.save", "Kuali :: Proposal Development Document");
        
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
        
        assertEquals(doc.getS2sOpportunity().getOpportunityId(),"ED-GRANTS-102003-003");
        assertEquals(doc.getS2sOpportunity().getCfdaNumber(),"84.264");
        assertEquals(doc.getS2sOpportunity().getS2sSubmissionTypeCode().toString(),"1");
    }
        
    @Test
    public void testS2sOpportunitySaveRevisionTypeOtherTextFieldBlank() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);
                        
        setFieldValue(proposalPage, "document.programAnnouncementTitle", "we want to give you money");
        setFieldValue(proposalPage, "document.cfdaNumber", "84.264");
        setFieldValue(proposalPage, "document.programAnnouncementNumber", "ED-GRANTS-102003-003");
        
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");        
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber","opportunityId","ED-GRANTS-102003-003",false);        

        assertContains(page2,"84.264");
        assertContains(page2,"ED-GRANTS-102003-003");
        
        setFieldValue(page2,"document.s2sOpportunity.s2sSubmissionTypeCode","1");
        setFieldValue(page2,"document.s2sOpportunity.revisionCode","5");
        HtmlPage page3 = clickOn(page2, "methodToCall.save", "Kuali :: Proposal Development Document");
        
        assertContains(page3,"If Revision Type is Other, the Description must be present for it");                
    }    

    @Test
    public void testS2sOpportunitySaveRevisionTypeOtherTextFieldmNotBlank() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        
        String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");
                
        setFieldValue(proposalPage, "document.programAnnouncementTitle", "we want to give you money");
        setFieldValue(proposalPage, "document.cfdaNumber", "84.264");
        setFieldValue(proposalPage, "document.programAnnouncementNumber", "ED-GRANTS-102003-003");
        
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");        
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber","opportunityId","ED-GRANTS-102003-003",false);        

        assertContains(page2,"84.264");
        assertContains(page2,"ED-GRANTS-102003-003");
        
        setFieldValue(page2,"document.s2sOpportunity.s2sSubmissionTypeCode","1");
        setFieldValue(page2,"document.s2sOpportunity.revisionCode","5");
        setFieldValue(page2,"document.s2sOpportunity.revisionOtherDescription","RevisionType Is Other");
        clickOn(page2, "methodToCall.save", "Kuali :: Proposal Development Document");
        
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
        
        assertEquals(doc.getS2sOpportunity().getOpportunityId(),"ED-GRANTS-102003-003");
        assertEquals(doc.getS2sOpportunity().getCfdaNumber(),"84.264");
        assertEquals(doc.getS2sOpportunity().getS2sSubmissionTypeCode().toString(),"1");
        assertEquals(doc.getS2sOpportunity().getRevisionCode().toString(),"5");
        assertEquals(doc.getS2sOpportunity().getRevisionOtherDescription(),"RevisionType Is Other");
    }   
    

    @Test
    public void testS2sOpportunitySaveRevisionTypeNotOtherTextFieldNotBlank() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        
        String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");
                
        setFieldValue(proposalPage, "document.programAnnouncementTitle", "we want to give you money");
        setFieldValue(proposalPage, "document.cfdaNumber", "84.264");
        setFieldValue(proposalPage, "document.programAnnouncementNumber", "ED-GRANTS-102003-003");
        
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");        
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber","opportunityId","ED-GRANTS-102003-003",false);        

        assertContains(page2,"84.264");
        assertContains(page2,"ED-GRANTS-102003-003");
        
        setFieldValue(page2,"document.s2sOpportunity.s2sSubmissionTypeCode","1");
        setFieldValue(page2,"document.s2sOpportunity.revisionCode","1");
        setFieldValue(page2,"document.s2sOpportunity.revisionOtherDescription","RevisionType Is Other");
        clickOn(page2, "methodToCall.save", "Kuali :: Proposal Development Document");
        
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
        
        assertEquals(doc.getS2sOpportunity().getOpportunityId(),"ED-GRANTS-102003-003");
        assertEquals(doc.getS2sOpportunity().getCfdaNumber(),"84.264");
        assertEquals(doc.getS2sOpportunity().getS2sSubmissionTypeCode().toString(),"1");
        assertEquals(doc.getS2sOpportunity().getRevisionCode().toString(),"1");
        assertNull(doc.getS2sOpportunity().getRevisionOtherDescription());        
    }
    
    @Test
    public void testS2sRemoveOpportunity() throws Exception{
        HtmlPage proposalPage = getProposalDevelopmentPage();

        setRequiredFields(proposalPage, DEFAULT_DOCUMENT_DESCRIPTION, "005891", DEFAULT_PROPOSAL_TITLE, "08/14/2007", "08/21/2007", DEFAULT_PROPOSAL_ACTIVITY_TYPE, DEFAULT_PROPOSAL_TYPE_CODE, DEFAULT_PROPOSAL_OWNED_BY_UNIT);
        
        String documentNumber = getFieldValue(proposalPage, "document.documentHeader.documentNumber");
                
        setFieldValue(proposalPage, "document.programAnnouncementTitle", "we want to give you money");
        setFieldValue(proposalPage, "document.cfdaNumber", "84.264");
        setFieldValue(proposalPage, "document.programAnnouncementNumber", "ED-GRANTS-102003-003");
        
        HtmlPage savedProposalPage = clickOn(proposalPage, "methodToCall.save", "Kuali :: Proposal Development Document");
        HtmlPage page1 = clickOn(savedProposalPage, GRANTS_GOV_IMAGE_NAME);        
        HtmlPage page2 = lookup(page1, "document.programAnnouncementNumber","opportunityId","ED-GRANTS-102003-003",false);
        
        assertContains(page2,"84.264");
        assertContains(page2,"ED-GRANTS-102003-003");
        
        setFieldValue(page2,"document.s2sOpportunity.s2sSubmissionTypeCode","1");
        HtmlPage page3 = clickOn(page2, "methodToCall.save", "Kuali :: Proposal Development Document");
        
        ProposalDevelopmentDocument doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
        
        assertEquals(doc.getS2sOpportunity().getOpportunityId(),"ED-GRANTS-102003-003");
        assertEquals(doc.getS2sOpportunity().getCfdaNumber(),"84.264");
        assertEquals(doc.getS2sOpportunity().getS2sSubmissionTypeCode().toString(),"1");
        
        HtmlPage page4 = clickOn(page3, "methodToCall.removeOpportunity", "Kuali :: Question Dialog Page");
        clickOn(page4, "methodToCall.processAnswer.button0", "Kuali :: Proposal Development Document");
        doc = (ProposalDevelopmentDocument) getDocument(documentNumber);
        assertNull(doc.getS2sOpportunity());
    }

}
