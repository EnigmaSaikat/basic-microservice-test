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
