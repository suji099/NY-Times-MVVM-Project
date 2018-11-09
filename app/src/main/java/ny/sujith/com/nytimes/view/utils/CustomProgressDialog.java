package ny.sujith.com.nytimes.view.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import ny.sujith.com.nytimes.R;

/**
 * Created by Sujith Chandran on 09-08-2018.
 */

public class CustomProgressDialog extends Dialog {

    Context mContext;
    Dialog progressDialog;

    public Dialog showDialog() {
        progressDialog = new CustomProgressDialog(mContext);
        ProgressBar progressBar = new ProgressBar(mContext);
        progressDialog.addContentView(progressBar, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        progressDialog.setCancelable(false);
        try {
            progressDialog.show();
        } catch (Exception e) {
            Log.e("Error" , "" + e.getMessage());
        }
        return progressDialog;
    }

    public void dismissDialog()
    {
        if(progressDialog !=null && progressDialog.isShowing())
        {
            progressDialog.dismiss();
        }
    }

    public CustomProgressDialog(@NonNull Context context) {
        super(context, R.style.NewDialog);
        this.mContext = context;
    }
}
