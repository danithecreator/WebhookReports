--
-- PostgreSQL database dump
--

-- Dumped from database version 15.10 (Debian 15.10-1.pgdg120+1)
-- Dumped by pg_dump version 15.10 (Debian 15.10-1.pgdg120+1)

-- Started on 2024-11-24 19:53:38 UTC

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
-- TOC entry 214 (class 1259 OID 16389)
-- Name: test; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.test (
    name character varying(50)
);


ALTER TABLE public.test OWNER TO "user";

--
-- TOC entry 3340 (class 0 OID 16389)
-- Dependencies: 214
-- Data for Name: test; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.test (name) FROM stdin;
daniel
\.


-- Completed on 2024-11-24 19:53:38 UTC

--
-- PostgreSQL database dump complete
--

