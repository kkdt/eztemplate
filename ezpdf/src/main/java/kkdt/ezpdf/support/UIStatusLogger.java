/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

/**
 * Log status to a UI component.
 * 
 * @author thinh ho
 *
 */
@FunctionalInterface
public interface UIStatusLogger {
    /**
     * Log a mesage.
     * 
     * @param message
     */
    void log(String message);
}
