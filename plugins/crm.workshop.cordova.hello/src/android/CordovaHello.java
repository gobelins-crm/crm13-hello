package crm.workshop.cordova.hello;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CordovaHello extends CordovaPlugin {

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (!"sayHello".equals(action)) {
            return false;
        }

        final String name = args.optString(0, "unknown");
        win(name, callbackContext, false);

        return true;
    }

    public void win(String name, CallbackContext callbackContext,
                    boolean keepCallback) throws JSONException {
        PluginResult result = new PluginResult(PluginResult.Status.OK, sayHello(name));
        result.setKeepCallback(keepCallback);
        callbackContext.sendPluginResult(result);
    }

    public void fail(int code, String msg, CallbackContext callbackContext,
                     boolean keepCallback) {
        JSONObject obj = new JSONObject();
        String backup = null;
        try {
            obj.put("code", code);
            obj.put("message", msg);
        } catch (JSONException e) {
            obj = null;
            backup = "{'code':" + code + ",'message':'"
                    + msg.replaceAll("'", "\'") + "'}";
        }
        PluginResult result;
        if (obj != null) {
            result = new PluginResult(PluginResult.Status.ERROR, obj);
        } else {
            result = new PluginResult(PluginResult.Status.ERROR, backup);
        }

        result.setKeepCallback(keepCallback);
        callbackContext.sendPluginResult(result);
    }

    private JSONObject sayHello(String name) throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("greetings", "Hello " + name + ", from android!");
        return obj;
    }
}
