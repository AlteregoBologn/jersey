--------------------------------------------------------
--  DDL for Sequence SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 238 CACHE 20 NOORDER  NOCYCLE ;

--------------------------------------------------------
--  DDL for Table T_IMMOBILE
--------------------------------------------------------


drop table T_IMMOBILE;

  CREATE TABLE "T_IMMOBILE" 
   (	"VIA" VARCHAR2(50 BYTE), 
	"CIVICO" VARCHAR2(10 BYTE), 
	"INTERNO" VARCHAR2(10 BYTE), 
	"PIANO" VARCHAR2(10 BYTE), 
	"DATADA" DATE, 
	"NOMEPROPRIETARIO" VARCHAR2(20 BYTE), 
	"QUALITA" VARCHAR2(20 BYTE), 
	"NOMEPRECEDENTEDETENTORE" VARCHAR2(60 BYTE), 
	"UNID" NUMBER
   );
--------------------------------------------------------
--  DDL for Index T_IMMOBILE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "T_IMMOBILE_PK" ON "T_IMMOBILE" ("UNID");
--------------------------------------------------------
--  Constraints for Table T_IMMOBILE
--------------------------------------------------------

  ALTER TABLE "T_IMMOBILE" ADD CONSTRAINT "T_IMMOBILE_PK" PRIMARY KEY ("UNID");
  ALTER TABLE "T_IMMOBILE" MODIFY ("UNID" NOT NULL ENABLE);

   
   
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
--  DDL for Index T_LOCALE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "T_LOCALE_PK" ON "T_LOCALE" ("UNID");
--------------------------------------------------------
--  Constraints for Table T_LOCALE
--------------------------------------------------------

  ALTER TABLE "T_LOCALE" ADD CONSTRAINT "T_LOCALE_PK" PRIMARY KEY ("UNID");
  ALTER TABLE "TEST"."T_LOCALE" MODIFY ("UNID" NOT NULL ENABLE);

   
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
--  DDL for Index T_DICHIARAZIONE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "T_DICHIARAZIONE_PK" ON "T_DICHIARAZIONE" ("UNID") ;
--------------------------------------------------------
--  Constraints for Table T_DICHIARAZIONE
--------------------------------------------------------

  ALTER TABLE "T_DICHIARAZIONE" ADD CONSTRAINT "T_DICHIARAZIONE_PK" PRIMARY KEY ("UNID");
  ALTER TABLE "T_DICHIARAZIONE" MODIFY ("UNID" NOT NULL ENABLE);

   
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
--  DDL for Index T_PRECEDENTEDICHIARAZIONE_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "T_PRECEDENTEDICHIARAZIONE_PK" ON "T_PRECEDENTEDICHIARAZIONE" ("UNID");
--------------------------------------------------------
--  Constraints for Table T_PRECEDENTEDICHIARAZIONE
--------------------------------------------------------

  ALTER TABLE "T_PRECEDENTEDICHIARAZIONE" ADD CONSTRAINT "T_PRECEDENTEDICHIARAZIONE_PK" PRIMARY KEY ("UNID");
  ALTER TABLE "T_PRECEDENTEDICHIARAZIONE" MODIFY ("UNID" NOT NULL ENABLE);

   
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
--  DDL for Index T_PERSONAGIURIDICA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "T_PERSONAGIURIDICA_PK" ON "T_PERSONAGIURIDICA" ("UNID");
--------------------------------------------------------
--  Constraints for Table T_PERSONAGIURIDICA
--------------------------------------------------------

  ALTER TABLE "T_PERSONAGIURIDICA" ADD CONSTRAINT "T_PERSONAGIURIDICA_PK" PRIMARY KEY ("UNID");
  ALTER TABLE "T_PERSONAGIURIDICA" MODIFY ("UNID" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table T_PERSONAGIURIDICA
--------------------------------------------------------

  ALTER TABLE "T_PERSONAGIURIDICA" ADD CONSTRAINT "T_PERSONAGIURIDICA_T_PERS_FK1" FOREIGN KEY ("RAPPRESENTANTE")
	  REFERENCES "T_PERSONA" ("UNID") ENABLE;

	  
--------------------------------------------------------
--  DDL for Table REL_DICHIARAZIONE_IMMOBILE
--------------------------------------------------------

  CREATE TABLE "REL_DICHIARAZIONE_IMMOBILE" 
   (	
   	"IDDICHIARAZIONE" NUMBER, 
	"IDIMMOBILE" NUMBER
   );
--------------------------------------------------------
--  Ref Constraints for Table REL_DICHIARAZIONE_IMMOBILE
--------------------------------------------------------

  ALTER TABLE "REL_DICHIARAZIONE_IMMOBILE" ADD CONSTRAINT "REL_DICHIARAZIONE_IMMOBIL_FK1" FOREIGN KEY ("IDDICHIARAZIONE")
	  REFERENCES "T_DICHIARAZIONE" ("UNID") ENABLE;
  ALTER TABLE "REL_DICHIARAZIONE_IMMOBILE" ADD CONSTRAINT "REL_DICHIARAZIONE_IMMOBIL_FK2" FOREIGN KEY ("IDIMMOBILE")
	  REFERENCES "T_IMMOBILE" ("UNID") ENABLE;
--------------------------------------------------------
--  DDL for Table REL_PERSONA_DICHIARAZIONE
--------------------------------------------------------

  CREATE TABLE "REL_PERSONA_DICHIARAZIONE" 
   (	
   	"IDPERSONA" NUMBER, 
	"IDDICHIARAZIONE" NUMBER
   );
--------------------------------------------------------
--  Ref Constraints for Table REL_PERSONA_DICHIARAZIONE
--------------------------------------------------------

  ALTER TABLE "REL_PERSONA_DICHIARAZIONE" ADD CONSTRAINT "REL_PERSONA_DICHIARAZIONE_FK1" FOREIGN KEY ("IDPERSONA")
	  REFERENCES "T_PERSONA" ("UNID") ENABLE;
  ALTER TABLE "REL_PERSONA_DICHIARAZIONE" ADD CONSTRAINT "REL_PERSONA_DICHIARAZIONE_FK2" FOREIGN KEY ("IDDICHIARAZIONE")
	  REFERENCES "T_DICHIARAZIONE" ("UNID") ENABLE;

--------------------------------------------------------
--  DDL for Table REL_DICHIARAZIONE_PRECDICHIARA
--------------------------------------------------------

  CREATE TABLE "REL_DICHIARAZIONE_PRECDICHIARA" 
   (	
   	"IDPRECEDENTEDICHIARAZIONE" NUMBER, 
	"IDDICHIARAZIONE" NUMBER
   );
--------------------------------------------------------
--  Ref Constraints for Table REL_DICHIARAZIONE_PRECDICHIARA
--------------------------------------------------------

  ALTER TABLE "REL_DICHIARAZIONE_PRECDICHIARA" ADD CONSTRAINT "REL_DICHIARAZIONE_PRECDIC_FK1" FOREIGN KEY ("IDDICHIARAZIONE")
	  REFERENCES "T_DICHIARAZIONE" ("UNID") ENABLE;
  ALTER TABLE "REL_DICHIARAZIONE_PRECDICHIARA" ADD CONSTRAINT "REL_DICHIARAZIONE_PRECDIC_FK2" FOREIGN KEY ("IDPRECEDENTEDICHIARAZIONE")
	  REFERENCES "T_PRECEDENTEDICHIARAZIONE" ("UNID") ENABLE;

--------------------------------------------------------
--  DDL for Table REL_IMMOBILE_LOCALE
--------------------------------------------------------

  CREATE TABLE "REL_IMMOBILE_LOCALE" 
   (	
   	"IDIMMOBILE" NUMBER, 
	"IDLOCALE" NUMBER
   );
--------------------------------------------------------
--  Ref Constraints for Table REL_IMMOBILE_LOCALE
--------------------------------------------------------

  ALTER TABLE "REL_IMMOBILE_LOCALE" ADD CONSTRAINT "REL_IMMOBILE_LOCALE_T_IMM_FK1" FOREIGN KEY ("IDIMMOBILE")
	  REFERENCES "T_IMMOBILE" ("UNID") ENABLE;
  ALTER TABLE "REL_IMMOBILE_LOCALE" ADD CONSTRAINT "REL_IMMOBILE_LOCALE_T_LOC_FK1" FOREIGN KEY ("IDLOCALE")
	  REFERENCES "T_LOCALE" ("UNID") ENABLE;

--------------------------------------------------------
--  DDL for Table REL_PERSGIURIDICA_DICHIARAZION
--------------------------------------------------------

  CREATE TABLE "REL_PERSGIURIDICA_DICHIARAZION" 
   (	
   	"IDPERSONAGIURIDICA" NUMBER, 
	"IDDICHIARAZIONE" NUMBER
   );
--------------------------------------------------------
--  Ref Constraints for Table REL_PERSGIURIDICA_DICHIARAZION
--------------------------------------------------------

  ALTER TABLE "REL_PERSGIURIDICA_DICHIARAZION" ADD CONSTRAINT "REL_PERSGIURIDICA_DICHIAR_FK1" FOREIGN KEY ("IDDICHIARAZIONE")
	  REFERENCES "T_DICHIARAZIONE" ("UNID") ENABLE;
  ALTER TABLE "REL_PERSGIURIDICA_DICHIARAZION" ADD CONSTRAINT "REL_PERSGIURIDICA_DICHIAR_FK2" FOREIGN KEY ("IDPERSONAGIURIDICA")
	  REFERENCES "T_PERSONAGIURIDICA" ("UNID") ENABLE;







   
   