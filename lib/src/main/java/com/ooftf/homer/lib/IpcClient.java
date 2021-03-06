package com.ooftf.homer.lib;

import android.net.Uri;

import com.ooftf.homer.lib.aidl.IpcRequestBody;
import com.ooftf.homer.lib.aidl.IpcResponseBody;

import io.reactivex.Single;

public class IpcClient {
    public static Single<IpcResponseBody> request(IpcRequestBody body) {
        IClient client = IpcHostManager.getClientMap().get(body.getUri().getHost());
        if (!body.getUri().getScheme().equals(IpcConst.IPC_SCHEME)) {
            return Single.error(new IpcException("Uri scheme" + body.getUri().getScheme() + " is not " + IpcConst.IPC_SCHEME, 502));
        }
        if (client == null) {
            return Single.error(new IpcException("未找到 " + body.getUri().getHost() + " 对应的AbsIpcClient", 502));
        }
        return client.call(body);
    }

    public static Single<IpcResponseBody> request(Uri uri) {
        return request(new IpcRequestBody(uri));
    }

    public static Uri.Builder getBaseUri(String host) {
        return new Uri.Builder().scheme(IpcConst.IPC_SCHEME).authority(host);
    }
}
