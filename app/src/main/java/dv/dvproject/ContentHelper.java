package dv.dvproject;

import com.Icar05.diagramview.DiagramDataHolder;

import java.util.ArrayList;
import java.util.List;

public class ContentHelper extends DiagramDataHolder {


    @Override
    protected List<Integer> fillList() {
            List<Integer> list = new ArrayList<>();

            final int maxValue = 8;

            for(int i = 0; i < 20; i++){
                int step = (int) ((Math.random()*((maxValue-1)+1))+1);
                list.add(step);
            }

            return list;
    }
}
