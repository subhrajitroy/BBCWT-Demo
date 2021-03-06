package org.motechproject.bbcwt.ivr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class IVRMessage {
    public static final String BBCWT_IVR_NEW_USER_WC_MESSAGE = "wc.msg.new.user";
    public static final String BBCWT_IVR_EXISTING_USER_WC_MESSAGE = "wc.msg.existing.user";
    public static final String BBCWT_IVR_NEW_USER_OPTIONS = "msg.new.user.options";
    public static final String IVR_HELP = "msg.help";
    public static final String INVALID_INPUT = "invalid.ivr.input";
    public static final String END_OF_CHAPTER_MENU = "msg.chapter.end";
    public static final String END_OF_LESSON_MENU = "msg.lesson.end";
    public static final String END_OF_QUIZ_MESSAGE = "msg.quiz.end";
    public static final String INFORM_SCORE_START = "msg.score.start";
    public static final String INFORM_SCORE_OUTOF = "msg.score.outof";
    public static final String END_OF_QUIZ_OPTIONS = "msg.quiz.end.options";
    public static final String MSG_START_OF_QUIZ = "msg.start.of.quiz";
    public static final String MSG_COURSE_COMPLETION = "msg.course.completion";

    @Qualifier("ivrProperties")
    @Autowired
    private Properties properties;
    public static final String CONTENT_LOCATION = "content.location";

    public String get(String key) {
        return (String) properties.get(key);
    }
}
