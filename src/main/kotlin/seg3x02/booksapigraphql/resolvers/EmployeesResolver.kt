
package seg3x02.booksapigraphql.resolvers

import org.springframework.stereotype.Controller
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.Argument
import seg3x02.booksapigraphql.entity.Employee
import seg3x02.booksapigraphql.repository.EmployeeRepository
import seg3x02.booksapigraphql.resolvers.types.CreateEmployeeInput
import java.util.UUID

@Controller
class EmployeesResolver(private val employeeRepository: EmployeeRepository) {

    @QueryMapping
    fun employees(): List<Employee> = employeeRepository.findAll()

    @QueryMapping
    fun employeeById(@Argument id: String): Employee? = employeeRepository.findById(id).orElse(null)

    @MutationMapping
    fun createEmployee(@Argument input: CreateEmployeeInput): Employee {
        val employee = Employee(
            id = UUID.randomUUID().toString(),
            firstName = input.firstName,
            lastName = input.lastName,
            position = input.position,
            salary = input.salary
        )
        employeeRepository.save(employee)
        return employee
    }

    @MutationMapping
    fun deleteEmployee(@Argument id: String): Boolean {
        return if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id)
            true
        } else {
            false
        }
    }

    @MutationMapping
    fun updateEmployee(@Argument id: String, @Argument input: CreateEmployeeInput): Employee? {
        val employee = employeeRepository.findById(id).orElse(null) ?: return null
        employee.apply {
            firstName = input.firstName
            lastName = input.lastName
            position = input.position
            salary = input.salary
        }
        employeeRepository.save(employee)
        return employee
    }
}
