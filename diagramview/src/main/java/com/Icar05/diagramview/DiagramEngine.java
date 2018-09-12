package com.Icar05.diagramview;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/*
  simple helper for animate
  graphic.

  there are three main methods:

   start()

   stop()

   addValue()
 */
public class DiagramEngine  {




    private DiagramView view;

    private List<Integer> queryContent = new ArrayList<>();

    private boolean running = false;

    private boolean finish = false;

    private static final int DELAY = 100;

    private MyHandler handler = new MyHandler(this);





    public DiagramEngine(DiagramView view) {
        this.view = view;
        initEngine();
    }



    //main part of animation
    private void initEngine() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (!finish){

                    if(running){
                        Message msg = handler.obtainMessage();
                        msg.what = DELAY;
                        handler.sendMessage(msg);

                        try {
                            Thread.sleep(DELAY);
                        } catch (InterruptedException e) {
                            printLog("error, "+e.getMessage());
                        }
                    }

                }

            }
        });

       thread.start();

    }




   //finish animation
   public void start(){
        running = true;
   }


   //start animation
   public void stop(){
        running = false;
        finish = true;
   }


    /*
     just add new frame
     */

    public void addValue(Integer value){
        int maxStep = view.getDataHolder().getMaxStep();
        if(checkStep(maxStep, value)){
            queryContent.add(value);
        }
    }

    /*
      get fresh content
     */
    private  void refresh(){
        if(queryContent.size() > 0){
            Integer current = queryContent.get(0);
            refreshContent(current);
            queryContent.remove(0);
        }
    }


    /*
       just check new frame, if match - reload view
     */

    private void refreshContent(Integer current) {

        List<Integer> freshContent;

        freshContent = pushNewData(view.getDataHolder().getContent(), current);
        view.setContent(new SimpleHolder(freshContent));
    }

    /*
      just replace new item, and push all
      previous items
     */

    private List<Integer> pushNewData(List<Integer> content, Integer current) {
        List<Integer> list = new ArrayList<>();

        for(int i = 1; i< content.size(); i++){
            list.add(content.get(i));
        }

        list.add(current);

        return list;
    }
    /*
      check if new value match
     */

    private boolean checkStep(int maxStep, int step) {
        return maxStep >= step;
    }


    /*
     this just log
     */
    private  void printLog(String message){
        Log.d("diagramEngine", message);
    }


    private  static class MyHandler extends Handler {

        private final WeakReference<DiagramEngine> myClassWeakReference;

        MyHandler(DiagramEngine myClassInstance) {
            myClassWeakReference = new WeakReference<>(myClassInstance);
        }

        @Override
        public void handleMessage(Message msg) {
            if(msg.what== DELAY){
                myClassWeakReference.get().refresh();
            }
            super.handleMessage(msg);
        }
    }

}
