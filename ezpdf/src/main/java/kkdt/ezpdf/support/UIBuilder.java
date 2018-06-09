/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import org.springframework.core.env.Environment;

import kkdt.ezpdf.support.table.TemplateTableController;

/**
 * Builds the UI.
 * 
 * @author thinh ho
 *
 */
public class UIBuilder {
    
    private final Environment environment;
    private final File workspace;
    
    public UIBuilder(Environment environment) {
        this.workspace = new File(environment.getProperty("ezpdf.default.workspace"));
        this.environment = environment;
    }
    
    public UIFrame build() {
        UIFrame window = new UIFrame(environment.getProperty("ezpdf.title"), 
            UIApplicationConfiguration.getIcon("img/ezpdf.png"));
        
        JTable table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TemplateTableController tableController = new TemplateTableController(table);
        TemplateResultActionListener templateActionListener = new TemplateResultActionListener(window, tableController);
        
        UITemplateController uiController = new UITemplateController(window, environment, workspace, tableController);
        
        window.exitListener(uiController).aboutListener(uiController);
        
        UISelectionPanel contents = new UISelectionPanel()
            .templateListener(uiController)
            .dictionaryListener(uiController)
            .saveListener(uiController)
            .templateInfoListener(uiController)
            .dictionaryInfoListener(uiController)
            .saveInfoListener(uiController);
        
        TemplateResultsPanel results = new TemplateResultsPanel(table)
            .deleteListener(templateActionListener)
            .viewListener(templateActionListener)
            .layoutComponents();
        results.enableActions(false);
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setIconImage(UIApplicationConfiguration.getIcon("img/ezpdf.png").getImage());
        window.setLayout(new BorderLayout(10,10));
        window.setContents(contents, results);
        
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.X_AXIS));
        footer.add(Box.createHorizontalGlue());
        footer.add(new JLabel("All files will be saved to " + workspace.getAbsolutePath()));
        footer.add(Box.createHorizontalGlue());
        
        window.setFooter(footer);
        
        // initialize listeners
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                results.enableActions(tableController.getSelectedEntry() != null);
            }
        });
        
//        for(int i = 0; i < 10; i++) {
//            TemplateEntry e = new TemplateEntry();
//            e.setDictionary(new File("dictionary" + i));
//            e.setOutput(new File("output" + i));
//            e.setTemplate(new File("Template" + i));
//            tableController.addEntry(e);
//        }
        
        return window;
    }
}
