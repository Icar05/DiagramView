package dv.dvproject;

import com.Icar05.diagramview.DiagramModel;
import com.Icar05.diagramview.SimpleHolder;

import java.util.ArrayList;
import java.util.List;


/*
   will return default content
 */
public class BigContentHelper {

    private bigContentHelper delegate;

    public void setDelegate(bigContentHelper delegate) {
        this.delegate = delegate;
    }





    public DiagramModel getRandomData(){
        int minStep = 1;
        int maxStep = 10;

        int minValue = 10;
        int maxValue = 100;


        int step = minStep + (int) (Math.random() * maxStep);
        int value = minValue + (int) (Math.random() * maxValue);

        return new DiagramModel(value, step);
    }

    public void emitData(int count){
        int minStep = 1;
        int maxStep = 10;

        int minValue = 10;
        int maxValue = 100;

        for(int i = 0; i< count; i++) {
            int step = minStep + (int) (Math.random() * maxStep);
            int value = minValue + (int) (Math.random() * maxValue);

            if (i % 5 == 0) {
                step = 10;
            }

            if(delegate!= null){
                delegate.getNewContent(new DiagramModel(value, step));
            }

        }

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




    interface bigContentHelper{
        void getNewContent(DiagramModel model);
    }

}
