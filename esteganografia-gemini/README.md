# Passagem de Babuínos

Este é um projeto em Java que simula a travessia de babuínos em um desfiladeiro montanhoso, evitando situações de deadlock. 
O projeto é uma brincadeira do professor para mostrar que até babuinos agricanos conseguiriam aprender sobre deadlocks.

## Descrição

O experimento consiste em babuínos atravessando um desfiladeiro utilizando uma corda estendida de uma extremidade à outra. 
Os babuínos podem atravessar o desfiladeiro apenas na mesma direção. 
No entanto, quando babuínos em direções opostas tentam atravessar simultaneamente, pode ocorrer um deadlock. 
Para evitar essa situação, os babuínos devem esperar até que a corda esteja livre e nenhum outro babuíno esteja atravessando na direção oposta.

> [!NOTE]
> Projeto feito durando o 6º período, na disciplina de programação concorrente e paralela.

## Especificação

O programa funciona da seguinte maneira:

1. **Execução:** O programa é executável no terminal Linux. As instruções de compilação estão na próxima seção.

2. **Parâmetros:** O programa recebe como parâmetro um número inteiro indicando quantos babuínos serão gerados para cada lado da travessia.

3. **Threads Auxiliares:** Duas threads auxiliares chamadas "GeradorBabuinosOeste" e "GeradorBabuinosLeste" são responsáveis por gerar continuamente os babuínos indo para cada direção até a quantidade especificada. Cada babuíno é gerado em um intervalo de tempo aleatório entre 1ms e 4ms.

4. **Numeração dos Babuínos:** Babuínos indo em direção a oeste são numerados a partir de números ímpares começando em 1, enquanto babuínos indo a leste são numerados a partir de números pares começando em 0.

5. **Threads Individuais:** Cada babuíno gerado é uma thread. Portanto, se houver 1000 babuínos para atravessar, serão criadas 2000 threads.

6. **Travessia Segura:** Não pode haver babuínos usando a corda em direções diferentes ao mesmo tempo.

7. **Procedimentos:** Cada babuíno chama o procedimento `babuino_quer_atravessar` para verificar se pode atravessar a corda. Se permitido, o babuíno chama o procedimento `pegar_corda` para iniciar a travessia e `soltar_corda` ao terminar.

8. **Informações Impressas:** O programa imprime informações sobre o estado dos babuínos durante a travessia, incluindo quando um babuíno está querendo usar a corda, quando ele começa a atravessar e quando termina.

### Exemplo de Saída

```
Início da simulação: 3 babuínos para cada lado
Babuíno 0 quer atravessar para LESTE
Babuíno 1 quer atravessar para OESTE
Babuíno 2 quer atravessar para LESTE
Babuíno 4 quer atravessar para LESTE
Babuíno 3 quer atravessar para OESTE
Babuíno 5 quer atravessar para OESTE
>>> BABUÍNO 0 está atravessando para LESTE
>>> BABUÍNO 2 está atravessando para LESTE
 BABUÍNO 0 chegou!
 BABUÍNO 2 chegou!
>>> BABUÍNO 1 está atravessando para OESTE
 BABUÍNO 1 chegou!
>>> BABUÍNO 3 está atravessando para OESTE
>>> BABUÍNO 5 está atravessando para OESTE
 BABUÍNO 4 chegou!
 BABUÍNO 5 chegou!
>>> BABUÍNO 4 está atravessando para LESTE
 BABUÍNO 5 chegou!
Fim da simulação
```

## Modo de Compilação

Para compilar o programa, siga os seguintes passos:

1. **Código:** Execute o seguinte comando para compilar o arquivo `Babuinos.java`:

```
javac Babuinos.java
```

2. **Execução:** Após a compilação, execute o programa da seguinte maneira, substituindo `[quantidade de babuinos]` pelo número desejado de babuínos:

```
java Babuinos [quantidade de babuinos]
```

> é possível que algum problema ocorre nessa etapa, e você poderá substituir o código acima por:
> 
> `java Babuinos.class [quantidade de babuinos]`.

### Referência
[Oracle Java Concorrencia](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
