package xyz.blue.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import xyz.blue.pojo.DeviceMsg;

public class DeviceJsonToDeviceMsg {
    /*
     * 解析设备发送的json转对象
     * */
    public static DeviceMsg ToDeviceMsg(String msg) {

        try {
            JSONObject obj = JSON.parseObject(msg);

            if (obj.size() == 3) {
                return new DeviceMsg(obj.getInteger("device_id"), obj.getInteger("to_user_id"), obj.getString("msg"));
            } else {
                return null;
            }

        } catch (JSONException e) {
            return null;
        }
    }
}
