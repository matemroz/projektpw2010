--------------------------------------------------------
--  File created - Sunday-June-05-2011   
--------------------------------------------------------
  DROP TABLE "AREA" cascade constraints;
  DROP TABLE "AREAPOINT" cascade constraints;
  DROP TABLE "CHILDREN" cascade constraints;
  DROP TABLE "FAMILY" cascade constraints;
  DROP TABLE "MARKER" cascade constraints;
  DROP TABLE "PARENT" cascade constraints;
  DROP TABLE "PARENTSETTINGS" cascade constraints;
  DROP SEQUENCE "AREAPOINT_SEQ";
  DROP SEQUENCE "AREA_SEQ";
  DROP SEQUENCE "FAMILY_SEQ";
  DROP SEQUENCE "I_SEQ";
  DROP SEQUENCE "CHILDREN_SEQ";
  DROP SEQUENCE "MARKER_SEQ";
  DROP SEQUENCE "PARENT_SEQ";
  DROP SEQUENCE "PARENTSETTINGS_SEQ";
--------------------------------------------------------
--  DDL for Sequence AREAPOINT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "AREAPOINT_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence AREA_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "AREA_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence FAMILY_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "FAMILY_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence I_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "I_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence MARKER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "MARKER_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PARENT_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PARENT_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence PARENTSETTINGS_SEQ
--------------------------------------------------------

   CREATE SEQUENCE  "PARENTSETTINGS_SEQ"  MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table AREA
