# Uma abordagem de esteganografia com inteligência artificial

Este é um projeto feito para a disciplina de Segurança com o professor Nilson Mori, com o intuito de apresentar uma nova abordagem
para a esteganografia textual utilizando as inteligências artificiais do mercado, nesse caso, o Gemini✨.

## Info

O modelo proposto utiliza Inteligência
Artificial para mesclar o texto original com um
texto de base que será gerado.

> [!NOTE]
> Projeto feito durando o 6º período, na disciplina de segurança.

## Compilação

O programa funciona da seguinte maneira:

**Codificar:** Para codificar um texto basta rodar o script 'codificar.py', passar o texto no qual será escondido, um tema e então esperar, sua chave para decodificar será fornecida junto com um arquivo de texto com o texto final. Exemplo:
```
python codificar.py
```

**Decodificar:** Para decodificar o arquivo final de volta para o texto original basta rodar o script 'decodificar.py' com sua respectiva chave e arquivo de texto.
Exemplo:
```
python decodificar.py chave nomeArquivo.txt
```

## Informações importantes

Vale ressaltar que você precisará [criar](https://ai.google.dev/gemini-api/docs/api-key?hl=pt-br) e informar uma chave para a aplicação funcionar. No arquivo 'codificar.py'
informe sua chave no campo mostrado abaixo (api_key):

```
gemini = tools.ComunicationGemini(api_key, 30, "gemini-1.5-pro" )
```

Os outros dois campos da inicialização de 'CominicationGemini' definem respectivamente o tempo de espera entre 
as requisições e o modelo do Gemini escolhido.

Acesse [Google Gemini Models](https://ai.google.dev/gemini-api/docs/models/gemini?hl=pt-br) para saber mais sobre os modelos do Gemini que existem.
