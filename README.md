<h1 align="center" style="font-weight: bold;">Desafio Bridge 2024 - Desenvolvimento Web</h1>

<p align="center">
  <a href="#tech">Tecnologias</a> • 
  <a href="#back">Back-End: Rotas & Testes Unitários</a> •
  <a href="#front">Front-End: Interface e Funcionalidades</a> •
  <a href="#local">Como executar localmente</a> •
  <a href="#aws">Deploy na AWS</a>
</p>

<p align="center">
    <b>Aplicação Web que recebe como entrada um número inteiro positivo k e retorna a quantidade de números primos positivos menores que k.</b>
</p>

<h2 id="tech">Tecnologias Utilizadas</h2>

**Front-End**

O Front-End do projeto foi desenvolvido utilizando várias tecnologias modernas para criar uma interface de usuário eficaz e responsiva:

- **Vite**: Vite é uma ferramenta de build rápida para projetos baseados em JavaScript e TypeScript. Ele oferece uma experiência de desenvolvimento extremamente rápida, com recarregamento rápido durante o desenvolvimento e build otimizado para produção.
  
- **React**: React é uma biblioteca JavaScript para construir interfaces de usuário. Ele permite criar componentes reutilizáveis e compostos, que são atualizados automaticamente conforme o estado do aplicativo muda. Isso torna mais fácil criar interfaces de usuário dinâmicas e interativas.

- **Bootstrap**: Bootstrap é um framework front-end popular para desenvolvimento de sites e aplicativos da web. Ele fornece uma coleção de componentes e estilos pré-construídos que podem ser facilmente personalizados e estilizados para atender às necessidades do projeto. O uso do Bootstrap ajuda a garantir uma aparência consistente e profissional em todo o aplicativo.

**Back-End**

O Back-End do projeto foi desenvolvido utilizando tecnologias Java modernas para criar uma API robusta e escalável:

- **Maven**: Maven é uma ferramenta de automação de construção e gerenciamento de dependências para projetos Java. Ele simplifica o processo de construção, teste e implantação de aplicativos Java, gerenciando automaticamente as dependências do projeto e fornecendo um sistema de construção configurável e flexível.

- **Java + Spring Boot**: Java é uma linguagem de programação amplamente utilizada para desenvolvimento de aplicativos empresariais e web. É conhecida por sua portabilidade, desempenho e robustez, tornando-a uma escolha popular para o desenvolvimento de aplicativos de back-end. Já o Spring Boot é um framework para desenvolvimento de aplicativos Java que simplifica o processo de criação de aplicativos Spring. Ele fornece uma configuração padrão pré-configurada, que permite iniciar rapidamente um novo projeto Spring com pouco esforço. O uso do Spring Boot ajuda a acelerar o desenvolvimento e a reduzir a quantidade de código necessário para criar uma API RESTful.


**Docker e Docker Compose**

O **Docker** é uma plataforma de software que permite a criação, o gerenciamento e a execução de aplicativos em contêineres. Os contêineres são ambientes isolados que contêm todos os recursos necessários para executar um aplicativo, incluindo código, bibliotecas e dependências. Isso garante que o aplicativo seja executado de maneira consistente, independentemente do ambiente em que esteja sendo executado.

No contexto deste projeto, o Docker foi usado para criar contêineres separados para o Front-End e o Back-End. Isso permite que cada parte do aplicativo seja encapsulada em seu próprio ambiente isolado, facilitando o desenvolvimento, o teste e a implantação.

O **Docker Compose** é uma ferramenta que simplifica o processo de definição e execução de aplicativos Docker compostos por vários contêineres. Ele usa um arquivo YAML chamado `docker-compose.yaml` para definir os serviços, as redes e os volumes necessários para o aplicativo. Isso simplifica a configuração e a execução de aplicativos compostos por vários contêineres, como o Front-End e o Back-End deste projeto.

**AWS**

A **Amazon Web Services (AWS)** é uma plataforma de computação em nuvem que oferece uma ampla variedade de serviços de computação, armazenamento, banco de dados, análise, machine learning, inteligência artificial, Internet das Coisas (IoT), segurança, entre outros. No contexto deste projeto, a AWS foi usada para hospedar e implantar o Front-End e o Back-End.

Para hospedar o projeto na AWS, foi criada uma instância EC2 (Elastic Compute Cloud), que é um serviço que oferece capacidade de computação redimensionável na nuvem. Dentro da instância EC2, o Docker foi usado para criar e executar os contêineres do Front-End e do Back-End em conjunto com o Docker Compose, que foi usado para facilitar a configuração e a execução dos contêineres.

As imagens dos contêineres foram hospedadas no **Docker Hub**, que é um serviço que permite o armazenamento de imagens de contêineres Docker em um repositório público ou privado. Isso facilita o compartilhamento e a distribuição das imagens entre diferentes ambientes e plataformas, como a AWS.

No geral, o uso do Docker e do Docker Compose simplifica o processo de desenvolvimento e implantação do projeto, enquanto a AWS fornece uma plataforma robusta e escalável para hospedar e executar o aplicativo. Essas tecnologias combinadas permitem que o projeto seja desenvolvido, testado e implantado de maneira eficiente e confiável.


<h2 id="back">Back-End: Rotas & Testes Unitários</h2>

<h3 id="routes">Rotas</h3>

A API Back-End possui uma rota principal para calcular a quantidade de números primos positivos menores que um determinado número informado no corpo da requisição:

