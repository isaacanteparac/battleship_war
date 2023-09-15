package com.iac.shipwar.interfaces;

import java.awt.Color;
import java.awt.Component;


public interface IProperties {

    default Component background_(Component component_, String hex) {
        component_.setBackground(Color.decode(hex));
        return component_;

    }

    default Component size_(Component component_, int width, int height) {
        component_.setSize(width, height);
        return component_;
    }

}
