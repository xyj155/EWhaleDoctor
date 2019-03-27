package com.example.module_library.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StringUtil {
    public StringUtil() {
    }

    public static boolean isEmpty(String var0) {
        return var0 == null || var0.trim().length() == 0;
    }

    public static String readAll(File var0) throws IOException {
        if (var0 != null && var0.exists()) {
            BufferedReader var1 = null;
            boolean var7 = false;

            String var13;
            try {
                var7 = true;
                FileReader var12 = new FileReader(var0);
                var1 = new BufferedReader(var12);
                StringBuilder var2 = new StringBuilder();

                while(true) {
                    if ((var13 = var1.readLine()) == null) {
                        var13 = var2.toString();
                        var7 = false;
                        break;
                    }

                    var2.append(var13);
                }
            } catch (IOException var10) {
                throw var10;
            } finally {
                if (var7) {
                    if (var1 != null) {
                        try {
                            var1.close();
                        } catch (Exception var8) {
                            Log.i(TAG, "Exception while close bufferreader", var8);

                        }
                    }

                }
            }

            try {
                var1.close();
            } catch (Exception var9) {
                Log.i(TAG, "Exception while close bufferreader", var9);
            }

            return var13;
        } else {
            return null;
        }
    }

    private static final String TAG = "StringUtil";

    public static void writeTo(String var0, File var1) throws IOException {
        if (var1 == null) {
            throw new IOException("Target File Can not be null in StringUtil.writeTo");
        } else {
            File var2;
            if (!(var2 = var1.getParentFile()).exists()) {
                var2.mkdirs();
            }

            FileWriter var3;
            (var3 = new FileWriter(var1)).write(var0);
            var3.close();
        }
    }

    public static int parseInteger(String var0, int var1) {
        try {
            return Integer.parseInt(var0);
        } catch (Throwable var2) {
            return var1;
        }
    }

    public static String join(String var0, String[] var1) {
        StringBuffer var2 = new StringBuffer();
        if (var1 != null) {
            for(int var3 = 0; var3 < var1.length; ++var3) {
                if (var0 != null && var3 != 0) {
                    var2.append(var0);
                }

                var2.append(var1[var3]);
            }
        }

        return var2.toString();
    }
}
