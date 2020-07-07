select
t.BDCODPROD, -- codigo produto
t.BDCODTIPOSER, -- tipo servico
t.BDDESCPROD, -- NOME PRODUTO
t.BDCODSAIDA, -- CFOP
t.BDCODCSTPISENT, --cst pis entrada
t.BDCODCSTCOFENT, --cst cofins entrada
t.BDCODCSTPISSAI, --cst pis saida
t.BDCODCSTCOFSAI, --cst cofins saida
t.BDALIQCOFENT,   --aliquota cofins entrada
t.BDALIQPISENT,   --aliquota pis entrada
t.BDALIQICMS,     --aliquota icms
t.BDALIQICMSST,    --aliquota icms st
t.*
from TCADPRO_VINC t
where
t.BDCODEMP = :enterpriseCode --:enterprise --empresa
and t.BDCODTIPOSER = :typeServiceCode --:serviceType -- tipo de serviço
