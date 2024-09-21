import sys

#PASSAR A CHAVE DPS O NOME DO ARQUIVO
chave = sys.argv[1]
nome_arquivo = sys.argv[2]

posicoes = []

chave = chave.split('p')[1:]
for item in chave:
  posicoes.append(item[2:])

paragrafos = []
with open(nome_arquivo, 'r', encoding='utf-8') as arquivo_texto:
  paragrafos = ''.join(arquivo_texto.readlines())

paragrafos = paragrafos.split('\n\n')
paragrafos = paragrafos[1:]

texto_final = []

for i, paragrafo in enumerate(paragrafos):
  paragrafo = paragrafo.replace('\n','')
  palavras = paragrafo.split(' ')
  palavra = palavras[int(posicoes[i])]
  texto_final.append(palavra)

texto_final = ' '.join(texto_final)
print(texto_final)