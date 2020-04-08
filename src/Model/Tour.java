package Model;

public class Tour {
    private int id;
    private String text;
    private int guideId;
    private String description;
    private int length;
    private int avgRating;
    private String date;

    public Tour(int id, String text, int guideId, String description, int length, int avgRating, String date)
    {
        this.id = id;
        this.text = text;
        this.guideId = guideId;
        this.description = description;
        this.length = length;
        this.avgRating = avgRating;
        this.date = date;
    }


    public String getText() {
        return this.text;
    }

    public int getGuideId() {
        return this.guideId;
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
