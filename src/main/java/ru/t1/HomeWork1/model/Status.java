package ru.t1.HomeWork1.model;

public enum Status {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETE;

    public static Status fromString(String status) {
        for (Status s : Status.values()) {
            if (s.name().equalsIgnoreCase(status.replace(" ", ""))) {
                return s;
            }
        }
        return null;
    }
}