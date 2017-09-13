/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * The application context publish this event to notify {@code UIApplicationConfiguration}
 * to start the UI.
 * 
 * @author thinh ho
 *
 */
public class InitializeUI extends ApplicationContextEvent {
    private static final long serialVersionUID = -9040556454906431698L;
    
    public InitializeUI(ApplicationContext source) {
        super(source);
    }
}
