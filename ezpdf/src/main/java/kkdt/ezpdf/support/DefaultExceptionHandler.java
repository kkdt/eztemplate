/** 
 * Copyright (C) 2018 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import java.awt.Window;
import java.lang.Thread.UncaughtExceptionHandler;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class DefaultExceptionHandler implements UncaughtExceptionHandler {
    private static final Logger logger = Logger.getLogger(DefaultExceptionHandler.class);

    private final Window parent;

    /**
     * Must instantiate with a parent window because a popup will be presented
     * in front of the parent for uncaught exceptions.
     * 
     * @param parent
     */
    public DefaultExceptionHandler(Window parent) {
        this.parent = parent;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        logger.error("Exception Thread: " + t.getId() + ", " + t.getName(), e);
        JOptionPane.showMessageDialog(parent, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

}
