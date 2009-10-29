/*
 * Copyright 2008 Momentum Systems, Inc.
 * All Rights Reserved
 * This software is the confidential and proprietary information
 * of Momentum Systems, Inc. ("Confidential Information").
 *
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with the author.
 *
 *
 */

package com.momentumsystems.eeoc.cts.web;
        
import com.momentumsystems.util.system.SystemInfo;
import com.momentumsystems.eeoc.cts.util.system.CtsSystemInfo;
import com.sun.rave.web.ui.appbase.AbstractApplicationBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.FacesException;
import java.util.Date;
import java.util.TimeZone;
import java.util.Locale;

/**
 *
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $   $Date: 2009-10-29 15:54:36 $
 */

public class ApplicationBean extends AbstractApplicationBean {

    protected Log logger = LogFactory.getLog( getClass() );

    /**
     * injected
     */
    SystemInfo systemInfo = null;

    private String systemName;
    private String defaultTimeZone;


    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    /**
     * <p>Construct a new application data bean instance.</p>
     */
    public ApplicationBean() {
    }

    /**
     * <p>This method is called when this bean is initially added to
     * application scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * application scope.</p>
     * <p/>
     * <p>You may customize this method to initialize and cache application wide
     * data values (such as the lists of valid options for dropdown list
     * components), or to allocate resources that are required for the
     * lifetime of the application.</p>
     */
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here
         Date today = new Date();
        TimeZone defaultTimeZone = TimeZone.getDefault();
        boolean inDaylightTime = defaultTimeZone.inDaylightTime(today);
        String shortName = defaultTimeZone.getDisplayName(inDaylightTime, TimeZone.SHORT, Locale.US);
        setDefaultTimeZone(shortName);
        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch ( Exception e ) {
            log( "ApplicationBean1 Initialization Failure", e );
            throw e instanceof FacesException ? ( FacesException ) e : new FacesException( e );
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here

    }

    /**
     * <p>This method is called when this bean is removed from
     * application scope.  Typically, this occurs as a result of
     * the application being shut down by its owning container.</p>
     * <p/>
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the application.</p>
     */
    public void destroy() {
    }

    /**
     * <p>Return an appropriate character encoding based on the
     * <code>Locale</code> defined for the current JavaServer Faces
     * view.  If no more suitable encoding can be found, return
     * "UTF-8" as a general purpose default.</p>
     * <p/>
     * <p>The default implementation uses the implementation from
     * our superclass, <code>AbstractApplicationBean</code>.</p>
     */
    public String getLocaleCharacterEncoding() {
        return super.getLocaleCharacterEncoding();
    }

    public SystemInfo getSystemInfo() {
        return this.systemInfo;
    }

    public void setSystemInfo( SystemInfo systemInfo ) {
        this.systemInfo = systemInfo;
    }

    public CtsSystemInfo getCtsSystemInfo()
    {
        return (CtsSystemInfo) systemInfo;
    }

    public String getSystemName() {
        return this.systemName;
    }

    public void setSystemName( String systemName ) {
        this.systemName = systemName;
    }

    public String getDefaultTimeZone() {
        return defaultTimeZone;
    }

    public void setDefaultTimeZone( String defaultTimeZone ) {
        this.defaultTimeZone = defaultTimeZone;
    }
}
