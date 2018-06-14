/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate;

import java.util.regex.Matcher;

import org.junit.Test;

import junit.framework.Assert;

public class ExPlaceholderTest {
    private static final String single = "${world}";
    private static final String multiple = "hello ${world} it's ${me.friend.world} ${spider_man}";
    
    @Test
    public void testProperty() {
        Matcher matcher;
        String p;
        EzPlaceholder property = EzPlaceholder.property;
        
        matcher = property.matcher(single);
        Assert.assertTrue(matcher != null);
        Assert.assertTrue(matcher.find());
        p = matcher.group();
        Assert.assertTrue("${world}".equals(p));
        Assert.assertTrue("world".equals(property.property(p)));
        
        matcher = property.matcher(multiple);
        Assert.assertTrue(matcher != null);
        Assert.assertTrue(matcher.find());
        p = matcher.group();
        Assert.assertTrue("${world}".equals(p));
        Assert.assertTrue("world".equals(property.property(p)));
        Assert.assertTrue(matcher.find());
        p = matcher.group();
        Assert.assertTrue("${me.friend.world}".equals(p));
        Assert.assertTrue("me.friend.world".equals(property.property(p)));
        Assert.assertTrue(matcher.find());
        p = matcher.group();
        Assert.assertTrue("${spider_man}".equals(p));
        Assert.assertTrue("spider_man".equals(property.property(p)));
    }
}
