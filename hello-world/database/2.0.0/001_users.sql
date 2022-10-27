CREATE TABLE public.uporabnik (
              id serial NOT NULL,
              ime varchar NULL,
              priimek varchar NULL,
              uporabniskoime varchar NULL,
              CONSTRAINT uporabnik_pk PRIMARY KEY (id));

-- Dummy user sample
INSERT INTO uporabnik (ime, priimek, uporabniskoime) VALUES ('janez', 'novak', 'janezn')