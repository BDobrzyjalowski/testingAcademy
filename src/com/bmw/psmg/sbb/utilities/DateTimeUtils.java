package com.bmw.psmg.sbb.utilities;

import wt.inf.container.WTContainer;
import wt.inf.container.WTContainerHelper;
import wt.inf.container.WTContainerRef;
import wt.org.TimeZoneHelper;
import wt.org.WTPrincipal;
import wt.org.WTUser;
import wt.preference.PreferenceHelper;
import wt.session.SessionHelper;
import wt.util.WTContext;
import wt.util.WTException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * Util class for easier handling Related to Date
 */
public class DateTimeUtils {
    
    
    private DateTimeUtils() { }
    
    /**
     * Get the time stamp from date in passed required format
     * @param date date String
     * @param actualFormat original Format
     * @param requiredFormat expected format
     * @return time stamp
     * @throws ParseException when issue occurs in parsing
     */
    public static Timestamp getTimestampforDateInRequiredFormat(String date, String actualFormat, String requiredFormat) throws ParseException{

        SimpleDateFormat actualDateFormat = new SimpleDateFormat(actualFormat);
        SimpleDateFormat requiredDateFormat = new SimpleDateFormat(requiredFormat);
        Date  actualFormattedDate= actualDateFormat.parse(date);
        String requiredformattedDateStr = requiredDateFormat.format(actualFormattedDate);
        Date requiredformattedDate = requiredDateFormat.parse(requiredformattedDateStr);
        return new Timestamp(requiredformattedDate.getTime());
    }
    
    /**
     * Gets TimeZone as set by preference
     * @param principal given WTPrincipal object 
     * @return TimeZone
     * @throws WTException if failed to get session principal or to read preference value
     */
    public static TimeZone getTimeZonePreference(WTPrincipal principal) throws WTException {
        if (principal == null) {
            principal = SessionHelper.getPrincipal();
        }
        String timeZonePrefStr = getTimeZonePreferenceString(principal);
        TimeZone timeZone = null;
        if (timeZonePrefStr == null) {
            timeZone = WTContext.getContext().getTimeZone();
        } else {
            timeZone = TimeZone.getTimeZone(timeZonePrefStr);
        }

        return timeZone;
    }
    
    /**
     * Gets time zone preference string for given princiapl
     * @param principal given WTPrincipal object 
     * @return time zone id
     * @throws WTException if failed to get container reference or to read preference value
     */
    private static String getTimeZonePreferenceString(WTPrincipal principal) throws WTException {
        if (!(principal instanceof WTUser)) {
            return null;
        } else {
            WTContainer container = WTContainerHelper.service.getExchangeContainer();
            TimeZone timeZone = ((TimeZone) PreferenceHelper.service.getValue(WTContainerRef.newWTContainerRef(container), "/ProjectLink/localTimeZone", "WINDCHILL", (WTUser) principal));
            if(timeZone == null) {
                return null;
            }
            return timeZone.getID();
        }
    }

    /**
     * format for time + date + time zone
     *
     * @return
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzz");
    }

    /**
     * wrapper for WT method for fetching TimeZone object
     *
     * @return
     * @throws WTException
     */
    public static TimeZone getSBBTimeZone() throws WTException {
        return TimeZoneHelper.getTimeZone();
    }

    /**
     * fetches ZoneId object for u8se with java.time functions
     *
     * @return
     * @throws WTException
     */
    public static ZoneId getSBBTimeZoneId() throws WTException {
        return ZoneId.of(getSBBTimeZone().getID());
    }
}
