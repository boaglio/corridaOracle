create or replace procedure teste_proc
is

cursor todos_ceps is
select *
from cep;

 id_corrida integer;

begin

select corrida.nextval
into id_corrida
from dual;

for linha in todos_ceps loop
 if loweR(linha.cidade) like '%paulo%' then
  INSERT INTO TESTE_PROCEDURE (ID,UF,CIDADE,BAIRRO,ENDERECO_CEP,LOGRADOURO,ID_CORRIDA)
  VALUES (seq1.nextval,linha.uf,linha.cidade,linha.bairro,linha.endereco_cep,linha.logradouro,id_corrida);
 end if;
end loop;

end teste_proc;