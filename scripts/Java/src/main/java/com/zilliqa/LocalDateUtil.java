import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
 
public final class LocalDateUtils {
 
    //默认使用系统当前时区
    private static final ZoneId ZONE = ZoneId.systemDefault();
 
    private static final String DATE_FORMAT = "yyyy-MM-dd";
 
    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
 
    private static final String TIME_NOFUII_FORMAT = "yyyyMMddHHmmss";
 
    private static final String REGEX = "\\:|\\-|\\s";
 
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
 
    /**
     * 根据传入的时间格式返回系统当前的时间
     *
     * @param format string
     * @return
     */
    public static String timeByFormat(String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime now = LocalDateTime.now();
        return now.format(dateTimeFormatter);
    }
 
    /**
     * 将Date转换成LocalDateTime
     *
     * @param d date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date d) {
        Instant instant = d.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE);
        return localDateTime;
    }
 
    /**
     * 将Date转换成LocalDate
     *
     * @param d date
     * @return
     */
    public static LocalDate dateToLocalDate(Date d) {
        Instant instant = d.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE);
        return localDateTime.toLocalDate();
    }
 
    /**
     * 将Date转换成LocalTime
     *
     * @param d date
     * @return
     */
    public static LocalTime dateToLocalTime(Date d) {
        Instant instant = d.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE);
        return localDateTime.toLocalTime();
    }
 
    /**
     * 将LocalDate转换成Date
     *
     * @param localDate
     * @return date
     */
    public static Date localDateToDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZONE).toInstant();
        return Date.from(instant);
    }
 
    /**
     * 将LocalDateTime转换成Date
     *
     * @param localDateTime
     * @return date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZONE).toInstant();
        return Date.from(instant);
    }
 
    /**
     * 将相应格式yyyy-MM-dd yyyy-MM-dd HH:mm:ss 时间字符串转换成Date
     *
     * @param time string
     * @param format string
     * @return date
     */
    public static Date stringToDate(String time , String format) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(format);
        if (DATE_FORMAT_DEFAULT.equals(format)) {
            return LocalDateUtils.localDateTimeToDate(LocalDateTime.parse(time, f));
        } else if (DATE_FORMAT.equals(format)){
            return LocalDateUtils.localDateToDate(LocalDate.parse(time, f));
        }
        return null;
    }
 
 
 
    /**
     * 根据ChronoUnit枚举计算两个时间差，日期类型对应枚举
     * 注:注意时间格式，避免cu选择不当的类型出现的异常
     *
     * @param cu chronoUnit.enum.key
     * @param d1 date
     * @param d2 date
     * @return
     */
    public static long chronoUnitBetweenByDate(ChronoUnit cu, Date d1, Date d2) {
        return cu.between(LocalDateUtils.dateToLocalDateTime(d1),LocalDateUtils.dateToLocalDateTime(d2));
    }
 
    /**
     * 根据ChronoUnit枚举计算两个时间差，日期类型对应枚举
     * 注:注意时间格式，避免cu选择不当的类型出现的异常
     *
     * @param cu chronoUnit.enum.key
     * @param s1 string
     * @param s2 string
     * @return
     */
    public static Long chronoUnitBetweenByString(ChronoUnit cu, String s1, String s2, String dateFormat) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern(dateFormat);
        if (DATE_FORMAT_DEFAULT.equals(dateFormat)) {
            LocalDateTime l1 = LocalDateUtils.dateToLocalDateTime(LocalDateUtils.stringToDate(s1,dateFormat));
            LocalDateTime l2 = LocalDateUtils.dateToLocalDateTime(LocalDateUtils.stringToDate(s2,dateFormat));
            return cu.between(l1,l2);
        }
        if (DATE_FORMAT.equals(dateFormat)) {
            LocalDate l1 = LocalDateUtils.dateToLocalDate(LocalDateUtils.stringToDate(s1,dateFormat));
            LocalDate l2 = LocalDateUtils.dateToLocalDate(LocalDateUtils.stringToDate(s2,dateFormat));
            return cu.between(l1,l2);
        }
        if (TIME_NOFUII_FORMAT.equals(dateFormat)) {
            LocalDateTime l1 = LocalDateTime.parse(s1.replaceAll(REGEX,""), f);
            LocalDateTime l2 = LocalDateTime.parse(s2.replaceAll(REGEX,""), f);
            return cu.between(l1,l2);
        }
        return null;
    }
 
    /**
     * 根据ChronoUnit枚举计算两个时间相加，日期类型对应枚举
     * 注:注意时间格式，避免cu选择不当的类型出现的异常
     *
     * @param cu chronoUnit.enum.key
     * @param d1 date
     * @param d2 long
     * @return
     */
    public static Date chronoUnitPlusByDate(ChronoUnit cu, Date d1, long d2) {
        return LocalDateUtils.localDateTimeToDate(cu.addTo(LocalDateUtils.dateToLocalDateTime(d1),d2));
    }
 
    /**
     * 将time时间转换成毫秒时间戳
     *
     * @param time string
     * @return
     */
    public static long stringDateToMilli (String time) {
        return LocalDateUtils.stringToDate(time,DATE_FORMAT_DEFAULT).toInstant().toEpochMilli();
    }
 
    /**
     * 将毫秒时间戳转换成Date
     *
     * @param time string
     * @return
     */
    public static Date timeMilliToDate (String time) {
        return Date.from(Instant.ofEpochMilli(Long.parseLong(time)));
    }
