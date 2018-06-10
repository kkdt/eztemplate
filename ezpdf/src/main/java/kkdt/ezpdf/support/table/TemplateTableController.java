/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support.table;

import javax.swing.JTable;

import kkdt.generictable.GenericTableController;
import kkdt.generictable.GenericTableModel;

/**
 * A table controller for generated templates.
 * 
 * @author thinh ho
 *
 */
public class TemplateTableController extends GenericTableController<TemplateEntry> {
    private final JTable table;
    
    public TemplateTableController(JTable table) {
        super(table, new GenericTableModel<TemplateEntry>() {
            private static final long serialVersionUID = 2531728898770538587L;
        });
        this.table = table;
    }
    
    public JTable getTable() {
        return table;
    }

}
