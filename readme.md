# Syarat 

- Java 21+
- PostgreSQL
- Maven
- Docker (jika ada)

# Tentang Program Ini

Program ini dibuat menggunakan Spring Boot untuk menyelesaikan *Technical Test* dari PT Pro Sigmaka.

Keamanan program ini menggunakan *OAuth2 Resource Server* dan JWT dengan Algoritma RS256 sehingga memerlukan dua kunci .pem (*private* dan *public key*) untuk memverifikasi JWT dan untuk memberi tanda.

Basis data yang digunakan adalah PostgreSQL dan dokumentasi API disediakan oleh Open API dan Swagger-UI


# Cara Menjalankan

Unduh atau *clone* proyek github ini lalu masuk ke folder utamanya.

Pastikan ada server postgres yang berjalan, jika tidak gunakan docker untuk memulai server postgres.

```
docker run -p 7002:5432 --name=casedb \
-e POSTGRES_USER=user \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=casestudy \
-d postgres:latest
```

jalankan perintah tersebut di terminal (powershell, bash, dll) untuk memulai server postgres menggunakan docker dengan konfigurasi yang sesuai dengan yang ada di *application.properties*

Ketika sudah berada di *root* folder jalankan perintah ini untuk membuat file .jar dari aplikasi.

```
./mvnw clean install
```

Maven akan menjalankan perintah tersebut dan akan ada file .jar baru di folder /target.  

Gunakan perintah ini untuk memulai aplikasinya.

```
java -jar target/caseestudy-0.0.1-SNAPSHOT.jar 
```
Aplikasi spring akan memulai di port 8080. 

Untuk Open API buka http://localhost:8080/api-docs untuk dokumentasi Open API dengan format .json.

Untuk Dokumentasi Swagger buka http://localhost:8080/swagger-ui/index.html

# Konfigurasi

Konfigurasi aplikasi ada di folder /src/main/resources/application.properties .

```properties
spring.datasource.url=jdbc:postgresql://localhost:7002/casestudy
spring.datasource.password=password
spring.datasource.username=user
spring.jpa.hibernate.ddl-auto=create-drop
```
Konfigurasi ini digunakan untuk basis data, ganti sesuai dengan server postgres yang digunakan.

```properties
# Rate Limit Amount
api.rate.limit.amount=100

# Rate Limit Duration in minutes
api.rate.limit.duration=1
```

Konfigurasi ini digunakan untuk *rate limit* ubah sesuai dengan kebutuhan.

```properties
# RS256 Keys
rsa.private-key=classpath:certs/private-key.pem
rsa.public-key=classpath:certs/public-key.pem
```

Ini adalah kunci yang digunakan untuk JWT. Ubah file tersebut untuk keamanan tambahan. Cara pembuatannya adalah sebagai berikut :

```
openssl genpkey -algorithm RSA -out privateKey.pem -pkeyopt rsa_keygen_bits:2048

openssl rsa -pubout -in privateKey.pem -out public_key.pem

openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in privateKey.pem -out private-key.pem
```

Pindahkan file private-key.pem dan public-key.pem ke /src/main/resources/certs . Dan jalankan aplikasi untuk memastikan tidak ada kesalahan.