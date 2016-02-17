package com.example.kamalrajsd.tracksensetest;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.os.Process;
import android.widget.Toast;

public class SMSReadService extends Service {

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;
    private final String LOG_TAG = "SMSReadService";

    public SMSReadService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        Message msg = mServiceHandler.obtainMessage();
        mServiceHandler.sendMessage(msg);

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(LOG_TAG, " onCreate - begins");
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        Log.i(LOG_TAG, " onCreate - thread created");
        // Get the HandlerThread's Looper and use it for our Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);


    }

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        //message handler for the thread
        @Override
        public void handleMessage(Message msg) {
            //the handler loops indefinitely
            loopForever();
        }
    }

    //loops indefinitely with sleep time of 20 seconds in each loop
    public void loopForever()
    {
        while(true)
            try{
                Thread.sleep(20000);
            }
            catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }

    }




    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
