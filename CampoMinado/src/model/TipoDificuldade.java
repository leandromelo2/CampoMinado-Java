package model;

/**
 *
 * @author leandro melo
 */
public enum TipoDificuldade {
    
    EASY {
            
          public StrategyDificuldadeGame obterDificuldade(){
              return new LevelEasy();
          }
    },
    NORMAL {
          public StrategyDificuldadeGame obterDificuldade(){
          return new LevelNormal();
          }
    },
    HARD {
          public StrategyDificuldadeGame obterDificuldade(){
          return new LevelHard();
          }
    };
    
    
    public abstract StrategyDificuldadeGame obterDificuldade();    
    
}
