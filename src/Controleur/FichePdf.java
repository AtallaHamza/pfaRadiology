/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Dao.CentreDao;
import Dao.ExamenDao;
import Dao.RenduDao;
import Model.Centre;
import Model.Examen;
import Model.Patient;
import Model.Rendu;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * @author asus
 */
public class FichePdf {

    private static String FILE = "c:/temp/FirstPdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public void createSamplePDF(Patient p) throws Exception {
        Document documento = new Document();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm");
        LocalDateTime now = LocalDateTime.now();

        File file = new File("C:/fiche/" + p.getNom() + "_" + p.getPrenom() + " " + now.format(formatter) + ".pdf");
        file.createNewFile();
        FileOutputStream fop = new FileOutputStream(file);
        PdfWriter.getInstance(documento, fop);
        documento.open();
        addMetaData(documento);
        addTitlePage(documento, p);
        //addContentPatient(documento, p);
        documento.close();
    }

    public static void main(String[] args) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document);
            //addTitlePage(document);
            // addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addMetaData(Document document) {
        document.addTitle("Centre de radiologie");
        document.addSubject("Fiche Patient");
        document.addKeywords("centre, radiologie, fiche");
        document.addAuthor("Test");
        document.addCreator("asus");
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void addTitlePage(Document document, Patient p)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        java.util.List<Centre> lst = CentreDao.findAllCentre();
        for (Centre emp : lst) {
            preface.add(new Paragraph("Centre de radiologie : "+emp.getNom() , catFont));
        addEmptyLine(preface, 1);
            preface.add(new Paragraph("Adresse : " + emp.getAdress() , catFont));
        addEmptyLine(preface, 1);
            preface.add(new Paragraph("Telephone : " + emp.getTel() , catFont));
        }

        addEmptyLine(preface, 1);
        // Will create: Report generated by: _name, _date
        preface.add(new Paragraph(
                "Rapport généré par : " + System.getProperty("user.name") + ", " + new Date(),
                smallBold));
        addEmptyLine(preface, 2);
        preface.add(new Paragraph(
                "Fiche Patient : " + p.getNom().toUpperCase() + " " + p.getPrenom().toUpperCase(),
                redFont));

        addEmptyLine(preface, 1);

        createTablePatient(preface, p);
        document.add(preface);

        /*
        preface.add(new Paragraph(
                "This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
                redFont));

        document.add(preface);
        // Start a new page
        document.newPage();*/
    }

    private static void addContentPatient(Document document, Patient p) throws DocumentException {
        Anchor anchor = new Anchor("First Chapter", catFont);
        anchor.setName("First Chapter");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

        Paragraph subPara = new Paragraph("Subcategory 1", subFont);
        Section subCatPart = catPart.addSection(subPara);
        /*subCatPart.add(new Paragraph("Hello"));

        subPara = new Paragraph("Subcategory 2", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Paragraph 1"));
        subCatPart.add(new Paragraph("Paragraph 2"));
        subCatPart.add(new Paragraph("Paragraph 3"));*/

        // add a list
        //createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        //subCatPart.add(paragraph);

        // add a table
        createTablePatient(subCatPart, p);
        // now add all this to the document
        document.add(catPart);

    }

    private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("First point"));
        list.add(new ListItem("Second point"));
        list.add(new ListItem("Third point"));
        subCatPart.add(list);
    }

    private static void createTablePatient(Section subCatPart, Patient p)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);
        PdfPTable table1 = new PdfPTable(3);
        PdfPTable table2 = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Nom "));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prénom"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date de naissance"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell(p.getNom().toUpperCase());
        table.addCell(p.getPrenom().toUpperCase());
        table.addCell(p.getDate().toString());
        /*table.addCell("2.1");
        table.addCell("2.2");
        table.addCell("2.3");
         */
        subCatPart.add(table);

    }

    private static void createTablePatient(Paragraph pre, Patient p)
            throws BadElementException {
        PdfPTable table = new PdfPTable(3);
        PdfPTable table1 = new PdfPTable(3);
        PdfPTable table2 = new PdfPTable(3);
        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        PdfPCell c1 = new PdfPCell(new Phrase("Nom "));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Prénom"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Date de naissance"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell(p.getNom().toUpperCase());
        table.addCell(p.getPrenom().toUpperCase());
        table.addCell(p.getDate().toString());

        //table1
        pre.add(table);
        addEmptyLine(pre, 2);
        pre.add(new Paragraph(
                "Examen(s) : ",
                smallBold));
        addEmptyLine(pre, 1);

        PdfPCell c11 = new PdfPCell(new Phrase("Examen"));
        c11.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(c11);

        c11 = new PdfPCell(new Phrase("Resultat"));
        c11.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(c11);

        c11 = new PdfPCell(new Phrase("Date"));
        c11.setHorizontalAlignment(Element.ALIGN_CENTER);
        table1.addCell(c11);
        table1.setHeaderRows(1);
        for (Rendu r : RenduDao.findAllRenduByPatientId(p.getIdPatient())) {
            Examen examen = ExamenDao.findExamenById(r.getIdExamen());

            table1.addCell(examen.Nom.toUpperCase());
            table1.addCell(examen.Resultat.toUpperCase());
            table1.addCell(examen.getDateExamen().toString());
        }

        pre.add(table1);
        addEmptyLine(pre, 2);
        pre.add(new Paragraph(
                "Rendu(s) : ",
                smallBold));
        addEmptyLine(pre, 1);

        PdfPCell c12 = new PdfPCell(new Phrase("Commentaire"));
        c12.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c12);

        c12 = new PdfPCell(new Phrase("Resultat"));
        c12.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c12);

        c12 = new PdfPCell(new Phrase("Date"));
        c12.setHorizontalAlignment(Element.ALIGN_CENTER);
        table2.addCell(c12);
        table2.setHeaderRows(1);
        for (Rendu r : RenduDao.findAllRenduByPatientId(p.getIdPatient())) {

            table2.addCell(r.getCommentaire().toUpperCase());
            table2.addCell(r.getResultat().toUpperCase());
            table2.addCell(r.getDateRendu().toString());
        }

        pre.add(table2);
    }
}
