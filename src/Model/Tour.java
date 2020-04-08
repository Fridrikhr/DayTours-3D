package Model;

public class Tour {
    private int id;
    private String text;
    private String guide;
    private String description;
    private int length;
    private int avgRating;
    private String date;

    public Tour(int id, String text, String guide, String description, int length, int avgRating, String date)
    {
        this.id = id;
        this.text = text;
        this.guide = guide;
        this.description = description;
        this.length = length;
        this.avgRating = avgRating;
        this.date = date;
    }

    public String getText() {
        return this.text;
    }

    public String getGuideId() {
        return this.guide;
    }

    public String getDescription() {
        return this.description;
    }

    public int getLength() {
        return this.length;
    }

    public int getAvgRating() {
        return this.avgRating;
    }

    public String getDate() {
        return this.date;
    }
}
