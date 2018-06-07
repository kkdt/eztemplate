/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Configuration for starting up the thick client.
 * 
 * @author thinh ho
 *
 */
@Configuration
public class UIApplicationConfiguration implements ApplicationListener<InitializeUI> {
    private static final Logger logger = Logger.getLogger(UIApplicationConfiguration.class);
    
    @Autowired(required=true)
    private Environment environment;
    
    @Override
    public void onApplicationEvent(InitializeUI event) {
        logger.debug("Staring UI...");
        
        setLookAndFeelDefaults();
        
        UIBuilder builder = new UIBuilder(environment);
        JFrame window = builder.build();
        window.pack();
        SwingUtilities.updateComponentTreeUI(window);
        EventQueue.invokeLater(() -> {
            window.setResizable(false);
            window.setVisible(true);
        });
    }

    static ImageIcon getIcon(String name) {
        ImageIcon icon = null;
        try {
            Image image = ImageIO.read(UIApplicationConfiguration.class.getClassLoader().getResourceAsStream(name));
            icon = new ImageIcon(image);
            return icon;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static void setLookAndFeelDefaults() {
        JFrame.setDefaultLookAndFeelDecorated(false);
        
        Font defaultFont = new Font("Arial", Font.PLAIN, 9);
        UIManager.put("defaultFont", defaultFont);
        UIManager.put("Button.font", defaultFont);
        UIManager.put("ToggleButton.font", defaultFont);
        UIManager.put("RadioButton.font", defaultFont);
        UIManager.put("CheckBox.font", defaultFont);
        UIManager.put("ColorChooser.font", defaultFont);
        UIManager.put("ComboBox.font", defaultFont);
        UIManager.put("Label.font", defaultFont);
        UIManager.put("List.font", defaultFont);
        UIManager.put("MenuBar.font", defaultFont);
        UIManager.put("MenuItem.font", defaultFont);
        UIManager.put("RadioButtonMenuItem.font", defaultFont);
        UIManager.put("CheckBoxMenuItem.font", defaultFont);
        UIManager.put("Menu.font", defaultFont);
        UIManager.put("PopupMenu.font", defaultFont);
        UIManager.put("OptionPane.font", defaultFont);
        UIManager.put("Panel.font", defaultFont);
        UIManager.put("ProgressBar.font", defaultFont);
        UIManager.put("ScrollPane.font", defaultFont);
        UIManager.put("Viewport.font", defaultFont);
        UIManager.put("TabbedPane.font", defaultFont);
        UIManager.put("Table.font", defaultFont);
        UIManager.put("TableHeader.font", defaultFont);
        UIManager.put("TextField.font", defaultFont);
        UIManager.put("PasswordField.font", defaultFont);
        UIManager.put("TextArea.font", defaultFont);
        UIManager.put("TextPane.font", defaultFont);
        UIManager.put("EditorPane.font", defaultFont);
        UIManager.put("TitledBorder.font", defaultFont);
        UIManager.put("ToolBar.font", defaultFont);
        UIManager.put("ToolTip.font", defaultFont);
        UIManager.put("Tree.font", defaultFont);
        UIManager.put("Table.alternateRowColor", new Color(240, 240, 240));
        
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            logger.error("Cannot start application", e);
            System.exit(-1);
        }
    }
}
