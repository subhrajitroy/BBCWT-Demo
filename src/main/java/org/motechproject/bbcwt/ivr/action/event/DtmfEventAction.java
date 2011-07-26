package org.motechproject.bbcwt.ivr.action.event;

import org.motechproject.bbcwt.ivr.IVRMessage;
import org.motechproject.bbcwt.ivr.IVRRequest;
import org.motechproject.bbcwt.ivr.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class DtmfEventAction extends BaseAction {

    @Autowired
    public DtmfEventAction(IVRMessage messages) {
        this.messages = messages;
    }

    @Override
    public String handle(IVRRequest ivrRequest, HttpServletRequest request, HttpServletResponse response) {
        String input = ivrRequest.getData();
        if(input.charAt(0) == '1') {
            return responseWith(ivrRequest, "msg.help");
        }
        return responseWith(ivrRequest, "content.chapter1");
    }

}
