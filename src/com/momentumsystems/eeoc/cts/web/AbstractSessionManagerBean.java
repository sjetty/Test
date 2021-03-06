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

import com.momentumsystems.eeoc.cts.util.ApplicationSecurityUser;
import com.momentumsystems.security.AppSecurity;
import com.momentumsystems.util.system.SystemInfo;
import com.sun.rave.web.ui.appbase.AbstractSessionBean;

import javax.faces.FacesException;
import java.util.logging.Logger;

/**
 *
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $   $Date: 2009-10-29 15:54:36 $
 */

public abstract class AbstractSessionManagerBean extends AbstractSessionBean implements com.momentumsystems.eeoc.cts.web.ManagerBean {
    protected transient Logger log = Logger.getLogger( getClass().getName() );

    protected SystemInfo systemInfo;

    /**
     * <p>Automatically managed component initialization.  <strong>WARNING:</strong>
     * This method is automatically generated, so any user-specified code inserted
     * here is subject to being replaced.</p>
     */
    private void _init() throws Exception {
    }
    // </editor-fold>

    /**
     * <p>Construct a new session data bean instance.</p>
     */
    public AbstractSessionManagerBean() {
    }

    /**
     * <p>This method is called when this bean is initially added to
     * session scope.  Typically, this occurs as a result of evaluating
     * a value binding or method binding expression, which utilizes the
     * managed bean facility to instantiate this bean and store it into
     * session scope.</p>
     * <p/>
     * <p>You may customize this method to initialize and cache data values
     * or resources that are required for the lifetime of a particular
     * user session.</p>
     */
    public void init() {
        // Perform initializations inherited from our superclass
        super.init();
        // Perform application initialization that must complete
        // *before* managed components are initialized
        // TODO - add your own initialiation code here

        // <editor-fold defaultstate="collapsed" desc="Managed Component Initialization">
        // Initialize automatically managed components
        // *Note* - this logic should NOT be modified
        try {
            _init();
        } catch ( Exception e ) {
            log( "SessionBean1 Initialization Failure", e );
            throw e instanceof FacesException ? ( FacesException ) e : new FacesException( e );
        }

        // </editor-fold>
        // Perform application initialization that must complete
        // *after* managed components are initialized
        // TODO - add your own initialization code here

    }

    /**
     * <p>This method is called when the session containing it is about to be
     * passivated.  Typically, this occurs in a distributed servlet container
     * when the session is about to be transferred to a different
     * container instance, after which the <code>activate()</code> method
     * will be called to indicate that the transfer is complete.</p>
     * <p/>
     * <p>You may customize this method to release references to session data
     * or resources that can not be serialized with the session itself.</p>
     */
    public void passivate() {
    }

    /**
     * <p>This method is called when the session containing it was
     * reactivated.</p>
     * <p/>
     * <p>You may customize this method to reacquire references to session
     * data or resources that could not be serialized with the
     * session itself.</p>
     */
    public void activate() {
    }

    /**
     * <p>This method is called when this bean is removed from
     * session scope.  Typically, this occurs as a result of
     * the session timing out or being terminated by the application.</p>
     * <p/>
     * <p>You may customize this method to clean up resources allocated
     * during the execution of the <code>init()</code> method, or
     * at any later time during the lifetime of the application.</p>
     */
    public void destroy() {
    }

    /**
     * <p>Return a reference to the scoped data bean.</p>
     */
    protected ApplicationBean getApplicationBean() {
        return ( ApplicationBean ) getBean( "ApplicationBean" );
    }

    public SystemInfo getSystemInfo() {
        if ( systemInfo == null ) {
            systemInfo = getApplicationBean().getSystemInfo();
        }
        return systemInfo;
    }

    public void setSystemInfo( SystemInfo systemInfo ) {
        this.systemInfo = systemInfo;
    }

    protected ApplicationSecurityUser getLoginUser() {
        AppSecurity security = ( AppSecurity ) getBean( "AppSecurity" );
        ApplicationSecurityUser loginUser = ( ApplicationSecurityUser ) security.getUser();

        return loginUser;
    }

    protected long getLoginUserId() {
        ApplicationSecurityUser loginUser = getLoginUser();
        long userId = Long.parseLong( loginUser.getUserId() );

        return userId;
    }

}
