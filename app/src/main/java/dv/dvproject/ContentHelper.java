package dv.dvproject;

import com.Icar05.diagramview.DiagramDataHolder;
import com.Icar05.diagramview.DiagramModel;

import java.util.ArrayList;
import java.util.List;

public class ContentHelper extends DiagramDataHolder {


    private int mMin, mMax;

    public ContentHelper(int min, int max) {
         this.mMax = max;
         this.mMin = min;
    }

    @Override
    protected List<DiagramModel> fillList() {
            List<DiagramModel> list = new ArrayList<>();


            for(int i = 0; i < 20; i++){
                int value = (int) ((Math.random()*((mMax-mMin)+1))+mMin);
                int step = (int) ((Math.random()*((8-1)+1))+1);
                list.add(new DiagramModel(value,step ));
            }

            return list;
    }
}
