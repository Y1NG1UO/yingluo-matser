package com.yingluo.service.handler;

import com.yingluo.core.enums.Events;
import com.yingluo.core.pojo.dto.SeckillWebMockRequestDTO;
import com.yingluo.service.util.StateMachineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class StateMachinePreRequestHandlerTest {

    @Mock
    private StateMachineService stateMachineService;

    @InjectMocks
    private StateMachinePreRequestHandler stateMachinePreRequestHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldHandleRequestSuccessfully() {
        SeckillWebMockRequestDTO request = new SeckillWebMockRequestDTO();
        request.setSeckillId(123L);
        when(stateMachineService.feedMachine(Events.ACTIVITY_RESET, request.getSeckillId())).thenReturn(true);
        when(stateMachineService.feedMachine(Events.ACTIVITY_START, request.getSeckillId())).thenReturn(true);

        stateMachinePreRequestHandler.handle(request);

        verify(stateMachineService, times(1)).initStateMachine(request.getSeckillId());
        verify(stateMachineService, times(1)).feedMachine(Events.ACTIVITY_RESET, request.getSeckillId());
        verify(stateMachineService, times(1)).feedMachine(Events.ACTIVITY_START, request.getSeckillId());
    }

    @Test
    public void shouldThrowExceptionWhenActivityNotEnded() {
        SeckillWebMockRequestDTO request = new SeckillWebMockRequestDTO();
        request.setSeckillId(123L);
        when(stateMachineService.feedMachine(Events.ACTIVITY_RESET, request.getSeckillId())).thenReturn(false);

        try {
            stateMachinePreRequestHandler.handle(request);
        } catch (RuntimeException e) {
            verify(stateMachineService, times(1)).initStateMachine(request.getSeckillId());
            verify(stateMachineService, times(1)).feedMachine(Events.ACTIVITY_RESET, request.getSeckillId());
            verify(stateMachineService, times(0)).feedMachine(Events.ACTIVITY_START, request.getSeckillId());
        }
    }
}
