--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4 (Ubuntu 13.4-0ubuntu0.21.04.1)
-- Dumped by pg_dump version 13.4 (Ubuntu 13.4-0ubuntu0.21.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: moving_movers_bios; Type: TABLE; Schema: public; Owner: loop
--

CREATE TABLE public.moving_movers_bios (
    id integer NOT NULL,
    name character varying,
    extra_services character varying,
    contacts integer,
    inventory_charges integer,
    charge_per_distance integer
);


ALTER TABLE public.moving_movers_bios OWNER TO loop;

--
-- Name: moving_movers_bios_id_seq; Type: SEQUENCE; Schema: public; Owner: loop
--

CREATE SEQUENCE public.moving_movers_bios_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.moving_movers_bios_id_seq OWNER TO loop;

--
-- Name: moving_movers_bios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: loop
--

ALTER SEQUENCE public.moving_movers_bios_id_seq OWNED BY public.moving_movers_bios.id;


--
-- Name: moving_orders; Type: TABLE; Schema: public; Owner: loop
--

CREATE TABLE public.moving_orders (
    id integer NOT NULL,
    user_name character varying,
    user_email character varying,
    inventory character varying,
    current_location character varying,
    new_location character varying,
    moving_company character varying,
    total_price integer,
    order_status character varying,
    pickup_time character varying
);


ALTER TABLE public.moving_orders OWNER TO loop;

--
-- Name: moving_orders_id_seq; Type: SEQUENCE; Schema: public; Owner: loop
--

CREATE SEQUENCE public.moving_orders_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.moving_orders_id_seq OWNER TO loop;

--
-- Name: moving_orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: loop
--

ALTER SEQUENCE public.moving_orders_id_seq OWNED BY public.moving_orders.id;


--
-- Name: moving_movers_bios id; Type: DEFAULT; Schema: public; Owner: loop
--

ALTER TABLE ONLY public.moving_movers_bios ALTER COLUMN id SET DEFAULT nextval('public.moving_movers_bios_id_seq'::regclass);


--
-- Name: moving_orders id; Type: DEFAULT; Schema: public; Owner: loop
--

ALTER TABLE ONLY public.moving_orders ALTER COLUMN id SET DEFAULT nextval('public.moving_orders_id_seq'::regclass);


--
-- Data for Name: moving_movers_bios; Type: TABLE DATA; Schema: public; Owner: loop
--

COPY public.moving_movers_bios (id, name, extra_services, contacts, inventory_charges, charge_per_distance) FROM stdin;
1	snilloc	offloading	748346387	2020	200
\.


--
-- Data for Name: moving_orders; Type: TABLE DATA; Schema: public; Owner: loop
--

COPY public.moving_orders (id, user_name, user_email, inventory, current_location, new_location, moving_company, total_price, order_status, pickup_time) FROM stdin;
1	fBiXip57zVfVxvtzaCVl0dkTtGi1	diana.kerubo@student.moringaschool.com	One Bedroom	A1, Kisumu, Kenya	Athi River, Kenya	trusties	79898	approved	17/8/2021 6:52 
2	fBiXip57zVfVxvtzaCVl0dkTtGi1	diana.kerubo@student.moringaschool.com	One Bedroom	Kenya	Eldoret, Kenya	Bobos Moving Company	79898	approved	20/8/2021 8:7 
3	fBiXip57zVfVxvtzaCVl0dkTtGi1	diana.kerubo@student.moringaschool.com	Three bedroom	Muthama Rd, Nairobi, Kenya	Kahawa Wendani, Kenya	trusties	79898	approved	17/8/2021 9:8 
4	fBiXip57zVfVxvtzaCVl0dkTtGi1	diana.kerubo@student.moringaschool.com	One Bedroom	Thika Rd, Nairobi, Kenya	Nairobi, Kenya	Southwell	79898	approved	24/8/2021 9:58 
5	fBiXip57zVfVxvtzaCVl0dkTtGi1	diana.kerubo@student.moringaschool.com	One Bedroom	Lodwar, Kenya	Machakos, Kenya	The move	79898	approved	16/8/2021 10:24 
6	fBiXip57zVfVxvtzaCVl0dkTtGi1	diana.kerubo@student.moringaschool.com	One Bedroom	Wajir, Kenya	Mombasa, Kenya	The move	79898	approved	16/8/2021 11:59 
\.


--
-- Name: moving_movers_bios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: loop
--

SELECT pg_catalog.setval('public.moving_movers_bios_id_seq', 1, true);


--
-- Name: moving_orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: loop
--

SELECT pg_catalog.setval('public.moving_orders_id_seq', 6, true);


--
-- Name: moving_movers_bios moving_movers_bios_pkey; Type: CONSTRAINT; Schema: public; Owner: loop
--

ALTER TABLE ONLY public.moving_movers_bios
    ADD CONSTRAINT moving_movers_bios_pkey PRIMARY KEY (id);


--
-- Name: moving_orders moving_orders_pkey; Type: CONSTRAINT; Schema: public; Owner: loop
--

ALTER TABLE ONLY public.moving_orders
    ADD CONSTRAINT moving_orders_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

