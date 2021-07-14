# Sistema-floricultura

O projeto a ser desenvolvido é um sistema para uma floricultura, com integração de diversos microsserviços. O projeto será realizado com Spring Cloud.

versão 1.0:
* Eureka - Service registry e Discovery    
* Spring Feign e ClientSide Loadbalance  
* Spring Config Server (Git: microservice-repository)  
* Distrubuted Tracing, Papertrail e Spring Sleuth; 

versão 2.0:
* Tratamento de falhas com Hystrix (Circuit breaker e Fallback)  
* Bulkhead com Hystrix  


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
