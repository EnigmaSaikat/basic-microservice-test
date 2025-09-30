# Demo Microservices\n\nBuild & test:\n\n- mvn -q -DskipTests=false test\n\nRun services (in separate terminals):\n\n- mvn -pl inventory-service spring-boot:run\n- mvn -pl order-service spring-boot:run\n\nAPIs:\n- GET/POST /api/products, GET/DELETE /api/products/{id}\n- GET/POST /api/orders\n

## Docker

### Build & Run with Docker Compose
From the project root (C:/Users/Saikat/demo-microservices):

```bash
docker compose build
docker compose up
```

- Inventory: http://localhost:8081/api/products
- Orders: http://localhost:8082/api/orders

To stop:
```bash
docker compose down
```

## API Documentation

### inventory-service (port 8081)
- Base URL: http://localhost:8081

1) GET /api/products
   - Description: List all products.
   - Response: 200 OK, JSON array of Product
   - Example:
```bash
curl -s http://localhost:8081/api/products
```

2) GET /api/products/{id}
   - Description: Get product by id.
   - Path params: id (string)
   - Responses: 200 OK with Product; 500 if not found (demo impl throws NoSuchElementException)
   - Example:
```bash
curl -s http://localhost:8081/api/products/123
```

3) POST /api/products
   - Description: Create a product.
   - Body (application/json): {"name":"A","quantity":5}
   - Responses: 201 Created with Product
   - Example:
```bash
curl -s -X POST http://localhost:8081/api/products \
  -H "Content-Type: application/json" \
  -d "{\"name\":\"A\",\"quantity\":5}"
```

4) DELETE /api/products/{id}
   - Description: Delete a product by id.
   - Responses: 204 No Content; 500 if not found (demo impl)
   - Example:
```bash
curl -s -X DELETE http://localhost:8081/api/products/123
```

Model: Product
```json
{"id":"string","name":"string","quantity":0}
```

### order-service (port 8082)
- Base URL: http://localhost:8082

1) GET /api/orders
   - Description: List all orders.
   - Response: 200 OK, JSON array of Order
   - Example:
```bash
curl -s http://localhost:8082/api/orders
```

2) POST /api/orders
   - Description: Create an order.
   - Body (application/json): {"productId":"p1","quantity":2}
   - Responses: 201 Created with Order
   - Example:
```bash
curl -s -X POST http://localhost:8082/api/orders \
  -H "Content-Type: application/json" \
  -d "{\"productId\":\"p1\",\"quantity\":2}"
```

Model: Order
```json
{"id":"string","productId":"string","quantity":1}
```

## Run on local machine (without Docker)
From project root:
```bash
mvn -q -DskipTests=false test
# Terminal 1
mvn -pl inventory-service spring-boot:run
# Terminal 2
mvn -pl order-service spring-boot:run
```

If -pl fails, run inside modules:
```bash
cd inventory-service && mvn spring-boot:run
# in another terminal
cd order-service && mvn spring-boot:run
```

## Run with Docker on any server or local machine
Prereq: Docker Engine + Compose plugin (or docker-compose).
From project root:
```bash
# Newer Compose
docker compose build
docker compose up -d
# Older Docker
docker-compose build
docker-compose up -d
```
Verify:
- http://localhost:8081/api/products
- http://localhost:8082/api/orders
