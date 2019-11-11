package com.example.pezyandroid.louise.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.pezyandroid.R;

public class AlertDialogHelper {
    private static AlertDialogHelper instance = new AlertDialogHelper();
    private static Context fContext;
    private static AlertDialog fDialog;
    private static AlertDialog.Builder fDialogBuilder;

    public static AlertDialogHelper init(Context context) {
        fContext = context;
        return instance;
    }

    public void dialogDismiss(){
        if(fDialog != null && fDialog.isShowing()){
            fDialog.dismiss();
        }
    }

    /**
     * Block UI
     * @param message | Message that can communicate to user.
     * @return AlertDialogHelper instance
     */
    public AlertDialogHelper blockUI(String message){
        dialogDismiss();
        fDialogBuilder = new AlertDialog.Builder(fContext);
        LayoutInflater inflater = (LayoutInflater) fContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(R.layout.layout_loading_dialog, null);
        TextView txtMsg = (TextView)v.findViewById(R.id.txtStatus);
        txtMsg.setText(message);
        fDialogBuilder.setCancelable(false).setView(v);
        fDialog = fDialogBuilder.create();
        fDialog.show();
        return instance;
    }


    public AlertDialogHelper loadCustomUI(boolean isCancelable, int layout, LoadCustomUI callback){
        dialogDismiss();
        fDialogBuilder = new AlertDialog.Builder(fContext);
        LayoutInflater inflater = (LayoutInflater) fContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v =  inflater.inflate(layout, null);
        v = callback.bind(v);
        fDialogBuilder.setCancelable(isCancelable).setView(v);
        fDialog = fDialogBuilder.create();
        fDialog.show();
        return instance;
    }


    public AlertDialogHelper loadCustomUIWithAutoBind(boolean isCancelable, LoadCustomUI callback) {
        dialogDismiss();
        fDialogBuilder = new AlertDialog.Builder(fContext);
        LayoutInflater inflater = (LayoutInflater) fContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = callback.autoBind(inflater);
        fDialogBuilder.setCancelable(isCancelable).setView(v);
        fDialog = fDialogBuilder.create();
        fDialog.show();
        return instance;
    }



    /**
     * Dialog that want answer 'confirm' or 'dismiss'
     * @param title Title message.
     * @param msg Message details.
     * @param cancelable Set it 'true' if can cancelable.
     * @param callback Call back for on any event.
     * @return AlertDialogHelper instance.
     */
    public AlertDialogHelper dialogConfirmDismiss(String title, String msg, boolean cancelable, DialogEventListener callback){
        Log.wtf("MYAPP", "dialogOKCancel()");
        dialogDismiss();
        fDialogBuilder = new AlertDialog.Builder(fContext);
        fDialogBuilder.setTitle(title)
                .setMessage(msg)
                .setCancelable(cancelable);

        setPositiveBtn("Confirm", callback);
        setNegativeBtn("Dismiss", callback);
        fDialog = fDialogBuilder.create();
        fDialog.show();
        return instance;
    }

    /**
     * Dialog that want answer 'yes' or 'dismiss'
     * @param title Title message.
     * @param msg Message details.
     * @param cancelable Set it 'true' if can cancelable.
     * @param callback Call back for on any event.
     * @return AlertDialogHelper instance.
     */
    public AlertDialogHelper dialogYesDismiss(String title, String msg, boolean cancelable, DialogEventListener callback){
        Log.wtf("MYAPP", "dialogOKCancel()");
        dialogDismiss();
        fDialogBuilder = new AlertDialog.Builder(fContext);
        fDialogBuilder.setTitle(title)
                .setMessage(msg)
                .setCancelable(cancelable);

        setPositiveBtn("Yes", callback);
        setNegativeBtn("Dismiss", callback);
        fDialog = fDialogBuilder.create();
        fDialog.show();
        return instance;
    }

    /**
     * Dialog that want answer 'yes' or 'no' or 'cancel'
     * @param title Title message.
     * @param msg Message details.
     * @param cancelable Set it 'true' if can cancelable.
     * @param callback Call back for on any event.
     * @return AlertDialogHelper instance.
     */
    public AlertDialogHelper dialogYesNoCancel(String title, String msg, boolean cancelable, DialogEventListener callback){
        Log.wtf("MYAPP", "dialogOKCancel()");
        dialogDismiss();
        fDialogBuilder = new AlertDialog.Builder(fContext);
        fDialogBuilder.setTitle(title)
                .setMessage(msg)
                .setCancelable(cancelable);

        setPositiveBtn("Yes", callback);
        setNeutralBtn("No", callback);
        setNegativeBtn("Cancel", callback);
        fDialog = fDialogBuilder.create();
        fDialog.show();
        return instance;
    }

