
package model;

/**
 *
 * @author leandro melo
 */
public class LevelNormal  implements StrategyDificuldadeGame{

    @Override
    public int linha(int linha) {
        return 20;
    }

    @Override
    public int coluna(int coluna) {
        return 20;
    }

    @Override
    public int minas(int minas) {
    return  40;
    }
    
}
