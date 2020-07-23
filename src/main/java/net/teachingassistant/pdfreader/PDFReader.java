/*
 * PDF text extractor
 * Extracts and save text in a text document with the same filename
 */
package net.teachingassistant.pdfreader;

/**
 *
 * @author muazzam
 */

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import utils.FileUtils;

public class PDFReader {

    public String getText(File pdfFile, int startPage, int endPage) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        PDFTextStripper stripper = new PDFTextStripper();
        if(startPage > 0 && endPage > 0){
            stripper.setStartPage(startPage);
            stripper.setEndPage(endPage);
        }        
        String text = stripper.getText(doc);
        doc.close();
        return text;
    }

    public String getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(doc);
        doc.close();
        return text;
    }

    public static void main(String args[]) {

        // Minimal example
        
        String PATH = "";
        // Name of the pdf file
        String inDoc = "";
        //String outDoc = "Corpora/chapters/txt/"+inDoc.substring(inDoc.lastIndexOf("/")+1, inDoc.length()-4) + ".txt";
        // Output file
        String outDoc = "output/"+inDoc+".txt";        
        
        FileUtils FU = new FileUtils();
        try {
            PDFReader pdf = new PDFReader();
            String text = pdf.getText(new File(PATH+inDoc), 1, 34);
            FU.writeFile(new File(PATH+outDoc), text);            
        } catch (IOException e) {
            System.out.println("Unable to read PDF file " + inDoc);
        }
    }
}
