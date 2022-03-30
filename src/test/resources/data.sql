INSERT INTO public."user" ("name",gender,created_at,updated_at,email) VALUES
	 ('ya','MALE','2022-03-17 21:39:53.788','2022-03-17 21:39:53.788',NULL),
	 ('ya','MALE','2022-03-17 22:17:01.156','2022-03-17 22:17:01.156',NULL),
	 ('gggaasdf','MALE','2022-03-17 22:25:40.492','2022-03-17 22:25:40.492','gogmow@nam.com'),
	 ('updated','MALE','2022-03-17 22:39:08.443','2022-03-17 22:39:08.561','updated@fast.com');

INSERT INTO public.publisher (id,"name",created_at,updated_at) VALUES
	 (10,'창창출판사','2022-03-16 23:24:59.598873+09',NULL);

INSERT INTO public.author (id,"name",country,created_at,updated_at) VALUES
	 (10,'라캉','한국','2022-03-16 23:24:59.598873+09',NULL);

INSERT INTO public.book ("name",category,publisher_id,created_at,updated_at,deleted,status) VALUES
	 ('초격차','programing',10,'2022-03-16 23:52:23.615','2022-03-16 23:52:23.615',false,200 ),
	 ('제피에이',Null,10,'2022-03-18 16:12:40.206','2022-03-18 16:12:40.206',false,200 ),
	 ('제피에이','program',10,'2022-03-18 16:20:37.892','2022-03-18 16:20:37.892',true,100);
INSERT INTO public.book_author (book_id,author_id) VALUES
	 (3,10);
