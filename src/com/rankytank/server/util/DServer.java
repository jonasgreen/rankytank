package com.rankytank.server.util;

import com.jg.core.client.appcontrol.SystemException;
import com.jg.core.client.model.CalendarDay;
import com.jg.core.client.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 */
public class DServer {
    public static SimpleDateFormat clientServerProtocolFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static SimpleDateFormat eventMail = new SimpleDateFormat("EEE d MMM yyyy");



    public static Date get(CalendarDay cd){
        try {
            return clientServerProtocolFormat.parse(cd.getValue());
        }
        catch (ParseException e) {
            throw new SystemException(e.getMessage());
        }
    }

    public static CalendarDay get(Date d){
        if(d == null){
            return null;
        }
        return new CalendarDay(clientServerProtocolFormat.format(d));
    }

    public static Date shiftTimeZone(Date d, Integer timeZoneOffset){
        return DateUtil.addHoursToDate(d, timeZoneOffset*(-1));
    }

}
