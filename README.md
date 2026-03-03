# 🐾 Jogo do Bicho – BichoFull

A **BichoFull** é uma aplicação web desenvolvida para simular o tradicional Jogo do Bicho, com fins exclusivamente educacionais.  

O projeto está sendo construído como atividade da disciplina **Laboratório de Produção de Software**, ministrada pelo professor Ronem Lavareda no **IFAM – Campus Parintins**.

> ⚠️ Este sistema tem caráter acadêmico e não possui qualquer vínculo com práticas reais de apostas.

---

## 🚧 Status do Projeto

🚀 **Em desenvolvimento**

Atualmente a aplicação está em fase de implementação e evolução contínua, com melhorias sendo adicionadas gradualmente tanto no front-end quanto no back-end.

---

## 🛠️ Tecnologias Utilizadas

### 🎨 Front-end
- Angular  
- Angular Material

### ⚙️ Back-end
- Java  
- Spring Boot  

### 🗄️ Banco de Dados
- MySQL  

---

## 📌 Arquitetura

A aplicação segue o padrão de **arquitetura em camadas**, separando responsabilidades em:

- Controller  
- Service  
- Repository  
- Model  

Isso garante maior organização, escalabilidade e facilidade de manutenção.

![Diagrama da arquitetura](assets/diagrama.png)

---

## 🔐 Funcionalidades do Back-end (API)

A API REST está sendo desenvolvida com os seguintes endpoints:

### 🔑 Autenticação
- `POST /auth/register` → Cadastro de usuário  
- `POST /auth/login` → Login de usuário  

### 👤 Usuário
- `GET /users/me` → Dados do usuário autenticado  
- `GET /users/me/bets` → Listar apostas do usuário  
- `PATCH /users/me/change_password` → Alterar senha  
- `POST /users/me/deposit` → Realizar depósito de saldo  

### 🐯 Animais
- `GET /animals` → Listar animais disponíveis  

### 🎲 Apostas
- `POST /bets` → Criar nova aposta  
- `GET /bets/{id}/result` → Dados do resultado da aposta  

---

Desenvolvido por **Matheus Nobre** 🐾  
IFAM – Campus Parintins