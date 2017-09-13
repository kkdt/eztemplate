/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.function.BiConsumer;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.io.IOUtils;

import kkdt.eztemplate.TemplateFactory;
import kkdt.eztemplate.pdf.PdfOut;
import kkdt.eztemplate.pdf.TemplateSubstitution;

/**
 * <p>
 * The main UI controller that handles 3 actions: template, dictionary, and generate.
 * </p>
 * 
 * @author thinh ho
 *
 */
public abstract class UITemplateController implements ActionListener {
    /**
     * Listen for template|dictionary|generate actions.
     * 
     * @param parent
     * @param fileChooser
     * @param filename
     * @param uiLogger
     * @return
     */
    public static UITemplateController withActionListener(final Window parent, final JFileChooser fileChooser, 
        JTextField templateFld, JTextField dictionaryFld, JTextField filename, UIStatusLogger uiLogger) 
    {
        BiConsumer<JTextField,String> setText = (f,t) -> {
            EventQueue.invokeLater(() -> f.setText(t == null ? "" : t));
        };
        
        UITemplateController local = new UITemplateController() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch(e.getActionCommand()) {
                case "template":
                    this.template = selectFile(fileChooser);
                    if(this.template != null) {
                        setText.accept(templateFld, this.template.getAbsolutePath());
                        uiLogger.log("Template set: " + this.template.getAbsolutePath());
                    } else {
                        setText.accept(templateFld, "");
                    }
                    break;
                case "dictionary":
                    this.dictionary = selectFile(fileChooser);
                    if(this.template != null) {
                        setText.accept(dictionaryFld, this.dictionary.getAbsolutePath());
                        uiLogger.log("Dictionary set: " + this.dictionary.getAbsolutePath());
                    } else {
                        setText.accept(dictionaryFld, "");
                    }
                    break;
                case "generate":
                    String output = filename.getText().trim();
                    if("".equals(output)) {
                        JOptionPane.showMessageDialog(null, "Please input an output file name", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            File pdf = this.handleSave(output);
                            uiLogger.log("File saved: " + pdf.getAbsolutePath());
                        } catch (IOException e1) {
                            JOptionPane.showMessageDialog(null, "Cannot generate PDF: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                }
            }
        };
        return local;
    }
    
    /**
     * Helper to display the file chooser.
     * 
     * @param fileChooser
     * @return
     */
    private static File selectFile(JFileChooser fileChooser) {
        File chosenfile = null;
        switch(fileChooser.showOpenDialog(null)) {
        case JFileChooser.APPROVE_OPTION:
            chosenfile = fileChooser.getSelectedFile();
            break;
        default:
            break;
        }
        return chosenfile;
    }
    
    protected File template = null;
    protected File dictionary = null;
    
    /**
     * Generate the pdf file using the configured template and dictionary.
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    protected File handleSave(String filename) throws IOException {
        if(template == null || dictionary == null) {
            JOptionPane.showMessageDialog(null, "Missing template/dictionary files", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        final Properties properties = new Properties();
        try (final InputStream stream = new FileInputStream(dictionary)) {
            properties.load(stream);
        }
        
        String input = null;
        try (final InputStream stream = new FileInputStream(template)) {
            Charset charset = Charset.defaultCharset();
            input = IOUtils.toString(stream, charset);
        }
        
        File file = new File(filename);
        try(FileOutputStream out = new FileOutputStream(file)) {
            TemplateFactory.buildTemplates(input, new TemplateSubstitution(properties), new PdfOut(out));
        }
        return file;
    }
}
