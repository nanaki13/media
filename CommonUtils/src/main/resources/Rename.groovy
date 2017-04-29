import java.io.File
import java.nio.file.Files
import java.util.function.Function

class Rename implements Function<File, File> {

	
	@Override
	public File apply(File t) {
		File f = new File(t.absolutePath.replaceAll("\\.\$","_1200\\."));
		println f;
	
		Files.copy(t.toPath(), f.toPath());
		return null;
	}

}
