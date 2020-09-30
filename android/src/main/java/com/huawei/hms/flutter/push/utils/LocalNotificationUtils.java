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

package com.huawei.hms.flutter.push.utils;

import android.os.Bundle;

import com.huawei.hms.flutter.push.config.NotificationAttributes;
import com.huawei.hms.flutter.push.constants.Code;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class LocalNotificationUtils {
    public static Bundle callArgsToBundle(MethodCall call, MethodChannel.Result result) {
        if (call.arguments == null) {
            result.error(Code.NULL_BUNDLE.code(), "Arguments are empty", "");
            return null;
        }
        try {
            return new NotificationAttributes(call).toBundle();
        } catch (Exception e) {
            return null;
        }
    }
}
