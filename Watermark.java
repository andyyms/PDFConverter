package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Watermark {

    // ADD TEXTUAL WATERMARK FOR AN IMAGE
    static void addTextWatermark(String text, File sourceImageFile, File destImageFile) throws IOException {
        try {
            BufferedImage sourceImage = ImageIO.read(sourceImageFile);
            Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();

            // initializes necessary graphic properties
            AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f); //0.1f
            g2d.setComposite(alphaChannel);
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 64));
            g2d.rotate(45 * Math.PI / 180);
            FontMetrics fontMetrics = g2d.getFontMetrics();
            Rectangle2D rect = fontMetrics.getStringBounds(text, g2d);

            // calculates the coordinate where the String is painted
            int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
            int centerY = sourceImage.getHeight() / 2;

            // ADDED BY ANDY, FOR REPEAT WATERMARK
            double xFactor = Math.cos(45 * Math.PI / 180);
            double yFactor = Math.sin(45 * Math.PI / 180);

            for (float x=0; x < sourceImage.getWidth(); x += (float) (rect.getWidth() * xFactor + rect.getHeight() * yFactor)) {
                // paints the textual watermark
                for (float y = (float) (-sourceImage.getWidth() * yFactor); y < sourceImage.getHeight(); y += (float) (rect.getWidth() * yFactor + rect.getHeight() * xFactor)) {
                    g2d.drawString(text, x, y);
                }
            }
            // ADDED END

            ImageIO.write(sourceImage, "png", destImageFile);
            g2d.dispose();

            System.out.println("The text watermark is added to the image.");

        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
