/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate.pdf;

import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;

import kkdt.eztemplate.EzPlaceholder;
import kkdt.eztemplate.TemplateTranslator;

/**
 * PDF generation that will format placeholder texts.
 * 
 * @author thinh ho
 *
 */
public class FormattedPlaceholderPdfOut extends PdfOut {
    private TemplateTranslator translator;
    private EzPlaceholder placeholder = EzPlaceholder.property;

    public FormattedPlaceholderPdfOut(OutputStream output) {
        super(output);
    }
    
    public FormattedPlaceholderPdfOut(OutputStream output, String newpage) {
        super(output, newpage);
    }
    
    /**
     * The translator for the configured placeholder.
     * 
     * @param translator
     */
    public void setTranslator(TemplateTranslator translator) {
        this.translator = translator;
    }

    /**
     * The placeholder type.
     * 
     * @param placeholder
     */
    public void setPlaceholder(EzPlaceholder placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    public void accept(String t) {
        String lines[] = tokenize(t);
        Stream.of(lines).forEach(p -> {
            if(newpage.equals(p)) {
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            } else {
                document.add(buildParagraph(p));
            }
        });
        document.close();
    }
    
    private Paragraph buildParagraph(String line) {
        String _regex = "[\\t\\s]+";
        String[] words = line.split(_regex);
        
        Paragraph paragraph = new Paragraph();
        Stream.of(words).forEach(w -> {
            Text text;
            Matcher matcher = placeholder.matcher(w);
            if(matcher.find()) {
                text = createSubstitution(translator.translate(matcher.group()));
            } else {
                text = new Text(w);
            }
            paragraph.add(text);
            paragraph.add(" ");
        });
        return paragraph;
    }
    
    private Text createSubstitution(String text) {
        return new Text(text).setBold().setBackgroundColor(Color.LIGHT_GRAY);
    }
}
