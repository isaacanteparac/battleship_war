package com.iac.shipwar.components;

import java.awt.*;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;

public class Options_<T extends Enum<T>> {

    protected JComboBox<T> boxOptions;
    protected String bg = "#FFFFFF";
    protected String fontColor = "#000000";
    protected int width = 60;//60
    protected int height = 30;//30
    protected int rounded = 20;

    public Options_(Class<T> enumClass) {
        T[] opciones = enumClass.getEnumConstants();
        this.boxOptions = new JComboBox<>(opciones);
        default_();

        size();
        
    }

    private void size(){
        this.boxOptions.setOpaque(false);
        this.boxOptions.setPreferredSize(new Dimension(width, height));

    }

    private void default_() {
         boxOptions.setBackground(Color.decode(this.bg));
        this.boxOptions.setForeground(Color.decode(this.fontColor));
        this.boxOptions.setBorder(null);

        Font font = this.boxOptions.getFont();

        this.boxOptions.setFont(new Font(font.getFontName(), Font.PLAIN, 18));
    }

    public JComboBox<T> getComboBox() {
        return this.boxOptions;
    }
}
