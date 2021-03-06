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
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.AreaBreakType;

import kkdt.eztemplate.TemplateWriter.TemplateOutput;

/**
 * PDF output implementation using the iText library.
 * 
 * @author thinh ho
 *
 */
public class PdfOut implements TemplateOutput {
    public static final String DefaultNewPage = "<%newpage%>";
    
    protected final Document document;
    protected final PdfDocument pdf;
    protected final String newpage;
    
    /**
     * Must supply the output stream.
     * 
     * @param output
     */
    public PdfOut(OutputStream output) {
        this(output, DefaultNewPage);
    }
    
    public PdfOut(OutputStream output, String newpage) {
        this.pdf = new PdfDocument(new PdfWriter(output));
        this.document = new Document(pdf);
        this.newpage = newpage;
    }

    @Override
    public void accept(String t) {
        String lines[] = tokenize(t);
        Stream.of(lines).forEach(p -> {
            if(newpage.equals(p)) {
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            } else {
                document.add(new Paragraph(p));
            }
        });
        
        document.close();
    }
    
    @Override
    public String toString() {
        return String.format("Document: %s, PDF: %s",
            "" + document, 
            "" + document.getPdfDocument());
    }
    
    /**
     * Tokenize the specified buffer into line(s) for text.
     * 
     * @param buffer
     * @return
     */
    protected String[] tokenize(String buffer) {
        return buffer.split("\\r?\\n");
    }

}
