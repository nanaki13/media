import java.io.File
import java.util.function.Function

class PrintPath implements Function<File, File> {

	@Override
	public File apply(File t) {
		println t;
		return null;
	}

}
