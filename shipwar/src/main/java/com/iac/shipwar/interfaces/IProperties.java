package com.iac.shipwar.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Map;

public interface IProperties {

    default Component background_(Component component_, String hex) {
        component_.setBackground(Color.decode(hex));
        return component_;

    }

    default Component size_(Component component_, int width, int height) {
        component_.setSize(width, height);
        return component_;
    }

    default Component font_(Component component_, Map<String, String> style) {
        Font f;
        if (!Boolean.parseBoolean(style.get("set").toLowerCase())) {
            f = new Font("Arial", getFontStyle(""), 20);
        } else {
            f = new Font(style.get("family"), getFontStyle(style.get("type")), Integer.parseInt(style.get("size")));
        }
        component_.setFont(f);
        component_.setForeground(Color.decode(style.get("color")));

        return component_;
    }


    private int getFontStyle(String f) {
        switch (f.toUpperCase()) {
            case "BOLD":
                return Font.BOLD;
            case "ITALIC":
                return Font.ITALIC;
            default:
                return Font.PLAIN;
        }
    }

}
