package api.jdbc.mock.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import api.jdbc.mock.exception.ReflectionException;

public class ObjectFactoryUtil {

	@SuppressWarnings("unchecked")
	public static <E> E getInstance(Class<E> clazz) throws ReflectionException {
		E object = null;

		Constructor<?>[] constructors = clazz.getConstructors();
		for (Constructor<?> cons : constructors) {
			try {
				object = (E) cons.newInstance();
			} catch (IllegalArgumentException e) {
				throw new ReflectionException(e.getMessage(), e);
			} catch (InstantiationException e) {
				throw new ReflectionException(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				throw new ReflectionException(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				throw new ReflectionException(e.getMessage(), e);
			}
			break;
		}

		return object;
	}

	public static void invokeMethod(Object obj, String methodName,
			Map<Class<?>, Object> paramMap) throws ReflectionException {
		Class<?> clazz = obj.getClass();

		Class<?>[] paramsClazz = paramMap.keySet().toArray(new Class<?>[] {});
		try {
			Method method = clazz.getMethod(methodName, paramsClazz);
			method.setAccessible(true);

			Object[] params = new Object[paramMap.size()];
			int count = 0;
			for (Entry<Class<?>, Object> entry : paramMap.entrySet()) {
				params[count++] = entry.getValue();
			}

			method.invoke(obj, params);
		} catch (SecurityException e) {
			throw new ReflectionException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new ReflectionException(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			throw new ReflectionException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new ReflectionException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new ReflectionException(e.getMessage(), e);
		}
	}
}
