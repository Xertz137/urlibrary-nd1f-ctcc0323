package Projects.GUI.GUI_URL_Library.src;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class LibraryPage extends JFrame implements ActionListener {
    
    //initializing the storages
    ArrayList<String> nameOfGame = new ArrayList<String>();
    ArrayList<String> urls = new ArrayList<String>();
    ArrayList<String> paths = new ArrayList<String>();
    
    //initializing the components
    //arrayist is also used for the components since we want to create these components based on the number of the contents in the file.
    ArrayList<JButton> buttonsAL = new ArrayList<JButton>();
    ArrayList<JLabel> labelsAL = new ArrayList<JLabel>(); 
    ArrayList<JPanel> panelsAL = new ArrayList<JPanel>();
    ArrayList<JPanel> subPanelsAL = new ArrayList<JPanel>();

    JFrame f = new JFrame("UR Library");

    JPanel lastPanel = new JPanel();
    JButton addButton = new JButton();

    LibraryPage () {

        //gets the data from the data process class and stores them locally so we can use them here.
        nameOfGame = DataProcess.gameNameAL;
        urls = DataProcess.urlsAL;
        paths = DataProcess.pathsAL;

        //sets an image as the background
         try {
            f.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File ("C:\\Users\\Jace\\Documents\\NetBeansProjects\\GUI_URL_Library\\images\\LibraryBackground.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //sets the attributes of the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(1400,800);
        f.setResizable(false);
        f.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 20));
        f.setLocation(300, 80);

        // adds panels, subpanels, buttons, and labels, based on the number of names
        for(int i = 0; i < nameOfGame.size(); i++) {
            JPanel panel = new JPanel();
            panelsAL.add(panel);

            JPanel subPanel = new JPanel();
            subPanelsAL.add(subPanel);
            
            JButton button = new JButton();
            buttonsAL.add(button);

            JLabel label = new JLabel(nameOfGame.get(i));
            labelsAL.add(label);
        }
        
        //sets dimension for the components
        Dimension panelSize = new Dimension(120, 150);
        Dimension buttonSize = new Dimension(120,120);
        Dimension subPanelSize = new Dimension(120, 30);

        //sets the attributes for the components.
        for(int i = 0; i < nameOfGame.size(); i++) {
            panelsAL.get(i).setLayout(new BorderLayout());
            panelsAL.get(i).setOpaque(false);
            panelsAL.get(i).setPreferredSize(panelSize);

            String pathname = paths.get(i);

            buttonsAL.get(i).setPreferredSize(buttonSize);
            buttonsAL.get(i).setIcon(new ImageIcon(pathname));
            buttonsAL.get(i).setContentAreaFilled(false);

            buttonsAL.get(i).addActionListener(this);
            panelsAL.get(i).add(buttonsAL.get(i), BorderLayout.NORTH);

            subPanelsAL.get(i).setLayout(new FlowLayout(FlowLayout.CENTER));
            subPanelsAL.get(i).setOpaque(false);
            subPanelsAL.get(i).setPreferredSize(subPanelSize);
            panelsAL.get(i).add(subPanelsAL.get(i), BorderLayout.SOUTH);

            labelsAL.get(i).setForeground(Color.WHITE);
            labelsAL.get(i).setFont(new Font("Palatino Linotype", Font.BOLD, 15));
            subPanelsAL.get(i).add(labelsAL.get(i));

            f.add(panelsAL.get(i));

        }
        //sets the attributes for the last panel
        lastPanel.setLayout(new BorderLayout());
        lastPanel.setOpaque(false);
        lastPanel.setPreferredSize(panelSize);

        //sets the attributes for the add button
        ImageIcon addButtIcon = new ImageIcon("C:\\Users\\Jace\\Documents\\NetBeansProjects\\GUI_URL_Library\\images\\Transparent_AddButton.png");
        addButton.setPreferredSize(buttonSize);
        addButton.setIcon(addButtIcon);
        addButton.setContentAreaFilled(false);
        addButton.setBorder(null);
        addButton.addActionListener(this);
        //adds the button into the panel
        lastPanel.add(addButton, BorderLayout.NORTH);

        //adds the panel
        f.add(lastPanel);
        f.setVisible(true); //this is initiated last as to not have any interferance when adding the components in a flow layout.
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //iterates over which button was pressed and opens the corresponding URL
        for(int i = 0; i < nameOfGame.size(); i++) {
            if (e.getSource() == buttonsAL.get(i)) {
                DataProcess.openWebPage(urls.get(i));
            }

        }
        //opens the add page.
        if (e.getSource() == addButton) {
            new AddPage();
            f.dispose();
        }

    }

    
}
