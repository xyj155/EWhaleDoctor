package com.example.module_library;

import com.alibaba.android.arouter.launcher.ARouter;

public class ApplicationInitial {
    public ApplicationInitial initAliBabaRouter() {
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(MyApp.getInstance());
        return this;
    }
}
