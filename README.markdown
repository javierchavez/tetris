Tetris
---
By: Javier Chavez

#Game Play

**Use the four arrow keys**
  
*	UP - rotate (clockwise)
*	DOWN - pushes the shape down
*	LEFT - moves the shape to the left
*	RIGHT - moves the shape to the right

#Scoring
  
* 40 for 1 line
* 100 for 2 lines
* 300 for 3 lines
* 1200 for 4 lines

#Classes

***TetrisFrame***  contains all the elements that are needed for interation with the game. Has This includes the timer, keylistener, and UI elements.

***Board*** contains most of the game logic. When the user presses a key the TetrisFrame class the corrosponding method and moves the peice accordingly. As the timer ticks the TetrisFrame class calls methods in the Board class to move the shape. This class contains the blocks array which is how the "committed" pieces are saved. Placing pieces is very simple, if I am moving down and there is no more room left I "commit" the piece, meaning I add it to the block array and set the current shape to the next shape and generate a new shape.

***CollisionManager*** contains logic to check if the piece can move. What I do is check the current board then I look at the pieces current poision, increase X or Y or rotate then check if there is a block or if there is enough room on the board, if there is I return true, false otherwise.   

***NextPieceGenerator*** contains an array of all the shapes with colors, and generates a random number from 0-6 and returns a random shape when called.

***End of Game*** since I am outputting pieces at 0,0(top-L-corner) I make sure that 1,1 is open if it is not then I end the game.
 

#Extras
* If the window is resized the board and pieces will also resize, although its very hard to play  when stretched to a wide aspect ratio.

*	After every 2 levels the timer will decrease and block begin to move faster.

#Bugs

***Clearing row*** - sometimes on clearing of rows the other blocks will disappear.

