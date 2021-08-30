package sisplan
class Plantio {
    String nome
    Integer umidade
    Integer erro

    static constraints = {
        umidade(range:0..100)
        erro(range:0..10)
    }
}
