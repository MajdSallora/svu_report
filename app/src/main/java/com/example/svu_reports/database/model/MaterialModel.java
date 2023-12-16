package com.example.svu_reports.database.model;

public class MaterialModel {
   private int id;
   private String name;
   private int type_id;
   private String description;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public MaterialModel(int id, String name, int type_id, String description) {
      this.id = id;
      this.name = name;
      this.type_id = type_id;
      this.description = description;
   }

   public int getType_id() {
      return type_id;
   }

   public void setType_id(int type_id) {
      this.type_id = type_id;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }


}
