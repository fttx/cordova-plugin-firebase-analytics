/**
 * Logs an app event.
 *
 * @param {string} name Enent name
 * @param {Record<string, number | string | Array<object>>} params Event parameters
 * @returns {Promise<void>} Callback when operation is completed
 *
 * @example
 * cordova.plugins.firebase.analytics.logEvent("my_event", {param1: "value1"});
 */
export function logEvent(name: string, params: Record<string, number | string | Array<object>>): Promise<void>;
/**
 * Sets the user ID property. This feature must be used in accordance with Google's Privacy Policy.
 *
 * @param {string} userId User's indentifier string
 * @returns {Promise<void>} Callback when operation is completed
 *
 * @see https://www.google.com/policies/privacy
 *
 * @example
 * cordova.plugins.firebase.analytics.setUserId("12345");
 */
export function setUserId(userId: string): Promise<void>;
/**
 * Sets a user property to a given value. Be aware of automatically collected user properties.
 *
 * @param {string} name Property name
 * @param {string} value Property value
 * @returns {Promise<void>} Callback when operation is completed
 *
 * @see https://support.google.com/firebase/answer/6317486?hl=en&ref_topic=6317484
 *
 * @example
 * cordova.plugins.firebase.analytics.setUserProperty("name1", "value1");
 */
export function setUserProperty(name: string, value: string): Promise<void>;
/**
 * Clears all analytics data for this instance from the device and resets the app instance ID.
 *
 * @returns {Promise<void>} Callback when operation is completed
 *
 * @example
 * cordova.plugins.firebase.analytics.resetAnalyticsData();
 */
export function resetAnalyticsData(): Promise<void>;
/**
 * Sets whether analytics collection is enabled for this app on this device.
 *
 * @param {boolean} enabled Flag that specifies new state
 * @returns {Promise<void>} Callback when operation is completed
 *
 * @example
 * cordova.plugins.firebase.analytics.setEnabled(false);
 */
export function setEnabled(enabled: boolean): Promise<void>;
/**
 * Sets the consent settings for Firebase Analytics based on provided boolean parameters.
 *
 * @param {Object} consentSettings An object containing boolean values for various consent settings:
 *  - "GOOGLE_ANALYTICS_DEFAULT_ALLOW_ANALYTICS_STORAGE": {boolean} - Grants or denies consent for analytics storage.
 *  - "GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_STORAGE": {boolean} - Grants or denies consent for ad storage.
 *  - "GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_USER_DATA": {boolean} - Grants or denies consent for ad user data.
 *  - "GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_PERSONALIZATION_SIGNALS": {boolean} - Grants or denies consent for ad personalization signals.
 * @returns {Promise<void>} Callback when the operation is completed
 *
 * @example
 * cordova.plugins.firebase.analytics.setAnalyticsConsent({
 *     "GOOGLE_ANALYTICS_DEFAULT_ALLOW_ANALYTICS_STORAGE": true,
 *     "GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_STORAGE": true,
 *     "GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_USER_DATA": true,
 *     "GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_PERSONALIZATION_SIGNALS": true
 * });
 */
export function setAnalyticsConsent(consentSettings: {
    ANALYTICS_COLLECTION_ENABLED: boolean,
    AUTOMATIC_SCREEN_REPORTING_ENABLED: boolean,
    GOOGLE_ANALYTICS_DEFAULT_ALLOW_ANALYTICS_STORAGE: boolean,
    GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_STORAGE: boolean,
    GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_USER_DATA: boolean,
    GOOGLE_ANALYTICS_DEFAULT_ALLOW_AD_PERSONALIZATION_SIGNALS: boolean
}): Promise<void>;

/**
 * Sets the current screen name, which specifies the current visual context in your app. This helps identify the areas in your app where users spend their time and how they interact with your app.
 *
 * @param {string} screenName Current screen name
 * @returns {Promise<void>} Callback when operation is completed
 *
 * @example
 * cordova.plugins.firebase.analytics.setCurrentScreen("User dashboard");
 */
export function setCurrentScreen(screenName: string): Promise<void>;
/**
 * Adds parameters that will be set on every event logged from the SDK, including automatic ones.
 * @param {Record<string, number | string | Array<object>>} defaults Key-value default parameters map
 * @returns {Promise<void>} Callback when operation is completed
 *
 * @example
 * cordova.plugins.firebase.analytics.setDefaultEventParameters({foo: "bar"});
 */
export function setDefaultEventParameters(defaults: Record<string, number | string | Array<object>>): Promise<void>;
