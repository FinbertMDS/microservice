## Các khái niệm liên quan đến Microservice
1. [Service Registration and Discovery - Eureka](https://spring.io/guides/gs/service-registration-and-discovery/)
2. [Client Side Load Balancing with Ribbon and Spring Cloud](https://spring.io/guides/gs/client-side-load-balancing/)
  Hoặc sử dụng cách mới hơn [Client-Side Load-Balancing with Spring Cloud LoadBalancer](https://spring.io/guides/gs/spring-cloud-loadbalancer/)
3. [Circuit Breaker - Hystrix](https://spring.io/guides/gs/circuit-breaker/)
4. [Routing and Filtering - Zuul](https://spring.io/guides/gs/routing-and-filtering/)

## Mở rộng để hiểu về Project
1. Cách các service gọi nhau:

Ví dụ: Trong service Order có sử dụng `CustomerClient` và `CatalogClient` để gọi tới các server tương ứng Customer và Catalog.

Minh họa thông qua CustomerClient:

- [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)

Trong service Catalog có class `CustomerRepository`.
```java
@RepositoryRestResource(collectionResourceRel = "customer", path = "customer")
public interface CustomerRepository extends
		PagingAndSortingRepository<Customer, Long> {
}
```
`RepositoryRestResource` sẽ mặc định tạo ra các Rest API để thao tác với Domain Class tương ứng là `Customer` như là GET, POST, PUT, DELETE.

- [Consuming a RESTful Web Service](https://spring.io/guides/gs/consuming-rest/)

Sử dụng RestTemplate để gọi Service thông qua URL được lấy thông qua hàm `catalogURL()`. URL của service thì được lấy từ Eureka và Ribbon.