/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.fondos;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author usuario
 */
public class LogoCliente extends JPanel {

    private Image image;

    @Override
    public void paint(Graphics g) {
        image = new ImageIcon(getClass().getResource("/img/cliente (1).png")).getImage();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

        setOpaque(false);

        super.paint(g);
    }
}
