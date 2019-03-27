package com.example.module_library.config;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_library.MyApp;
import com.example.module_library.weight.toast.ToastUtils;

public class ApplicationInitial {
    public ApplicationInitial initAliBabaRouter() {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(MyApp.getInstance());
        return this;
    }

    public ApplicationInitial initToast() {
        ToastUtils.init(MyApp.getInstance());
        return this;
    }
}
