package LowLevelDesign.TV;

public class Main {
    public static void main(String[] args)  {
        TVContext context = TVContext.getInstance();

       try {
           context.getState().powerOn(context);

//           context.getState().powerOn(context);
           context.getState().changeVolume(context, 30);
           context.getState().switchChannel(context);
           context.getState().powerOff(context);

       }catch (Exception e) {
          e.printStackTrace();
       }
    }
}

/*
TV is turned on
Changed Volume to 30
Switching channel
Turned off TV
 */