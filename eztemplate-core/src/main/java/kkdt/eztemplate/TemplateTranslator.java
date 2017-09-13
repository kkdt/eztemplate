/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate;

/**
 * Simple functional interface that translate a template string.
 * 
 * @author thinh ho
 *
 */
@FunctionalInterface
public interface TemplateTranslator {
    /**
     * Translate the specified template text.
     * 
     * @param template
     * @return the translated text.
     */
    String translate(String template);
}
