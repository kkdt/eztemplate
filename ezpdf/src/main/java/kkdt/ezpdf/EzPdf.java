/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.ezpdf;

import org.apache.log4j.Logger;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"kkdt.ezpdf.support"})
public class EzPdf {
    private static final Logger logger = Logger.getLogger(EzPdf.class);
    
    public static void main(String[] args) {
        logger.debug("======================================================");
        logger.debug("Starting application " + EzPdf.class.getSimpleName());
        logger.debug("======================================================");
        
        SpringApplicationBuilder app = new SpringApplicationBuilder(EzPdf.class)
           .bannerMode(Mode.OFF).logStartupInfo(false);
        app.headless(false).web(false)
           .run(args);
    }

}
