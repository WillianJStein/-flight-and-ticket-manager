import java.util.ArrayList;
import java.util.List;

class Aeroporto {
    private String nome;
    private String codigo;
    private String localizacao;

    public Aeroporto(String nome, String codigo, String localizacao) {
        this.nome = nome;
        this.codigo = codigo;
        this.localizacao = localizacao;
    }

    public String getNome() { return nome; }
    public String getCodigo() { return codigo; }
    public String getLocalizacao() { return localizacao; }
}

class Voo {
    private String codigo;
    private int totalAssentos;
    private int assentosOcupados;
    private Aeroporto aeroportoOrigem;
    private Aeroporto aeroportoDestino;

    public Voo(String codigo, int totalAssentos, Aeroporto aeroportoOrigem, Aeroporto aeroportoDestino) {
        this.codigo = codigo;
        this.totalAssentos = totalAssentos;
        this.assentosOcupados = 0;
        this.aeroportoOrigem = aeroportoOrigem;
        this.aeroportoDestino = aeroportoDestino;
    }

    public void reservarAssento() {
        if (assentosOcupados < totalAssentos) {
            assentosOcupados++;
        } else {
            throw new RuntimeException("Não há assentos disponíveis para este voo.");
        }
    }

    public int getAssentosDisponiveis() {
        return totalAssentos - assentosOcupados;
    }

    public String getCodigo() { return codigo; }
    public Aeroporto getAeroportoOrigem() { return aeroportoOrigem; }
    public Aeroporto getAeroportoDestino() { return aeroportoDestino; }
}

class Passagem {
    private static final double TAXA_EMBARQUE = 50.0;
    private Voo voo;
    private String nomePassageiro;
    private double preco;

    public Passagem(Voo voo, String nomePassageiro, double preco) {
        this.voo = voo;
        this.nomePassageiro = nomePassageiro;
        this.preco = preco;
    }

    public double calcularPrecoTotal() {
        return preco + TAXA_EMBARQUE;
    }

    public String getNomePassageiro() { return nomePassageiro; }
    public Voo getVoo() { return voo; }
}

class CompanhiaAerea {
    private String nome;
    private List<Voo> voos;

    public CompanhiaAerea(String nome) {
        this.nome = nome;
        this.voos = new ArrayList<>();
    }

    public void adicionarVoo(Voo voo) {
        voos.add(voo);
    }

    public String getNome() { return nome; }
    public List<Voo> getVoos() { return voos; }
}

public class Main {
    public static void main(String[] args) {
        // Criando aeroportos
        Aeroporto florianopolisInternacional = new Aeroporto("Aeroporto Internacional de Florianópolis", "FLN", "Florianópolis");
        Aeroporto florianopolisHercilioLuz = new Aeroporto("Aeroporto Hercílio Luz", "SBFL", "Florianópolis");

        // Criando companhia aérea
        CompanhiaAerea companhia = new CompanhiaAerea("Azul");

        // Criando voo
        Voo voo = new Voo("AZ1234", 150, florianopolisInternacional, florianopolisHercilioLuz);
        companhia.adicionarVoo(voo);

        // Criando passagens
        Passagem passagem1 = new Passagem(voo, "João Silva", 300.0);
        Passagem passagem2 = new Passagem(voo, "Maria Souza", 300.0);

        // Testando reserva de assentos
        System.out.println("Assentos disponíveis antes das reservas: " + voo.getAssentosDisponiveis());
        voo.reservarAssento();
        voo.reservarAssento();
        System.out.println("Assentos disponíveis após as reservas: " + voo.getAssentosDisponiveis());

        // Testando cálculo de preço total
        System.out.println("Preço total da passagem de " + passagem1.getNomePassageiro() + ": R$" + passagem1.calcularPrecoTotal());

        // Exibindo informações do voo
        System.out.println("Voo " + voo.getCodigo() + " de " + 
                           voo.getAeroportoOrigem().getNome() + " (" + voo.getAeroportoOrigem().getCodigo() + ") para " + 
                           voo.getAeroportoDestino().getNome() + " (" + voo.getAeroportoDestino().getCodigo() + ")");
    }
}