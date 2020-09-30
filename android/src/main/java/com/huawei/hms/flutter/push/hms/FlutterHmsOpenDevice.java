/*
Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

package com.huawei.hms.flutter.push.hms;

import android.util.Log;

import com.huawei.hmf.tasks.Task;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.flutter.push.PushPlugin;
import com.huawei.hms.flutter.push.constants.Code;
import com.huawei.hms.flutter.push.logger.HMSLogger;
import com.huawei.hms.opendevice.OpenDevice;
import com.huawei.hms.support.api.opendevice.OdidResult;

import io.flutter.plugin.common.MethodChannel;

public class FlutterHmsOpenDevice {
    public static void getOdid(final MethodChannel.Result result) {
        HMSLogger.getInstance(PushPlugin.getContext()).startMethodExecutionTimer("getOdid");
        Task<OdidResult> odidResultTask = OpenDevice.getOpenDeviceClient(PushPlugin.getContext()).getOdid();
        odidResultTask.addOnSuccessListener(ooidRes -> {
            String ooid = ooidRes.getId();
            HMSLogger.getInstance(PushPlugin.getContext()).sendSingleEvent("getOdid");
            Log.d("FlutterHmsInstanceId", "Odid");
            result.success(ooid);
        }).addOnFailureListener(e -> {
            if (e instanceof ApiException) {
                ApiException apiException = (ApiException) e;
                HMSLogger.getInstance(PushPlugin.getContext())
                        .sendSingleEvent("getOdid", String.valueOf(apiException.getStatusCode()));
            } else {
                HMSLogger.getInstance(PushPlugin.getContext())
                        .sendSingleEvent("getOdid", Code.RESULT_UNKNOWN.code());
            }
            Log.e("FlutterHmsOpenDevice", "getOdid failed. Error message: " + e.getMessage());
        });
    }
}
