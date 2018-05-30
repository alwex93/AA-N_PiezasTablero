/*
 * Copyright (c) 2003 The Visigoth Software Society. All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Visigoth Software Society (http://www.visigoths.org/)."
 *    Alternately, this acknowledgement may appear in the software itself,
 *    if and wherever such third-party acknowledgements normally appear.
 *
 * 4. Neither the name "FreeMarker", "Visigoth", nor any of the names of the 
 *    project contributors may be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact visigoths@visigoths.org.
 *
 * 5. Products derived from this software may not be called "FreeMarker" or "Visigoth"
 *    nor may "FreeMarker" or "Visigoth" appear in their names
 *    without prior written permission of the Visigoth Software Society.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE VISIGOTH SOFTWARE SOCIETY OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Visigoth Software Society. For more
 * information on the Visigoth Software Society, please see
 * http://www.visigoths.org/
 */

package freemarker.testcase;

import java.util.HashMap;
import java.util.Map;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateModelException;

/**
 * @version $Id: TestBeanMaps.java,v 1.2 2003/04/02 11:25:53 szegedia Exp $
 */
public class TestBeanMaps extends AbstractTestCase
{
    public TestBeanMaps(String name)
    {
        super(name);
    }

    /**
     * Set up the test case prior to running.
     */
    public void setUp()
    throws TemplateModelException
    {
        setUpFiles( "test-bean-maps.html" );

        BeansWrapper w1 = new BeansWrapper();
        BeansWrapper w2 = new BeansWrapper();
        BeansWrapper w3 = new BeansWrapper();
        BeansWrapper w4 = new BeansWrapper();
        BeansWrapper w5 = new BeansWrapper();
        BeansWrapper w6 = new BeansWrapper();
        BeansWrapper w7 = new BeansWrapper();

        w1.setExposureLevel(BeansWrapper.EXPOSE_PROPERTIES_ONLY);
        w2.setExposureLevel(BeansWrapper.EXPOSE_PROPERTIES_ONLY);
        w3.setExposureLevel(BeansWrapper.EXPOSE_NOTHING);
        w4.setExposureLevel(BeansWrapper.EXPOSE_NOTHING);
        w5.setExposureLevel(BeansWrapper.EXPOSE_ALL);
        w6.setExposureLevel(BeansWrapper.EXPOSE_ALL);

        w1.setMethodsShadowItems(true);
        w2.setMethodsShadowItems(false);
        w3.setMethodsShadowItems(true);
        w4.setMethodsShadowItems(false);
        w5.setMethodsShadowItems(true);
        w6.setMethodsShadowItems(false);

        w7.setSimpleMapWrapper(true);

        Object test = getTestBean();

        root.put("m1", w1.wrap(test));
        root.put("m2", w2.wrap(test));
        root.put("m3", w3.wrap(test));
        root.put("m4", w4.wrap(test));
        root.put("m5", w5.wrap(test));
        root.put("m6", w6.wrap(test));
        root.put("m7", w7.wrap(test));
    }

    public Object getTestBean()
    {
        Map testBean = new TestBean();
        testBean.put("name", "Chris");
        testBean.put("location", "San Francisco");
        testBean.put("age", new Integer(27));
        return testBean;
    }

    public static class TestBean extends HashMap {
        public String getName() {
            return "Christopher";
        }
        public int getLuckyNumber() {
            return 7;
        }
    }

    /** Bootstrap for the self-test code.
     */
    public static void main( String[] argc ) throws Exception {
        AbstractTestCase cTest = new TestBeanMaps( "test-bean-maps.html" );
        cTest.run();
    }
}
