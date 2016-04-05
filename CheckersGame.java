package checkersgame;

import java.io.IOException;
import java.util.*;
import static java.lang.Math.*;

public class CheckersGame {

       private static final String DCB[][]  = {
         {"[_]", "[b]", "[_]", "[b]", "[_]", "[b]", "[_]", "[b]"},
         {"[b]", "[_]", "[b]", "[_]", "[b]", "[_]", "[b]", "[_]"},
         {"[_]", "[b]", "[_]", "[b]", "[_]", "[b]", "[_]", "[b]"},
         {"[_]", "[_]", "[_]", "[_]", "[_]", "[_]", "[_]", "[_]"},
         {"[_]", "[_]", "[_]", "[_]", "[_]", "[_]", "[_]", "[_]"},
         {"[r]", "[_]", "[r]", "[_]", "[r]", "[_]", "[r]", "[_]"},
         {"[-]", "[r]", "[_]", "[r]", "[_]", "[r]", "[_]", "[r]"},
         {"[r]", "[_]", "[r]", "[_]", "[r]", "[_]", "[r]", "[_]"},
         };
       
       private static String[][] CB = new String[8][8];
       private static int[] piece = new int[2];
       private static int[] toSpace = new int[2];
       private static boolean pieceTaken;
       private static int redCount = 12;
       private static int blackCount = 12;
       private static char turn = 'r';
       private static int xChange, yChange;
       private static String moveOrder;
       private static int numMoves;
       
    public static void main(String[] args) throws IOException {
        String userInput;
        boolean legal;
        Scanner stdin = new Scanner(System. in);
        pieceTaken = false;
        moveOrder = null;
        numMoves = 0;
        Save save = new Save();
        
        intCB();
        //save.userSetBoard(CB);
        while(redCount != 0 && blackCount != 0){
            display();
            System.out.print("\n" + Character.toUpperCase(turn) + " - Piece: ");
            userInput = stdin.nextLine();
            piece = coord(userInput);
        
            System.out.print(Character.toUpperCase(turn) +" - To: ");
            userInput = stdin.nextLine();
            toSpace = coord(userInput);
                
            legal = isLegal();
            movePiece(legal);    
        }
    }
   
    private static void intCB()
    {
        for (int i = 0; i < 8; i++) 
            System.arraycopy(DCB[i], 0, CB[i], 0, 8);
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
       boolean leftRight;
       boolean legal;
       String jumpedPiece;
      
       legal = false;
       xChange = (toSpace[0])- (piece[0]);
       yChange = (toSpace[1])-(piece[1]);
       
         if (CB[piece[0]][piece[1]].equals("[r]") && turn == 'r') {
            
              if (CB[toSpace[0]][toSpace[1]].equals("[_]") && xChange == -1) {
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
                    } else legal = false;  
              } 
        }
        
          if (CB[piece[0]][piece[1]].equals("[R]") && turn == 'r') {
           if (CB[toSpace[0]][toSpace[1]].equals("[_]")) {
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
                } else legal = false;
              } 
        }
          
          if (CB[piece[0]][piece[1]].equals("[b]") && turn == 'b')  {
            if (CB[toSpace[0]][toSpace[1]].equals("[_]")) {
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
            if (CB[toSpace[0]][toSpace[1]].equals("[_]")) {
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
                } else legal = false;
              } 
        }
        return legal;
     }
     private static void movePiece(boolean legal)
     {
         moveOrder += piece[0] + ',' + piece[1];
         moveOrder += '_' + toSpace[0] + ',' + toSpace[1];
         moveOrder += turn;
         numMoves++;
         
        if(legal) {
            if (CB[piece[0]][piece[1]].equals("[r]") && legal == true&& turn == 'r') {
                 if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[toSpace[0]+1][toSpace[1]-(yChange)] ="[_]";
             } else turn = 'b';
                 CB[piece[0]][piece[1]] = "[_]";
       
                if (toSpace[0] != 0) CB[toSpace[0]][toSpace[1]] = "[r]";
                else CB[toSpace[0]][toSpace[1]] = "[R]";
         }
         
         if (CB[piece[0]][piece[1]].equals("[R]") && legal == true&& turn == 'r')
         {
             if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[(toSpace[0])-(xChange)][(toSpace[1])-(yChange)] = "[_]";
             } else turn = 'b';
            
              CB[piece[0]][piece[1]] = "[_]";
              CB[toSpace[0]][toSpace[1]] = "[R]"; 
         }
         if (CB[piece[0]][piece[1]].equals("[b]") && legal == true&& turn == 'b') {
             if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[(toSpace[0])-(xChange)][(toSpace[1])-(yChange)] = "[_]";
             } else turn = 'r';
       
              CB[piece[0]][piece[1]] = "[_]";
              if (toSpace[0] != 7) CB[toSpace[0]][toSpace[1]] = "[b]";
              else CB[toSpace[0]][toSpace[1]] = "[B]";  
         }
          
          if (CB[piece[0]][piece[1]].equals("[B]") && legal == true&& turn == 'b') {
             if (pieceTaken == true) {
                 pieceTaken = false;
                 CB[toSpace[0]-(xChange)][toSpace[1]-(yChange)] = "[_]";
             } else turn = 'r';
            
              CB[piece[0]][piece[1]] = "[_]";
              CB[toSpace[0]][toSpace[1]] = "[B]";
         }
        }
     }
     
    
    
}
