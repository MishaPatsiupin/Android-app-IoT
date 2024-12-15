package com.example.home.ui.device;

import org.json.JSONObject;

public class Device {
    private String name;
    private String type;


    private String localIP;
    private String SSID;
    private String password;

    private JSONObject data;

    public Device(String name, String type, String localIP) {
        this.name = name;
        this.type = type;
        this.localIP = localIP;
    }


    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLocalIP() {
        return localIP;
    }
}
