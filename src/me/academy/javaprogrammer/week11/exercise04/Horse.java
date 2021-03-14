package me.academy.javaprogrammer.week11.exercise04;

import java.util.Objects;

final class Horse implements Comparable<Horse> {
    /**
     * we will use integer variable type to save odds
     * - an odds value of 101 corresponds to a 1.01 decimal odds expression
     * - if you bet 1$ on a horse with 1.01 decimal odds you win 1 cent
     */
    private final static int MIN_ODDS = 101;
    private final static int MAX_ODDS = 800;

    private final static int MIN_PERIOD = 1;    // min duration to travel 1% of total track length
    private final static int MAX_PERIOD = 200;  // max duration to travel 1% of total track length

    // each new instance will have an unique id as we increment ID
    private static int ID = 0;
    private final int id;

    private final String name;
    private final int odds;
    private int percentageOfRaceTrackTraveled;

    Horse(String name, int odds) {
        if (name == null) throw new HorseException("Null name.");
        if (name.trim().isEmpty()) throw new HorseException("Empty name.");
        if (odds < MIN_ODDS) throw new HorseException("Invalid odds.");
        if (odds > MAX_ODDS) throw new HorseException("Invalid odds.");

        this.name = name;
        this.odds = odds;
        this.id = ID++;
    }

    static Horse getRandomInstance(int index) {
        String name = "Horse" + index;
        int odds = (int)(Math.random() * (MAX_ODDS - MIN_ODDS) + MIN_ODDS) + 1;
        return new Horse(name, odds);
    }

    int race() {
        if (percentageOfRaceTrackTraveled < 0) throw new HorseException();
        if (percentageOfRaceTrackTraveled > 99) throw new HorseException();
        // with each method call the horse travels 1% of total distance
        percentageOfRaceTrackTraveled++;
        // returns how much time the horse spent to travel 1% of the track
        return (int)(Math.random() * (MAX_PERIOD - MIN_PERIOD) + MIN_PERIOD) + 1;
    }

    String[] getHorseAsTableRow() {
        String[] row = new String[2];
        row[0] = name;
        row[1] = String.valueOf(odds / 100.00);
        return row;
    }

    void moveToStartOfTrack() {
        percentageOfRaceTrackTraveled = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Horse)) return false;
        Horse horse = (Horse) o;
        return getId() == horse.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public synchronized int compareTo(Horse horse) {
        if (horse == null) throw new HorseException();
        int comparisonResult = horse.getPercentageOfRaceTrackTraveled() - this.percentageOfRaceTrackTraveled;
        return comparisonResult;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    int getOdds() {
        return odds;
    }

    int getPercentageOfRaceTrackTraveled() {
        return percentageOfRaceTrackTraveled;
    }
}
