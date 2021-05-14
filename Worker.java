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
        	TimeUnit.MILLISECONDS.sleep(25);
        	
//        	if(board.getListOfOrganisms().size()<1000)
//        		TimeUnit.MILLISECONDS.sleep(10); 	
        }
		return null;
    }

}