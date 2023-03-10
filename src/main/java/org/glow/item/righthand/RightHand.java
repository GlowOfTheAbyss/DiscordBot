package org.glow.item.righthand;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.glow.item.Weapon;

@JsonDeserialize(as = RightHandCreated.class)
public interface RightHand extends Weapon {

}
