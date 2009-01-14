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
package org.kuali.kra.award.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.web.ui.KeyLabelPair;

public class FrequencyCodeValuesFinderTest {
    
    FrequencyCodeValuesFinder frequencyCodeValuesFinder;
    List<KeyLabelPair> frequencyCodes;

    @Before
    public void setUp() throws Exception {
        frequencyCodeValuesFinder = new FrequencyCodeValuesFinder();
        frequencyCodeValuesFinder.setReportClassCode("1");
        frequencyCodes = new ArrayList<KeyLabelPair>();
        frequencyCodes = frequencyCodeValuesFinder.getKeyValues();
    }

    @After
    public void tearDown() throws Exception {
        frequencyCodeValuesFinder = null;
        frequencyCodes = null;
    }

    @Test
    public final void testGetKeyValues() {
        Assert.assertEquals(30,frequencyCodes.size());
    }
    
    @Test
    public final void testGetKeyValuesAreNotNull() {
        for(KeyLabelPair keyLabelPair:frequencyCodes){
            Assert.assertNotNull(keyLabelPair.getKey());
            Assert.assertNotNull(keyLabelPair.getLabel());
        }
    }
    

}

