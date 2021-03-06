package com.example.hzmt.facedetectusb.CameraUtil;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.example.hzmt.facedetectusb.MyApplication;

import java.lang.ref.WeakReference;

public class IDCardReadHandler extends Handler {
    private final WeakReference<CameraActivity> mActivity;

    public IDCardReadHandler(CameraActivity activity) {
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        CameraActivity activity = mActivity.get();
        switch(msg.what){
            case IDCardReadThread.IDCARD_ERR_READERR:
                if (activity != null) {
                    activity.setHelpImgVisibility(View.INVISIBLE);
                    String errMsg = "身份证读卡失败!";
                    Toast.makeText(activity, errMsg, Toast.LENGTH_SHORT).show();
                    // 屏幕亮度及信息清理Timer
                    CameraActivity.startBrightnessWork(activity, activity.mInfoLayout);
                }

                //synchronized(CameraActivityData.fdvlock) {
                    CameraActivityData.idcardfdv_idcardstate = IDCardReadThread.IDCARD_ERR_READERR;
                //}
                break;
            case IDCardReadThread.IDCARD_READ_OK:
                if (activity != null)
                    activity.setHelpImgVisibility(View.INVISIBLE);
                break;
            case IDCardReadThread.IDCARD_ALL_OK:
                if (activity != null)
                    activity.setHelpImgVisibility(View.INVISIBLE);
                //synchronized(CameraActivityData.fdvlock) {
                    CameraActivityData.idcardfdv_idcardstate = IDCardReadThread.IDCARD_ALL_OK;
                //}
                break;
            default:
                break;
        }

    }
}
