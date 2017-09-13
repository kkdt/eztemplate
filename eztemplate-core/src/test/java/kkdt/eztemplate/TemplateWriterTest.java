/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate;

import org.junit.BeforeClass;
import org.junit.Test;

import kkdt.eztemplate.TemplateTranslator;
import kkdt.eztemplate.TemplateWriter;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

public class TemplateWriterTest {
    private static TemplateTranslator defaultTranslator;
    
    @BeforeClass
    public static void setup() {
        defaultTranslator = template -> {
            return template.replace("the", "hello");
        };
    }
    
    @Test
    public void testSimpleTranslation() {
        TemplateTranslator t = template -> {
            return template.replace("test", "final");
        };
        assertTrue("This is a final message".equals(t.translate("This is a test message")));
    }
    
    @Test
    public void testMissingTranslator() throws IOException {
        String input = "original message!";
        new TemplateWriter().write(input, s -> {
            assertTrue(s.equals(input));
        });
    }
    
    @Test
    public void testWriterTranslator() throws IOException {
        String input = "the world!";
        new TemplateWriter(defaultTranslator).write(input, s -> {
            assertTrue(s.equals("hello world!"));
        });
    }
}
