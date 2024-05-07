# Virgil Kyo experiment

The point of this experiment is to use Virgil with Kyo.

Set up Cassandra:
```bash
docker compose up -d
```

Log into Cassandra:
```bash
docker compose exec -it cassandra cqlsh
```

```cql
CREATE KEYSPACE IF NOT EXISTS virgil
  WITH REPLICATION = {
    'class': 'SimpleStrategy',
    'replication_factor': 1
};
USE virgil;
```

```cql
CREATE TABLE IF NOT EXISTS example (
  id INT PRIMARY KEY,
  info TEXT
);
```
