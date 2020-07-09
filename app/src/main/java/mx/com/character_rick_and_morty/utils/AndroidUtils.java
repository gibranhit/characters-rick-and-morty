package mx.com.character_rick_and_morty.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.widget.ProgressBar;

import mx.com.character_rick_and_morty.R;

import static android.view.Window.FEATURE_NO_TITLE;

public class AndroidUtils {
    private AndroidUtils() {
    }

    public static Dialog configProgressBar(Activity context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_loader);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        ProgressBar progressd = dialog.findViewById(R.id.progress);
        progressd.setIndeterminateTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.colorPrimary)));
        dialog.setCancelable(false);
        return dialog;
    }

}
