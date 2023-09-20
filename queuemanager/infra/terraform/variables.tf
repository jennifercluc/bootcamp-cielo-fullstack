variable "region" {
        description = "Region of the resources"
        default = "us-west-2"
}

variable "queue_names" {
  description = "Names of the SQS queues and SNS topics"
  type        = list(string)
  default     = [
    "ada_cielo_elogio",
    "ada_cielo_critica",
    "ada_cielo_sugestao"
  ]
}
