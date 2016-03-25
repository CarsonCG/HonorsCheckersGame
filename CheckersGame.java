package checkersgame;
import java.util.*;
import static java.lang.Math.*;
import javax.swing.JOptionPane;

public class CheckersGame {

       private static String CB[][]  = {
         {"[ ]", "[b]", "[ ]", "[b]", "[ ]", "[b]", "[ ]", "[b]"},
         {"[b]", "[ ]", "[b]", "[ ]", "[b]", "[ ]", "[b]", "[ ]"},
         {"[ ]", "[b]", "[ ]", "[b]", "[ ]", "[b]", "[ ]", "[b]"},
         {"[ ]", "[ ]", "[r]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]"},
         {"[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]", "[ ]"},
         {"[r]", "[ ]", "[r]", "[ ]", "[r]", "[ ]", "[r]", "[ ]"},
         {"[ ]", "[r]", "[ ]", "[ ]", "[ ]", "[r]", "[ ]", "[r]"},
         {"[r]", "[ ]", "[r]", "[ ]", "[r]", "[ ]", "[r]", "[ ]"},
         };
       private static int[] piece = new int[2];
       private static int[] toSpace = new int[2];
       private static boolean pieceTaken;
       private static int redCount = 12;
       private static int blackCount = 12;
       private static char turn = 'b';
       
    public static void main(String[] args) {
        String userInput;
        boolean legal;
        Scanner stdin = new Scanner(System. in);
        pieceTaken = false;
        
        while(redCount != 0 && blackCount != 0){
            display();
            System.out.print("\n" + Character.toUpperCase(turn) + " - Piece: ");
            userInput = stdin.nextLine();
            piece = coord(userInput);
        
            System.out.print(Character.toUpperCase(turn) +" - To: ");
            userInput = stdin.nextLine();
            toSpace = coord(userInput);
                
            legal = isLegal();
            tryMove(legal);    
        }
    }
   
    private static int[] coord(String input)
    {
        int point[] = new int[2];
        char temp;
        
        temp = input.charAt(0);
        point[0] = Character.getNumericValue(temp) - 1;
        temp = input.charAt(2);
        point[1] = Character.getNumericValue(temp) - 1;
      return point;
    }
    
