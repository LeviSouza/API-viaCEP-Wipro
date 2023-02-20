#language: pt
# encoding: utf-8


Funcionalidade: Consulta um endereço por CEP


@FluxoPrincipal
Esquema do Cenario: Consultar endereço por CEP válido

Dado que o usuário informou um CEP válido "25065051"
Quando o usuário solicita a consulta do endereço
Então o sistema deve retornar os dados do endereço com o frete da região

Exemplos:
|     CEP     |          RUA         |
|  25065051   |  Rua Santo Antônio   |
|  76824654   |  Rua Itaúba          |
|  49015260   |  Rua Frei Paulo      |


@FluxoExceção
Esquema do Cenario: Consultar endereço por CEP inválido

Então o sistema deve exibir uma mensagem de erro informando que o CEP é inválido

Exemplos:
|     CEP      |          MENSAGEM         |
|   12345-678  |    "CEP não encontrado!"  |
