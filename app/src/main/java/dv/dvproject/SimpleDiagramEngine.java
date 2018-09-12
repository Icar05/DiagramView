package dv.dvproject;

import com.Icar05.diagramview.DiagramEngine;
import com.Icar05.diagramview.DiagramView;

public class SimpleDiagramEngine extends DiagramEngine implements BigContentHelper.bigContentHelper {

    public SimpleDiagramEngine(DiagramView view, int delay) {
        super(view, delay);
    }

    public SimpleDiagramEngine(DiagramView view) {
        super(view);
    }

    @Override
    public void getNewContent(int model) {
        addValue(model);
    }
}
