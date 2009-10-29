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

package com.momentumsystems.eeoc.cts.persistence;

import com.momentumsystems.persistence.PersistenceException;

import java.util.List;

/**
 *
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $   $Date: 2009-10-29 15:54:36 $
 */

public interface NvsBaseDao {


    /**
     * This method returns all items
     *
     * @return List<Object>
     * @throws com.momentumsystems.persistence.PersistenceException
     *
     */
    List getAll() throws PersistenceException;

    List getAllActive();

    List getAll( final String queryName );


    /**
     * This method returns all items
     *
     * @return List<Object>
     * @throws PersistenceException
     */
    Object get( Long id ) throws PersistenceException;

    Object get( String id ) throws PersistenceException;

    Object get( long id ) throws PersistenceException;

    void remove( Object object );

    void remove( Long id );

    void remove( String id );

    void remove( long id );

    Object create( Object object );

    Object save( Object object );

    Object saveOrUpdate( Object object );

    /**
     * This method returns all items including ones marked as deleted
     *
     * @return List<Object>
     * @throws PersistenceException
     */
    List getAllWithDeletes() throws PersistenceException;


}
