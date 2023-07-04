package org.glow.location;

import java.util.Objects;

public abstract class Location {

    private String name;
    private String image;

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!Objects.equals(name, location.name)) return false;
        return Objects.equals(image, location.image);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }
}
