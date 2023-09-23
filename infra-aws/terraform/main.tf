provider "aws" {
    region = var.region
}

resource "aws_sqs_queue" "my_sqs" {
  count = length(var.queue_names)
  name  = element(var.queue_names, count.index)
}

resource "aws_sns_topic" "my_sns" {
  count = length(var.queue_names)
  name  = "${element(var.queue_names, count.index)}.fifo"
  fifo_topic = true  # Configuração de tópico FIFO
}

resource "aws_sns_topic_subscription" "my_sns_subscription" {
  count    = length(var.queue_names)
  topic_arn = aws_sns_topic.my_sns[count.index].arn
  protocol = "sqs"
  endpoint = aws_sqs_queue.my_sqs[count.index].arn
}

resource "aws_sqs_queue_policy" "my_sqs_policy" {
  count     = length(var.queue_names)
  queue_url = aws_sqs_queue.my_sqs[count.index].url
  policy = jsonencode({
    Version = "2012-10-17",
    Statement = [
      {
        Sid       = "Allow-SNS-SendMessage",
        Effect    = "Allow",
        Principal = {
          Service = "sns.amazonaws.com"
        },
        Action    = "sqs:SendMessage",
        Resource  = aws_sqs_queue.my_sqs[count.index].arn,
        Condition = {
          ArnEquals = {
            "aws:SourceArn" = aws_sns_topic.my_sns[count.index].arn
          }
        }
      }
    ]
  })
}

