package org.motechproject.bbcwt.ivr.action;

import org.motechproject.bbcwt.domain.Chapter;
import org.motechproject.bbcwt.domain.Lesson;
import org.motechproject.bbcwt.domain.Milestone;
import org.motechproject.bbcwt.ivr.IVR;
import org.motechproject.bbcwt.ivr.IVRMessage;
import org.motechproject.bbcwt.ivr.IVRRequest;
import org.motechproject.bbcwt.repository.ChaptersRespository;
import org.motechproject.bbcwt.repository.MilestonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/chapterEndAnswer")
public class ChapterEndAnswerAction extends BaseAction{

    private ChaptersRespository chaptersRespository;
    private MilestonesRepository milestonesRepository;

    @Autowired
    public ChapterEndAnswerAction(ChaptersRespository chaptersRespository, MilestonesRepository milestonesRepository, IVRMessage messages) {
        this.chaptersRespository = chaptersRespository;
        this.milestonesRepository = milestonesRepository;
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

        String callerId = (String) request.getSession().getAttribute(IVR.Attributes.CALLER_ID);

        //TODO: Marking of the last milestone finish is being done at lesson end answer action as well, any way to remove this duplication?
        Milestone milestone = milestonesRepository.markLastMilestoneFinish(callerId);

        Chapter currentChapter = chaptersRespository.get(milestone.getChapterId());
        Lesson lastLesson = currentChapter.getLessonById(milestone.getLessonId());

        if(chosenOption == '1') {
            return "forward:/chapter/"+currentChapter.getNumber()+"/lesson/"+lastLesson.getNumber();
        }
        else {
           if(chosenOption == '2') {
               return "forward:/startQuiz";
           }
           else {
               ivrResponseBuilder(request).addPlayText(messages.get(IVRMessage.INVALID_INPUT));
               return "forward:/lessonEndMenu";
           }
        }
    }

}