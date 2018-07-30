
/*
Symbol   Meaning                 Presentation        Example
------   -------                 ------------        -------
G        era designator          (Text)              AD
y        year                    (Number)            1996
M        month in year           (Text & Number)     July & 07
d        day in month            (Number)            10
h        hour in am/pm (1~12)    (Number)            12
H        hour in day (0~23)      (Number)            0
m        minute in hour          (Number)            30
s        second in minute        (Number)            55
S        millisecond             (Number)            978
E        day in week             (Text)              Tuesday
D        day in year             (Number)            189
F        day of week in month    (Number)            2 (2nd Wed in July)
w        week in year            (Number)            27
W        week in month           (Number)            2
a        am/pm marker            (Text)              PM
k        hour in day (1~24)      (Number)            24
K        hour in am/pm (0~11)    (Number)            0
z        time zone               (Text)              Pacific Standard Time
'        escape for text         (Delimiter)
''       single quote            (Literal)           '
 */

package com.master.root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatter {
	public static String  SHORT_DATE = "dd/MM/yyyy",
		DATE = "dd 'de' MMMM 'de' yyyyy",
		SHORT_TIME = "HH:mm",
		TIME = "HH:mm:ss",
		DATE_TIME1 = "HH:mm 'do dia' dd/MM/yyyy",
		DATE_TIME2 = "HH:mm 'do dia' dd/MMM/yyyy";
	
	public DateFormatter() {
	}
	public static void main(String[] args) {
		DateFormatter dateFormatter = new DateFormatter();
		Calendar calendar = Calendar.getInstance();
		// //// System.out.println(dateFormatter.calendarToString(calendar, SHORT_DATE));
		// //// System.out.println(dateFormatter.calendarToString(calendar, DATE));
		// //// System.out.println(dateFormatter.calendarToString(calendar, SHORT_TIME));
		// //// System.out.println(dateFormatter.calendarToString(calendar, TIME));
		// //// System.out.println(dateFormatter.calendarToString(calendar, DATE_TIME1));
		// //// System.out.println(dateFormatter.calendarToString(calendar, DATE_TIME2));
		
		calendar = stringToCalendar("00:00 do dia 31/Dez/1919", DATE_TIME2);
		// //// System.out.println(dateFormatter.calendarToString(calendar, DATE_TIME2));
		
		calendar = stringToCalendar("01 de Janeiro de 1000", DATE);
		// //// System.out.println(dateFormatter.calendarToString(calendar, DATE));
		
	}
	
	// Recebe um objeto do tipo Calendar e retorna uma representaçao em forma de String
	public String calendarToString(Calendar calendar, String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date currentTime_1 = calendar.getTime();
		return formatter.format(currentTime_1);
	}
	
	// Recebe uma String representando uma data e restorna um objeto do tipo Calendar com a mesma informaçao
	static public Calendar stringToCalendar(String str1, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			Date date = formatter.parse(str1);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		}
		catch(ParseException e) {
			// //// System.out.println("Quebrou");
			return null;
		}
	}
}