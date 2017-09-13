/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for processing the command line arguments on startup.
 * 
 * @author thinh ho
 *
 */
@Configuration
public class ApplicationRuntimeConfiguration implements ApplicationRunner {
    
    @Autowired(required=true)
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        handleUI();
    }
    
    /**
     * Notify the appropriate listener to start the UI.
     */
    private void handleUI() {
        applicationContext.publishEvent(new InitializeUI(applicationContext));
    }

}
