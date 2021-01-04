package model;

/**
 *
 * @author melo
 */
public class LevelEasy  implements StrategyDificuldadeGame{

    @Override
    public int linha(int linha) {
        return 10;
    }

    @Override
    public int coluna(int coluna) {
        return 15;
    }

    @Override
    public int minas(int minas) {
    return  20;
    }
    
}
