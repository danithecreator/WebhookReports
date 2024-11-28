--
-- PostgreSQL database dump
--

-- Dumped from database version 15.10 (Debian 15.10-1.pgdg120+1)
-- Dumped by pg_dump version 15.10 (Debian 15.10-1.pgdg120+1)

-- Started on 2024-11-27 06:02:05 UTC

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
-- TOC entry 215 (class 1259 OID 16581)
-- Name: commit; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.commit (
    commit_id character varying(100) NOT NULL,
    commit_date timestamp without time zone,
    commit_url character varying(300),
    commit_committer character varying(100),
    fk_repository_id character varying(100) NOT NULL
);


ALTER TABLE public.commit OWNER TO "user";

--
-- TOC entry 217 (class 1259 OID 16656)
-- Name: pipeline; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.pipeline (
    pipeline_id character varying(50) NOT NULL,
    pipeline_url character varying(300),
    pipeline_status character varying(20),
    pipeline_date timestamp without time zone,
    fk_repository_id character varying(100) NOT NULL
);


ALTER TABLE public.pipeline OWNER TO "user";

--
-- TOC entry 216 (class 1259 OID 16593)
-- Name: pullrequest; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.pullrequest (
    pullrequest_id character varying(50) NOT NULL,
    pullrequest_url character varying(300),
    pullrequest_closed_at timestamp without time zone,
    fk_repository_id character varying(100) NOT NULL
);


ALTER TABLE public.pullrequest OWNER TO "user";

--
-- TOC entry 214 (class 1259 OID 16576)
-- Name: repository; Type: TABLE; Schema: public; Owner: user
--

CREATE TABLE public.repository (
    repository_id character varying(100) NOT NULL,
    repository_name character varying(50),
    repository_url character varying(300)
);


ALTER TABLE public.repository OWNER TO "user";

