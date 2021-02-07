## Voucher store

![Java CI with Maven](https://github.com/jkanclerz/pp5-voucher-store-12/workflows/Java%20CI%20with%20Maven/badge.svg)

### Testing


```bash
mvn test
```

### crud testing

```bahs
curl localhost:9999/api/clients -X POST -H "content-type: application/json" -d '{"firstname": "Michał", "lastname": "Zzz", "address": {"street": "rakowicka"}}'
curl localhost:9999/api/clients -X POST -H "content-type: application/json" -d '{"firstname": "Michał", "lastname": "Zzz", "address": {"street": "rakowicka"}}'

curl localhost:9999/api/clients | python -m json.tool
```