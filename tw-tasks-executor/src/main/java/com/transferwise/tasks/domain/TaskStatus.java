package com.transferwise.tasks.domain;

public enum TaskStatus {
    NEW,
    WAITING,
    SUBMITTED,
    PROCESSING,
    DONE,
    ERROR, // Generates alerts
    FAILED // ERROR is acked, alerts are off
}
