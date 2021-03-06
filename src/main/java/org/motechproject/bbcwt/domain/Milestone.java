package org.motechproject.bbcwt.domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.ektorp.support.TypeDiscriminator;

import java.util.Date;

@TypeDiscriminator("doc.documentType == 'Milestone'")
public class Milestone extends BaseCouchEntity {
    private String healthWorkerId;
    private String chapterId;
    private String lessonId;
    private String questionId;
    private Date startDate;
    private Date endDate;

    private HealthWorker healthWorker;
    private Chapter chapter;

    public Milestone()
    {

    }

    public Milestone(String healthWorkerId, String chapterId, String lessonId, String questionId, Date startDate) {
        this.healthWorkerId = healthWorkerId;
        this.chapterId = chapterId;
        this.lessonId = lessonId;
        this.questionId = questionId;
        this.startDate = startDate;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getHealthWorkerId() {
        return healthWorkerId;
    }

    public void setHealthWorkerId(String healthWorkerId) {
        this.healthWorkerId = healthWorkerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    @JsonIgnore
    public boolean isAccomplished() {
        return this.endDate != null;
    }

    @JsonIgnore
    public HealthWorker getHealthWorker() {
        return healthWorker;
    }

    @JsonIgnore
    public void setHealthWorker(HealthWorker healthWorker) {
        this.healthWorker = healthWorker;
    }

    @JsonIgnore
    public Chapter getChapter() {
        return chapter;
    }

    @JsonIgnore
    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
}