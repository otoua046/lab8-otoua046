
package seg3x02.booksapigraphql.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "employees")
data class Employee(
    @Id var id: String = "",
    var firstName: String = "",
    var lastName: String = "",
    var position: String = "",
    var salary: Float = 0.0f
)
