package com.example.svu_reports.database.model;

public class InvoiceModel {


   private int id;
   private int agent_id;
   private String date;
   private boolean isBuy;
   private  String description;

   public InvoiceModel(int id, int agent_id, String date, boolean isBuy, String description) {
      this.id = id;
      this.agent_id = agent_id;
      this.date = date;
      this.isBuy = isBuy;
      this.description = description;
   }

   public InvoiceModel(int agent_id, String date, boolean isBuy, String description) {
      this.agent_id = agent_id;
      this.date = date;
      this.isBuy = isBuy;
      this.description = description;
   }
   public InvoiceModel(int agent_id, boolean isBuy, String description) {
      this.agent_id = agent_id;
      this.isBuy = isBuy;
      this.description = description;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public int getAgent_id() {
      return agent_id;
   }

   public void setAgent_id(int agent_id) {
      this.agent_id = agent_id;
   }

   public String getDate() {
      return date;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public boolean isBuy() {
      return isBuy;
   }

   public void setBuy(boolean buy) {
      isBuy = buy;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }




}
