package org.glow.item;

import org.glow.item.body.Body;
import org.glow.item.finger.Finger;
import org.glow.item.head.Head;
import org.glow.item.legs.Legs;
import org.glow.item.neck.Neck;

public class NoneArmor implements Items, Armor, Body, Finger, Head, Legs, Neck {


    @Override
    public int getArmor() {
        return 0;
    }

    @Override
    public String getName() {
        return "Пусто";   }

    @Override
    public int getPrice() {
        return 0;
    }


}
