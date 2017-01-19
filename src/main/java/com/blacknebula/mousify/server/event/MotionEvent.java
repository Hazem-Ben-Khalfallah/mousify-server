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
public class MotionEvent {
    @Getter
    @Setter
    private int dx;

    @Getter
    @Setter
    private int dy;
}
