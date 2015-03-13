package com.iiitd.RRCpacketSniffer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {

	Intent measure_intent ;
	PackageManager packageManager;
	Button button1;
	Button button2;
    Spinner spinner2;
    String result;
    int currentpos = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		

		packageManager = this.getPackageManager();
		button1 = (Button) findViewById(R.id.button1);
		button2 = (Button) findViewById(R.id.button2);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parentView,
                                       View selectedItemView, int position, long id) {
                // Object item = parentView.getItemAtPosition(position);

                currentpos = spinner2.getSelectedItemPosition();
                Log.d("action",currentpos+"");

            }

            public void onNothingSelected(AdapterView<?> arg0) {// do nothing
            }

        });
        final Process  process;
		
		button1.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

                switch (currentpos) {

                    case 0:
                    Log.d("ACTION:","BOTH");
                    RunPacketCapture();
                    if (packageManager != null) {
                        measure_intent = new Intent(Intent.ACTION_MAIN).
                                addCategory(Intent.CATEGORY_LAUNCHER).setComponent(new ComponentName(
                                "com.sec.android.app.servicemodeapp",
                                "com.sec.android.app.servicemodeapp.ServiceModeApp"));
                        measure_intent.putExtra("keyString", "0011");
                        ResolveInfo resolved = packageManager.resolveActivity(
                                measure_intent, PackageManager.MATCH_DEFAULT_ONLY);
                        if (resolved != null) {
                            startActivity(measure_intent);
                            return;
                        } else {
                            Toast.makeText(getApplicationContext(), "version not implemented", Toast.LENGTH_LONG).show();
                        }
                    }
                    Toast t = Toast.makeText(getApplicationContext(),
                            "Both Sniffer started", Toast.LENGTH_SHORT);
                    t.show();
                    break;
                    case 1:
                        Log.d("ACTION:","PACKET");
                        RunPacketCapture();
                        Toast t1 = Toast.makeText(getApplicationContext(),
                                "Packet Sniffer started", Toast.LENGTH_SHORT);
                        t1.show();
                        break;
                    case 2:
                        Log.d("ACTION:","RRC");
                        if (packageManager != null) {
                            measure_intent = new Intent(Intent.ACTION_MAIN).
                                    addCategory(Intent.CATEGORY_LAUNCHER).setComponent(new ComponentName(
                                    "com.sec.android.app.servicemodeapp",
                                    "com.sec.android.app.servicemodeapp.ServiceModeApp"));
                            measure_intent.putExtra("keyString", "0011");
                            ResolveInfo resolved = packageManager.resolveActivity(
                                    measure_intent, PackageManager.MATCH_DEFAULT_ONLY);
                            if (resolved != null) {
                                startActivity(measure_intent);
                                return;
                            } else {
                                Toast.makeText(getApplicationContext(), "version not implemented", Toast.LENGTH_LONG).show();
                            }
                        }
                        Toast t2 = Toast.makeText(getApplicationContext(),
                                "RRC Sniffer started", Toast.LENGTH_SHORT);
                        t2.show();
                        break;
                }
            }
		});

		button2.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

                switch (currentpos) {

                    case 0:
                    Log.d("CLOSING","BOTH");
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            BufferedWriter bw = null;
                            String line;
                            String outText;
                            int i;
                            try {
                                File file1 = new File("mnt/sdcard/dump.txt");
                                file1.createNewFile();

                                OutputStreamWriter os = new OutputStreamWriter(
                                        new FileOutputStream(file1));

                                bw = new BufferedWriter(os);

                                Process process = Runtime.getRuntime().exec(
                                        "logcat -v threadtime -s ServiceMode");
                                int length = process.toString().length();

                                System.out.println("Length" + length);

                                BufferedReader reader = new BufferedReader(
                                        new InputStreamReader(process.getInputStream()));

                                StringBuffer output1 = new StringBuffer();

                                while ((line = reader.readLine()) != null) {

                                    bw.write(line);
                                    bw.newLine();

                                }

                            } catch (IOException e) {
                            } finally {

                                try {
                                    if (bw != null) {
                                        bw.close();

                                        Toast.makeText(getBaseContext(),
                                                "Done writing both log and dump file",
                                                Toast.LENGTH_SHORT).show();


                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }

                    };


                    new Thread(runnable).start();

                    Toast.makeText(getBaseContext(),
                            "Done writing to dump file",
                            Toast.LENGTH_SHORT).show();
                    stopService();
                    break;

                   case 1:
                       Log.d("CLOSING","PACKET");
                       stopService();
                       Toast.makeText(getBaseContext(),
                               "Done writing log file",
                               Toast.LENGTH_SHORT).show();

                       break;
                    case 2:
                        Log.d("CLOSING","RRC");
                        Runnable runnable1 = new Runnable() {
                            @Override
                            public void run() {
                                BufferedWriter bw = null;
                                String line;
                                String outText;
                                int i;
                                try {
                                    File file1 = new File("mnt/sdcard/dump.txt");
                                    file1.createNewFile();

                                    OutputStreamWriter os = new OutputStreamWriter(
                                            new FileOutputStream(file1));

                                    bw = new BufferedWriter(os);

                                    Process process = Runtime.getRuntime().exec(
                                            "logcat -v threadtime -s ServiceMode");
                                    int length = process.toString().length();

                                    System.out.println("Length" + length);

                                    BufferedReader reader = new BufferedReader(
                                            new InputStreamReader(process.getInputStream()));

                                    StringBuffer output1 = new StringBuffer();

                                    while ((line = reader.readLine()) != null) {

                                        bw.write(line);
                                        bw.newLine();

                                    }

                                } catch (IOException e) {
                                } finally {

                                    try {
                                        if (bw != null) {
                                            bw.close();

                                            Toast.makeText(getBaseContext(),
                                                    "Done writing to dump file",
                                                    Toast.LENGTH_SHORT).show();


                                        }
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }

                        };


                        new Thread(runnable1).start();

                        Toast.makeText(getBaseContext(),
                                "Done writing to dump file",
                                Toast.LENGTH_SHORT).show();
                        break;

                }


            }
		});

	}

	public void RunPacketCapture()
    {
        try{
        BufferedInputStream is = new BufferedInputStream(getAssets().open(
                "C_packetCapture"));
        int ib = -1;
        Log.d("Log3GState:", "Accessing  file");
        File file = new File("/data/data/com.example.RRCpacketSniffer/C_packetCapture");
        Log.d("Log3GState:", "Creating  file");
        if(file.exists()){
            file.delete();
        }
        if(file.createNewFile())
        {
            Log.d("Log3GState:", "file created");
        }
        else
        {
            Log.e("Log3GState:", "file existed");
        }
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));

        Log.d("Log3GState:", "Write executable  file");
        while ((ib = is.read()) != -1) {
            os.write(ib);
        }
        is.close();
        os.close();
    } catch (Exception e) {
        Log.d("Error:", e.getMessage());

    }


    Log.d("Log3GState:","Executing as root");
    // ---till here
    startService();



}
    public void startService() {
        startService(new Intent(getBaseContext(), PacketSniffer.class));
    }

    // Method to stop the service
    public void stopService() {
        stopService(new Intent(getBaseContext(), PacketSniffer.class));
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
