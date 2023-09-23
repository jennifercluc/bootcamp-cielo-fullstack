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

- queuemanager: Implementação do backend
- queue-manager-front:  Implementação do front end
- resources/terraform: Arquivos relacionados aos recursos AWS necessários
- resources/scripts: Scripts de carga de dados nas filas

## Recursos

### AWS / Terraform

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

### Carga inicial

Com todo o ambiente preparado, é possível fazer uma carga
inicial de feedback, 10 de cada tipo, executando o script de
carga. 

## URLs

- Swagger da API (backend):
  - http://localhost:8080/swagger-ui/index.html
- Frontend:
  - http://localhost:4200/
- Spring Boot Actuator
  - http://localhost:8080/actuator
