package com.iac.shipwar.models.enums;

import java.awt.Font;

public enum FontType {
    PLAIN(Font.PLAIN),
    BOLD(Font.BOLD),
    ITALIC(Font.ITALIC);

    private final int style;

    FontType(int style) {
        this.style = style;
    }

    public int getValue() {
        return style;
    }
}
