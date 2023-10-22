
package com.iac.shipwar.UI.widgets;

/**
 *
 * @author isaac
 */
import javax.swing.*;

import com.iac.shipwar.models.dataclass.PanelCharacteristic;
import com.iac.shipwar.models.enums.SubcomponentOrientation;

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

    public void changeColorBorder(String h) {
            panel.setBorder(BorderFactory.createLineBorder(Color.decode(h), 5));
    }

    public void noBorder(){
        panel.setBorder(null);
    }
    

    private void size() {
        this.panel.setOpaque(false);
        this.panel.setPreferredSize(new Dimension(this.characteristic.width(), this.characteristic.height()));
    }

    public void setWidth(int width) {
        this.panel.setPreferredSize(new Dimension(width, this.characteristic.height()));
    }

    public void setHeight(int height) {
        this.panel.setPreferredSize(new Dimension(this.characteristic.width(), height));
    }

    public int getWidth() {
        return this.characteristic.width();
    }

    public int getSizeWidthComponent() {
        int widthComponet = this.characteristic.width() - this.characteristic.padding();
        return widthComponet;
    }

    public JPanel getPanel() {
        return this.panel;
    }

    public void activateGrip(SubcomponentOrientation o) {
        this.panel.setLayout((o == SubcomponentOrientation.CENTER) ? new GridBagLayout()
                : (o == SubcomponentOrientation.HORIZONTAL_WH)
                        ? new FlowLayout(FlowLayout.CENTER, this.characteristic.gap(), this.characteristic.gap())
                        : (o == SubcomponentOrientation.HORIZONTAL_W)
                                ? new FlowLayout(FlowLayout.CENTER, this.characteristic.gap(), 0)
                                : (o == SubcomponentOrientation.HORIZONTAL_H)
                                        ? new FlowLayout(FlowLayout.CENTER, 0, this.characteristic.gap())
                                        : (o == SubcomponentOrientation.VERTICAL_LEFT)
                                                ? new FlowLayout(FlowLayout.LEFT)
                                                : (o == SubcomponentOrientation.VERTICAL)
                                                        ? new FlowLayout(FlowLayout.CENTER, this.characteristic.gap(),
                                                                this.characteristic.gap())
                                                        : null);

    }

    public void addComponent(Component component) {
        this.panel.add(component);
    }

    public void visible(boolean v) {
        this.panel.setVisible(v);
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
