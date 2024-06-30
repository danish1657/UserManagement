import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Unit } from '../models/unit';

@Injectable({
  providedIn: 'root'
})
export class UnitService {

  private apiUrl = 'http://localhost:8080/api/units';

  constructor(private http: HttpClient) { }

  // Existing method to get units
  getUnits(): Observable<Unit[]> {
    return this.http.get<Unit[]>(this.apiUrl);
  }

  // Method to create a unit (POST)
  createUnit(unit: Unit): Observable<Unit> {
    return this.http.post<Unit>(this.apiUrl, unit, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  // Method to update a unit (PUT)
  updateUnit(unitId: number, unit: Unit): Observable<Unit> {
    const url = `${this.apiUrl}/${unitId}`;
    return this.http.put<Unit>(url, unit, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    });
  }

  // Method to delete a unit (DELETE)
  deleteUnit(unitId: number): Observable<void> {
    const url = `${this.apiUrl}/${unitId}`;
    return this.http.delete<void>(url);
  }
}
