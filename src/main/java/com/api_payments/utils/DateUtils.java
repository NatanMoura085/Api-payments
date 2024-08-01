package com.api_payments.utils;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateUtils {
    public String formatador(String data){
        OffsetDateTime dateTime = OffsetDateTime.parse(data);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm");
        String formatted = dateTime.format(formatter);

        return formatted;
    }
}
