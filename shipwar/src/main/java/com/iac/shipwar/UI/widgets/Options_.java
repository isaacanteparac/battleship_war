package com.iac.shipwar.UI.widgets;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JComboBox;

import com.iac.shipwar.models.enums.SubcomponentOrientation;

public class Options_<T extends Enum<T>> {

    protected JComboBox<T> boxOptions;
    protected String bg = "#FFFFFF";
    protected String fontColor = "#000000";
    protected int width = 60;// 60
    protected int height = 30;// 30
    protected int rounded = 20;

    public void enumList(ArrayList<T> enumList) {
        T[] opciones = enumList.toArray((T[]) new Enum[0]);
        this.boxOptions = new JComboBox<>(opciones);
        default_();

    }

    public void pureEnum(Class<T> enumClass) {
        T[] opciones = enumClass.getEnumConstants();
        this.boxOptions = new JComboBox<>(opciones);
        default_();

    }

    public void addPanel(Panel_ panel) {
        panel.addComponent(this.boxOptions);
        panel.activateGrip(SubcomponentOrientation.CENTER);
    }

    private void size() {
        this.boxOptions.setOpaque(false);
        this.boxOptions.setPreferredSize(new Dimension(width, height));
    }

    public void setWidth(int width) {
        this.width = width;
        this.boxOptions.setPreferredSize(new Dimension(width, height));
    }

    public void setHeight(int height) {
        this.height = height;
        this.boxOptions.setPreferredSize(new Dimension(width, height));
    }

    private void default_() {
        size();
        boxOptions.setBackground(Color.decode(this.bg));
        this.boxOptions.setForeground(Color.decode(this.fontColor));
        this.boxOptions.setBorder(null);
        Font font = this.boxOptions.getFont();
        this.boxOptions.setFont(new Font(font.getFontName(), Font.PLAIN, 18));
    }

    public void deleteAllItems() {
        this.boxOptions.removeAllItems();

    }

    public void setOptions(ArrayList<T> enumList) {
        T[] opciones = enumList.toArray((T[]) new Enum[0]);
        this.boxOptions.removeAllItems();
        for (T option : opciones) {
            this.boxOptions.addItem(option);
        }
    }

    public JComboBox<T> getComboBox() {
        return this.boxOptions;
    }

    public void setEnabled(boolean enabled) {
        this.boxOptions.setEnabled(enabled);
    }

}
