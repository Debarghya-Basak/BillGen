package com.dbtapps.billgen;

/*TO RUN ANDROID 13 EMULATOR (USING VULKAN DRIVER)......

GO TO COMMAND LINE AND TYPE

emulator -avd <name of the emulator> -feature -Vulkan
*/


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}