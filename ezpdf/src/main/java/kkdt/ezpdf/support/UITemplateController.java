/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.springframework.core.env.Environment;

import kkdt.ezpdf.support.table.TemplateEntry;
import kkdt.ezpdf.support.table.TemplateTableController;

/**
 * <p>
 * The main UI controller that handles the following actions
 * <ol>
 * <li>Exit</li>
 * <li>About</li>
 * <li>templateInfo</li>
 * <li>dictionaryInfo</li>
 * <li>saveInfo</li>
 * <li>template</li>
 * <li>dictionary</li>
 * <li>generate</li>
 * </ol>
 * </p>
 * 
 * @author thinh ho
 *
 */
public class UITemplateController implements ActionListener {
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
    
    private final Environment environment;
    private final UIFrame window;
    private final TemplateTableController tableController;
    private final PdfGenerator pdfGenerator;
    private JFileChooser fileChooser;
    private File template = null;
    private File dictionary = null;
    
    public UITemplateController(UIFrame window, Environment environment, File workspace, TemplateTableController tableController) {
        this.window = window;
        this.environment = environment;
        this.pdfGenerator = new PdfGenerator(workspace);
        this.fileChooser = new JFileChooser(workspace);
        this.tableController = tableController;
    }
    
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
        return pdfGenerator.generate(template, dictionary, filename);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
        case "Exit":
            System.exit(0);
            break;
        case "About":
            handleAbout();
            break;
        case "templateInfo":
            {
                String text = environment.getProperty("ezpdf.info.template");
                JOptionPane.showMessageDialog(window, text, "Template",JOptionPane.PLAIN_MESSAGE);
            }
            break;
        case "dictionaryInfo":
            {
                String text = environment.getProperty("ezpdf.info.dictionary");
                JOptionPane.showMessageDialog(window, text, "Dictionary",JOptionPane.PLAIN_MESSAGE);
            }
            break;
        case "saveInfo":
            {
                String text = environment.getProperty("ezpdf.info.filename");
                JOptionPane.showMessageDialog(window, text, "Filename",JOptionPane.PLAIN_MESSAGE);
            }
            break;
        case "template":
            this.template = selectFile(fileChooser);
            window.setTemplate(template != null ? template.getAbsolutePath() : "");
            break;
        case "dictionary":
            this.dictionary = selectFile(fileChooser);
            window.setDictionary(dictionary != null ? dictionary.getAbsolutePath() : "");
            break;
        case "generate":
            String output = window.getFilename();
            if("".equals(output)) {
                JOptionPane.showMessageDialog(null, "Please input an output file name", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    // generate
                    File pdf = this.handleSave(output);
                    
                    // add to results table
                    TemplateEntry entry = new TemplateEntry();
                    entry.setDictionary(dictionary);
                    entry.setTemplate(template);
                    entry.setOutput(pdf);
                    tableController.addEntry(entry);
                    
                    // display using desktop default app
                    Desktop.getDesktop().open(pdf);
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Cannot generate PDF: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            break;
        }
    }
    
    private void handleAbout() {
        Package p = UIApplicationConfiguration.class.getPackage();
        String version = p.getImplementationVersion();
        String spec = p.getSpecificationVersion();
        String vendor = p.getImplementationVendor();
        StringBuilder buffer = new StringBuilder(p.getImplementationTitle() != null ? p.getImplementationTitle() : "EzPDF");
        buffer.append("\nVersion: " + version);
        buffer.append("\nSpecification: " + p.getSpecificationTitle() + " " + spec);
        buffer.append("\nAuthor: " + vendor);
        JOptionPane.showMessageDialog(null, buffer.toString(),"About",JOptionPane.INFORMATION_MESSAGE, UIApplicationConfiguration.getIcon("img/ezpdf.png"));
    }
}
