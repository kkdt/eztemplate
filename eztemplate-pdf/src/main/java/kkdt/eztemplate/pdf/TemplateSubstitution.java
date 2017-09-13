/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate.pdf;

import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;

import kkdt.eztemplate.TemplateTranslator;

/**
 * A key-value string substitution translator via {@code StrSubstitutor}.
 * 
 * @author thinh ho
 *
 */
public class TemplateSubstitution implements TemplateTranslator {
    private final StrSubstitutor substition;
    
    /**
     * Must provide a map for the dictionary.
     * 
     * @param dictionary
     */
    @SuppressWarnings("rawtypes")
    public TemplateSubstitution(Map dictionary) {
        this.substition = new StrSubstitutor(dictionary);
    }
    
    @Override
    public String translate(String template) {
        return substition.replace(template);
    }

}
