package com.kiosk_app

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod

class KioskModeModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    private var devicePolicyManager: DevicePolicyManager
    private var adminComponent: ComponentName

    init {
        devicePolicyManager = reactContext.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
        adminComponent = ComponentName(reactContext, MyDeviceAdminReceiver::class.java)
    }

    override fun getName(): String {
        return "KioskMode"
    }

    @ReactMethod
    fun startLockTask() {
        val activity: Activity? = currentActivity
        activity?.startLockTask()
    }

    @ReactMethod
    fun stopLockTask() {
        val activity: Activity? = currentActivity
        activity?.stopLockTask()
    }

    @ReactMethod
    fun isLockTaskPermitted(): Boolean {
        return devicePolicyManager.isLockTaskPermitted(currentActivity?.packageName)
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    @ReactMethod
    fun setLockTaskPackages(packages: Array<String>) {
        devicePolicyManager.setLockTaskPackages(adminComponent, packages)
    }
}
