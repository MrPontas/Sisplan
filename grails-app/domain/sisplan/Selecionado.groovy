package sisplan

class Selecionado {
    Plantio plantio

    static constraints = {
    }
    static mapping = {
        id generator: 'sequence', params: [sequence: 'sequence_cliente']
    }
}
