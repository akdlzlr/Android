package com.example.student.doit10_5_capture;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by student on 2018-11-23.
 */

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback{

    private SurfaceHolder mHolder;
    private Camera camera = null;

    public CameraSurfaceView(Context context){
        super(context);

        mHolder=getHolder();
        mHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        camera = Camera.open();

        try{
            camera.setPreviewDisplay(mHolder);
        }catch (Exception e){

        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public boolean capture(Camera.PictureCallback handler){
        if(camera != null){
            camera.takePicture(null,null,handler);
            return true;
        }else{
            return false;
        }
    }
}
