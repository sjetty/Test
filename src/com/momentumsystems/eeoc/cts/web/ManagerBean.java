
package com.momentumsystems.eeoc.cts.web;

import com.momentumsystems.util.system.SystemInfo;

/**
 *
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $   $Date: 2009-10-29 15:54:36 $
 */

public interface ManagerBean {

    SystemInfo getSystemInfo();

    void setSystemInfo( SystemInfo systemInfo );

    //todo: put getters/setters for business beans here
}
