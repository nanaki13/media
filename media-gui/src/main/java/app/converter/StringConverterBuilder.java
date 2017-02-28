package app.converter;

import java.util.function.Function;

import javafx.util.StringConverter;

public class StringConverterBuilder {
	public static <T> StringConverter<T> build(Function<T,String> toString){
		return new StringConverter<T>() {

			@Override
			public String toString(T object) {
				return toString.apply(object);
			}

			@Override
			public T fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
}
