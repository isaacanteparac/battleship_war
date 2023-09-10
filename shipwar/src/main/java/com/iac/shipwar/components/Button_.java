package com.iac.shipwar.components;

import javax.swing.JButton;

import java.awt.*;

public class Button_ {
    protected JButton button;
    protected String text;
    protected String colorHex = "#FFFFFF";
    protected int width = 70;
    protected int height = width;
    protected int rounded = 45;

    public Button_() {
        this.button = new JButton(this.text) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.decode(colorHex));
                boolean isClicked = getModel().isPressed();
                int borderRadius = isClicked ?  (int)rounded/2: rounded;
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
                g2d.dispose();
            }
        };
        size();
    }

    private void size() {
        this.button.setOpaque(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        this.button.setPreferredSize(new Dimension(width, height));
    }

    public void setColorBg(String hex){
        this.colorHex = hex;
    }

    public void add(Component component) {
        this.button.setLayout(new GridBagLayout());
        this.button.add(component);
    }

    public JButton geButton() {
        return this.button;
    }
}
