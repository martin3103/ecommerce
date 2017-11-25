package sg.apptreme.e_commerce.util.common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.crashlytics.android.Crashlytics;
import com.fusion_payments.beruang.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by martinluternainggolan on 11/24/16.
 */

public class BeruangConfiguration {
    public Activity activity;
    public File myPath;
    public String fileName;

    public BeruangConfiguration(Activity activity) {
        this.activity = activity;
    }

    public void setupUI(View view) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(activity);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public boolean takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        myPath = new File("/sdcard/" + now + ".jpg");
        fileName = now + ".jpg";
        FileOutputStream fos;
        try {
            // create bitmap screen capture
            View v1 = activity.getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            fos = new FileOutputStream(myPath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 10, fos);
            fos.flush();
            fos.close();
            MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, "Screen", "screen");
//            openScreenshot(myPath);
            return true;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public void openScreenshot() throws Exception {
        try {
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_VIEW);
////        Uri uri = Uri.fromFile(myPath);
//            Uri uri = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".provider", myPath);
//            intent.setDataAndType(uri, "image/*");
//            activity.startActivity(intent);

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "content://media/internal/images/media"));
            activity.startActivity(intent);
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } catch (Exception e) {
            Crashlytics.log(Log.ERROR, "BeruangConfiguration", e.getMessage());
        }
    }
}
