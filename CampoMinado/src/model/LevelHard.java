/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author leandro melo
 */
public class LevelHard  implements StrategyDificuldadeGame{

    @Override
    public int linha(int linha) {
        return 30;
    }

    @Override
    public int coluna(int coluna) {
        return 30;
    }

    @Override
    public int minas(int minas) {
    return  70;
    }
    
}
