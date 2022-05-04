package se.liu.thebo717.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class StartscreenViewer extends JComponent
{
    final URL url = ClassLoader.getSystemResource("images/IMG_1271.jpeg");
    final ImageIcon img = new ImageIcon(url);
    final JFrame frame = new JFrame("Graphics2D Test");

    public JFrame getFrame() {
	return frame;
    }

    public void paintComponent(final Graphics g) {
	final Graphics2D g2d = (Graphics2D) g;
	g2d.setRenderingHint(	RenderingHints.KEY_ANTIALIASING,
				     RenderingHints.VALUE_ANTIALIAS_ON);
	final AffineTransform old = g2d.getTransform();
	final AffineTransform at = AffineTransform.getScaleInstance(0.5, 0.5);
	g2d.setTransform(at);
	img.paintIcon(this, g, 0, 0);
	g2d.setTransform(old);
    }

    public void show(){
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setLayout(new GridLayout(1,1));
	frame.add(new StartscreenViewer());
	frame.setSize(1000, 3000);
	frame.setVisible(true);
    }
}

