package com.iac.shipwar.UI.widgets;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.iac.shipwar.models.enums.FontType;
import com.iac.shipwar.models.enums.AlingText;

public class Text_ {
    private JLabel label_;
    private String content;
    private final double factor = 1.5;
    private int fontSize_ = 18;
    private int fontType = FontType.PLAIN.getValue();
    private String fontFamily = "Arial";
    private String colorHex = "#000000";
    private String bg;
    private int align = SwingConstants.LEFT;
    private int width;
    private int height = (int) (this.fontSize_ * this.factor);

    public Text_(String t, int width) {
        this.content = t;
        this.width = (int) width;
        this.label_ = new JLabel(this.content);
        default_();
    }

    private void default_() {
        this.label_.setForeground(Color.decode(this.colorHex));
        fontUpdate();
    }

    private void fontUpdate() {
        this.label_.setFont(new Font(this.fontFamily, this.fontType, this.fontSize_));
        this.label_.setPreferredSize(new Dimension(this.width, this.height));
        this.label_.setHorizontalAlignment(this.align);
    }

    public void setAling(AlingText p) {
        this.align = p.get();
        fontUpdate();
    }

    public void setBackground_(String hex) {
        this.bg = hex;
        label_.setBackground(Color.decode(this.bg));
        label_.setOpaque(true);

    }

    public void setText(String t) {
        this.content = t;
        this.label_.setText(this.content);
    }

    public void setColor(String h) {
        this.colorHex = h;
        this.label_.setForeground(Color.decode(this.colorHex));
    }

    public void setSize(int s) {
        this.fontSize_ = (int) s;
        this.height = (int) (this.fontSize_ * this.factor);
        fontUpdate();
    }

    public void setType(FontType f) {
        this.fontType = f.getValue();
        fontUpdate();

    }

    public void setFamily(String f) {
        this.fontFamily = f;
        fontUpdate();
    }

    public void setWidth(int w) {
        this.width = (int) w;
        fontUpdate();
    }

    public void setHeight(int h) {
        this.height = (int) h;
        fontUpdate();
    }

    public JLabel getLabel() {
        return this.label_;
    }

}
