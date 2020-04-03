package window;

import helper.MoveFromBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Win extends JFrame implements ActionListener {

    private JButton boxToBag = new JButton("取出芜菁");
    private JButton bagToBox = new JButton("放入芜菁");
    private JButton saleInShop = new JButton("商店出售");
    JPanel rootPanel = new JPanel();
    MoveFromBox moveFromBox = new MoveFromBox();

    public Win() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,230,400);
        this.add(rootPanel);
        boxToBag.setBounds(20, 20, 180, 45);
        bagToBox.setBounds(20, 90, 180, 45);
        saleInShop.setBounds(20, 160, 180, 45);
        rootPanel.setLayout(null);
        rootPanel.add(boxToBag);
        rootPanel.add(bagToBox);
        rootPanel.add(saleInShop);
        boxToBag.addActionListener(this);
        bagToBox.addActionListener(this);
        saleInShop.addActionListener(this);
        this.setAlwaysOnTop(true);
        setVisible(true);
        moveFromBox.initBagAndBox();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boxToBag) {
            System.out.println("boxToBag event");
            try {
                moveFromBox.moveFromBoxToBag();
            } catch (InterruptedException | AWTException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == bagToBox) {
            try {
                moveFromBox.moveFromBagToBox();
            } catch (InterruptedException | AWTException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == saleInShop) {
            try {
                moveFromBox.saleInShop();
            } catch (AWTException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
