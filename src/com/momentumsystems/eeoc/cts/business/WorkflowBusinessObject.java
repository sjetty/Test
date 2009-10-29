package com.momentumsystems.eeoc.cts.business;

import com.momentumsystems.eeoc.cts.domain.DailyNewsClips;
import com.momentumsystems.livelink.User;

/**
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $ $Date: 2009-10-29 15:54:36 $
 */
public interface WorkflowBusinessObject extends CtsBusinessObject {
    int startDncWorkflow(DailyNewsClips dnc, User user);
}