--
-- TOC entry 3367 (class 0 OID 16581)
-- Dependencies: 215
-- Data for Name: commit; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.commit (commit_id, commit_date, commit_url, commit_committer, fk_repository_id) FROM stdin;
c33bf7581fa659a7e213cb05b3d804e7f17b0eba	2024-11-24 02:41:02	https://norris.com/	Kevin Nguyen	f2d3b451-gde8-4b7d-8d6c-b549333a859f
5000914dc39ad877c7445ae30955e0eff2302dbb	2024-11-24 08:15:04	http://www.grant-garcia.com/	Victor Avila	d1c3a352-hde9-4f8c-9f5e-b549333a279f
a87c25c5f7a21d01340cc8032517163f628d1d64	2024-11-24 08:47:04	https://www.barr.biz/	Christopher Wheeler	d1c3a352-hde9-4f8c-9f5e-b549333a279f
452ea99421c3dd33533cada6ce721afcda0796a6	2024-11-24 07:45:09	http://www.neal.org/	Peter Preston	c2f3b459-ede6-4d8b-8e7d-b549333ac39f
886fc8c7ade6726f3a320b32b6857eb6495d2596	2024-11-25 04:23:09	https://www.braun.com/	Veronica Bell	c2f3b459-ede6-4d8b-8e7d-b549333ac39f
d0f8eebf5a5439e5d0f32eb26ed07a6c2c6b0d66	2024-11-23 00:00:09	https://www.brown.com/	Sierra Carr	c2f3b459-ede6-4d8b-8e7d-b549333ac39f
5df2b1b5e2d2b04a5c94e66f300bc28774b86028	2024-11-24 03:34:09	https://harper.com/	Melissa Walker	c2f3b459-ede6-4d8b-8e7d-b549333ac39f
e86d9c51235a571b4dbac68e0e67bb777a9d77d1	2024-11-24 00:51:09	https://schmitt-jones.biz/	William Fisher	c2f3b459-ede6-4d8b-8e7d-b549333ac39f
3f5a17b45313a29a7779abafa4d1f51279c0c983	2024-11-23 04:05:10	https://brown.com/	Jonathan Huynh	d1c3a352-hde9-4f8c-9f5e-b549333a279f
ec52218ff0d19bbdc84a50422683a25ae8356bb1	2024-11-23 04:49:10	https://www.peterson-smith.com/	Nicole Palmer	d1c3a352-hde9-4f8c-9f5e-b549333a279f
75caa68a93ee8482764baf97b2c8aa43ca4e535f	2024-11-23 08:26:10	http://www.smith.com/	Sharon Barker	d1c3a352-hde9-4f8c-9f5e-b549333a279f
5cfad734b810a3abf30dbca70538f0372314cb6a	2024-11-23 02:24:10	http://acosta.com/	Dana Evans	d1c3a352-hde9-4f8c-9f5e-b549333a279f
3b6d407aa85cfa25882e70553ce404a9081daea1	2024-11-23 07:43:10	https://www.griffin-robbins.org/	Scott Mcintyre	d1c3a352-hde9-4f8c-9f5e-b549333a279f
675b481b7db05f2415b19094c81972d37aac577a	2024-11-23 22:37:02	http://white-santos.com/	Kevin Nguyen	f2d3b451-gde8-4b7d-8d6c-b549333a859f
61b032b7566787c28cc6154fcad12542e1b20540	2024-11-24 00:48:04	https://www.johnston-sparks.com/	Victor Avila	d1c3a352-hde9-4f8c-9f5e-b549333a279f
35d2ed3923e50159ed1a6f51cb03ad563f86522d	2024-11-23 08:34:04	http://www.pierce.com/	Victor Avila	d1c3a352-hde9-4f8c-9f5e-b549333a279f
da7e7aed3317cbd1953125636038fda2a862a80b	2024-11-25 05:28:04	https://baxter.net/	Victor Avila	d1c3a352-hde9-4f8c-9f5e-b549333a279f
db5272be05c2d68246dcd18d3f120ba52f85b1cf	2024-11-23 02:13:07	https://richardson.info/	Christopher Wheeler	d2e4a557-ade3-4b9c-9e8d-b549333ae17f
de116ff9e0883b044e2cd7ef0efb83f67db9a999	2024-11-26 08:53:04	http://thompson-wolf.com/	Victor Avila	d1c3a352-hde9-4f8c-9f5e-b549333a279f
5552e7b939eb96e6e90473b79a045f106a2ac9a2	2024-11-26 04:50:04	http://www.richards.org/	Kevin Nguyen	d1c3a352-hde9-4f8c-9f5e-b549333a279f
\.


