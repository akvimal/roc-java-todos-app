CREATE TABLE public.todos (
	id serial NOT NULL,
	description varchar NOT NULL,
	due_date date NULL,
	done char(1) NOT NULL DEFAULT N
);