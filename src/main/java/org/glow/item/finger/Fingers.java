package org.glow.item.finger;

import java.util.ArrayList;
import java.util.List;

public class Fingers {

    private static final Fingers fingers = new Fingers();

    private final List<Finger> fingerList = new ArrayList<>();

    private Fingers() {

    }

    public static Fingers getFingers() {
        return fingers;
    }

    public List<Finger> getFingerList() {
        return fingerList;
    }
}
