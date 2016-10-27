package controllers.manager;

import controllers.EventType;
import controllers.GameController;
import controllers.Subcriber;

import java.util.Vector;

/**
 * Created by tu4nFPT on 25/10/2016.
 */
public class NotificatonCenter  {
    private Vector<Subcriber> subcriberVector;

    private NotificatonCenter() {
        subcriberVector = new Vector<>();
    }

    public void onEvent(EventType eventType, GameController sender){
        for (Subcriber subcriber : subcriberVector){
            subcriber.onEvent(eventType, sender);
        }
    }
    public void resgister(Subcriber subcriber){
        subcriberVector.add(subcriber);
    }
    public void unregister(Subcriber subcriber){
        subcriberVector.remove(subcriber);
    }
    public static void createNoti(){
        instance = new NotificatonCenter();
    }
    public static NotificatonCenter instance = null;
}
