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
public class ScreenSize {
    @Getter
    @Setter
    private int width;

    @Getter
    @Setter
    private int height;
}
