package genetic;

import java.util.ArrayList;

import connect.AlphaBetaE;
import connect.Game;
import connect.NeuralNetAI;

public class FitnessThread implements Runnable {
	
	public ArrayList<Genome> subPop = new ArrayList<Genome>();
	
	
	@Override
	public void run() {
		for(Genome g : subPop) {
			double f = calculateFitness(g);
			g.setFitness(f);
		}
		
	}
	
    /*
     * Let's define the fitness as (the amount of turns) before
     * this AI gets beaten.
     *
     * If the NeuralNetAI wins the game, the fitness becomes 50 
     *
     */ 
	public double calculateFitness(Genome g) {
	       
        Game game1 = new Game(new NeuralNetAI(g), new AlphaBetaE(1));
        Game game2 = new Game(new AlphaBetaE(1), new NeuralNetAI(g));
        
        double score1 = game1.getTotalTurns();
            
        if (game1.getWinner() == 1) {
                score1 = 200;
        } else if (game1.getWinner() == 3) {
                score1 = 46;
        }

        double score2 = game2.getTotalTurns();

        if (game2.getWinner() == 2) {
                score2 = 200;
        } else if (game2.getWinner() == 3) {
                score2 = 46;
        }
            
        return ( (score1 + score2)/2);
	}
}
