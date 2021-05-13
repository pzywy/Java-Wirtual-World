import java.util.concurrent.TimeUnit;

import javax.swing.SwingWorker;

import Board.GameBoard;

class Worker extends SwingWorker<String, Object> {

private GameBoard board;
    Worker(GameBoard _board) {
    	board = _board;
    }

    @Override
    protected String doInBackground() throws Exception {

        while(GameControll.autoSymulate)
        {
        	GameControll.turn(board);
        	if(board.getListOfOrganisms().size()<500)
        		TimeUnit.MILLISECONDS.sleep(10);
        	if(board.getListOfOrganisms().size()<1000)
        		TimeUnit.MILLISECONDS.sleep(10);
        }
		return null;
    }

}