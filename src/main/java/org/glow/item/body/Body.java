package org.glow.item.body;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.glow.item.Armor;

@JsonDeserialize(as = BodyCreated.class)
public interface Body extends Armor {
}
