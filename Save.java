package checkersgame;

import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.File;

public class Save {
    
    private final int numCB[][] = {
        { 1,  0,  2,  0,  3,  0,  4,  0},
        { 0,  5,  0,  6,  0,  7,  0,  8},
        { 9,  0, 10,  0, 11,  0, 12,  0},
        { 0, 13,  0, 14,  0, 15,  0, 16},
        {17,  0, 18,  0, 19,  0, 20,  0},
        { 0, 21,  0, 22,  0, 23,  0, 24},
        {25,  0, 26,  0, 27,  0, 28,  0},
        { 0, 29,  0, 30,  0, 31,  0, 32}
        
    };
    
     public static void saveMoves(String moveOrder, int numMoves) throws IOException{
       PrintStream P; 
       File Save;
       String moves[];
       
       moves = new String[moveOrder.length() % 8];
       Save = new File(getSavePath().toString());
       P = new PrintStream(Save);
       P.print(numMoves + "\n");
       for (int i = 0; i <= numMoves; i++) 
               P.print(moves[i] + "\n");
     }
     public static boolean loadSavedMoves(String moveOrder, int numMoves) throws IOException{
       File Save;
       Save = new File(getOpenPath().toString());
       boolean valid;
       Scanner fileIn;
       
       valid = true;
       System.out.println(" "+ Save.exists());
       if(Save.exists()) {
           fileIn = new Scanner(Save);
           
           try {
           numMoves = Integer.parseInt(fileIn.next());
           moveOrder = null;
           for (int i = 0; i <= numMoves; i++) {
               moveOrder += fileIn.next();
               }
            } catch (Exception e) {
                   msgBox("Invalid File!");
                   valid = false;
                }
           }  else {
           msgBox("File Does Not Exist!");
           valid = false;
       }
       return valid;
     }

     
     public static boolean loadSavedState(String CB[][]) throws IOException
     {
       File Save;
       Save = new File(getOpenPath().toString());
       Scanner fileIn;
       boolean valid;
               
       valid = true;
       System.out.println(" "+ Save.exists());
       if(Save.exists()) {
           fileIn = new Scanner(Save);
           for (int i = 0; i < 8; i++) {
               for (int count = 0; count < 8; count++) 
                    CB[i][count] = fileIn.next();
           }
       } else {
           valid = false;
           msgBox("File Does Not Exist!");
       }
       return valid;
     }
     public static void saveState(String CB[][]) throws IOException {
         PrintStream P;
         File Save;
         
         Save = new File(getSavePath().toString());
         P = new PrintStream(Save);
        for (int i = 0; i < 8; i++) {
            for (int count = 0; count < 8; count++) 
                P.print(CB[i][count]);
            P.println();
        }
     }
     
     
     private static File getOpenPath() {
         File path;
         
        final JFileChooser fc = new JFileChooser();
        int returnVal;
        returnVal = fc.showOpenDialog(null);
        
        path = null;
        msgBox(returnVal + " ");
        if (returnVal == 0) path = fc.getSelectedFile();
    
         return path;
    }
     private static File getSavePath(){
        File Save;
        int retrival;
        
        Save = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("/home/me/Documents"));
        retrival = chooser.showSaveDialog(null);
    
         if (retrival == 0) Save = chooser.getSelectedFile();
        return Save;
     }
    
    private void  numCBContains(String holder, int coord[][], int i){
        int x,y;
       
        x = 0;
        y = 0;
        
        while(numCB[x][y] != Integer.parseInt(holder)) {
                    y++;
                    
                    if (y == 8){
                        //msgBox("XH");
                        y = 0;
                        x++;
                    }
                }
                coord[i][0] = x;
                coord[i][1] = y;
                msgBox(x + " " + y);
    }
    private void setCoord(String pieces, int pieceCoord[][]){
        int x,y;
        String holder;
       
        for (int i = 0; i < 12; i++)
            pieceCoord[i][0] = -1;
        
        holder = "";
        for (int i =0; i < pieces.length(); i++) {
            
            if (pieces.charAt(i) == ' ') {
                numCBContains(holder, pieceCoord, i);
                holder = "";
            } else holder += pieces.charAt(i);
        }
        
           numCBContains(holder, pieceCoord, pieces.length()-1);
                
        
        if (pieces.length() < 12) {
            for (int i = 0; i < 12-pieces.length(); i++) 
                pieceCoord[pieces.length() + i][0] = -1;
        }
    }
    public  void userSetBoard(String CB[][]){
        String redUserInput;
        String blackUserInput;
        
        int blackCoord[][] = new int[12][2];
        int redCoord[][] = new int [12][2];
        
        for (int i = 0; i < 8; i++) {
            for (int count = 0; count < 8; count++) 
                CB[i][count] = "[_]";
        }
        
        redUserInput = inpBox("Enter location of red Pieces: ");
        setCoord(redUserInput, redCoord);
        for (int i = 0; i < 12; i++) 
            if (redCoord[i][0] != -1) CB[redCoord[i][0]][redCoord[i][1]] = "[r]";
        
        blackUserInput = inpBox("Enter location of black Pieces: ");
        msgBox(blackUserInput);
        setCoord(blackUserInput, blackCoord);
        for (int i = 0; i < 12; i++) 
            if (blackCoord[i][0] != -1) CB[blackCoord[i][0]][blackCoord[i][1]] = "[b]";
    }
     
     
     private static String inpBox(String dialogue) {
         String input;
         
         input = JOptionPane.showInputDialog(null, dialogue);
         return input;
     }
     private static void msgBox(String dialogue)
      {
         JOptionPane.showMessageDialog(null,dialogue,"MessageBox", JOptionPane.INFORMATION_MESSAGE);        
      }
}
