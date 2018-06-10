/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import kkdt.eztemplate.TemplateFactory;
import kkdt.eztemplate.pdf.PdfOut;
import kkdt.eztemplate.pdf.TemplateSubstitution;

/**
 * UI pdf generator via a template and dictionary files.
 * 
 * @author thinh ho
 *
 */
public class PdfGenerator {
    private final File outputDir;
    
    public PdfGenerator(File outputDir) {
        this.outputDir = outputDir;
    }
    
    public File generate(File template, File dictionary, String filename) throws IOException {
        final Properties properties = new Properties();
        try (final InputStream stream = new FileInputStream(dictionary)) {
            properties.load(stream);
        }
        
        String input = null;
        try (final InputStream stream = new FileInputStream(template)) {
            Charset charset = Charset.defaultCharset();
            input = IOUtils.toString(stream, charset);
        }
        
        File file = outputDir.toPath().resolve(filename).toFile();
        try(FileOutputStream out = new FileOutputStream(file)) {
            TemplateFactory.buildTemplates(input, new TemplateSubstitution(properties), new PdfOut(out));
        }
        return file;
    }
}
