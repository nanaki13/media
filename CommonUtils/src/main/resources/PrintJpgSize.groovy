

import java.util.function.Function

import javax.imageio.ImageIO
import javax.imageio.ImageReader
import javax.imageio.stream.ImageInputStream



class PrintJpgSize implements Function<File, File> {

	@Override
	public File apply(File f) {
		try{
		//	Path p = f.toPath();
			if (f.toPath().getFileName().toString().endsWith(".jpg")) {
				ImageInputStream inp = ImageIO.createImageInputStream(f);
					 Iterator<ImageReader> readers = ImageIO.getImageReaders(inp);
					if (readers.hasNext()) {
						ImageReader reader = readers.next();
						try {
							reader.setInput(inp);
							File fDest = new File(
								f.getAbsolutePath().replaceFirst("\\.jpg\$",reader.getWidth(0)+"x"+reader.getHeight(0)+"\\.jpg"));
							println "cocuouc";
							println fDest.toPath();
							java.nio.file.Path pdtest = fDest.toPath();
							java.nio.file.Path pSource = f.toPath();
							
							java.nio.file.Files.copy(
								pSource
								,  pdtest
								);
						} finally {
							reader.dispose();
						}
					}
				
			}
		}catch(e){
			e.printStackTrace();	
		}
		
	}
}
