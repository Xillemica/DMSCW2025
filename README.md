# Tetris JavaFX

A JavaFX-based Tetris game implementing classic Tetris mechanics with scores, high scores, and a simple GUI.

## Installation
1. Clone the repository:
git clone <https://github.com/Xillemica/DMSCW2025>
2. Open in your IDE.
3. Ensure JavaFX SDK is added to your project.
4. Open in terminal and run: mvn clean javafx:run

## Controls

| Key        | Action           |
|:----------:|:----------------:|
| Left / A   | Move brick left  |
| Right / D  | Move brick right |
| Up / W     | Rotate brick     |
| Down / S   | Soft drop        |
| Space      | Hard drop        |
| P          | Pause / Resume   |
| N          | Start new game   |
| H          | Show scoreboard  |


## Features / Project Structure

1. com.comp2042 – Main package containing all game logic and GUI classes
2. Board – Interface defining board behavior
3. SimpleBoard – Implements Board, handles brick movement and collisions
4. GuiController – Controls JavaFX GUI and user input
5. GameController – Manages game flow and interactions between board and GUI
6. BrickRotator – Handles rotation logic for bricks
7. Score / Scoreboard / HighScore – Score management and persistence
8. ViewData / DownData – DTOs for passing board and brick state to GUI
9. NotificationPanel / GameOverPanel / StartScreen – GUI components
10. MatrixOperations – Utility class for board matrix operations
11. MoveEvent, EventType, EventSource – Event handling structures

## How to Play

1. Launch the game
2. Use the controls to move, rotate, and drop bricks.
3. Clear lines to increase your score.
4. When the game ends, enter your name to save your high score.
5. Restart with the N key or close the game.

## Preview of the Game (Screenshots)

<div style="display: flex; flex-wrap: wrap; gap: 10px; justify-content: center;">

  <figure style="margin: 0; text-align: center;">
    <img src="tetris_images/1.png" width="200" />
    <figcaption>Main menu with high scores</figcaption>
  </figure>

  <figure style="margin: 0; text-align: center;">
    <img src="tetris_images/2.png" width="200" />
    <figcaption>Gameplay with falling bricks</figcaption>
  </figure>

  <figure style="margin: 0; text-align: center;">
    <img src="tetris_images/3.png" width="200" />
    <figcaption>Game over screen</figcaption>
  </figure>

  <figure style="margin: 0; text-align: center;">
    <img src="tetris_images/4.png" width="200" />
    <figcaption>Save score screen</figcaption>
  </figure>

  <figure style="margin: 0; text-align: center;">
    <img src="tetris_images/5.png" width="200" />
    <figcaption>New score saved in Start Screen</figcaption>
  </figure>

</div>

