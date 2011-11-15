create or replace view cep as
select uf.uf_descricao uf,
       cidade.cidade_descricao cidade,
       bairro.bairro_descricao bairro,
       endereco.endereco_cep ,
       endereco.endereco_logradouro logradouro
  from uf,
       cidade,
       bairro,
       endereco
 where uf.uf_codigo = cidade.uf_codigo
       and cidade.cidade_codigo = bairro.cidade_codigo
       and bairro.bairro_codigo = endereco.bairro_codigo
 order by 1,2,3
