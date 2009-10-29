package com.momentumsystems.eeoc.cts.business;

import com.momentumsystems.eeoc.cts.domain.DailyNewsClips;
import com.momentumsystems.eeoc.cts.util.system.CtsSystemInfoImpl;
import com.momentumsystems.livelink.LivelinkException;
import com.momentumsystems.livelink.User;
import com.momentumsystems.livelink.ObjectReference;
import com.momentumsystems.livelink.WorkflowInformation;
import com.momentumsystems.livelink.server.WorkflowTool;

import java.util.*;

/**
 * @author $Author: xlu $
 * @version $Revision: 1.1.1.1 $ $Date: 2009-10-29 15:54:36 $
 */
public class WorkflowBusinessObjectImpl extends CtsBusinessObjectImpl implements WorkflowBusinessObject {

    WorkflowTool livelinkWorkflowTool;

    public int startDncWorkflow(DailyNewsClips dnc, User user)
    {
        //if submittal type is archive DON'T DO WORFLOW!
        if (dnc.getSubmittalTypeCd() == 10)
            return 0;

        //Find the workflow map
        CtsSystemInfoImpl ctsSystemInfo = (CtsSystemInfoImpl) systemInfo;
        ObjectReference workflowMap = new ObjectReference(0, ctsSystemInfo.getDncWorkflowId());


        Map instructionsMacroMap = setInstructionsMacros(dnc);
        List stepInformationList = new ArrayList();
        WorkflowInformation wfi;
        String title = "Daily News Clips_" + dnc.getControlNbr();

        //Launch the Workflows
        if (user != null)
        {
            try
            {

                wfi = livelinkWorkflowTool.initiate(workflowMap, user, title, stepInformationList,
                                                instructionsMacroMap, null, true);
                
//                log(wfi.getName() + " (" + wfi.getWorkID() + ") was initiated by " + user.getUsername());

                livelinkWorkflowTool.update(wfi, null, null, instructionsMacroMap, null);
            }
            catch (LivelinkException e)
            {
                e.printStackTrace();
                log.error("Unable to initiate workflow for: " + workflowMap.getObjectID());
                throw new RuntimeException(e);
            }

            return wfi.getWorkID();
        }
        return -1;
    }

    public Map setInstructionsMacros(DailyNewsClips dnc)
	{
        Map instructions = new HashMap();
        String baseUrl = systemInfo.getBaseUrl();
        instructions.put("{1}", baseUrl + "?func-ll&objId=" + dnc.getFolderId() + "&objectAction=browse&sort=name");
        return instructions;

	}

    public WorkflowTool getLivelinkWorkflowTool() {
        return livelinkWorkflowTool;
    }

    public void setLivelinkWorkflowTool(WorkflowTool livelinkWorkflowTool) {
        this.livelinkWorkflowTool = livelinkWorkflowTool;
    }
}
