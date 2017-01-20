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
public class ClickEvent {
    public final static String ACTION_CLICK = "click";
    public final static String ACTION_DOWN = "down";
    public final static String ACTION_UP = "up";

    @Getter
    @Setter
    private boolean isLeft;

    @Getter
    @Setter
    private String action;
}
