package org.sfec.util;

import org.sfec.exception.FieldEditException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * Simple encoder used for secure the value of any field of object. Use default {@link PasswordEncoder} object
 * used in application also. Has parametrized methods and work with reflection to get access for encoded fields
 */
@Component
public class FieldEncoder {

    private final PasswordEncoder encoder;

    public FieldEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public <T> T encodeField(T t, String fieldName) {
        try {
            Class clazz = t.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            String fieldValue = encoder.encode(field.toString());
            field.set(t, fieldValue);

            return t;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new FieldEditException("Class hasn't such private field - " + fieldName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new FieldEditException("Haven't access to field  - " + fieldName);
        }
    }
}
