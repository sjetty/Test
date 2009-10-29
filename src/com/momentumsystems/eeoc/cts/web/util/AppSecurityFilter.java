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

package com.momentumsystems.eeoc.cts.web.util;

import com.momentumsystems.exceptions.ExceptionConstants;
import com.momentumsystems.exceptions.NullObjectException;
import com.momentumsystems.eeoc.cts.util.ApplicationSecurityUser;
import com.momentumsystems.livelink.util.LivelinkUtils;
import com.momentumsystems.logon.LogonFilter;
import com.momentumsystems.security.AppSecurity;
import com.momentumsystems.security.BaseAppSecurity;
import com.momentumsystems.security.SecurityUtils;
import com.momentumsystems.security.UserSession;
import com.momentumsystems.util.StringUtilities;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AppSecurityFilter implements javax.servlet.Filter {
    private static Log log = LogFactory.getLog( AppSecurityFilter.class );

    private FilterConfig filterConfig;


    public FilterConfig getFilterConfig() {
        return filterConfig;
    }

    public void setFilterConfig( FilterConfig filterConfig ) {
        this.filterConfig = filterConfig;
    }

    /**
     * Perform the actual filter operation. Look for the cookie. Call the helper class to get the userInformation. Store
     * the result in session. If the cookie is not available, then redirect the user to a logon page.
     *
     * @param request  the standard ServletRequest object
     * @param response the standard ServletResponse object
     * @param chain    the standard FilterChain object
     * @throws java.io.IOException
     * @throws ServletException    if any problem occurs with the decoding or retrieval of a logon url, then an exception
     *                             with root cause will be raised.
     */
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException,
        ServletException {
        // Find the cookie
        if ( !( request instanceof HttpServletRequest ) ) {
            throw new ServletException( "This filter only works with HttpServletRequest objects" );
        }
        HttpServletRequest httpRequest = ( HttpServletRequest ) request;
        HttpSession session = httpRequest.getSession();

        //todo: get an AppSecurity object from the userInfo if necessary


        Object o = session.getAttribute( LogonFilter.USER_INFORMATION_SESSION_VAR_NAME );
        if ( ( o == null ) || !( o instanceof com.momentumsystems.livelink.User ) ) {
            throw new NullObjectException( ExceptionConstants.ERROR_NULL_USER_OBJECT );
        }

        com.momentumsystems.livelink.User liveLinkUser = null;
        liveLinkUser = ( com.momentumsystems.livelink.User ) o;

        //get the security object from the session
        AppSecurity security = ( AppSecurity ) session.getAttribute( "AppSecurity" );

        //if it is not valid, otherwise recreate it
        if ( security == null || security.getUser() == null || security.getUser().getUserId() == null
            || !security.getUser().getUserId().equalsIgnoreCase( liveLinkUser.getId() + "" ) ) {

            security = new BaseAppSecurity();

            security.setUser( new ApplicationSecurityUser() );

            ApplicationSecurityUser user = ( ApplicationSecurityUser ) security.getUser();
            user.setUsername( liveLinkUser.getName() );
            user.setUserId( StringUtilities.getStringFromInteger( liveLinkUser.getId() ) );
            user.setFirstName( liveLinkUser.getFirstName() );
            user.setLastName( liveLinkUser.getLastName() );
            user.setEmail( liveLinkUser.getEMail() );

            user.setDefaultGroup( LivelinkUtils.getGroupName( liveLinkUser.getDefaultGroup() ) );
            user.setGroups( LivelinkUtils.getGroupNameList( liveLinkUser.getGroups() ) );
            user.setGroupNames( LivelinkUtils.getGroupNameList( liveLinkUser.getGroups() ) );
            user.setLivelinkGroups( liveLinkUser.getGroups() );
            user.setDefaultGroupId( liveLinkUser.getDefaultGroupID() );
            user.setDefaultGroup( liveLinkUser.getDefaultGroup().getName() );

            UserSession userSession = security.getSession();

            String pageName = SecurityUtils.getPageName( httpRequest );
            userSession.setPageName( pageName );
            userSession.setSessionId( session.getId() );

            //put the security object in the session
            session.setAttribute( "AppSecurity", security );
            /*String livelinkUrl = ConfigurationUtils.getLivelinkBaseUrl();
            session.setAttribute( "LivelinkBaseUrl", livelinkUrl);
            if ( !StringUtils.isEmpty(livelinkUrl))
            {
                int index = livelinkUrl.indexOf( "/livelink/livelink.exe" );
                session.setAttribute( "LivelinkServerUrl", livelinkUrl.substring( 0, index));
            }*/
        }


        chain.doFilter( request, response );
    }

    public void init( FilterConfig newFilterConfig ) throws ServletException {
        this.filterConfig = newFilterConfig;
    }

    /**
     * Called by the Servlet container when the filter is taken out of service. This filter requires no processing here.
     */
    public void destroy() {
    }


}
