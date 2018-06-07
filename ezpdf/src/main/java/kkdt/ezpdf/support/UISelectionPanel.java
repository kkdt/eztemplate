/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The main content panel.
 * 
 * @author thinh ho
 *
 */
public class UISelectionPanel extends JPanel {
    private static final long serialVersionUID = 5527561374187216268L;

    private JButton templateBtn, templateInfo;
    private JButton dictionaryBtn, dictionaryInfo;
    private JButton saveBtn, saveInfo;
    private JTextField filename;
    private JTextField template;
    private JTextField dictionary;
    
    public UISelectionPanel() {
        initComponents();
        layoutComponents();
    }
    
    public UISelectionPanel templateListener(ActionListener l) {
        templateBtn.addActionListener(l);
        return this;
    }
    
    public UISelectionPanel templateInfoListener(ActionListener l) {
        templateInfo.addActionListener(l);
        return this;
    }
    
    public UISelectionPanel dictionaryListener(ActionListener l) {
        dictionaryBtn.addActionListener(l);
        return this;
    }
    
    public UISelectionPanel dictionaryInfoListener(ActionListener l) {
        dictionaryInfo.addActionListener(l);
        return this;
    }
    
    public UISelectionPanel saveListener(ActionListener l) {
        saveBtn.addActionListener(l);
        return this;
    }
    
    public UISelectionPanel saveInfoListener(ActionListener l) {
        saveInfo.addActionListener(l);
        return this;
    }
    
    public void setTemplate(String template) {
        this.template.setText(template);
    }
    
    public void setDictionary(String dictionary) {
        this.dictionary.setText(dictionary);
    }
    
    public void setFilename(String filename) {
        this.filename.setText(filename);
    }
    
    public String getFilename() {
        return filename.getText().trim();
    }
    
    private void initComponents() {
        templateBtn = new JButton(UIApplicationConfiguration.getIcon("img/open.png"));
        templateBtn.setActionCommand("template");
        templateBtn.setToolTipText("Choose template file");
        
        templateInfo = new JButton(UIApplicationConfiguration.getIcon("img/info.png"));
        templateInfo.setActionCommand("templateInfo");
        templateInfo.setToolTipText("A template file contains placeholder(s)");
        
        dictionaryBtn = new JButton(UIApplicationConfiguration.getIcon("img/open.png"));
        dictionaryBtn.setActionCommand("dictionary");
        dictionaryBtn.setToolTipText("Choose dictionary file");
        
        dictionaryInfo = new JButton(UIApplicationConfiguration.getIcon("img/info.png"));
        dictionaryInfo.setActionCommand("dictionaryInfo");
        dictionaryInfo.setToolTipText("A dictionary file contains key-value pair(s)");
        
        saveBtn = new JButton(UIApplicationConfiguration.getIcon("img/save.png"));
        saveBtn.setActionCommand("generate");
        saveBtn.setToolTipText("Generate file");
        
        saveInfo = new JButton(UIApplicationConfiguration.getIcon("img/info.png"));
        saveInfo.setActionCommand("saveInfo");
        saveInfo.setToolTipText("Generate pdf");
        
        filename = new JTextField(40);
        template = new JTextField(40);
        template.setEditable(false);
        dictionary = new JTextField(40);
        dictionary.setEditable(false);
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
        add(Box.createHorizontalStrut(10), BorderLayout.WEST);
        add(panel3, BorderLayout.CENTER);
        add(Box.createHorizontalStrut(10), BorderLayout.EAST);
    }
}
