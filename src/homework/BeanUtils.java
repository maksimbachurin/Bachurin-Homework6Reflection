package homework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Максим on 21.12.2016.
 */

public class BeanUtils {

    /**
        * Scans object "from" for all getters. If object "to"
        * contains correspondent setter, it will invoke it
        * to set property value for "to" which equals to the property
        * of "from".
        * <p/>
        * The type in setter should be compatible to the value returned
        * by getter (if not, no invocation performed).
        * Compatible means that parameter type in setter should
        * be the same or be superclass of the return type of the getter.
        * <p/>
        * The method takes care only about public methods.
        
        * @param to   Object which properties will be set.
        * @param from Object which properties will be used to get values.
        */
    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        Map<String, Class<?>> classes = new HashMap<String, Class<?>>();
        Map<String, Object> values = new HashMap<String, Object>();

        Method[] getters = from.getClass().getMethods();
        for (Method method : getters) {
            String methodName = method.getName();
            if (methodName.startsWith("get") && !method.getReturnType().equals(Void.TYPE) && (method.getParameterCount() == 0)) {
                String arg = methodName.substring(3, methodName.length());
                classes.put(arg, method.getReturnType());
                values.put(arg, method.invoke(from));
            }
        }

        Method[] setters = to.getClass().getMethods();
        for (Method method : setters) {
            String methodName = method.getName();
            if (methodName.startsWith("set") && method.getReturnType().equals(Void.TYPE) && method.getParameterCount() == 1) {
                String arg = methodName.substring(3, methodName.length());
                if (classes.containsKey(arg)) {
                    Class<?> curClass = method.getParameterTypes()[0];
                    if (classes.get(arg).equals(curClass)) {
                        Object value = values.get(arg);
                        method.invoke(to, value);
                    }
                }
            }
        }
    }
}


