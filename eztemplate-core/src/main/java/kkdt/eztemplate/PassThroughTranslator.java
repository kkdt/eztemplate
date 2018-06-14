/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate;

/**
 * No translation will occur using this implementation - whatever is passed-in
 * will be the result, unchanged.
 * 
 * @author thinh ho
 *
 */
public class PassThroughTranslator implements TemplateTranslator {

    @Override
    public String translate(String template) {
        return template;
    }

}
