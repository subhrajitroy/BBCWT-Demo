package org.motechproject.bbcwt.ivr.action;

import org.motechproject.bbcwt.ivr.IVRMessage;
import org.motechproject.bbcwt.ivr.IVRRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/helpMenuAnswer")
public class HelpMenuAnswerAction extends BaseAction {

    @Autowired
    public HelpMenuAnswerAction(IVRMessage messages) {
        this.messages = messages;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public String handle(IVRRequest ivrRequest, HttpServletRequest request, HttpServletResponse response) {
        String dtmfInput = ivrRequest.getData();
        char chosenOption = ' ';

        if(dtmfInput!=null && dtmfInput.length() > 0) {
            chosenOption = dtmfInput.charAt(0);
        }

        if(chosenOption == '1') {
            return "forward:/chapter/1/lesson/1";
        }
        else {
           if(chosenOption == '2') {
               ivrResponseBuilder(request).addPlayAudio(contentAt(messages.get(IVRMessage.IVR_HELP)));
           }
           else {
               ivrResponseBuilder(request).addPlayText(messages.get(IVRMessage.INVALID_INPUT));
           }
        }
        return "forward:/helpMenu";
    }

    private String contentAt(String path) {
        return messages.get(IVRMessage.CONTENT_LOCATION).concat(path);
    }
}
