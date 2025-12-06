package com.comp2042;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GuiController implements Initializable {

    private static final int BRICK_SIZE = 20;
    private static final int FONT_SIZE = 38;
    private static final int BRICK_ARC = 9;
    private static final int BRICK_PANEL_Y_OFFSET = -42;
    private static final Paint[] COLORS = {
        Color.TRANSPARENT, Color.AQUA, Color.BLUEVIOLET, Color.DARKGREEN,
        Color.YELLOW, Color.RED, Color.BEIGE, Color.BURLYWOOD
    };

    @FXML private GridPane gamePanel;
    @FXML private Group groupNotification;
    @FXML private GridPane brickPanel;
    @FXML private GameOverPanel gameOverPanel;

    private Rectangle[][] displayMatrix;
    private Rectangle[][] rectangles;
    private Timeline timeLine;
    private InputEventListener eventListener;
    private final BooleanProperty isPause = new SimpleBooleanProperty();
    private final BooleanProperty isGameOver = new SimpleBooleanProperty();
    private final Scoreboard scoreboard = new Scoreboard();
    private IntegerProperty currentScore;
    
    public void bindScore(IntegerProperty scoreProperty) {
        this.currentScore = scoreProperty;
        gameOverPanel.bindScore(scoreProperty); 
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupFont();
        setupGameOverPanel();
        setupReflectionEffect();
        setupKeyEvents();
    }

    private void setupFont() {
        Font.loadFont(getClass().getClassLoader().getResource("digital.ttf").toExternalForm(), FONT_SIZE);
    }

    private void setupGameOverPanel() {
        gameOverPanel.setVisible(false);
    }

    private void setupReflectionEffect() {
        Reflection reflection = new Reflection();
        reflection.setFraction(0.8);
        reflection.setTopOpacity(0.9);
        reflection.setTopOffset(-12);
    }

    private void setupKeyEvents() {
        gamePanel.setFocusTraversable(true);
        gamePanel.requestFocus();
        gamePanel.setOnKeyPressed(this::handleKeyPress);
    }

    private void handleKeyPress(KeyEvent keyEvent) {
        if (!isPause.get() && !isGameOver.get()) {
            switch (keyEvent.getCode()) {
                case LEFT, A -> refreshBrick(eventListener.onLeftEvent(new MoveEvent(EventType.LEFT, EventSource.USER)));
                case RIGHT, D -> refreshBrick(eventListener.onRightEvent(new MoveEvent(EventType.RIGHT, EventSource.USER)));
                case UP, W -> refreshBrick(eventListener.onRotateEvent(new MoveEvent(EventType.ROTATE, EventSource.USER)));
                case DOWN, S -> moveDown(new MoveEvent(EventType.DOWN, EventSource.USER));
                case SPACE -> refreshBrick(eventListener.onHardDropEvent());
            }
        }
        if (keyEvent.getCode() == KeyCode.P) {
            togglePause();
        }
        if (keyEvent.getCode() == KeyCode.N) {
            StartScreen startScreen = new StartScreen(scoreboard);
            startScreen.show((Stage) gamePanel.getScene().getWindow());
        }
        if (keyEvent.getCode() == KeyCode.H) {
            showScoreboard();
        }
        keyEvent.consume();
    }

    public void initGameView(int[][] boardMatrix, ViewData brick) {
        setupDisplayMatrix(boardMatrix);
        setupBrickRectangles(brick);
        startGameTimeline();
    }

    private void setupDisplayMatrix(int[][] boardMatrix) {
        displayMatrix = new Rectangle[boardMatrix.length][boardMatrix[0].length];
        for (int i = 2; i < boardMatrix.length; i++) {
            for (int j = 0; j < boardMatrix[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(Color.TRANSPARENT);
                displayMatrix[i][j] = rectangle;
                gamePanel.add(rectangle, j, i - 2);
            }
        }
    }

    private void setupBrickRectangles(ViewData brick) {
        rectangles = new Rectangle[brick.getBrickData().length][brick.getBrickData()[0].length];
        for (int i = 0; i < brick.getBrickData().length; i++) {
            for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                Rectangle rectangle = new Rectangle(BRICK_SIZE, BRICK_SIZE);
                rectangle.setFill(getFillColor(brick.getBrickData()[i][j]));
                rectangles[i][j] = rectangle;
                brickPanel.add(rectangle, j, i);
            }
        }
        positionBrickPanel(brick);
    }

    private void startGameTimeline() {
        timeLine = new Timeline(new KeyFrame(
                Duration.millis(400),
                ae -> moveDown(new MoveEvent(EventType.DOWN, EventSource.THREAD))
        ));
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    private void positionBrickPanel(ViewData brick) {
        brickPanel.setLayoutX(gamePanel.getLayoutX() + brick.getxPosition() * brickPanel.getVgap() + brick.getxPosition() * BRICK_SIZE);
        brickPanel.setLayoutY(BRICK_PANEL_Y_OFFSET + gamePanel.getLayoutY() + brick.getyPosition() * brickPanel.getHgap() + brick.getyPosition() * BRICK_SIZE);
    }

    private Paint getFillColor(int i) {
        return i >= 0 && i < COLORS.length ? COLORS[i] : Color.WHITE;
    }

    private void refreshBrick(ViewData brick) {
        if (!isPause.get()) {
            positionBrickPanel(brick);
            for (int i = 0; i < brick.getBrickData().length; i++) {
                for (int j = 0; j < brick.getBrickData()[i].length; j++) {
                    setRectangleData(brick.getBrickData()[i][j], rectangles[i][j]);
                }
            }
        }
    }

    public void refreshGameBackground(int[][] board) {
        for (int i = 2; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                setRectangleData(board[i][j], displayMatrix[i][j]);
            }
        }
    }

    private void setRectangleData(int color, Rectangle rectangle) {
        rectangle.setFill(getFillColor(color));
        rectangle.setArcHeight(BRICK_ARC);
        rectangle.setArcWidth(BRICK_ARC);
    }


    private void moveDown(MoveEvent event) {
        if (!isPause.get()) {
            DownData downData = eventListener.onDownEvent(event);
            handleScoreNotification(downData);
            refreshBrick(downData.getViewData());
        }
        gamePanel.requestFocus();
    }

    private void handleScoreNotification(DownData downData) {
        if (downData.getClearRow() != null && downData.getClearRow().getLinesRemoved() > 0) {
            NotificationPanel notificationPanel = new NotificationPanel("+" + downData.getClearRow().getScoreBonus());
            groupNotification.getChildren().add(notificationPanel);
            notificationPanel.showScore(groupNotification.getChildren());
        }
    }
    private void saveScoreAtGameOver() {
    int finalScore = currentScore.get();
    
    TextInputDialog dialog = new TextInputDialog("Player");
    dialog.setHeaderText("Game Over! Your score: " + finalScore);
    dialog.setContentText("Enter your name:");

    Optional<String> result = dialog.showAndWait();
    String name = result.orElse("Anonymous");

    scoreboard.addScore(new HighScore(name, finalScore));
    scoreboard.saveScores();
    }
    
    private void showScoreboard() {
        StringBuilder sb = new StringBuilder("Top 5 Scores:\n\n");
        
        for (HighScore hs : scoreboard.getScores()) {
            sb.append(hs.getName())
            .append(" — ")
            .append(hs.getScore())
            .append("\n");
        }

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Scoreboard");
    alert.setHeaderText("High Scores");
    alert.setContentText(sb.toString());
    alert.showAndWait();
    }

    public void setEventListener(InputEventListener eventListener) {
        this.eventListener = eventListener;
    }
    
    public void gameOver() {
        timeLine.stop();
        gameOverPanel.setVisible(true);
        isGameOver.set(true);
        saveScoreAtGameOver();
    }

    public void newGame(ActionEvent actionEvent) {
        timeLine.stop();
        gameOverPanel.setVisible(false);
        eventListener.createNewGame();
        gamePanel.requestFocus();
        timeLine.play();
        isPause.set(false);
        isGameOver.set(false);
    }

    public void pauseGame(ActionEvent actionEvent) {
        gamePanel.requestFocus();
    }

    private void togglePause() {
    if (isPause.get()) {
        timeLine.play();
        isPause.set(false);
    } else {
        timeLine.stop();
        isPause.set(true);
    }
    }
    
    public GridPane getRootPane() {
    return gamePanel;
    }
}
