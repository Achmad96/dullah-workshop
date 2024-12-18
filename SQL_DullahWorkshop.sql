-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://github.com/pgadmin-org/pgadmin4/issues/new/choose if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE IF NOT EXISTS public.detail_pembayaran
(
    subtotal integer NOT NULL,
    id_sparepart character(5) COLLATE pg_catalog."default" NOT NULL,
    id_detail_pembayaran character(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT detail_pembayaran_pkey PRIMARY KEY (id_sparepart, id_detail_pembayaran)
);

CREATE TABLE IF NOT EXISTS public.detail_perbaikan
(
    id_detail_perbaikan character(10) COLLATE pg_catalog."default" NOT NULL,
    id_sparepart character(5) COLLATE pg_catalog."default" NOT NULL,
    jumlah_sparepart integer NOT NULL,
    CONSTRAINT detail_perbaikan_pkey PRIMARY KEY (id_detail_perbaikan, id_sparepart)
);

CREATE TABLE IF NOT EXISTS public.kasir
(
    id_kasir character(5) COLLATE pg_catalog."default" NOT NULL,
    nama_kasir character varying(50) COLLATE pg_catalog."default" NOT NULL,
    waktu_kerja character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT pk_kasir PRIMARY KEY (id_kasir)
);

CREATE TABLE IF NOT EXISTS public.kendaraan
(
    id_kendaraan character(5) COLLATE pg_catalog."default" NOT NULL,
    id_pelanggan character(5) COLLATE pg_catalog."default" NOT NULL,
    no_plat character(15) COLLATE pg_catalog."default",
    merk character varying(50) COLLATE pg_catalog."default",
    model character varying(50) COLLATE pg_catalog."default",
    tahun integer,
    kilometer integer,
    no_mesin character(10) COLLATE pg_catalog."default",
    no_rangka character(10) COLLATE pg_catalog."default",
    CONSTRAINT pk_kendaraan PRIMARY KEY (id_kendaraan)
);

CREATE TABLE IF NOT EXISTS public.konsultan
(
    id_konsultan character(5) COLLATE pg_catalog."default" NOT NULL,
    nama_konsultan character varying(50) COLLATE pg_catalog."default" NOT NULL,
    alamat_konsultan character varying(50) COLLATE pg_catalog."default" NOT NULL,
    telpon_konsultan character(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_konsultan PRIMARY KEY (id_konsultan)
);

CREATE TABLE IF NOT EXISTS public.layanan
(
    id_layanan character(5) COLLATE pg_catalog."default" NOT NULL,
    jenis_layanan character varying(50) COLLATE pg_catalog."default" NOT NULL,
    biaya_layanan integer,
    CONSTRAINT pk_jasa PRIMARY KEY (id_layanan)
);

CREATE TABLE IF NOT EXISTS public.mekanik
(
    id_mekanik character(5) COLLATE pg_catalog."default" NOT NULL,
    nama_mekanik character varying(50) COLLATE pg_catalog."default" NOT NULL,
    spesialis_mekanik character varying(50) COLLATE pg_catalog."default" NOT NULL,
    telpon_mekanik character(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_mekanik PRIMARY KEY (id_mekanik)
);

CREATE TABLE IF NOT EXISTS public.pelanggan
(
    id_pelanggan character(5) COLLATE pg_catalog."default" NOT NULL,
    nama_pelanggan character varying(50) COLLATE pg_catalog."default" NOT NULL,
    alamat_pelanggan character varying(50) COLLATE pg_catalog."default" NOT NULL,
    telpon_pelanggan character(15) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_pelanggan PRIMARY KEY (id_pelanggan)
);

CREATE TABLE IF NOT EXISTS public.pembayaran
(
    id_pembayaran character(10) COLLATE pg_catalog."default" NOT NULL,
    id_perbaikan character(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pembayaran_pkey1 PRIMARY KEY (id_pembayaran)
);

CREATE TABLE IF NOT EXISTS public.penanganan
(
    id_penanganan character(20) COLLATE pg_catalog."default" NOT NULL,
    id_konsultan character(5) COLLATE pg_catalog."default" NOT NULL,
    id_kendaraan character(5) COLLATE pg_catalog."default" NOT NULL,
    status_perbaikan character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_penanganan PRIMARY KEY (id_penanganan)
);

CREATE TABLE IF NOT EXISTS public.perbaikan
(
    id_perbaikan character(10) COLLATE pg_catalog."default" NOT NULL,
    id_reservasi integer NOT NULL,
    CONSTRAINT perbaikan_pkey PRIMARY KEY (id_perbaikan),
    CONSTRAINT reservasi_uniq UNIQUE (id_reservasi)
);

CREATE TABLE IF NOT EXISTS public.reservasi
(
    id_reservasi serial NOT NULL,
    id_kendaraan character(5) COLLATE pg_catalog."default",
    id_mekanik character(5) COLLATE pg_catalog."default",
    CONSTRAINT reservasi_pkey PRIMARY KEY (id_reservasi),
    CONSTRAINT kendaraan_uniq UNIQUE (id_kendaraan)
);

CREATE TABLE IF NOT EXISTS public.riwayat_pembayaran
(
    id_riwayat_pembayaran character(10) COLLATE pg_catalog."default" NOT NULL,
    metode_pembayaran character varying(10) COLLATE pg_catalog."default" NOT NULL,
    total_harga integer NOT NULL,
    id_kasir character(5) COLLATE pg_catalog."default" NOT NULL,
    tanggal_pembayaran date NOT NULL DEFAULT now(),
    id_perbaikan character(10) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pembayaran_pkey PRIMARY KEY (id_riwayat_pembayaran),
    CONSTRAINT perbaikan_uniq UNIQUE (id_perbaikan)
);

CREATE TABLE IF NOT EXISTS public.riwayat_perbaikan
(
    id_riwayat_perbaikan character(10) COLLATE pg_catalog."default" NOT NULL,
    id_kendaraan character(5) COLLATE pg_catalog."default" NOT NULL,
    id_mekanik character(5) COLLATE pg_catalog."default" NOT NULL,
    tanggal_perbaikan date NOT NULL DEFAULT now(),
    kilometer_pertama integer,
    id_layanan character(5) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT pk_transaki PRIMARY KEY (id_riwayat_perbaikan)
);

CREATE TABLE IF NOT EXISTS public.sparepart
(
    id_sparepart character(5) COLLATE pg_catalog."default" NOT NULL,
    jenis_sparepart character varying(30) COLLATE pg_catalog."default" NOT NULL,
    harga_sparepart integer NOT NULL,
    stock_sparepart integer NOT NULL,
    CONSTRAINT pk_sparepart PRIMARY KEY (id_sparepart)
);

CREATE TABLE IF NOT EXISTS public."user"
(
    password character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(50) COLLATE pg_catalog."default" NOT NULL,
    notelp character varying(12) COLLATE pg_catalog."default" NOT NULL,
    alamat character varying(50) COLLATE pg_catalog."default" NOT NULL,
    nama character varying(35) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (email)
);

ALTER TABLE IF EXISTS public.detail_pembayaran
    ADD CONSTRAINT pembayaran_fk FOREIGN KEY (id_detail_pembayaran)
    REFERENCES public.riwayat_pembayaran (id_riwayat_pembayaran) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE CASCADE
    NOT VALID;


ALTER TABLE IF EXISTS public.detail_pembayaran
    ADD CONSTRAINT sparepart_fk FOREIGN KEY (id_sparepart)
    REFERENCES public.sparepart (id_sparepart) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.detail_perbaikan
    ADD CONSTRAINT riwayat_perbaikan_fk FOREIGN KEY (id_detail_perbaikan)
    REFERENCES public.riwayat_perbaikan (id_riwayat_perbaikan) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE CASCADE
    NOT VALID;


ALTER TABLE IF EXISTS public.detail_perbaikan
    ADD CONSTRAINT sparepart_fk FOREIGN KEY (id_sparepart)
    REFERENCES public.sparepart (id_sparepart) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE SET NULL
    NOT VALID;


ALTER TABLE IF EXISTS public.kendaraan
    ADD CONSTRAINT fk_kendaraa_relations_pelangga FOREIGN KEY (id_pelanggan)
    REFERENCES public.pelanggan (id_pelanggan) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT;
CREATE INDEX IF NOT EXISTS relationship_10_fk
    ON public.kendaraan(id_pelanggan);


ALTER TABLE IF EXISTS public.pembayaran
    ADD CONSTRAINT pembayaran_fk FOREIGN KEY (id_pembayaran)
    REFERENCES public.riwayat_pembayaran (id_riwayat_pembayaran) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE CASCADE;
CREATE INDEX IF NOT EXISTS pembayaran_pkey1
    ON public.pembayaran(id_pembayaran);


ALTER TABLE IF EXISTS public.pembayaran
    ADD CONSTRAINT perbaikan_fk FOREIGN KEY (id_perbaikan)
    REFERENCES public.perbaikan (id_perbaikan) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE CASCADE;


ALTER TABLE IF EXISTS public.penanganan
    ADD CONSTRAINT fk_penangan_relations_kendaraa FOREIGN KEY (id_kendaraan)
    REFERENCES public.kendaraan (id_kendaraan) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT;
CREATE INDEX IF NOT EXISTS relationship_13_fk
    ON public.penanganan(id_kendaraan);


ALTER TABLE IF EXISTS public.penanganan
    ADD CONSTRAINT fk_penangan_relations_konsulta FOREIGN KEY (id_konsultan)
    REFERENCES public.konsultan (id_konsultan) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT;
CREATE INDEX IF NOT EXISTS relationship_12_fk
    ON public.penanganan(id_konsultan);


ALTER TABLE IF EXISTS public.perbaikan
    ADD CONSTRAINT perbaikan_fk FOREIGN KEY (id_perbaikan)
    REFERENCES public.riwayat_perbaikan (id_riwayat_perbaikan) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE CASCADE
    NOT VALID;
CREATE INDEX IF NOT EXISTS perbaikan_pkey
    ON public.perbaikan(id_perbaikan);


ALTER TABLE IF EXISTS public.perbaikan
    ADD CONSTRAINT reservasi_fk FOREIGN KEY (id_reservasi)
    REFERENCES public.reservasi (id_reservasi) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    NOT VALID;
CREATE INDEX IF NOT EXISTS reservasi_uniq
    ON public.perbaikan(id_reservasi);


ALTER TABLE IF EXISTS public.reservasi
    ADD CONSTRAINT kendaraan_fk FOREIGN KEY (id_kendaraan)
    REFERENCES public.kendaraan (id_kendaraan) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
CREATE INDEX IF NOT EXISTS kendaraan_uniq
    ON public.reservasi(id_kendaraan);


ALTER TABLE IF EXISTS public.reservasi
    ADD CONSTRAINT mekanik_fk FOREIGN KEY (id_mekanik)
    REFERENCES public.mekanik (id_mekanik) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.riwayat_pembayaran
    ADD CONSTRAINT kasir_fk FOREIGN KEY (id_kasir)
    REFERENCES public.kasir (id_kasir) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.riwayat_pembayaran
    ADD CONSTRAINT perbaikan_fk FOREIGN KEY (id_perbaikan)
    REFERENCES public.riwayat_perbaikan (id_riwayat_perbaikan) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;
CREATE INDEX IF NOT EXISTS perbaikan_uniq
    ON public.riwayat_pembayaran(id_perbaikan);


ALTER TABLE IF EXISTS public.riwayat_perbaikan
    ADD CONSTRAINT fk_layanan FOREIGN KEY (id_layanan)
    REFERENCES public.layanan (id_layanan) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.riwayat_perbaikan
    ADD CONSTRAINT fk_transaki_relations_kendaraa FOREIGN KEY (id_kendaraan)
    REFERENCES public.kendaraan (id_kendaraan) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT;
CREATE INDEX IF NOT EXISTS relationship_7_fk
    ON public.riwayat_perbaikan(id_kendaraan);


ALTER TABLE IF EXISTS public.riwayat_perbaikan
    ADD CONSTRAINT fk_transaki_relations_mekanik FOREIGN KEY (id_mekanik)
    REFERENCES public.mekanik (id_mekanik) MATCH SIMPLE
    ON UPDATE RESTRICT
    ON DELETE RESTRICT;
CREATE INDEX IF NOT EXISTS relationship_11_fk
    ON public.riwayat_perbaikan(id_mekanik);

END;