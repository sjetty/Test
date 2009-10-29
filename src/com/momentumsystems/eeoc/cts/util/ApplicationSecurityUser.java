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

package com.momentumsystems.eeoc.cts.util;

import com.momentumsystems.livelink.Group;
import com.momentumsystems.security.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $   $Date: 2009-10-29 15:54:36 $
 */

public class ApplicationSecurityUser extends User {
    List<Group> livelinkGroups = new ArrayList();
    List<String> groupNames;
    List<Integer> groupIds;

    public ApplicationSecurityUser() {
        super();
    }

    public List<Group> getLivelinkGroups() {
        return livelinkGroups;
    }

    public void setLivelinkGroups( List<Group> livelinkGroups ) {
        this.livelinkGroups = livelinkGroups;

        List<Integer> groupIds = new ArrayList<Integer>();

        for ( Group aGroup : this.livelinkGroups ) {
            groupIds.add( aGroup.getId() );
        }

        setGroupIds( groupIds );
    }


    public List<String> getGroupNames() {
        return groupNames;
    }

    public void setGroupNames( List<String> groupNames ) {
        this.groupNames = groupNames;
    }

    public List<Integer> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds( List<Integer> groupIds ) {
        this.groupIds = groupIds;
    }

    public boolean isInGroup( String groupName ) {
        if ( getGroupNames() != null ) {
            for ( String name : getGroupNames() ) {
                if ( name.equalsIgnoreCase( groupName ) ) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * added for the manager view in dashboard
     */
    public boolean isManager() {
        if ( getGroupNames() != null ) {
            for ( String name : getGroupNames() ) {
                if ( name.startsWith( "CM_R" ) && name.endsWith( "_Managers" ) ) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * added for the intakeUser view in dashboard
     */
    public boolean isIntakeUser() {
        if ( getGroupNames() != null ) {
            for ( String name : getGroupNames() ) {
                if ( name.startsWith( "CM_R" ) && name.endsWith( "_Intake" ) ) {
                    return true;
                }
            }
        }
        return false;
    }
}