/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.springframework.core.env.Environment;

/**
 * The main content panel.
 * 
 * @author thinh ho
 *
 */
public class UISelectionPanel extends JPanel {
    private static final long serialVersionUID = 5527561374187216268L;

    private final Environment environment;
    private JTextArea statusArea;
    private JButton templateBtn, templateInfo;
    private JButton dictionaryBtn, dictionaryInfo;
    private JButton saveBtn, saveInfo;
    private JTextField filename;
    private JTextField template;
    private JTextField dictionary;
    private JFileChooser fileChooser; 
    private UIStatusLogger uiLogger;
    private Window parent;
    
    public UISelectionPanel(Window parent, Environment environment) {
        this.parent = parent;
        this.environment = environment;
        initComponents();
        layoutComponents();
        
        // attach the controller
        UITemplateController uiTemplateController = 
            UITemplateController.withActionListener(parent, fileChooser, template, dictionary, filename, uiLogger);
        templateBtn.addActionListener(uiTemplateController);
        dictionaryBtn.addActionListener(uiTemplateController);
        saveBtn.addActionListener(uiTemplateController);
    }
    
    private void initComponents() {
        templateBtn = new JButton(UIApplicationConfiguration.getIcon("img/open.png"));
        templateBtn.setActionCommand("template");
        templateBtn.setToolTipText("Choose template file");
        
        templateInfo = new JButton(UIApplicationConfiguration.getIcon("img/info.png"));
        templateInfo.setToolTipText("A template file contains placeholder(s)");
        templateInfo.addActionListener(e -> {
            String text = environment.getProperty("ezpdf.info.template");
            JOptionPane.showMessageDialog(parent, text, "Template",JOptionPane.PLAIN_MESSAGE);
        });
        
        dictionaryBtn = new JButton(UIApplicationConfiguration.getIcon("img/open.png"));
        dictionaryBtn.setActionCommand("dictionary");
        dictionaryBtn.setToolTipText("Choose dictionary file");
        
        dictionaryInfo = new JButton(UIApplicationConfiguration.getIcon("img/info.png"));
        dictionaryInfo.setToolTipText("A dictionary file contains key-value pair(s)");
        dictionaryInfo.addActionListener(e -> {
            String text = environment.getProperty("ezpdf.info.dictionary");
            JOptionPane.showMessageDialog(parent, text, "Dictionary",JOptionPane.PLAIN_MESSAGE);
        });
        
        saveBtn = new JButton(UIApplicationConfiguration.getIcon("img/save.png"));
        saveBtn.setActionCommand("generate");
        saveBtn.setToolTipText("Generate file");
        
        saveInfo = new JButton(UIApplicationConfiguration.getIcon("img/info.png"));
        saveInfo.setToolTipText("Generate pdf");
        saveInfo.addActionListener(e -> {
            String text = environment.getProperty("ezpdf.info.filename");
            JOptionPane.showMessageDialog(parent, text, "Filename",JOptionPane.PLAIN_MESSAGE);
        });
        
        statusArea = new JTextArea(10,30);
        statusArea.setMargin(new Insets(5,5,5,5));
        statusArea.setEditable(false);
        statusArea.setWrapStyleWord(true);
        
        filename = new JTextField(20);
        template = new JTextField(20);
        template.setEditable(false);
        dictionary = new JTextField(20);
        dictionary.setEditable(false);
        
        uiLogger = message -> {
            final DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:SS");
            statusArea.append("[" + df.format(new Date()) + "] " + message + "\n");
        };
        
        fileChooser = new JFileChooser();
    }
    
    private void layoutComponents() {
        JPanel panel0 = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // template
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 0;
            c.gridy = 0;
            c.gridheight = 1;
            panel0.add(new JLabel("Template "), c);
        }
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 1;
            c.gridy = 0;
            c.gridheight = 1;
            panel0.add(template, c);
        }
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 2;
            c.gridy = 0;
            panel0.add(templateBtn, c);
        }
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 3;
            c.gridy = 0;
            panel0.add(templateInfo, c);
        }
        // dictionary
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 0;
            c.gridy = 1;
            c.gridheight = 1;
            panel0.add(new JLabel("Dictionary "), c);
        }
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 1;
            c.gridy = 1;
            c.gridheight = 1;
            panel0.add(dictionary, c);
        }
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 2;
            c.gridy = 1;
            c.gridheight = 1;
            panel0.add(dictionaryBtn, c);
        }
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 3;
            c.gridy = 1;
            panel0.add(dictionaryInfo, c);
        }
        // filename
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 0;
            c.gridy = 2;
            c.gridheight = 1;
            panel0.add(new JLabel("Filename "), c);
        }
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 1;
            c.gridy = 2;
            c.gridheight = 1;
            panel0.add(filename, c);
        }
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 2;
            c.gridy = 2;
            c.gridheight = 1;
            panel0.add(saveBtn, c);
        }
        {
            c.anchor = GridBagConstraints.LINE_START;
            c.gridx = 3;
            c.gridy = 2;
            c.gridheight = 1;
            panel0.add(saveInfo, c);
        }
        
        JPanel panel3 = new JPanel(new BorderLayout(10,10));
        panel3.add(panel0, BorderLayout.CENTER);
        
        setLayout(new BorderLayout());
        add(panel3, BorderLayout.PAGE_START);
        add(new JScrollPane(statusArea), BorderLayout.CENTER);
    }
}
