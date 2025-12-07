package com.comp2042;

/**
 * Manages the main game logic for Tetris.
 * <p>
 * Handles user input events, controls the game board, and updates the GUI.
 * Implements {@link InputEventListener} to respond to user input such as moving,
 * rotating, and dropping bricks.
 */
public class GameController implements InputEventListener {

    private static final int BOARD_HEIGHT = 25;
    private static final int BOARD_WIDTH = 10;

    private final Board board = new SimpleBoard(BOARD_HEIGHT, BOARD_WIDTH);
    private final GuiController viewGuiController;

    /**
     * Constructs a new {@code GameController} with the specified GUI controller.
     * Initializes the game and sets up event listeners.
     *
     * @param c the GUI controller to handle view updates and user interactions
     */
    public GameController(GuiController c) {
        this.viewGuiController = c;
        initGame();
    }

    /**
     * Initializes the game by creating the first brick, setting the GUI event listener,
     * initializing the game view, and binding the score.
     */
    private void initGame() {
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
    }

    /**
     * Handles the landing of a brick.
     * <p>
     * Merges the current brick into the board background, clears any completed rows,
     * updates the score, and checks for game over.
     *
     * @return a {@link ClearRow} object containing information about removed lines
     *         and score bonus, or {@code null} if no rows were cleared
     */
    private ClearRow handleBrickLanding() {
        board.mergeBrickToBackground();
        ClearRow clearRow = board.clearRows();

        if (clearRow.getLinesRemoved() > 0) {
            board.getScore().add(clearRow.getScoreBonus());
        }

        if (board.createNewBrick()) {
            viewGuiController.gameOver();
        }

        viewGuiController.refreshGameBackground(board.getBoardMatrix());
        return clearRow;
    }

    /**
     * Executes a board action and returns the updated view data.
     *
     * @param action the action to perform on the board
     * @return the {@link ViewData} representing the updated state of the board
     */
    private ViewData updateAndGetView(Runnable action) {
        action.run();
        return board.getViewData();
    }

    /**
     * Handles the "down" movement event for a brick.
     * <p>
     * Moves the brick down if possible; otherwise, handles landing.
     *
     * @param event the move event
     * @return a {@link DownData} object containing cleared row info and updated view data
     */
    @Override
    public DownData onDownEvent(MoveEvent event) {
        ClearRow clearRow = null;

        if (!board.moveBrickDown()) {
            clearRow = handleBrickLanding();
        } else if (event.getEventSource() == EventSource.USER) {
            board.getScore().add(1);
        }

        return new DownData(clearRow, board.getViewData());
    }

    /**
     * Handles the "left" movement event for a brick.
     *
     * @param event the move event
     * @return the updated {@link ViewData} after moving left
     */
    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        return updateAndGetView(board::moveBrickLeft);
    }

    /**
     * Handles the "right" movement event for a brick.
     *
     * @param event the move event
     * @return the updated {@link ViewData} after moving right
     */
    @Override
    public ViewData onRightEvent(MoveEvent event) {
        return updateAndGetView(board::moveBrickRight);
    }

    /**
     * Handles the "rotate" event for a brick.
     *
     * @param event the move event
     * @return the updated {@link ViewData} after rotating the brick
     */
    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        return updateAndGetView(board::rotateLeftBrick);
    }

    /**
     * Performs a hard drop by moving the brick down until it cannot move further,
     * then handles landing and updates the view.
     *
     * @return the updated {@link ViewData} after the hard drop
     */
    public ViewData hardDrop() {
        while (board.moveBrickDown()) {}
        handleBrickLanding();
        return board.getViewData();
    }

    /**
     * Handles the "hard drop" input event.
     *
     * @return the updated {@link ViewData} after performing the hard drop
     */
    @Override
    public ViewData onHardDropEvent() {
        return hardDrop();
    }

    /**
     * Starts a new game by resetting the board and refreshing the view.
     */
    @Override
    public void createNewGame() {
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }

    /**
     * Returns the current board matrix representing the game state.
     *
     * @return a 2D integer array of the board matrix
     */
    public int[][] getBoardMatrix() {
        return board.getBoardMatrix();
    }

    /**
     * Returns the current view data of the board.
     *
     * @return the {@link ViewData} representing the board state
     */
    public ViewData getViewData() {
        return board.getViewData();
    }

    /**
     * Resets the game to its initial state by re-initializing the game.
     */
    public void resetGame() {
        initGame();
    }
}