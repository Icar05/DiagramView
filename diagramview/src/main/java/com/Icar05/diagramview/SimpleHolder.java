package com.Icar05.diagramview;

import java.util.List;

public class SimpleHolder extends DiagramDataHolder {

    private List<Integer> list;

    public SimpleHolder(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected List<Integer> fillList() {
        return list;
    }

}