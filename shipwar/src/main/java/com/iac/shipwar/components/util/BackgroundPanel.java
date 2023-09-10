package com.iac.shipwar.components.util;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int imageWidth = backgroundImage.getWidth(this);
            int imageHeight = backgroundImage.getHeight(this);

            double scaleWidth = (double) panelWidth / imageWidth;
            double scaleHeight = (double) panelHeight / imageHeight;
            double scale = Math.max(scaleWidth, scaleHeight);

            int newImageWidth = (int) (imageWidth * scale);
            int newImageHeight = (int) (imageHeight * scale);

            int x = (panelWidth - newImageWidth) / 2;
            int y = (panelHeight - newImageHeight) / 2;

            g.drawImage(backgroundImage, x, y, newImageWidth, newImageHeight, this);

        }
    }
}
