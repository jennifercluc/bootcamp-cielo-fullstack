# Desafio em grupo do Bootcamp Cielo Dev - AdaTech

Backend desenvolvido em Spring Boot (Maven) implementando
endpoits de API pedidos, para interação com serviços AWS
SNS e SQS.

Frontend desenvolvido em Angular demonstrando a utilização
da API desenvolvida no backend. 

Desenvolvedores (Equipe G6):
- Jennifer Carolinne
- Mateus Fellipe
- Anselmo

## Tecnologias

- Java 17
- Spring Boot
- Maven
- AWS SDK for Java 2
- Angular

## Diretórios

- queue-manager: Implementação do backend
- queue-manager-front:  Implementação do front end
- infra-aws: Arquivos relacionados aos recursos AWS necessários

## Recursos

- criação de tópicos e filas na AWS de acordo com informações
  encontradas em infra-aws/terreform

- criação de access key com
  - direitos necessários
    - sqs:DeleteMessage"
    - sns:Publish
    - sqs:ReceiveMessage
  - sobre tópico e filas criadas acima

Observação: Apenas para fins deste bootcamp, para facilitar,
incluímos credenciais e identificadores de recursos 
diretamente neste repositório.

## Execução

- Frontend
  - A partir do diretório queue-manager-front execute
    - `npm start`
- Backend
  - A partir do diretório queue-manager execute
    - `mvnw spring-boot:start`

## Sobre funcionamento

O feedback buscado na fila SQS fica com o status "Recebido".
A alteração para os status "Em Processamento" e "Finalizado",
simulando um processamento real da mensagem, ocorre em
intervalos de 10 segundos.

## URLs

- Swagger da API (backend):
  - http://localhost:8080/swagger-ui/index.html
- Frontend:
  - http://localhost:4200/
