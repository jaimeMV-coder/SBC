import jess.*;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;

public class OneShotJessAgent extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new JessBehaviour());
  } 

  private class JessBehaviour extends OneShotBehaviour {

    public void action() {
        System.out.println("Agent's action method executed");
        try {
        Rete engine = new Rete();
        engine.eval("(reset)");
        engine.batch("C:/Jess71p2/market/run.clp");
        }catch (JessException ex) {
        System.err.println(ex);
        
        }
    } 
    
    public int onEnd() {
      myAgent.doDelete();   
      return super.onEnd();
    } 
   
    }
}