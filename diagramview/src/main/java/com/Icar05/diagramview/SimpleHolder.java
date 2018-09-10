package com.Icar05.diagramview;

import java.util.List;

public class SimpleHolder extends DiagramDataHolder {

    private List<DiagramModel> list;

    public SimpleHolder(List<DiagramModel> list) {
        this.list = list;
    }

    @Override
    protected List<DiagramModel> fillList() {
        return list;
    }

}