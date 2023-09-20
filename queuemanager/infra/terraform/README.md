# Para uso do Terraform

Instalar Terraform (https://developer.hashicorp.com/terraform/downloads)

Criar, na AWS, access key com direitos de criação e destriução de tópicos no SNS e filas no SQS.

Utilizar variáveis de ambiente para informar as access key e secret, como no exemplo:

 export AWS_ACCESS_KEY_ID=ABCDFEGHIJKLMNOPQRST
 export AWS_SECRET_ACCESS_KEY=ABCDFEGHIJKLMNOPQRSTABCDFEGHIJKLMNOPQRST

No arquivo variables.tf ajustar os nomes de tópicos e filas que se deseja, alterando o seguinte trexo:
```
  default     = [
    "ada_cielo_elogio",
    "ada_cielo_critica",
    "ada_cielo_sugestao"
  ]
```
Obs.: O main.tf foi feito de forma a aceitar qualquer quantidade de nomes.

Na linha de comando, ir para o diretório onde está este arquivo e os arquivos main.tf e variables.tf, e executar:

`terraform init`

Que fará, na primeira vez, download referente ao provider em questão, AWS.

`terraform plan -out calculated_plan`

Que mostrará o que será feito.
Obs.: No lugar de "calculated_plan" pode ser qq nome de arquivo local, onde será salvo o plano de execução do terraform.

`terraform apply calculated_plan`

Que aplicará as edições planejadas.

Quando quiser destruir os recursos criados, use:

`terraform destroy`
