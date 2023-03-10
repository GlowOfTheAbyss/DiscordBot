package org.glow.item.head;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.glow.item.Armor;

@JsonDeserialize(as = HeadCreated.class)
public interface Head extends Armor {}
