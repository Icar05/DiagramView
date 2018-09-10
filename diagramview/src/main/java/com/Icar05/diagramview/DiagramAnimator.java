package com.Icar05.diagramview;

import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;
import java.util.List;



/*
  help to create animation
 */
public class DiagramAnimator {




    private DiagramView view;

    private List<DiagramModel> modelList = new ArrayList<>();

    private int mValue = -1;

    private String errorMessage;

    private int mDuration;




    public DiagramAnimator(DiagramView view, int duration) {
        this.view = view;
        this.mDuration = duration;
    }




    public void animate(List<SimpleHolder> holders){

        mValue = -1;
        setupHolders(holders);

        if(checkEquelStepHeight(holders)){
            animateAll(createAllFrameList(holders));
        }else{
            throw new DiagramException(errorMessage);
        }

    }




    private void animateAll(@NonNull final List<DiagramModel> list){

        DiagramDataHolder main = view.getDataHolder();
        if(main != null){
            main.setup();
            modelList = main.getContent();
            getAnimator(list).start();
        }
    }


    private ValueAnimator getAnimator(final List<DiagramModel> list){
        final ValueAnimator va = ValueAnimator.ofInt(0, list.size() -1 );

        va.setDuration(mDuration);
        va.setInterpolator(new LinearInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                Log.d("animTest", "value "+value);
                iterateValue(value, list.get(value));
            }
        });

        return va;
    }




    private void iterateValue(int value, DiagramModel model) {
        if(mValue != value){
            modelList = push(modelList, model);
            view.setContent(new SimpleHolder(modelList));
            mValue = value;
        }
    }

    private List<DiagramModel> createAllFrameList(List<SimpleHolder> holders) {
        List<DiagramModel> list = new ArrayList<>();
        for(SimpleHolder simpleHolder: holders){
            list.addAll(simpleHolder.getContent());
        }

        return list;
    }



    private List<DiagramModel> push(List<DiagramModel> source, DiagramModel diagramModel){
        List<DiagramModel> list = new ArrayList<>();

        for(int i = 1; i< source.size(); i++){
            list.add(source.get(i));
        }

        list.add(diagramModel);
        return list;
    }



    private boolean checkEquelStepHeight(List<SimpleHolder> holders) {
        int maxStep = holders.get(0).getMaxStep();

        for(SimpleHolder holder : holders){
            if(holder.getMaxStep() != maxStep){
                errorMessage = "Max values should be equels themself!";
                return false;
            }
        }

        return true;
    }


    private void setupHolders(List<SimpleHolder> holders) {
        for(SimpleHolder holder: holders){
            holder.setup();
        }
    }
}
