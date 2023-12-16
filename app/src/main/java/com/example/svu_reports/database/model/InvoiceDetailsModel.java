package com.example.svu_reports.database.model;

public class InvoiceDetailsModel {

      private int id;
      private int Invoice_id;
      private int Material_id;
      private int number;
      private int price;
      private int total;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getInvoice_id() {
      return Invoice_id;
   }

   public void setInvoice_id(int invoice_id) {
      Invoice_id = invoice_id;
   }

   public int getMaterial_id() {
      return Material_id;
   }

   public void setMaterial_id(int material_id) {
      Material_id = material_id;
   }

   public int getNumber() {
      return number;
   }

   public void setNumber(int number) {
      this.number = number;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public InvoiceDetailsModel(int id, int invoice_id, int material_id, int number, int price, int total) {
      this.id = id;
      Invoice_id = invoice_id;
      Material_id = material_id;
      this.number = number;
      this.price = price;
      this.total = total;
   }
   public InvoiceDetailsModel( int invoice_id, int material_id, int number, int price, int total) {
      this.id = id;
      Invoice_id = invoice_id;
      Material_id = material_id;
      this.number = number;
      this.price = price;
      this.total = total;
   }
}
