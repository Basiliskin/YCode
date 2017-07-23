package com.ycode.plugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import android.content.Context;
import android.widget.Toast;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

import caragulak.m8s.ycodelib.YCodeActivity;

public class YCodeScanner extends CordovaPlugin {
	
	public final int MY_OP = 11;
	private CallbackContext callback = null;
	
	@Override
	public boolean execute(
		String action, 
		JSONArray args, 
		CallbackContext callbackContext
	) throws JSONException {
		if ("scan".equals(action)) {
		  echo(args.getString(0), callbackContext);
		  return true;
		}else if (action.equals("openScanner")) {
			callback = callbackContext;
			cordova.setActivityResultCallback (this);
			String message = args.getString(0);
			this.openScanner(message, callbackContext);
			return true;
		}

		return false;
	}
	private void showToast(final String message) {
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(webView.getContext()/*cordova.getActivity()*/, message, Toast.LENGTH_LONG).show();
			}
		});
	}
	private void openScanner(String message, CallbackContext callbackContext) {
		//showToast("openScanner");
		final CordovaPlugin that = this;
		final String apiKey = message;
		cordova.getThreadPool().execute(new Runnable() {
            public void run() {
				that.cordova.setActivityResultCallback(that);
				Intent intentScan = new Intent(that.cordova.getActivity().getBaseContext(), YCodeActivity.class);
				intentScan.putExtra("apiKey", apiKey);
				/*
				  intentScan.setAction("com.dynamsoft.dbr");
 
                // avoid calling other phonegap apps
                intentScan.setPackage(that.cordova.getActivity().getApplicationContext().getPackageName());
 
				*/
				that.cordova.startActivityForResult(that, intentScan, MY_OP);
			}
		});
		
		PluginResult result = new PluginResult(PluginResult.Status.NO_RESULT);
		result.setKeepCallback(true);
		callback.sendPluginResult(result);
		
		/*
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
		*/
    }
	@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
		//showToast("onActivityResult="+requestCode+","+resultCode);
		JSONObject r = new JSONObject();
        if (requestCode == MY_OP) {
            if(resultCode == this.cordova.getActivity().RESULT_OK){
                String code = data.getStringExtra("text");
				PluginResult result = new PluginResult(PluginResult.Status.OK, code);
				result.setKeepCallback(true);
				callback.sendPluginResult(result);
            }
            if (resultCode == this.cordova.getActivity().RESULT_CANCELED) {
                //Write your code if there's no result
				PluginResult result = new PluginResult(PluginResult.Status.ERROR, "no params returned successfully" );
				result.setKeepCallback(true);
				callback.sendPluginResult(result);
            }
        }
    }
	private void echo(
		String msg, 
		CallbackContext callbackContext
	) {
		if (msg == null || msg.length() == 0) {
		  callbackContext.error("Empty message!");
		} else {
		  Toast.makeText(
			webView.getContext(), 
			msg, 
			Toast.LENGTH_LONG
		  ).show();
		  callbackContext.success(msg);
		}
	}
}