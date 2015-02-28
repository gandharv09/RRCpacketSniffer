package com.example.RRCpacketSniffer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketSniffer extends Service {
    public PacketSniffer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Let it continue running until it is stopped.
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        DataOutputStream dos = null;
        DataInputStream dis = null;
        DataInputStream des = null;
        Process process = null;

        boolean errorStream=false;

        String[] commands = new String[2];
        commands[0] = "chmod 777 /data/data/com.example.RRCpacketSniffer/C_packetCapture";
        commands[1] = "/data/data/com.example.RRCpacketSniffer/C_packetCapture";

        try {
            process = Runtime.getRuntime().exec("su -c sh");
           // process = Runtime.getRuntime().exec(commands[0]);
            //process = Runtime.getRuntime().exec(commands[1]);
        } catch (IOException io) {
            Log.e("TestApp:Exception:", io.toString());
        }
        dos = new DataOutputStream(process.getOutputStream());
        dis = new DataInputStream(process.getInputStream());
        if(errorStream)
            des = new DataInputStream(process.getErrorStream());
        try {
            String result = "";
            for (String single : commands) {
                dos.writeBytes(single + "\n");
                dos.flush();
            }
            dos.writeBytes("exit\n");
            dos.flush();
//process.waitFor();
            Log.d("done","Commands");
            while(dis.available() > 0){
                result += dis.readLine() + "\n";
                Log.d("output",dis.readUTF());
            }
            if(errorStream)
            {
                while(des.available() > 0)
                    result += des.readLine() + "\n";
            }
            dis.close();
            dos.close();
            if(errorStream)
                des.close();
           // return result;
        } catch (Exception e) {
            Log.e ("Error",e.getMessage());
        }
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
