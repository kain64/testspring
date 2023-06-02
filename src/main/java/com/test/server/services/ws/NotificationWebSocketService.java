package com.test.server.services.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * ws notification service
 */
@Service
public class NotificationWebSocketService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    /**
     * Send update employee id via ws
     * @param id
     * @throws Exception
     */
    public void sendUpdatedUserId(final String id) {
        simpMessagingTemplate.convertAndSend("/all", id);
    }
}
