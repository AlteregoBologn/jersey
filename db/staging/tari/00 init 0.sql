--------------------------------------------------------
--  DDL for Sequence SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 238 CACHE 20 NOORDER  NOCYCLE ;

   --------------------------------------------------------
--  DDL for Table T_PERSONA
--------------------------------------------------------

  CREATE TABLE "T_PERSONA" 
   (	
     "UNID" NUMBER(*,0), 
	"NOME" VARCHAR2(20 BYTE), 
	"COGNOME" VARCHAR2(40 BYTE), 
	"CF" VARCHAR2(20 BYTE), 
	"CANC" VARCHAR2(20 BYTE), 
	"PASSWORD" VARCHAR2(40 BYTE), 
	"EMAIL" VARCHAR2(50 BYTE), 
	"SESSO" VARCHAR2(5 BYTE), 
	"DATANASCITA" DATE, 
	"COMUNENASCITA" VARCHAR2(50 BYTE), 
	"PEC" VARCHAR2(50 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Index T_PERSONA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "T_PERSONA_PK" ON "T_PERSONA" ("UNID") ;
--------------------------------------------------------
--  Constraints for Table T_PERSONA
--------------------------------------------------------

  ALTER TABLE "T_PERSONA" ADD CONSTRAINT "T_PERSONA_PK" PRIMARY KEY ("UNID")
  
  ALTER TABLE "T_PERSONA" MODIFY ("UNID" NOT NULL ENABLE);
