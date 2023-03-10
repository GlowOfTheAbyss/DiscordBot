package org.glow.location;

public class Location {

    protected String name;
    protected String image;

    public Location() {
    }

    public Location(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

}
