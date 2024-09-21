import time
import google.generativeai as genai

class ComunicationGemini:
  model = None
  start_request = 0
  end_request = 0
  sleep_time = 3
  conteudo_final = []

  def __init__( self, api_key, sleep_timeout, gemini_model ) -> None:
    genai.configure( api_key = api_key )
    self.sleep_time = sleep_timeout
    self.model = genai.GenerativeModel( gemini_model )


  def criar_titulo( self, tema ):
    self.start_request = time.time()

    time.sleep(self.get_tempo_sleep())

    pedido = f'Preciso que você escreva um titulo para um texto sobre o tema "{tema}". Responda com apenas um título de no máximo 30 palavras'

    response =  self.model.generate_content(
      pedido,
      generation_config=genai.types.GenerationConfig
      (
        max_output_tokens=50,
        temperature=2
    ))

    self.conteudo_final.append( response.text )

    self.end_request = time.time()
    return response.text
  
  def criar_paragrafo( self, tema, palavra ):
    self.start_request = time.time()

    time.sleep(self.get_tempo_sleep())

    pedido = f'Preciso que você escreva um paragrafo (curto, de até 100 palavras) sobre o tema "{tema}" que contenha a palavra "{palavra}". Este paragrafo será inserido apos o texto abaixo e precisa fazer sentido com o texto (inicio texto){'\n\n'.join(self.conteudo_final)}(final texto)'

    response =  self.model.generate_content(
      pedido,
      generation_config=genai.types.GenerationConfig
      (
        max_output_tokens=150,
        temperature=1.3
    ))

    texto_gerado = response.text.replace('*','')

    self.conteudo_final.append( texto_gerado )

    self.end_request = time.time()
    return texto_gerado
  
  def get_tempo_sleep( self ):
    tempo_decorrido = self.end_request - self.start_request

    if tempo_decorrido < 0: tempo_decorrido = 0
    elif tempo_decorrido > self.sleep_time: tempo_decorrido = self.sleep_time 
      
    tempo_sleep = self.sleep_time - tempo_decorrido
    return tempo_sleep

  def procura_posicao( self ,texto, palavra ):
    partes = texto.replace('*','').replace(',','').replace('"','').replace("'",'').replace('.','').replace(';','').replace(':','').split(' ')
    indice = None
    for i, parte in enumerate( partes ):
      if parte.casefold().find( palavra.casefold() ) != -1:
        indice = i
        break
    return indice