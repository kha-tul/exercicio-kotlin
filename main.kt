
enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(var nome: String,var username:String,var senha:String, var formacao: Formacao=Formacao()){
    override fun toString(): String {
    return """
        
        nome: '$nome',
        username: '$username',
        senha: '$senha',
        formacao: $formacao,
        
    """.trimMargin()
    }
}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60, val nivel: Nivel)

enum class ConteudoFormacao(
    val nome:String,
    val conteudosObrigatorios: MutableList<ConteudoEducacional>
){
    BACKEND(
        nome="Backend",
        conteudosObrigatorios = mutableListOf(
            ConteudoEducacional(nome = "Modelagem de Dados", duracao = 86 , nivel = Nivel.BASICO),
            ConteudoEducacional(nome = "Desenvolvimento Ágil", duracao = 102, nivel = Nivel.INTERMEDIARIO),
            ConteudoEducacional(nome = "Comunicação", duracao = 102, nivel = Nivel.DIFICIL)
        )
    ),
    FRONTEND(
        nome = "Frontend",
        conteudosObrigatorios = mutableListOf()
    );
}

data class Formacao(
    val nome: String = "",
    var conteudos: List<ConteudoEducacional> = mutableListOf()
) {

    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        usuario.formacao = Formacao(nome = nome, conteudos = conteudos)
        inscritos.add(usuario)
        println("${usuario.nome} foi matriculado na formação de $nome")
    }
    fun listarAlunos(){
        println("""
            ________________
            Id  - Aluno
            ________________
        """.trimIndent())
        inscritos.forEachIndexed{index, aluno ->
            println("${index.inc()} - $aluno")
        }
    }
}

fun main(args:Array<String>) {
    val usuarioAna = Usuario(
        "Ana Cruz",
        "AnaCruz",
        "324384343"
    )

    val backend = Formacao(
        ConteudoFormacao.BACKEND.nome,
        ConteudoFormacao.BACKEND.conteudosObrigatorios
    )
    val frontend = Formacao(
        ConteudoFormacao.BACKEND.nome,
        ConteudoFormacao.BACKEND.conteudosObrigatorios
    )

    backend.matricular(usuario = usuarioAna)

    val usuarioPenelope = Usuario(
        "Penelope",
        "peneloq",
        "34837233"
    )

    backend.matricular(usuarioPenelope)

    println(usuarioPenelope)

    backend.listarAlunos()

}