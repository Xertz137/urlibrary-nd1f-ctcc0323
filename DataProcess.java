package Projects.GUI.GUI_URL_Library.src;
import java.util.*;
import java.io.*;
import java.net.URL;

public class DataProcess {
    //initializing the files
    String dataFolderPath = "C:\\Users\\Jace\\Documents\\NetBeansProjects\\GUI_URL_Library\\data";

    File gameNameFile = new File(dataFolderPath + "/nameOfGame.txt");
    File urlsFile = new File(dataFolderPath + "/url.txt");
    File imgPathFile = new File (dataFolderPath + "/ImageFilePaths.txt");

    //arraylists as the storage of the components of the file
    //we used arraylists because it doesn't have a fixed size
    static ArrayList<String> gameNameAL = new ArrayList<String>();
    static ArrayList<String> urlsAL = new ArrayList<String>();
    static ArrayList<String> pathsAL = new ArrayList<String>(); 

    //storage for the updated contents if the user wishes to remove one.
    ArrayList<String> tempName = new ArrayList<String>();
    ArrayList<String> tempURL = new ArrayList<String>();
    ArrayList<String> tempPath = new ArrayList<String>();
    

    //getting the contents from the file and storing then in an arrayLsit
    DataProcess() {

        //this will get the contents of the file
        gameNameAL = getArrayList(gameNameFile);
        urlsAL = getArrayList(urlsFile);
        pathsAL = getArrayList(imgPathFile);

    }

    //adding contents to the files
    DataProcess(ArrayList<String> name, ArrayList<String> url, ArrayList<String> path) {

        //if the user wishes to add something, it will append that to the files
        appendOnFile(gameNameFile, name);
        appendOnFile(urlsFile, url);
        appendOnFile(imgPathFile, path);
    }

    //removing a file
    DataProcess(ArrayList<String> name) {
        
        ArrayList<Integer> removeIndex = new ArrayList<>(); 
        removeIndex = indexCheck(gameNameAL, name); //this will get the indices of the elements the user wishes to remove.

        //this will NOT add the selected elements onto the new arraylist, thus removing them. 
        int index = 0;
        for (int i = 0; i < gameNameAL.size(); i++) {
            if(removeIndex.get(index) != i) {
                tempName.add(gameNameAL.get(i));
                tempURL.add(urlsAL.get(i));
                tempPath.add(pathsAL.get(i));
            } else if (index+1 != removeIndex.size()) {
                index++;
            }
        }

        //deletes the files
        gameNameFile.delete();
        urlsFile.delete();
        imgPathFile.delete();

        //create the files again so that it's empty
        File gameNameFile = new File(dataFolderPath + "/nameOfGame.txt");
        File urlsFile = new File(dataFolderPath + "/url.txt");
        File imgPathFile = new File (dataFolderPath + "/ImageFilePaths.txt");

        //and write the updated arraylists into the files
        rewriteFile(gameNameFile, tempName);
        rewriteFile(urlsFile, tempURL);
        rewriteFile(imgPathFile, tempPath);

    }

    //this method will take the filename as an argument, read it, and store it into an arraylist
    public ArrayList<String> getArrayList(File filename) {
        ArrayList<String> arrayList = new ArrayList<String>();

        try (BufferedReader br1 = new BufferedReader(new FileReader(filename))) {
            String data = br1.readLine();

            while (data != null) {
                arrayList.add(data);
                data = br1.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace(); //iprint kung saan o pano nagkamali
        }
        return arrayList;

    }

    //this method takes in the file and arraylist as the arguments and will append the elements of the arraylist onto the file
    public void appendOnFile(File filename, ArrayList<String> list) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true)) ) {
            for (int i = 0; i < list.size(); i++) {
                bw.newLine();
                bw.write(list.get(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this takes in the file and the updated list, it writes all the elements of the arraylists onto the file.
    public void rewriteFile(File filename, ArrayList<String> newlist) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true)) ) {
            for (int i = 0; i < newlist.size(); i++) {
                bw.write(newlist.get(i));
                if(i+1 != newlist.size()) {
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this checks for the indices the user wishes to remove. this helps in identifying which element are needed to be deleted.
    public ArrayList<Integer> indexCheck(ArrayList<String> original, ArrayList<String> removeContent) {
        ArrayList<Integer> indices = new ArrayList<>(); 

        for (int h = 0; h < removeContent.size(); h++) {
            for (int i = 0; i < original.size(); i++) {
                if(removeContent.contains(original.get(i)) && removeContent.get(h).equalsIgnoreCase(original.get(i))) {
                    indices.add(i);
                    break;
                }
            }
        }
        
        Collections.sort(indices);
        return indices;
    }

    //checks if the file path is valid
    public static boolean pathCheck(String path) {
        File file = new File(path);

        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    //checks if the URL is valid
    public static boolean isValidURL(String checkURL) {
        try {
            URL url = new URL(checkURL);
            url.toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //opens the URL if the button is pushed in the Library page.
    public static void openWebPage(String url) {
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("404 not found");
        }
    }
}
