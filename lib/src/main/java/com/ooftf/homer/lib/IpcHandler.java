package com.ooftf.homer.lib;


import android.net.Uri;
import android.os.RemoteException;

import com.ooftf.homer.lib.aidl.IpcRequestBody;

public interface IpcHandler {
    void handler(IpcRequestBody body, IpcCallback callback) throws RemoteException;
}
