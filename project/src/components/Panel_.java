package components;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Panel_ {
    private JPanel panel;
    protected int width;
    protected int height;
    protected String colorHex;
    protected int radius;
    protected int padding;

    public Panel_(int w, int h, int p, String hex, int alpha) {
        this.width = w;
        this.height = h;
        this.padding = p;
        this.colorHex = hex;

        // Inicializa un JFXPanel (necesario para usar JavaFX en Swing)
       

        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color colorWithAlpha = new Color(
                        Integer.parseInt(colorHex.substring(1, 3), 16),
                        Integer.parseInt(colorHex.substring(3, 5), 16),
                        Integer.parseInt(colorHex.substring(5, 7), 16),
                        alpha
                );
                g2d.setColor(colorWithAlpha);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                g2d.dispose();
            }
        };

        size();
    }

    private void size() {
        this.panel.setOpaque(false);
        this.panel.setPreferredSize(new Dimension(this.width, this.height));
    }

    public void setPadding(int size) {
        this.padding = size;
    }

    public Dimension getSizeWidthComponent(int heightComponent) {
        int widthComponent = this.width - this.padding;
        return new Dimension(widthComponent, heightComponent);
    }

    public void radiusBorder(int r) {
        this.radius = r;
        panel.repaint();
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void addComponent(Component c) {
        this.panel.add(c);
    }
}
