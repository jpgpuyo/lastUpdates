package com.focusings.focusingsworld5.notificationManagement;

import java.util.List;

public interface AsyncNotificationResponse {
    void sendNotification(List<Update> output);
}