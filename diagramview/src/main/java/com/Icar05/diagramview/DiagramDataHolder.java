package com.Icar05.diagramview;

import java.util.List;

public abstract class DiagramDataHolder {


    private List<DiagramModel> list;



    private String errorMessage;


    public final String getErrorMessage() {
        return errorMessage;
    }

    public final boolean setup(){
        list = fillList();
        return checkNull() && checkValues() && checkSteps() && checkContentSize();
    }

    private boolean checkContentSize() {
        if(list.size() < 1){
            errorMessage = "Size of content should be > 1";
            return false;
        }

        return true;
    }

    private boolean checkSteps() {
        for(DiagramModel model : list){
            if(model.getStep() < 1){
                errorMessage = "Each steps should be > 1";
                return false;
            }
        }
        return true;
    }

    private boolean checkValues() {

        for(DiagramModel model : list){
            if(model.getValue() < 1){
                errorMessage = "Each values should be > 1";
                return false;
            }
        }
        return true;
    }

    private boolean checkNull() {

        if(list == null){
            errorMessage = "List of content should not be null!";
            return false;
        }

        return true;
    }

    protected abstract List<DiagramModel> fillList();


    public final List<DiagramModel> getContent() {
        return list;
    }

    public final int getMaxStep() {
        return calculateMaxStep(list);
    }


    private  Integer calculateMaxStep(List<DiagramModel> list) {

        int maxValue = 0;

        for(DiagramModel model : list){
            if(model.getStep() > maxValue){
                maxValue = model.getStep();
            }
        }
        return maxValue;
    }
}
