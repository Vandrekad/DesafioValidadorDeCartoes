# Desafio Validador de Cartões

Este projeto é uma aplicação simples em Kotlin que identifica a bandeira de um cartão de crédito (Visa, Mastercard, American Express, etc.) e valida se o número informado é potencialmente válido, utilizando o Algoritmo de Luhn.

## Funcionalidades
- **Identificação da bandeira**: Detecta a bandeira do cartão com base nos prefixos e no tamanho do número.
- **Validação do número**: Verifica se o número do cartão é válido conforme o Algoritmo de Luhn, utilizado mundialmente para validação de cartões de crédito.
- **Exemplo prático**: O programa já traz exemplos de cartões válidos e inválidos para teste.

## Lógica de Funcionamento

### 1. Identificação da Bandeira
A função `identifyCardBrand` recebe o número do cartão (com ou sem espaços) e verifica, através de expressões regulares, a qual bandeira ele pertence. Cada bandeira possui um ou mais prefixos e um tamanho específico:
- **Mastercard**: começa com 51-55, 16 dígitos
- **Visa**: começa com 4, 16 dígitos
- **American Express**: começa com 34 ou 37, 15 dígitos
- **Diners Club**: começa com 300-305, 14 dígitos
- **Discover**: começa com 6011, 16 dígitos
- **EnRoute**: começa com 2014 ou 2149, 15 dígitos
- **JCB**: começa com 35, 16 dígitos
- **Voyager**: começa com 8699, 15 dígitos
- **HiperCard**: começa com 6062, 16 dígitos
- **Aura**: começa com 50, 16 dígitos

### 2. Validação do Número (Algoritmo de Luhn)
A função `isValidCardNumber` implementa o Algoritmo de Luhn, que consiste em:
- Remover espaços do número.
- Multiplicar por 2 os dígitos em posições alternadas, da direita para a esquerda.
- Se o resultado da multiplicação for maior que 9, subtrair 9.
- Somar todos os dígitos.
- Se o total for divisível por 10, o número é considerado válido.

### 3. Exemplo de Uso
O programa principal (`main`) percorre uma lista de cartões de exemplo, exibindo no console:
- A bandeira identificada
- Se o número é válido ou não

Cartões inválidos ou com bandeira desconhecida são informados adequadamente.

## Como Rodar o Projeto

### Pré-requisitos
- [Kotlin](https://kotlinlang.org/docs/getting-started.html) instalado na máquina
- (Opcional) IntelliJ IDEA ou outro editor compatível com Kotlin

### Passos para execução
1. Clone ou baixe este repositório.
2. Abra o terminal na pasta do projeto.
3. Execute o arquivo `Main.kt`:
   
   **Via linha de comando:**
   ```sh
   kotlinc src/Main.kt -include-runtime -d CartaoValidador.jar
   java -jar CartaoValidador.jar
   ```
   
   **Ou via IntelliJ IDEA:**
   - Abra o projeto.
   - Clique com o botão direito em `Main.kt` e selecione "Run 'MainKt'".

### Saída Esperada
O console exibirá para cada cartão:
```
Cartão: 5232 7661 6131 6539 => Bandeira: Mastercard | Válido: true
Cartão: 4111 1111 1111 1112 => Válido: false
...
```

## Personalização
Para testar outros cartões, basta adicionar/remover números na lista `cards` dentro da função `main`.

## Observações
- O projeto é didático e não deve ser usado em produção sem adaptações de segurança.
- O Algoritmo de Luhn não garante que o cartão existe, apenas que o número é potencialmente válido.

---

Desenvolvido para fins de estudo e prática de Kotlin.

