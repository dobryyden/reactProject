package com.mrybakova;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class OperationHelper {
    public static String getUUIDAndTimeStamp(String methodType) {
        UUID uuid = UUID.randomUUID();
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return uuid.toString() + " " + timestamp + " " + methodType;
    }
}
