package com.ooftf.homer.lib;

import android.net.Uri;

import com.ooftf.homer.lib.aidl.IpcRequestBody;
import com.ooftf.homer.lib.aidl.IpcResponseBody;

import io.reactivex.Single;

public class IpcClient {
    public static Single<IpcResponseBody> request(Uri uri, IpcRequestBody body) {
        IClient client = IpcHostManager.getClientMap().get(uri.getHost());
        if (client == null) {
            return Single.error(new IpcException("未找到 " + uri.getHost() + " 对应的AbsIpcClient", 502));
        }
        return client.call(uri, body);
    }

    public static Uri.Builder getBaseUri(String host){
        return  new Uri.Builder().scheme(IpcConst.IPC_SCHEME).authority(host);
    }
}
