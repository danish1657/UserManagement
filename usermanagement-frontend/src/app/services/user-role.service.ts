import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserRole } from '../models/user-role';

@Injectable({
  providedIn: 'root'
})
export class UserRoleService {
  private apiUrl = 'http://localhost:8080/api/userroles'; // Update with your backend URL

  constructor(private http: HttpClient) { }

  getUserRoles(userId: number, unitId: number, timestamp: string): Observable<UserRole[]> {
	const url = `${this.apiUrl}/valid?userId=${userId}&unitId=${unitId}&timestamp=${timestamp}`;
	return this.http.get<UserRole[]>(url);
    return this.http.get<UserRole[]>(`${this.apiUrl}/${userId}/${unitId}/${timestamp}`);
  }

  createUserRole(userRole: UserRole): Observable<UserRole> {
    return this.http.post<UserRole>(this.apiUrl, userRole);
  }

  updateUserRole(userRoleId: number, userRole: UserRole): Observable<UserRole> {
    return this.http.put<UserRole>(`${this.apiUrl}/${userRoleId}`, userRole);
  }

  deleteUserRole(userRoleId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${userRoleId}`);
  }

  getValidUserRoles(userId: number, unitId: number, timestamp: string): Observable<UserRole[]> {
    return this.http.get<UserRole[]>(`${this.apiUrl}/valid`, {
      params: {
        userId: userId.toString(),
        unitId: unitId.toString(),
        timestamp: timestamp
      }
    });
  }
}