| ROTA               | DESCRIÇÃO                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /api/primenumber</kbd>     | Calcula a quantidade de números primos positivos menores que o número informado no [corpo da requisição.](#post-detail)

<div id="post-detail">

**Requisição**  
A requisição deve ser feita utilizando o método POST para a rota `/api/primenumber` e deve conter um objeto JSON no corpo da requisição com o campo "k", que representa o número inteiro positivo para o qual deseja-se calcular a quantidade de números primos menores que ele.

Exemplo de requisição para calcular a quantidade de números primos positivos menores que 10:

```json
{
  "k": 10
}
```
</div>


**Resposta**   

Após receber a requisição, a API retorna um objeto JSON contendo a quantidade de números primos positivos menores que o número informado e o tempo de execução da requisição em segundos, além do status HTTP 200. O tempo de execução é fornecido para análise de desempenho da API.

Exemplo de resposta para a requisição acima:
```json
{
    "result": 4,
    "timeElapsed": "0.000003"
}
```

Caso ocorra algum erro durante a requisição, como uma requisição inválida ou um erro interno do servidor, será retornado um status HTTP 400 ou 500 (dependendo do erro) e uma mensagem de erro explicativa no corpo da resposta.
```json
{
    "errorMessage": "errorMsg"
}
```

<h3>Testes Unitários</h3>

Para garantir a qualidade e o funcionamento correto do Back-End, foram implementados testes unitários que verificam o comportamento das funcionalidades em diferentes cenários. Para executar os testes unitários do Back-End, é necessário ter o **Maven** instalado na máquina.

**Passos para executar os testes unitários:**

1. Certifique-se de que o Maven está instalado na sua máquina. Caso não esteja, você pode seguir o [tutorial de instalação do Maven](https://maven.apache.org/install.html).

2. Navegue até a pasta do Back-End do projeto (localizada em `/back`).

3. Execute o seguinte comando no terminal para iniciar a execução dos testes unitários:


```bash
mvn test
```

Este comando irá compilar o código-fonte, executar os testes unitários e gerar relatórios sobre os resultados dos testes.

<h2 id="front">Front-End: Interface e Funcionalidades</h2>

A interface do Front-End da aplicação foi projetada para fornecer uma experiência intuitiva ao usuário e garantir a correta execução dos cálculos de números primos. A aplicação realiza validações na entrada do usuário para garantir que apenas números inteiros sejam inseridos no formulário. Isso evita erros de cálculo e contribui para uma experiência mais fluida. Além disso, em caso de erro na requisição, uma mensagem específica é exibida na tela para informar o usuário sobre o problema e orientá-lo sobre como proceder.

Abaixo estão algumas imagens que demonstram a interface e as funcionalidades do Front-End:

<h3 align="center">
    Tela inicial da aplicação:
</h3>

<h3 align="center">
    <br>
    <img src="https://i.imgur.com/8tU6iIs.png" width="450"/>
    <img src="https://i.imgur.com/1QwzII6.png" width="450"/>
    <br>
</h3>

<p align="center">
  A tela inicial apresenta um formulário onde o usuário pode inserir um número inteiro para calcular a quantidade de números primos menores que ele.
<p>



<h3 align="center">
    Calculando o resultado:
</h3>

<h3 align="center">
    <br>
    <img src="https://i.imgur.com/AWnazaO.png" width="450"/>
    <img src="https://i.imgur.com/giFuxZa.png" width="450"/>
    <br>
</h3>

<p align="center">
  Enquanto o cálculo é realizado, uma animação de carregamento é exibida para indicar que a aplicação está processando a requisição. Após a conclusão do cálculo, o resultado é exibido na tela.
<p>


<h3 align="center">
    Em caso de erro:
</h3>

<h3 align="center">
    <br>
    <img src="https://i.imgur.com/GtCpTt2.png" width="450"/>
    <br>
</h3>

<p align="center">
  Se ocorrer um erro durante a requisição, uma mensagem de erro é exibida na tela para informar o usuário sobre o problema e orientá-lo sobre como proceder.
<p>

<h2 id="local">Como executar localmente</h2>

Para executar o projeto localmente, um pré requisito é ter o **Docker** e o **Docker Compose** instalados na máquina. O tutorial de instalação do Docker pode ser encontrado [neste link](https://docs.docker.com/get-docker/).


Após a instalação, basta executar o comando abaixo na raiz do projeto:

```bash
docker compose up
# ou, para executar em detach mode (no background):
docker compose -d up
```

Após a execução do comando, o Front-End estará disponível em [http://localhost:5173](http://localhost:5173) e o Back-End em [http://localhost:8080/api/primenumber](http://localhost:8080/api/primenumber), onde é possível realizar a requisição descrita [aqui.](#routes)

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

Como citado na seção de [Tecnologias Utilizadas](#tech), o projeto foi hospedado na AWS. Para realizar o deploy do projeto na AWS, foi utilizado uma instância EC2, onde foi instalado o Docker e o Docker Compose para facilitar a execução dos containers. 

As imagens dos containers foram hospedadas no Docker Hub para facilitar o deploy na AWS, a imagem do Front-End pode ser encontrada [aqui](https://hub.docker.com/r/avilamatheus/desafio-bridge-2024-frontend-aws) e a imagem do Back-End pode ser encontrada [aqui](https://hub.docker.com/r/avilamatheus/desafio-bridge-2024-backend-aws).

Dentro da instância EC2, foi criado um diretório referente ao projeto e dentro deste foi criado o arquivo `docker-compose.yaml` a seguir:

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

Assim, executando o comando `docker-compose up -d` na instância EC2, o Front-End estará disponível em [http://ec2-3-146-221-156.us-east-2.compute.amazonaws.com:5173](http://ec2-3-146-221-156.us-east-2.compute.amazonaws.com:5173) e o Back-End em [http://ec2-3-146-221-156.us-east-2.compute.amazonaws.com:8080/api/primenumber](http://ec2-3-146-221-156.us-east-2.compute.amazonaws.com:8080/api/primenumber), onde é possível realizar a requisição descrita [aqui.](#routes)