--
-- TOC entry 3369 (class 0 OID 16656)
-- Dependencies: 217
-- Data for Name: pipeline; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.pipeline (pipeline_id, pipeline_url, pipeline_status, pipeline_date, fk_repository_id) FROM stdin;
9815297	http://www.mitchell.com/	succeeded	2021-12-30 04:46:59	33d3b559-dde4-4e8e-b8c9-b549333be59f
2482466	https://www.alvarez.com/	succeeded	2024-08-16 21:00:07	a4e3a353-jde1-4c9a-9e4c-b549333a939f
9186970	https://perez.info/	failed	2024-08-12 20:24:41	e3f3b454-ide0-4d7b-8e5d-b549333a149f
8370392	http://www.hall.com/	failed	2023-06-26 23:11:46	d1c3a352-hde9-4f8c-9f5e-b549333a279f
7235040	https://www.sanchez-mckinney.com/	succeeded	2022-04-01 01:16:44	d1c3a352-hde9-4f8c-9f5e-b549333a279f
3961086	http://www.ray.net/	succeeded	2024-02-20 21:52:23	a1f3b458-cce2-4e7b-8d7c-b549333bf58f
2617572	http://simpson.biz/	failed	2022-07-06 19:58:45	33d3b559-dde4-4e8e-b8c9-b549333be59f
5118047	https://stevens.biz/	succeeded	2024-10-22 06:23:39	33d3b559-dde4-4e8e-b8c9-b549333be59f
2398418	http://www.howell.com/	succeeded	2023-11-16 04:19:33	e3f3b454-ide0-4d7b-8e5d-b549333a149f
476316	http://www.smith.net/	succeeded	2023-06-08 22:50:44	33d3b559-dde4-4e8e-b8c9-b549333be59f
9382590	https://buchanan.com/	succeeded	2021-12-24 05:33:35	f2d3b451-gde8-4b7d-8d6c-b549333a859f
9442557	http://www.young-marshall.com/	failed	2022-05-24 00:23:22	a4e3a353-jde1-4c9a-9e4c-b549333a939f
9062407	https://www.knight.org/	failed	2022-03-10 20:21:08	a4e3a353-jde1-4c9a-9e4c-b549333a939f
5616241	http://gonzalez.com/	failed	2023-09-30 22:14:42	c2f3b459-ede6-4d8b-8e7d-b549333ac39f
9039370	https://www.nichols.net/	failed	2024-01-24 21:58:34	a1f3b458-cce2-4e7b-8d7c-b549333bf58f
6698383	https://warren.com/	failed	2024-08-08 22:37:34	e3f3b454-ide0-4d7b-8e5d-b549333a149f
2555936	http://johnson-goodman.com/	succeeded	2022-10-03 22:01:17	b3e3a457-fde7-4c9a-9f6e-b549333aa47f
749602	https://www.miller.com/	failed	2023-11-21 04:34:41	f2d3b451-gde8-4b7d-8d6c-b549333a859f
6895161	https://www.evans.biz/	succeeded	2022-10-20 05:43:25	a1f3b458-cce2-4e7b-8d7c-b549333bf58f
1855415	https://espinoza.net/	failed	2022-12-04 21:39:17	a1f3b458-cce2-4e7b-8d7c-b549333bf58f
976269	https://koch.com/	succeeded	2024-02-23 22:14:58	e3f3b454-ide0-4d7b-8e5d-b549333a149f
737948	https://www.mcdowell-miller.com/	succeeded	2022-09-18 01:01:52	e3f3b454-ide0-4d7b-8e5d-b549333a149f
8056238	http://harper.org/	failed	2022-11-26 02:44:47	f2d3b451-gde8-4b7d-8d6c-b549333a859f
6855720	https://cole.com/	failed	2022-01-08 21:13:02	b3e3a457-fde7-4c9a-9f6e-b549333aa47f
9825935	http://www.hicks.net/	failed	2022-05-28 22:57:24	f2d3b451-gde8-4b7d-8d6c-b549333a859f
6198214	http://www.blevins.com/	failed	2023-03-25 08:42:15	d2e4a557-ade3-4b9c-9e8d-b549333ae17f
3481300	https://www.powell.com/	failed	2024-02-07 05:03:30	b3e3a457-fde7-4c9a-9f6e-b549333aa47f
7510179	http://hoffman-petersen.info/	failed	2023-10-24 04:13:30	c2f3b459-ede6-4d8b-8e7d-b549333ac39f
7972143	http://mitchell.org/	failed	2022-03-24 03:13:06	e4c3a356-dce5-4f9c-9f9e-b549333ad98f
3534830	https://www.kramer.net/	failed	2024-04-24 02:10:11	e3f3b454-ide0-4d7b-8e5d-b549333a149f
140828	http://todd-horn.com/	succeeded	2022-01-19 03:09:08	f2d3b451-gde8-4b7d-8d6c-b549333a859f
4417553	http://www.gilbert.com/	succeeded	2023-10-26 21:04:34	b3e3a457-fde7-4c9a-9f6e-b549333aa47f
7667068	https://hill.com/	succeeded	2024-02-03 21:39:40	f2d3b451-gde8-4b7d-8d6c-b549333a859f
3327561	http://collins-hayes.com/	failed	2022-07-10 08:38:59	f2d3b451-gde8-4b7d-8d6c-b549333a859f
7386829	https://pierce-mcgee.net/	succeeded	2024-02-09 00:04:36	b3e3a457-fde7-4c9a-9f6e-b549333aa47f
5390611	http://www.miller.com/	succeeded	2024-06-18 05:18:30	a1f3b458-cce2-4e7b-8d7c-b549333bf58f
4158766	http://russell.com/	failed	2023-01-31 05:31:35	d1c3a352-hde9-4f8c-9f5e-b549333a279f
2695222	http://www.johnson.biz/	failed	2024-01-31 01:09:12	e3f3b454-ide0-4d7b-8e5d-b549333a149f
2335601	https://ford-james.com/	succeeded	2023-05-03 04:43:37	e4c3a356-dce5-4f9c-9f9e-b549333ad98f
5822468	http://www.james-johnson.com/	failed	2023-05-12 01:38:57	a4e3a353-jde1-4c9a-9e4c-b549333a939f
6462014	http://young.org/	failed	2024-06-05 04:46:10	a4e3a353-jde1-4c9a-9e4c-b549333a939f
8319064	https://www.parsons.com/	succeeded	2022-09-28 06:57:11	d2e4a557-ade3-4b9c-9e8d-b549333ae17f
3806665	http://www.sharp.com/	failed	2024-05-11 03:06:31	e3f3b454-ide0-4d7b-8e5d-b549333a149f
5242588	http://castro.info/	failed	2024-09-20 06:53:08	b3e3a457-fde7-4c9a-9f6e-b549333aa47f
2840049	https://bridges.org/	succeeded	2024-10-16 21:06:07	d2e4a557-ade3-4b9c-9e8d-b549333ae17f
1962386	https://www.garner-williams.biz/	succeeded	2022-10-05 02:13:03	d1c3a352-hde9-4f8c-9f5e-b549333a279f
3395480	https://garza.com/	failed	2022-03-31 08:03:42	f2d3b451-gde8-4b7d-8d6c-b549333a859f
7027432	https://ho.com/	failed	2024-05-10 03:43:33	d2e4a557-ade3-4b9c-9e8d-b549333ae17f
46124	http://warren.info/	succeeded	2024-08-18 02:30:26	e4c3a356-dce5-4f9c-9f9e-b549333ad98f
3983120	http://www.matthews.com/	succeeded	2023-10-29 22:02:34	33d3b559-dde4-4e8e-b8c9-b549333be59f
3666287	http://marshall-williams.com/	failed	2021-12-16 00:50:27	b3e3a457-fde7-4c9a-9f6e-b549333aa47f
8411117	https://ellis.com/	succeeded	2024-04-04 21:50:33	b3e3a457-fde7-4c9a-9f6e-b549333aa47f
1096871	https://www.young.biz/	succeeded	2023-03-18 03:18:15	b3e3a457-fde7-4c9a-9f6e-b549333aa47f
8571679	http://lewis.com/	failed	2023-05-09 05:13:52	f2d3b451-gde8-4b7d-8d6c-b549333a859f
4408438	https://romero-patel.com/	failed	2023-12-06 00:51:18	f2d3b451-gde8-4b7d-8d6c-b549333a859f
9404896	http://www.crawford.com/	succeeded	2024-02-15 02:09:57	33d3b559-dde4-4e8e-b8c9-b549333be59f
6870184	https://www.butler.com/	failed	2023-02-06 19:22:23	d1c3a352-hde9-4f8c-9f5e-b549333a279f
8115245	https://www.butler-baker.com/	succeeded	2023-02-08 02:05:12	d2e4a557-ade3-4b9c-9e8d-b549333ae17f
839589	http://www.stewart-martin.net/	failed	2024-10-08 02:02:46	a4e3a353-jde1-4c9a-9e4c-b549333a939f
\.


