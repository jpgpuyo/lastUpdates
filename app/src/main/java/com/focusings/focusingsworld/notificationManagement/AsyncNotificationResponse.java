package com.focusings.focusingsworld.notificationManagement;

import java.util.List;

public interface AsyncNotificationResponse {
    void sendNotification(List<Update> output);
}