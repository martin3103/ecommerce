package sg.apptreme.e_commerce.util.common;

import android.os.Handler;

/**
 * Created by martinluternainggolan on 9/13/16.
 */
public abstract class ThreadRunnableTask {

    public Handler handler;
    public Runnable runnable;
    private boolean isRun = false, isCalled = false;

    public abstract void executeRunnableTask();

    public void executeThread() {
        handler.post(runnable);
    }

    public void executeDelayedThread(int miliSecond) {
        isRun = true;
        handler.postDelayed(runnable, miliSecond);
    }

    public ThreadRunnableTask() {
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                isCalled = true;
                removeRunnableTask();
                executeRunnableTask();
            }
        };
    }

    /**
     * always call this on onPause and onDestroy method
     */
    public void removeRunnableTask() {
        isRun = false;
        isCalled = false;
        handler.removeCallbacks(runnable);
    }

}

