package xyz.blue.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import xyz.blue.pojo.Device;

import java.util.List;

public class JsonToString {

//    /*
//     * 解析设备发送的json转对象
//     * */
//    public static DeviceMsg ToDeviceMsg(String msg) {
//
//        try {
//            JSONObject obj = JSON.parseObject(msg);
//
//            if (obj.size() == 3) {
//                try {
//                    return new DeviceMsg(obj.getInteger("device_id"), obj.getInteger("to_user_id"), obj.getString("msg"));
//                } catch (Exception n) {
//                    return null;
//                }
//            } else {
//                //解析错误：key键不对应，数据类型错误
//                return null;
//            }
//
//        } catch (JSONException e) {
//            return null;
//        }
//    }

    public static String DeviceMsg(String msg) {
        JSONObject obj = JSON.parseObject(msg);
        return null;
    }

    public static String DeviceListToJson(List<Device> deviceList) throws JSONException {


        String jsonStr = JSONObject.toJSONString( deviceList );
        return jsonStr;

//        if (deviceList == null) return "";
//        JSONArray array = new JSONArray();
//        JSONObject jsonObject = new JSONObject();
//
//        deviceList.forEach(device -> {
//            jsonObject.clear();
//            jsonObject.put("Device_id",device.getDevice_id());
//            jsonObject
//        });
//
//        GoodInfo info = null;
//        for (int i = 0; i < items.size(); i++) {
//            info = items.get(i);
//            jsonObject = ;
//            jsonObject.put(Api.COLORID, info.getColorId());
//            jsonObject.put(Api.STOCK, info.getStock());
//            array.put(jsonObject);
//        }
//        return array.toString();
    }

}
