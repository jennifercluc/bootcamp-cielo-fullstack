aws --endpoint http://localhost:4566/ sns create-topic --name awstopic

aws --endpoint http://localhost:4566/ sqs create-queue --queue-name awsqueue

aws --endpoint http://localhost:4566/ sns subscribe --topic-arn   arn:aws:sns:us-east-1:000000000000:awstopic --protocol sqs --notification-endpoint arn:aws:sqs:us-east-1:000000000000:awsqueue

aws sns publish --endpoint-url=http://localhost:4566/ --topic-arn arn:aws:sns:us-east-1:000000000000:awstopic --message "Hello World"

aws --endpoint http://localhost:4566/ sqs get-queue-attributes --queue awsqueue --attribute-name All