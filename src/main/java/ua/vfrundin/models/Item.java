package ua.vfrundin.models;

public class Item {
    private String id;
    public String name;
    public String description;
    public long create;
    private String[] comments;
    private int commentsPosition = 0;

    public Item(String name, String description, long create) {
        this.name = name;
        this.description = description;
        this.create = create;
        commentsPosition = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public long getCreate() {
        return this.create;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getComments() {
        return this.comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    public int getCommentsPosition() {
        return this.commentsPosition;
    }

    public void setCommentsPosition(int commentsPosition) {
        this.commentsPosition = commentsPosition;
    }
}