--------------------------------------------------------

  CREATE TABLE "AREA" 
   (	"IDAREA" NUMBER(19,0), 
	"R" FLOAT(126), 
	"IDCHILD" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table AREAPOINT
--------------------------------------------------------

  CREATE TABLE "AREAPOINT" 
   (	"IDAREAPOINT" NUMBER(19,0), 
	"IDAREA" NUMBER(19,0), 
	"X" FLOAT(126), 
	"Y" FLOAT(126)
   ) ;
--------------------------------------------------------
--  DDL for Table CHILDREN
--------------------------------------------------------

  CREATE TABLE "CHILDREN" 
   (	"IDCHILD" NUMBER(19,0), 
	"IDAREA" NUMBER(19,0), 
	"CHILDNAME" VARCHAR2(20), 
	"IMEI" VARCHAR2(20), 
	"KEY" VARCHAR2(20), 
	"DATEOFBIRTH" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table FAMILY
--------------------------------------------------------

  CREATE TABLE "FAMILY" 
   (	"IDFAMILY" NUMBER(19,0), 
	"IDPARENT" NUMBER(19,0), 
	"IDCHILD" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table MARKER
--------------------------------------------------------

  CREATE TABLE "MARKER" 
   (	"IDMARKER" NUMBER, 
	"IDCHILD" NUMBER, 
	"X" VARCHAR2(20), 
	"Y" VARCHAR2(20), 
	"DATETIME" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table PARENT
--------------------------------------------------------

  CREATE TABLE "PARENT" 
   (	"IDPARENT" NUMBER(19,0), 
	"SHORTEDPASSWORD" VARCHAR2(20), 
	"EMAIL" VARCHAR2(20), 
	"PARENTNAME" VARCHAR2(20)
   ) ;
--------------------------------------------------------
--  DDL for Table PARENTSETTINGS
--------------------------------------------------------

  CREATE TABLE "PARENTSETTINGS" 
   (	"IDPARENTSETTINGS" NUMBER(19,0), 
	"IDPARENT" NUMBER(19,0), 
	"IDCHILD" NUMBER(19,0), 
	"STEPTIME" FLOAT(126)
   ) ;
--------------------------------------------------------
--  Constraints for Table CHILDREN
--------------------------------------------------------

  ALTER TABLE "CHILDREN" ADD CONSTRAINT "I_PK" PRIMARY KEY ("IDCHILD") ENABLE;
 
  ALTER TABLE "CHILDREN" MODIFY ("IDCHILD" NOT NULL ENABLE);
 
  ALTER TABLE "CHILDREN" MODIFY ("IDAREA" NOT NULL ENABLE);
 
  ALTER TABLE "CHILDREN" MODIFY ("CHILDNAME" NOT NULL ENABLE);
 
  ALTER TABLE "CHILDREN" MODIFY ("IMEI" NOT NULL ENABLE);
 
  ALTER TABLE "CHILDREN" MODIFY ("KEY" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PARENTSETTINGS
--------------------------------------------------------

  ALTER TABLE "PARENTSETTINGS" ADD CONSTRAINT "PARENTSETTINGS_PK" PRIMARY KEY ("IDPARENTSETTINGS") ENABLE;
 
  ALTER TABLE "PARENTSETTINGS" MODIFY ("IDPARENTSETTINGS" NOT NULL ENABLE);
 
  ALTER TABLE "PARENTSETTINGS" MODIFY ("IDPARENT" NOT NULL ENABLE);
 
  ALTER TABLE "PARENTSETTINGS" MODIFY ("IDCHILD" NOT NULL ENABLE);
 
  ALTER TABLE "PARENTSETTINGS" MODIFY ("STEPTIME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table AREAPOINT
--------------------------------------------------------

  ALTER TABLE "AREAPOINT" ADD CONSTRAINT "AREAPOINT_PK" PRIMARY KEY ("IDAREAPOINT") ENABLE;
 
  ALTER TABLE "AREAPOINT" MODIFY ("IDAREAPOINT" NOT NULL ENABLE);
 
  ALTER TABLE "AREAPOINT" MODIFY ("IDAREA" NOT NULL ENABLE);
 
  ALTER TABLE "AREAPOINT" MODIFY ("X" NOT NULL ENABLE);
 
  ALTER TABLE "AREAPOINT" MODIFY ("Y" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table FAMILY
--------------------------------------------------------

  ALTER TABLE "FAMILY" ADD CONSTRAINT "FAMILY_PK" PRIMARY KEY ("IDFAMILY") ENABLE;
 
  ALTER TABLE "FAMILY" MODIFY ("IDFAMILY" NOT NULL ENABLE);
 
  ALTER TABLE "FAMILY" MODIFY ("IDPARENT" NOT NULL ENABLE);
 
  ALTER TABLE "FAMILY" MODIFY ("IDCHILD" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table MARKER
--------------------------------------------------------

  ALTER TABLE "MARKER" ADD CONSTRAINT "MARKER_PK" PRIMARY KEY ("IDMARKER") ENABLE;
 
  ALTER TABLE "MARKER" MODIFY ("IDMARKER" NOT NULL ENABLE);
 
  ALTER TABLE "MARKER" MODIFY ("IDCHILD" NOT NULL ENABLE);
 
  ALTER TABLE "MARKER" MODIFY ("X" NOT NULL ENABLE);
 
  ALTER TABLE "MARKER" MODIFY ("Y" NOT NULL ENABLE);
 
  ALTER TABLE "MARKER" MODIFY ("DATETIME" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table AREA
--------------------------------------------------------

  ALTER TABLE "AREA" ADD CONSTRAINT "AREA_PK" PRIMARY KEY ("IDAREA") ENABLE;
 
  ALTER TABLE "AREA" MODIFY ("IDAREA" NOT NULL ENABLE);
 
  ALTER TABLE "AREA" MODIFY ("R" NOT NULL ENABLE);
 
  ALTER TABLE "AREA" MODIFY ("IDCHILD" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PARENT
--------------------------------------------------------

  ALTER TABLE "PARENT" ADD CONSTRAINT "PARENT_PK" PRIMARY KEY ("IDPARENT") ENABLE;
 
  ALTER TABLE "PARENT" MODIFY ("IDPARENT" NOT NULL ENABLE);
 
  ALTER TABLE "PARENT" MODIFY ("SHORTEDPASSWORD" NOT NULL ENABLE);
 
  ALTER TABLE "PARENT" MODIFY ("EMAIL" NOT NULL ENABLE);
 
  ALTER TABLE "PARENT" MODIFY ("PARENTNAME" NOT NULL ENABLE);
--------------------------------------------------------
--  DDL for Index FAMILY_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "FAMILY_PK" ON "FAMILY" ("IDFAMILY") 
  ;
--------------------------------------------------------
--  DDL for Index I_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "I_PK" ON "CHILDREN" ("IDCHILD") 
  ;
--------------------------------------------------------
--  DDL for Index AREAPOINT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "AREAPOINT_PK" ON "AREAPOINT" ("IDAREAPOINT") 
  ;
--------------------------------------------------------
--  DDL for Index PARENTSETTINGS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PARENTSETTINGS_PK" ON "PARENTSETTINGS" ("IDPARENTSETTINGS") 
  ;
--------------------------------------------------------
--  DDL for Index AREA_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "AREA_PK" ON "AREA" ("IDAREA") 
  ;
--------------------------------------------------------
--  DDL for Index MARKER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MARKER_PK" ON "MARKER" ("IDMARKER") 
  ;
--------------------------------------------------------
--  DDL for Index PARENT_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "PARENT_PK" ON "PARENT" ("IDPARENT") 
  ;

--------------------------------------------------------
--  Ref Constraints for Table AREAPOINT
--------------------------------------------------------

  ALTER TABLE "AREAPOINT" ADD CONSTRAINT "AREAPOINT_AREA_FK1" FOREIGN KEY ("IDAREA")
	  REFERENCES "AREA" ("IDAREA") ENABLE;





--------------------------------------------------------
--  DDL for Trigger AREAPOINT_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "AREAPOINT_TRG" BEFORE
  INSERT ON AREAPOINT FOR EACH ROW BEGIN <<COLUMN_SEQUENCES>> BEGIN IF :NEW.IDAREAPOINT IS NULL THEN
  SELECT AREAPOINT_SEQ.NEXTVAL INTO :NEW.IDAREAPOINT FROM DUAL;
END IF;
END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "AREAPOINT_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger AREA_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "AREA_TRG" BEFORE
  INSERT ON AREA FOR EACH ROW BEGIN <<COLUMN_SEQUENCES>> BEGIN IF :NEW.IDAREA IS NULL THEN
  SELECT AREA_SEQ.NEXTVAL INTO :NEW.IDAREA FROM DUAL;
END IF;
END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "AREA_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger FAMILY_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "FAMILY_TRG" BEFORE
  INSERT ON FAMILY FOR EACH ROW BEGIN <<COLUMN_SEQUENCES>> BEGIN IF :NEW.IDFAMILY IS NULL THEN
  SELECT FAMILY_SEQ.NEXTVAL INTO :NEW.IDFAMILY FROM DUAL;
END IF;
END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "FAMILY_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger I_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "I_TRG" BEFORE
  INSERT ON CHILDREN FOR EACH ROW BEGIN <<COLUMN_SEQUENCES>> BEGIN IF :NEW.IDCHILD IS NULL THEN
  SELECT I_SEQ.NEXTVAL INTO :NEW.IDCHILD FROM DUAL;
END IF;
END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "I_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger MARKER_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "MARKER_TRG" BEFORE
  INSERT ON MARKER FOR EACH ROW BEGIN <<COLUMN_SEQUENCES>> BEGIN IF :NEW.IDMARKER IS NULL THEN
  SELECT MARKER_SEQ.NEXTVAL INTO :NEW.IDMARKER FROM DUAL;
END IF;
END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "MARKER_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PARENTSETTINGS_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PARENTSETTINGS_TRG" BEFORE
  INSERT ON PARENTSETTINGS FOR EACH ROW BEGIN <<COLUMN_SEQUENCES>> BEGIN IF :NEW.IDPARENTSETTINGS IS NULL THEN
  SELECT PARENTSETTINGS_SEQ.NEXTVAL INTO :NEW.IDPARENTSETTINGS FROM DUAL;
END IF;
END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PARENTSETTINGS_TRG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger PARENT_TRG
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "PARENT_TRG" BEFORE
  INSERT ON PARENT FOR EACH ROW BEGIN <<COLUMN_SEQUENCES>> BEGIN IF :NEW.IDPARENT IS NULL THEN
  SELECT PARENT_SEQ.NEXTVAL INTO :NEW.IDPARENT FROM DUAL;
END IF;
END COLUMN_SEQUENCES;
END;
/
ALTER TRIGGER "PARENT_TRG" ENABLE;
