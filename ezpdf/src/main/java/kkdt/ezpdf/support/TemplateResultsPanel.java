/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

/**
 * Table panel container the generate template results.
 * 
 * @author thinh ho
 *
 */
public class TemplateResultsPanel extends JPanel {
    private static final long serialVersionUID = 3410067187732462130L;

    private final JTable table;
    private final JButton viewBtn;
    private final JButton deleteBtn;
    private final JButton regenerateBtn;
    
    public TemplateResultsPanel(JTable table) {
        this.table = table;
        this.viewBtn = new JButton("View");
        this.deleteBtn = new JButton("Delete");
        this.regenerateBtn = new JButton("Regenerate");
    }
    
    public TemplateResultsPanel viewListener(ActionListener l) {
        this.viewBtn.addActionListener(l);
        return this;
    }
    
    public TemplateResultsPanel deleteListener(ActionListener l) {
        this.deleteBtn.addActionListener(l);
        return this;
    }
    
    public TemplateResultsPanel regenerateListener(ActionListener l) {
        this.regenerateBtn.addActionListener(l);
        return this;
    }
    
    public TemplateResultsPanel layoutComponents() {
        JScrollPane scrollpane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        JPanel panel0 = new JPanel(new BorderLayout(10, 10));
        panel0.add(scrollpane, BorderLayout.CENTER);
        panel0.setPreferredSize(new Dimension(300, 200));
        
        JPanel panel1 = new JPanel();
        panel1.add(viewBtn);
        panel1.add(deleteBtn);
        panel1.add(regenerateBtn);
        
        setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        setLayout(new BorderLayout());
        setSize(400, 400);
        add(panel1, BorderLayout.NORTH);
        add(panel0, BorderLayout.CENTER);
        
        return this;
    }
    
    public void enableActions(boolean enable) {
        this.viewBtn.setEnabled(enable);
        this.deleteBtn.setEnabled(enable);
        this.regenerateBtn.setEnabled(enable);
    }
}
