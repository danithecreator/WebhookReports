package co.com.challenge.commonutils;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class DateHelper {
    private static final List<DateTimeFormatterWrapper> knownFormatters = new ArrayList<>();

    private static class DateTimeFormatterWrapper {
        private final DateTimeFormatter formatter;
        private final boolean isZoned;

        public DateTimeFormatterWrapper(String pattern, boolean isZoned) {
            this.formatter = DateTimeFormatter.ofPattern(pattern);
            this.isZoned = isZoned;
        }

        public DateTimeFormatterWrapper(DateTimeFormatter formatter, boolean isZoned) {
            this.formatter = formatter;
            this.isZoned = isZoned;
        }
    }

    static {
        knownFormatters.add(new DateTimeFormatterWrapper(DateTimeFormatter.ISO_DATE_TIME, true));
        knownFormatters.add(new DateTimeFormatterWrapper("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", true));
        knownFormatters.add(new DateTimeFormatterWrapper("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", true));
        knownFormatters.add(new DateTimeFormatterWrapper("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", false));
        knownFormatters.add(new DateTimeFormatterWrapper("yyyy-MM-dd'T'HH:mm:ss.SSS", false));
        knownFormatters.add(new DateTimeFormatterWrapper("yyyy-MM-dd'T'HH:mm:ss", false));
        knownFormatters.add(new DateTimeFormatterWrapper("yyyy-MM-dd HH:mm:ss", false));
        knownFormatters.add(new DateTimeFormatterWrapper("dd/MM/yyyy HH:mm:ss", false));
        knownFormatters.add(new DateTimeFormatterWrapper("MM/dd/yyyy HH:mm:ss", false));
        knownFormatters.add(new DateTimeFormatterWrapper("yyyy/MM/dd HH:mm:ss", false));
    }

    public static String parseToStandardFormat(String dateStr) {
        dateStr = dateStr.replaceAll("\\s+\\d{2}:\\d{2}$", "");

        for (DateTimeFormatterWrapper formatterWrapper : knownFormatters) {
            try {
                LocalDateTime dateTime;
                if (formatterWrapper.isZoned) {
                    ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr, formatterWrapper.formatter);
                    dateTime = zonedDateTime.toLocalDateTime();
                } else {
                    dateTime = LocalDateTime.parse(dateStr, formatterWrapper.formatter);
                }
                return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            } catch (DateTimeParseException e) {
                continue;
            }
        }

        throw new IllegalArgumentException("Fecha no se puede parsear: " + dateStr);
    }
}
