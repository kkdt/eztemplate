/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class ApacheCommonsTextReplacementTest {
    private static String longText;
    
    @Rule
    public Timeout globalTimeout = new Timeout(100);
    
    @BeforeClass
    public static void setup() throws IOException {
        try(InputStream is = ApacheCommonsTextReplacementTest.class.getClassLoader().getResourceAsStream("declaration.txt")) {
            assertTrue(is != null);
            longText = IOUtils.toString(is, "UTF-8");
            assertTrue(longText != null);
        }
    }
    
    @Test
    public void testSimpleReplacement() {
        String txt = "Hello ${world}!";
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("world", ApacheCommonsTextReplacementTest.class.getSimpleName());
        StrSubstitutor substituor = new StrSubstitutor(dictionary);
        assertTrue(("Hello " + ApacheCommonsTextReplacementTest.class.getSimpleName() + "!").equals(substituor.replace(txt)));
    }
    
    @Test
    public void test1ReplacementsInLongText() {
        Map<String, String> dictionary = Collections.unmodifiableMap(Stream.of(
                new SimpleEntry<>("power", "xray vision")
            ).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
        );
        
        StrSubstitutor substituor = new StrSubstitutor(dictionary);
        assertTrue(!longText.contains("xray vision"));
        String replaced = substituor.replace(longText);
        assertTrue(replaced.contains("xray vision"));
    }
    
    @Test
    public void test2ReplacementsInLongText() {
        Map<String, String> dictionary = Collections.unmodifiableMap(Stream.of(
                new SimpleEntry<>("power", "xray vision"),
                new SimpleEntry<>("forbidden", "never forbidden")
            ).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
        );
        
        StrSubstitutor substituor = new StrSubstitutor(dictionary);
        assertTrue(!longText.contains("xray vision"));
        assertTrue(!longText.contains("never forbidden"));
        String replaced = substituor.replace(longText);
        assertTrue(replaced.contains("xray vision"));
        assertTrue(replaced.contains("never forbidden"));
    }
    
    @Test
    public void testReplaceCommonInLongText() {
        Map<String, String> dictionary = Collections.unmodifiableMap(Stream.of(
                new SimpleEntry<>("the", "TheNewThe"),
                new SimpleEntry<>("has", "TheNewHas")
            ).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()))
        );
        
        StrSubstitutor substituor = new StrSubstitutor(dictionary);
        assertTrue(!longText.contains("TheNewThe"));
        assertTrue(!longText.contains("TheNewHas"));
        String replaced = substituor.replace(longText);
        assertTrue(replaced.contains("TheNewThe"));
        assertTrue(replaced.contains("TheNewHas"));
    }
}