     private static void display()
    {
        System.out.print("    1  2  3  4  5  6  7  8 \n");
        for (int i = 0; i < 8; i++) {
            System.out.print(" " + (i+1)+ " ");
            for (int counter = 0; counter < 8; counter++) {
                System.out.print(CB[i][counter]);
            }
            System.out.println();
            
        }
     }
     
     
     private static boolean isLegal()
     {
       int xChange;
       int yChange;
       boolean leftRight;
       boolean legal;
       String jumpedPiece;
      
       legal = false;
       xChange = (toSpace[0])- (piece[0]);
       yChange = (toSpace[1])-(piece[1]);
       
         if (CB[piece[0]][piece[1]].equals("[r]") && turn == 'r') {
            
              if (CB[toSpace[0]][toSpace[1]].equals("[ ]") && xChange == -1) {
                legal = (xChange == -1);
                leftRight =  (abs(yChange) == 1);
                legal = (leftRight == true && legal == true);
            }
              jumpedPiece = CB[toSpace[0]-xChange/2][toSpace[1]-yChange/2];
               if (jumpedPiece.equals("[b]")|| jumpedPiece.equals("[B]")) {
               legal = (xChange == -2);
               leftRight =(abs(yChange) == 2);
                    if (leftRight== true && legal == true)  {
                        legal = true;
                        pieceTaken = true;
                        blackCount--;
                    }
                else legal = false;  
              } 
        }
        
          if (CB[piece[0]][piece[1]].equals("[R]") && turn == 'r') {
           if (CB[toSpace[0]][toSpace[1]].equals("[ ]")) {
                legal = (abs(xChange) == 1);
                leftRight = (abs(yChange) == 1);
                legal = (leftRight == true && legal == true);
            }
             jumpedPiece = CB[toSpace[0]-xChange/2][toSpace[1]-yChange/2];
             if (jumpedPiece.equals("[b]")|| jumpedPiece.equals("[B]")) {
                legal =  (abs(xChange) == 2);
                leftRight = (abs(yChange) == 2);
                if (leftRight == true && legal == true) {
                    pieceTaken = true;
                    redCount--;
                }
                else legal = false;
              } 
        }
          
          if (CB[piece[0]][piece[1]].equals("[b]") && turn == 'b')  {
            if (CB[toSpace[0]][toSpace[1]].equals("[ ]")) {
                legal = (xChange == 1);
                leftRight = (abs(yChange) == 1);
                legal = (leftRight == true && legal == true);
            }
             jumpedPiece = CB[toSpace[0]-xChange/2][toSpace[1]-yChange/2];
             if (jumpedPiece.equals("[r]")|| jumpedPiece.equals("[R]")) {
                legal =  (xChange == 2);
                leftRight = (abs(yChange) == 2);
                if (leftRight == true && legal == true) {
                    pieceTaken = true;
                    redCount--;
                }
                else legal = false;
              }     
        }
        
        if (CB[piece[0]][piece[1]].equals("[B]") && turn == 'b')
        {
            if (CB[toSpace[0]][toSpace[1]].equals("[ ]")) {
                legal = (abs(xChange) == 1);
                leftRight = (abs(yChange) == 1);
                legal =  (leftRight == true && legal == true);  
            }
             jumpedPiece = CB[toSpace[0]-xChange/2][toSpace[1]-yChange/2];
             if (jumpedPiece.equals("[r]") || jumpedPiece.equals("[R]")) {
                legal =  (abs(xChange) == 2);
                leftRight =  (abs(yChange) == 2);
                if (leftRight == true && legal == true) {
                    pieceTaken = true;
                    redCount--;
                }
                else legal = false;
              } 
        }
        return legal;
     }
     private static void tryMove(boolean legal)
     {//Check for double jump method
        if(legal) {
            if (CB[piece[0]][piece[1]].equals("[r]") && legal == true&& turn == 'r') {
                 if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[toSpace[0]+1][toSpace[1]-(toSpace[1] - piece[1])/2] ="[ ]";
             }
                 CB[piece[0]][piece[1]] = "[ ]";
       
                if (toSpace[0] != 0)
                    CB[toSpace[0]][toSpace[1]] = "[r]";
                 else
                    CB[toSpace[0]][toSpace[1]] = "[R]";
                turn = 'b';
         }
         
         if (CB[piece[0]][piece[1]].equals("[R]") && legal == true&& turn == 'r')
         {
             if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[(toSpace[0])-(toSpace[0]-piece[0])/2][(toSpace[1])-(toSpace[1]-piece[1])/2] = "[ ]";
             }
             else turn = 'b';
            
              CB[piece[0]][piece[1]] = "[ ]";
              CB[toSpace[0]][toSpace[1]] = "[R]"; 
         }//Fix red piece taken and make code look identical
          if (CB[piece[0]][piece[1]].equals("[b]") && legal == true&& turn == 'b') {
             if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[(toSpace[0])-(toSpace[0]-piece[0])/2][(toSpace[1])-(toSpace[1]-piece[1])/2] = "[ ]"; 
                 //Insert Double Jump check method return boolean
                 
             }
             else turn = 'r';
       
              CB[piece[0]][piece[1]] = "[ ]";
              if (toSpace[0] != 7)
                CB[toSpace[0]][toSpace[1]] = "[b]";
              else
                  CB[toSpace[0]][toSpace[1]] = "[B]";  
         }
          
          if (CB[piece[0]][piece[1]].equals("[B]") && legal == true&& turn == 'b') {
             if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[toSpace[0]-(toSpace[0]-piece[0])/2][toSpace[1]-(toSpace[1]-piece[1])/2] = "[ ]";
             }
             else turn = 'r';
            
              CB[piece[0]][piece[1]] = "[ ]";
              CB[toSpace[0]][toSpace[1]] = "[B]";
         }
        }
     }
     
          public static void msgBox(String dialogue)
     {
         JOptionPane.showMessageDialog(null,dialogue,"MessageBox", JOptionPane.INFORMATION_MESSAGE);
         
     }
}
