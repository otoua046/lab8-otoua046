package seg3x02.booksapigraphql.resolvers.types

data class CreateEmployeeInput(
    val firstName: String,
    val lastName: String,
    val position: String,
    val salary: Float
)