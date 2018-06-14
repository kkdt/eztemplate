/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Supported placeholder texts.
 * 
 * @author thinh ho
 *
 */
public enum EzPlaceholder {
    /**
     * Property placeholders <code>${property.key}</code>.
     */
    property("\\$\\{[\\w\\.]+\\}");
    
    private final Pattern pattern;
    private final Pattern p;
    
    EzPlaceholder(String regex) {
        this.pattern = Pattern.compile(regex);
        this.p = Pattern.compile("[\\w\\.]+");
    }
    
    /**
     * The <code>Matcher</code> to manipulate for the specified text.
     * 
     * @param text
     * @return
     */
    public Matcher matcher(final String text) {
        return pattern.matcher(Matcher.quoteReplacement(text));
    }
    
    /**
     * Extract the property value for the specified placeholder.
     * 
     * @param placeholder
     * @return the placeholder; or null.
     */
    public String property(final String placeholder) {
        Matcher matcher = p.matcher(placeholder);
        return matcher.find() ? matcher.group() : null;
    }
}
