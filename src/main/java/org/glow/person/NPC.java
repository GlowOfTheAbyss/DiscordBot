package org.glow.person;

import org.glow.magic.Element;

public abstract class NPC extends Person {

    private String image;

    private String name;

    private Element element;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }
}
