package org.glow.item.lefthand;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.glow.item.Shield;

@JsonDeserialize(as = LeftHandCreated.class)
public interface LeftHand extends Shield {

}
