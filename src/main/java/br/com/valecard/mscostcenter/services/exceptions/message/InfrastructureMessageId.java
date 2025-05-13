package br.com.valecard.mscostcenter.services.exceptions.message;

public final class InfrastructureMessageId {

    public static final String EXCEPTION_INTERNAL_ERROR = "Ocorreu um erro interno inesperado no sistema. " +
            "Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

    public static final String EXCEPTION_WRONG_VALUE = "A propriedade {%s} recebeu o valor {%s}, que é de um tipo " +
            "inválido. Corrija e informe um valor compatível com o tipo %s.";

    public static final String EXCEPTION_INVALID_BODY = "O corpo da requisição está inválido. Verifique erro de " +
            "sintaxe.";

    public static final String EXCEPTION_INVALID_PARAMETER = "O parâmetro de URL {%s} recebeu o valor {%s},  que é " +
            "de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.";

    public static final String EXCEPTION_RESOURCE_NOT_FOUND = "O recurso {%s}, que você tentou acessar, é inexistente.";

    public static final String EXCEPTION_INVALID_DATA = "Um ou mais campos estão inválidos. Faça o preenchimento " +
            "correto e tente novamente.";

    public static final String EXCEPTION_REQUIRED_NOT_FOUND = "A coluna {%s} é obrigatória e não pode ser nula.";

    private InfrastructureMessageId() {
    }

}