--
-- TOC entry 3368 (class 0 OID 16593)
-- Dependencies: 216
-- Data for Name: pullrequest; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.pullrequest (pullrequest_id, pullrequest_url, pullrequest_closed_at, fk_repository_id) FROM stdin;
689875	https://gutierrez.com/	2024-11-26 08:58:05	e4c3a356-dce5-4f9c-9f9e-b549333ad98f
861351	http://torres.com/	2024-11-26 08:58:06	33d3b559-dde4-4e8e-b8c9-b549333be59f
546920	http://www.hardin.net/	2024-11-26 08:58:08	a4e3a353-jde1-4c9a-9e4c-b549333a939f
666487	http://jones-martinez.org/	2024-11-26 08:58:11	e3f3b454-ide0-4d7b-8e5d-b549333a149f
\.


--
-- TOC entry 3366 (class 0 OID 16576)
-- Dependencies: 214
-- Data for Name: repository; Type: TABLE DATA; Schema: public; Owner: user
--

COPY public.repository (repository_id, repository_name, repository_url) FROM stdin;
d1c3a352-hde9-4f8c-9f5e-b549333a279f	AZ-INSIDE-QUESTION	http://anderson-petersen.com/
33d3b559-dde4-4e8e-b8c9-b549333be59f	AZ-COMMUNITY-OUT	https://www.fox.com/
a1f3b458-cce2-4e7b-8d7c-b549333bf58f	AZ-GIVE-PAGE	https://henry.com/
d2e4a557-ade3-4b9c-9e8d-b549333ae17f	AZ-REPRESENT-UP	http://www.wright.com/
f2d3b451-gde8-4b7d-8d6c-b549333a859f	AZ-HOWEVER-INCREASE	https://nelson.com/
c2f3b459-ede6-4d8b-8e7d-b549333ac39f	AZ-FACT-LEFT	https://www.mcguire.org/
b3e3a457-fde7-4c9a-9f6e-b549333aa47f	AZ-FUTURE-ORGANIZATION	https://www.schmidt.biz/
e4c3a356-dce5-4f9c-9f9e-b549333ad98f	AZ-BUSINESS-IMPORTANT	https://white.org/
e3f3b454-ide0-4d7b-8e5d-b549333a149f	AZ-FIRE-TECHNOLOGY	https://stewart-barrett.com/
a4e3a353-jde1-4c9a-9e4c-b549333a939f	AZ-RETURN-LEG	https://willis-moreno.org/
\.


