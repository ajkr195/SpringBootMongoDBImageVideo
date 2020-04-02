package com.springboot.mongodb.imagevideo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String... args) {
		disableAccessWarnings();
		SpringApplication.run(Application.class, args);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void disableAccessWarnings() {
		try {
			Class unsafeClass = Class.forName("sun.misc.Unsafe");
			Field field = unsafeClass.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			Object unsafe = field.get(null);

			Method putObjectVolatile = unsafeClass.getDeclaredMethod("putObjectVolatile", Object.class, long.class,
					Object.class);
			Method staticFieldOffset = unsafeClass.getDeclaredMethod("staticFieldOffset", Field.class);

			Class loggerClass = Class.forName("jdk.internal.module.IllegalAccessLogger");
			Field loggerField = loggerClass.getDeclaredField("logger");
			Long offset = (Long) staticFieldOffset.invoke(unsafe, loggerField);
			putObjectVolatile.invoke(unsafe, loggerClass, offset, null);
		} catch (Exception ignored) {
		}
	}

}
