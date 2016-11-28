--------------------------------------------------------
--  DDL for Sequence SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 238 CACHE 20 NOORDER  NOCYCLE ;

--------------------------------------------------------
--  DDL for Table T_IMMOBILE
--------------------------------------------------------


drop table T_IMMOBILE;

  CREATE TABLE "T_IMMOBILE" 
   (	
    "VIA" VARCHAR2(50 BYTE), 
	"CIVICO" VARCHAR2(10 BYTE), 
	"INTERNO" VARCHAR2(10 BYTE), 
	"PIANO" VARCHAR2(10 BYTE), 
	"DATADA" DATE, 
	"NOMEPROPRIETARIO" VARCHAR2(20 BYTE), 
	"QUALITA" VARCHAR2(20 BYTE), 
	"NOMEPRECEDENTEDETENTORE" VARCHAR2(60 BYTE), 
	"UNID" NUMBER
   )  ;
   
   
--------------------------------------------------------
--  DDL for Table T_LOCALE
--------------------------------------------------------

  CREATE TABLE "T_LOCALE" 
   (	
   	"TIPO" VARCHAR2(150 BYTE), 
	"MQ" NUMBER, 
	"FOGLIO" VARCHAR2(30 BYTE), 
	"PARTICELLA" VARCHAR2(30 BYTE), 
	"SUBALTERNO" VARCHAR2(30 BYTE), 
	"UNID" NUMBER
   );
   
   --------------------------------------------------------
--  DDL for Table T_DICHIARAZIONE
--------------------------------------------------------

  CREATE TABLE "T_DICHIARAZIONE" 
   (	
   	"UNICOOCCUPANTE" VARCHAR2(20 BYTE), 
	"AGRICOLTORE" VARCHAR2(20 BYTE), 
	"ITALIANOALLESTERO" VARCHAR2(20 BYTE), 
	"DATA" VARCHAR2(20 BYTE), 
	"FIRMA" VARCHAR2(20 BYTE), 
	"UNID" NUMBER
   );
   
   --------------------------------------------------------
--  DDL for Table T_PRECEDENTEDICHIARAZIONE
--------------------------------------------------------

  CREATE TABLE "T_PRECEDENTEDICHIARAZIONE" 
   (	
   	"VIA" VARCHAR2(50 BYTE), 
	"MOTIVO" VARCHAR2(80 BYTE), 
	"UNID" NUMBER, 
	"DATADA" VARCHAR2(20 BYTE), 
	"CIVICO" VARCHAR2(20 BYTE), 
	"INTERNO" VARCHAR2(20 BYTE)
   );
   
--------------------------------------------------------
--  DDL for Table T_PERSONAGIURIDICA
--------------------------------------------------------

  CREATE TABLE "T_PERSONAGIURIDICA" 
   (	
   	"DESCRIZIONE" VARCHAR2(40 BYTE), 
	"PIVA" VARCHAR2(30 BYTE), 
	"PEC" VARCHAR2(50 BYTE), 
	"RAPPRESENTANTE" NUMBER, 
	"UNID" NUMBER
   );
--------------------------------------------------------
--  Ref Constraints for Table T_PERSONAGIURIDICA
--------------------------------------------------------

  ALTER TABLE "T_PERSONAGIURIDICA" ADD CONSTRAINT "T_PERSONAGIURIDICA_T_PERS_FK1" FOREIGN KEY ("RAPPRESENTANTE")
	  REFERENCES "T_PERSONA" ("UNID") ENABLE;







   
   