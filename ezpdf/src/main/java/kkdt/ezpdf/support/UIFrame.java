/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Main application window.
 * 
 * @author thinh ho
 *
 */
public class UIFrame extends JFrame {
    private static final long serialVersionUID = -5403259055929118050L;
    
    private JMenuItem exit;
    private JMenuItem about;
    private UISelectionPanel contents;
    
    /**
     * Expected <code>BorderLayout</code> window.
     * 
     * @param title
     * @param icon
     */
    public UIFrame(String title, ImageIcon icon) {
        super(title);
        setJMenuBar(initMenu());
        setIconImage(icon.getImage());
        setLayout(new BorderLayout(10,10));
    }
    
    /**
     * Attach a listener to the 'About' menu item.
     * 
     * @param about
     * @return
     */
    public UIFrame aboutListener(ActionListener about) {
        this.about.addActionListener(about);
        return this;
    }
    
    /**
     * Attach a listener to the 'Exit' menu item.
     * 
     * @param exit
     * @return
     */
    public UIFrame exitListener(ActionListener exit) {
        this.exit.addActionListener(exit);
        return this;
    }
    
    /**
     * The main contents will be a <code>UISelectionPanel</code>.
     * 
     * @param contents
     * @param panel
     */
    public void setContents(UISelectionPanel contents, TemplateResultsPanel panel) {
        getContentPane().add(contents, BorderLayout.NORTH);
        getContentPane().add(panel, BorderLayout.CENTER);
        this.contents = contents;
    }
    
    /**
     * Exposes a footer area.
     * 
     * @param footer
     */
    public void setFooter(Component footer) {
        getContentPane().add(footer, BorderLayout.SOUTH);
    }
    
    /**
     * Delegates to the contents for getting the filename.
     * 
     * @return
     */
    public String getFilename() {
        return this.contents.getFilename();
    }
    
    /**
     * Delegates to the contents for setting the template.
     * 
     * @param template
     */
    public void setTemplate(String template) {
        this.contents.setTemplate(template);
    }
    
    /**
     * Delegates to the contents for setting the dictionary.
     * 
     * @param dictionary
     */
    public void setDictionary(String dictionary) {
        this.contents.setDictionary(dictionary);
    }
    
    /**
     * Delegates to the contents for setting the filename.
     * 
     * @param filename
     */
    public void setFilename(String filename) {
        this.contents.setFilename(filename);
    }
    
    private JMenuBar initMenu() {
        exit = new JMenuItem("Exit");
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setToolTipText("Exit application");
        
        about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        about.setToolTipText("About");
        
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);
        file.add(exit);
        
        JMenu help = new JMenu("Help");
        help.setMnemonic(KeyEvent.VK_H);
        help.add(about);
        
        JMenuBar menubar = new JMenuBar();
        menubar.add(file);
        menubar.add(help);
        
        return menubar;
    }
}
