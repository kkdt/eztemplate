/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate;

import java.io.IOException;
import java.util.stream.Stream;

import kkdt.eztemplate.TemplateWriter.TemplateOutput;

/**
 * Convenient utility class for building templates.
 * 
 * @author thinh ho
 *
 */
public class TemplateFactory {
    /**
     * Given an input template and an optional translator, write the template out
     * the the provided {@code TemplateOutput} specification(s).
     * 
     * @param input template text.
     * @param translator (optional) template translator.
     * @param outputs output specifications.
     */
    public static void buildTemplates(String input, TemplateTranslator translator, TemplateOutput...outputs) {
        TemplateWriter writer = new TemplateWriter();
        writer.setTemplateTranslator(translator);
        Stream.of(outputs).forEach(o -> {
            try {
                writer.write(input, o);
            } catch (IOException e) {
                throw new TemplateIOException(e);
            }
        });
    }
}
