package com.comp2042;

public class GameController implements InputEventListener {

    private static final int BOARD_HEIGHT = 25;
    private static final int BOARD_WIDTH = 10;

    private final Board board = new SimpleBoard(BOARD_HEIGHT, BOARD_WIDTH);
    private final GuiController viewGuiController;

    public GameController(GuiController c) {
        this.viewGuiController = c;
        initGame();
    }

    private void initGame() {
        board.createNewBrick();
        viewGuiController.setEventListener(this);
        viewGuiController.initGameView(board.getBoardMatrix(), board.getViewData());
        viewGuiController.bindScore(board.getScore().scoreProperty());
    }

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

    private ViewData updateAndGetView(Runnable action) {
        action.run();
        return board.getViewData();
    }

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

    @Override
    public ViewData onLeftEvent(MoveEvent event) {
        return updateAndGetView(board::moveBrickLeft);
    }

    @Override
    public ViewData onRightEvent(MoveEvent event) {
        return updateAndGetView(board::moveBrickRight);
    }

    @Override
    public ViewData onRotateEvent(MoveEvent event) {
        return updateAndGetView(board::rotateLeftBrick);
    }

    public ViewData hardDrop() {
        while (board.moveBrickDown()) {}
        handleBrickLanding();
        return board.getViewData();
    }
    @Override
    public ViewData onHardDropEvent() {
        return hardDrop();
}

    @Override
    public void createNewGame() {
        board.newGame();
        viewGuiController.refreshGameBackground(board.getBoardMatrix());
    }
    public int[][] getBoardMatrix() {
    return board.getBoardMatrix();
    }
    
    public ViewData getViewData() {
    return board.getViewData();
    }

    public void resetGame() {
    initGame();
    }
}