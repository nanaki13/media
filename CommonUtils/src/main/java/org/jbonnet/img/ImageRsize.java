package org.jbonnet.img;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/*
 * @author mkyong
 *
 */
public class ImageRsize extends Application{

	private static final int IMG_WIDTH = 1024;
	private static final int IMG_HEIGHT = 100;

	private static FileChooser fileChooser;
	public static void main(String[] args) {
		
		Application.launch(args);

//		try (Stream<Path> walk = Files.walk(FileSystems.getDefault().getPath("/home/jonathan/Bureau/julia/scan2"));) {
//			walk.forEach((p) -> resize(p));
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}

	private static void resize(Path p) {
		if (p.getFileName().toString().endsWith(".jpg")) {
			System.out.println(p.getFileName()+" ...");
			BufferedImage originalImage;
			try {
				originalImage = ImageIO.read(p.toFile());
				if(originalImage != null){
					File file = new File("/home/jonathan/Documents/site_julia/image_resized/" + p.getFileName());
					int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
					if(!file.exists()){
						BufferedImage resizeImageJpg = resizeImageWithHint(originalImage, type);
						ImageIO.write(resizeImageJpg, "jpg",
								file);
					}else{
						System.out.println("Already exists, skeep");
					}
				}else{
					System.out.println("p.toFile() -> read -> null");
				}
				
				
				

				// BufferedImage resizeImagePng = resizeImage(originalImage,
				// type);
				// ImageIO.write(resizeImagePng, "png", new
				// File("c:\\image\\mkyong_png.jpg"));

//				BufferedImage resizeImageHintJpg = resizeImageWithHint(originalImage, type);
//				ImageIO.write(resizeImageHintJpg, "jpg",
//						new File("/home/jonathan/Documents/site_julia/image_resized/" + p.getFileName()));

				// BufferedImage resizeImageHintPng =
				// resizeImageWithHint(originalImage, type);
				// ImageIO.write(resizeImageHintPng, "png", new
				// File("c:\\image\\mkyong_hint_png.jpg"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("... done");
		}

	}

	public static BufferedImage resizeImage(BufferedImage originalImage, int type) {
		double ratio = (double) originalImage.getHeight() / (double) originalImage.getWidth();
		int newWidth = IMG_WIDTH;
		int newH = (int) (newWidth * ratio);
		BufferedImage resizedImage = new BufferedImage(newWidth, newH, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, newWidth, newH, null);
		g.dispose();

		return resizedImage;
	}

	public static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type) {
		double ratio = (double) originalImage.getHeight() / (double) originalImage.getWidth();
		int newWidth = IMG_WIDTH;
		int newH = (int) (newWidth * ratio);
		BufferedImage resizedImage = new BufferedImage(newWidth, newH, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, newWidth, newH, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		fileChooser = new FileChooser();
		
		List<File> showOpenMultipleDialog = fileChooser.showOpenMultipleDialog(primaryStage);
//		try (Stream<Path> walk = Files.walk(FileSystems.getDefault().getPath("/home/jonathan/Bureau/julia/scan2"));) {
		showOpenMultipleDialog.forEach((p) -> resize(p.toPath()));
//
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
		
	}
}