--
-- TOC entry 3213 (class 2606 OID 16587)
-- Name: commit commit_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.commit
    ADD CONSTRAINT commit_pkey PRIMARY KEY (commit_id);


--
-- TOC entry 3220 (class 2606 OID 16660)
-- Name: pipeline pipeline_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.pipeline
    ADD CONSTRAINT pipeline_pkey PRIMARY KEY (pipeline_id);


--
-- TOC entry 3217 (class 2606 OID 16597)
-- Name: pullrequest pullrequest_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.pullrequest
    ADD CONSTRAINT pullrequest_pkey PRIMARY KEY (pullrequest_id);


--
-- TOC entry 3211 (class 2606 OID 16580)
-- Name: repository repository_pkey; Type: CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.repository
    ADD CONSTRAINT repository_pkey PRIMARY KEY (repository_id);


--
-- TOC entry 3214 (class 1259 OID 16667)
-- Name: index_commit_date_committer; Type: INDEX; Schema: public; Owner: user
--

CREATE INDEX index_commit_date_committer ON public.commit USING btree (commit_date, commit_committer);


--
-- TOC entry 3218 (class 1259 OID 24865)
-- Name: index_pipeline_date_status; Type: INDEX; Schema: public; Owner: user
--

CREATE INDEX index_pipeline_date_status ON public.pipeline USING btree (pipeline_date, pipeline_status);


--
-- TOC entry 3215 (class 1259 OID 24859)
-- Name: index_pullrequest_date; Type: INDEX; Schema: public; Owner: user
--

CREATE INDEX index_pullrequest_date ON public.pullrequest USING btree (pullrequest_closed_at);


--
-- TOC entry 3221 (class 2606 OID 16588)
-- Name: commit fk_repository_id; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.commit
    ADD CONSTRAINT fk_repository_id FOREIGN KEY (fk_repository_id) REFERENCES public.repository(repository_id);


--
-- TOC entry 3222 (class 2606 OID 16598)
-- Name: pullrequest fk_repository_id; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.pullrequest
    ADD CONSTRAINT fk_repository_id FOREIGN KEY (fk_repository_id) REFERENCES public.repository(repository_id);


--
-- TOC entry 3223 (class 2606 OID 16661)
-- Name: pipeline fk_repository_id; Type: FK CONSTRAINT; Schema: public; Owner: user
--

ALTER TABLE ONLY public.pipeline
    ADD CONSTRAINT fk_repository_id FOREIGN KEY (fk_repository_id) REFERENCES public.repository(repository_id);


-- Completed on 2024-11-27 06:02:05 UTC

--
-- PostgreSQL database dump complete
--

