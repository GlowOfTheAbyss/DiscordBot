package org.glow.item.neck;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.glow.item.Items;

@JsonDeserialize(as = NeckCreated.class)
public interface Neck extends Items {
}
