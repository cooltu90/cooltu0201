package com.codingtu.cooltu.processor.lib.param;

import com.codingtu.cooltu.lib4j.data.kv.KV;
import com.codingtu.cooltu.lib4j.es.BaseEs;
import com.codingtu.cooltu.lib4j.es.Es;
import com.codingtu.cooltu.lib4j.tools.CountTool;
import com.codingtu.cooltu.lib4j.tools.StringTool;

import java.util.ArrayList;
import java.util.List;

public class Params {
    private List<KV<String, String>> kvs;


    public static Params obtain(List<KV<String, String>> kvs) {
        Params params = new Params();
        params.kvs = kvs;
        return params;
    }

    public List<KV<String, String>> getKvs() {
        return kvs;
    }

    public void add(String type, String name) {
        if (kvs == null) {
            kvs = new ArrayList<>();
        }
        KV<String, String> kv = new KV<>();
        kv.k = type;
        kv.v = name;
        kvs.add(kv);
    }

    public void add(KV<String, String> kv) {
        if (kvs == null) {
            kvs = new ArrayList<>();
        }
        kvs.add(kv);
    }

    public String getMethodParams() {
        StringBuilder sb = new StringBuilder();
        Es.es(kvs).ls(new Es.EachEs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (position != 0) {
                    sb.append(", ");
                }
                sb.append(kv.k).append(" ").append(kv.v);
                return false;
            }
        });
        return sb.toString();
    }

    public String getParams() {
        StringBuilder sb = new StringBuilder();
        Es.es(kvs).ls(new Es.EachEs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (position != 0) {
                    sb.append(", ");
                }
                sb.append(kv.v);
                return false;
            }
        });
        return sb.toString();
    }

    public String getParams(boolean hasFirst, boolean hasNext) {
        StringBuilder sb = new StringBuilder();
        int count = CountTool.count(kvs);
        if (count > 0 && hasFirst) {
            sb.append(",");
        }
        Es.es(kvs).ls(new Es.EachEs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (position != 0) {
                    sb.append(",");
                }
                sb.append(" ").append(kv.v);
                return false;
            }
        });
        if (count > 0 && hasNext) {
            sb.append(", ");
        }

        return sb.toString();
    }

    public String getMethodParams(boolean hasFirst, boolean hasNext) {
        StringBuilder sb = new StringBuilder();
        int count = CountTool.count(kvs);
        if (count > 0 && hasFirst) {
            sb.append(", ");
        }
        Es.es(kvs).ls(new Es.EachEs<KV<String, String>>() {
            @Override
            public boolean each(int position, KV<String, String> kv) {
                if (position != 0) {
                    sb.append(", ");
                }
                sb.append(kv.k).append(" ").append(kv.v);
                return false;
            }
        });

        if (count > 0 && hasNext) {
            sb.append(", ");
        }

        return sb.toString();
    }

    public int count() {
        return CountTool.count(kvs);
    }

    public String getParam(Convert convert) {
        StringBuilder sb = new StringBuilder();

        int index = 0;
        int count = CountTool.count(kvs);
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                KV<String, String> kv = kvs.get(i);
                String convertStr = convert.convert(i, kv);
                if (StringTool.isNotBlank(convertStr)) {
                    if (index != 0) {
                        sb.append(", ");
                    }
                    sb.append(convertStr);
                    index++;
                }
            }
        }
        return sb.toString();
    }

    public static <E> String getParam(E[] es, Es.Convert<E, String> convert) {
        return getParam(Es.es(es), convert);
    }

    public static <E> String getParam(List<E> es, Es.Convert<E, String> convert) {
        return getParam(Es.es(es), convert);
    }

    public static <E> String getParam(BaseEs<E> es, Es.Convert<E, String> convert) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int count = es.count();
        for (int i = 0; i < count; i++) {
            String convertStr = convert.convert(i, es.getByIndex(i));
            if (StringTool.isNotBlank(convertStr)) {
                if (index != 0) {
                    sb.append(", ");
                }
                sb.append(convertStr);
                index++;
            }
        }
        return sb.toString();
    }

    public void ls(Es.EachEs<KV<String, String>> eachEs) {
        Es.es(kvs).ls(eachEs);
    }

    public static interface Convert {
        String convert(int index, KV<String, String> kv);
    }

}
