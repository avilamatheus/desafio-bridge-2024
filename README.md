<h1 align="center" style="font-weight: bold;">Desafio Bridge 2024 - Desenvolvimento Web</h1>

<p align="center">
  <a href="#tech">Tecnologias</a> • 
  <a href="#back">Back-End: Rotas & Testes Unitários</a> •
  <a href="#front">Front-End: Imagens</a> •
  <a href="#local">Como executar localmente</a> •
  <a href="#aws">Deploy na AWS</a>
</p>

<p align="center">
    <b>Aplicação Web que recebe como entrada um número inteiro positivo k e retorna a quantidade de números primos positivos menores que k.</b>
</p>

<h2 id="tech">Tecnologias Utilizadas</h2>

**Front-End**
- O Front-End foi desenvolvido a utilização da ferramenta **Vite**, a biblioteca **React** 
para criar as interfaces e o framework **Bootstrap**
para estilização da página.

**Back-End**
- O Back-End foi desenvolvido com a utilização do gerenciador de dependências **Maven**, a linguagem de programação **Java** e o framework **Spring Boot**.

**Docker e Docker Compose**
- Para facilitar a execução do projeto, foi utilizado **Docker** para criar containers com as dependências necessárias para a execução do projeto, onde o Front-End e o Back-End estão em containers separados e a imagem de ambos são criadas a partir de um arquivo **Dockerfile** em suas respectivas pastas. Além disso, foi utilizado o **Docker Compose** que utiliza do arquivo **docker-compose.yml** para facilitar a execução de ambos os containers.

**AWS**
- O projeto foi hospedado na **AWS**, utilizando uma instância **EC2** para hospedar o Front-End e o Back-End. Dentro do EC2 foi utilizado o **Docker** para criar os containers e o **Docker Compose** para facilitar a execução dos containers. As imagens dos containers foram hospedadas no **Docker Hub** para facilitar o deploy na AWS.


<h2 id="back">Back-End: Rotas & Testes Unitários</h2>


<h3 id="routes">Rotas</h3>

| ROTA               | DESCRIÇÃO                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /api/primenumber</kbd>     | Calcula o número de números primos positivos menores que o número informado no [corpo da requisição.](#post-detail)

**Requisição**  
Exemplo de requisição para calcular o número de números primos positivos menores que 10.

```json
{
  "k": "10"
}
```

**Resposta**   
Exemplo de resposta para a requisição acima, contendo o número de números primos positivos menores que 10 e o tempo de execução da requisição (em segundos), além do status HTTP 200.
```json
{
    "result": 4,
    "timeElapsed": "0.000003"
}
```

Caso ocorra algum erro durante a requisição, será retornado um status HTTP 400 ou 500 e uma mensagem de erro:
```json
{
    "errorMessage": "errorMsg"
}
```

<h3>Testes Unitários</h3>

Para executar os testes unitários do Back-End, um pré requisito é ter o **Maven** instalado na máquina. Para executar os testes, basta executar o comando abaixo dentro da pasta do Back-End 

(`/back`)

```bash
mvn test
```

<h2 id="front">Front-End: Imagens</h2>
<h1 align="center">
    <img src="https://i.imgur.com/8tU6iIs.png" width="600"/>
    <img src="https://i.imgur.com/1QwzII6.png" width="600"/>
    <img src="https://i.imgur.com/AWnazaO.png" width="600"/>
    <img src="https://i.imgur.com/giFuxZa.png" width="600"/>
</h1>

<h2 id="local">Como executar localmente</h2>

Para executar o projeto localmente, um pré requisito é ter o **Docker** e o **Docker Compose** instalados na máquina. Após a instalação, basta executar o comando abaixo na raiz do projeto:

```bash
docker compose up
# ou, para executar em detach mode (no background):
docker compose -d up
```

Após a execução do comando, o Front-End estará disponível em `http://localhost:5173` e o Back-End em `http://localhost:8080/api/primenumber`, onde é possível realizar a requisição descrita [aqui.](#routes)

O comando abaixo pode ser utilizado para parar a execução dos containers, deve ser executado na raiz do projeto:
```bash
docker compose down
```

É possivel também executar o Front-End e o Back-End separadamente, para isso, basta executar o comando abaixo na raiz do projeto:
```bash
# Caso queira executar apenas o Front-End
docker compose up frontend #(ou docker compose up frontend -d para executar em detach mode)

# Caso queira executar apenas o Back-End
docker compose up backend #(ou docker compose up backend -d para executar em detach mode)
```

<h2 id="aws">Deploy na AWS</h2>

Como citado na seção de [Tecnologias Utilizadas](#tech), o projeto foi hospedado na AWS. Para realizar o deploy do projeto na AWS, foi utilizado uma instância EC2, onde foi instalado o Docker e o Docker Compose para facilitar a execução dos containers. Além disso, as imagens dos containers foram hospedadas no Docker Hub para facilitar o deploy na AWS. Dentro da instança EC2, foi criado um diretório referente ao projeto e dentro deste foi criado o arquivo `docker-compose.yml` a seguir:

```yaml
services:
  backend:
    container_name: back-bridge-container
    image: avilamatheus/desafio-bridge-2024-backend-aws:1.0
    ports:
      - "8080:8080"
  frontend:
    container_name: front-bridge-container
    image: avilamatheus/desafio-bridge-2024-frontend-aws:1.0
    ports:
      - "5173:5173"
```

Assim, executando o comando `docker-compose up -d` na instância EC2, o Front-End estará disponível em `http://ec2-3-146-221-156.us-east-2.compute.amazonaws.com:5173` e o Back-End em `http://ec2-3-146-221-156.us-east-2.compute.amazonaws.com:8080/api/primenumber`, onde é possível realizar a requisição descrita [aqui.](#routes)