
import { Injectable } from '@angular/core';
import { Apollo, gql } from 'apollo-angular';
import { Observable } from 'rxjs';

const GET_EMPLOYEES = gql`
  query {
    employees {
      id
      firstName
      lastName
      position
      salary
    }
  }
`;

const CREATE_EMPLOYEE = gql`
  mutation ($input: CreateEmployeeInput!) {
    createEmployee(input: $input) {
      id
      firstName
      lastName
      position
      salary
    }
  }
`;

@Injectable({
  providedIn: 'root',
})
export class EmployeesService {
  constructor(private apollo: Apollo) {}

  getEmployees(): Observable<any> {
    return this.apollo.query({
      query: GET_EMPLOYEES,
    });
  }

  createEmployee(input: any): Observable<any> {
    return this.apollo.mutate({
      mutation: CREATE_EMPLOYEE,
      variables: {
        input,
      },
    });
  }
}
