package com.release.mvp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Mr.release
 * @create 2019/3/26
 * @Describe
 */
public class SPUtil {

    private static final String FILE_NAME = "share_date";
    private static SPUtil mInstance;
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;
    public static int SCREEN_WIDTH = 0;

    public synchronized static SPUtil getInstance(Context context) {

        if (mInstance == null) {
            mInstance = new SPUtil(context.getApplicationContext());
        }

        return mInstance;
    }

    private SPUtil(Context context) {

        mSharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static void setParam(String key, Object object) {

        String type = object == null ? "" : object.getClass().getSimpleName();

        if ("String".equals(type)) {
            mEditor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            mEditor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            mEditor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            mEditor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            mEditor.putLong(key, (Long) object);
        }

        mEditor.apply();
    }


    public static Object getParam(String key, Object defaultObject) {

        String type = defaultObject == null ? "" : defaultObject.getClass().getSimpleName();
        if ("String".equals(type)) {
            return mSharedPreferences.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return mSharedPreferences.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return mSharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return mSharedPreferences.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return mSharedPreferences.getLong(key, (Long) defaultObject);
        }

        return null;
    }


//    public static <T> void setDataList(String key, List<T> datalist) {
//        if (null == datalist || datalist.size() <= 0)
//            return;
//
//        String strJson = JSON.toJSONString(datalist);
//        mEditor.putString(key, strJson);
//        mEditor.apply();
//
//    }


//    public static <T> List<T> getDataList(String key, Class<T> cls) {
//        List<T> datalist = new ArrayList<T>();
//        String strJson = mSharedPreferences.getString(key, null);
//        if (null == strJson) {
//            return datalist;
//        }
//
//        Gson gson = new Gson();
//        JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
//        for(final JsonElement elem : array){
//            datalist.add(gson.fromJson(elem, cls));
//        }
//        return datalist;
//
//    }

    public static void clearUserInfo() {
        assert (mEditor != null);
//        mEditor.putInt(Constants.USER_ID, 0);
        mEditor.apply();
    }
}
