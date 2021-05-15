package com.example.aopdemo.services.utils;

import com.example.aopdemo.models.Vehicle;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class BeanUtils {
    public static void copy(Object dest, Object source) throws Exception {

        BeanInfo beanInfo = Introspector.getBeanInfo(source.getClass());
        PropertyDescriptor[] pdList = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pdList) {
            Method writeMethod = null;
            Method readMethod = null;
            try {
                writeMethod = pd.getWriteMethod();
                readMethod = pd.getReadMethod();
            } catch (Exception e) {
            }

            if (readMethod == null || writeMethod == null) {
                continue;
            }

            Object val = readMethod.invoke(source);
            if (val != null) {
                writeMethod.invoke(dest, val);
            }
        }
    }

    public static void main(String[] args) {

        Vehicle d = new Vehicle(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
        Vehicle d1 = new Vehicle("656757",null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
        try {
            copy(d, d1);
            System.out.print(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
