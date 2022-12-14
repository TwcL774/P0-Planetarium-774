-- DCL (Data Control Language)
-- creates a new role that is able to login to the db
create role revuser login password 'revpass';

-- grants permission to select
grant select on teams to revuser;

-- removes permission
revoke select on teams from revuser;

-- TCL (Translation Control Language)
-- 

create table teams(
	team_id serial primary key,
	team_name varchar(30)
);

create table players(
	player_id serial primary key,
	player_name varchar(30),
	player_team_id int references teams(team_id) on delete set null
);

insert into teams values (default, 'test1'), (default, 'test2');

begin; -- starts a transaction
	insert into teams values (default, 'test3');
	savepoint test3_added; -- creates a savepoint to use for rolling back
	insert into teams values (default, 'test4');
	rollback to test3_added; -- this roll the transaction back to the indicated savepoint
	release savepoint test3_added; -- delete savepoint
end; -- ends the transcation

insert into players values (default, 'Player1', 1), (default, 'Player2', 1), (default, 'Player3', 2);

select player_name, team_name
from players right join teams
on player_team_id = team_id; -- combine the tables using these columns

select player_name, team_name
from players left join teams
on player_team_id = team_id;