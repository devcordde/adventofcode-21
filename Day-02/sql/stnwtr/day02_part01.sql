-- TABLE: commands(command varchar, value int)
select (
  ((select sum(value) from commands WHERE command="down") -
  (select sum(value) from commands where command="up")) *
  (SELECT sum(value) from commands WHERE command="forward")
) as result;
