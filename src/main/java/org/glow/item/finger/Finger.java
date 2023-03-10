package org.glow.item.finger;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.glow.item.Items;

@JsonDeserialize(as = FingerCreated.class)
public interface Finger extends Items {



}
