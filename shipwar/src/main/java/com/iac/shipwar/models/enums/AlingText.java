package com.iac.shipwar.models.enums;

import javax.swing.SwingConstants;

public enum AlingText {
    CENTER(SwingConstants.CENTER),
    LEFT(SwingConstants.LEFT),
    RIGHT(SwingConstants.RIGHT);

    private final int alignmentConstant;

    AlingText(int alignmentConstant) {
        this.alignmentConstant = alignmentConstant;
    }

    public int get() {
        return alignmentConstant;
    }
    
}
