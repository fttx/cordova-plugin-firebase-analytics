package by.chemerisuk.cordova.firebase;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import by.chemerisuk.cordova.support.CordovaMethod;
import by.chemerisuk.cordova.support.ReflectiveCordovaPlugin;

import com.google.firebase.analytics.FirebaseAnalytics;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import java.util.Map;
import java.util.EnumMap;
import com.google.firebase.analytics.FirebaseAnalytics.ConsentType;
import com.google.firebase.analytics.FirebaseAnalytics.ConsentStatus;

public class FirebaseAnalyticsPlugin extends ReflectiveCordovaPlugin {
    private static final String TAG = "FirebaseAnalyticsPlugin";

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void pluginInitialize() {
        Log.d(TAG, "Starting Firebase Analytics plugin");
        Context context = this.cordova.getActivity().getApplicationContext();
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    @CordovaMethod
    protected void logEvent(CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        String name = args.getString(0);
        JSONObject params = args.getJSONObject(1);
        firebaseAnalytics.logEvent(name, parse(params));
        callbackContext.success();
    }

    @CordovaMethod
    protected void setUserId(CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        String userId = args.getString(0);
        firebaseAnalytics.setUserId(userId);
        callbackContext.success();
    }

    @CordovaMethod
    protected void setUserProperty(CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        String name = args.getString(0);
        String value = args.getString(1);
        firebaseAnalytics.setUserProperty(name, value);
        callbackContext.success();
    }

    @CordovaMethod
    protected void resetAnalyticsData(CordovaArgs args, CallbackContext callbackContext) {
        firebaseAnalytics.resetAnalyticsData();
        callbackContext.success();
    }

    @CordovaMethod
    protected void setEnabled(CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        boolean enabled = args.getBoolean(0);
        firebaseAnalytics.setAnalyticsCollectionEnabled(enabled);
        callbackContext.success();
    }

    @CordovaMethod
    public void setAnalyticsConsent(CordovaArgs args, CallbackContext callbackContext) {
        try {
        JSONObject params = args.getJSONObject(0);

        boolean allowAnalyticsStorage = params.optBoolean("GOOGLE_ANALYTICS_DEFAULT_ALLOW_ANALYTICS_STORAGE", true);
        boolean allowAdStorage = params.optBoolean("GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_STORAGE", true);
        boolean allowAdUserData = params.optBoolean("GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_USER_DATA", true);
        boolean allowAdPersonalizationSignals = params.optBoolean("GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_PERSONALIZATION_SIGNALS", true);

        Map<ConsentType, ConsentStatus> consentMap = new EnumMap<>(ConsentType.class);
        consentMap.put(ConsentType.ANALYTICS_STORAGE, allowAnalyticsStorage ? ConsentStatus.GRANTED : ConsentStatus.DENIED);
        consentMap.put(ConsentType.AD_STORAGE, allowAdStorage ? ConsentStatus.GRANTED : ConsentStatus.DENIED);
        consentMap.put(ConsentType.AD_USER_DATA, allowAdUserData ? ConsentStatus.GRANTED : ConsentStatus.DENIED);
        consentMap.put(ConsentType.AD_PERSONALIZATION, allowAdPersonalizationSignals ? ConsentStatus.GRANTED : ConsentStatus.DENIED);

        firebaseAnalytics.setConsent(consentMap);

        callbackContext.success();
        } catch (JSONException e) {
        callbackContext.error("Invalid JSON format: " + e.getMessage());
        }
    }

    @CordovaMethod
    protected void setCurrentScreen(CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        String screenName = args.getString(0);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, bundle);
        callbackContext.success();
    }

    @CordovaMethod
    protected void setDefaultEventParameters(CordovaArgs args, CallbackContext callbackContext) throws JSONException {
        JSONObject params = args.getJSONObject(0);
        firebaseAnalytics.setDefaultEventParameters(parse(params));
        callbackContext.success();
    }

    private static Bundle parse(JSONObject params) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator<String> it = params.keys();

        while (it.hasNext()) {
            String key = it.next();
            Object value = params.get(key);

            if (value instanceof String) {
                bundle.putString(key, (String)value);
            } else if (value instanceof Integer) {
                bundle.putInt(key, (Integer)value);
            } else if (value instanceof Double) {
                bundle.putDouble(key, (Double)value);
            } else if (value instanceof Long) {
                bundle.putLong(key, (Long)value);
            } else if (value instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray)value;
                ArrayList<Bundle> items = new ArrayList<>();
                for (int i = 0, n = jsonArray.length(); i < n; i++) {
                    items.add(parse(jsonArray.getJSONObject(i)));
                }
                bundle.putParcelableArrayList(key, items);
            } else {
                Log.w(TAG, "Value for key " + key + " is not supported");
            }
        }

        return bundle;
    }
}
