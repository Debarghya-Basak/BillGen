package Utilities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import Models.PermissionModel;

public class PermissionManager extends AppCompatActivity {

    private static ArrayList<PermissionModel> permissionModelList;
    private static final int PERMISSION_REQ_CODE = 100;

    public static void permissionMaker(){
        permissionModelList = new ArrayList<>();

        permissionModelList.add(new PermissionModel("READ EXTERNAL STORAGE", Manifest.permission.READ_EXTERNAL_STORAGE, Build.VERSION_CODES.N, Build.VERSION_CODES.TIRAMISU));
        permissionModelList.add(new PermissionModel("WRITE EXTERNAL STORAGE", Manifest.permission.WRITE_EXTERNAL_STORAGE, Build.VERSION_CODES.N, Build.VERSION_CODES.TIRAMISU));
    }

    public static void permissionManager(Activity activity){

        permissionMaker();

        ArrayList<String> permissionToBeGranted = new ArrayList<>();
        for(int i=0;i<permissionModelList.size();i++)
            if(Build.VERSION.SDK_INT >= permissionModelList.get(i).permissionMinBuildVersion && Build.VERSION.SDK_INT <= permissionModelList.get(i).permissionMaxBuildVersion)
                if(ContextCompat.checkSelfPermission(activity.getApplicationContext(), permissionModelList.get(i).permission) != PackageManager.PERMISSION_GRANTED)
                    permissionToBeGranted.add(permissionModelList.get(i).permission);

        if(permissionToBeGranted.size() != 0) {
            String permissionToBeGrantedString[] = new String[permissionToBeGranted.size()];
            int i = 0;
            for (String permission : permissionToBeGranted)
                permissionToBeGrantedString[i++] = permission;

            //MakeToast.makeToast(activity.getApplicationContext(), "Please accept the permissions");
            ActivityCompat.requestPermissions(activity, permissionToBeGrantedString, PERMISSION_REQ_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_REQ_CODE:
                for(int i =0;i<grantResults.length;i++){
                    if(grantResults[i] == PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(getApplicationContext(), "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "PERMISSION NOT GRANTED", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
