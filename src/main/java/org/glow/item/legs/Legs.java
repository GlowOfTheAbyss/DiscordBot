package org.glow.item.legs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.glow.item.Armor;

@JsonDeserialize(as = LegsCreated.class)
public interface Legs extends Armor {
}
