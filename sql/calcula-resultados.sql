  select count(*) , id_corrida
 from teste_procedure
 group by id_corrida

 select min(d)*24*60*60,max(d)*24*60*60,avg(d)*24*60*60,count(0) from (
 select max(alteracao)- min(alteracao) as d , id_corrida
 from teste_procedure
 group by id_corrida
 )

   select count(*)  , id_corrida
  from teste_java_proc
   group by id_corrida

   select min(d)*24*60*60,max(d)*24*60*60,avg(d)*24*60*60,count(0) from (
 select max(alteracao)- min(alteracao) as d , id_corrida
  from teste_java_proc
 group by id_corrida
 )

 select count(*) , id_corrida
 from teste_driver_thin
 group by id_corrida

 select min(d)*24*60*60,max(d)*24*60*60,avg(d)*24*60*60,count(0) from (
 select max(alteracao)- min(alteracao) as d , id_corrida
 from teste_driver_thin
  group by id_corrida
  )

  select count(*)  , id_corrida
  from teste_driver_oci
   group by id_corrida

 select min(d)*24*60*60,max(d)*24*60*60,avg(d)*24*60*60,count(0) from (
 select max(alteracao)- min(alteracao) as d , id_corrida
  from teste_driver_oci
 group by id_corrida
 )
