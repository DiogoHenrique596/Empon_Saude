# ms-centro-custo
Backend - Service Interfaces, SpringbootApp

# Tecnologias

- [java 21.0.6](https://www.oracle.com/br/java/technologies/javase/jdk21-archive-downloads.html)
- [Gradle 8.13](https://gradle.org/next-steps/?version=8.13&format=all)

# Configuração das Variáveis de Ambiente

Para executar este projeto, é necessário configurar as variáveis de ambiente do Java e do Gradle.
Isso garante que as ferramentas necessárias estejam disponíveis no seu sistema.

- Java: Certifique-se de que o JAVA_HOME aponte para o diretório de instalação do JDK.
- Gradle: Configure a variável GRADLE_HOME para apontar para o diretório de instalação do Gradle,
  e adicione o diretório bin do Gradle ao PATH. Após a configuração,
  reinicie o terminal para que as alterações tenham efeito.

# Build

```
./gradlew clean build

```

# Executar

A primeira vez ao executar o projeto na sua maquina deve executar o comando *gradle clean build*

para executar o projeto sem o *debug* somente rodar o comando

```
./gradlew bootRun --args='--spring.profiles.active=local'

```
Ao executar o comando para iniciar o projeto, será necessário escolher qual arquivo de propriedades (.properties)
você deseja utilizar. O projeto permite apontar para diferentes ambientes,
como DEV, QA ou PRD. No exemplo abaixo, estamos utilizando as configurações do ambiente de QA:



Para executar o projeto em modo *debug* (é necessário anexar um *debugger* na porta 5005):

```
gradle bootRun --args='--spring.profiles.active=local' --debug-jvm

```
