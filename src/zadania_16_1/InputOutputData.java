package zadania_16_1;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class InputOutputData {

    ZonedDateTime nowD = ZonedDateTime.now();
    Map<ZonedDateTime, Task> tasks;
    private String pattern1 = "yyyy-MM-dd HH:mm"; // 2018-10-31 09:00
    private String pattern2 = "dd.MM.yyyy HH:mm"; //27.12.2019 19:00
    private String zone = "Europe/Warsaw";
    private ZoneId zdt = ZoneId.of(zone);
    private DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(pattern1);
    private DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(pattern2);


    public Map<ZonedDateTime, Task> readData() throws IOException {
        tasks = new TreeMap<>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("data.csv ");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        BufferedReader buffRead = new BufferedReader(fileReader);
        String line = null;

        while ((line = buffRead.readLine()) != null) {
            String[] split = line.split(";");
            ZonedDateTime id = dateToZoneDateTime(getLocalDateTime(split[0]));
            String description = split[1];
            boolean finish = Boolean.valueOf(split[2]);
            tasks.put(id, new Task(id, description, finish));
        }
        buffRead.close();
        //  System.out.println(task);
        return tasks;
    }


    public Map inputNewDate() {

        Task task;
        Scanner scanner = new Scanner(System.in);
        LocalDateTime tascEndDateTime = null;
        boolean ok = false;
        ZonedDateTime zonedEndDateTime;
        do {

            do {
                System.out.println("Podaj datę w realizacji zadania w formacie: " + pattern1);
                String userInput = scanner.nextLine();
                tascEndDateTime = getLocalDateTime(userInput);
            } while (tascEndDateTime == null);

            zonedEndDateTime = ZonedDateTime.of(tascEndDateTime, zdt);
            System.out.println("Podaj trześć zadania");
            String taskDescription = scanner.nextLine();
            task = new Task(zonedEndDateTime, taskDescription);
            tasks.put(zonedEndDateTime, task);
            System.out.println("Czy chcesz wpisać kolejne zadanie (T/N)");

        } while (!scanner.nextLine().toUpperCase().equals("N"));
        return tasks;
    }
//        if (tascEndDateTime == null) return;


//        Duration duration = Duration.between(tascEndDateTime, nowD);
//        System.out.println("Duration " + duration);
//
//        long hours = duration.toHours();
//        System.out.println("godziny ,," + hours);
//
//        LocalDate localDateTask = tascEndDateTime.toLocalDate();
//
//        LocalDate localDatenowD = nowD.toLocalDate();
//        Period period = Period.between(localDateTask, localDatenowD);
//        System.out.println("Period " + period);
//
//        int dats = period.getDays();
//        System.out.println("Ilość dni" + dats);
//        //    ZonedDateTime nowDateTime = ZonedDateTime.of(nowD.getYear(), nowD.getMonthValue(), nowD.getDayOfMonth(), nowD.getHour(), nowD.getMinute(), nowD.getSecond(), 0, zdt);
////        Duration PT44H40M36.0565702S
////        end  2019-03-01T15:00+01:00[Europe/Warsaw]
////        Now 2019-03-03T11:40:36.056570200+01:00[Europe/Belgrade]
//
//        //    ZonedDateTime aa = ZonedDateTime.of(2018, 10, 27, 19, 0, 0, 0, );
//        //  System.out.println("NOW  " + nowDateTime);
//        System.out.println("end  " + zonedEndDateTime);
//        System.out.println("Now " + nowD);
//
//        System.out.println("Dzień tygodnia " + nowD.getDayOfWeek());
//        //System.out.println("Podaj datę realizacji zadania: " + tascEndDateTime);
//
//        // System.out.println("Podaj liczbę godzin");
//
//        // int hours = scanner.nextInt();
//
//
//        // System.out.println("Data po tym czasie to: " + zdt);


    private ZonedDateTime dateToZoneDateTime(LocalDateTime id) {
        return ZonedDateTime.of(id, zdt);
    }


    private LocalDateTime getLocalDateTime(String userInput) {
        LocalDateTime tascEndDateTime = null;
        try {
            tascEndDateTime = LocalDateTime.parse(userInput, formatter1);
        } catch (DateTimeParseException e) {
            try {
                tascEndDateTime = LocalDateTime.parse(userInput, formatter2);
            } catch (DateTimeParseException e2) {
                System.out.println("Nie kumam - mały plagiat :(");
                return null;
            }
        }
        return tascEndDateTime;
    }
}



