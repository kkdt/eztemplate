/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class PlaceholderTest {
    private static final String propertyRegex = "[\\w\\.]+";
    private static final String placeholderRegex = "\\$\\{[\\w\\.]+\\}";
    private Pattern placeholder;
    private Pattern property;
    
    @Before
    public void before() {
        placeholder = Pattern.compile(placeholderRegex);
        property = Pattern.compile(propertyRegex);
    }
    
    @Test
    public void testSpacesTabs() {
        String _regex = "[\\t\\s]+";
        String s = "Hello world\twhat      is up.";
        String[] tokens = s.split(_regex);
        Assert.assertTrue(tokens.length == 5);
    }
    
    @Test
    public void testPlaceholderCharacters() {
        Assert.assertTrue(Pattern.matches("\\$", "$"));
        Assert.assertTrue(Pattern.matches("\\{", "{"));
        Assert.assertTrue(Pattern.matches("\\}", "}"));
        Assert.assertTrue(Pattern.matches("\\$\\{\\}", "${}"));
        String _regex = "\\$\\{[\\w\\.]+\\}";
        Assert.assertTrue(Pattern.matches(_regex, "${hello}"));
        Assert.assertTrue(Pattern.matches(_regex, "${hello.world}"));
    }
    
    @Test
    public void testSingleMatch() {
        String s = Matcher.quoteReplacement("${world}");
        Matcher matcher = placeholder.matcher(s);
        
        Assert.assertTrue(matcher.find());
        String g = matcher.group();
        Assert.assertTrue("${world}".equals(g));
        
        matcher = property.matcher(g);
        Assert.assertTrue(matcher.find());
        Assert.assertTrue("world".equals(matcher.group()));
    }
    
    @Test
    public void testMultipleMatches() {
        String s = "hello ${world} it's ${me.friend.world} ${spider_man}";
        // need to quote replacement or else it will not match
        Assert.assertFalse(Pattern.matches(placeholderRegex, s));
        
        Matcher p;
        String g;
        Matcher matcher = placeholder.matcher(Matcher.quoteReplacement(s));
        
        Assert.assertTrue(matcher.find());
        g = matcher.group();
        Assert.assertTrue("${world}".equals(g));
        p = property.matcher(g);
        Assert.assertTrue(p.find());
        Assert.assertTrue("world".equals(p.group()));
        
        Assert.assertTrue(matcher.find());
        g = matcher.group();
        Assert.assertTrue("${me.friend.world}".equals(g));
        p = property.matcher(g);
        Assert.assertTrue(p.find());
        Assert.assertTrue("me.friend.world".equals(p.group()));
        
        Assert.assertTrue(matcher.find());
        g = matcher.group();
        Assert.assertTrue("${spider_man}".equals(g));
        p = property.matcher(g);
        Assert.assertTrue(p.find());
        Assert.assertTrue("spider_man".equals(p.group()));
    }
}
