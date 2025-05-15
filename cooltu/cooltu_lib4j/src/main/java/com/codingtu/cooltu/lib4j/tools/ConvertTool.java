package com.codingtu.cooltu.lib4j.tools;

import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.processor.annotation.bean.ConvertItem;
import com.codingtu.cooltu.processor.annotation.bean.ConvertTo;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

public class ConvertTool {
    /**************************************************
     *
     * 字母大小写的操作
     *
     **************************************************/
    public static char toUpper(char c) {
        return (char) (c - 32);
    }

    public static char toLower(char c) {
        return (char) (c + 32);
    }

    /**************************************************
     *
     * 转换成小写加下划线类型
     * 比如 myName 转换后为 my_name
     *
     **************************************************/
    public static String toLayoutType(String str) {
        return toStaticType(str).toLowerCase();
    }

    /**************************************************
     *
     * 转换成静态变量的命名方式
     * 比如 myName 转换后为 MY_NAME
     *
     **************************************************/
    public static String toStaticType(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (CharTool.isLower(c)) {
                sb.append(toUpper(c));
            } else if (CharTool.isUpper(c) && i != 0) {
                sb.append("_" + c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 转换成类的命名方式
     *
     **************************************************/
    public static String toClassType(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && CharTool.isLower(c)) {
                c = toUpper(c);
            } else if (CharTool.isLowerLine(c)) {
                if (i == str.length() - 1) {
                    break;
                } else {
                    i++;
                    c = str.charAt(i);
                    if (CharTool.isLower(c)) {
                        c = toUpper(c);
                    }
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 转换成函数的命名方式
     *
     **************************************************/
    public static String toMethodType(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == 0 && CharTool.isUpper(c)) {
                c = toLower(c);
            } else if (CharTool.isLowerLine(c)) {
                if (i == str.length() - 1) {
                    break;
                } else {
                    i++;
                    c = str.charAt(i);
                    if (CharTool.isLower(c)) {
                        c = toUpper(c);
                    }
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 包名转路径，以\开始
     *
     **************************************************/
    public static String pkgToPath(String pkg) {
        if (pkg == null) return null;
        int length = pkg.length();
        if (length <= 0) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(File.separator);
        for (int i = 0; i < length; i++) {
            char c = pkg.charAt(i);
            if (c == '.') {
                sb.append(File.separator);
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**************************************************
     *
     * 路径转包名
     *
     **************************************************/
    public static String pathToPkg(String path) {
        if (path == null) return null;
        int len = path.length();
        if (len <= 0) return null;
        char separatorChar = File.separatorChar;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = path.charAt(i);
            if (c == separatorChar || c == '/') {
                c = '.';
            }
            if (i == 0 || i == len - 1) {
                if (c == '.') {
                    continue;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**************************************************
     *
     * bean的转换
     *
     **************************************************/
    public static <T> T convert(Object srcObj) {
        try {

            HashMap<String, Field> fieldHashMap = new HashMap<>();
            HashMap<String, Method> methodHashMap = new HashMap<>();

            Class srcClass = srcObj.getClass();
            Es.es(srcClass.getFields()).ls(new Es.EachEs<Field>() {
                @Override
                public boolean each(int position, Field field) {
                    ConvertItem convertItem = field.getAnnotation(ConvertItem.class);
                    String name = field.getName();
                    if (convertItem != null) {
                        name = convertItem.value();
                    }
                    fieldHashMap.put(name, field);
                    return false;
                }
            });

            Es.es(srcClass.getMethods()).ls(new Es.EachEs<Method>() {
                @Override
                public boolean each(int position, Method method) {
                    ConvertItem convertItem = method.getAnnotation(ConvertItem.class);
                    String name = method.getName();
                    if (convertItem != null) {
                        name = convertItem.value();
                    }
                    methodHashMap.put(name, method);
                    return false;
                }
            });

            Annotation annotation = srcClass.getAnnotation(ConvertTo.class);
            if (annotation instanceof ConvertTo) {
                ConvertTo convertTo = (ConvertTo) annotation;
                Class targetClass = convertTo.value();
                Object targetObj = targetClass.newInstance();
                Field[] fields = targetClass.getFields();
                Es.es(fields).ls(new Es.EachEs<Field>() {
                    @Override
                    public boolean each(int position, Field field) {
                        String name = field.getName();
                        Method method = methodHashMap.get(name);
                        if (method != null) {
                            try {
                                field.set(targetObj, method.invoke(srcObj));
                            } catch (Exception e) {

                            }
                        } else {
                            Field srcField = fieldHashMap.get(name);
                            if (srcField != null) {
                                try {
                                    field.set(targetObj, srcField.get(srcObj));
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        return false;
                    }
                });
                return (T) targetObj;
            }
        } catch (Exception e) {

        }
        return null;
    }

    public static byte[] toBytes(int[] ints) {
        int count = CountTool.count(ints);
        byte[] bytes = new byte[count];
        for (int i = 0; i < count; i++) {
            bytes[i] = (byte) ints[i];
        }
        return bytes;
    }

    public static byte[] getBytes(byte[] datas, int skip, int bits) {
        byte[] bytes = new byte[bits];
        for (int i = 0; i < bits; i++) {
            bytes[i] = datas[i + skip];
        }
        return bytes;
    }

    private byte[] crc16(byte[] bytes) {
        int crc = 0xffff;
        int poly = 0xA001;
        for (byte b : bytes) {
            crc ^= b & 0xff;
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x0001) != 0) {
                    crc >>= 1;
                    crc ^= poly;
                } else {
                    crc >>= 1;
                }
            }
        }
        byte[] bytes1 = new byte[2];
        bytes1[0] = (byte) crc;
        bytes1[1] = (byte) (crc >> 8);
        return bytes1;
    }

}
