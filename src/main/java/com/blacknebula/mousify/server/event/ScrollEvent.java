package com.blacknebula.mousify.server.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hazem
 */
@AllArgsConstructor
@NoArgsConstructor
public class ScrollEvent {
    @Getter
    @Setter
    private int amount;
}
