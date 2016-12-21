package homework;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Максим on 21.12.2016.
 */
public class TestApplication {
    public static void main(String[] args) {
        TestClass a = new TestClass(22, "Bob", 50.0);
        TestClass b = new TestClass();
        System.out.println(b.getAge());
        System.out.println(b.getName());
        System.out.println(b.getPrice());
        try {
            BeanUtils.assign(b, a);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(b.getAge());
        System.out.println(b.getName());
        System.out.println(b.getPrice());
    }
}
