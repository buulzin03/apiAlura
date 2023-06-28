delimiter ;;
DROP PROCEDURE IF EXISTS alterNoFailure;;
create procedure alterNoFailure ()
begin
    declare continue handler for 1060 begin end;
    ALTER TABLE  medicos 
    ADD COLUMN ativo tinyint DEFAULT 0 NOT NULL,ALGORITHM=INPLACE, LOCK=NONE;
end;;
call alterNoFailure();;