package dv.dvproject;

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





    public Integer getRandomData(){
        int minStep = 1;
        int maxStep = 10;
        return minStep + (int) (Math.random() * maxStep);
    }

    public void emitData(int count){
        int minStep = 1;
        int maxStep = 10;


        for(int i = 0; i< count; i++) {
            int step = minStep + (int) (Math.random() * maxStep);

            if (i % 5 == 0) {
                step = 10;
            }

            if(delegate!= null){
                delegate.getNewContent(step);
            }

        }

    }

    public SimpleHolder getSource() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(4);
        list.add(3);
        list.add(7);
        list.add(2);
        list.add(8);
        list.add(3);
        list.add(6);


        return new SimpleHolder(list);
    }




    interface bigContentHelper{
        void getNewContent(int model);
    }

}
