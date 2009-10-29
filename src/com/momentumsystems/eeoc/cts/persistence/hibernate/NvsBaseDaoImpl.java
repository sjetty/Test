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

package com.momentumsystems.eeoc.cts.persistence.hibernate;

import com.momentumsystems.exceptions.GeneralRuntimeException;
import com.momentumsystems.eeoc.cts.persistence.NvsBaseDao;
import com.momentumsystems.persistence.PersistenceException;
import com.momentumsystems.persistence.hibernate.HibernateDomainUtils;
import com.momentumsystems.persistence.hibernate.HibernateQueryHelper;
import com.momentumsystems.util.system.SystemInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 *
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $   $Date: 2009-10-29 15:54:36 $
 */

public class NvsBaseDaoImpl extends HibernateDaoSupport implements NvsBaseDao {

    protected Log log = LogFactory.getLog( getClass() );
    protected SystemInfo systemInfo;

    protected String SELECT;
    protected String SELECT_WITH_DELETE;
    protected String SELECT_ALL_ACTIVE;


    protected String getQuery( String key ) {
        if ( systemInfo != null ) {
            return HibernateQueryHelper.getQuery( key, systemInfo.getHibernateResourceBundle() );
        }
        else {
            return HibernateQueryHelper.getQuery( key );
        }
    }

    public List getAll() throws PersistenceException {
        return ( List ) getHibernateTemplate().execute( new HibernateCallback() {
            public Object doInHibernate( Session session ) throws HibernateException {
                Query query = session.createQuery( getQuery( SELECT ) );
                List result = query.list();
                return result;
            }
        } );
    }

    public List getAllWithDeletes() throws PersistenceException {
        return ( List ) getHibernateTemplate().execute( new HibernateCallback() {
            public Object doInHibernate( Session session ) throws HibernateException {
                Query query = session.createQuery( getQuery( SELECT_WITH_DELETE ) );
                List result = query.list();
                return result;
            }
        } );
    }

    public List getAllActive() throws PersistenceException {
        return ( List ) getHibernateTemplate().execute( new HibernateCallback() {
            public Object doInHibernate( Session session ) throws HibernateException {
                Query query = session.createQuery( getQuery( SELECT_ALL_ACTIVE ) );
                List result = query.list();
                return result;
            }
        } );
    }

    public List getAll( final String queryName ) throws PersistenceException {
        return ( List ) getHibernateTemplate().execute( new HibernateCallback() {
            public Object doInHibernate( Session session ) throws HibernateException {
                Query query = session.createQuery( getQuery( queryName ) );
                List result = query.list();
                return result;
            }
        } );
    }

    /**
     * Return a domain object if fully qualified name is used AND the DAO naming convention
     * <name>Dao<whatever> is used. For example, if the domain object is Address
     * com.momentumsystems.project.something.AddressDaoImpl, then the Address Domain object is used.
     * If we dont' find our class, let's see if we are using the <name>DaoImpl where domain name is <name>+"Data"
     * convention. If not, throw exception
     * <p/>
     * //TODO dmccoy : ME : Feb 28, 2006 : Add support for using Strings
     * //TODO dmccoy : ME : Feb 28, 2006 : add support for looking for a list of package names
     *
     * @param id
     * @return Object
     */
    public Object get( Long id ) {
        Class clazz = getClazz();
        return getHibernateTemplate().get( clazz, id );
    }

    private Class getClazz() {
        Class clazz;
        String className = null;
        try {
            String domainPath = systemInfo.getDomainObjectPath();
            className = domainPath + HibernateDomainUtils.getDomainNameFromDao( getClass().getName() );
            clazz = Class.forName( className );
        }
        catch ( ClassNotFoundException e ) {
            // If we dont' find our class, let's see if we are using the <name>DaoImpl where domain name is <name>+"Data"
            // convention. If not, throw exception
            try {
                className += "Data";
                clazz = Class.forName( className );
            }
            catch ( ClassNotFoundException e1 ) {
                //TODO dmccoy : ME : Jan 22, 2006 : Need a better error message
                GeneralRuntimeException gre = new GeneralRuntimeException( e1 );
                log.error( gre );
                throw gre;
            }

        }

        return clazz;
    }

    public Object get( long id ) throws PersistenceException {
        return get( new Long( id ) );
    }


    public Object get( String id ) {
        return get( new Long( id ) );
    }

    public Object save( Object object ) {
        getHibernateTemplate().save( object );
        return object;
    }

    public Object saveOrUpdate( Object object ) {
        getHibernateTemplate().saveOrUpdate( object );
        return object;
    }

    public void remove( Object object ) {
        getHibernateTemplate().delete( object );
    }

    public void remove( Long objectId ) {
        throw new GeneralRuntimeException( "missing implementation" );
    }

    public void remove( long id ) {
        throw new GeneralRuntimeException( "missing implementation" );
    }

    public void remove( String objectId ) {
        throw new GeneralRuntimeException( "missing implementation" );
    }

    public Object create( Object object ) {
        getHibernateTemplate().save( object );
        return object;
    }

    public SystemInfo getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo( SystemInfo systemInfo ) {
        this.systemInfo = systemInfo;
    }

    protected <T> List<T> findByCriteria(Class clazz, Criterion... criterion) {
        Criteria crit = getSession().createCriteria(clazz);
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
   }

    protected <T> List<T> findByCriteria(Criterion... criterion) {
        Criteria crit = getSession().createCriteria(getClazz());
        for (Criterion c : criterion) {
            crit.add(c);
        }
        return crit.list();
    }

    protected <T> List<T> findByExample( T exampleInstance, String... excludeProperty ) {
        Criteria crit = getSession().createCriteria( getClazz() );
        Example example = Example.create( exampleInstance );
        for ( String exclude : excludeProperty ) {
            example.excludeProperty( exclude );
        }
        crit.add( example );
        return crit.list();
    }

}
