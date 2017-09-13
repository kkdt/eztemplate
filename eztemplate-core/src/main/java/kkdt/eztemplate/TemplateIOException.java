/** 
 * Copyright (C) 2017 thinh ho
 * This file is part of 'eztemplate' which is released under the MIT license.
 * See LICENSE at the project root directory.
 */
package kkdt.eztemplate;

class TemplateIOException extends RuntimeException {
    private static final long serialVersionUID = 1293785708877537882L;
    
    public TemplateIOException(String message) {
        super(message);
    }
    
    public TemplateIOException(Throwable t) {
        super(t);
    }
    
    public TemplateIOException(String message, Throwable t) {
        super(message, t);
    }
}
