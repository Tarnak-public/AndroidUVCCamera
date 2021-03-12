package com.lgh.test;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;

import java.util.HashMap;

public class CameraFinder {
    private final Context context;
    HashMap<String, UsbDevice> cameraDevices;
    private final UsbManager usbManager;

    public CameraFinder(Context context) {
        this.context = context;
        this.usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        cameraDevices = getUsbCameraDevices();
    }

    public HashMap<String, UsbDevice> getUsbCameraDevices() {
        HashMap<String, UsbDevice> deviceMap = usbManager.getDeviceList();
        HashMap<String, UsbDevice> cameraDevices = new HashMap<>();
        if (deviceMap != null) {
            int index = 0;
            for (UsbDevice usbDevice : deviceMap.values()) {
                if (usbDevice != null && 239 == usbDevice.getDeviceClass() && 2 == usbDevice.getDeviceSubclass())
                    cameraDevices.put(String.valueOf(index), usbDevice);
                index++;
            }
        }
        return cameraDevices;
    }
}
