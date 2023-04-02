
## Kafka Consumer to OpenSearch

1) Kafka Consumer polls data (has autocommit disabled)
2) ConsumerRecords converted to IndexRequest(OpenSearch Object) and collected into Bulk
3) Once all ConsumerRecords are processed Bulk is sent to OpenSearch + Kafka offset committed