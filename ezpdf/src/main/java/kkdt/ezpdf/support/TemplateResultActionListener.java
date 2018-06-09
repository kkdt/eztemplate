/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.awt.Desktop;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;

import javax.swing.JOptionPane;

import kkdt.ezpdf.support.table.TemplateEntry;
import kkdt.ezpdf.support.table.TemplateTableController;

/**
 * Handle actions that can be performed on the template results table.
 * 
 * @author thinh ho
 *
 */
public class TemplateResultActionListener implements ActionListener {
    private final Window reference;
    private final TemplateTableController tableController;
    
    public TemplateResultActionListener(Window reference, TemplateTableController tableController) {
        this.reference = reference;
        this.tableController = tableController;
    }

    @Override
    public void actionPerformed(ActionEvent e) throws RuntimeException {
        if(tableController.getSelectedEntry() == null) {
            JOptionPane.showMessageDialog(reference, "Please select an entry from the table", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        TemplateEntry selected = tableController.getSelectedEntry();
        
        try {
            switch(e.getActionCommand()) {
            case "Delete":
                Files.delete(selected.getOutput().toPath());
                tableController.removeSelectedEntry();
                break;
            case "View":
                Desktop.getDesktop().open(selected.getOutput());
                break;
            }
        } catch (Exception er) {
            throw new IllegalStateException(er);
        }
    }

}
