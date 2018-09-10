package dv.dvproject;

import com.Icar05.diagramview.DiagramModel;
import com.Icar05.diagramview.SimpleHolder;

import java.util.ArrayList;
import java.util.List;


/*
   will return default content
 */
public class BigContentHelper {

    List<SimpleHolder> getContent(){

        List<SimpleHolder> list = new ArrayList<>();

        for(int i = 0; i< 5; i++){
            list.add(getNewHolder());
        }

        return list;
    }



    private SimpleHolder getNewHolder() {
        return new SimpleHolder(getNewList());
    }

    private List<DiagramModel> getNewList() {

        int minStep = 1;
        int maxStep = 10;

        int minValue = 10;
        int maxValue = 100;



        List<DiagramModel> list = new ArrayList<>();
        for(int i = 0; i< 10; i++){
            int step  = minStep + (int)(Math.random() * maxStep);
            int value = minValue + (int)(Math.random() * maxValue);

            if(i %5 == 0){
                step = 10;
            }

            list.add(new DiagramModel(value, step));
        }
        return list;
    }

    public SimpleHolder getSource() {
        List<DiagramModel> list = new ArrayList<>();
        list.add(new DiagramModel(40, 1));
        list.add(new DiagramModel(10, 2));
        list.add(new DiagramModel(20, 3));
        list.add(new DiagramModel(10, 4));
        list.add(new DiagramModel(20, 4));
        list.add(new DiagramModel(40, 3));
        list.add(new DiagramModel(10, 7));
        list.add(new DiagramModel(20, 3));
        list.add(new DiagramModel(20, 8));
        list.add(new DiagramModel(20, 3));
        list.add(new DiagramModel(20, 6));


        return new SimpleHolder(list);
    }

}
