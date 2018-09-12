package com.Icar05.diagramview;

import java.util.List;

public abstract class DiagramDataHolder {


    private List<Integer> list;

    private String errorMessage;





    public final String getErrorMessage() {
        return errorMessage;
    }

    public final boolean setup(){
        list = fillList();
        return checkNull() && checkSteps() && checkContentSize();
    }

    private boolean checkContentSize() {
        if(list.size() < 1){
            errorMessage = "Size of content should be > 1";
            return false;
        }

        return true;
    }

    private boolean checkSteps() {
        for(int i = 0; i< list.size(); i++){
            if(list.get(i) < 1){
                errorMessage = "Each steps should be > 1";
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

    protected abstract List<Integer> fillList();


    public final List<Integer> getContent() {
        return list;
    }

    public final int getMaxStep() {
        return calculateMaxStep(list);
    }


    private  Integer calculateMaxStep(List<Integer> list) {

        int maxValue = 0;

        for(int i = 0; i< list.size(); i++){
            if(list.get(i) > maxValue){
                maxValue = list.get(i);
            }
        }

        return maxValue;
    }
}
