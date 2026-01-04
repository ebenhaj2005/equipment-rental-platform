# equipment-rental-platform
Equipment rental platform for art students - proof of concept

# Equipment Rental Platform - Proof of Concept

Een webapplicatie voor kunststudenten om materiaal (verlichting, kabels, controlepanelen) te reserveren en huren voor projecten en eindwerken.

## Project Beschrijving

Dit is een proof of concept voor een verhuurplatform gebouwd met:
- **Backend**: Java Spring Boot
- **Database**: H2 (in-memory)
- **Authentication**: Session-based met BCrypt password hashing
- **Frontend**: HTML5, CSS3, JavaScript (Vanilla)

## Features

### Core Functionaliteit
- ✅ User registratie en login met veilige wachtwoordopslag (BCrypt)
- ✅ Product catalogus met categorieën (Kabels, Verlichting, Controlepanelen)
- ✅ Winkelmandje met toevoegen/verwijderen van producten
- ✅ Datum selectie voor verhuurperiode
- ✅ Checkout en bevestigingspagina
- ✅ Verhuurgeschiedenis per student

### Technische Details

**Backend API Endpoints:**
- `POST /api/auth/register` - Student registratie
- `POST /api/auth/login` - Inloggen
- `POST /api/auth/logout` - Uitloggen
- `GET /api/auth/me` - Huidige gebruiker
- `GET /api/categories` - Alle categorieën
- `GET /api/products` - Alle producten
- `GET /api/products/category/{id}` - Producten per categorie
- `GET /api/cart` - Winkelwagen ophalen
- `POST /api/cart/add` - Product aan winkelwagen toevoegen
- `DELETE /api/cart/remove/{id}` - Product verwijderen
- `POST /api/rentals/checkout` - Aankoop bevestigen
- `GET /api/rentals/my-rentals` - Verhuurgeschiedenis

**Veiligheid:**
- Wachtwoorden gehasht met BCrypt (geen plaintext in database)
- Session-based authentication
- CORS geconfigureerd voor lokale ontwikkeling
- Input validatie op server-side

## Setup & Installatie

### Vereisten
- Java 21+
- Maven 3.6+

### Project Starten

1. **Repository klonen**
```bash
   git clone https://github.com/ebenhaj2005/equipment-rental-platform.git
   cd equipment-rental-platform
```

2. **Backend starten**
```bash
   mvn clean install
   mvn spring-boot:run
```
Backend draait op `http://localhost:8080`

3. **Database console** (optioneel)
```
   http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:testdb
   - Username: sa
   - Password: (leeg)
```

4. **Frontend openen**
   Open `index.html` in je browser

## Database Schema

### Tabellen
- **users**: Geregistreerde studenten
- **categories**: Productcategorieën
- **products**: Beschikbaar materiaal (10 items)
- **carts**: Winkelwagen per student
- **cart_items**: Items in winkelwagen
- **rentals**: Bevestigde verhuren

### Testdata
Automatisch geladen bij startup:
- 3 categorieën (Kabels, Verlichting, Controlepanelen)
- 10 producten verdeeld over categorieën

## Gebruikte Technologieën

### Backend
- Spring Boot 4.0.1
- Spring Data JPA (Hibernate)
- Spring Security
- H2 Database
- Lombok
- BCrypt (Password Hashing)

### Frontend
- HTML5
- CSS3 (Grid/Flexbox)
- Vanilla JavaScript (fetch API)
- LocalStorage voor sessie

### Build & Deploy
- Maven
- Git

## 📖 Referenties

### Official Documentation

#### Spring Framework
- [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/3.2.1/reference/html/) - Officiële Spring Boot docs
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/6.2/) - Security configuratie
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/) - Database abstractie
- [Building REST Services with Spring](https://spring.io/guides/tutorials/rest/) - REST API tutorial

#### Database
- [H2 Database Engine](https://www.h2database.com/html/main.html) - H2 documentatie
- [Spring Boot with H2 Database](https://www.baeldung.com/spring-boot-h2-database) - Baeldung tutorial
- [JPA Relationships](https://www.baeldung.com/jpa-hibernate-associations) - Entity relaties

#### Security
- [BCrypt Password Encoder](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html) - Password hashing
- [Spring Security CORS](https://spring.io/guides/gs/rest-service-cors/) - CORS configuratie
- [Session Management](https://docs.spring.io/spring-security/reference/servlet/authentication/session-management.html) - Session handling

#### Frontend
- [MDN Web Docs - Fetch API](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch) - HTTP requests
- [MDN - Async/Await](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Statements/async_function) - Asynchrone JavaScript
- [MDN - SessionStorage](https://developer.mozilla.org/en-US/docs/Web/API/Window/sessionStorage) - Browser storage

### Tutorials & Blog Posts

- [RESTful Web Services with Spring Boot](https://www.baeldung.com/rest-with-spring-series) - Baeldung REST series
- [Spring Boot Tutorial](https://www.tutorialspoint.com/spring_boot/index.htm) - TutorialsPoint guide
- [JPA One-to-Many Relationship](https://www.baeldung.com/hibernate-one-to-many) - Hibernate relaties
- [Spring Boot Error Handling](https://www.baeldung.com/exception-handling-for-rest-with-spring) - Exception handling

### Design Patterns

- [Repository Pattern](https://martinfowler.com/eaaCatalog/repository.html) - Martin Fowler
- [DTO Pattern](https://martinfowler.com/eaaCatalog/dataTransferObject.html) - Data Transfer Objects
- [MVC Pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) - Architecture pattern
- [Dependency Injection](https://martinfowler.com/articles/injection.html) - DI principle

### Stack Overflow Referenties

Gebruikte Stack Overflow antwoorden voor specifieke problemen:

- [JPA Circular References with Jackson](https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue) - `@JsonIgnoreProperties` oplossing
- [Spring Boot CORS Configuration](https://stackoverflow.com/questions/35091524/spring-boot-cors-filter-cors-preflight-channel-did-not-succeed) - CORS setup
- [Hibernate LazyInitializationException](https://stackoverflow.com/questions/11746499/solve-failed-to-lazily-initialize-a-collection-of-role) - Lazy loading fix
- [OptimisticLockException](https://stackoverflow.com/questions/7059928/what-is-optimistic-locking-in-jpa) - Transaction management

### Maven Dependencies

Alle gebruikte libraries zijn gedocumenteerd in `pom.xml`:
```xml


    org.springframework.boot
    spring-boot-starter-web
    3.2.1




    org.springframework.boot
    spring-boot-starter-data-jpa




    org.springframework.boot
    spring-boot-starter-security




    com.h2database
    h2
    runtime

```

## 🤖 AI Assistentie

Dit project is ontwikkeld met assistentie van **Claude 3.5 Sonnet** (Anthropic AI)
## Contact

Voor vragen: benhajelias@gmail.com

---

**Opmerking:** Dit is een proof of concept. Voor production gebruik:
