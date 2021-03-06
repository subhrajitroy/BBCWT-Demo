package org.motechproject.bbcwt.ivr.action;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.motechproject.bbcwt.domain.HealthWorker;
import org.motechproject.bbcwt.ivr.IVR;
import org.motechproject.bbcwt.ivr.IVRRequest;
import org.motechproject.bbcwt.repository.HealthWorkersRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class NewCallActionTest extends BaseActionTest {
    private NewCallAction action;
    @Mock
    private HealthWorkersRepository healthWorkers;
    @Mock
    private HealthWorker healthWorker;

    @Before
    public void setUp() {
        action = new NewCallAction(messages, healthWorkers);
    }

    @Test
    public void shouldWelcomeNewUser() {
        String callerId = "9898982323";
        IVRRequest ivrRequest = new IVRRequest("unique-call-id", callerId, IVR.Event.NEW_CALL.key(), "Data");

        when(healthWorkers.findByCallerId(ivrRequest.getCid())).thenReturn(null);
        when(messages.get("wc.msg.new.user")).thenReturn("Welcome. This is the first time you are accessing FLW Training course.");

        String nextAction = action.handle(ivrRequest, request, response);

        verify(session).setAttribute(IVR.Attributes.CALLER_ID, callerId);
        verify(healthWorkers).findByCallerId(ivrRequest.getCid());
        verify(ivrResponseBuilder).addPlayText("Welcome. This is the first time you are accessing FLW Training course.");

        assertEquals("The next action chained in case of new user should be helpMenu", "forward:/helpMenu", nextAction);
    }

    @Test
    public void shouldWelcomeExistingUser() {
        String callerId = "9898982323";
        IVRRequest ivrRequest = new IVRRequest("unique-call-id", callerId, IVR.Event.NEW_CALL.key(), "Data");

        when(healthWorkers.findByCallerId(ivrRequest.getCid())).thenReturn(healthWorker);
        when(messages.get("wc.msg.existing.user")).thenReturn("Welcome. Continue your FLW Training course.");


        String nextAction = action.handle(ivrRequest, request, response);

        verify(session).setAttribute(IVR.Attributes.CALLER_ID, callerId);
        verify(healthWorkers).findByCallerId(ivrRequest.getCid());

        assertEquals("The next action chained in case of existing user should be /existingUserMenu.", "forward:/existingUserMenu", nextAction);
    }

}
