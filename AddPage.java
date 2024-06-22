package Projects.GUI.GUI_URL_Library.src;
import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.util.ArrayList;

public class AddPage extends JFrame implements ActionListener {
    JFrame f = new JFrame("Add Page");

    //initializes an array of components. An array is viable in this instance because there is only a fixed number we want to add
    JLabel[] nameLabels = new JLabel[5];
    JLabel[] urlLabels = new JLabel[5];
    JLabel[] pathLabels = new JLabel[5];

    //initailizes the labels for the textfields.
    JLabel name = new JLabel("NAME: (NOTE: Only the name is required in order to remove it.)");
    JLabel url = new JLabel("URL: ");
    JLabel path = new JLabel("FILE PATH: ");

    //same concept as the array of labels.
    JTextField[] nameTF = new JTextField[5];
    JTextField[] urlTF = new JTextField[5];
    JTextField[] pathTF = new JTextField[5];

    //initializes the buttons
    JButton addButton = new JButton("Add");
    JButton resetButton = new JButton("Reset");
    JButton removeButton = new JButton("Remove");

    JButton decoy = new JButton();

    //initializes arraylists to be used if the user wants to add or remove something.
    ArrayList<String> addGameName = new ArrayList<String>();
    ArrayList<String> addUrls = new ArrayList<String>();
    ArrayList<String> addPaths = new ArrayList<String>();

    ArrayList<String> gameNames = new ArrayList<String>();

    public AddPage() {

        //declares the starting y valuse of the components
        int y1 = 80;
        int y2 = 340;
        int y3 = 600;

        //declares the components, set the corrdinates, and adds them into the frame.
        for (int i = 0; i < nameLabels.length; i++) {
            nameLabels[i] = new JLabel((i+1) + ". ");
            nameLabels[i].setBounds(20, y1, 20,30);

            nameTF[i] = new JTextField();
            nameTF[i].setBounds(40, y1, 600, 30);

            y1+=40;

            urlLabels[i] = new JLabel((i+1) + ". ");
            urlLabels[i].setBounds(20, y2, 20,30);

            urlTF[i] = new JTextField();
            urlTF[i].setBounds(40, y2, 600,30);

            y2+=40;

            pathLabels[i] = new JLabel((i+1) + ". ");
            pathLabels[i].setBounds(20, y3, 20,30);

            pathTF[i] = new JTextField();
            pathTF[i].setBounds(40, y3, 600,30);

            y3+=40;

            f.add(nameLabels[i]);
            f.add(nameTF[i]);

            f.add(urlLabels[i]);
            f.add(urlTF[i]);

            f.add(pathLabels[i]);
            f.add(pathTF[i]);
        }

        //sets the coordinates for the labels
        name.setBounds(20, 40, 700, 40);
        url.setBounds(20, 300, 700, 40);
        path.setBounds(20, 560, 700, 40);

        //sets the font of the labels
        name.setFont(new Font("SansSerif", Font.BOLD, 14));
        url.setFont(new Font("SansSerif", Font.BOLD, 14));
        path.setFont(new Font("SansSerif", Font.BOLD, 14));

        //setes the coordinates for the buttons and add action listener
        addButton.setBounds(380, 850, 80, 25);
        addButton.addActionListener(this);

        removeButton.setBounds(470, 850, 80, 25);
        removeButton.addActionListener(this);

        resetButton.setBounds(560, 850, 80, 25);
        resetButton.addActionListener(this);

        //adding the componets to the container
        f.add(name);
        f.add(url);
        f.add(path);

        f.add(addButton);
        f.add(resetButton);
        f.add(removeButton);

        decoy.setVisible(false);
        f.add(decoy);

        //sets attributes for the frame
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(700,950);
        f.setLocation(600, 50);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            for (int i = 0; i < nameLabels.length; i++) {
                String getName = nameTF[i].getText();
                String getURLs = urlTF[i].getText();
                String getPath = pathTF[i].getText();

                //if all the text fields are simultaneously not empty, that means the user wants to add something
                if(!getName.isBlank() && !getURLs.isBlank() && !getPath.isBlank()) {
                    boolean validURL = DataProcess.isValidURL(getURLs);
                    boolean validPath = DataProcess.pathCheck(getPath);

                    //if the URL and file path are valid, then it adds it.
                    if (validURL && validPath) {
                        addGameName.add(getName);
                        addUrls.add(getURLs);
                        addPaths.add(getPath);
                    }
                    
                    //these if statements will tell if whether the URL or the file path is valid.
                    if (!validURL) {
                        url.setText("URL: (INVALID URL)!");
                    } 
                    if (!validPath) {
                        path.setText("FILE PATH: (INVALID FILE PATH)!");
                    }

                }
            }
            //will only process the data and rerun the program if the there is something stored in the arraylist
            if (!addGameName.isEmpty() && !addUrls.isEmpty() && !addPaths.isEmpty()) {
                new DataProcess(addGameName, addUrls, addPaths);
                addGameName.clear();
                addUrls.clear();
                addPaths.clear();

                f.dispose();
                Main.rerun();
            }
            
        }

        if (e.getSource() == removeButton) {
            ArrayList<String> checkNames = DataProcess.gameNameAL;

            for (int i = 0; i < nameLabels.length; i++) {
                //this will check if the name the user wants to remove is actually in the text file.
                if (checkNames.contains(nameTF[i].getText())) {
                    gameNames.add(nameTF[i].getText());
                } 
            }
            
            //this will only run if the arraylist is not empty.
            //meaning there is a valid element in the files that the user wants to remove.
            if (!gameNames.isEmpty()) {
                new DataProcess(gameNames);
                f.dispose();
                Main.rerun();
            } else {
                System.out.println("No such portal found");
            }
            
        }

        //simple clears the textfields
        if (e.getSource() == resetButton) {
            for (int i = 0; i < nameLabels.length; i++) {
                nameTF[i].setText("");
                urlTF[i].setText("");
                pathTF[i].setText("");
            }
        }

    }
    
}
