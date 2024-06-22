package Projects.GUI.GUI_URL_Library.src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;


public class WelcomePage extends JFrame implements ActionListener {

    JFrame f = new JFrame("UR Library");

    JLabel title = new JLabel();

    JButton enterButton = new JButton("ENTER");
    JButton decoy = new JButton();

    WelcomePage() {

        //sets an inmage as the bacground of the frame.
        try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File ("C:\\Users\\Jace\\Documents\\NetBeansProjects\\GUI_URL_Library\\images\\WelcomePageBG.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //coordinates of the button, adding action listener, and setting focuable to false.
        enterButton.setBounds(410, 320, 200, 90);
        enterButton.addActionListener(this);
        enterButton.setFocusable(false);

        //adding the components to the frame
        f.add(enterButton);

        f.add(decoy);
        decoy.setVisible(false);

        //setting the attributes of the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1000,562);
        f.setLayout(null);
        f.setLocation(480, 200);
        f.setResizable(false);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //enters the Library and processes the data
        if(e.getSource() == enterButton) {
            new DataProcess();
            new LibraryPage();
            f.dispose();
        }
    }
    
}
