
class TravessiaCorda {
  // 1 - indo Leste
  // 0 - niguem atravessando
  // -1 - indo oeste
  private int direcao = 0;

  private int babuIndoLeste = 0;
  private int babuIndoOeste = 0;

  static final Object lock = new Object();

  public synchronized void irLeste() {
    if (this.direcao == 0) {
      direcao += 1;
    }
    babuIndoLeste += 1;
  }

  public synchronized void irOeste() {
    if (this.direcao == 0) {
      direcao -= 1;
    }
    babuIndoOeste += 1;
  }

  public synchronized void travessiaLivre() {
    if (babuIndoOeste > 0 && babuIndoLeste > 0)
      System.out.println("\n\nPAROU\n\n");
    if (babuIndoLeste > 0)
      babuIndoLeste -= 1;
    if (babuIndoOeste > 0)
      babuIndoOeste -= 1;
    if (babuIndoLeste == 0 && babuIndoOeste == 0)
      this.direcao = 0;
  }

  public synchronized int getDirecao() {
    return this.direcao;
  }
}

// Babuinos que vem do OESTE e querem ir para LESTE
class BabuinoOeste extends Thread {
  public int id; // numerados em pares
  private TravessiaCorda corda;

  public BabuinoOeste(TravessiaCorda corda, int id) {
    this.id = id;
    this.corda = corda;
  }

  public void run() {
    System.out.println("BABUINO " + this.id + " quer atravessar para LESTE");
    try {

      this.babuino_quer_atravessar();

      this.pegar_corda();

      // simula ele atravessando
      sleep(1);

      this.soltar_corda();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  void babuino_quer_atravessar() throws InterruptedException {
    synchronized (TravessiaCorda.lock) {
      while (this.corda.getDirecao() == -1) {
        TravessiaCorda.lock.wait();
      }
      this.corda.irLeste();
    }
  }

  void pegar_corda() {
    System.out.println(">>>>BABUINO " + this.id + " está atravessando para Leste");
  }

  void soltar_corda() {
    synchronized (TravessiaCorda.lock) {
      System.out.println("\tBABUIDO " + this.id + " CHEGOU!");
      this.corda.travessiaLivre();
      TravessiaCorda.lock.notifyAll();
    }
  }
}

// Babuinos quer vem do LESTE e querem ir para OESTE
class BabuinoLeste extends Thread {
  public int id; // numerados em impares
  private TravessiaCorda corda;

  public BabuinoLeste(TravessiaCorda corda, int id) {
    this.id = id;
    this.corda = corda;
  }

  public void run() {
    System.out.println("BABUINO " + this.id + " quer atravessar para OESTE");
    try {
      this.babuino_quer_atravessar();

      this.pegar_corda();

      // simula ele atravessando
      sleep(1);

      this.soltar_corda();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  void babuino_quer_atravessar() throws InterruptedException {
    synchronized (TravessiaCorda.lock) {
      while (this.corda.getDirecao() == 1) {
        TravessiaCorda.lock.wait();
      }
      this.corda.irOeste();
    }
  }

  void pegar_corda() {
    System.out.println(">>>>BABUINO " + this.id + " está atravessando para OESTE");
  }

  void soltar_corda() {
    synchronized (TravessiaCorda.lock) {
      System.out.println("\tBABUINO " + this.id + " CHEGOU!");
      this.corda.travessiaLivre();
      TravessiaCorda.lock.notifyAll();
    }
  }
}

class GeradorBabuinoOeste extends Thread {

  protected int id = 0;
  private int quantidade;
  private BabuinoOeste[] babuOeste;
  private TravessiaCorda corda;

  public GeradorBabuinoOeste(TravessiaCorda corda, int quantidade) {
    this.quantidade = quantidade;
    this.babuOeste = new BabuinoOeste[quantidade];
    this.corda = corda;
  }

  public void run() {
    for (int i = 0; i < quantidade; i++) {
      babuOeste[i] = new BabuinoOeste(this.corda, this.id);
      babuOeste[i].start();
      this.id += 2;

      try {
        long tempoAleatorio = (long) (Math.random() * 4) + 1;
        sleep(tempoAleatorio);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    for (int i = 0; i < quantidade; i++) {
      try {
        babuOeste[i].join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

class GeradorBabuinoLeste extends Thread {

  private int id = 1;
  private int quantidade;
  private BabuinoLeste[] babuLeste;
  private TravessiaCorda corda;

  public GeradorBabuinoLeste(TravessiaCorda corda, int quantidade) {
    this.quantidade = quantidade;
    babuLeste = new BabuinoLeste[quantidade];
    this.corda = corda;
  }

  public void run() {
    for (int i = 0; i < quantidade; i++) {
      babuLeste[i] = new BabuinoLeste(this.corda, this.id);
      babuLeste[i].start();
      this.id += 2;

      try {
        long tempoAleatorio = (long) (Math.random() * 4) + 1;
        sleep(tempoAleatorio);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    for (int i = 0; i < quantidade; i++) {
      try {
        babuLeste[i].join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

public final class Babuinos {
  public static void main(String[] args) {

    int quantidade = Integer.parseInt(args[0]);

    TravessiaCorda corda = new TravessiaCorda();

    GeradorBabuinoLeste gerBabLeste = new GeradorBabuinoLeste(corda, quantidade);
    GeradorBabuinoOeste gerBabOeste = new GeradorBabuinoOeste(corda, quantidade);

    gerBabLeste.start();
    gerBabOeste.start();

    try {
      gerBabOeste.join();
      gerBabLeste.join();
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println("Fim da simulação");
  }

}