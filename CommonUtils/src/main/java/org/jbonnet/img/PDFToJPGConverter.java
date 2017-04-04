package org.jbonnet.img;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PDFToJPGConverter {
	public static void main(String[] args) throws Exception {

		selectPdf();

	}

	// allow images selection for converting
	public static void selectPdf() throws Exception {

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf");
		chooser.setFileFilter(filter);
		chooser.setMultiSelectionEnabled(true);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File[] files = chooser.getSelectedFiles();
			for (File file : files) {
				convertPDFToJPG(file.toString());
			}

		}

	}

	public static void convertPDFToJPG(String src) throws Exception {

		PDDocument document = PDDocument.load(new File(src));
		PDFRenderer pdfRenderer = new PDFRenderer(document);
		for (int page = 0; page < document.getNumberOfPages(); ++page) {
			BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
		
			// suffix in filename will be used as the file format
			ImageIOUtil.writeImage(bim, src + "-" + (page + 1) + ".jpg", 300);
		}
		document.close();
	}

}
