/*
    Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License")
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        https://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.huawei.hms.flutter.push.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapUtils {

    private MapUtils() {
        throw new IllegalStateException("Utility class");
    }


    public static Map<String, Object> toMap(JSONObject jsonObject) {

        Map<String, Object> map = new HashMap<>();

        if (jsonObject == null) return map;

        Iterator<String> iterator = jsonObject.keys();
        try {
            while (iterator.hasNext()) {
                String key = iterator.next();
                Object value = jsonObject.get(key);

                if (value instanceof JSONObject) {
                    value = MapUtils.toMap((JSONObject) value);
                }
                if (value instanceof JSONArray) {
                    value = ArrayUtils.toArray((JSONArray) value);
                }

                map.put(key, value);
            }

            return map;
        } catch (JSONException e) {
            return map;
        }

    }

}
