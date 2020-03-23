/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import entity.Customer;
import entity.Invoice;
import entity.Order;
import entity.Product;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel Gomez
 */
@Named(value = "pdfgenerator")
@SessionScoped

public class PDFGenerator implements Serializable{

    public void generatePDF(Invoice inv) {
        Document document = new Document();

        String file = "inv_" + inv.getInvoiceId() + ".pdf";
        try {
            try {
                PdfWriter.getInstance(document, new FileOutputStream("D:/"+file));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DocumentException ex) {
            Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }

        document.open();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persis");
        EntityManager em = entityManagerFactory.createEntityManager();

        Order ord = em.find(Order.class, inv.getOrderId());
        Customer cus = em.find(Customer.class, ord.getCustId());
        
        em.close();
        entityManagerFactory.close();

        try {
            document.add(new Paragraph("Invoice number: " + inv.getInvoiceId()));
            document.add(new Paragraph("________________________________"));
            document.add(new Paragraph("Customer Data:"));
            document.add(new Paragraph("Name: " + cus.getFirstName() + " " + cus.getLastName()));
            document.add(new Paragraph("Address: " + cus.getStreet() + " " + cus.getAppt() + " " + cus.getCity() + " " + cus.getZipCode()));
            document.add(new Paragraph("________________________________"));
            document.add(new Paragraph("Order Details: " + ord.getOrderDesc()));
            document.add(new Paragraph("Invoice date: " + inv.getOrderRaisedDt()));
        } catch (DocumentException ex) {
            Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        Table table;
        try {
            table = new Table(4);
            table.setBorderWidth(1);
            table.setBorderColor(new Color(50, 100, 200));
            table.setPadding(5);
            table.setSpacing(5);
            Cell cell = new Cell("header");
            cell.setHeader(true);
            cell = new Cell("Number");
            table.addCell(cell);
            cell = new Cell("Products");
            table.addCell(cell);
            cell = new Cell("Total Price");
            table.addCell(cell);
            cell = new Cell("State");
            table.addCell(cell);
            table.endHeaders();

            table.addCell(String.valueOf(inv.getInvoiceId()));
            String prodInOrd = "";
            for(Product p : ord.getProductList()){
                prodInOrd = prodInOrd + p.getProdName() + "\n";
            }
            table.addCell(prodInOrd);
            
            table.addCell(String.valueOf(ord.getTotPrice()));
            
            if (inv.getOrderSettledDt()==null){
                table.addCell("Not Settled");
                
            }else{
                table.addCell("Settled");
            }
            try {
                document.add(table);
                document.close();
            } catch (DocumentException ex) {
                Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (BadElementException ex) {
            Logger.getLogger(PDFGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
