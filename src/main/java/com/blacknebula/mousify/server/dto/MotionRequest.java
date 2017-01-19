package com.blacknebula.mousify.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author hazem
 */
@AllArgsConstructor
@NoArgsConstructor
public class MotionRequest {
    @Getter
    @Setter
    private int dx;

    @Getter
    @Setter
    private int dy;
}
