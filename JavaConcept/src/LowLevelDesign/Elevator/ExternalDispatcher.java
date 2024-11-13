package LowLevelDesign.Elevator;

public class ExternalDispatcher {
   ExternalRequestServingStrategy requestStrategy;
   public ExternalDispatcher(ExternalRequestServingStrategy requestStrategy) {
       this.requestStrategy = requestStrategy;
   }
    public void acceptRequest(Request request){
      requestStrategy.addRequest(request);
    }
}
