# Sistema-floricultura

O projeto a ser desenvolvido é um sistema para uma floricultura e seus diversos microsserviços. O projeto será realizado com Spring Cloud.


## Loja 
  Realiza a gestão do pedido:
  * Disponibiliza um catálogo de produtos por Estado;  a floricultura tem um cadastro de fornecedor por região;
  * Realiza o pedido e envia para fornecedor de um Estado;
  * Conversa com uma transportadora para realizar a entrega do produto;
  * Reserva de voucher.

## Fornecedor
  * Gerencia o catálogo, mantendo os produtos disponíveis atualizados e envia para a floricultura;
  * Executa os pedidos e envia uma estimativa para que o produto fique pronto.

## Transportador
  Gestão do voucher:
  * Gera um voucher para origem e destino.
