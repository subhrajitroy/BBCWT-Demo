package org.motechproject.bbcwt.ivr.action;

import org.motechproject.bbcwt.domain.Chapter;
import org.motechproject.bbcwt.domain.Milestone;
import org.motechproject.bbcwt.ivr.IVR;
import org.motechproject.bbcwt.ivr.IVRRequest;
import org.motechproject.bbcwt.repository.MilestonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/repeatLastChapter")
public class RepeatLastChapterAction extends BaseAction {
    private MilestonesRepository milestonesRepository;

    @Autowired
    public RepeatLastChapterAction(MilestonesRepository milestonesRepository) {
        this.milestonesRepository = milestonesRepository;
    }

    @Override
    @RequestMapping(method= RequestMethod.GET)
    public String handle(IVRRequest ivrRequest, HttpServletRequest request, HttpServletResponse response) {
        String healthWorkerCallerId = (String)request.getSession().getAttribute(IVR.Attributes.CALLER_ID);

        Milestone currentMilestone = milestonesRepository.currentMilestoneWithLinkedReferences(healthWorkerCallerId);
        Chapter currentChapter = currentMilestone.getChapter();
        int currentChapterNumber = currentChapter.getNumber();

        return "forward:/chapter/"+ currentChapterNumber +"/lesson/1";
    }
}