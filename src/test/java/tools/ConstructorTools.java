package tools;

import m1graf2021.Graf;
import org.junit.Assert;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorTools {
    public static Object usePrivateConstructor(Class classContainingConstructor, Class<?>[] paramsTypes, Object... params) {
        Object result = null;

        for(Constructor constructor : classContainingConstructor.getDeclaredConstructors()) {
            if(constructor.getParameterCount() == params.length) {
                boolean constructorSignatureOk = true;
                for(int i=0; i<paramsTypes.length; i++) {
                    if(constructor.getParameterTypes()[i] != paramsTypes[i]) {
                        constructorSignatureOk = false;
                        break;
                    }
                }
                if(!constructorSignatureOk) continue;

                constructor.setAccessible(true);
                try{
                    result = constructor.newInstance(params);
                }
                catch (InstantiationException | IllegalAccessException | InvocationTargetException exception) {
                    return null;
                }
            }
        }

        return result;
    }
}
