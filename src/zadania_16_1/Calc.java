package zadania_16_1;

import javax.xml.crypto.Data;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Set;

public class Calc {
    public void printAllData(Map<ZonedDateTime, Task> tasks) {
        Set<ZonedDateTime> key = tasks.keySet();
        Set<Map.Entry<ZonedDateTime, Task>> entrySet = tasks.entrySet();

        for (ZonedDateTime zonedDateTime : key) {
            LocalDateTime lDT = zonedDateTime.toLocalDateTime();
            System.out.println(lDT + " - " + tasks.get(zonedDateTime).getDescription());
        }
    }

    public void printData(Map<ZonedDateTime, Task> tasks, boolean termFuture) {
        InputOutputData inpOuData = new InputOutputData();
        ZonedDateTime nowData = inpOuData.nowD;
        int future = 0;
        Set<ZonedDateTime> key = tasks.keySet();

        if (termFuture) {
            System.out.println("Zadanie przyszłe");
            printFutureZone(tasks, nowData, key);
        } else {
            System.out.println("Zadanie przeszłe");
            printPastZone(tasks, nowData, key);
        }
    }

    private void printPastZone(Map<ZonedDateTime, Task> tasks, ZonedDateTime nowData, Set<ZonedDateTime> key) {
        for (ZonedDateTime zonedDateTime : key) {
            LocalDateTime lDT = zonedDateTime.toLocalDateTime();
            Duration duration = Duration.between(nowData, zonedDateTime);
            int i = 0;
            if (duration.getSeconds() < 0) {
                System.out.println(++i + ". - " + lDT + " - " + tasks.get(zonedDateTime).getDescription());
            }
        }
    }


    private void printFutureZone(Map<ZonedDateTime, Task> tasks, ZonedDateTime nowData, Set<ZonedDateTime> key) {

        int i24h = 0;

        System.out.println("Zadanie do wykonanie w ciągu 24 godzin");
        for (ZonedDateTime zonedDateTime : key) {
            LocalDateTime lDT = zonedDateTime.toLocalDateTime();
            Duration duration = Duration.between(nowData, zonedDateTime);
            if (duration.toSeconds() >= 0 && duration.toHours() <= 24) {
                System.out.println(++i24h + ". - " + lDT + " - " + tasks.get(zonedDateTime).getDescription());
            }
        }

        System.out.println("Zadanie do wykonanie w ciągu 30 dni");
        int i30d = 0;
        for (ZonedDateTime zonedDateTime : key) {
            LocalDateTime lDT = zonedDateTime.toLocalDateTime();
            Duration duration = Duration.between(nowData, zonedDateTime);
            if (duration.toSeconds() >= 0 && duration.toDays() <= 30) {

                System.out.println(++i30d + ". - " + lDT + " - " + tasks.get(zonedDateTime).getDescription());
            }
        }
        System.out.println("Zadania do wykonanie w tym  tygodniu");
        System.out.println(nowData.getDayOfWeek());

        for (ZonedDateTime zonedDateTime : key) {
            LocalDateTime lDT = zonedDateTime.toLocalDateTime();
            Duration duration = Duration.between(nowData, zonedDateTime);

            int lastDay = 0;
            switch (lDT.getDayOfWeek()) {
                case MONDAY:
                    lastDay = 6;
                    break;
                case TUESDAY:
                    lastDay = 5;
                    break;
                case WEDNESDAY:
                    lastDay = 4;
                    break;
                case THURSDAY:
                    lastDay = 3;
                    break;
                case FRIDAY:
                    lastDay = 2;
                    break;
                case SATURDAY:
                    lastDay = 1;
                    break;
                case SUNDAY:
                    lastDay = 0;
                    break;
            }

            System.out.println(zonedDateTime.getDayOfWeek() + "    " + (duration.toDays()) + "   " + lastDay);
            if (duration.toSeconds() >= 0 && duration.toDays() <= lastDay) {
                System.out.println(++i30d + ". - " + lDT + " - " + tasks.get(zonedDateTime).getDescription());
            }
        }
    }
}