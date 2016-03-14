package appewtc.masterung.enssystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by masterUNG on 1/19/16 AD.
 */
public class MyAlertDialog {

    public void MyDialog(Context context,
                         int intIcon,
                         String strTitle,
                         String strMessage) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(context);
        objBuilder.setIcon(intIcon);
        objBuilder.setTitle(strTitle);
        objBuilder.setMessage(strMessage);
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });
        objBuilder.show();
    }

}   // Main Class
