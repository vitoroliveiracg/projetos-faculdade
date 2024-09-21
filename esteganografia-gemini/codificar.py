import tools
import time

api_key = 'Sua chave da API'
gemini = tools.ComunicationGemini(api_key, 30, "gemini-1.5-pro" )

# USER INPUTS
texto_segredo = input("Digite o seu segredo: ")
tema = input("Tema do texto: ")

palavras = texto_segredo.split(' ')

posicoes = []

gemini.criar_titulo( tema )
tamanho_total = len( palavras )
inicio_execucao = time.time()
for paragrafo, palavra in enumerate( palavras ):
  print( f"( {(paragrafo + 1)} / {tamanho_total} ) ({((paragrafo + 1)/tamanho_total)*100}%)" )
  try:
    texto_resposta = gemini.criar_paragrafo( tema=tema, palavra=palavra )
  
    posicao = gemini.procura_posicao( texto_resposta, palavra )

    posicoes.append(f"p{paragrafo}c{posicao}")
  except Exception: 
    pass


print("--->criando arquivo")
with open('conteudoSaida.txt', 'w', encoding='utf-8') as arquivo_txt:
  conteudo_final = gemini.conteudo_final
  texto = '\n\n'.join(conteudo_final)
  arquivo_txt.write(texto)

final_execucao = time.time()

tempo_total_execucao = final_execucao - inicio_execucao
print(f"tempo total de execucao: {tempo_total_execucao}")

print("--->Chave segredo:")
posicoes_finais = "".join(posicoes)
print( posicoes_finais )