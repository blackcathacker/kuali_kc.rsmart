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
package org.kuali.kra.proposaldevelopment.printing.print;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.SponsorFormTemplate;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.proposaldevelopment.printing.service.impl.ProposalDevelopmentPrintingServiceImpl;
import org.kuali.kra.proposaldevelopment.printing.xmlstream.ResearchAndRelatedXmlStream;
import org.kuali.kra.s2s.S2SException;
import org.kuali.rice.kns.service.BusinessObjectService;

/**
 * This class provides the implementation for printing Print Certification
 * Report. It generates XML that conforms with Certification Report XSD, fetches
 * XSL style-sheets applicable to this XML, returns XML and XSL for any consumer
 * that would use this XML and XSls for any purpose like report generation, PDF
 * streaming etc.
 * 
 */
public class PrintProposalSponsorForms extends AbstractPrint {

    private BusinessObjectService businessObjectService;
    private ResearchAndRelatedXmlStream researchAndRelatedXmlStream;
	/**
	 * Fetches the {@link ResearchDocumentBase}
	 * 
	 * @return {@link ResearchDocumentBase} document
	 */
	public ResearchDocumentBase getDocument() {
		return document;
	}

	/**
	 * This method fetches the XSL style-sheets required for transforming the
	 * generated XML into PDF.
	 * 
	 * @return {@link ArrayList}} of {@link Source} XSLs
	 */
	public ArrayList<Source> getXSLT() {
		ArrayList<Source> sourceList = new ArrayList<Source>(); 
		List<SponsorFormTemplate> printFormTemplates = (List<SponsorFormTemplate>)getReportParameters().get(ProposalDevelopmentPrintingServiceImpl.SELECTED_TEMPLATES);
		for (SponsorFormTemplate sponsorFormTemplate : printFormTemplates) {
		    Map<String,Object> htData = new HashMap<String,Object>();
		    htData.put("sponsorCode", sponsorFormTemplate.getSponsorCode());
		    htData.put("packageNumber", sponsorFormTemplate.getPackageNumber());
		    htData.put("pageNumber", sponsorFormTemplate.getPageNumber());
		    SponsorFormTemplate sponsorTemplate = (SponsorFormTemplate)getBusinessObjectService().findByPrimaryKey(SponsorFormTemplate.class, htData);
		    sourceList.add(new StreamSource(new ByteArrayInputStream(sponsorTemplate.getAttachmentContent())));
        }
		return sourceList;
	}

    /**
     * Prints the proposal sponsor forms by passing the given proposal
     * information to {@link ProposalPrintReader}
     * 
     * @param proposalNumber
     *            proposal number.
     * @param sponsorFormTemplates
     *            list of SponsorFormTemplate.
     * @return byte array of forms corresponding to the proposal number and
     *         SponsorFormTemplate objects.
     * @throws S2SException
     * @see org.kuali.kra.s2s.service.PrintService#printProposalSponsorForms(java.lang.String,
     *      java.util.List)
     */
    public byte[] printProposalSponsorForms(String proposalNumber,
            List<SponsorFormTemplate> sponsorFormTemplates) throws S2SException {
        // List<Map<String, Object>> listData = new ArrayList<Map<String,
        // Object>>();
        // for (SponsorFormTemplate sponsorFormTemplate : sponsorFormTemplates)
        // {
        // SponsorTemplateBean coeusSponsorTemplate = new SponsorTemplateBean();
        // try {
        // BeanUtils.copyProperties(coeusSponsorTemplate, sponsorFormTemplate);
        // }
        // catch (IllegalAccessException e) {
        // LOG.error(e.getMessage(), e);
        // throw new S2SException(e);
        // }
        // catch (InvocationTargetException e) {
        // LOG.error(e.getMessage(), e);
        // throw new S2SException(e);
        // }
        // listData.add(getproposalSponsorMap(proposalNumber,
        // sponsorFormTemplate, coeusSponsorTemplate));
        // }
        // Map<String, List<Map<String, Object>>> map = new HashMap<String,
        // List<Map<String, Object>>>();
        // map.put(KEY_PRINT_PROPOSAL, listData);
        // ProposalPrintReader proposalPrintReader = new ProposalPrintReader();
        // try {
        // return proposalPrintReader.read(map).getDocumentData();
        // }
        // catch (CoeusException e) {
        // LOG.error(e.getMessage(), e);
        // return null;
        // }
        throw new RuntimeException("Unsupported functionality");
    }

    /**
     * Gets the researchAndRelatedXmlStream attribute. 
     * @return Returns the researchAndRelatedXmlStream.
     */
    public ResearchAndRelatedXmlStream getResearchAndRelatedXmlStream() {
        return researchAndRelatedXmlStream;
    }

    /**
     * Sets the researchAndRelatedXmlStream attribute value.
     * @param researchAndRelatedXmlStream The researchAndRelatedXmlStream to set.
     */
    public void setResearchAndRelatedXmlStream(ResearchAndRelatedXmlStream researchAndRelatedXmlStream) {
        this.researchAndRelatedXmlStream = researchAndRelatedXmlStream;
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
