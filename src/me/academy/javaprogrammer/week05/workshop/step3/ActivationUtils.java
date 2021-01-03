package me.academy.javaprogrammer.week05.workshop.step3;

import java.time.*;

public class ActivationUtils {

    public static String durationToDHMS(Duration d) {
        long days = d.toDays();
        Duration s = d.minus(Duration.ofDays(days));
        long hours = s.toHours();
        s = s.minus(Duration.ofHours(hours));
        long mins = s.toMinutes();
        long secs = s.minus(Duration.ofMinutes(mins)).getSeconds();

        return days + " zile, " +
                hours + " ore, " +
                mins + " minute si " +
                secs + " secunde";
    }

    /**
     *  Activation[] generateActivationSeries(LocalDateTime start, LocalDateTime end, Duration d)
     *      + it returns an array of consecutive Activation objects which cover the entire period between start and end;
     *      + every Activation will have duration equal to d minus one second - the next activation starts one second after the previous activation ended;
     *      + the last activation in the series will be less or equal the duration d
    */
    public static Activation[] generateActivationSeries(LocalDateTime start, LocalDateTime end, Duration d) {
        // the size of returned array can be as much as the result of dividing the duration between start and end against the duration of an activation plus 1
        Duration seriesDuration = Duration.between(start, end);
        int size = (int) (seriesDuration.getSeconds() / d.getSeconds()) + 1; // number of possible activation sessions

        // allocate array for max possible activation sessions
        Activation[] tempSeries = new Activation[size];

        // we will begin from start and use a cursor to move every Duration d forward until or past end inside loop
        LocalDateTime cursorStart = LocalDateTime.of(start.toLocalDate(), start.toLocalTime());
        // count valid activations; used also as index to store the valid activation object into tempSeries array with each increment
        int count = 0;
        // start of loop
        while(true) {
            // if start cursor falls on a Sunday then set it to next day Monday 00:00 AM
            if (cursorStart.getDayOfWeek() == DayOfWeek.SUNDAY) {
                cursorStart = LocalDateTime.of(cursorStart.toLocalDate().plusDays(1), LocalTime.of(0,0));
            }
            // set cursor end with Duration d minus 1 sec forward from cursorStart
            LocalDateTime cursorEnd = cursorStart.plus(d).minusSeconds(1);
            // if cursor got at or past the end then break out of loop
            if (end.isBefore(cursorEnd)) {
                // before terminating loop, test and store the last possible activation if valid
                Activation lastActivation = Activation.getInstance(cursorStart, end);
                if (lastActivation != null) {
                    tempSeries[count++] = lastActivation;
                }
                break;
            }
            // if cursor did not reach end
            // test and store the possible activation between start and end cursors if valid
            Activation cursorActivation = Activation.getInstance(cursorStart, cursorEnd);
            if (cursorActivation != null) tempSeries[count++] = cursorActivation;

            // move cursor start just past end cursor
            cursorStart = cursorStart.plus(d);
        }

        // copy the stored activations into separate array
        Activation[] outSeries = new Activation[count];
        System.arraycopy(tempSeries, 0, outSeries, 0, count);

        return outSeries;
    }

    /**
     *  * Activation[] generateRandomActivations(int count, Duration minDuration, Duration maxDuration, LocalDate minDate, LocalDate maxDate)
     *      - returns an array of count activations generated randomly as follows
     *          + each activation will have a duration randomly generated between minDuration and maxDuration
     *          + each activation must span between minDate and maxDate; that is between minDate 00:00:00 and maxDate 23:59:59
     *          + activations must not overlap
     */

    public static Activation[] generateRandomActivations(int count, Duration minDuration, Duration maxDuration, LocalDate minDate, LocalDate maxDate) {
        Activation[] out = new Activation[count];

        // set maximum LocalDateTime
        LocalDateTime maxDateTime = LocalDateTime.of(maxDate, LocalTime.of(23,59,59));
        for(int i = 0; i < count; i++) {
            while (out[i] == null) {
                // generate random start date
                Period diffPeriod = Period.between(minDate, maxDate);
                int diffDays = diffPeriod.getDays();
                int startDayOffset = (int)(Math.random() * diffDays + 1);
                int startHour = (int)(Math.random() * 24);
                int startMinute = (int)(Math.random() * 60);
                int startSecond = (int)(Math.random() * 60);
                LocalDateTime randomStart = LocalDateTime.of(minDate.plusDays(startDayOffset), LocalTime.of(startHour, startMinute, startSecond));

                // generate random duration
                Duration diffDuration = maxDuration.minus(minDuration);
                Duration randomDuration = minDuration.plus(Duration.ofSeconds((int)(Math.random() * diffDuration.getSeconds() + 1)));

                // if random start date plus random duration goes past maxDate then generate again
                if (maxDateTime.isBefore(randomStart.plus(randomDuration))) continue;

                // else we can generate an Activation object and save to array
                out[i] = Activation.getInstance(randomStart, randomDuration);

                // if this random object overlaps previous activations then remove it from array
                for (int j = 0; j < i; j++) {
                    if (out[i].overlaps(out[j])) {
                        out[i] = null;
                        break;
                    }
                }
            }
        }

        return out;
    }

    public static void main(String[] args) {
        // test durationToDHMS()
        System.out.println("Testing durationToDHMS()");
        Duration d = Duration.ofDays(2).plusHours(3).plusMinutes(15).plusSeconds(35);
        System.out.println(durationToDHMS(d));

        // test generateActivationSeries()
        System.out.println("Testing generateActivationSeries()");
        Activation[] testSeries = generateActivationSeries(LocalDateTime.now(), LocalDateTime.now().plusDays(11), Duration.ofHours(21));
        for (Activation t : testSeries) {
            System.out.println(t);
        }

        // test generateRandomActivations()
        System.out.println("Testing generateRandomActivations()");
        Activation[] testRandom = generateRandomActivations(4, Duration.ofHours(1), Duration.ofHours(3), LocalDate.now(), LocalDate.now().plusDays(5));
        for (Activation t : testRandom) {
            System.out.println(t);
        }
    }
}
