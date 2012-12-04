package com.the6hours.hsoytemplates;

import com.google.template.soy.data.SoyListData;
import com.google.template.soy.data.SoyMapData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @author Igor Artamonov (http://igorartamonov.com)
 * @since 03.12.12
 */
public class DataConverter {

    private CustomDataConverter customDataConverter;
    private DateFormat dateFormat;

    private static final String[] SUPPORTED_CLASSES = {
            String.class.getCanonicalName(),
            Integer.class.getCanonicalName(), Double.class.getCanonicalName(),
            Boolean.class.getCanonicalName()
    };

    static {
        Arrays.sort(SUPPORTED_CLASSES);
    }

    public DataConverter() {
    }

    public DataConverter(CustomDataConverter customDataConverter) {
        this.customDataConverter = customDataConverter;
    }

    public CustomDataConverter getCustomDataConverter() {
        return customDataConverter;
    }

    public void setCustomDataConverter(CustomDataConverter customDataConverter) {
        this.customDataConverter = customDataConverter;
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public SoyListData toSoy(Collection coll) {
        if (coll == null) {
            return null;
        }
        SoyListData res = new SoyListData();
        for (Object it: coll) {
            res.add(soyValue(it));
        }
        return res;
    }

    public SoyMapData toSoy(Map<Object, Object> data) {
        if (data == null) {
            return null;
        }
        SoyMapData res = new SoyMapData();
        for (Map.Entry kv: data.entrySet()) {
            res.put(kv.getKey(), soyValue(kv.getValue(), kv.getKey().toString()));
        }
        return res;
    }

    public Object soyValue(Object value) {
        return soyValue(value, "?");
    }

    public Object soyValue(Object value, String key) {
        if (value == null) {
            return "";
        } else if (value instanceof Collection) {
            return toSoy((Collection)value);
        } else if (value instanceof Map) {
            return toSoy((Map) value);
        } else if (value instanceof Long) { //or you'll get Soy data exception. Name: exam.question.mini. Message: Attempting to convert unrecognized object to Soy data (object type java.lang.Long)
            return value.toString();
        } else if (value instanceof Date) {
            Date date = (Date) value;
            if (dateFormat != null) {
                dateFormat.format(date);
            }
            return date.toString();
        } else if (value.getClass().isEnum()) {
            return value.toString();
        } else if (Arrays.binarySearch(SUPPORTED_CLASSES, value.getClass().getCanonicalName()) >= 0) {
            return value;
        } if (customDataConverter != null) {
            Object val = customDataConverter.tryConvert(value, this);
            if (val != null) {
                return val;
            }
        }
        //log.info("Invalid value class: ${value.class}, for key $key")
        return "";
    }
}
