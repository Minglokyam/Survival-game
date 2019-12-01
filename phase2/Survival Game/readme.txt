################### Game Playing Guide ###################

Running game:
Tap the screen to make the runner jump.
The runner has to jump over the spikes. Hitting the spike will cost one life.
The runner can jump to collect the coins. Collecting one coin will yield 100 scores.
The number of lives has to be greater than 0 to proceed.

Pong game:
Tap the screen to make the paddle move.
The paddle will move to the left if you tap the left-hand side of the paddle.
The paddle will move to the right if you tap the right-hand side of the paddle.
Make sure you can catch the ball with the paddle.
Hitting the bottom will cost one life. The system will regenerate the ball at the centre.
The number of lives has to be greater than 0 to proceed.

Dodge game:
Control the plane to dodge shells around you.
The plane would follow your fingers or mouse to travel.
You will win this game if you can keep the number of lives greater than 0.
You will lose this game if you fail to keep the number of lives greater than 0.

######################## Citation ########################

The Pong Game takes the following website as a reference.
http://gamecodeschool.com/android/programming-a-pong-game-for-android/

Noted that the codes were changed substantially to improve software architecture.

The Running Game takes the following video as a reference.
https://www.youtube.com/watch?v=gx7bxb2TTUg&list=PL5xhT8WWHZ9gTAiJgJrHEjOQefcdJi5az&index=1

Noted that the code has changed completely. We made game manager,  thread and view classes to
improve the software design architecture. The reference just inspires how the
objects in the game are constructed.

The codes of the game manager, game thread and game view take assignment 1 fish tank as a
reference.

https://www.mindrot.org/projects/jBCrypt/
We imported jBCrypt to encrypt the password.
We added the dependency to build.gradle.

####################### Change in Phase 2 #########################
We have restructured all games and the login system to implement MVP structure. Noted that some model and
presenter classes in dodge and running games import android Rect and RectF classes.
It violates the rule of MVP. We can solve this by creating our Rect and RectF classes.
But it will unnecessarily increase the number of classes.

We added a scoreboard and a replay function for the pong game as our extra features.

I received feedback for phase 1 that the HP feature in the dodge game is difficult to understand.
We added introduction pages before starting the games. It contains a screenshot of the game to illustrate a brief picture to the player.

We used jBCrypt to encrypt the password.
We used regex to ensure the complexity of the password. The password must contain at least one uppercase letter.

The usermanager becomes a singleton class.

We implemented package by feature and subpackage by layer.

####################### Known bug in Phase 1 #######################
*********************** update **************************
This bug has been fixed during phase 2.

*********************************************************
When the player starts the second round, if he or she leaves the running game, the app
will proceed to the pong game. This bug does not affect the functioning of
the game. We are going to fix this bug.
