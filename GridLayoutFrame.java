// Modify the code to solve a puzzle automatically to 12345678 or 12345687
package ButtonFrame;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;

public class GridLayoutFrame extends JFrame implements ActionListener 
{
   private JButton[] buttons; // array of buttons
   private int tpos, bpos, temp; // tile position 0 to 8 and blank position 0 to 8 in 8 puzzle
   private int xb, yb, xt,yt; // pixel coordinate of blank (= hole)
   private int [] buttonpos = {0, 1,2,3,4,5,6,7,8};
   private boolean baround = false;
   private String numstr;
   private int steps = 0;
   private static final String[] names1 = 
      {"3", "5", "1", "4", "", "2", "6","8", "7" };
 //  private static final String[] names = {"3", "1", "2", "7", "", "4", "5", "8","6" };
   private static final String[] names = {"1","2","3","4","6","5","8","7",""};
   private int poshole; // the index of hoel or "" in the names string array, = 5 for this example.
   private boolean toggle = true; // toggle between two layouts
   private Container container; // frame container
   private GridLayout gridLayout1; // first gridlayout
   private GridLayout gridLayout2; // second gridlayout

   // no-argument constructor
   public GridLayoutFrame()
   {
      super( "GridLayout Demo" );
      for (int i = 0; i < names.length; i ++)
    	  if (names[i] == "")
    		  poshole = i;
      System.out.println("Position of hole is " + poshole);
      gridLayout1 = new GridLayout( 3, 3, 5, 5 ); // 2 by 3; gaps of 5
  //    gridLayout2 = new GridLayout( 3, 2 ); // 3 by 2; no gaps
      container = getContentPane(); // get content pane
      setLayout( gridLayout1 ); // set JFrame layout
      buttons = new JButton[ names.length ]; // create array of JButtons

      for ( int count = 0; count < names.length; count++ )
      {
         buttons[ count ] = new JButton( names[ count ] );
         buttons[ count ].addActionListener( this ); // register listener
         add( buttons[ count ] ); // add button to JFrame
      } // end for
      
      solvePuzzle ();
   } // end GridLayoutFrame constructor

   // handle button events by toggling between layouts
   public void actionPerformed( ActionEvent event )
   {
	   numstr = event.getActionCommand();
	   System.out.println("Button " + numstr + " clicked");
	   checkBlankTile (numstr) ; // check if one of 4 neighbors is blank tile, if so, move to blank
	  
   } // end method actionPerformed
   
   public void checkBlankTile (String numstr) {
	   // find the location in this 3x3 matrix of the num string
	   
	   int num;
	   
	   num = Integer.parseInt(numstr);
	
	   // Search for num in the matrix
	   for (int i = 0 ; i < 3; i++)
		   for (int j = 0; j < 3; j ++)
		   {
			  if (buttons[i*3+j].getActionCommand().equals(numstr))
	//	  if (buttonpos[i*3+j] ==num)
			   {
				   tpos = i*3+j;
				   System.out.println("Found tile position: " + tpos + " i: " + i 
						   + " j: " + j + " for " + num);
				   break;
			   }				
		    } // end of for j	   
          // end of for i
	   
	   //Search for blank in the matrix
	   for (int i = 0 ; i < 3; i++)
		   for (int j = 0; j < 3; j ++)
		   {
		  if (buttons[i*3+j].getActionCommand().equals(""))
		//	if (buttonpos[i*3+j] == 8) // Position 8 for hole or blank
			   {
				   bpos = i*3+j;
				   System.out.println("Found blank position: " + bpos + " i: " + i 
						   + " j: " + j);
				   break;
			   }				
		    } // end of for j	   
          // end of for i
   
   // Check now if blank tile is around. 
   // Positions 0, 2, 6, and 8 each has 2 neighbors: 0: 1 & 3, 2: 1 & 5, 6: 3 & 7, 8: 5 & 7
   // Positions 1, 3, 5, and 7 each has 3 neighbors; 
   // Position 4 has 4 neighbors.
   
   switch (tpos) {
   case 0:  // neighbors are 1 and 3
	   if (bpos == 1 || bpos == 3) baround = true; break;
   case 2:  // neighbors 1 & 5
	   if (bpos == 1 || bpos == 5) baround = true; break;
   case 6: // neighbors 3 & 7
	   if (bpos == 3 || bpos == 7) baround = true; break;
   case 8: // neighbors 5 & 7
	   if (bpos == 5 || bpos == 7) baround = true; break;
   case 1: // neighbors 0, 2, 4
	   if (bpos == 0 || bpos == 2 || bpos == 4) baround = true; break;
   case 3: // neighbors 0, 4, & 6
	   if (bpos == 0 || bpos == 4 || bpos == 6) baround = true; break;
   case 5: // neighbors 2, 4, & 8
	   if (bpos == 2 || bpos == 4 || bpos == 8) baround = true; break;
   case 7: // neighbors 4, 6, & 8
	   if (bpos == 4 || bpos == 6 || bpos == 8) baround = true; break;
   case 4: // neighbors 1, 3, 5, & 7
	   if (bpos == 1 || bpos == 3 || bpos == 5 || bpos == 7) baround = true; break;
  default:
	  System.out.println ("Error, tile position is " + tpos); break;  
	   
   			}  // end of switch
   
	   if (baround)  // switch the clicked tile and blank when blank is around
		   
	   {
		  
		   //switch tile and hole
	
		   buttons[tpos].setText(""); // set to empty
		   buttons[bpos].setText(numstr);
		// Update buttonpos array
		   buttonpos[tpos]= 4;  // This actually does not matter since buttonpos is NOT used
		   buttonpos[bpos]= num;
		   System.out.println("Steps: " + steps ++);
		   baround = false;
	
		   
	   }
	   else
	   {
		   System.out.println ("Tile position " + tpos +
				   " with value " + buttons[tpos].getActionCommand() 
				   + " is NOT next to Blank at " + bpos);
	   }
	   
	   // Switch tile and blank if blank is around

   }  // end of method checkBlankTile
 
   public void solvePuzzle () {
	   // Step 1: Put tile 1 in position (0,0)
	   // There are 8 possible positions for tile 1 not in (0,0), followed by 8 positions 
	   // of hole (which can be at (0,0) first.
	   
	   // A heuristic is: first find where tile 1 is and where blank (hole) is
	   // If the blank is right below "1", we do not move 1 down into the hole
	   // Similarly if the blank is to the right of "1", we do not move 1 to the right.
	   
	   
	   
	   
   }
} // end class GridLayoutFrame

