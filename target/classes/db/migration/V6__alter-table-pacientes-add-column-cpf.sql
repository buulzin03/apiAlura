DROP PROCEDURE IF EXISTS alterNoFailure;;
create procedure alterNoFailure ()
begin
    declare continue handler for 1060 begin end;
    ALTER TABLE  pacientes 
    ADD COLUMN cpf varchar(14) DEFAULT 0 NOT NULL unique,ALGORITHM=INPLACE, LOCK=NONE;
end;;
call alterNoFailure();;