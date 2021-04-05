package entity;

import java.util.StringJoiner;

public class Show {
    private int showId;
    private String showTitle;
    private int numberOfSeasons;
    private int initialYear;

    public Show() {
        super();
    }

    public Show(int showId, String showTitle, int numberOfSeasons, int initialYear) {
        this.showId = showId;
        this.showTitle = showTitle;
        this.numberOfSeasons = numberOfSeasons;
        this.initialYear = initialYear;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public String getShowTitle() {
        return showTitle;
    }

    public void setShowTitle(String showTitle) {
        this.showTitle = showTitle;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getInitialYear() {
        return initialYear;
    }

    public void setInitialYear(int initialYear) {
        this.initialYear = initialYear;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ")
                .add(showId+"")
                .add(showTitle)
                .add(numberOfSeasons+"")
                .add(initialYear+"")
                .toString();
    }
}
