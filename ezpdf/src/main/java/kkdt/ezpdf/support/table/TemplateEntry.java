/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support.table;

import java.io.File;

import kkdt.generictable.OrderedColumn;

public class TemplateEntry {
    @OrderedColumn(index=0, displayName="Template", name="Template", type=File.class)
    private File template;
    
    @OrderedColumn(index=1, displayName="Dictionary", name="Dictionary", type=File.class)
    private File dictionary;
    
    @OrderedColumn(index=2, displayName="Output", name="Output", type=File.class)
    private File output;

    public File getOutput() {
        return output;
    }

    public void setOutput(File output) {
        this.output = output;
    }

    public File getTemplate() {
        return template;
    }

    public void setTemplate(File template) {
        this.template = template;
    }

    public File getDictionary() {
        return dictionary;
    }

    public void setDictionary(File dictionary) {
        this.dictionary = dictionary;
    }
    
}
