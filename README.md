### Enrichment of Events is achieved by using JOINS!

### To count items grouped by key:

1) use aggregation functions on stream - save result to other topic

```java
KStream<String, JsonNode> bankTransactions =
builder.stream(Serdes.String(), jsonSerde, "bank-transactions");

        KTable<String, JsonNode> bankBalance = bankTransactions
            .groupByKey(Serdes.String(), jsonSerde)
            .aggregate(
                BankBalanceExactlyOnceApp::initialBalance,
                (aggKey, newValue, aggValue) -> newBalance(newValue, aggValue),
                jsonSerde,
                "bank-balance-agg"
            );

        bankBalance.to(Serdes.String(), jsonSerde, "bank-balance-exactly-once");
```


2) save data into intermediate-topic, read as KTable, aggregate - save result to other topic

```java
// step 1 - process data save as user id as key, colour as value
usersAndColours.to("user-keys-and-colours");

// step 2 - we read that topic as a KTable so that updates are read correctly
KTable<String, String> usersAndColoursTable = builder.table("user-keys-and-colours");

// step 3 - we count the occurrences of colours
KTable<String, Long> favouriteColours = usersAndColoursTable
        // 4 - we group by colour within the KTable
        .groupBy((user, colour) -> new KeyValue<>(colour, colour))
        .count("CountsByColours");

// 5 - we output the results to a Kafka Topic - don't forget the serializers
favouriteColours.to(Serdes.String(), Serdes.Long(),"favourite-colour-output");

```