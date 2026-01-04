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
   git clone https://github.com/jouw-username/equipment-rental-platform.git
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
   Open `index.html` in je browser (of serve via een local server)

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

## Projectstructuur
```
src/main/java/com/rentalplatform/equipmentrental/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── ProductController.java
│   ├── CartController.java
│   └── RentalController.java
├── service/
│   ├── UserService.java
│   ├── ProductService.java
│   ├── CartService.java
│   └── RentalService.java
├── repository/
│   ├── UserRepository.java
│   ├── ProductRepository.java
│   ├── CartRepository.java
│   └── RentalRepository.java
├── model/
│   ├── User.java
│   ├── Product.java
│   ├── Category.java
│   ├── Cart.java
│   ├── CartItem.java
│   └── Rental.java
└── dto/
    ├── UserDTO.java
    ├── ProductDTO.java
    └── ...

src/main/resources/
├── application.properties
└── import.sql
```

## Testen

### Handmatig testen
1. Registreer een student: `POST /api/auth/register`
2. Login: `POST /api/auth/login`
3. Bekijk producten: `GET /api/products`
4. Voeg product toe aan cart: `POST /api/cart/add`
5. Checkout: `POST /api/rentals/checkout`

### Test Credentials
```
Email: student@example.com
Wachtwoord: test123
```

## Toekomstige Verbeteringen

- [ ] JWT tokens voor stateless authentication
- [ ] React/Vue frontend
- [ ] Email notificaties
- [ ] Geavanceerde filters (prijs, beschikbaarheid)
- [ ] Beheerders dashboard
- [ ] Meerdere database support (PostgreSQL, MySQL)
- [ ] Docker containerization
- [ ] Unit tests

## Licentie

MIT

## Contact

Voor vragen: [jouw-email@example.com]

---

**Opmerking:** Dit is een proof of concept. Voor production gebruik:
- Migreer naar persistent database (PostgreSQL/MySQL)
- Implementeer JWT tokens
- Voeg unit/integration tests toe
- Beveilig endpoints met role-based access control