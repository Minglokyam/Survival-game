################# Game References ###################
The Pong Game takes the following website as a reference. 
http://gamecodeschool.com/android/programming-a-pong-game-for-android/

However, the code is changed substantially to improve software architecture.


The Running Game takes the following website videos as a reference.
https://www.youtube.com/watch?v=gx7bxb2TTUg&list=PL5xhT8WWHZ9gTAiJgJrHEjOQefcdJi5az&index=1

Note the code has changed completely to have game manager, game thread and game view classes to meet our software design architecture, and the reference just provides inspiration for how the objects in the game are constructed.

The codes of the game manager, game thread and game view take assignment 1 fish tank as reference.

################# Game Playing Guide ###################

Running game: 
tap the screen to make the runner jump; 
you should jump over the spikes and hit the spike will cost one life;
you can also jump to collect the coins, and collect one coin will add 100 points to the score.
you have to survive (aka life is above 0) before the game time becomes 0.

Pong game:
tap the screen to make the paddle move.
the paddle will move to left if you tap the left side of the paddle.
the paddle will move to the right if you tap the right side of the paddle.
you have to prevent the ball from touching the bottom.
you have to survive (aka life is above 0) before the game time becomes 0.

Dodge game:

################# Known bug ###################
When the player starts the second round, if he or she leave the running game, the app will go to the pong game. We are going to fix the bug.
you should control the plane to dodge shells around you.
the plane would follow where your fingers or mouse is.
you have to survive (aka life is above 0) before the game time becomes 0.
If you run the game on your computer,then use the mouse to control the plane..
If you run the game on your phone,then use fingers to control the plane.