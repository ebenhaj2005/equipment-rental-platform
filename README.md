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


## Contact

Voor vragen: benhajelias@gmail.com

---

**Opmerking:** Dit is een proof of concept. Voor production gebruik:
