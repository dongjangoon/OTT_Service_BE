package elice.team5th.elice.team5h.common.exception

enum class ErrorType(
    val code: Int
) {
    INVALID_REQUEST(0),

    NOT_ALLOWED(1000),

    UNAUTHORIZED(2000),
    TOKEN_VALIDATION_FAILED(2001),

    NOT_FOUND(3000),
    USER_NOT_FOUND(3001),

    CONFLICT(4000),

    SERVER_ERROR(10000)
}
