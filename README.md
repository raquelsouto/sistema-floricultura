# Sistema-floricultura

O projeto a ser desenvolvido é um sistema para uma floricultura e seus diversos microsserviços. O projeto será realizado com Spring Cloud.

* Uma floriculta disponibiliza um catálogo de produtos por Estado;
* Para cada Estado, a floricultura tem um cadastro de fornecedor por região;
* Cada fornecedor tem um catálogo com os produtos disponíveis. E eles enviam o catálogo para a floricultura;
* Quando um cliente realiza um pedido, a floricultura envia o pedido realizado para o fornecedor daquele Estado;
* O fornecedor envia uma estimativa para que o produto fique pronto;
* A floriculta conversa com uma transportadora para realizar a entrega do produto, gerando um voucher.
