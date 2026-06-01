# Hotel System 🏨

Sistema de reservas de hotel desenvolvido em Java, praticando **Programação Orientada a Objetos (POO)** e **integração com banco de dados** usando JDBC e MySQL.

---

## 📌 Funcionalidades

**Gerenciamento de Quartos**
- Criar quartos com ID, tipo, preço e status
- Ocupar e liberar quartos
- Verificar disponibilidade

**Gerenciamento de Hóspedes**
- Armazenar informações do hóspede (nome, CPF, celular, e-mail)
- Exibir detalhes do hóspede

**Gerenciamento de Reservas**
- Criar reservas vinculando um hóspede e um quarto
- Gerenciar datas de entrada e saída com `LocalDate`
- Calcular o valor total da estadia com base no número de dias × preço do quarto
- Confirmar e cancelar reservas (atualiza o status do quarto)
- Exibir detalhes completos da reserva

**Integração com Banco de Dados**
- Persistir todos os dados em um banco MySQL via JDBC
- Padrão DAO para `Guest`, `Room` e `Reservation`
- Credenciais gerenciadas com segurança via `db.properties`

---

## 🛠️ Tecnologias

- Java 17+
- MySQL 8+
- JDBC (mysql-connector-java 8.0.33)
- Maven
- `java.time.LocalDate` e `ChronoUnit` para manipulação de datas
- Princípios de Programação Orientada a Objetos

---

## 📂 Estrutura do Projeto

```
com.kauabiscotto.HotelSystem/
│
├── Main.java
├── Guest.java
├── Room.java
├── Reservation.java
│
├── factory/
│   └── ConnectionFactory.java
│
└── dao/
    ├── GuestDAO.java
    ├── RoomDAO.java
    └── ReservationDAO.java
```

---

## 🗄️ Configuração do Banco de Dados

Execute o seguinte script SQL no MySQL Workbench (com o seu banco de dados selecionado):

```sql
CREATE TABLE rooms (
    id INT PRIMARY KEY,
    type VARCHAR(50),
    price DOUBLE,
    status VARCHAR(20)
);

CREATE TABLE guests (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    cpf VARCHAR(14),
    cellphone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE reservations (
    id INT PRIMARY KEY AUTO_INCREMENT,
    guest_id INT,
    room_id INT,
    entry_date DATE,
    departure_date DATE,
    total_value DOUBLE,
    FOREIGN KEY (guest_id) REFERENCES guests(id),
    FOREIGN KEY (room_id) REFERENCES rooms(id)
);
```

---

## ⚙️ Configuração das Credenciais

Crie o arquivo `src/main/resources/db.properties` com as credenciais do seu banco:

```properties
db.url=jdbc:mysql://localhost:3306/seu_banco
db.user=seu_usuario
db.password=sua_senha
```

> ⚠️ Adicione `src/main/resources/db.properties` ao seu `.gitignore` para evitar expor suas credenciais.

---

## ▶️ Como Executar

1. Clone este repositório:
```bash
git clone https://github.com/kaua5702/HotelSystem.git
```

2. Configure o banco de dados e o `db.properties` conforme descrito acima.

3. Execute o projeto pela sua IDE (recomendado IntelliJ IDEA) ou via Maven:
```bash
mvn compile exec:java -Dexec.mainClass="com.kauabiscotto.HotelSystem.Main"
```

---

## 📊 Exemplo de Saída

```
Guest salvo! ID gerado: 1
Room salvo! ID: 101
Reservation salva! ID gerado: 1
=== Reservation Details ===
Guest: John Doe
CPF: 123.456.789-00
Cellphone: 999999999
Email: john@email.com
Room ID: 101
Room Type: Suite
Room Status: available
Entry Date: 2026-04-22
Departure Date: 2026-04-27
Total Value: R$1000.0
===========================
```
