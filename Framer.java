import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
public class Framer {

    public static JFrame method(JFrame f, String a) throws IOException {
    	File x = new File(a);
    	LayoutManager FlowLayout = null;
		f.getContentPane().setLayout(FlowLayout);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(600, 600);
		BufferedImage img = ImageIO.read(x);
    	f.setContentPane(new JLabel(new ImageIcon(img)));
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		return f;
    }
    
    public static JButton setButtonP (JButton a, String b, String c, String d, int e, int f) throws IOException {
    	File x, y, z;
    	x = new File(b);
    	y = new File(c);
    	z = new File(d);
    	BufferedImage image, image_rollover, image_selected;
    	image = ImageIO.read(x);
    	image_rollover = ImageIO.read(y);
    	image_selected = ImageIO.read(z);
    	ImageIcon icon, icon_rollover, icon_selected;
    	icon = new ImageIcon(image);
    	icon_rollover = new ImageIcon(image_rollover);
    	icon_selected = new ImageIcon(image_selected);
    	a.setBounds(e, f, 200, 30);
    	a.setContentAreaFilled(false);
		a.setBorderPainted(false);
		a.setIcon(icon);
		a.setRolloverIcon(icon_rollover);
		a.setPressedIcon(icon_selected);
    	return a;
    }
}