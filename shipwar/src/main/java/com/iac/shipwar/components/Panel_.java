
package com.iac.shipwar.components;

/**
 *
 * @author isaac
 */
import javax.swing.*;

import com.iac.shipwar.data.PanelCharacteristic;
import com.iac.shipwar.data.enums.Orientation;

import java.awt.*;

public class Panel_ {
    private JPanel panel;
    protected PanelCharacteristic characteristic;

    public Panel_(PanelCharacteristic characteristic) {
        this.characteristic = characteristic;

        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color colorWithTransparent = new Color(
                        Integer.parseInt(characteristic.colorHex().substring(1, 3), 16),
                        Integer.parseInt(characteristic.colorHex().substring(3, 5), 16),
                        Integer.parseInt(characteristic.colorHex().substring(5, 7), 16),
                        characteristic.transparency());
                g2d.setColor(colorWithTransparent);
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), characteristic.rounded(), characteristic.rounded());
                g2d.dispose();
            }
        };

        this.size();
    }

    private void size() {
        this.panel.setOpaque(false);
        this.panel.setPreferredSize(new Dimension(this.characteristic.width(), this.characteristic.height()));
    }

    public int getSizeWidthComponent() {
        int widthComponet = this.characteristic.width() - this.characteristic.padding();
        return widthComponet;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void activateGrip(Orientation o) {
        this.panel.setLayout((o == Orientation.CENTER) ? new GridBagLayout()
                : (o == Orientation.HORIZONTAL_WH)
                        ? new FlowLayout(FlowLayout.CENTER, this.characteristic.gap(), this.characteristic.gap())
                        : (o == Orientation.HORIZONTAL_W)
                                ? new FlowLayout(FlowLayout.CENTER, this.characteristic.gap(), 0)
                                : (o == Orientation.HORIZONTAL_H)
                                        ? new FlowLayout(FlowLayout.CENTER, 0, this.characteristic.gap())
                                        : (o == Orientation.VERTICAL_LEFT)
                                        ? new FlowLayout(FlowLayout.LEFT)
                                        : null);

    }

    public void addComponent(Component component) {
        this.panel.add(component);
    }


    public GridBagConstraints spacer() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = characteristic.Xposition();
        gbc.gridy = characteristic.Yposition();
        gbc.insets = new Insets(characteristic.gap(), characteristic.gap(), characteristic.gap(), characteristic.gap());
        characteristic.container().add(this.panel, gbc);
        return gbc;
    }

}
