/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * This object writes an input template out to the defined {@code TemplateOutput},
 * performing translation if a {@code TemplateTranslator} is configured. 
 * 
 * @author thinh ho
 *
 */
public class TemplateWriter {
    /**
     * A functional interface that describes how template text result will
     * be formatted and saved.
     * 
     * @author thinh ho
     *
     */
    @FunctionalInterface
    public interface TemplateOutput extends Consumer<String> {}
    
    private TemplateTranslator templateTranslator;
    
    public TemplateWriter() {}
    
    public TemplateWriter(TemplateTranslator templateTranslator) {
        this.templateTranslator = templateTranslator;
    }

    /**
     * The translator to use for the input template text.
     * 
     * @param templateTranslator
     */
    public void setTemplateTranslator(TemplateTranslator templateTranslator) {
        this.templateTranslator = templateTranslator;
    }

    /**
     * Write the input template.
     * 
     * @param input
     * @param output determines how output data is handled
     * @throws IOException
     */
    public void write(String input, TemplateOutput output) throws IOException {
        String outputText = templateTranslator != null ? templateTranslator.translate(input) : input;
        Optional.ofNullable(output).ifPresent(s -> {
            output.accept(outputText);
        });
    }

}
