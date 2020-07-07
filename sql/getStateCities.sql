select
c.BDCODCID,
c.BDCODUF,
c.BDNOMCID
from TCIDADE c where c.BDCODUF = :coduf;
