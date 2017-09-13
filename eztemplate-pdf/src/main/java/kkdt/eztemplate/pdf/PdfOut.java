/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate.pdf;

import java.io.OutputStream;
import java.util.stream.Stream;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import kkdt.eztemplate.TemplateWriter.TemplateOutput;

/**
 * PDF output implementation using the iText library.
 * 
 * @author thinh ho
 *
 */
public class PdfOut implements TemplateOutput {
    private final Document document;
    
    /**
     * Must supply the output stream.
     * 
     * @param output
     */
    public PdfOut(OutputStream output) {
        document = new Document(new PdfDocument(new PdfWriter(output)));
    }

    @Override
    public void accept(String t) {
        String lines[] = t.split("\\r?\\n");
        Stream.of(lines).forEach(p -> {
            document.add(new Paragraph(p));
        });
        document.close();
    }
    
    @Override
    public String toString() {
        return String.format("Document: %s, PDF: %s",
            "" + document, 
            "" + document.getPdfDocument());
    }

}
