##For Register

```json

{
  "firstName": "Munnu",
  "lastName": "Singh",
  "email": "packager@example.com",
  "password": "12345",
  "roles": [
    {
      "roleName": "PACKAGER"
    }
//  , multiple role
//    {
//      "roleName": "SHIPPER"
//    }
  ],
  "address": {
    "addressType": "SHIPPING",
    "addressLine1": "123 Main St",
    "addressLine2": "Apt 4B",
    "city": "Springfield",
    "zipCode": 12345,
    "state": "IL",
    "country": "USA"
  }
}
```

##Output
```json
{
"jwtToken": "
eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1BBQ0tBR0VSIl0sInN1YiI6InBhY2thZ2VyQGV4YW1wbGUuY29tIiwiaWF0IjoxNzI0NjU3NjgwLCJleHAiOjE3MjQ2NjEyODB9.MKMbuz6JtXUXZwELaq4PmtZGwIB3a4mS4N6-wAKeuNM"
}
```

###Authenticate
```json
{
    "email" : "packager@example.com",
    "password" : "12345"
}
```
###Output
```json
{
    "jwtToken": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJBRE1JTiJdLCJzdWIiOiJtdXNrYW5AZXhhbXBsZS5jb20iLCJpYXQiOjE3MjQ2MTA5MTQsImV4cCI6MTcyNDYxNDUxNH0.FzVdtDzaEzhrGjBU66i4PqBRX8_pj05P9hBDG6qtpTk"
}
```

###api each of them are individual and can only be accessed by the respective roles
Prefer SecurityConfig.java for more details in Security package
```json
http://localhost:8080/api/v1/auth/authenticate
http://localhost:8080/api/v1/auth/register
http://localhost:8080/api/v1/packager/test
http://localhost:8080/api/v1/admin/test
http://localhost:8080/api/v1/dispatcher/test
http://localhost:8080/api/v1/storemanager/test
http://localhost:8080/api/v1/stockmanager/test
```