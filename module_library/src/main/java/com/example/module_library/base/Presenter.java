package com.example.module_library.base;

public interface Presenter<T extends BaseView> {
    void attachView(T view);

    void detachView();
}