    /**
     * Dialog that want answer 'ok' or 'cancel'
     * @param title Title message.
     * @param msg Message details.
     * @param cancelable Set it 'true' if can cancelable.
     * @param callback Call back for on any event.
     * @return AlertDialogHelper instance.
     */
    public AlertDialogHelper dialogOKCancel(String title, String msg, boolean cancelable, DialogEventListener callback){
        Log.wtf("MYAPP", "dialogOKCancel()");
        dialogDismiss();
        fDialogBuilder = new AlertDialog.Builder(fContext);
        fDialogBuilder.setTitle(title)
                .setMessage(msg)
                .setCancelable(cancelable);

        setPositiveBtn("OK", callback);
        setNegativeBtn("Cancel", callback);
        fDialog = fDialogBuilder.create();
        fDialog.show();
        return instance;
    }

    /**
     * Dialog that want answer 'confirm' or 'cancel'
     * @param title Title message.
     * @param msg Message details.
     * @param cancelable Set it 'true' if can cancelable.
     * @param callback Call back for on any event.
     * @return AlertDialogHelper instance.
     */
    public AlertDialogHelper dialogConfirmCancel(String title, String msg, boolean cancelable, DialogEventListener callback){
        Log.wtf("MYAPP", "dialogConfirmCancel()");
        dialogDismiss();
        fDialogBuilder = new AlertDialog.Builder(fContext);
        fDialogBuilder.setTitle(title)
                .setMessage(msg)
                .setCancelable(cancelable);

        setPositiveBtn("Confirm", callback);
        setNegativeBtn("Cancel", callback);
        fDialog = fDialogBuilder.create();
        fDialog.show();
        return instance;
    }

    /**
     * Dialog OK
     * @param title Title message.
     * @param msg Message details
     * @param cancelable Set true if can cancelable.
     * @param callback Call back for on any event.
     * @return AlertDialogHelper instance.
     */
    public AlertDialogHelper dialogOK(String title, String msg, boolean cancelable, DialogEventListener callback){
        Log.wtf("MYAPP", "dialogOK()");
        dialogDismiss();
        fDialogBuilder = new AlertDialog.Builder(fContext);
        fDialogBuilder.setTitle(title)
                .setMessage(msg)
                .setCancelable(cancelable);

        setPositiveBtn("OK", callback);
        fDialog = fDialogBuilder.create();
        fDialog.show();
        return instance;
    }

    /**
     * Dialog that want answer 'yes' or 'no'
     * @param title Title message
     * @param msg Message details
     * @param cancelable Set true if can cancelable
     * @param callback Call back for on any event.
     * @return AlertDialogHelper instance.
     */
    public AlertDialogHelper dialogYesNo(String title, String msg, boolean cancelable, DialogEventListener callback){
        Log.wtf("MYAPP", "dialogYesNo()");
        dialogDismiss();
        fDialogBuilder = new AlertDialog.Builder(fContext);
        fDialogBuilder.setTitle(title)
                .setMessage(msg)
                .setCancelable(cancelable);

        setPositiveBtn("Yes", callback);
        setNegativeBtn("No", callback);
        fDialog = fDialogBuilder.create();
        fDialog.show();
        return instance;
    }

    /**
     * Core process area...
     */

    private void setNegativeBtn(int stringResource, final DialogEventListener callBack){
        if(fDialogBuilder != null){
            fDialogBuilder.setNegativeButton(stringResource, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(callBack != null){
                        callBack.onNegativeClick(dialog, which);
                    }
                }
            });
        }
    }

    private void setNegativeBtn(String negativeMsg, final DialogEventListener callBack){
        if(fDialogBuilder != null){
            fDialogBuilder.setNegativeButton(negativeMsg, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(callBack != null){
                        callBack.onNegativeClick(dialog, which);
                    }
                }
            });
        }
    }

    private void setNeutralBtn(int stringResource, final DialogEventListener callBack){
        if(fDialogBuilder != null){
            fDialogBuilder.setNeutralButton(stringResource, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(callBack != null){
                        callBack.onNeutralClick(dialog, which);
                    }
                }
            });
        }
    }

    private void setNeutralBtn(String neutralMsg, final DialogEventListener callBack){
        if(fDialogBuilder != null){
            fDialogBuilder.setNeutralButton(neutralMsg, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(callBack != null){
                        callBack.onNeutralClick(dialog, which);
                    }
                }
            });
        }
    }

    private void setPositionBtn(int stringResource, final DialogEventListener callBack){
        if(fDialogBuilder != null){
            fDialogBuilder.setPositiveButton(stringResource, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(callBack != null){
                        callBack.onPositiveClick(dialog, which);
                    }
                }
            });
        }
    }

    private void setPositiveBtn(String positiveMsg, final DialogEventListener callBack){
        if(fDialogBuilder != null){
            fDialogBuilder.setPositiveButton(positiveMsg, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(callBack != null){
                        callBack.onPositiveClick(dialog, which);
                    }
                }
            });
        }
    }


}